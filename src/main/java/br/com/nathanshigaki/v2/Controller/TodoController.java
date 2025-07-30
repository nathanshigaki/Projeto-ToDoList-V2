package br.com.nathanshigaki.v2.Controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nathanshigaki.v2.Controller.DTO.TodoDTO;
import br.com.nathanshigaki.v2.Service.TodoService;
import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
@RequestMapping("/todos")
public record TodoController(TodoService todoService) {

    @GetMapping("{id}")
    @Operation(summary ="Get todo by ID", description = "Retrieve a specific todo based on its ID")
    public ResponseEntity<TodoDTO> findById(@PathVariable Long id){
        var todo = todoService.findById(id);
        return ResponseEntity.ok(new TodoDTO(todo));
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "Get all todos by user ID", description = "Retrieve all todos based on user ID")
    public ResponseEntity<List<TodoDTO>> listByUser(@PathVariable("id") long userId){
        var todo = todoService.listByUser(userId);
        var todosDTO = todo.stream().map(TodoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(todosDTO);
    }

    @PostMapping("/user/{id}")
    @Operation(summary = "Create a new todo", description = "Create a new Todo and return the created todo's data")
    public ResponseEntity<TodoDTO> createTodoByUser(@PathVariable("id") long userId, @RequestBody TodoDTO todoDTO){
        var todo = todoService.createTodoByUser(userId, todoDTO.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(todo.getId())
                .toUri();
        return ResponseEntity.created(location).body(new TodoDTO(todo));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a todo", description = "Update the data of an existing todo based on the user Id")
    public ResponseEntity<TodoDTO> update(@PathVariable long id, @RequestBody TodoDTO todoDTO){
        var todo = todoService.updateTodoByUser(id, todoDTO.toModel());
        return ResponseEntity.ok(new TodoDTO(todo));
    }

    @DeleteMapping("/user/{userId}/todo/{id}")
    @Operation(summary = "Delete a todo", description = "Delete an existing todo based on the user ID")
    public ResponseEntity<TodoDTO> delete(@PathVariable long userId, @PathVariable long id){
        todoService.delete(userId, id);
        return ResponseEntity.noContent().build();
    }
}
