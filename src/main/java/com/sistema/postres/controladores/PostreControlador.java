package com.sistema.postres.controladores;

import com.sistema.postres.modelo.Postre;
import com.sistema.postres.servicio.PostreServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postres")
public class PostreControlador {

    @Autowired
    private PostreServicio postreServicio;

    @GetMapping
    public ResponseEntity<List<Postre>> listarPostres() {
        List<Postre> postres = postreServicio.listAll();

        if (postres.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(postres, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postre> listarPostre(@PathVariable Integer id) {
        try {
            Postre postre = postreServicio.get(id);
            return new ResponseEntity<>(postre, HttpStatus.OK);
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Postre> guardarPostre(@RequestBody Postre postre) {
        Postre postreBBDD = postreServicio.save(postre);
        return new ResponseEntity<>(postre, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Postre> editarPostre(@RequestBody Postre postre) {
        Postre postreBBDD = postreServicio.save(postre);
        return new ResponseEntity<>(postreBBDD, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPostre(@PathVariable Integer id) {
        try {
            postreServicio.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

}
