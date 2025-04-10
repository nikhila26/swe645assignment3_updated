package com.example.service;

import java.util.List;

import com.example.model.SurveyModel;

public interface SurveyService {
	List<SurveyModel> getAllSurveys();
	SurveyModel getSurveyById(Long id);
	SurveyModel createSurvey(SurveyModel student);
	SurveyModel updateSurvey(Long id, SurveyModel student);
	void deleteSurvey(Long id);
}

