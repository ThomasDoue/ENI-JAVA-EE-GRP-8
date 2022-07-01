package fr.eni.Enchere.ihm;

import java.io.IOException;

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
	private UtilisateurManager utilisateurMger;
	
	public void init() throws ServletException {
		utilisateurMger = BLLFactory.getUtilisateurManager();
	}

	/**
	 *recuperation des différent paramétre du profil utlisateur qui est sur la base de donnéer
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recuperation du numéro de l'utilisateur de la page de connection par la session 
		Integer utilisateur = 3;
		HttpSession session = request.getSession();
		session.setAttribute("No_utlisateur",utilisateur);
		
		try {
			//appel de la fonction manageur avec le selectUtilisateur 
			
			Utilisateur RetourProfil =utilisateurMger.selectUser(utilisateur);
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
			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/pages/profil.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		
	}

	
}

