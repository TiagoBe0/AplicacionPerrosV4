package com.perrosv1.app.repositorios;


import com.perrosv1.app.entidades.Foto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FotoRepositorio extends JpaRepository<Foto, String> {

    
}
