package fr.eni.Enchere.bll;

import fr.eni.Enchere.bo.Utilisateur;
import fr.eni.Enchere.dal.DALException;
import fr.eni.Enchere.dal.UtilisateurDao;
import fr.eni.Enchere.dao.DaoFactory;

public class UtilisateurManagerImpl implements UtilisateurManager{
	
	private UtilisateurDao utilisateurDao;
	
	public UtilisateurManagerImpl() {
		utilisateurDao = DaoFactory.getUtilisateurDao();
	}
	
	public int verifConnect(String username, String password) throws BLLException, Exception {
		try {
			return utilisateurDao.connect(username, password);
		} catch (DALException e) {
			throw new BLLException ("Erreur verifLogin - username = "+username+" - password = "+password, e);
		}	catch (Exception e) {
			throw new Exception (e);
		}		
	}
	
	public void InscriptionUtilisateur(Utilisateur usr) throws BLLException{
		try {
			utilisateurDao.inscription(usr);
		}catch (DALException e){
			throw new BLLException ("Erreur lors de l'inscription pour l'utilisateur : " + usr.toString() , e);
		}
		
	}
	
	public Utilisateur selectUser(Integer no_utlisateur) throws BLLException{
		Utilisateur user=null;
		try {
			user=utilisateurDao.selectUser(no_utlisateur);
			}catch (DALException e) {
				throw new BLLException ("Erreur la remonter des informations de l'utilisateur : " + no_utlisateur.toString(), e);
			}
		return user;
	}

}
