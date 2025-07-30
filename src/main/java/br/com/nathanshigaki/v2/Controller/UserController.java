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

import br.com.nathanshigaki.v2.Controller.DTO.UserCreateDTO;
import br.com.nathanshigaki.v2.Controller.DTO.UserDTO;
import br.com.nathanshigaki.v2.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid user data provided")
    })
    public ResponseEntity<UserDTO> create(@RequestBody UserCreateDTO userCreateDTO) {
        var user = userService.create(userCreateDTO.toCreateModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();        
        return ResponseEntity.created(location).body(new UserDTO(user));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a user", description = "Update the data of an existing user based on its ID")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserCreateDTO userCreateDTO){
        var user = userService.update(id, userCreateDTO.toCreateModel());
        return ResponseEntity.ok(new UserDTO(user));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user", description = "Delete an existing user based on its ID")
    public ResponseEntity<UserDTO> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
