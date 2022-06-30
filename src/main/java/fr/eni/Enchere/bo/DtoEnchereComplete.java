package fr.eni.Enchere.bo;

import java.sql.Date;

public class DtoEnchereComplete {

	private Integer noEnchere;
	private Date dateEnchere;
	private int montantEnchere;
	private int noArticle;
	private int noUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private String motDePasse;
	private Integer credit;
	private boolean administrateur;
	private String nomArticle;
	private String  description;
	private Date  dateDebutEncheres;
	private Date  dateFinEncheres;
	private int prixInitial;
	private int prixVente;
	private int noCategorie;
	
	public DtoEnchereComplete(String nomArticle,int prixVente,Date localDate,String pseudo) {
		super();
		this.setNomArticle(nomArticle);
		this.setPrixVente(prixVente);
		this.setDateFinEncheres(localDate);
		this.setPseudo(pseudo);
	}


	//to string 
	@Override
	public String toString() {
		return "Enchere [nomArticle=" + getNomArticle() + ", prixVente=" + getPrixVente() + ", dateFinEncheres=" + getDateFinEncheres() + ", pseudo=" + getPseudo() + "]";
	}


	public Date getDateFinEncheres() {
		return dateFinEncheres;
	}


	public void setDateFinEncheres(Date dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}


	public String getPseudo() {
		return pseudo;
	}


	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


	public String getNomArticle() {
		return nomArticle;
	}


	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}


	public int getPrixVente() {
		return prixVente;
	}


	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
	
	
	
}
