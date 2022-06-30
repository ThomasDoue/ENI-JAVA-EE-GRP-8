package fr.eni.Enchere.ihm;
import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet("/Inscription")
public class InscriptionUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilisateurMger;
	
	@Override
	public void init() throws ServletException {
		utilisateurMger = BLLFactory.getUtilisateurManager();
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("/WEB-INF/pages/InscriptionUtilisateur.jsp").forward(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idUtilisateur = 0;
		System.out.println("mdp : " + req.getParameter("MotDePasse") + " confirmation : " + req.getParameter("Confirmation"));
		if(!req.getParameter("MotDePasse").equals(req.getParameter("Confirmation"))) 
		{
			req.setAttribute("verifmdp",false);
			PrintWriter out = resp.getWriter();  
			resp.setContentType("text/html");  
			out.println("<script type=\"text/javascript\">");  
			out.println("alert('le mot de passe n est pas le meme');");  
			out.println("</script>");
			req.getRequestDispatcher("/WEB-INF/pages/InscriptionUtilisateur.jsp").forward(req, resp);
		}else {
			Utilisateur user = new Utilisateur(req.getParameter("Pseudo"),req.getParameter("Nom"),req.getParameter("Prenom"),req.getParameter("Email"),req.getParameter("Telephone"),req.getParameter("Rue"),req.getParameter("CodePostal"),req.getParameter("Ville"),req.getParameter("MotDePasse"));
			System.out.println("Servlet création utilisateurs : " + user.toString());
			try {
				utilisateurMger.InscriptionUtilisateur(user);
				idUtilisateur = utilisateurMger.verifConnect(user.getPseudo(),user.getMotDePasse());
				HttpSession session = req.getSession();
				session.setAttribute( "idUtilisateur", idUtilisateur);
				req.getRequestDispatcher("/WEB-INF/pages/accueil.jsp").forward(req, resp);
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}

