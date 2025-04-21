package mx.unam.aragon.controller.detallePrestamo;

import jakarta.validation.Valid;
import mx.unam.aragon.model.entity.PeliculaEntity;
import mx.unam.aragon.model.entity.PrestamoEntity;
import mx.unam.aragon.model.entity.detallePrestamo.DetallePrestamoEntity;
import mx.unam.aragon.model.entity.detallePrestamo.DetallePrestamoId;
import mx.unam.aragon.model.entity.ejemplar.EjemplarEntity;
import mx.unam.aragon.model.entity.ejemplar.EjemplarId;
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
        model.addAttribute("pelicula", peliculaService.findAll());
        model.addAttribute("ejemplar", ejemplarService.findAll());
        model.addAttribute("contenido","Alta Detalle de Prestamo");
        return "detallePrestamo/alta-detallePrestamo";
    }

    // /detallePrestamo/guardar-detallePrestamo
    @PostMapping("guardar-detallePrestamo")
    public String guardarDetallePrestamo(@Valid @ModelAttribute(value = "detallePrestamo") DetallePrestamoEntity detallePrestamo,
                                   BindingResult result, Model model){

        // Validación básica
        if (detallePrestamo.getPrestamo() == null || detallePrestamo.getPelicula() == null || detallePrestamo.getEjemplar() == null) {
            result.reject("", "Debe seleccionar prestamo, pelicula y ejemplar");
        }

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "detallePrestamo/alta-detallePrestamo";
        }

        PrestamoEntity prestamo = prestamoService.findById(detallePrestamo.getPrestamo().getId());
        PeliculaEntity pelicula = peliculaService.findById(detallePrestamo.getPelicula().getId());
        EjemplarEntity ejemplar = ejemplarService.findById(detallePrestamo.getEjemplar().getId());

        if (prestamo == null || pelicula == null || ejemplar == null) {
            result.reject("", "Prestamo, pelicula o ejemplar no encontrados");
            return "detallePrestamo/alta-detallePrestamo";
        }

        // Caso de modificación
        if (detallePrestamo.getId() != null) {
            DetallePrestamoId idOriginal = detallePrestamo.getId();
            DetallePrestamoEntity existente = detallePrestamoService.findById(idOriginal);

            if (existente == null) {
                return "detallePrestamo/lista-detallePrestamo";
            }

            boolean cambioPrestamo = !prestamo.getId().equals(idOriginal.getIdPrestamo());
            boolean cambioPelicula = !pelicula.getId().equals(idOriginal.getIdPelicula());
            boolean cambioEjemplar = !ejemplar.getId().equals(idOriginal.getConsecutivo());

            if (cambioPrestamo || cambioPelicula || cambioEjemplar) {
                // Crear nuevo ID
                DetallePrestamoId nuevoId = new DetallePrestamoId(prestamo.getId(), pelicula.getId(), ejemplar.getConsecutivo());

                if (detallePrestamoService.existsById(nuevoId)) {
                    result.reject("", "Ya existe esta combinación de película y actor");
                    return "detallePrestamo/alta-detallePrestamo";
                }

                // Eliminar original y crear nueva
                detallePrestamoService.deleteById(idOriginal);

                DetallePrestamoEntity nuevoDetallePrestamo = new DetallePrestamoEntity();
                nuevoDetallePrestamo.setId(nuevoId);
                nuevoDetallePrestamo.setPrestamo(prestamo);
                nuevoDetallePrestamo.setPelicula(pelicula);
                nuevoDetallePrestamo.setEjemplar(ejemplar);
                // Copiar otros atributos si existen
                // nuevaParticipa.setOtrosDatos(existente.getOtrosDatos());

                detallePrestamoService.save(detallePrestamo);
            } else {
                // Actualizar existente
                existente.setPrestamo(prestamo);
                existente.setPelicula(pelicula);
                existente.setEjemplar(ejemplar);
                detallePrestamoService.save(existente);
            }
        } else {
            // Alta nueva
            DetallePrestamoId nuevoId = new DetallePrestamoId(prestamo.getId(), pelicula.getId(), ejemplar.getConsecutivo());

            if (detallePrestamoService.existsById(nuevoId)) {
                result.reject("", "Ya existe esta combinación de prestamo, película y ejemplar");
                return "detallePrestamo/alta-detallePrestamo";
            }

            detallePrestamo.setId(nuevoId);
            detallePrestamo.setPrestamo(prestamo);
            detallePrestamo.setPelicula(pelicula);
            detallePrestamo.setEjemplar(ejemplar);
            detallePrestamoService.save(detallePrestamo);
        }
        model.addAttribute("contenido","Se almaceno con exito");
        return "participa/alta-participa";
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
    @GetMapping("modificar-detallePrestamo/{idPrestamo}/{idPelicula}/{consecutivo}")
    public String mdificar(@PathVariable("idPrestamo") Long idPrestamo,
                           @PathVariable("idPelicula") Long idPelicula,
                           @PathVariable("consecutivo") Long consecutivo,
                           Model model){

        DetallePrestamoId id = new DetallePrestamoId(idPrestamo, idPelicula, consecutivo);
        DetallePrestamoEntity detallePrestamo=detallePrestamoService.findById(id);

        EjemplarId idEjemplar = new EjemplarId(idPelicula, consecutivo);

        detallePrestamo.setPrestamo(prestamoService.findById(idPrestamo));
        detallePrestamo.setPelicula(peliculaService.findById(idPelicula));
        detallePrestamo.setEjemplar(ejemplarService.findById(idEjemplar));

        model.addAttribute("detallePrestamo",detallePrestamo);
        model.addAttribute("prestamos", prestamoService.findAll());
        model.addAttribute("pelicula", peliculaService.findAll());
        model.addAttribute("ejemplar", ejemplarService.findAll());
        model.addAttribute("contenido","Modificar ejemplar");
        return "detallePrestamo/alta-detallePrestamo";

    }
}
