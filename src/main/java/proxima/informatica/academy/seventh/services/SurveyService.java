package proxima.informatica.academy.seventh.services;


import java.util.List;

import centauri.academy.proxima.cerepro.entity.EntityInterface;
import centauri.academy.proxima.cerepro.entity.Surveys;
import centauri.academy.proxima.cerepro.repository.SurveysRepository;

public class SurveyService {

	SurveysRepository surveyRepository = null ;
	
	private SurveyService() {
		surveyRepository = new SurveysRepository();
	}
	
	private static SurveyService instance;
	
	public static SurveyService getInstance() {
		if(instance == null) {
			instance = new SurveyService();
		}
		return instance;
	}
	
	public boolean insert(Surveys survey) {
		boolean response = false;
		
		if (surveyRepository.create(survey) > 0) {
			response = true;
		}
		return response;
	}
	
	public List<EntityInterface> getAllSurveys() {
		return surveyRepository.findAll();
	}
	
	public boolean deleteSurvey(int id) {
		boolean response = false;
		if (surveyRepository.delete(id)) {
			response = true;
		}
		return response;
	}
	
	public Surveys selectById(int id) {
		Surveys surveyRetrieved = new Surveys();
		surveyRetrieved = (Surveys)surveyRepository.findById((long)id);
		return surveyRetrieved;
	}
	
	public boolean updateSurvey(Surveys survey) {
		return surveyRepository.update(survey);
	}
	
	public boolean deleteById(int id) {
		return surveyRepository.delete(id) ;
	}
}