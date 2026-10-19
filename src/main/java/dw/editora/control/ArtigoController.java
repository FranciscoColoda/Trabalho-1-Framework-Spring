package dw.editora.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dw.editora.repository.ArtigoRepository;

import dw.editora.model.Artigo;
import dw.editora.service.ArtigoService;

@RestController 
@RequestMapping({"/editora/artigos/", "/editora/artigos"})
public class ArtigoController {

    @Autowired
    private ArtigoService artigoService;

    @Autowired 
    ArtigoRepository rep;
    
    @GetMapping
    public ResponseEntity<List<Artigo>> listarTodos(@RequestParam(required = false) String titulo) {
    List<Artigo> artigos = artigoService.listar(titulo);
    return ResponseEntity.ok(artigos);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Artigo artigo) {
        try {
            Artigo novoArtigo = artigoService.salvar(artigo);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoArtigo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
