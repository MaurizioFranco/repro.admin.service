package proxima.informatica.academy.seventh.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import centauri.academy.proxima.cerepro.entity.EntityInterface;
import centauri.academy.proxima.cerepro.entity.Surveys;
import centauri.academy.proxima.cerepro.repository.SurveysRepository;

public class SurveyService {

	private final static Logger logger = LoggerFactory.getLogger(SurveyService.class);
	
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
	
	public Surveys insert(Surveys item) {
		logger.debug("insert - START - item: " + item);
        long insertedId = surveyRepository.create(item) ;
        logger.debug("insert - DEBUG - insertedId: " + insertedId);
        Surveys itemToReturn = selectById(insertedId);
        return itemToReturn ;
	}
	
	public List<EntityInterface> getAllSurveys() {
		return surveyRepository.findAll();
	}
	
	public boolean deleteSurvey(long id) {
		boolean response = false;
		if (surveyRepository.delete(id)) {
			response = true;
		}
		return response;
	}
	
	public Surveys selectById(long id) {
		Surveys surveyRetrieved = new Surveys();
		surveyRetrieved = (Surveys)surveyRepository.findById((long)id);
		return surveyRetrieved;
	}
	
	public Surveys update(Surveys item) {
		logger.debug("update - START - item: " + item);
        boolean returnValue = surveyRepository.update(item) ;
        logger.debug("update - DEBUG - updated result: " + returnValue);
        if (returnValue) {
        	return (Surveys)surveyRepository.findById(item.getId());
        }
        return null ;
	}
	
	public boolean deleteById(long id) {
		return surveyRepository.delete(id) ;
	}
}