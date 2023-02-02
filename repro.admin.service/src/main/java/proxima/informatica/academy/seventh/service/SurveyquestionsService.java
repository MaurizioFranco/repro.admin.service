package proxima.informatica.academy.seventh.service;

import java.util.ArrayList;
import java.util.List;

import centauri.academy.proxima.cerepro.entity.EntityInterface;
import centauri.academy.proxima.cerepro.entity.SurveysQuestions;
import centauri.academy.proxima.cerepro.repository.SurveyQuestionsRepository;
/**
 * 
 * @author Giacomo Della Luna
 *
 */
public class SurveyquestionsService {

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

	public boolean insert(SurveysQuestions sq) {
		boolean response = false;

		if (surveyQuestionsRepo.create(sq) > 0)
			response = true;
		return response;
	}

	public SurveysQuestions selectById(int id) {
		SurveysQuestions sqRetrived = new SurveysQuestions();
		sqRetrived = surveyQuestionsRepo.findById(id);

		return sqRetrived;
	}

	public List<SurveysQuestions> getAllSurveyquestions() {
		List<EntityInterface> listEntityInterface = new ArrayList<EntityInterface>();
		List<SurveysQuestions> listSurveysQuestions = new ArrayList<SurveysQuestions>();

		listEntityInterface = surveyQuestionsRepo.findAll(SurveysQuestions.class);
		
		for (EntityInterface entity : listEntityInterface) {
			listSurveysQuestions.add((SurveysQuestions)entity);
		}

		return listSurveysQuestions;
	}

	public boolean deleteSurveyquestion(SurveysQuestions sq) {
		return surveyQuestionsRepo.delete(SurveysQuestions.class, sq.getId());
	}

	public boolean deleteById(int id) {
		return surveyQuestionsRepo.delete(id) ;
	}

	public boolean updateSurveyquestions(SurveysQuestions sq) {
		return surveyQuestionsRepo.update(sq);
	}
}
