package br.com.nathanshigaki.v2.Service.Impl;

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

}
