package mx.unam.aragon.controller.pago;

import jakarta.validation.Valid;
import mx.unam.aragon.converter.DigitosTarjetaConverter;
import mx.unam.aragon.model.entity.PagoEntity;
import mx.unam.aragon.model.entity.PeliculaEntity;
import mx.unam.aragon.model.entity.TipoPagoEntity;
import mx.unam.aragon.service.pago.PagoService;
import mx.unam.aragon.service.tipoPago.TipoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("pago")
public class PagoController {
    @Autowired
    PagoService pagoService;

    @Autowired
    TipoPagoService tipoPagoService;

    /// /pago/alta-pago
    @GetMapping("alta-pago")
    public String altaPago(Model model){
        PagoEntity pago = new PagoEntity();
        model.addAttribute("pago",pago);
        model.addAttribute("tipoPago", tipoPagoService.findAll());
        model.addAttribute("contenido","Alta Pago");
        return "pago/alta-pago";
    }

    // /pago/guardar-pago
    @PostMapping("guardar-pago")
    public String guardarPago(@Valid @ModelAttribute(value = "pago") PagoEntity pago,
                                  BindingResult result, Model model){

        if (pago.getTarjeta() == null || pago.getTarjeta().trim().isEmpty()) {
            result.rejectValue("tarjeta", "tarjeta.required",
                    "El número de tarjeta es obligatorio");
        }

        if (pago.getCantidad() == null || pago.getCantidad().doubleValue() <= 0) {
            result.rejectValue("cantidad", "cantidad.required",
                    "La cantidad de pago es obligatoria y debe ser mayor a 0");
        }


        if (pago.getTarjeta() != null && pago.getTipo() != null) {
            TipoPagoEntity tipoPago = tipoPagoService.findById(pago.getTipo().getId());

            if (tipoPago != null && tipoPago.getDigitos() != null) {
                String tarjetaSinFormato = pago.getTarjeta().replaceAll("[^0-9]", "");

                if (tarjetaSinFormato.length() != tipoPago.getDigitos()) {
                    result.rejectValue("tarjeta", "tarjeta.digitos",
                            "Este tipo de tarjeta requiere " + tipoPago.getDigitos() + " dígitos");
                }
            }
        }

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "pago/alta-pago";
        }
        //realizar la lògica de negocio
        pagoService.save(pago);
        model.addAttribute("contenido","Se almaceno con exito");
        return "pago/alta-pago";
    }

    @GetMapping("lista-pago")
    public String listaPago(Model model){
        List<PagoEntity> lista=pagoService.findAll();
        model.addAttribute("lista",lista);
        model.addAttribute("contenido","Lista de Pagos");
        return "pago/lista-pago";
    }

    @GetMapping("eliminar-pago/{id}")
    public String delete(@PathVariable("id")Long id,
                         Model model){
        pagoService.deleteById(id);
        return "redirect:/pago/lista-pago";

    }
    @GetMapping("modificar-pago/{id}")
    public String mdificar(@PathVariable("id")Long id,
                           Model model){
        PagoEntity pago=pagoService.findById(id);
        model.addAttribute("pago",pago);
        model.addAttribute("tipoPago", tipoPagoService.findAll());
        model.addAttribute("contenido","Modificar pago");
        return "pago/alta-pago";

    }

    @InitBinder("pago")
    public void convertir(WebDataBinder binder){
        binder.registerCustomEditor(String.class,
                "tarjeta", new DigitosTarjetaConverter());
    }
}
