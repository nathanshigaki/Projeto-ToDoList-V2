package br.com.nathanshigaki.v2.Controller.DTO;

import br.com.nathanshigaki.v2.Model.User;

public record UserCreateDTO(String nome, String email, String senha) {

    public UserCreateDTO(User model) {
        this(
                model.getNome(),
                model.getEmail(),
                model.getSenha()
        );
    }

    public User toCreateModel() {
        User model = new User();
        model.setNome(this.nome);
        model.setEmail(this.email);
        model.setSenha(this.senha);
        return model;
    }
}
