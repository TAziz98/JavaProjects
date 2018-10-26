package assignment10.repositories.test;

import org.junit.Test;

import assignment10.UnimplementedException;
import assignment10.dtos.GroupDTO;
import assignment10.dtos.UserDTO;
import assignment10.repositories.GroupRepository;
import assignment10.repositories.IGroupRepository;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class GroupRepositoryTest extends RepositoryTestBase<GroupDTO, IGroupRepository> {
    private GroupRepository repository;
    
    @Test
    public void findById() {
        GroupDTO group = new GroupDTO(1, "alfa", "do smth");
        UserDTO user = new UserDTO(1, "joker", "batman");
        group.addUser(user);
        repository.add(group);
        GroupDTO groupActual = repository.findById(1);
        Assert.assertEquals(group, groupActual);
    }

    @Test
    public void findByName() {
        GroupDTO group1 = new GroupDTO(1, "alfa", "do smth");
        UserDTO user1 = new UserDTO(1, "joker", "batman");
        group1.addUser(user1);
        GroupDTO group2 = new GroupDTO(2, "alfa", "do smth else");
        UserDTO user2 = new UserDTO(2, "nightwing", "robin");
        group2.addUser(user2);
        List<GroupDTO> groups = new ArrayList<>();
        groups.add(group1);
        groups.add(group2);
        
        repository.add(group1);
        repository.add(group2);
        List<GroupDTO> groupsActual = repository.findByName("alfa");
        
        Assert.assertEquals(groups, groupsActual);
    }
    
    @Test
    public void add() {
        GroupDTO group = new GroupDTO(1, "alfa", "do smth");
        UserDTO user = new UserDTO(1, "joker", "batman");
        group.addUser(user);
        repository.add(group);
        GroupDTO groupActual = repository.findById(1);
        Assert.assertEquals(group, groupActual);
    }

    @Test
    public void update() {
        GroupDTO group = new GroupDTO(1, "alfa", "do smth");
        UserDTO user = new UserDTO(1, "joker", "batman");
        group.addUser(user);
        repository.add(group);
        group.setName("beta");
        repository.update(group);
        GroupDTO groupExpected = new GroupDTO(1, "beta", "do smth");
        GroupDTO groupActual = repository.findById(1);
        Assert.assertEquals(groupExpected, groupActual);
    }

    @Test
    public void delete() {
        GroupDTO group = new GroupDTO(1, "alfa", "do smth");
        UserDTO user = new UserDTO(1, "joker", "batman");
        group.addUser(user);
        repository.add(group);
        repository.delete(group);
        int countActual = repository.rowCount();
        Assert.assertEquals(0, countActual);
    }

    @Override
    protected IGroupRepository Create() {
        if(repository == null)
            repository = new GroupRepository();
        return repository;
    }
}
