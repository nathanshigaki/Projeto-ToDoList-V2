package br.com.nathanshigaki.v2.Controller.DTO;

import br.com.nathanshigaki.v2.Model.Todo;

public record TodoCreateDTO(String nome, String descricao, boolean prioridade, boolean finalizada, Long userId) {

    public Todo toCreateModel() {
        Todo model = new Todo();
        model.setNome(nome);
        model.setDescricao(descricao);
        model.setPrioridade(prioridade);
        model.setFinalizada(finalizada);
        return model;
    }
}
