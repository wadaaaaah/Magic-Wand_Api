package com.accenture.magicwand.controller;

import brave.Tracer;
import com.accenture.magicwand.entity.MagicWand;
import com.accenture.magicwand.exception.MagicWandException;
import com.accenture.magicwand.repository.MagicWandRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/MagicWandApi")
public class MagicWandController {

    private final MagicWandRepository repo;

    Tracer tracer;

    public MagicWandController(MagicWandRepository repo, Tracer tracer) {
        this.repo = repo;
        this.tracer = tracer;
    }

    @GetMapping("/getInfo/{id}")
    public ResponseEntity<?> test(@PathVariable(value = "id") long id, MagicWand magicWand) {
        repo.findById(magicWand.getId()).orElseThrow(() ->
                new MagicWandException(MagicWandException.INVALID_ID));

        System.out.println(tracer.currentSpan().context().traceIdString());
        return ResponseEntity.ok(repo.findById(id).get());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addWizard(@RequestBody MagicWand magic){
        MagicWand newMagic = new MagicWand();
        newMagic.setName(magic.getName());
        newMagic.setNarrative(magic.getNarrative());
        newMagic.setAge_limit(magic.getAge_limit());
        newMagic.setStock(magic.getStock());

        return ResponseEntity.ok(repo.save(newMagic));
    }

    @DeleteMapping("/delete/{id}")
    public String deleteMagic(@PathVariable(value = "id") Long id){

        repo.deleteById(id);

        return ("Magic Wand successfully deleted");
    }

    @PutMapping("/update")
    public MagicWand updateMagic(@RequestBody MagicWand magicWand){
        repo.findById(magicWand.getId()).orElseThrow(() ->
                new MagicWandException(MagicWandException.INVALID_ID));

        return repo.save(magicWand);
    }



}
