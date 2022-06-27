package fr.eni.Enchere.bo;

import java.time.LocalDate;

public class Encheres {
	
	//attribue 
	
	private Integer noEnchere;
	private LocalDate dateEnchere;
	private int montantEnchere;
	private int noArticle;
	private int noUtilisateur;
	
	//constructeur sans no_enchere IDENTITY(1,1):
	
	public Encheres(LocalDate dateEnchere, int montantEnchere, int noArticle, int noUtilisateur) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.noArticle = noArticle;
		this.noUtilisateur = noUtilisateur;
	}

	
	//constructeur avec no_enchere: 
	
	public Encheres(Integer noEnchere, LocalDate dateEnchere, int montantEnchere, int noArticle, int noUtilisateur) {
		super();
		this.noEnchere = noEnchere;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.noArticle = noArticle;
		this.noUtilisateur = noUtilisateur;
	}

	//get et set

	public Integer getNoEnchere() {
		return noEnchere;
	}


	public void setNoEnchere(Integer noEnchere) {
		this.noEnchere = noEnchere;
	}


	public LocalDate getDateEnchere() {
		return dateEnchere;
	}


	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}


	public int getMontantEnchere() {
		return montantEnchere;
	}


	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}


	public int getNoArticle() {
		return noArticle;
	}


	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}


	public int getNoUtilisateur() {
		return noUtilisateur;
	}


	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	//to String
	@Override
	public String toString() {
		return "Encheres [noEnchere=" + noEnchere + ", dateEnchere=" + dateEnchere + ", montantEnchere="
				+ montantEnchere + ", noArticle=" + noArticle + ", noUtilisateur=" + noUtilisateur + "]";
	}



	
	
	
	
	
	
	
	

	

}
