package com.example.stage4e.Service;

import com.example.stage4e.Entities.Annonce;
import com.example.stage4e.Entities.User;
import com.example.stage4e.Interfaces.AnnonceServiceInterface;
import com.example.stage4e.Repository.AnnonceRepository;
import com.example.stage4e.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnonceServiceImpl implements AnnonceServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AnnonceRepository annonceRepository;


    public String addAnnonce(Integer idUser, Annonce annonce){
        User user = userRepository.findById(idUser).orElseThrow(null);
        annonce.setPublierPar(user);
        BadWordServiceImp badWordServiceImp = new BadWordServiceImp();
        if(badWordServiceImp.filterText(annonce.getContenu()).equals("This post contain bad word"))
            return "This post contains bad word";
        else {
            annonceRepository.save(annonce);
            return "Annonce ajouté avec succée";
        }
    }


    public String deleteAnnonce(Integer idAnnonce){
        annonceRepository.deleteById(idAnnonce);
        return ("Annonce deleted succefully ");
    }






    public String updateAnnonce(Annonce annonce,Integer idAnnonce,Integer idUser) {



        Annonce p = annonceRepository.findById(idAnnonce).get();
        annonce.setIdAnnonce(p.getIdAnnonce());
        User user = userRepository.findById(idUser).get();
        annonce.setPublierPar(user);

        BadWordServiceImp badWordServiceImp = new BadWordServiceImp();
        if (badWordServiceImp.filterText(annonce.getContenu()).equals("This post contain bad word"))
            return "This post contains bad word";
        else {
            annonceRepository.saveAndFlush(annonce);
            return "Annonce updated succesfsuly";
        }

    }


    public List<Annonce> getAllAnnonce(){return annonceRepository.findAll();}
}
