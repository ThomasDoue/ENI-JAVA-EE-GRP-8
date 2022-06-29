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

@WebServlet({"/RetourFormulaire"})
public class MonProfilServlet extends HttpServlet {
	private UtilisateurManager utilisateurMger;
	
	public void init() throws ServletException {
		utilisateurMger = BLLFactory.getUtilisateurManager();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//recuperation du numéro de l'utilisateur de la page de connection par al session 
		Integer utilisateur = 1; 
//		utilisateurMger.selectUser(no_utilisateur);
		try {
			Utilisateur RetourProfil =utilisateurMger.selectUser(utilisateur);
			request.setAttribute("pseudo",RetourProfil.getPseudo());
			request.setAttribute("nom",RetourProfil.getNom());
			request.setAttribute("premon",RetourProfil.getPrenom());
			request.setAttribute("email",RetourProfil.getEmail());
			request.setAttribute("telephone",RetourProfil.getTelephone());
			request.setAttribute("rue",RetourProfil.getRue());
			request.setAttribute("codePostal",RetourProfil.getCodePostal());
			request.setAttribute("ville",RetourProfil.getVille());
			request.setAttribute("credit", RetourProfil.getCredit());
			System.out.println(RetourProfil.toString());
			
			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		request.getRequestDispatcher("/WEB-INF/pages/ProfilUtilisateur.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//recuperation du numéro de l'utilisateur de la page de connection par al session 
//		Integer utilisateur = 1; 
////		utilisateurMger.selectUser(no_utilisateur);
//		try {
//			Utilisateur RetourProfil =utilisateurMger.selectUser(utilisateur);
//			request.setAttribute("pseudo",RetourProfil.getPseudo());
//			request.setAttribute("nom",RetourProfil.getNom());
//			request.setAttribute("premon",RetourProfil.getPrenom());
//			request.setAttribute("email",RetourProfil.getEmail());
//			request.setAttribute("telephone",RetourProfil.getTelephone());
//			request.setAttribute("rue",RetourProfil.getRue());
//			request.setAttribute("codePostal",RetourProfil.getCodePostal());
//			request.setAttribute("ville",RetourProfil.getVille());
//			request.setAttribute("credit", RetourProfil.getCredit());
//			System.out.println(RetourProfil.toString());
//			
//			
//		} catch (BLLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		//retour des informations sur la pages MonProfilServet
		
		
		
		
		request.getRequestDispatcher("/WEB-INF/pages/ProfilUtilisateur.jsp").forward(request, response);
		
	}

	
}

