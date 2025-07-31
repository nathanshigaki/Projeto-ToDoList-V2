package br.com.nathanshigaki.v2.Controller.DTO;

import br.com.nathanshigaki.v2.Model.Todo;
import io.swagger.v3.oas.annotations.media.Schema;

public record TodoCreateDTO(String nome, String descricao, @Schema(defaultValue = "false") boolean prioridade, @Schema(defaultValue = "false") boolean finalizada, Long userId) {

    public Todo toCreateModel() {
        Todo model = new Todo();
        model.setNome(nome);
        model.setDescricao(descricao);
        model.setPrioridade(prioridade);
        model.setFinalizada(finalizada);
        return model;
    }
}
