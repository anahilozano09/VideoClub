package mx.unam.aragon.controller.actor;

import jakarta.validation.Valid;
import mx.unam.aragon.model.entity.ActorEntity;
import mx.unam.aragon.model.entity.DirectorEntity;
import mx.unam.aragon.service.actor.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "actor")
public class ActorController {
    @Autowired
    ActorService actorService;

    /// /actor/alta-actor
    @GetMapping("alta-actor")
    public String altaActor(Model model){
        ActorEntity actor = new ActorEntity();
        model.addAttribute("actor",actor);
        model.addAttribute("contenido","Alta Actor");
        return "actor/alta-actor";
    }

    // /actor/guardar-actor
    @PostMapping("guardar-actor")
    public String guardarDirector(@Valid @ModelAttribute(value = "actor") ActorEntity actor,
                                  BindingResult result, Model model){
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "actor/alta-actor";
        }
        //realizar la lògica de negocio
        actorService.save(actor);
        model.addAttribute("contenido","Se almaceno con exito");
        return "actor/alta-actor";
    }

    @GetMapping("lista-actor")
    public String listaActor(Model model){
        List<ActorEntity> lista=actorService.findAll();
        model.addAttribute("lista",lista);
        model.addAttribute("contenido","Lista de Actores");
        return "actor/lista-actor";
    }

    @GetMapping("eliminar-actor/{id}")
    public String delete(@PathVariable("id")Long id,
                         Model model){
        actorService.deleteById(id);
        return "redirect:/actor/lista-actor";

    }
    @GetMapping("modificar-actor/{id}")
    public String mdificar(@PathVariable("id")Long id,
                           Model model){
        ActorEntity actor=actorService.findById(id);
        model.addAttribute("actor",actor);
        model.addAttribute("contenido","Modificar actor");
        return "actor/alta-actor";

    }
}
