package br.com.nathanshigaki.v2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.nathanshigaki.v2.Model.Todo;
import br.com.nathanshigaki.v2.Model.User;
import br.com.nathanshigaki.v2.Repository.TodoRepository;
import br.com.nathanshigaki.v2.Service.Impl.TodoServiceImpl;
import br.com.nathanshigaki.v2.Service.Impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class TodoServiceImplTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private UserServiceImpl userServiceImpl;

    @InjectMocks
    private TodoServiceImpl todoService;

    private User validUser;
    private Todo validTodo;
    private Todo validTodo2;
    private User otherUser;

    @BeforeEach
    void setUp() {
        validUser = new User();
        validUser.setId(1L);

        otherUser = new User();
        otherUser.setId(2L);

        validTodo = new Todo();
        validTodo.setId(10L);
        validTodo.setNome("Comprar Leite");
        validTodo.setUser(validUser);
        validTodo.setFinalizada(false);
        validTodo.setPrioridade(true);

        validTodo2 = new Todo();
        validTodo2.setId(11L);
        validTodo2.setNome("Pagar Contas");
        validTodo2.setUser(validUser);
    }

    @Test
    void findById_Success() {
        when(todoRepository.findById(10L)).thenReturn(Optional.of(validTodo));
        
        Todo result = todoService.findById(10L);
        
        assertNotNull(result);
        assertEquals(10L, result.getId());
        verify(todoRepository, times(1)).findById(10L);
    }

    @Test
    void listByUser_Success() {
        List<Todo> todos = Arrays.asList(validTodo, validTodo2);
        when(todoRepository.findByUserId(1L)).thenReturn(todos);

        List<Todo> result = todoService.listByUser(1L);

        assertEquals(2, result.size());
        verify(todoRepository, times(1)).findByUserId(1L);
    }

    @Test
    void createTodoByUser_Success() {
        Todo newTodo = new Todo();
        newTodo.setNome("New Task");
        
        when(userServiceImpl.findById(1L)).thenReturn(validUser);
        when(todoRepository.save(any(Todo.class))).thenAnswer(i -> {
            Todo todoToSave = i.getArgument(0);
            todoToSave.setId(20L);
            return todoToSave;
        });

        Todo createdTodo = todoService.createTodoByUser(1L, newTodo);

        assertEquals(validUser, createdTodo.getUser());
        verify(userServiceImpl, times(1)).findById(1L);
        verify(todoRepository, times(1)).save(newTodo);
    }

    @Test
    void updateTodoByUser_Success() {
        Todo novosDados = new Todo();
        novosDados.setNome("Updated Task");
        novosDados.setFinalizada(true);
        novosDados.setPrioridade(false);

        when(todoRepository.findById(10L)).thenReturn(Optional.of(validTodo));
        when(todoRepository.save(any(Todo.class))).thenReturn(validTodo);

        Todo updated = todoService.updateTodoByUser(10L, novosDados);

        assertEquals("Updated Task", updated.getNome());
        assertTrue(updated.getFinalizada());
        assertFalse(updated.getPrioridade());
        
        verify(todoRepository, times(1)).save(validTodo);
    }
    
    @Test
    void delete_Success() {
        when(todoRepository.findById(10L)).thenReturn(Optional.of(validTodo));
        when(todoRepository.findByUserId(1L)).thenReturn(Arrays.asList(validTodo2));

        todoService.delete(1L, 10L);

        verify(todoRepository, times(1)).findById(10L);
        verify(todoRepository, times(1)).deleteById(10L);
    }

    @Test
    void delete_ThrowException() {
        when(todoRepository.findById(10L)).thenReturn(Optional.of(validTodo));

        assertThrows(RuntimeException.class, () -> todoService.delete(2L, 10L));
        
        verify(todoRepository, never()).deleteById(anyLong());
    }
}
