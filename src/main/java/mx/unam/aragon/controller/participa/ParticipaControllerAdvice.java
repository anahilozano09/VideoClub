package mx.unam.aragon.controller.participa;

import mx.unam.aragon.model.entity.participa.ParticipaId;
import mx.unam.aragon.model.entity.participa.ParticipaIdEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class ParticipaControllerAdvice {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(ParticipaId.class, new ParticipaIdEditor());
    }
}
