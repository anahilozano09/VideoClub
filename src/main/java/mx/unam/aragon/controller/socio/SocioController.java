package mx.unam.aragon.controller.socio;

import jakarta.validation.Valid;
import mx.unam.aragon.model.entity.GeneroEntity;
import mx.unam.aragon.model.entity.SocioEntity;
import mx.unam.aragon.service.socio.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "socio")

public class SocioController {
    @Autowired
    SocioService socioService;

    /// /socio/alta-socio
    @GetMapping("alta-socio")
    public String altaSocio(Model model){
        SocioEntity socio = new SocioEntity();
        model.addAttribute("socio", socio);
        model.addAttribute("contenido","Alta Socio");
        return "socio/alta-socio";
    }

    // /socio/guardar-socio
    @PostMapping("guardar-socio")
    public String guardarSocio(@Valid @ModelAttribute(value = "socio") SocioEntity socio,
                                BindingResult result, Model model){
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "socio/alta-socio";
        }
        //realizar la lògica de negocio
        socioService.save(socio);
        model.addAttribute("contenido","Se almaceno con exito");
        return "socio/alta-socio";
    }

    @GetMapping("lista-socio")
    public String listaSocio(Model model){
        List<SocioEntity> lista=socioService.findAll();
        model.addAttribute("lista",lista);
        model.addAttribute("contenido","Lista de Socios");
        return "socio/lista-socio";
    }

    @GetMapping("eliminar-socio/{id}")
    public String delete(@PathVariable("id")Long id,
                         Model model){
        socioService.deleteById(id);
        return "redirect:/socio/lista-socio";

    }
    @GetMapping("modificar-socio/{id}")
    public String mdificar(@PathVariable("id")Long id,
                           Model model){
        SocioEntity socio=socioService.findById(id);
        model.addAttribute("socio",socio);
        model.addAttribute("contenido","Modificar socio");
        return "socio/alta-socio";

    }
}
