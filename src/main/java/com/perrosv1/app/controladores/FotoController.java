package com.perrosv1.app.controladores;



import com.perrosv1.app.entidades.Foto;
import com.perrosv1.app.entidades.Perro;
import com.perrosv1.app.errores.ErrorServicio;
import com.perrosv1.app.servicios.FotoServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/foto")
public class FotoController {


@Autowired
private FotoServicio fotoServicio;

   @GetMapping("/perrofoto/{id}")
    public ResponseEntity<byte[]> fotoCristal(@PathVariable String id) {

        try {
           Perro perro =new Perro();
            if (perro.getImagen() == null) {
                throw new ErrorServicio("El usuario no tiene una foto asignada.");
            }
            byte[] foto = perro.getImagen().getContenido();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(foto, headers, HttpStatus.OK);
        } catch (ErrorServicio ex) {
            Logger.getLogger(FotoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    
       @GetMapping("/foto/{id}")
    public ResponseEntity<byte[]> foto(@PathVariable String id) throws ErrorServicio {

           Foto foto=new Foto();
          foto=fotoServicio.buscarPorId(id);
            

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(foto.getContenido(), headers, HttpStatus.OK);
       

    }


}
