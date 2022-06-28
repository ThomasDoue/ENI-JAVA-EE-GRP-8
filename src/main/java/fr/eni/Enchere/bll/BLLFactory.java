package fr.eni.Enchere.bll;

public class BLLFactory {
	
	public static UtilisateurManager getUtilisateurManager() {
		return new UtilisateurManagerImpl();
	}

}
