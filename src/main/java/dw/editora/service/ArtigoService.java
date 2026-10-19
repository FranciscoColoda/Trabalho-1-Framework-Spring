package dw.editora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dw.editora.entity.Autor;
import dw.editora.model.Artigo;
import dw.editora.repository.ArtigoRepository;
import dw.editora.repository.AutorRepository;

@Service
public class ArtigoService {

    @Autowired
    private ArtigoRepository artigoRepository;

    @Autowired
    private AutorRepository autorRepository;

    public Artigo salvar(Artigo artigo) {
        if (artigo.getAutor() == null || artigo.getAutor().getId() == null) {
            throw new IllegalArgumentException("Coloque o ID do autor");
        }

        Long autorId = artigo.getAutor().getId();

        Autor autor = autorRepository.findById(autorId)
                .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado"));

        artigo.setAutor(autor);

        return artigoRepository.save(artigo);
    }

    public List<Artigo> listar(String titulo) {
        if (titulo != null && !titulo.isBlank()) {
            return artigoRepository.findByTituloContaining(titulo);
        }
        return artigoRepository.findAll();
    }

    public List<Artigo> listarTodos() {
        return artigoRepository.findAll();
    }

    public List<Artigo> buscarPorAutor(Long autorId) {
        return artigoRepository.findByAutorId(autorId);
    }
}