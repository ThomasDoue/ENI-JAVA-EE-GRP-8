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

@WebServlet("/ConnexionUtilisateurServlet")
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
		
			
		if ("createAccount".equals(req.getParameter("action"))) {
			req.getRequestDispatcher("/WEB-INF/pages/InscriptionUtilisateur.jsp").forward(req, resp);
			return;
		}
		req.getRequestDispatcher("/WEB-INF/pages/connexionUtilisateur.jsp").forward(req, resp);
	}

	/**
	 * Je créé une variable locale idUtilisateur qui prend pour valeur le numéro d'identifiant de l' utilisateur qui se connecte.
	 * Si cette valeur est égale à 0, cela signifie que l'utilisateur n'a pas été trouvé dans la base de donnée à travers la méthode
	 * 	"connect" dans "UtilisateurDaoImpl", dans ce cas là un message d'erreur informe l'utilisateur que son pseudo/email ou son mdp est erroné.
	 * Dans le cas où idUtilisateur est différent de 0 (il est induit que idUtilisateur ne peut qu'être > 0)
	 * 	on créé une session, le numero d'identifiant de l'utilisateur rentre en attribut de session sous le nom "idUtilisateur"
	 * L'utilisateur est redirigé vers la page d'accueil.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int idUtilisateur = 0;
		
		try {
			idUtilisateur = utilisateurMger.verifConnect(req.getParameter("identifier"), req.getParameter("password"));
			if (idUtilisateur == 0) {
				req.setAttribute("errorLogin", "Le nom d'utilisateur ou le mot de passe est incorrect");
				req.getRequestDispatcher("/WEB-INF/pages/connexionUtilisateur.jsp").forward(req, resp);
				return;
			} else {
				HttpSession session = req.getSession();
				session.setAttribute( "idUtilisateur", idUtilisateur);
				req.getRequestDispatcher("/WEB-INF/pages/accueil.jsp").forward(req, resp);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
}
