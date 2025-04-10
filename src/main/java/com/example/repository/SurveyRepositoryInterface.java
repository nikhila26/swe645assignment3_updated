package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.SurveyModel;

public interface SurveyRepositoryInterface extends JpaRepository<SurveyModel, Long> {

}
