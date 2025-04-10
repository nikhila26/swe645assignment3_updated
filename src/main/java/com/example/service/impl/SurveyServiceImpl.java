package com.example.service.impl;

import com.example.exception.ResourceNotFoundException;
import com.example.model.SurveyModel;
import com.example.repository.SurveyRepositoryInterface;
import com.example.service.SurveyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {
    private final SurveyRepositoryInterface surveyRepository;

    public SurveyServiceImpl(SurveyRepositoryInterface surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Override
    public List<SurveyModel> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public SurveyModel getSurveyById(Long id) {
        return surveyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Survey not found"));
    }

    @Override
    public SurveyModel createSurvey(SurveyModel survey) {
        return surveyRepository.save(survey);
    }

    @Override
    public SurveyModel updateSurvey(Long id, SurveyModel survey) {
        SurveyModel existingSurvey = getSurveyById(id);
        existingSurvey.setFirstName(survey.getFirstName());
        existingSurvey.setLastName(survey.getLastName());
        existingSurvey.setEmail(survey.getEmail());
        existingSurvey.setAddress(survey.getAddress());
        existingSurvey.setCity(survey.getCity());
        existingSurvey.setState(survey.getState());
        existingSurvey.setZip(survey.getZip());
        existingSurvey.setTelephone(survey.getTelephone());
        existingSurvey.setDateOfSurvey(survey.getDateOfSurvey());
        existingSurvey.setRecommendation(survey.getRecommendation());
        existingSurvey.setInterest(survey.getInterest());
        existingSurvey.setLikings(survey.getLikings());
        return surveyRepository.save(existingSurvey);
    }

    @Override
    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }

}
