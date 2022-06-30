package fr.eni.Enchere.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.Enchere.bo.Categorie;

public interface CategorieDao {
	List<Categorie> SelectAll() throws DALException, SQLException,Exception; 
}
