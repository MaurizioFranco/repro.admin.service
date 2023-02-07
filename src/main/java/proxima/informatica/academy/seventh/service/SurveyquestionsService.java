package proxima.informatica.academy.seventh.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import centauri.academy.proxima.cerepro.entity.EntityInterface;
import centauri.academy.proxima.cerepro.entity.SurveysQuestions;
import centauri.academy.proxima.cerepro.repository.SurveyQuestionsRepository;

/**
 * 
 * @author Giacomo Della Luna
 *
 */
public class SurveyquestionsService {

	private final static Logger logger = LoggerFactory.getLogger(SurveyquestionsService.class);
	SurveyQuestionsRepository surveyQuestionsRepo = null;

	private SurveyquestionsService() {
		surveyQuestionsRepo = new SurveyQuestionsRepository();
	}

	private static SurveyquestionsService instance;

	public static SurveyquestionsService getInstance() {
		if (instance == null) {
			instance = new SurveyquestionsService();
		}
		return instance;
	}

	public SurveysQuestions insert(SurveysQuestions item) {
		logger.debug("insert - START - item: " + item);
		long insertedId = surveyQuestionsRepo.create(item);
		logger.debug("insert - DEBUG - insertedId: " + insertedId);
		SurveysQuestions itemToReturn = selectById(insertedId);
		return itemToReturn;
	}

	public SurveysQuestions selectById(long id) {
		SurveysQuestions sqRetrived = new SurveysQuestions();
		sqRetrived = surveyQuestionsRepo.findById(id);

		return sqRetrived;
	}

	public List<EntityInterface> getAllSurveyquestions() {
		return surveyQuestionsRepo.findAll();
	}

	public boolean deleteSurveyquestion(SurveysQuestions item) {
		return surveyQuestionsRepo.delete(SurveysQuestions.class, item.getId());
	}

	public boolean deleteById(int id) {
		return surveyQuestionsRepo.delete(id);
	}

	public SurveysQuestions updateSurveyquestions(SurveysQuestions item) {
		logger.debug("update - START - item: " + item);
		boolean returnValue = surveyQuestionsRepo.update(item);
		logger.debug("update - DEBUG - returnValue: " + returnValue);
		if (returnValue)
			return surveyQuestionsRepo.findById(item.getId());
		else
			return null;
	}
}
