package fr.eni.Enchere.dao;
import fr.eni.Enchere.dal.UtilisateurDao;

import fr.eni.Enchere.dal.UtilisateurDaoImpl;

public class DaoFactory {
	
	public static UtilisateurDao getUtilisateurDao() {
		return new UtilisateurDaoImpl();
	}

}
