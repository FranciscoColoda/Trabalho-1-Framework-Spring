package dw.editora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dw.editora.entity.Autor;
import dw.editora.repository.AutorRepository;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> listar(String nome) {
        if (nome != null && !nome.isBlank()) {
            return autorRepository.findByNomeContaining(nome);
        }
        return autorRepository.findAll();
    }

    public Autor salvar(Autor autor) {
        if (autorRepository.existsByEmail(autor.getEmail())) {
            throw new IllegalArgumentException("Já existe um autor cadastrado com o e-mail: " + autor.getEmail());
        }
        return autorRepository.save(autor);
    }

    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    public Optional<Autor> buscarPorId(Long id) {
        return autorRepository.findById(id);
    }

    public boolean deletar(Long id) {
        if (autorRepository.existsById(id)) {
            autorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
