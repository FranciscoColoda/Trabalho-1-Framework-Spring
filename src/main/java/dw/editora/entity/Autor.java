package dw.editora.entity;

import dw.editora.model.Artigo;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Autor {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column (nullable = false, unique = true)
    private String email;

    @Lob 
    @Column(columnDefinition = "TEXT")
    private String biografia;

    @OneToMany(mappedBy = "autor")
    @JsonIgnoreProperties("autor")
    private List<Artigo> artigos = new ArrayList<>();

    public Autor(){}

    public Autor(String nome, String email, String biografia) {
        this.nome = nome;
        this.email = email;
        this.biografia = biografia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    
}
