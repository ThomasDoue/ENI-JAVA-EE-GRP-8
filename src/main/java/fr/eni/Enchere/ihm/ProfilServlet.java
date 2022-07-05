package fr.eni.Enchere.ihm;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

@WebServlet({"/RetourFormulaire"})
public class ProfilServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilisateurMger;
	
	public void init() throws ServletException {
		utilisateurMger = BLLFactory.getUtilisateurManager();
	}

	/**
	 *recuperation des différent paramétre du profil utlisateur qui est sur la base de donnéer
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Récupération des attributs de session idUtilisateur
		HttpSession session = request.getSession();
		int idUtilisateur = (int) session.getAttribute("idUtilisateur");
		//pour test***********************************
		 idUtilisateur =3;
	    //**********************************************		
				try {
			//appel de la fonction manageur avec le selectUtilisateur 
			
			Utilisateur RetourProfil = utilisateurMger.selectUser(idUtilisateur);
			//insertion du pseudo recuperer de la base vers la page html. 
			request.setAttribute("pseudo",RetourProfil.getPseudo());
			//insertion du nom recuperer de la base vers la page html. 
			request.setAttribute("nom",RetourProfil.getNom());
			//insertion du prenom recuperer de la base vers la page html. 
			request.setAttribute("prenom",RetourProfil.getPrenom());
			//insertion du email recuperer de la base vers la page html. 
			request.setAttribute("email",RetourProfil.getEmail());
			//insertion du telephone recuperer de la base vers la page html. 
			request.setAttribute("telephone",RetourProfil.getTelephone());
			//insertion du rue recuperer de la base vers la page html. 
			request.setAttribute("rue",RetourProfil.getRue());
			//insertion du codePostal recuperer de la base vers la page html. 
			request.setAttribute("codePostal",RetourProfil.getCodePostal());
			//insertion du ville recuperer de la base vers la page html. 
			request.setAttribute("ville",RetourProfil.getVille());
			//insertion du credit recuperer de la base vers la page html. 
			request.setAttribute("credit", RetourProfil.getCredit());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/pages/profil.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Récupération du profil d'un utilisateur qui n'est pas celui connecté
		int idEnchere = Integer.parseInt(request.getParameter("IdEnchere"));
		try {
			int idUser = utilisateurMger.SelectIdVendeurByIdEnchere(idEnchere);
			Utilisateur RetourProfil = utilisateurMger.selectUser(idUser);
			//insertion du pseudo recuperer de la base vers la page html. 
			request.setAttribute("pseudo",RetourProfil.getPseudo());
			//insertion du nom recuperer de la base vers la page html. 
			request.setAttribute("nom",RetourProfil.getNom());
			//insertion du prenom recuperer de la base vers la page html. 
			request.setAttribute("prenom",RetourProfil.getPrenom());
			//insertion du email recuperer de la base vers la page html. 
			request.setAttribute("email",RetourProfil.getEmail());
			//insertion du telephone recuperer de la base vers la page html. 
			request.setAttribute("telephone",RetourProfil.getTelephone());
			//insertion du rue recuperer de la base vers la page html. 
			request.setAttribute("rue",RetourProfil.getRue());
			//insertion du codePostal recuperer de la base vers la page html. 
			request.setAttribute("codePostal",RetourProfil.getCodePostal());
			//insertion du ville recuperer de la base vers la page html. 
			request.setAttribute("ville",RetourProfil.getVille());
			//insertion du credit recuperer de la base vers la page html. 
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/pages/profil.jsp").forward(request, response);
	}

	
}

