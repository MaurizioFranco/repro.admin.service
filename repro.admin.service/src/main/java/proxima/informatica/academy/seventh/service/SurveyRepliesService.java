package proxima.informatica.academy.seventh.service;

import java.util.ArrayList;
import java.util.List;

//import org.proxima.common.mail.MailUtility;
import org.slf4j.LoggerFactory;

import centauri.academy.proxima.cerepro.entity.EntityInterface;
import centauri.academy.proxima.cerepro.entity.SurveysReplies;
import centauri.academy.proxima.cerepro.repository.SurveyRepliesRepository;

public class SurveyRepliesService {

	private static SurveyRepliesService instance;
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(SurveyRepliesService.class);

	private SurveyRepliesRepository surveyRepliesRepository;
	
	private SurveyRepliesService() {
		surveyRepliesRepository = new SurveyRepliesRepository();
	}

	public static SurveyRepliesService getInstance() {
		if (instance == null) {
			instance = new SurveyRepliesService();
		}
		return instance;
	}
	
	public boolean insertSurveyreplies(SurveysReplies surveyrepliesToInsert) {
		boolean response = false;
		if(surveyRepliesRepository.create(surveyrepliesToInsert)>1) {
			response = true;
		}
		return response;
	}
	
//	public List<SurveyReplies> selectAllSurveyreplies() {
//		return surveyRepliesRepository.selectAll();
//	}
	public List<SurveysReplies> getAllSurveyReplies() {
		List<EntityInterface> listEntityInterface = new ArrayList<EntityInterface>();
		List<SurveysReplies> listSurveysQuestions = new ArrayList<SurveysReplies>();

		listEntityInterface = surveyRepliesRepository.findAll(SurveysReplies.class);
		
		for (EntityInterface entity : listEntityInterface) {
			listSurveysQuestions.add((SurveysReplies)entity);
		}

		return listSurveysQuestions;
	}
	
	public void deleteSurveyreplies(SurveysReplies surveyrepliesToDelete) {
		surveyRepliesRepository.delete(surveyrepliesToDelete.getId());
	}
	
	public SurveysReplies selectSurveyrepliesById(int id) {
		return surveyRepliesRepository.findById(id);
	}
	
	public boolean updateSurveyReplies(SurveysReplies item) {
		return surveyRepliesRepository.update(item);
	}
}
