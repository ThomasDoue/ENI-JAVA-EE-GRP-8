package fr.eni.Enchere.bll;

public class BLLFactory {
	
	public static UtilisateurManager getUtilisateurManager() {
		return new UtilisateurManagerImpl();
	}
	public static CategorieManager getCategorieManager() {
		return new CategorieManagerImpl();
	}
	public static EnchereManager getEnchereManager() {
		return new EnchereManagerImpl();
	}

}
