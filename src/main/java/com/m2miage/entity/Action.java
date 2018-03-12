/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m2miage.entity;

import javax.persistence.Id;

/**
 *
 * @author Anthony
 */
public class Action {
    @Id
    private String id;
    private String nom;
    private String personneEnCharge;
    private String etat;
    private String date;

    public Action() {
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

    public String getPersonneEnCharge() {
        return personneEnCharge;
    }

    public void setPersonneEnCharge(String personneEnCharge) {
        this.personneEnCharge = personneEnCharge;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}