package com.devmarvin.noticias.services;

import com.devmarvin.noticias.entities.Noticia;

import java.util.List;

public interface NoticiaService {
    List<Noticia> obtenerNoticias();

    Noticia obtenerNoticiaDetalles(Long id);

    List<Noticia> obtenerNoticiasRecomendadas();
}
