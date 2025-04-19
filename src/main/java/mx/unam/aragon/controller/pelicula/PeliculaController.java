package mx.unam.aragon.controller.pelicula;

import jakarta.validation.Valid;
import mx.unam.aragon.model.entity.ActorEntity;
import mx.unam.aragon.model.entity.GeneroEntity;
import mx.unam.aragon.model.entity.PeliculaEntity;
import mx.unam.aragon.service.director.DirectorService;
import mx.unam.aragon.service.genero.GeneroService;
import mx.unam.aragon.service.pelicula.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "pelicula")

public class PeliculaController {

    @Autowired
    PeliculaService peliculaService;

    @Autowired
    GeneroService generoService;

    @Autowired
    DirectorService directorService;

    /// /pelicula/alta-pelicula
    @GetMapping("alta-pelicula")
    public String altaPelicula(Model model){
        PeliculaEntity pelicula = new PeliculaEntity();
        model.addAttribute("pelicula",pelicula);
        model.addAttribute("genero", generoService.findAll());
        model.addAttribute("director", directorService.findAll());
        model.addAttribute("contenido","Alta Pelicula");
        return "pelicula/alta-pelicula";
    }

    // /pelicula/guardar-pelicula
    @PostMapping("guardar-pelicula")
    public String guardarPelicula(@Valid @ModelAttribute(value = "pelicula") PeliculaEntity pelicula,
                                BindingResult result, Model model){
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "pelicula/alta-pelicula";
        }
        //realizar la lògica de negocio
        peliculaService.save(pelicula);
        model.addAttribute("contenido","Se almaceno con exito");
        return "pelicula/alta-pelicula";
    }

    @GetMapping("lista-pelicula")
    public String listaPelicula(Model model){
        List<PeliculaEntity> lista=peliculaService.findAll();
        model.addAttribute("lista",lista);
        model.addAttribute("contenido","Lista de Pelicula");
        return "pelicula/lista-pelicula";
    }

    @GetMapping("eliminar-pelicula/{id}")
    public String delete(@PathVariable("id")Long id,
                         Model model){
        peliculaService.deleteById(id);
        return "redirect:/pelicula/lista-pelicula";

    }
    @GetMapping("modificar-pelicula/{id}")
    public String mdificar(@PathVariable("id")Long id,
                           Model model){
        PeliculaEntity pelicula=peliculaService.findById(id);
        model.addAttribute("pelicula",pelicula);
        model.addAttribute("genero", generoService.findAll());
        model.addAttribute("director", directorService.findAll());
        model.addAttribute("contenido","Modificar pelicula");
        return "pelicula/alta-pelicula";

    }
}
