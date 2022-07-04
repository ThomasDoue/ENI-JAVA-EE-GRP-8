package fr.eni.Enchere.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.Enchere.bll.ArticleAVendreManagerImpl;
import fr.eni.Enchere.bll.BLLException;
import fr.eni.Enchere.bll.BLLFactory;
import fr.eni.Enchere.bll.EnchereManager;
import fr.eni.Enchere.bo.ArticleVendu;
import fr.eni.Enchere.bo.DtoEnchereComplete;
import fr.eni.Enchere.bo.Utilisateur;

public class ArticleVenduServlet extends HttpServlet {
	/**
	 * 
	
	 */
	private EnchereManager enchereMger;
	public void init() throws ServletException {
		enchereMger = BLLFactory.getEnchereManager();		
	}
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DtoEnchereComplete dto = new DtoEnchereComplete();
		try {
			dto = enchereMger.getMontantEtPseudoDeLaMeilleurOffre(null);
			req.setAttribute("pseudo", dto.getPseudo()); 
			req.setAttribute("description", dto.getDescription());
			req.setAttribute("PrixVente", dto.getPrixVente());
			req.setAttribute("MontantEnchere", dto.getNoEnchere());
			req.setAttribute("dateFinEnchere", dto.getDateFinEncheres());
			req.setAttribute("rueRetrait", dto.getRueRetrait());
			req.setAttribute("codePostal", dto.getCodePostalRetrait());
			req.setAttribute("ville", dto.getVilleRetrait());
	}catch (Exception e) {
		
		
	}
	}
		
	

}
