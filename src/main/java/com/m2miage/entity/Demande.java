/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m2miage.entity;

/**
 *
 * @author Anthony
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Demande {

    @Id
    private String id;
    private String nom;
    private String prenom;
    private String adresse;
    private String datenaiss;
    private float revenu;
    private float credit;
    private int duree;
    private String etat;
   
    @ElementCollection
    @JsonProperty("actions-id")
    private Set<String> actionsId;

    public Demande() {
    }

    public Demande(String id, String nom, String prenom, String adresse, String dateNaiss, float revenu3DernAnnee, float mntCreditDem, int duree, String etat) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.datenaiss = dateNaiss;
        this.revenu = revenu3DernAnnee;
        this.credit = mntCreditDem;
        this.duree = duree;
        this.etat = etat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDatenaiss() {
        return datenaiss;
    }

    public void setDatenaiss(String datenaiss) {
        this.datenaiss = datenaiss;
    }

    public float getRevenu() {
        return revenu;
    }

    public void setRevenu(float revenu) {
        this.revenu = revenu;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Set<String> getActionsId() {
        return actionsId;
    }

    public void setActionsId(Set<String> actionsId) {
        this.actionsId = actionsId;
    }

  
    
}
