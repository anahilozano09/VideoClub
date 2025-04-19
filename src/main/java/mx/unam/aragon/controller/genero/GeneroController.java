package mx.unam.aragon.controller.genero;

import jakarta.validation.Valid;
import mx.unam.aragon.model.entity.ActorEntity;
import mx.unam.aragon.model.entity.DirectorEntity;
import mx.unam.aragon.model.entity.GeneroEntity;
import mx.unam.aragon.service.actor.ActorService;
import mx.unam.aragon.service.genero.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "genero")
public class GeneroController {
    @Autowired
    GeneroService generoService;

    /// /genero/alta-genero
    @GetMapping("alta-genero")
    public String altaGenero(Model model){
        GeneroEntity genero = new GeneroEntity();
        model.addAttribute("genero",genero);
        model.addAttribute("contenido","Alta Genero");
        return "genero/alta-genero";
    }

    // /genero/guardar-genero
    @PostMapping("guardar-genero")
    public String guardarGenero(@Valid @ModelAttribute(value = "genero") GeneroEntity genero,
                                  BindingResult result, Model model){
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "genero/alta-genero";
        }
        //realizar la lògica de negocio
        generoService.save(genero);
        model.addAttribute("contenido","Se almaceno con exito");
        return "genero/alta-genero";
    }

    @GetMapping("lista-genero")
    public String listaGenero(Model model){
        List<GeneroEntity> lista=generoService.findAll();
        model.addAttribute("lista",lista);
        model.addAttribute("contenido","Lista de Generos");
        return "genero/lista-genero";
    }

    @GetMapping("eliminar-genero/{id}")
    public String delete(@PathVariable("id")Long id,
                         Model model){
        generoService.deleteById(id);
        return "redirect:/genero/lista-genero";

    }
    @GetMapping("modificar-genero/{id}")
    public String mdificar(@PathVariable("id")Long id,
                           Model model){
        GeneroEntity genero=generoService.findById(id);
        model.addAttribute("genero",genero);
        model.addAttribute("contenido","Modificar genero");
        return "genero/alta-genero";

    }
}
