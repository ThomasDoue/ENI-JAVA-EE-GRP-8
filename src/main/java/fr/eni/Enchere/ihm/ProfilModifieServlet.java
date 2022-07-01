package fr.eni.Enchere.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.Enchere.bll.BLLException;
import fr.eni.Enchere.bll.BLLFactory;
import fr.eni.Enchere.bll.UtilisateurManager;
import fr.eni.Enchere.bo.Utilisateur;

@WebServlet ({"/ProfilUtilisateurModification"})
public class ProfilModifieServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UtilisateurManager  utilisateurManager;
	
	public void init() {
		utilisateurManager = BLLFactory.getUtilisateurManager();
				}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer no_utilisateur = 1;
		try {
			Utilisateur UserModif = utilisateurManager.selectUser(no_utilisateur);
			request.setAttribute( "pseudo", UserModif.getPseudo()); 
			request.setAttribute( "nom", UserModif.getNom());
			request.setAttribute("prenom", UserModif);
			request.setAttribute( "email", UserModif.getEmail());
			request.setAttribute( "telephone", UserModif.getTelephone());
			request.setAttribute( "rue", UserModif.getRue());
			request.setAttribute("codePostal", UserModif.getCodePostal());
			request.setAttribute("ville", UserModif.getVille());
			
			
			
			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		request.getRequestDispatcher("/WEB-INF/pages/profilBouton.jsp").forward(request, response);
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Récupération Paramètres Formulaire et insertion dans mon constructeur Utlisateur pour enregistement sur la base de donnée 
		Utilisateur usermodif = new Utilisateur(request.getParameter("pseudo"),
											  	request.getParameter("nom"),
											  	request.getParameter("prenom"),
											  	request.getParameter("email"), 
											  	request.getParameter("telephone"),
											  	request.getParameter("rue"),
											  	request.getParameter("codePostal"),
											  	request.getParameter("ville"));
		

	
	}
	
	
	

}
