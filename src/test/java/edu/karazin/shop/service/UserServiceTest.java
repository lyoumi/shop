package edu.karazin.shop.service;

import edu.karazin.shop.model.User;
import edu.karazin.shop.repository.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UsersRepository usersRepository;

    @Test
    public void shouldCreateUser(){
        when(usersRepository.save((User) anyObject())).thenReturn(new User());
        User user = new User();
        user.setId(1);
        user.setUsername("Test");
        user.setPassword("Test");
        user.setName("Test");
        user.setSurname("Test");
        user.setSurname("Test");
        boolean result = userService.createUser(user);

        assertEquals(result, true);
    }

    @Test
    public void shouldGetAllUsers(){
        when(usersRepository.findAll()).thenReturn(new ArrayList<>());
        List<User> users = userService.getAllUsers();
        assertNotNull(users);
    }

    @Test
    public void shouldGetUserByName(){
        when(usersRepository.getUserByUsername(anyString())).thenReturn(new User());
        User user = userService.getUserByName("Test");
        assertNotNull(user);
    }

    @Test
    public void shouldGetUserById(){
        when(usersRepository.findOne(anyInt())).thenReturn(new User());
        User user = userService.getUserById(42);
        assertNotNull(user);
    }

    @Test
    public void shouldUpdateUser(){
        when(usersRepository.save((User) anyObject())).thenReturn(new User());
        User user = userService.updateUser(new User());
        assertNotNull(user);
    }

    @Test
    public void shouldDeleteUserById(){
        when(usersRepository.findOne(0)).thenReturn(new User());

        userService.deleteUser(0);

        verify(usersRepository, times(1)).delete(0);
    }
}
