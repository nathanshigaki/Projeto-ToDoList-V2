package br.com.nathanshigaki.v2.Controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nathanshigaki.v2.Controller.DTO.UserDTO;
import br.com.nathanshigaki.v2.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("/users")
public record UserController(UserService userService) {

    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieve a list of all registered users")
    public ResponseEntity<List<UserDTO>> findAll(){
        var users = userService.findAll();
        var usersDto = users.stream().map(UserDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(usersDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieve a specific user based on its ID")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        var user = userService.findById(id);
        return ResponseEntity.ok(new UserDTO(user));
    }
    
    @PostMapping
    @Operation(summary = "Create a new user", description = "Create a new user and return the created user's data")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        var user = userService.create(userDTO.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();        
        return ResponseEntity.created(location).body(new UserDTO(user));
    }

    
}
