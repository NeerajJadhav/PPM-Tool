package com.niraj.ppmtool.repositories;

import com.niraj.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findProjectByIdentifier(String projectIdentifier);

    @Override
    Iterable<Project> findAll();
}
