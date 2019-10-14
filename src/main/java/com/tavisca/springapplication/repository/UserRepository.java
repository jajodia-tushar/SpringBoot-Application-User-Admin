package com.tavisca.springapplication.repository;

import com.tavisca.springapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "SELECT * FROM user WHERE username = ?1", nativeQuery = true)
    public User findByUsername(String username);

    @Query(value = "SElECT role FROM user WHERE username = ?1",nativeQuery = true)
    public String getRoleOfUser(String username);
}
