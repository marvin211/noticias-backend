package com.devmarvin.noticias.services.impl;

import com.devmarvin.noticias.entities.Noticia;
import com.devmarvin.noticias.repositories.NoticiaRepository;
import com.devmarvin.noticias.services.NoticiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticiaServiceImpl implements NoticiaService {

    private final NoticiaRepository noticiaRepository;

    @Override
    public List<Noticia> obtenerNoticias() {
        return noticiaRepository.findAllByOrderByFechaPublicacionesDesc();
    }

    @Override
    public Noticia obtenerNoticiaDetalles(Long id) {
        return noticiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Noticia no encontrada"));
    }

    @Override
    public List<Noticia> obtenerNoticiasRecomendadas() {
        return noticiaRepository.findAllByEsRecomendadaTrueOrderByFechaPublicacionesDesc();
    }

}
