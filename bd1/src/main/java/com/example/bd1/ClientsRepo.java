package com.example.bd1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface ClientsRepo extends JpaRepository<Clients, Integer> {
    List<Clients> findAllBylastName(String lastName);
}