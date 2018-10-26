package eu.glowacki.utp.assignment10.repositories.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import eu.glowacki.utp.assignment10.dtos.GroupDTO;
import eu.glowacki.utp.assignment10.dtos.UserDTO;
import eu.glowacki.utp.assignment10.repositories.IUserRepository;
import eu.glowacki.utp.assignment10.repositories.UserRepository;

public final class UserRepositoryTest extends RepositoryTestBase<UserDTO, IUserRepository> {
	private UserRepository repository;
    
    @Test
    public void findById() {
        GroupDTO group = new GroupDTO(1, "alfa", "do smth");
        UserDTO user = new UserDTO(1, "joker", "batman");
        user.addGroup(group);
        repository.add(user);
        UserDTO userActual = repository.findById(1);
        Assert.assertEquals(user, userActual);
    }

    @Test
    public void findByName() {
        GroupDTO group1 = new GroupDTO(1, "alfa", "do smth");
        UserDTO user1 = new UserDTO(1, "joker", "batman");
        user1.addGroup(group1);
        GroupDTO group2 = new GroupDTO(2, "alfa", "do smth else");
        UserDTO user2 = new UserDTO(2, "joker", "robin");
        user2.addGroup(group2);
        List<UserDTO> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        
        repository.add(user1);
        repository.add(user2);
        List<UserDTO> usersActual = repository.findByName("joker");
        
        Assert.assertEquals(users, usersActual);
    }
    
    @Test
    public void add() {
        GroupDTO group = new GroupDTO(1, "alfa", "do smth");
        UserDTO user = new UserDTO(1, "joker", "batman");
        user.addGroup(group);
        repository.add(user);
        UserDTO userActual = repository.findById(1);
        Assert.assertEquals(user, userActual);
    }

    @Test
    public void update() {
        GroupDTO group = new GroupDTO(1, "alfa", "do smth");
        UserDTO user = new UserDTO(1, "joker", "batman");
        user.addGroup(group);
        repository.add(user);
        user.setLogin("batman");
        repository.update(user);
        UserDTO userExpected = new UserDTO(1, "batman", "batman");
        UserDTO userActual = repository.findById(1);
        Assert.assertEquals(userExpected, userActual);
    }

    @Test
    public void delete() {
        GroupDTO group = new GroupDTO(1, "alfa", "do smth");
        UserDTO user = new UserDTO(1, "joker", "batman");
        user.addGroup(group);
        repository.add(user);
        repository.delete(user);
        int countActual = repository.rowCount();
        Assert.assertEquals(0, countActual);
    }

    @Override
    protected IUserRepository Create() {
        if(repository == null)
            repository = new UserRepository();
        return repository;
    }
}
