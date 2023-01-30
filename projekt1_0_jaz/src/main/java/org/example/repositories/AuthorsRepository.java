package org.example.repositories;

import org.example.model.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AuthorsRepository extends JpaRepository<Authors, Long> {

}
