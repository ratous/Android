package com.example.gsb;

// Classe représentant un échantillon avec un code, un libellé et une quantité en stock
public class Echantillon {
    protected static String code; // Code unique de l'échantillon
    protected String libelle; // Libellé (nom) de l'échantillon
    protected String quantiteStock; // Quantité en stock sous forme de chaîne de caractères

    // Constructeur permettant d'initialiser un échantillon avec des valeurs spécifiques
    public Echantillon(String code, String libelle, String qteStock) {
        this.code = code;
        this.libelle = libelle;
        this.quantiteStock = qteStock;
    }

    // Constructeur sans paramètres qui initialise les attributs à null
    public Echantillon() {
        this.code = null;
        this.libelle = null;
        this.quantiteStock = null;
    }

    // Setter pour modifier le code de l'échantillon
    public void setCode(String code) {
        this.code = code;
    }

    // Setter pour modifier le libellé de l'échantillon
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    // Setter pour modifier la quantité en stock de l'échantillon
    public void setQuantiteStock(String quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    // Getter pour récupérer le code de l'échantillon
    public static String getCode() {
        return code;
    }

    // Getter pour récupérer le libellé de l'échantillon
    public String getLibelle() {
        return libelle;
    }

    // Getter pour récupérer la quantité en stock de l'échantillon
    public String getQuantiteStock() {
        return quantiteStock;
    }
}
