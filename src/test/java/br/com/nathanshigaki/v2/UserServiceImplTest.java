package br.com.nathanshigaki.v2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.nathanshigaki.v2.Model.User;
import br.com.nathanshigaki.v2.Repository.UserRepository;
import br.com.nathanshigaki.v2.Service.Impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	private User validUser;

	@BeforeEach
	void setUp(){
		validUser = new User();
        validUser.setId(1L);
        validUser.setNome("Teste");
        validUser.setEmail("teste@email.com");
	}

	@Test
	void findById_Success() {
		when(userRepository.findById(1L)).thenReturn(Optional.of(validUser));
		User foundUser = userServiceImpl.findById(1L);

		assertNotNull(foundUser);
        assertEquals(1L, foundUser.getId());

		verify(userRepository, times(1)).findById(1L);
	}

	@Test
	void findById_ThrowException(){
		when(userRepository.findById(2L)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> userServiceImpl.findById(2L));
        
        verify(userRepository, times(1)).findById(2L);
	}

	@Test
	void findAll_Success(){
		List<User> userList = Arrays.asList(validUser, new User());
        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userServiceImpl.findAll();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
	}

	@Test
    void createUser_Success() {
        User userToCreate = new User();
        userToCreate.setNome("New User");
        when(userRepository.save(any(User.class))).thenReturn(validUser);

        User createdUser = userServiceImpl.create(userToCreate);

        assertNotNull(createdUser);
        assertEquals(validUser.getId(), createdUser.getId());
        verify(userRepository, times(1)).save(userToCreate);
    }

	@Test
    void updateUser_Success() {
        User existing = new User();
        existing.setId(1L);
        User updatedData = new User();
        updatedData.setId(1L);
        updatedData.setNome("Updated Name");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(userRepository.save(any(User.class))).thenReturn(updatedData);

        User result = userServiceImpl.update(1L, updatedData);

        assertEquals("Updated Name", result.getNome());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(existing); 
	}

	@Test
    void updateUser_ThrowException() {
        User updatedData = new User();
        updatedData.setId(1L);
        
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

    	assertThrows(NoSuchElementException.class, () -> userServiceImpl.update(1L, updatedData));

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, never()).save(any(User.class));
    }

	@Test
    void deleteUser_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(validUser));
        doNothing().when(userRepository).deleteById(1L); 

        userServiceImpl.delete(1L);

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

	@Test
    void deleteUser_Failed() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userServiceImpl.delete(2L));

        verify(userRepository, times(1)).findById(2L);
        verify(userRepository, never()).deleteById(anyLong());
    }
}
