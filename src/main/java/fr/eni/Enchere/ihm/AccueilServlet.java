package fr.eni.Enchere.ihm;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.Enchere.bll.BLLFactory;
import fr.eni.Enchere.bll.CategorieManager;
import fr.eni.Enchere.bll.EnchereManager;
import fr.eni.Enchere.bll.UtilisateurManager;
import fr.eni.Enchere.bo.Categorie;
import fr.eni.Enchere.bo.DtoEnchereComplete;

@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategorieManager categorieMger;
	private EnchereManager enchereMger;
	
	@Override
	public void init() throws ServletException {
		categorieMger = BLLFactory.getCategorieManager();
		enchereMger = BLLFactory.getEnchereManager();		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Categorie> listCategorie = new ArrayList<Categorie>();
		List<DtoEnchereComplete> listEncheres = new ArrayList<DtoEnchereComplete>();
		try {
			listCategorie = categorieMger.SelectAllCategorie();
			listEncheres = enchereMger.SelectAllEnchere();
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Retour des catégories dans la servlet : " + listCategorie.toString());
		System.out.println("Retour des enchères dans la servlet : " + listEncheres.toString());
		req.setAttribute("ListCategorie", listCategorie);
		req.setAttribute("ListEncheres", listEncheres);
		
		
		HttpSession session = req.getSession();
		Integer idUser = (Integer) session.getAttribute("idUtilisateur");
		
		if(idUser==null) {
			session.setAttribute("estConnecte", "0");
		} else {
			session.setAttribute("estConnecte", "1");
		}
		
		req.getRequestDispatcher("/WEB-INF/pages/accueil.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Categorie> listCategorie = new ArrayList<Categorie>();
		List<DtoEnchereComplete> listEncheresFiltered = new ArrayList<DtoEnchereComplete>();
		System.out.println("test filtre " + req.getParameter("NomArticle"));
		System.out.println("valeur du bouton EnchereOuvert " + req.getParameter("EnchereOuvert"));
		System.out.println("valeur du bouton MesVentesEnCours " + req.getParameter("MesVentesEnCours"));
		System.out.println("valeur du radioButton ventes " + req.getParameter("choixfiltre"));
		System.out.println("valeur du radioButton achats " + req.getParameter("achats"));
		HttpSession session = req.getSession();
				try {
					listCategorie = categorieMger.SelectAllCategorie();
			if(req.getParameter("choixfiltre") == null) {
				
				if(Integer.parseInt(req.getParameter("Categorie")) != 0 && req.getParameter("NomArticle").equals("")) {
					System.out.println("Que la catégorie en filtre");
					listEncheresFiltered = enchereMger.selectVenteByCateg(Integer.parseInt(req.getParameter("Categorie")));
				}else if(Integer.parseInt(req.getParameter("Categorie")) == 0 && !req.getParameter("NomArticle").equals("")) 
				{
					System.out.println("Que le nom article en filtre");
					listEncheresFiltered = enchereMger.selectVenteByNomArticle(req.getParameter("NomArticle"));
				}else if(Integer.parseInt(req.getParameter("Categorie")) != 0 && !req.getParameter("NomArticle").equals("")){
					System.out.println("Filtre sur la catégorie et sur le nom d'article");
					listEncheresFiltered = enchereMger.selectVenteByNomArticleAndCateg(Integer.parseInt(req.getParameter("Categorie")),req.getParameter("NomArticle"));
				}else {
					System.out.println("Pas de filtre");
					listEncheresFiltered = enchereMger.SelectAllEnchere();
				}
			}else if(req.getParameter("choixfiltre").equals("vente")) {
				listEncheresFiltered = enchereMger.selectArticleEnVenteOfUser((int)session.getAttribute( "idUtilisateur"));
			}else if(req.getParameter("choixfiltre").equals("achats")) {
				listEncheresFiltered = enchereMger.SelectAllEnchere();
			}
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		System.out.println("Retour des catégories dans la servlet : " + listCategorie.toString());
		System.out.println("Retour des enchères filtré dans la servlet : " + listEncheresFiltered.toString());
		req.setAttribute("ListCategorie", listCategorie);
		req.setAttribute("ListEncheres", listEncheresFiltered);
		req.getRequestDispatcher("/WEB-INF/pages/accueil.jsp").forward(req, resp);}
	
}
	
 



