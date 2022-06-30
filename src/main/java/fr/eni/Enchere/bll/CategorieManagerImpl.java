package fr.eni.Enchere.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.Enchere.bo.Categorie;
import fr.eni.Enchere.dal.CategorieDao;
import fr.eni.Enchere.dal.DALException;
import fr.eni.Enchere.dao.DaoFactory;

public class CategorieManagerImpl implements CategorieManager {
	
	private CategorieDao categorieDao;
	
	public CategorieManagerImpl() {
		categorieDao = DaoFactory.getCategorieDao();
	}
	
	public List<Categorie> SelectAllCategorie() throws DALException, SQLException,Exception{
		List<Categorie> listRetour = new ArrayList<Categorie>();
		try {
			listRetour = categorieDao.SelectAll();
		}catch(Exception e) {
			throw new BLLException ("Erreur lors de la récupération des catégories : " +  e);
		}
		return listRetour;
	}
}
