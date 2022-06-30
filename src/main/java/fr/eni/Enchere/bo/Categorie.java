package fr.eni.Enchere.bo;

public class Categorie {
	//attribue 
private Integer noCategorie;
private String libelle;

//constructeur
public Categorie(String libelle) {
	this.libelle = libelle;
}

public Categorie(Integer noCategorie,String libelle) {
	this.noCategorie = noCategorie;
	this.libelle = libelle;
}

//get et set
public Integer getNoCategorie() {
	return noCategorie;
}

public void setNoCategorie(Integer noCategorie) {
	this.noCategorie = noCategorie;
}

public String getLibelle() {
	return libelle;
}

public void setLibelle(String libelle) {
	this.libelle = libelle;
}


//to string 
@Override
public String toString() {
	return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + "]";
}
	





}
