package mx.unam.aragon.controller.participa;

import jakarta.validation.Valid;
import mx.unam.aragon.model.entity.ActorEntity;
import mx.unam.aragon.model.entity.PeliculaEntity;
import mx.unam.aragon.model.entity.participa.ParticipaEntity;
import mx.unam.aragon.model.entity.participa.ParticipaId;
import mx.unam.aragon.service.actor.ActorService;
import mx.unam.aragon.service.director.DirectorService;
import mx.unam.aragon.service.genero.GeneroService;
import mx.unam.aragon.service.participa.ParticipaService;
import mx.unam.aragon.service.pelicula.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "participa")

public class ParticipaController {
    @Autowired
    ParticipaService participaService;

    @Autowired
    PeliculaService peliculaService;

    @Autowired
    ActorService actorService;

    /// /participa/alta-participa
    @GetMapping("alta-participa")
    public String altaParticipa(Model model){
        ParticipaEntity participa = new ParticipaEntity();
        model.addAttribute("participa",participa);
        model.addAttribute("pelicula", peliculaService.findAll());
        model.addAttribute("actor", actorService.findAll());
        model.addAttribute("contenido","Alta Participa");
        return "participa/alta-participa";
    }

    // /participa/guardar-participa
    @PostMapping("guardar-participa")
    public String guardarParticipa(@Valid @ModelAttribute(value = "participa") ParticipaEntity participa,
                                  BindingResult result, Model model){

        // Validación básica
        if (participa.getPelicula() == null || participa.getActor() == null) {
            result.reject("", "Debe seleccionar película y actor");
        }

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "participa/alta-participa";
        }

        PeliculaEntity pelicula = peliculaService.findById(participa.getPelicula().getId());
        ActorEntity actor = actorService.findById(participa.getActor().getId());

        if (pelicula == null || actor == null) {
            result.reject("", "Película o actor no encontrados");
            return "participa/alta-participa";
        }

        // Caso de modificación
        if (participa.getId() != null) {
            ParticipaId idOriginal = participa.getId();
            ParticipaEntity existente = participaService.findById(idOriginal);

            if (existente == null) {
                return "participa/lista-participa";
            }

            boolean cambioPelicula = !pelicula.getId().equals(idOriginal.getIdPelicula());
            boolean cambioActor = !actor.getId().equals(idOriginal.getIdActor());

            if (cambioPelicula || cambioActor) {
                // Crear nuevo ID
                ParticipaId nuevoId = new ParticipaId(pelicula.getId(), actor.getId());

                if (participaService.existsById(nuevoId)) {
                    result.reject("", "Ya existe esta combinación de película y actor");
                    return "participa/alta-participa";
                }

                // Eliminar original y crear nueva
                participaService.deleteById(idOriginal);

                ParticipaEntity nuevaParticipa = new ParticipaEntity();
                nuevaParticipa.setId(nuevoId);
                nuevaParticipa.setPelicula(pelicula);
                nuevaParticipa.setActor(actor);
                // Copiar otros atributos si existen
                // nuevaParticipa.setOtrosDatos(existente.getOtrosDatos());

                participaService.save(nuevaParticipa);
            } else {
                // Actualizar existente
                existente.setPelicula(pelicula);
                existente.setActor(actor);
                participaService.save(existente);
            }
        } else {
            // Alta nueva
            ParticipaId nuevoId = new ParticipaId(pelicula.getId(), actor.getId());

            if (participaService.existsById(nuevoId)) {
                result.reject("", "Ya existe esta combinación de película y actor");
                return "participa/alta-participa";
            }

            participa.setId(nuevoId);
            participa.setPelicula(pelicula);
            participa.setActor(actor);
            participaService.save(participa);
        }
        model.addAttribute("contenido","Se almaceno con exito");
        return "participa/alta-participa";
    }

    @GetMapping("lista-participa")
    public String listaParticipa(Model model){
        List<ParticipaEntity> lista=participaService.findAll();
        model.addAttribute("lista",lista);
        model.addAttribute("contenido","Lista de Participa");
        return "participa/lista-participa";
    }

    @GetMapping("eliminar-participa/{idPelicula}/{idActor}")
    public String delete(@PathVariable("idPelicula") Long idPelicula,
                         @PathVariable("idActor") Long idActor){
        ParticipaId id = new ParticipaId(idPelicula, idActor);
        participaService.deleteById(id);
        return "redirect:/participa/lista-participa";

    }
    @GetMapping("modificar-participa/{idPelicula}/{idActor}")
    public String mdificar(@PathVariable("idPelicula") Long idPelicula,
                           @PathVariable("idActor") Long idActor,
                           Model model){

        ParticipaId id = new ParticipaId(idPelicula, idActor);
        ParticipaEntity participa=participaService.findById(id);

        participa.setPelicula(peliculaService.findById(idPelicula));
        participa.setActor(actorService.findById(idActor));

        model.addAttribute("participa",participa);
        model.addAttribute("pelicula", peliculaService.findAll());
        model.addAttribute("actor", actorService.findAll());
        model.addAttribute("contenido","Modificar participa");
        return "participa/alta-participa";

    }
}
