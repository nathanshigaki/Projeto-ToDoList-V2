package br.com.nathanshigaki.v2.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    private String descricao;
    private boolean prioridade;
    private boolean finalizada;
    private LocalDate dataTermino;

    @ManyToOne 
    private User user;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public boolean getPrioridade() {
        return prioridade;
    }
    public void setPrioridade(boolean prioridade) {
        this.prioridade = prioridade;
    }
    public boolean getFinalizada() {
        return finalizada;
    }
    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }
    public LocalDate getDataTermino() {
        return dataTermino;
    }
    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}

