package mx.unam.aragon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class InicioController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String inicio(){
        return "inicio";
    }

    @RequestMapping(value = "/principal",method = RequestMethod.GET)
    public String cambio(){
        return "principal";
    }

}
