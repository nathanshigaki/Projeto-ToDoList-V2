package br.com.nathanshigaki.v2.Controller.DTO;

import br.com.nathanshigaki.v2.Model.Todo;

public record TodoDTO(Long id, String nome, String descricao, boolean prioridade, boolean finalizada) {

    public TodoDTO(Todo model) {
        this(
            model.getId(), 
            model.getNome(), 
            model.getDescricao(), 
            model.getPrioridade(),
            model.getFinalizada()
        );
    }

    public Todo toModel(){
        Todo model = new Todo();
        model.setId(this.id);
        model.setNome(this.nome);
        model.setDescricao(this.descricao);
        model.setPrioridade(this.prioridade);
        model.setFinalizada(this.finalizada);
        return model;
    }

    public Long getId(){
        return id;
    }
}