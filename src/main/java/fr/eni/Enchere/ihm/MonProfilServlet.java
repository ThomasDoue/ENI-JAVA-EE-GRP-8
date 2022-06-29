package fr.eni.Enchere.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.Enchere.bll.BLLFactory;
import fr.eni.Enchere.bll.UtilisateurManager;

@WebServlet({"/RETOURFORMULAIRE"})
public class MonProfilServlet extends HttpServlet {
	private UtilisateurManager utilisateurMger;
	
	public void init() throws ServletException {
		utilisateurMger = BLLFactory.getUtilisateurManager();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/monProfilGuigui.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recuperation du numéro de l'utilisateur de la page de connection
//		utilisateurMger.selectUser(no_utilisateur);
		
		//retour des informations sur la pages MonProfilServet
		
	}

	
}

