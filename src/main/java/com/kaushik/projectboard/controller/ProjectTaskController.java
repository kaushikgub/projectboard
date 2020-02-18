package com.kaushik.projectboard.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaushik.projectboard.entity.ProjectTask;
import com.kaushik.projectboard.services.ProjectTaskService;

@RestController
@RequestMapping("api/board")
@CrossOrigin
public class ProjectTaskController {

	@Autowired
	private ProjectTaskService projectTaskService;
	
	@PostMapping("")
	public ResponseEntity<?> addPostBoard(@Valid @RequestBody ProjectTask projectTask, BindingResult result){
		
		if(result.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error: result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
		
		ProjectTask newPt = projectTaskService.saveOrUpdate(projectTask);
		return new ResponseEntity<ProjectTask>(newPt, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public Iterable<ProjectTask> getAllProjectTask(){
		return projectTaskService.findAll();
	}
	
	@GetMapping("/get/{id}")
	public Optional<ProjectTask> getById(@PathVariable Long id){
		Optional<ProjectTask> projectTask = projectTaskService.findById(id);
		return projectTask;
	}
	
	@GetMapping("/delete/{id}")
	public String delete (@PathVariable Long id){
		projectTaskService.delete(id);
		return "deleted";
	}
}
