package com.example.vroom.Vroom.Repository;

import com.example.vroom.Vroom.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
    List<Client> findByLastNameAndFirstName(String lastName, String firstName);

}
