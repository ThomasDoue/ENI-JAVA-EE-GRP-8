package fr.eni.Enchere.bll;

import fr.eni.Enchere.dal.DALException;
import fr.eni.Enchere.dal.UtilisateurDao;
import fr.eni.Enchere.dao.DaoFactory;

public class UtilisateurManagerImpl implements UtilisateurManager{
	
	private UtilisateurDao utilisateurDao;
	
	public UtilisateurManagerImpl() {
		utilisateurDao = DaoFactory.getUtilisateurDao();
	}
	
	public boolean verifLogin(String username, String password) throws BLLException {
		try {
			return utilisateurDao.loginCheck(username, password);
		} catch (DALException e) {
			throw new BLLException ("Erreur verifLogin - username = "+username+" - password = "+password, e);
		}		
	}

}
