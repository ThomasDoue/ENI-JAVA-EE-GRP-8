package fr.eni.Enchere.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.Enchere.bll.BLLFactory;
import fr.eni.Enchere.bll.EnchereManager;
import fr.eni.Enchere.bo.DtoEnchereComplete;

/**
 * Servlet implementation class DetailsEncheres
 */
@WebServlet("/DetailsEncheres")
public class DetailsEncheresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EnchereManager enchereMger;
	
	public void init() throws ServletException {
		enchereMger = BLLFactory.getEnchereManager();		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DtoEnchereComplete ObjetRetour = new DtoEnchereComplete();
		System.out.println(("valeur id enchere" + request.getParameter("IdEnchere")));
		try {
			ObjetRetour = enchereMger.SelectEnchereById(Integer.parseInt(request.getParameter("IdEnchere")));
			request.setAttribute("Enchere", ObjetRetour);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}