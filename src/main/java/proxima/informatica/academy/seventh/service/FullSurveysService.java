package proxima.informatica.academy.seventh.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import centauri.academy.proxima.cerepro.entity.FullSurveys;
import centauri.academy.proxima.cerepro.entity.Questions;
import centauri.academy.proxima.cerepro.entity.QuestionsResponse;
import centauri.academy.proxima.cerepro.entity.Surveys;
import centauri.academy.proxima.cerepro.repository.FullSurveysRepository;
import centauri.academy.proxima.cerepro.repository.SurveysRepository;

/**
 * 
 * @author Giacomo Della Luna 
 *
 */
public class FullSurveysService {

	private final static Logger logger = LoggerFactory.getLogger(FullSurveysService.class);

	SurveysRepository surveysRepository = null;
	FullSurveysRepository fullSurveysRepository = null;

	private FullSurveysService() {
		surveysRepository = new SurveysRepository();
		fullSurveysRepository = new FullSurveysRepository();
	}

	private static FullSurveysService instance;

	public static FullSurveysService getInstance() {
		if (instance == null) {
			instance = new FullSurveysService();
		}
		return instance;
	}

	public FullSurveys selectFullSurveysBySurveysId(long id) {

		logger.debug("selectFullSurveysBySurveysId - START");

		List<Questions> questionsRetrived = new ArrayList<Questions>();
		List<QuestionsResponse> questionsResponse = new ArrayList<QuestionsResponse>();
		FullSurveys fullService = new FullSurveys();
		fullService.setSurvey((Surveys) surveysRepository.findById(id));

		questionsRetrived = fullSurveysRepository.findQuestionBySurveyId(id);
		for (int i = 0; i < questionsRetrived.size(); i++) {
			questionsResponse.add(i, new QuestionsResponse());
			questionsResponse.get(i).setId(questionsRetrived.get(i).getId());
			questionsResponse.get(i).setLabel(questionsRetrived.get(i).getLabel());
			questionsResponse.get(i).setDescription(questionsRetrived.get(i).getDescription());
			questionsResponse.get(i).setAnsa(questionsRetrived.get(i).getAnsa());
			questionsResponse.get(i).setAnsb(questionsRetrived.get(i).getAnsb());
			questionsResponse.get(i).setAnsc(questionsRetrived.get(i).getAnsc());
			questionsResponse.get(i).setAnsd(questionsRetrived.get(i).getAnsd());
			questionsResponse.get(i).setAnse(questionsRetrived.get(i).getAnse());
			questionsResponse.get(i).setAnsf(questionsRetrived.get(i).getAnsf());
			questionsResponse.get(i).setAnsg(questionsRetrived.get(i).getAnsg());
			questionsResponse.get(i).setAnsh(questionsRetrived.get(i).getAnsh());

		}
		
		fullService.setQuestionsResponse(questionsResponse);	
		return fullService;
	}
}
