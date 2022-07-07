package fr.eni.Enchere.ihm;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.Enchere.bll.ArticleAVendreManager;
import fr.eni.Enchere.bll.BLLException;
import fr.eni.Enchere.bll.BLLFactory;
import fr.eni.Enchere.bll.CategorieManager;
import fr.eni.Enchere.bll.UtilisateurManager;
import fr.eni.Enchere.bo.ArticleVendu;
import fr.eni.Enchere.bo.Categorie;
import fr.eni.Enchere.bo.Utilisateur;


@WebServlet ({"/nouvelleVente"})
public class NouvelleVenteServlet  extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private ArticleAVendreManager  articleAVendreManager;
	private UtilisateurManager utilisateurManager;
	private CategorieManager categorieManager;
	private int idUtilisateur=3;
	public void init() {
		articleAVendreManager = BLLFactory.getArticleAVendreManager();
		utilisateurManager = BLLFactory.getUtilisateurManager();
		categorieManager = BLLFactory.getCategorieManager();
				}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Récupération des attributs de session idUtilisateur
		HttpSession session = request.getSession();
		int idUtilisateur = (int) session.getAttribute("idUtilisateur");
		
		 List<Categorie> listCategorie = new ArrayList<Categorie>();

			try {
				listCategorie = categorieManager.SelectAllCategorie();
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("ListCategorie", listCategorie);
		
		//**********************************************
		//fonction en cour de dev
		//fonction afficher date et heure 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("yyyy/MM/dd HH:mm:ss-> "+dtf.format(LocalDateTime.now()));
        System.out.println(dtf);
        //a voir affichage de la date nim dans la variable 
        String dateLocal=dtf.format(LocalDateTime.now());
        request.setAttribute( "dateLocal", dateLocal); 
        //**************************************************
        //affichage de de la rue, codepostal,villedu vendeur    
    	try {
			Utilisateur UserModif = utilisateurManager.selectUser(idUtilisateur);
			request.setAttribute( "rue", UserModif.getRue());
			request.setAttribute("codePostal", UserModif.getCodePostal());
			request.setAttribute("ville", UserModif.getVille());
			
			//afficher image a faire
			//quand elle est charger 
			
		
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
		request.getRequestDispatcher("/WEB-INF/pages/nouvelleVente.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//creation de deux attribute date vide 
		 Date debutEnchere=null;
		 Date finEnchere=null;
		//recuperation de l'action utlisateur
		 String action = request.getParameter("actionUtilisateur");
		System.out.println(action);
		if ("Enregister".equals(action)) {
		  System.out.println(request.getParameter("debutEnchére"));
		//recuperatation de mes deux date  
			String StdebutEnchere = request.getParameter("debutEnchére");
			String StfinEnchere = request.getParameter("finEnchere");
			//mise en forme de la date pour transformer la date string en date java
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 

				try {
					debutEnchere = (Date) simpleDateFormat.parse(StdebutEnchere);
					System.out.println("date de debut "+ debutEnchere);
					finEnchere =(Date) simpleDateFormat.parse(StfinEnchere);
					System.out.println("date de fin "+ finEnchere);	
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//enregistrement de tous les champs sur le serveur SQL
				ArticleVendu nouvelleArticle = new ArticleVendu(request.getParameter("article"),
																request.getParameter("description"),
																debutEnchere, 
																finEnchere,
																Integer.parseInt(request.getParameter("miseaPrix")),
																0,
																idUtilisateur,
																Integer.parseInt((request.getParameter("Categorie"))));

				System.out.println("nouvelle article :"+nouvelleArticle);
				try {
					articleAVendreManager.NouvelleArticle(nouvelleArticle);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
			}


			if ("annuler".equals(action)) {
				//retour la page AccueilServlet
				response.sendRedirect("accueil");
			
			
		}
		
		//retour la page AccueilServlet
		response.sendRedirect("accueil");
	
		
		}
		
	}
	
	
	
	
	

