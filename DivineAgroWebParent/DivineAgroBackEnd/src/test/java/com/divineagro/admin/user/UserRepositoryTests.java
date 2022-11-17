package com.divineagro.admin.user;

import com.divineagro.common.entity.Role;
import com.divineagro.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import javax.sound.midi.Soundbank;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        Role roleAdmin = entityManager.find(Role.class, 1);
        User  userEmeka = new User ("emeka@divineagro.com", "emeka2021", "Chief", "Emeka");
        userEmeka.addRole(roleAdmin);

        User savedUser = repo.save(userEmeka);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateNewUserWithTwoRoles() {
        User userChuks = new User("chuks@gmail.com", "chuks2021", "Don", "Chuks");
        Role roleEditor = new Role(3);
        Role roleAssistant = new Role(5);

        userChuks.addRole(roleEditor);
        userChuks.addRole(roleAssistant);

        User savedUser = repo.save(userChuks);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllUsers() {
        Iterable<User> listUsers = repo.findAll();
        listUsers.forEach(System.out::println);
    }

    @Test
    public void testGetUserById() {
        User userEmeka = repo.findById(1).get();
        System.out.println(userEmeka);
        assertThat(userEmeka).isNotNull();
    }

    @Test
    public void testUpdateUserDetails() {
        User userEmeka = repo.findById(1).get();
        userEmeka.setEnabled(true);
        userEmeka.setEmail("emekajavprogrammer@gmail.com");

        repo.save(userEmeka);
    }

    @Test
    public void testUpdateUserRoles() {
        User userChuks = repo.findById(2).get();
        Role roleEditor = new Role(3);
        Role roleSalesPerson = new Role(2);

        userChuks.getRoles().remove(roleEditor);
        userChuks.getRoles().remove(roleSalesPerson);

        repo.save(userChuks);
    }

    @Test
    public void testDeleteUser(){
        Integer userId = 2;
        repo.deleteById(userId);
    }

    @Test
    public void testGetUserByEmail() {
        String email = "chuks@gmail.com";
        User user = repo.getUserByEmail(email);

        assertThat(user).isNotNull();
    }
}
