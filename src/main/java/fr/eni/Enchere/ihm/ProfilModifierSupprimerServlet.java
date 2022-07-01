package fr.eni.Enchere.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.Enchere.bll.BLLException;
import fr.eni.Enchere.bll.BLLFactory;
import fr.eni.Enchere.bll.UtilisateurManager;
import fr.eni.Enchere.bo.Utilisateur;

@WebServlet("/ProfilModifierSupprimerServlet")
public class ProfilModifierSupprimerServlet extends HttpServlet {

	private UtilisateurManager  utilisateurManager;
	private Integer creditUser ;
	private boolean erreur;
	public void init() {
		utilisateurManager = BLLFactory.getUtilisateurManager();
				}
	
	/**
	 *recuperation des données de la base et affichage à l'utilisateur
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//envoie a la BDD du numéro utlisateur
		
		//*************************************
		//POUR TEST 
		Integer userID = 12;
		
		
		//creation d'une instance session vide 
		HttpSession session = request.getSession();
		//mise an place de id 3 a l'utlisateur
		session.setAttribute("No_utlisateur",userID);
		//***************************************
		
		//creation d'une instance session vide 
		//HttpSession session = request.getSession();
		//**session.getAttribute recupére le No_utlisateur;
		//Integer userID=(int)session.getAttribute("No_utilisateur");
		
		try {
			Utilisateur UserAffichage = utilisateurManager.selectUser(userID);
			request.setAttribute("pseudo", UserAffichage.getPseudo()); 
			request.setAttribute("nom", UserAffichage.getNom());
			request.setAttribute("prenom", UserAffichage.getPrenom());
			request.setAttribute("email", UserAffichage.getEmail());
			request.setAttribute("telephone", UserAffichage.getTelephone());
			request.setAttribute("rue", UserAffichage.getRue());
			request.setAttribute("codePostal", UserAffichage.getCodePostal());
			request.setAttribute("ville", UserAffichage.getVille());
			creditUser=UserAffichage.getCredit();
			request.setAttribute("credit", creditUser);
			if (erreur==true) {
			//remonter une erreur mots de passe ou confirmation incorecte
    		request.setAttribute("erreurMotdePasse", "Mot de passe nom valide");
			}
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/pages/profilModifierSupprimer.jsp").forward(request, response);
		
	}

	
	

	/**
	 *enregistement dans la base de donner ou suppertion du profil
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Récupération des attributs de session idUtilisateur
		HttpSession session = request.getSession();
//		int idUtilisateur = (int) session.getAttribute("idUtilisateur");
		int idUtilisateur = 12;
	
		// Création de l'attribut Action pour le JSP
        String action = request.getParameter("actionUtilisateur");
      
        /*
         *  ?Action=Supprimer Dans le JSP
         *  Suppresion du compte, suppresion des attributs de session, suppression de la session
         */
        if ("supprimer".equals(action)) {
        	try {
				utilisateurManager.supprimerUtilisateur(idUtilisateur);
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	session.invalidate();
			request.logout();
			
			response.sendRedirect("/WEB-INF/pages/accueil.jsp");
        }

        // ?Action=Modifier Dans le JSP
        if ("enregister".equals(action)) {
        try {
        	String nouveauMotDepasse=request.getParameter("nouveaumotdepasse");
        	String confirmationMotDePasse = request.getParameter("confirmation");
        	System.out.println(request.getParameter("email"));
        			//verrification du nouveau mots de passe 
        	if (nouveauMotDepasse.equals(confirmationMotDePasse)) {
			// Récupération Paramètres Formulaire et insertion dans mon constructeur Utlisateur pour enregistement sur la base de donnée 
			Utilisateur usermodif = new Utilisateur(idUtilisateur,
													request.getParameter("pseudo"),
												  	request.getParameter("nom"),
												  	request.getParameter("prenom"),
												  	request.getParameter("email"), 
												  	request.getParameter("telephone"),
												  	request.getParameter("rue"),
												  	request.getParameter("codePostal"),
												  	request.getParameter("ville"),
												  	nouveauMotDepasse,
												  	creditUser,false);
			
			utilisateurManager.miseAjourUtilisateur(usermodif);
			//retour sur la servlet RetourFormulaire
			response.sendRedirect("RetourFormulaire");
			
        	}else {
        		erreur =true;
        		//retour sur la servlet profilModifierSupprimer
        		response.sendRedirect("ProfilModifierSupprimerServlet");
        			}
        
        }catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        	
        }
		
	}
			
	}


