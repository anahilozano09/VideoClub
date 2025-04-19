package mx.unam.aragon.controller.prestamo;

import jakarta.validation.Valid;
import mx.unam.aragon.model.entity.PeliculaEntity;
import mx.unam.aragon.model.entity.PrestamoEntity;
import mx.unam.aragon.service.prestamo.PrestamoService;
import mx.unam.aragon.service.socio.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "prestamo")

public class PrestamoController {

    @Autowired
    PrestamoService prestamoService;
    @Autowired
    SocioService socioService;

    /// /prestamo/alta-prestamo
    @GetMapping("alta-prestamo")
    public String altaPrestamo(Model model){
        PrestamoEntity prestamo = new PrestamoEntity();
        model.addAttribute("prestamo",prestamo);
        model.addAttribute("socio", socioService.findAll());
        model.addAttribute("contenido","Alta Prestamo");
        return "prestamo/alta-prestamo";
    }

    // /prestamo/guardar-prestamo
    @PostMapping("guardar-prestamo")
    public String guardarPrestamo(@Valid @ModelAttribute(value = "prestamo") PrestamoEntity prestamo,
                                  BindingResult result, Model model){
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "prestamo/alta-prestamo";
        }
        //realizar la lògica de negocio
        prestamoService.save(prestamo);
        model.addAttribute("contenido","Se almaceno con exito");
        return "prestamo/alta-prestamo";
    }

    @GetMapping("lista-prestamo")
    public String listaPrestamo(Model model){
        List<PrestamoEntity> lista=prestamoService.findAll();
        model.addAttribute("lista",lista);
        model.addAttribute("contenido","Lista de Prestamo");
        return "prestamo/lista-prestamo";
    }

    @GetMapping("eliminar-prestamo/{id}")
    public String delete(@PathVariable("id")Long id,
                         Model model){
        prestamoService.deleteById(id);
        return "redirect:/prestamo/lista-prestamo";

    }
    @GetMapping("modificar-prestamo/{id}")
    public String mdificar(@PathVariable("id")Long id,
                           Model model){
        PrestamoEntity prestamo=prestamoService.findById(id);
        model.addAttribute("prestamo",prestamo);
        model.addAttribute("socio", socioService.findAll());
        model.addAttribute("contenido","Modificar prestamo");
        return "prestamo/alta-prestamo";

    }
}
