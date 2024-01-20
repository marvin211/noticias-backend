package com.devmarvin.noticias.controllers;

import com.devmarvin.noticias.entities.Noticia;
import com.devmarvin.noticias.services.NoticiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = {"http://localhost:4200"})
public class NoticiaController {

    private final NoticiaService noticiaService;

    //Obtener las ultimas 5 noticias
     @GetMapping(value = "/noticias")
     public ResponseEntity<List<Noticia>> getNoticias() {
         return ResponseEntity.ok(noticiaService.obtenerNoticias());
     }

     //Obtener detalles de una noticia
    @GetMapping(value = "/noticias/detalles/{id}")
    public ResponseEntity<Noticia> getNoticia(@PathVariable Long id) {
        return ResponseEntity.ok(noticiaService.obtenerNoticiaDetalles(id));
    }

    //Noticias recomendadas
    @GetMapping(value = "/noticias/recomendadas")
    public ResponseEntity<List<Noticia>> getNoticiasRecomendadas() {
        return ResponseEntity.ok(noticiaService.obtenerNoticiasRecomendadas());
    }

}
