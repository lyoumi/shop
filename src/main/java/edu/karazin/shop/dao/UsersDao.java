package edu.karazin.shop.dao;

import edu.karazin.shop.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersDao extends CrudRepository<User, Integer>{
    User getUserByUsername(String username);
}
