package com.example.controller;

import com.example.model.SurveyModel;
import com.example.service.SurveyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SurveyController {

	private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    // Root endpoint for the application
	@GetMapping("/")
	public String home() {
		return "Welcome to the Student Management Application! Please use this link to view the application. the database has 12 entries.";
	}

	// Endpoint to get all surveys
	@GetMapping("/api/surveys")
	public List<SurveyModel> getAllSurveys() {
		return surveyService.getAllSurveys();
	}

	// Endpoint to get a survey by ID
	@GetMapping("/api/surveys/{id}")
	public ResponseEntity<SurveyModel> getSurveyById(@PathVariable Long id) {
		return ResponseEntity.ok(surveyService.getSurveyById(id));
	}

	// Endpoint to create a new survey
	@PostMapping("/api/surveys")
	public ResponseEntity<SurveyModel> createSurvey(@Valid @RequestBody SurveyModel survey) {
		return ResponseEntity.ok(surveyService.createSurvey(survey));
	}

	// Endpoint to update an existing survey
	@PutMapping("/api/surveys/{id}")
	public ResponseEntity<SurveyModel> updateSurvey(@PathVariable Long id, @RequestBody SurveyModel survey) {
		return ResponseEntity.ok(surveyService.updateSurvey(id, survey));
	}

	// Endpoint to delete a survey
	@DeleteMapping("/api/surveys/{id}")
	public ResponseEntity<Void> deleteSurvey(@PathVariable Long id) {
		surveyService.deleteSurvey(id);
		return ResponseEntity.noContent().build();
	}
}
