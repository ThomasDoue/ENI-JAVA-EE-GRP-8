package fr.eni.Enchere.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.Enchere.bo.ArticleVendu;
import fr.eni.Enchere.bo.Categorie;
import fr.eni.Enchere.bo.DtoEnchereComplete;
import fr.eni.Enchere.dal.DALException;

public interface EnchereManager {

	List<DtoEnchereComplete> SelectAllEnchere() throws DALException, SQLException,Exception;
	List<DtoEnchereComplete> SelectEncheresByCategorie(int noCategorie) throws DALException, SQLException,Exception; 
	List<DtoEnchereComplete> SelectEnchereByNomArticle(String nomArticle) throws SQLException,BLLException;
	List<DtoEnchereComplete> SelectEnchereByNomArticleAndCateg(int noCategorie,String nomArticle) throws SQLException,BLLException;
	DtoEnchereComplete SelectEnchereById(int noArticle) throws SQLException, BLLException;
	public void updatePrixVenteEnchere(int noArticle,int montantEnchere,int noUtilisateur) throws SQLException, BLLException;
	DtoEnchereComplete getMontantEtPseudoDeLaMeilleurOffre(ArticleVendu articleVendu);

}
