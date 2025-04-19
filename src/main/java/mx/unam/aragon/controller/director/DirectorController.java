package mx.unam.aragon.controller.director;

import jakarta.validation.Valid;
import mx.unam.aragon.model.entity.DirectorEntity;
import mx.unam.aragon.service.director.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "director")
public class DirectorController {
    @Autowired
    DirectorService directorService;

    /// /director/alta-director
    @GetMapping("alta-director")
    public String altaDirector(Model model){
        DirectorEntity director=new DirectorEntity();
        model.addAttribute("director",director);
        model.addAttribute("contenido","Alta Director");
        return "director/alta-director";
    }
    // /director/guardar-director
    @PostMapping("guardar-director")
    public String guardarDirector(@Valid @ModelAttribute(value = "director") DirectorEntity director,
                                  BindingResult result,Model model){
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "director/alta-director";
        }
        //realizar la lògica de negocio
        directorService.save(director);
        model.addAttribute("contenido","Se almaceno con exito");
        return "director/alta-director";
    }
    @GetMapping("lista-director")
    public String listaDirector(Model model){
        List<DirectorEntity> lista=directorService.findAll();
        model.addAttribute("lista",lista);
        model.addAttribute("contenido","Lista de Directores");
        return "director/lista-director";
    }

    @GetMapping("eliminar-director/{id}")
    public String delete(@PathVariable("id")Long id,
                         Model model){
        directorService.deleteById(id);
        return "redirect:/director/lista-director";

    }
    @GetMapping("modificar-director/{id}")
    public String mdificar(@PathVariable("id")Long id,
                         Model model){
        DirectorEntity director=directorService.findById(id);
        model.addAttribute("director",director);
        model.addAttribute("contenido","Modificar director");
        return "director/alta-director";

    }

}
