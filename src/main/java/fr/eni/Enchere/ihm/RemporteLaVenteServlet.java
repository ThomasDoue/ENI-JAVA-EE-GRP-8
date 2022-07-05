package fr.eni.Enchere.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.Enchere.bll.ArticleAVendreManager;
import fr.eni.Enchere.bll.BLLException;
import fr.eni.Enchere.bll.BLLFactory;
import fr.eni.Enchere.bll.UtilisateurManager;
import fr.eni.Enchere.bo.ArticleVendu;
import fr.eni.Enchere.bo.Retrait;
import fr.eni.Enchere.bo.Utilisateur;

@WebServlet("/remporteLaVente")
public class RemporteLaVenteServlet extends HttpServlet{

	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
//
//	private ArticleAVendreManager articleAVendreMger;
//	private RetraitManager retraitMger;
//	private UtilisateurManager utilisateurMger;
//	
//	public void init() throws ServletException {
//		articleAVendreMger = BLLFactory.getArticleAVendreManager();
//		retraitMger = BLLFactory.getRetraitMGer();
//		utilisateurMger = BLLFactory.getUtilisateurManager();
//	}
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		try {
//			ArticleVendu articleVendu = articleAVendreMger.selectArticleById(no_article);
//			Retrait retrait = retraitMger.selectRetraitByID(no_retrait);
//			Utilisateur vendeur = utilisateurMger.selectUser(no_utilisateur);
//			req.setAttribute("nomArticle", articleVendu.getNomArticle());
//			req.setAttribute("description", articleVendu.getDescription());
//			req.setAttribute("meilleureOffre", articleVendu.getPrixVente());
//			req.setAttribute("prixInitial", articleVendu.getPrixInitial());
//			req.setAttribute("rue", retrait.getRue());
//			req.setAttribute("codePostal", retrait.getCodePostal());
//			req.setAttribute("ville", retrait.getVille());
//			req.setAttribute("vendeur", vendeur.getPseudo());
//			req.setAttribute("tel", vendeur.getTelephone());
//		} catch (BLLException e) {
//			e.printStackTrace();
//		}
//		req.getRequestDispatcher("/WEB-INF/pages/remporteLaVente.jsp").forward(req, resp);
//		
//	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("accueil").forward(req, resp);
	}
	
	
	

}
