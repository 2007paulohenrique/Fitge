package com.fitge.api.repository.main;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fitge.api.model.main.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailHash(String emailHash);

    Optional<User> findByEmailHashOrNickname(String emailHash, String nickname);
    
    List<User> findByName(String name);
    
    List<User> findByNameOrNickname(String name, String nickname);

}
