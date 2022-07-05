package fr.eni.Enchere.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.Enchere.bll.ArticleAVendreManager;
import fr.eni.Enchere.bll.ArticleAVendreManagerImpl;
import fr.eni.Enchere.bll.BLLException;
import fr.eni.Enchere.bll.BLLFactory;
import fr.eni.Enchere.bll.EnchereManager;
import fr.eni.Enchere.bo.ArticleVendu;
import fr.eni.Enchere.bo.DtoEnchereComplete;
import fr.eni.Enchere.bo.Utilisateur;
@WebServlet("/ArticleVendu")
public class ArticleVenduServlet extends HttpServlet {
	/**
	 * 
	
	 */
	private ArticleAVendreManager articleMger;
	private EnchereManager enchereMger;
	
	public void init() throws ServletException {
		articleMger = BLLFactory.getArticleAVendreManager();	
		enchereMger = BLLFactory.getEnchereManager();		
	}
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DtoEnchereComplete dto = new DtoEnchereComplete();
		try {
			dto = enchereMger.selectVenteById(Integer.parseInt(req.getParameter("IdEnchere")));
			req.setAttribute("Enchere", dto);
			
			if(articleMger.FinDEnchere(Integer.parseInt(req.getParameter("noArticle")))) {
				req.setAttribute("estFini", "true");
			req.setAttribute("pseudo", dto.getPseudo()); 
			req.setAttribute("nomArticle", dto.getNomArticle());
			req.setAttribute("description", dto.getDescription());
			req.setAttribute("PrixVente", dto.getPrixVente());
			req.setAttribute("MontantEnchere", dto.getNoEnchere());
			req.setAttribute("dateFinEnchere", dto.getDateFinEncheres());
			}
			else
				req.setAttribute("estFini", "false");
	}catch (Exception e) {
		e.printStackTrace();
		
		
		
		}
		req.getRequestDispatcher("/WEB-INF/pages/ArticleVendu.jsp").forward(req, resp);
	}
		
	

}
