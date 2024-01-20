package com.devmarvin.noticias.repositories;

import com.devmarvin.noticias.entities.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
    List<Noticia> findAllByOrderByFechaPublicacionesDesc();

    //Noticias recomendadas
    List<Noticia> findAllByEsRecomendadaTrueOrderByFechaPublicacionesDesc();

}
