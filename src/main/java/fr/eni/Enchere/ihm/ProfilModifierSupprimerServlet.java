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
		Integer userID = 3;
		
		
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
			request.setAttribute("motdepassseactuel",UserAffichage.getMotDePasse());
			request.setAttribute("credit", UserAffichage.getCredit());
			
			
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
		
		// Création de l'attribut Action pour le JSP
        String action = request.getParameter("actionUtilisateur");
        
        System.out.println("je suis dans le post   :" +  action);
        // ?Action=Supprimer Dans le JSP
        if ("supprimer".equals(action)) {
        	System.out.println("supprimer");
            return;
        }

        // ?Action=Modifier Dans le JSP
        if ("enregister".equals(action)) {
           System.out.println("enregister");
            return;
        }
		
	}
			
	}


