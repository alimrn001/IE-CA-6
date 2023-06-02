package com.baloot.baloot.Repository.User;

import com.baloot.baloot.models.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User getUserByUsername(String username);

    User getUserByEmail(String email);

}
