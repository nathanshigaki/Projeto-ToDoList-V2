package br.com.nathanshigaki.v2.Service.Impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.com.nathanshigaki.v2.Model.User;
import br.com.nathanshigaki.v2.Repository.UserRepository;
import br.com.nathanshigaki.v2.Service.UserService;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {
        if(userRepository.existsById(userToCreate.getId())){
            throw new IllegalArgumentException("ID já existe.");
        }
        return userRepository.save(userToCreate);
    }

    @Override
    public User update(Long id, User userToUpdate) {
        User existente = findById(id);
        if(userToUpdate == null || id != userToUpdate.getId()){
            throw new IllegalArgumentException("Update IDs must be the same.");
        }

        existente.setNome(userToUpdate.getNome());
        existente.setEmail(userToUpdate.getEmail());
        existente.setSenha(userToUpdate.getSenha());

        return userRepository.save(existente);
    }

    @Override
    public void delete(long id) {
        User user = findById(id);
        userRepository.deleteById(user.getId());
    }
}
