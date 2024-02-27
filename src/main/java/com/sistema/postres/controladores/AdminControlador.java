package com.sistema.postres.controladores;

import com.sistema.postres.modelo.Categoria;
import com.sistema.postres.modelo.Postre;
import com.sistema.postres.repositorios.CategoriaRepositorio;
import com.sistema.postres.repositorios.PostreRepositorio;
import com.sistema.postres.servicio.AlmacenServicioImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    private PostreRepositorio postreRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    private AlmacenServicioImpl servicio;

    @GetMapping("")
    public ModelAndView verPaginaDeInicio(@PageableDefault(sort = "nombre", size = 5) Pageable pageable) {
        Page<Postre> postres = postreRepositorio.findAll(pageable);
        return new ModelAndView("admin/index").addObject("postres", postres);
    }

    @GetMapping("/postres/nuevo")
    public ModelAndView mostrarFormularioDeNuevoPostre() {
        List<Categoria> categorias = categoriaRepositorio.findAll(Sort.by("descripcion"));
        return new ModelAndView("admin/nuevo-postre")
                .addObject("postre", new Postre())
                .addObject("categorias", categorias);
    }

    @PostMapping("/postres/nuevo")
    public ModelAndView registrarPostre(@Validated Postre postre, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || postre.getImagen().isEmpty()) {
            if (postre.getImagen().isEmpty()) {
                bindingResult.rejectValue("imagen", "MultipartNotEmpty");
            }
            List<Categoria> categorias = categoriaRepositorio.findAll(Sort.by("descripcion"));
            return new ModelAndView("admin/nuevo-postre")
                    .addObject("postre", postre)
                    .addObject("categorias", categorias);
        }
        String rutaImagen = servicio.almacenarArchivo(postre.getImagen());
        postre.setRutaImagen(rutaImagen);
        postreRepositorio.save(postre);
        return new ModelAndView("redirect:/admin");
    }

    @GetMapping("/postres/{id}/editar")
    public ModelAndView mostrarFormularioDeEditarPostre(@PathVariable Integer id) {
        Postre postre = postreRepositorio.getOne(id);
        List<Categoria> categorias = categoriaRepositorio.findAll(Sort.by("descripcion"));
        return new ModelAndView("admin/editar-postre")
                .addObject("postre", postre)
                .addObject("categorias", categorias);
    }

    @PostMapping("/postres/{id}/editar")
    public ModelAndView actualizarPostre(@PathVariable Integer id, @Validated Postre postre, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<Categoria> categorias = categoriaRepositorio.findAll(Sort.by("descripcion"));
            return new ModelAndView("admin/editar-postre")
                    .addObject("postre", postre)
                    .addObject("categorias", categorias);
        }
        Postre postreDB = postreRepositorio.getOne(id);
        postreDB.setNombre(postre.getNombre());
        postreDB.setDescripcion(postre.getDescripcion());
        postreDB.setCategorias(postre.getCategorias());
        postreDB.setPrecio(postre.getPrecio());

        if (!postre.getImagen().isEmpty()) {
            servicio.eliminarArchivo(postreDB.getRutaImagen());
            String rutaImagen = servicio.almacenarArchivo(postre.getImagen());
            postreDB.setRutaImagen(rutaImagen);
        }

        postreRepositorio.save(postreDB);
        return new ModelAndView("redirect:/admin");
    }

    @PostMapping("/postres/{id}/eliminar")
    public String eliminarPostre(@PathVariable Integer id) {
        Postre postre = postreRepositorio.getOne(id);
        postreRepositorio.delete(postre);
        servicio.eliminarArchivo(postre.getRutaImagen());

        return "redirect:/admin";
    }
}
