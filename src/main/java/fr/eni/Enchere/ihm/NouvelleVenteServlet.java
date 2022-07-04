package fr.eni.Enchere.ihm;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

@WebServlet ({"/nouvelleVente"})
public class NouvelleVenteServlet  extends HttpServlet{
	private UtilisateurManager  utilisateurManager;
	
	public void init() {
		utilisateurManager = BLLFactory.getUtilisateurManager();
				}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Récupération des attributs de session idUtilisateur
		HttpSession session = request.getSession();
		//int idUtilisateur = (int) session.getAttribute("idUtilisateur");
		//pour test***********************************
		 int idUtilisateur =3;
	    //**********************************************
		
		
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
		//recuperation de l'action utlisateur
		 String action = request.getParameter("actionUtilisateur");
		
		if ("enregister".equals(action)) {
			//enregistrement de tous les champs sur le serveur SQL
			
			
			
			
		}
		
		if ("annuler".equals(action)) {
			//retour pages vide 
			//retour liste Enrchére connecter servlet a faire 
			response.sendRedirect("......................");
		
		}
		
	}
	
	
	
	
	
}
