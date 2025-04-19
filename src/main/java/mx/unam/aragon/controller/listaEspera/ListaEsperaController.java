package mx.unam.aragon.controller.listaEspera;

import jakarta.validation.Valid;
import mx.unam.aragon.model.entity.ActorEntity;
import mx.unam.aragon.model.entity.PeliculaEntity;
import mx.unam.aragon.model.entity.SocioEntity;
import mx.unam.aragon.model.entity.listaEspera.ListaEsperaEntity;
import mx.unam.aragon.model.entity.listaEspera.ListaEsperaId;
import mx.unam.aragon.model.entity.participa.ParticipaEntity;
import mx.unam.aragon.model.entity.participa.ParticipaId;
import mx.unam.aragon.service.listaEspera.ListaEsperaService;
import mx.unam.aragon.service.pelicula.PeliculaService;
import mx.unam.aragon.service.socio.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "listaEspera")
public class ListaEsperaController {
    @Autowired
    ListaEsperaService listaEsperaService;
    @Autowired
    SocioService socioService;
    @Autowired
    PeliculaService peliculaService;

    /// /listaEspera/alta-listaEspera
    @GetMapping("alta-listaEspera")
    public String altaListaEspera(Model model){
        ListaEsperaEntity listaEspera = new ListaEsperaEntity();
        model.addAttribute("listaEspera",listaEspera);
        model.addAttribute("socio", socioService.findAll());
        model.addAttribute("pelicula", peliculaService.findAll());
        model.addAttribute("contenido","Alta Lista de Espera");
        return "listaEspera/alta-listaEspera";
    }

    // /listaEspera/guardar-participa
    @PostMapping("guardar-listaEspera")
    public String guardarListaEspera(@Valid @ModelAttribute(value = "listaEspera") ListaEsperaEntity listaEspera,
                                   BindingResult result, Model model){

        // Validación básica
        if (listaEspera.getSocio() == null || listaEspera.getPelicula() == null) {
            result.reject("", "Debe seleccionar un socio y una película");
        }

        if (listaEspera.getFecha() == null) {
            result.rejectValue("fecha", "NotNull", "La fecha es obligatoria");
        }

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "listaEspera/alta-listaEspera";
        }

        SocioEntity socio = socioService.findById(listaEspera.getSocio().getId());
        PeliculaEntity pelicula = peliculaService.findById(listaEspera.getPelicula().getId());

        if (socio == null || pelicula == null) {
            result.reject("", "Socio o película no encontrados");
            return "listaEspera/alta-listaEspera";
        }

        // Caso de modificación
        if (listaEspera.getId() != null) {
            ListaEsperaEntity existente = listaEsperaService.findById(listaEspera.getId());

            if (existente != null) {
                // Actualizar todos los campos incluyendo la fecha
                existente.setSocio(socio);
                existente.setPelicula(pelicula);
                existente.setFecha(listaEspera.getFecha()); // Esta línea es crucial

                listaEsperaService.save(existente);
                model.addAttribute("contenido","Se almaceno con exito");
                return "listaEspera/alta-listaEspera";
            }
        }
        // Caso de alta nueva
        else {
            ListaEsperaId nuevoId = new ListaEsperaId(socio.getId(), pelicula.getId());

            if (listaEsperaService.existsById(nuevoId)) {
                result.reject("", "Ya existe esta combinación de socio y película");
                model.addAttribute("socio", socioService.findAll());
                model.addAttribute("pelicula", peliculaService.findAll());
                model.addAttribute("contenido","Se almaceno con exito");
                return "listaEspera/alta-listaEspera";
            }

            listaEspera.setId(nuevoId);
            listaEspera.setSocio(socio);
            listaEspera.setPelicula(pelicula);
            listaEsperaService.save(listaEspera);
        }

        model.addAttribute("contenido","Se almaceno con exito");
        return "listaEspera/alta-listaEspera";
    }


    @GetMapping("lista-listaEspera")
    public String listaListaEspera(Model model){
        List<ListaEsperaEntity> lista=listaEsperaService.findAll();
        model.addAttribute("lista",lista);
        model.addAttribute("contenido","Lista de Lista de Espera");
        return "listaEspera/lista-listaEspera";
    }

    @GetMapping("eliminar-listaEspera/{idSocio}/{idPelicula}")
    public String delete(@PathVariable("idSocio") Long idSocio,
                         @PathVariable("idPelicula") Long idPelicula){
        ListaEsperaId id = new ListaEsperaId(idSocio,idPelicula);
        listaEsperaService.deleteById(id);
        return "redirect:/listaEspera/lista-listaEspera";

    }
    @GetMapping("modificar-listaEspera/{idSocio}/{idPelicula}")
    public String mdificar(@PathVariable("idSocio") Long idSocio,
                           @PathVariable("idPelicula") Long idPelicula,
                           Model model){

        ListaEsperaId id = new ListaEsperaId(idSocio,idPelicula);
        ListaEsperaEntity listaEspera=listaEsperaService.findById(id);

        listaEspera.setSocio(socioService.findById(idSocio));
        listaEspera.setPelicula(peliculaService.findById(idPelicula));

        model.addAttribute("listaEspera",listaEspera);
        model.addAttribute("socio", socioService.findAll());
        model.addAttribute("pelicula", peliculaService.findAll());

        model.addAttribute("contenido","Modificar participa");
        return "listaEspera/alta-listaEspera";

    }
}
