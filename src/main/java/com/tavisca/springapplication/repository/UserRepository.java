package com.tavisca.springapplication.repository;

import com.tavisca.springapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
