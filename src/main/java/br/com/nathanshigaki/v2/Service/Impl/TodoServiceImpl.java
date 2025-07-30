package br.com.nathanshigaki.v2.Service.Impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.com.nathanshigaki.v2.Model.Todo;
import br.com.nathanshigaki.v2.Model.User;
import br.com.nathanshigaki.v2.Repository.TodoRepository;
import br.com.nathanshigaki.v2.Service.TodoService;

@Service
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;
    private final UserServiceImpl userServiceImpl;

    public TodoServiceImpl(TodoRepository todoRepository, UserServiceImpl userServiceImpl) {
        this.todoRepository = todoRepository;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public Todo findById(long id) {
        return todoRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Todo> listByUser(Long userId) {
        return todoRepository.findByUserId(userId);
    }

    @Override
    public Todo createTodoByUser(Long userId, Todo todo) {
        User user = userServiceImpl.findById(userId);
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    @Override
    public Todo updateTodoByUser(Long id, Todo novosDados) {
        Todo existente = findById(id);

        existente.setNome(novosDados.getNome());
        existente.setDescricao(novosDados.getDescricao());
        existente.setPrioridade(novosDados.getPrioridade());
        existente.setFinalizada(novosDados.getFinalizada());

        return todoRepository.save(existente);
    }

    @Override
    public List<Todo> delete(long userId, long id) {
        Todo todo = findById(id);
        
        if (todo.getUser().getId() != userId) {
            throw new RuntimeException("Essa tarefa não pertence ao usuário informado.");
        }

        todoRepository.deleteById(id);
        return listByUser(userId);
    }
}
