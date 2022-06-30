package fr.eni.Enchere.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.Enchere.bll.BLLException;
import fr.eni.Enchere.bo.DtoEnchereComplete;

public interface EncheresDao {
	 List<DtoEnchereComplete> SelectAllEnchere() throws SQLException;
	 List<DtoEnchereComplete> SelectEnchereByCategorie(int noCateg) throws SQLException;
	 List<DtoEnchereComplete> SelectEnchereByNomArticle(String nomArticle) throws SQLException;
	 List<DtoEnchereComplete> SelectEnchereByNomArticleAndCateg(int noCategorie,String nomArticle) throws SQLException,BLLException;
}
