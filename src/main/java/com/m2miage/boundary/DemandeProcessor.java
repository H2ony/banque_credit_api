package com.m2miage.boundary;

import java.util.List;
import java.util.stream.Collectors;
import com.m2miage.entity.Demande;
import com.m2miage.entity.Detail;
import com.m2miage.entity.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Service;

@Service
public class DemandeProcessor implements ResourceProcessor<Resource<? extends Demande>> {

    @Autowired
    ActionClient client;

    @Override
    public Resource<Detail> process(Resource<? extends Demande> resource) {
        Demande demande = resource.getContent();
        List<Action> intervenants = demande
                .getActionsId()
                .stream()
                .map(client::get)
                .collect(Collectors.toList());
        return new Resource<>(new Detail(demande, intervenants), resource.getLinks());
    }
}
