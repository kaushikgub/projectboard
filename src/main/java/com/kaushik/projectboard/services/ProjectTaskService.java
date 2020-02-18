package com.kaushik.projectboard.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaushik.projectboard.entity.ProjectTask;
import com.kaushik.projectboard.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	public ProjectTask saveOrUpdate(ProjectTask projectTask) {
		if(projectTask.getStatus() == null || projectTask.getStatus() == "") {
			projectTask.setStatus("TO-DO");
		}
		return projectTaskRepository.save(projectTask);
	}
	
	public Iterable<ProjectTask> findAll(){
		return projectTaskRepository.findAll();
	}
	
	public Optional<ProjectTask> findById(Long id) {
		return projectTaskRepository.findById(id);
	}
	
	public void delete(Long id) {
		projectTaskRepository.deleteById(id);
	}
}
