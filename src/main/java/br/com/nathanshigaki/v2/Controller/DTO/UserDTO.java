package br.com.nathanshigaki.v2.Controller.DTO;

import br.com.nathanshigaki.v2.Model.User;

public record UserDTO(Long id, String nome, String email) {

    public UserDTO(User model) {
        this(
                model.getId(),
                model.getNome(),
                model.getEmail()
        );
    }

    public User toModel() {
        User model = new User();
        model.setId(this.id);
        model.setNome(this.nome);
        model.setEmail(this.email);
        return model;
    }
}
