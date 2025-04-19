package mx.unam.aragon.controller.devolucion;

import jakarta.validation.Valid;
import mx.unam.aragon.model.entity.DevolucionEntity;
import mx.unam.aragon.model.entity.PrestamoEntity;
import mx.unam.aragon.service.devolucion.DevolucionService;
import mx.unam.aragon.service.prestamo.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "devolucion")
public class DevolucionController {
    @Autowired
    DevolucionService devolucionService;

    @Autowired
    PrestamoService prestamoService;

    /// /devolucion/alta-devolucion
    @GetMapping("alta-devolucion")
    public String altaDevolucion(Model model){
        DevolucionEntity devolucion = new DevolucionEntity();
        model.addAttribute("devolucion",devolucion);
        model.addAttribute("prestamos", prestamoService.findAll());
        model.addAttribute("contenido","Alta Devolucion");
        return "devolucion/alta-devolucion";
    }

    // /devolucion/guardar-devolucion
    @PostMapping("guardar-devolucion")
    public String guardarDevolucion(@Valid @ModelAttribute(value = "devolucion") DevolucionEntity devolucion,
                                  BindingResult result, Model model){

        // Validación personalizada para el préstamo
        if (devolucion.getPrestamo() == null || devolucion.getPrestamo().getId() == null) {
            result.rejectValue("prestamo", "NotNull", "Debe seleccionar un préstamo");
        }

        // Validación de fecha de devolución
        if (devolucion.getFecha() == null) {
            result.rejectValue("fechaDevolucion", "NotNull", "La fecha de devolución es obligatoria");
        }

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "devolucion/alta-devolucion";
        }
        //realizar la lògica de negocio

        devolucionService.save(devolucion);
        model.addAttribute("contenido","Se almaceno con exito");
        return "devolucion/alta-devolucion";
    }

    @GetMapping("lista-devolucion")
    public String listaDevolucion(Model model){
        List<DevolucionEntity> lista=devolucionService.findAll();
        model.addAttribute("lista",lista);
        model.addAttribute("contenido","Lista de Devolucion");
        return "devolucion/lista-devolucion";
    }

    @GetMapping("eliminar-devolucion/{id}")
    public String delete(@PathVariable("id")Long id,
                         Model model){
        devolucionService.deleteById(id);
        return "redirect:/devolucion/lista-devolucion";

    }
    @GetMapping("modificar-devolucion/{id}")
    public String mdificar(@PathVariable("id")Long id,
                           Model model){

        DevolucionEntity devolucion=devolucionService.findById(id);

        if (devolucion == null) {
            return "devolucion/lista-devolucion";
        }

        // Cargar el préstamo asociado si existe
        PrestamoEntity prestamoSeleccionado = null;
        if (devolucion.getPrestamo() != null) {
            prestamoSeleccionado = prestamoService.findById(devolucion.getPrestamo().getId());
        }

        model.addAttribute("devolucion",devolucion);
        model.addAttribute("prestamos", prestamoService.findAll());

        model.addAttribute("contenido","Modificar devolucion");
        return "devolucion/alta-devolucion";

    }
}
