package fr.eni.Enchere.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.Enchere.bo.Categorie;
import fr.eni.Enchere.dal.DALException;

public interface CategorieManager {
	List<Categorie> SelectAllCategorie() throws DALException, SQLException,Exception; 
}
