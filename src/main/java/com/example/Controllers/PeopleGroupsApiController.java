package com.example.Controllers;


import com.example.entities.User;
import com.example.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@RestController
public class PeopleGroupsApiController {
    @Autowired
    UserRepository users;

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public List<User> readUser() {
        return (List<User>) users.findAll();
    }
    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public void createUser(@RequestBody User user) {
        users.save(user);
    }
    @RequestMapping(path = "/user", method = RequestMethod.PUT)
    public void updateUser(@RequestBody User user) {
        users.save(user);
    }
    @RequestMapping(path = "/user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") int id) {
        users.delete(id);
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") int id) {
        return users.findOne(id);
    }


    @PostConstruct
    public void init(){
        if (users.count() == 0){
            User user = new User();
            user.setAddress("123 Testville");
            user.setEmail("me@you.com");
            user.setUsername("Mike");
            user.setSsn("333-444-5522");
            user.setWorking(true);
            users.save(user);
        }
    }
    //TODO: You can edit configurations for the database variable and set it to map to localhost.
}
