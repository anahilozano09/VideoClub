package mx.unam.aragon.controller.detallePrestamo;

import jakarta.validation.Valid;
import mx.unam.aragon.model.entity.PeliculaEntity;
import mx.unam.aragon.model.entity.PrestamoEntity;
import mx.unam.aragon.model.entity.detallePrestamo.DetallePrestamoEntity;
import mx.unam.aragon.model.entity.detallePrestamo.DetallePrestamoId;
import mx.unam.aragon.model.entity.ejemplar.EjemplarEntity;
import mx.unam.aragon.model.entity.ejemplar.EjemplarId;
import mx.unam.aragon.model.entity.listaEspera.ListaEsperaEntity;
import mx.unam.aragon.model.entity.listaEspera.ListaEsperaId;
import mx.unam.aragon.model.entity.participa.ParticipaEntity;
import mx.unam.aragon.model.entity.participa.ParticipaId;
import mx.unam.aragon.service.detallePrestamo.DetallePrestamoService;
import mx.unam.aragon.service.ejemplar.EjemplarService;
import mx.unam.aragon.service.pelicula.PeliculaService;
import mx.unam.aragon.service.prestamo.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "detallePrestamo")
public class DetallePrestamoController {
    @Autowired
    DetallePrestamoService detallePrestamoService;

    @Autowired
    PeliculaService peliculaService;

    @Autowired
    PrestamoService prestamoService;

    @Autowired
    EjemplarService ejemplarService;


    /// /detallePrestamo/alta-detallePrestamo
    @GetMapping("alta-detallePrestamo")
    public String altaDetallePrestamo(Model model){
        DetallePrestamoEntity detallePrestamo = new DetallePrestamoEntity();
        model.addAttribute("detallePrestamo",detallePrestamo);
        model.addAttribute("prestamos", prestamoService.findAll());
        model.addAttribute("peliculas", peliculaService.findAll());

        model.addAttribute("ejemplares", ejemplarService.findAll());
        model.addAttribute("contenido","Alta Detalle de Prestamo");
        return "detallePrestamo/alta-detallePrestamo";
    }

    // /detallePrestamo/guardar-detallePrestamo
    @PostMapping("guardar-detallePrestamo")
    public String guardarDetallePrestamo(@Valid @ModelAttribute(value = "detallePrestamo") DetallePrestamoEntity detallePrestamo,
                                   BindingResult result, Model model){

        if (detallePrestamo.getPrestamo() == null || detallePrestamo.getPelicula() == null ||
                detallePrestamo.getEjemplar() == null || detallePrestamo.getEjemplar().getId() == null) {
            result.reject("", "Debe seleccionar préstamo, película y ejemplar válidos");
        }

        Long idPrestamo = detallePrestamo.getPrestamo().getId();
        Long idPelicula = detallePrestamo.getPelicula().getId();
        Long consecutivo = detallePrestamo.getEjemplar().getId().getConsecutivo();

        PrestamoEntity prestamo = prestamoService.findById(idPrestamo);
        PeliculaEntity pelicula = peliculaService.findById(idPelicula);
        EjemplarId idEjemplar = new EjemplarId(idPelicula, consecutivo);
        EjemplarEntity ejemplar = ejemplarService.findById(idEjemplar);

        if (prestamo == null || pelicula == null || ejemplar == null) {
            result.reject("", "Prestamo, película o ejemplar no encontrados");
        }

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "detallePrestamo/alta-detallePrestamo";
        }

        DetallePrestamoId nuevoId = new DetallePrestamoId(idPrestamo, idPelicula, consecutivo);
        boolean esModificacion = detallePrestamo.getId() != null;

        if (!esModificacion) {
            if (detallePrestamoService.existsById(nuevoId)) {
                result.reject("", "Ya existe esta combinación de préstamo, película y ejemplar");
                model.addAttribute("prestamos", prestamoService.findAll());
                model.addAttribute("peliculas", peliculaService.findAll());
                model.addAttribute("ejemplares", ejemplarService.findByPelicula(pelicula));
                model.addAttribute("contenido", "Alta Detalle de Préstamo");
                return "detallePrestamo/alta-detallePrestamo";
            }

            detallePrestamo.setId(nuevoId);
            detallePrestamo.setPrestamo(prestamo);
            detallePrestamo.setPelicula(pelicula);
            detallePrestamo.setEjemplar(ejemplar);
            detallePrestamoService.save(detallePrestamo);
        } else {
            DetallePrestamoId idOriginal = detallePrestamo.getId();

            if (!idOriginal.equals(nuevoId)) {
                // Si cambia la combinación de claves primarias
                if (detallePrestamoService.existsById(nuevoId)) {
                    result.reject("", "Ya existe esta combinación de préstamo, película y ejemplar");
                    model.addAttribute("prestamos", prestamoService.findAll());
                    model.addAttribute("peliculas", peliculaService.findAll());
                    model.addAttribute("ejemplares", ejemplarService.findByPelicula(pelicula));
                    model.addAttribute("contenido", "Modificar Detalle de Préstamo");
                    return "detallePrestamo/alta-detallePrestamo";
                }

                // Borrar y guardar como nuevo
                detallePrestamoService.deleteById(idOriginal);
                DetallePrestamoEntity nuevo = new DetallePrestamoEntity();
                nuevo.setId(nuevoId);
                nuevo.setPrestamo(prestamo);
                nuevo.setPelicula(pelicula);
                nuevo.setEjemplar(ejemplar);
                detallePrestamoService.save(nuevo);
            } else {
                // Es la misma combinación, solo actualiza
                detallePrestamo.setPrestamo(prestamo);
                detallePrestamo.setPelicula(pelicula);
                detallePrestamo.setEjemplar(ejemplar);
                detallePrestamoService.save(detallePrestamo);
            }
        }

        model.addAttribute("contenido","Se almaceno con exito");
        return "detallePrestamo/alta-detallePrestamo";
    }

    @GetMapping("lista-detallePrestamo")
    public String listaDetallePrestamo(Model model){
        List<DetallePrestamoEntity> lista=detallePrestamoService.findAll();
        model.addAttribute("lista",lista);
        model.addAttribute("contenido","Lista de Detalle de Prestamo");
        return "detallePrestamo/lista-detallePrestamo";
    }

    @GetMapping("eliminar-detallePrestamo/{idPrestamo}/{idPelicula}/{consecutivo}")
    public String delete(@PathVariable("idPrestamo") Long idPrestamo,
                         @PathVariable("idPelicula") Long idPelicula,
                         @PathVariable("consecutivo") Long consecutivo){
        DetallePrestamoId id = new DetallePrestamoId(idPrestamo, idPelicula, consecutivo);
        detallePrestamoService.deleteById(id);
        return "redirect:/detallePrestamo/lista-detallePrestamo";

    }
    @GetMapping("/modificar-detallePrestamo/{idPrestamo}/{idPelicula}/{consecutivo}")
    public String modificar(
            @PathVariable("idPrestamo") Long idPrestamo,
            @PathVariable("idPelicula") Long idPelicula,
            @PathVariable("consecutivo") Long consecutivo,
            Model model) {

        DetallePrestamoId id = new DetallePrestamoId(idPrestamo, idPelicula, consecutivo);
        DetallePrestamoEntity detallePrestamo = detallePrestamoService.findById(id);

        EjemplarId idEjemplar = new EjemplarId(idPelicula, consecutivo);
        PeliculaEntity pelicula = peliculaService.findById(idPelicula);

        detallePrestamo.setPrestamo(prestamoService.findById(idPrestamo));
        detallePrestamo.setPelicula(pelicula);
        detallePrestamo.setEjemplar(ejemplarService.findById(idEjemplar));

        // Solo ejemplares de la película seleccionada
        List<EjemplarEntity> ejemplares = ejemplarService.findByPelicula(pelicula);

        model.addAttribute("detallePrestamo", detallePrestamo);
        model.addAttribute("prestamos", prestamoService.findAll());
        model.addAttribute("peliculas", peliculaService.findAll());
        model.addAttribute("ejemplares", ejemplares);
        model.addAttribute("contenido", "Modificar Detalle de Préstamo");

        return "detallePrestamo/alta-detallePrestamo";
    }
}
