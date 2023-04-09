package com.fundall.eWallet.repository;

import com.fundall.eWallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
     User findByEmail(String email);
}