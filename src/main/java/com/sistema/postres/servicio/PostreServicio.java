package com.sistema.postres.servicio;

import com.sistema.postres.modelo.Postre;
import com.sistema.postres.repositorios.PostreRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostreServicio {

    @Autowired
    private PostreRepositorio postreRepositorio;

    public List<Postre> listAll() {
        return postreRepositorio.findAll();
    }

    public Postre get(Integer id) {
        return postreRepositorio.findById(id).get();
    }

    public Postre save(Postre postre) {
        return postreRepositorio.save(postre);
    }

    public void delete(Integer id) {
        postreRepositorio.deleteById(id);
    }

}
