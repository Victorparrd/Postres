package com.sistema.postres.repositorios;

import com.sistema.postres.modelo.Postre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostreRepositorio extends JpaRepository<Postre, Integer> {

}
