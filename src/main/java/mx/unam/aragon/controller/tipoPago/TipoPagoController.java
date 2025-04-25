package mx.unam.aragon.controller.tipoPago;

import jakarta.validation.Valid;
import mx.unam.aragon.model.entity.ActorEntity;
import mx.unam.aragon.model.entity.TipoPagoEntity;
import mx.unam.aragon.service.tipoPago.TipoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "tipoPago")
public class TipoPagoController {
    @Autowired
    TipoPagoService tipoPagoService;

    /// /tipoPago/alta-tipoPago
    @GetMapping("alta-tipoPago")
    public String altaTipoPago(Model model){
        TipoPagoEntity tipoPago = new TipoPagoEntity();
        model.addAttribute("tipoPago",tipoPago);
        model.addAttribute("contenido","Alta Tipo Pago");
        return "tipoPago/alta-tipoPago";
    }

    // /tipoPago/guardar-tipoPago
    @PostMapping("guardar-tipoPago")
    public String guardarTipoPago(@Valid @ModelAttribute(value = "actor") TipoPagoEntity tipoPago,
                                  BindingResult result, Model model){
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "tipo/alta-tipoPago";
        }
        //realizar la lògica de negocio
        tipoPagoService.save(tipoPago);
        model.addAttribute("contenido","Se almaceno con exito");
        return "tipoPago/alta-tipoPago";
    }

    @GetMapping("lista-tipoPago")
    public String listaTipoPago(Model model){
        List<TipoPagoEntity> lista=tipoPagoService.findAll();
        model.addAttribute("lista",lista);
        model.addAttribute("contenido","Lista de Tipos de Pago");
        return "tipoPago/lista-tipoPago";
    }

    @GetMapping("eliminar-tipoPago/{id}")
    public String delete(@PathVariable("id")Long id,
                         Model model){
        tipoPagoService.deleteById(id);
        return "redirect:/tipoPago/lista-tipoPago";

    }
    @GetMapping("modificar-tipoPago/{id}")
    public String mdificar(@PathVariable("id")Long id,
                           Model model){
        TipoPagoEntity tipoPago=tipoPagoService.findById(id);
        model.addAttribute("tipoPago",tipoPago);
        model.addAttribute("contenido","Modificar Tipo de Pago");
        return "tipoPago/alta-tipoPago";

    }
}
