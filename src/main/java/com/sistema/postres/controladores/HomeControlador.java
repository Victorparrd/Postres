package com.sistema.postres.controladores;

import java.util.List;

import com.sistema.postres.modelo.Postre;
import com.sistema.postres.repositorios.PostreRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("")
public class HomeControlador {

    @Autowired
    private PostreRepositorio postreRepositorio;

    @GetMapping("")
    public ModelAndView verPaginaDeInicio() {
        List<Postre> ordenPostres = postreRepositorio.findAll(PageRequest.of(0,4,Sort.by("precio").descending())).toList();
        return new ModelAndView("index")
                .addObject("ordenPostres", ordenPostres);
    }

    @GetMapping("/postres")
    public ModelAndView listarPostres(@PageableDefault(sort = "precio",direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Postre> postres = postreRepositorio.findAll(pageable);
        return new ModelAndView("postres")
                .addObject("postres",postres);
    }

    @GetMapping("/postres/{id}")
    public ModelAndView mostrarDetallesDePostres(@PathVariable Integer id) {
        Postre postre = postreRepositorio.getOne(id);
        return new ModelAndView("postre").addObject("postre",postre);
    }
}
