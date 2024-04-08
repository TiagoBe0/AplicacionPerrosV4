package com.perrosv1.app.servicios;

import com.perrosv1.app.entidades.Foto;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.perrosv1.app.entidades.Perro;

import com.perrosv1.app.repositorios.PerroRepository;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PerroService {

    @Autowired
    private PerroRepository perroRepository;
    
    @Autowired
    private FotoServicio fotoServicio;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Perro guardar(String nombre, String apodo, String raza,MultipartFile archivo ) throws Exception {

        validar(nombre, apodo, "null", raza);
        
       

        Perro entidad = new Perro();

        entidad.setNombre(nombre);
        entidad.setApodo(apodo);
      
        entidad.setRaza(raza);
        
        entidad.setActivo(true);
        entidad.setCreado(new Date());
 if(archivo!=null){
        
        Foto imagen = new Foto();
        imagen = fotoServicio.guardar(archivo);
        
        entidad.setImagen(imagen);
        
        }
        return perroRepository.save(entidad);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Perro modificar(String id, String nombre, String apodo, String foto, String raza) throws Exception {

        validar(nombre, apodo, foto, raza);

        Perro entidad = perroRepository.getOne(id);

        entidad.setNombre(nombre);
        entidad.setApodo(apodo);
        entidad.setFoto(foto);
        entidad.setRaza(raza);

        return perroRepository.save(entidad);

    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Perro alta(String id) {

        Perro entidad = perroRepository.getOne(id);

        entidad.setActivo(true);
        return perroRepository.save(entidad);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Perro baja(String id) {

        Perro entidad = perroRepository.getOne(id);

        entidad.setActivo(false);
        return perroRepository.save(entidad);
    }
    @Transactional(readOnly=true)
    public Perro getOne(String id){
        return perroRepository.getOne(id);
    }
    

    @Transactional(readOnly = true)
    public List<Perro> listarActivos() {
        return perroRepository.buscarActivos();
    }

    @Transactional(readOnly = true)
    public List<Perro> listarTodos() {
        return perroRepository.findAll();
    }

    public void validar(String nombre, String apodo, String foto, String raza) throws Exception {

        if (nombre == null || nombre.isEmpty() || nombre.contains("  ")) {
            throw new Exception();
        }

        if (apodo == null || apodo.isEmpty() || apodo.contains("  ")) {
            throw new Exception();
        }

        if (foto == null || foto.isEmpty() || foto.contains("  ")) {
            throw new Exception();
        }

        if (raza == null || raza.isEmpty() || raza.contains("  ")) {
            throw new Exception();
        }
    }
}
