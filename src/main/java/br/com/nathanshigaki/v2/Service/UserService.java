package br.com.nathanshigaki.v2.Service;

import br.com.nathanshigaki.v2.Model.User;

public interface UserService {

    User findById(Long id);

    User create(User userToCreate);

    User update(Long id, User dadosAtualizados);

    void delete(long id);
}
