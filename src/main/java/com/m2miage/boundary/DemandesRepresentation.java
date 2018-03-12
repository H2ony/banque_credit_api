package com.m2miage.boundary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.m2miage.entity.Demande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping(value="/demandes", produces=MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Demande.class)
public class DemandesRepresentation {
    private final DemandeRessource ir;
    
    @Autowired 
    public DemandesRepresentation(DemandeRessource ir) {
        this.ir = ir;
    }
    
    // GET all
    @GetMapping
    public ResponseEntity<?> getAllDemandes() {
        Iterable<Demande> allDemandes = ir.findAll();
        return new ResponseEntity<>(demandes2Resource(allDemandes), HttpStatus.OK);
    }
    
    
    // GET one
    @GetMapping(value="/{demandeId}")
    public ResponseEntity<?> getOneDemande(@PathVariable("demandeId") String id) {
        return Optional.ofNullable(ir.findOne(id))
                .map(i -> new ResponseEntity<>(demandeToResource(i,true), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // POST
    @PostMapping
    public ResponseEntity<?> saveDemande(@RequestBody Demande demande) {
        demande.setId(UUID.randomUUID().toString());
        Demande saved = ir.save(demande);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.setLocation(linkTo(DemandesRepresentation.class).slash(saved.getId()).toUri());
        return new ResponseEntity<>(null, responseHeader, HttpStatus.CREATED);
    }
    
    // DELETE
    @DeleteMapping(value="/{demandeId}")
    public ResponseEntity<?> deleteDemande(@PathVariable("demandeId") String id) {
        ir.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    //PUT
    @PutMapping(value="{demandeId}")
    public ResponseEntity<?> updateDemande(@PathVariable("demandeId") String id,
            @RequestBody Demande demande) {
        if (!ir.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Demande> body = Optional.ofNullable(demande);
        if (!body.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        demande.setId(id);
        Demande resultat = ir.save(demande);
        return new ResponseEntity<>(HttpStatus.OK); // NO_CONTENT
    }
    
   // gestion de HATEOAS
    private Resources<Resource<Demande>> demandes2Resource(Iterable<Demande> demandes) {
        Link selfLink = linkTo(methodOn(DemandesRepresentation.class).getAllDemandes())
                .withSelfRel();
        List<Resource<Demande>> demandesResources = new ArrayList();
        demandes.forEach(demande -> 
                demandesResources.add(demandeToResource(demande, false)
                ));
        return new Resources<>(demandesResources, selfLink);
    }
    
    private Resource<Demande> demandeToResource(Demande demande, Boolean collection) {
        Link selfLink = linkTo(DemandesRepresentation.class)
                .slash(demande.getId())
                .withSelfRel();
        if (collection) {
            Link collectionLink = linkTo(methodOn(DemandesRepresentation.class).getAllDemandes())
                    .withRel("collection");
            return new Resource<>(demande, selfLink, collectionLink);
        } else {
            return new Resource<>(demande, selfLink);
        }
    }

}
