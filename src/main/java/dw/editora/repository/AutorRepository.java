package dw.editora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import dw.editora.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{

    boolean existsByEmail(String email);

    List<Autor> findByNomeContaining(String nome);
}
