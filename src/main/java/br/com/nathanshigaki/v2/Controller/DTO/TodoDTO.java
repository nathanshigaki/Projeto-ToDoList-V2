package br.com.nathanshigaki.v2.Controller.DTO;

import br.com.nathanshigaki.v2.Model.Todo;

public record TodoDTO(Long id, String nome, String descricao, boolean finalizada) {

    public TodoDTO(Todo model) {
        this(
            model.getId(), 
            model.getNome(), 
            model.getDescricao(), 
            model.getFinalizada()
        );
    }

    public Todo toModel(){
        Todo model = new Todo();
        model.setId(this.id);
        model.setNome(this.nome);
        model.setDescricao(this.descricao);
        model.setFinalizada(this.finalizada);
        return model;
    }
}
