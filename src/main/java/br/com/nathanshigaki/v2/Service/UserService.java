    package br.com.nathanshigaki.v2.Service;

    import java.util.List;

    import br.com.nathanshigaki.v2.Model.User;

    public interface UserService {

        List<User> findAll();

        User findById(Long id);

        User create(User userToCreate);

        User update(Long id, User dadosAtualizados);

        void delete(long id);
    }
