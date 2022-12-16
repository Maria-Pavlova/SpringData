package com.example.workshopnextleveltechnologiesmvc.repositories;

import com.example.workshopnextleveltechnologiesmvc.data.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    Optional<Project> findByName(String name);

    List<Project> findAllByIsFinishedIsTrue();
}
