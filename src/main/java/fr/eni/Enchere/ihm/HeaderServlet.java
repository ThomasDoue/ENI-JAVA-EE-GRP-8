package fr.eni.Enchere.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/header")
public class HeaderServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	public void init() throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	  String action = req.getParameter("actionUtilisateur");
	  if ("encheres".equals(action))
		  resp.sendRedirect("accueil");
	  if ("vendreArticle".equals(action))
		  resp.sendRedirect("nouvelleVente");
	  if ("afficherProfil".equals(action))
		  resp.sendRedirect("RetourFormulaire");
	  if ("profilModifierSupprimer".equals(action))
		  resp.sendRedirect("ProfilModifierSupprimerServlet");
	  if("deconnexion".equals(action)) {
		  HttpSession session = req.getSession();
		  session.invalidate();
		  resp.sendRedirect("accueil");
	  }
	  if("connexion".equals(action)) {
		  resp.sendRedirect("ConnexionUtilisateurServlet");
	  }
	}

}
