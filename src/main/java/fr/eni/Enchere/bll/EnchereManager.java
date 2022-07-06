package fr.eni.Enchere.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.Enchere.bo.ArticleVendu;
import fr.eni.Enchere.bo.DtoEnchereComplete;
import fr.eni.Enchere.dal.DALException;

public interface EnchereManager {

	List<DtoEnchereComplete> SelectAllEnchere() throws DALException, SQLException,Exception;
	List<DtoEnchereComplete> selectVenteByCateg(int noCategorie) throws DALException, SQLException,Exception; 
	List<DtoEnchereComplete> selectVenteByNomArticle(String nomArticle) throws SQLException,BLLException;
	List<DtoEnchereComplete> selectVenteByNomArticleAndCateg(int noCategorie,String nomArticle) throws SQLException,BLLException;
	DtoEnchereComplete selectVenteById(int noArticle) throws SQLException, BLLException;
	boolean updatePrixVenteEnchere(int noArticle,int montantEnchere,int noUtilisateur) throws SQLException, BLLException;
	DtoEnchereComplete getMontantEtPseudoDeLaMeilleurOffre(ArticleVendu articleVendu);
	List<DtoEnchereComplete> selectArticleEnVenteOfUser(int noUtilisateur)throws SQLException, BLLException;

}
