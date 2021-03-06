/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m2miage.entity;

import com.m2miage.entity.Action;
import com.m2miage.entity.Demande;
import java.util.List;
import org.springframework.hateoas.core.Relation;

@Relation(collectionRelation="demande")
public class Detail extends Demande {

    private final List<Action> actions;

    public Detail(Demande demande, List<Action> actions) {
        super(demande);
        this.actions = actions;
    }

    public List<Action> getIntervenants() {
        return actions;
    }
}