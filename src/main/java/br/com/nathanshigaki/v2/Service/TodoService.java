package br.com.nathanshigaki.v2.Service;

import java.util.List;

import br.com.nathanshigaki.v2.Model.Todo;

public interface TodoService {

    Todo findById(long id);

    List<Todo> listByUser(Long userId);

    Todo createTodoByUser(Long userId, Todo todo);

    Todo updateTodoByUser(Long id, Todo novosDados);

    List<Todo> delete(long userId, long id);
}
