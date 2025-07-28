package br.com.nathanshigaki.v2.Controller.DTO;

import java.util.List;
import static java.util.Optional.ofNullable;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import br.com.nathanshigaki.v2.Model.User;

public record UserDTO(Long id, String nome, List<TodoDTO> todos) {

    public UserDTO(User model) {
        this(
                model.getId(),
                model.getNome(),
                ofNullable(model.getTodos()).orElse(emptyList())
                        .stream()
                        .map(TodoDTO::new)
                        .collect(toList())
        );
    }

    public User toModel() {
        User model = new User();
        model.setId(this.id);
        model.setNome(this.nome);
        model.setTodos(ofNullable(this.todos).orElse(emptyList())
                .stream()
                .map(TodoDTO::toModel)
                .collect(toList()));
        return model;
    }
}
