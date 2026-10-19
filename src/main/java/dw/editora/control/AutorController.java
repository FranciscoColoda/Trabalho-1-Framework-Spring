package dw.editora.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dw.editora.entity.Autor;
import dw.editora.model.Artigo;
import dw.editora.service.AutorService;
import dw.editora.service.ArtigoService;

@RestController
@RequestMapping("/editora/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @Autowired
    private ArtigoService artigoService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Autor autor) {
        try {
            Autor novoAutor = autorService.salvar(autor);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoAutor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Autor>> listarTodos(@RequestParam(required = false) String nome) {
    List<Autor> autores = autorService.listar(nome);
    return ResponseEntity.ok(autores);
}
    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarPorId(@PathVariable Long id) {
        return autorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (autorService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/artigos")
    public ResponseEntity<List<Artigo>> buscarArtigosPorAutor(@PathVariable Long id) {
        if (autorService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna 404 se o autor não existir
        }
        List<Artigo> artigos = artigoService.buscarPorAutor(id);
        return ResponseEntity.ok(artigos);
    }
}
