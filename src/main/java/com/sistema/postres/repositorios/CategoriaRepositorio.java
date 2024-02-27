package com.sistema.postres.repositorios;

import com.sistema.postres.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Integer> {

}
