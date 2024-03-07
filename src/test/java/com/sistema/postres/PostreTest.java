package com.sistema.postres;

import com.sistema.postres.modelo.Categoria;
import com.sistema.postres.modelo.Postre;
import com.sistema.postres.repositorios.PostreRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PostreTest {

    @Mock
    private PostreRepositorio postreRepositorio;

    @InjectMocks
    private Postre postre;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        postre = new Postre(1, "Postre", "Descripción", 10.0f, "imagen.jpg", Arrays.asList(new Categoria("Categoria")),
                null);
    }

    @Test
    public void testInsertPostre() {
        when(postreRepositorio.save(postre)).thenReturn(postre);
        Postre postreGuardado = postreRepositorio.save(postre);
        assertEquals(postre, postreGuardado);
    }

    @Test
    public void testUpdatePostre() {
        when(postreRepositorio.findById(1)).thenReturn(Optional.of(postre));
        postre.setDescripcion("Nueva descripción");
        when(postreRepositorio.save(postre)).thenReturn(postre);
        Postre postreActualizado = postreRepositorio.save(postre);
        assertEquals("Nueva descripción", postreActualizado.getDescripcion());
    }

    @Test
    public void testDeletePostre() {
        doNothing().when(postreRepositorio).deleteById(1);
        postreRepositorio.deleteById(1);
        verify(postreRepositorio, times(1)).deleteById(1);
    }
}