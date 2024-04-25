package com.example.vroom.Vroom.Repository;

import com.example.vroom.Vroom.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {


    Admin findByEmail(String email);

    List<Admin> findByLastNameAndFirstName(String lastName, String firstName);

    List<Admin> findByOfficeId(Long id);




}
