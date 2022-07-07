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
	private String libelleCateg;
	private String rueRetrait;
	private String codePostalRetrait;
	private String villeRetrait;
	
	public DtoEnchereComplete(String nomArticle,int prixVente,Date localDate,String pseudo,int noEnchere,int noArticle) {
		super();
		this.setNomArticle(nomArticle);
		this.setPrixVente(prixVente);
		this.setDateFinEncheres(localDate);
		this.setPseudo(pseudo);
		this.setNoEnchere(noEnchere);
		this.setNoArticle(noArticle);
	}
	
	public DtoEnchereComplete(String nomArticle,int prixVente,Date localDate,String pseudo,int noArticle) {
		super();
		this.setNomArticle(nomArticle);
		this.setPrixVente(prixVente);
		this.setDateFinEncheres(localDate);
		this.setPseudo(pseudo);
		this.setNoArticle(noArticle);
	}
	
	public DtoEnchereComplete(String nomArticle,int prixVente,Date localDate,String pseudo,int noEnchere,int noArticle,String libelleCateg, String telephone, int prixInitial ,String rueRetrait,String codePostalRetrait,String villeRetrait,String  description) {
		super();
		this.setNomArticle(nomArticle);
		this.setPrixVente(prixVente);
		this.setDateFinEncheres(localDate);
		this.setPseudo(pseudo);
		this.setNoEnchere(noEnchere);
		this.setLibelleCateg(libelleCateg);
		this.setTelephone(telephone);
		this.setRueRetrait(rueRetrait);
		this.setCodePostalRetrait(codePostalRetrait);
		this.setVilleRetrait(villeRetrait);
		this.setDescription(description);
		this.setPrixInitial(prixInitial);
		this.setNoArticle(noArticle);
	}
	
	public DtoEnchereComplete(String nomArticle,int prixVente,Date localDate,String pseudo, String telephone, int noArticle,String libelleCateg, int prixInitial ,String rueRetrait,String codePostalRetrait,String villeRetrait,String  description,Date dateDebutEnchere) {
		super();
		this.setNomArticle(nomArticle);
		this.setPrixVente(prixVente);
		this.setDateFinEncheres(localDate);
		this.setPseudo(pseudo);
		this.setLibelleCateg(libelleCateg);
		this.setTelephone(telephone);
		this.setRueRetrait(rueRetrait);
		this.setCodePostalRetrait(codePostalRetrait);
		this.setVilleRetrait(villeRetrait);
		this.setDescription(description);
		this.setPrixInitial(prixInitial);
		this.setNoArticle(noArticle);
		this.setDateDebutEncheres(dateDebutEnchere);
	}

	public DtoEnchereComplete() {
		
	}

	public DtoEnchereComplete(String nomArticle, int prixVente, Date DateFinEnchere, String pseudo, int montantEnchere, String rueRetrait,
			String codePostalRetrait, String villeRetrait, String description) {
		// TODO Auto-generated constructor stub
	}

	//to string 
	@Override
	public String toString() {
		return "Enchere [nomArticle=" + getNomArticle() + ", prixVente=" + getPrixVente() + ", dateFinEncheres=" + getDateFinEncheres() + ", pseudo : " + getPseudo() + ", telephone : " + getTelephone() + ", CodePostalRetrait : " + getCodePostalRetrait() + ", Rue retrait : " + getRueRetrait() + "]" ;
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


	public int getNoArticle() {
		return noArticle;
	}


	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public Integer getNoEnchere() {
		return noEnchere;
	}

	public void setNoEnchere(Integer noEnchere) {
		this.noEnchere = noEnchere;
	}

	public String getVilleRetrait() {
		return villeRetrait;
	}

	public void setVilleRetrait(String villeRetrait) {
		this.villeRetrait = villeRetrait;
	}

	public String getCodePostalRetrait() {
		return codePostalRetrait;
	}

	public void setCodePostalRetrait(String codePostalRetrait) {
		this.codePostalRetrait = codePostalRetrait;
	}

	public String getRueRetrait() {
		return rueRetrait;
	}

	public void setRueRetrait(String rueRetrait) {
		this.rueRetrait = rueRetrait;
	}

	public String getLibelleCateg() {
		return libelleCateg;
	}

	public void setLibelleCateg(String libelleCateg) {
		this.libelleCateg = libelleCateg;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(Date dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	
	
	
}
