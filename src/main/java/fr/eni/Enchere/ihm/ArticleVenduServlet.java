package fr.eni.Enchere.ihm;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.Enchere.bll.ArticleAVendreManager;
import fr.eni.Enchere.bll.BLLException;
import fr.eni.Enchere.bll.BLLFactory;
import fr.eni.Enchere.bll.EnchereManager;
import fr.eni.Enchere.bo.DtoEnchereComplete;
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
		DtoEnchereComplete ArticleVendu = new DtoEnchereComplete();
		try {
			ArticleVendu = enchereMger.selectVenteById(Integer.parseInt(req.getParameter("IdEnchere")));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (BLLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			req.setAttribute("DtoEnchereComplete", ArticleVendu); 
	}catch (Exception e) {
		e.printStackTrace();
		}
		req.getRequestDispatcher("/WEB-INF/pages/ArticleVendu.jsp").forward(req, resp);
	}
		
	

}
