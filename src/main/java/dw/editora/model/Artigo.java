package dw.editora.model;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import dw.editora.entity.Autor;

@Entity 
@Table(name = "artigo")
public class Artigo {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80, nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String resumo;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean publicado = true;

    @ManyToOne 
    @JoinColumn(name = "autor_id", nullable = false)
    @JsonIgnoreProperties("artigos")
    private Autor autor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public Boolean isPublicado() {
        return publicado;
    }

    public void setPublicado(Boolean publicado) {
        this.publicado = publicado;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Artigo() {
    }

    public Artigo(String titulo, String resumo, Boolean publicado) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.publicado = publicado;
    }

    

    

}
