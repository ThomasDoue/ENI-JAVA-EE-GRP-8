package fr.eni.Enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.Enchere.bo.Categorie;

public class CategorieDaoImpl implements CategorieDao {
	private static final String SELECT_ALL = "select * from CATEGORIES";
	
	public List<Categorie> SelectAll() throws DALException, SQLException,Exception{
		List<Categorie> ListeRetour = new ArrayList<Categorie>();
		try(Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement stmtSelectAll = conn.prepareStatement(SELECT_ALL);
			ResultSet rs = stmtSelectAll.executeQuery();
			while(rs.next()) {
				Categorie cat = new Categorie(rs.getInt("no_categorie"),rs.getString("libelle"));
				ListeRetour.add(cat);
				
			}
		}catch(SQLException e){
			throw new Exception("Erreur sql lors de la récupération des données avec la méthode SelectAll de CatégorieDaoImpl : " + e);
		}catch(Exception e){
			throw new Exception("Erreur critique lors de la récupération des données avec la méthode SelectAll de CatégorieDaoImpl : " + e);
		}
		return ListeRetour;
	}

	
}
