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

@WebServlet({"","/ConnexionUtilisateurServlet"})
public class ConnexionUtilisateurServlet extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilisateurMger;
	
	@Override
	public void init() throws ServletException {
		utilisateurMger = BLLFactory.getUtilisateurManager();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if ("createAccount".equals(req.getParameter("action")))
			req.getRequestDispatcher("/WEB-INF/pages/InscriptionUtilisateur.jsp").forward(req, resp);
		
		req.getRequestDispatcher("/WEB-INF/pages/connexionUtilisateur.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			if (!utilisateurMger.verifLogin(req.getParameter("identifier"), req.getParameter("password"))) {
				req.setAttribute("errorLogin", "Le nom d'utilisateur ou le mot de passe est incorrect");
				req.getRequestDispatcher("/WEB-INF/pages/connexionUtilisateur.jsp").forward(req, resp);
				return;
			} else {
				resp.sendRedirect(req.getContextPath()+"/accueil");
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
}
