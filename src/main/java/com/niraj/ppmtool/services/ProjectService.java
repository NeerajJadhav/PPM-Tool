package com.niraj.ppmtool.services;

import com.niraj.ppmtool.domain.Project;
import com.niraj.ppmtool.exception.ProjectIdentifierException;
import com.niraj.ppmtool.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {


    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveOrUpdateProject(Project project) {

        try {
            project.setIdentifier(project.getIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdentifierException("Project Identifier '" + project.getIdentifier().toUpperCase()
                    + "' is already used by '" + project.getName() + "'");
        }

    }

    public Project findProjectByIdentifier(String projectIdentifier) {
        String identifier = projectIdentifier.toUpperCase();
        Project project = projectRepository.findProjectByIdentifier(identifier);
        if (project == null) {
            throw new ProjectIdentifierException("Project Identifier '" + identifier
                    + "' does not exists");
        }
        return project;
    }


    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }
}
