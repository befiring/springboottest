package com.befiring.repository;

import com.befiring.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    User findByNameAndPassword(String name,String password);
}
