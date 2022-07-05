package fr.eni.Enchere.bo;

import java.util.Date;

//attribue
public class ArticleVendu {

	//attribu	
	private Integer noArticle;
	private String nomArticle;
	private String  description;
	private Date  dateDebutEncheres;
	private Date  dateFinEncheres;
	private int prixInitial;
	private int prixVente;
	private int noUtilisateur;
	private int noCategorie;
	
	//constructeur sans noArticle IDENTITY(1,1):
	
	public ArticleVendu(String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres,
			int prixInitial, int prixVente, int noUtilisateur, int noCategorie) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
	}
	
	
	
	//constructeur avec noArticle
	
	public ArticleVendu(Integer noArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int prixInitial, int prixVente, int noUtilisateur, int noCategorie) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
	}
	
	//get et set
	
	public Integer getNoArticle() {
		return noArticle;
	}
	
	
	
	public void setNoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}
	
	
	
	public String getNomArticle() {
		return nomArticle;
	}
	
	
	
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	
	
	
	public String getDescription() {
		return description;
	}
	
	
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	public Date getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	
	
	
	public void setDateDebutEncheres(Date dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	
	
	
	public Date getDateFinEncheres() {
		return dateFinEncheres;
	}
	
	
	
	public void setDateFinEncheres(Date dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	
	
	
	public int getPrixInitial() {
		return prixInitial;
	}
	
	
	
	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}
	
	
	
	public int getPrixVente() {
		return prixVente;
	}
	
	
	
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
	
	
	
	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	
	
	
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	
	
	
	public int getNoCategorie() {
		return noCategorie;
	}
	
	
	
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}
	
	
	//to string
	
	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", prixInitial="
				+ prixInitial + ", prixVente=" + prixVente + ", noUtilisateur=" + noUtilisateur + ", noCategorie="
				+ noCategorie + "]";
	}








}

