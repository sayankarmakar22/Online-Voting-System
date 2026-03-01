package com.sayan.code.onlinevotingsystem.Repository;

import com.sayan.code.onlinevotingsystem.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
}
