package com.sophinia.backend.repository;

import com.sophinia.backend.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "select * from clients where email = :e", nativeQuery = true)
    Optional<Client> findClientByEmail (@Param("e") String email);

}
