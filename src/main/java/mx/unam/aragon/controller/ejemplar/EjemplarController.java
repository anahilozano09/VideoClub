package mx.unam.aragon.controller.ejemplar;

import jakarta.validation.Valid;
import mx.unam.aragon.model.entity.ActorEntity;
import mx.unam.aragon.model.entity.PeliculaEntity;
import mx.unam.aragon.model.entity.ejemplar.EjemplarEntity;
import mx.unam.aragon.model.entity.ejemplar.EjemplarId;
import mx.unam.aragon.model.entity.participa.ParticipaEntity;
import mx.unam.aragon.model.entity.participa.ParticipaId;
import mx.unam.aragon.service.ejemplar.EjemplarService;
import mx.unam.aragon.service.pelicula.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "ejemplar")
public class EjemplarController {
    @Autowired
    EjemplarService ejemplarService;
    @Autowired
    PeliculaService peliculaService;

    /// /ejemplar/alta-ejemplar
    @GetMapping("alta-ejemplar")
    public String altaEjemplar(Model model){
        EjemplarEntity ejemplar = new EjemplarEntity();
        // valor por defecto para consecutivo
        ejemplar.setConsecutivo(1L);
        model.addAttribute("ejemplar",ejemplar);
        model.addAttribute("pelicula", peliculaService.findAll());
        model.addAttribute("contenido","Alta Ejemplar");
        return "ejemplar/alta-ejemplar";
    }

    // /ejemplar/guardar-ejemplar
    @PostMapping("guardar-ejemplar")
    public String guardarEjemplar(
            @Valid @ModelAttribute("ejemplar") EjemplarEntity ejemplar,
            BindingResult result,
            Model model) {

        // Validación básica
        if (ejemplar.getPelicula() == null || ejemplar.getPelicula().getId() == null) {
            result.rejectValue("pelicula", "error.ejemplar", "Debe seleccionar una película");
        }

        if (ejemplar.getConsecutivo() == null) {
            result.rejectValue("consecutivo", "error.ejemplar", "El número consecutivo es obligatorio");
        }

        // Validar si ya existe el ejemplar
        if (ejemplar.getPelicula() != null && ejemplar.getConsecutivo() != null) {
            EjemplarId id = new EjemplarId(ejemplar.getPelicula().getId(), ejemplar.getConsecutivo());
            if (ejemplarService.existsById(id)) {
                result.rejectValue("consecutivo", "error.ejemplar",
                        "Ya existe un ejemplar con este número para la película seleccionada");
            }
        }

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "ejemplar/alta-ejemplar";
        }

        // Asignar ID y guardar
        EjemplarId id = new EjemplarId(ejemplar.getPelicula().getId(), ejemplar.getConsecutivo());
        ejemplar.setId(id);
        ejemplarService.save(ejemplar);

        model.addAttribute("contenido","Se almaceno con exito");
        return "ejemplar/alta-ejemplar";
    }

    @GetMapping("lista-ejemplar")
    public String listaParticipa(Model model){
        List<EjemplarEntity> lista=ejemplarService.findAll();
        model.addAttribute("lista",lista);
        model.addAttribute("contenido","Lista de Ejemplares");
        return "ejemplar/lista-ejemplar";
    }

    @GetMapping("eliminar-ejemplar/{idPelicula}/{consecutivo}")
    public String delete(@PathVariable("idPelicula") Long idPelicula,
                         @PathVariable("consecutivo") Long consecutivo){
        EjemplarId id = new EjemplarId(idPelicula, consecutivo);
        ejemplarService.deleteById(id);
        return "redirect:/ejemplar/lista-ejemplar";

    }
    @GetMapping("agregar-ejemplar/{idPelicula}/{consecutivo}")
    public String agregarEjemplar(
            @PathVariable Long idPelicula,
            @PathVariable Long consecutivo,
            Model model) {

        PeliculaEntity pelicula = peliculaService.findById(idPelicula);
        if (pelicula == null) {
            return "redirect:/ejemplar/lista-ejemplar";
        }

        // Obtener todos los ejemplares y filtrar localmente
        List<EjemplarEntity> todosEjemplares = ejemplarService.findAll();
        Long maxConsecutivo = todosEjemplares.stream()
                .filter(e -> e.getPelicula() != null && e.getPelicula().getId().equals(idPelicula))
                .mapToLong(EjemplarEntity::getConsecutivo)
                .max()
                .orElse(0L);

        EjemplarEntity ejemplar = new EjemplarEntity();
        ejemplar.setPelicula(pelicula);
        ejemplar.setConsecutivo(maxConsecutivo + 1);

        model.addAttribute("ejemplar", ejemplar);
        model.addAttribute("pelicula", peliculaService.findAll());
        model.addAttribute("contenido","Agregar Ejemplar a: " + pelicula.getTitulo());
        return "ejemplar/alta-ejemplar";
    }
}
