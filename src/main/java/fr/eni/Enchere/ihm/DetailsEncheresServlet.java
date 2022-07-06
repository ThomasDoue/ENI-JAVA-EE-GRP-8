package fr.eni.Enchere.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.Enchere.bll.ArticleAVendreManager;
import fr.eni.Enchere.bll.BLLFactory;
import fr.eni.Enchere.bll.EnchereManager;
import fr.eni.Enchere.bo.DtoEnchereComplete;

/**
 * Servlet implementation class DetailsEncheres
 */
@WebServlet("/DetailsEncheres")
public class DetailsEncheresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleAVendreManager articleMger;
	private EnchereManager enchereMger;
	public void init() throws ServletException {
		articleMger = BLLFactory.getArticleAVendreManager();	
		enchereMger = BLLFactory.getEnchereManager();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DtoEnchereComplete ObjetRetour = new DtoEnchereComplete();
		System.out.println(("valeur id enchere" + request.getParameter("IdArticle")));
		try {
			ObjetRetour = enchereMger.selectVenteById(Integer.parseInt(request.getParameter("IdArticle")));
			request.setAttribute("Enchere", ObjetRetour);
			
//			if(articleMger.FinDEnchere(Integer.parseInt(request.getParameter("IdArticle"))))
//				request.setAttribute("estFini", "true");
//			else
//				request.setAttribute("estFini", "false");
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Retour details vente dans servlet : " + ObjetRetour.toString());
		request.getRequestDispatcher("/WEB-INF/pages/DetailsEnchere.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println("Id de la session utilisateur : " + session.getAttribute( "idUtilisateur"));
		System.out.println("offre de l'utilisateur : " + request.getParameter( "offre"));
		System.out.println("Numéro de l'article : " + request.getParameter("noArticle"));
		//creation de la fonction qui verifie le solde retour bouléan 
		//********************************************************************************
		// retourne un booléen updatePrixVenteEnchere
		
		
		
		
		//********************************************************************************
		try {
			enchereMger.updatePrixVenteEnchere(Integer.parseInt(request.getParameter("noArticle")), Integer.parseInt(request.getParameter("offre")),(int)session.getAttribute( "idUtilisateur"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
