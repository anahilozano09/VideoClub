package mx.unam.aragon.controller.director;

import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.validation.Valid;
import mx.unam.aragon.converter.EnteroConverter;
import mx.unam.aragon.model.entity.DirectorEntity;
import mx.unam.aragon.service.director.DirectorService;
import mx.unam.aragon.validator.NombreValidaor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.servlet.http.HttpSession;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping(value = "director")
public class DirectorController {
    @Autowired
    DirectorService directorService;

    @Autowired
    NombreValidaor nombreValidaor;

    @Value("${ejemplo.imagen.ruta}")
    private String archivoRuta;


    /// /director/alta-director
    @GetMapping("alta-director")
    public String altaDirector(Model model){
        DirectorEntity director=new DirectorEntity();
        model.addAttribute("director",director);
        model.addAttribute("contenido","Alta Director");
        return "director/alta-director";
    }
    // /director/guardar-director
    @PostMapping("guardar-director")
    public String guardarDirector(@Valid @ModelAttribute(value = "director") DirectorEntity director,
                                  BindingResult result,Model model){
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "director/alta-director";
        }
        //realizar la lògica de negocio
        directorService.save(director);
        model.addAttribute("contenido","Se almaceno con exito");
        return "director/alta-director";
    }
    @GetMapping("lista-director")
    public String listaDirector(Model model){
        List<DirectorEntity> lista=directorService.findAll();
        model.addAttribute("lista",lista);
        model.addAttribute("contenido","Lista de Directores");
        return "director/lista-director";
    }

    @GetMapping("eliminar-director/{id}")
    public String delete(@PathVariable("id")Long id,
                         Model model){
        directorService.deleteById(id);
        return "redirect:/director/lista-director";

    }
    @GetMapping("modificar-director/{id}")
    public String mdificar(@PathVariable("id")Long id,
                         Model model){
        DirectorEntity director=directorService.findById(id);
        model.addAttribute("director",director);
        model.addAttribute("contenido","Modificar director");
        return "director/alta-director";

    }
    @InitBinder("director")
    public void convertir(WebDataBinder binder){
        binder.registerCustomEditor(Integer.class,
                "dato",new EnteroConverter());
        binder.addValidators(nombreValidaor);

    }
    @GetMapping("crear-pdf")
    public String generarPdf(Model modelo){
        List<DirectorEntity> usuarios=directorService.findAll();
        modelo.addAttribute("datos",usuarios);
        modelo.addAttribute("ruta",archivoRuta);
        return "principal/crear-pdf";
    }
    @GetMapping("mandar-correo")
    private String gmail(RedirectAttributes model) {
        List<DirectorEntity> usuarios=directorService.findAll();
        String gmail = "clientepruebaspring@gmail.com";
        String pswd = "ehlv ioyt qixn lmwl";
        Properties p = System.getProperties();
        p.setProperty("mail.smtps.host", "smpt.gmail.com");
        p.setProperty("mail.smtps.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.setProperty("mail.smtps.socketFactory.fallback", "false");
        p.setProperty("mail.smtp.port", "465");
        p.setProperty("mail.smtp.socketFactory.port", "465");
        p.setProperty("mail.smtps.auth", "true");
        p.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
        p.setProperty("mail.smtps.ssl.trust", "smtp.gmail.com");
        p.setProperty("mail.smtp.ssl.quitwait", "false");
        //construcción del html
        String cadena = "<h2>Usuarios</br>";
        for (DirectorEntity s : usuarios) {
            cadena += "<h2>" +
                    s.getNombre() +
                    "</h2></br>";
        }
        try {
            Session session = Session.getInstance(p, null);
            MimeMessage message = new MimeMessage(session);

            MimeBodyPart texto = new MimeBodyPart();
            texto.setContent(cadena, "text/html;charset=utf-8");
            //adjuntar la imagen
            BodyPart adjunto = new MimeBodyPart();
            String r = archivoRuta + "/fes.jpg";
            adjunto.setDataHandler(new DataHandler(new FileDataSource(r)));
            adjunto.setFileName("temp.pdf");
            Multipart multiple = new MimeMultipart();
            multiple.addBodyPart(texto);
            multiple.addBodyPart(adjunto);

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("clientepruebaspring@gmail.com", false));
            message.setSubject("Informacion de Spring");
            message.setContent(multiple);
            message.setSentDate(new Date());


            Transport transport = (Transport) session.getTransport("smtps");
            transport.connect("smtp.gmail.com", gmail, pswd);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addFlashAttribute("contenido", "El correo se mando con éxito");
        return "redirect:/director/lista-director";
    }
}
