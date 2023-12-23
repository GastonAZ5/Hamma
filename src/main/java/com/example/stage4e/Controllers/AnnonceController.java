package com.example.stage4e.Controllers;


import com.example.stage4e.Entities.Annonce;
import com.example.stage4e.Service.AnnonceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/annonce")
public class AnnonceController {
    @Autowired
    AnnonceServiceImpl annonceService;

    @PostMapping("/addAnnonce/{IdUser}")
    public ResponseEntity<?> addPublication(@RequestBody Annonce annonce , @PathVariable Integer IdUser) {
        return new ResponseEntity<>(annonceService.addAnnonce(IdUser, annonce), HttpStatus.valueOf(200));
    }

    @PutMapping("/updateAnnonce/{idAnnonce}/{idUser}")
    public ResponseEntity<?> updateAnnonce(@RequestBody Annonce Annonce,@PathVariable Integer idAnnonce,@PathVariable Integer idUser) {
        return new ResponseEntity<>(annonceService.updateAnnonce(Annonce,idAnnonce,idUser), HttpStatus.valueOf(200));
    }

    @DeleteMapping("/delete/{idAnnonce}")
    public ResponseEntity<?> deleteAnnonce(@PathVariable Integer idAnnonce){
        return new ResponseEntity<>(annonceService.deleteAnnonce(idAnnonce),HttpStatus.valueOf(200));
    }


    @GetMapping("/getAllAnnonce")
    public List<Annonce> getAllPosts() {
        return annonceService.getAllAnnonce();
    }

}
