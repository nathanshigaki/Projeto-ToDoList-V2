package br.com.nathanshigaki.v2.Controller.DTO;

import br.com.nathanshigaki.v2.Model.User;

public record UserUpdateDTO(Long id, String nome, String email, String senha) {

    public UserUpdateDTO(User model) {
        this(
                model.getId(),
                model.getNome(),
                model.getEmail(),
                model.getSenha()
        );
    }

    public User toUpdateModel() {
        User model = new User();
        model.setId(this.id);
        model.setNome(this.nome);
        model.setEmail(this.email);
        model.setSenha(this.senha);
        return model;
    }

    public Long getId() {
        return id;
    }
}
