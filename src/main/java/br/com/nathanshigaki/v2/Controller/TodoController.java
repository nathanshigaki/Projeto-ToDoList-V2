package br.com.nathanshigaki.v2.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nathanshigaki.v2.Controller.DTO.TodoDTO;
import br.com.nathanshigaki.v2.Service.TodoService;
import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
@RequestMapping("/todos")
public record TodoController(TodoService todoService) {

    @GetMapping
    @Operation(summary = "Retrieve a specific todo based on its ID")
    public ResponseEntity<TodoDTO> findById(@PathVariable Long id){
        var todo = todoService.findById(id);
        return ResponseEntity.ok(new TodoDTO(todo));
    }
}
