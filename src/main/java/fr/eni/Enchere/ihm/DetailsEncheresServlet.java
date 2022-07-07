package fr.eni.Enchere.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	private int IdArticle = 0;
	private boolean boolErreur = false;
	public void init() throws ServletException {	
		enchereMger = BLLFactory.getEnchereManager();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DtoEnchereComplete ObjetRetour = new DtoEnchereComplete();
		System.out.println(("valeur id enchere" + request.getParameter("IdArticle")));
		System.out.println("bool erreur" + boolErreur);
		try {
			if(request.getParameter("IdArticle") != null) {
			ObjetRetour = enchereMger.selectVenteById(Integer.parseInt(request.getParameter("IdArticle")));
			}else {
				ObjetRetour = enchereMger.selectVenteById(IdArticle);
			}
			request.setAttribute("Enchere", ObjetRetour);
			request.setAttribute("Erreurs", boolErreur);
			if(request.getParameter("IdArticle") != null) {
				if(enchereMger.FinDEnchere(Integer.parseInt(request.getParameter("IdArticle"))))
					request.setAttribute("estFini", "true");
				else
					request.setAttribute("estFini", "false");
			}else {
				if(enchereMger.FinDEnchere(IdArticle))
					request.setAttribute("estFini", "true");
				else
					request.setAttribute("estFini", "false");
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		boolErreur = false;
		System.out.println("Retour details vente dans servlet : " + ObjetRetour.toString());
		request.getRequestDispatcher("/WEB-INF/pages/DetailsEnchere.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		String action = request.getParameter("actionUtilisateur");
		if("encherir".equals(action)) {
			try {
				HttpSession session = request.getSession();
				//creation de la fonction qui verifie le solde retour bouléan 
				//********************************************************************************
				// retourne un booléen updatePrixVenteEnchere
				
				
				
				
				//********************************************************************************
				boolean test = enchereMger.updatePrixVenteEnchere(Integer.parseInt(request.getParameter("noArticle")), Integer.parseInt(request.getParameter("offre")),(int)session.getAttribute( "idUtilisateur"));
				if(!test) {
					boolErreur = true;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			finally {
				IdArticle = Integer.parseInt(request.getParameter("noArticle"));
				response.sendRedirect("DetailsEncheres");
			}
		} else if ("back".equals(action)) {
			response.sendRedirect("accueil");
		}
	}

}
