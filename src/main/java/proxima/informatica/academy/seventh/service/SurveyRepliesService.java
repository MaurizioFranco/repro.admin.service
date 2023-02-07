package proxima.informatica.academy.seventh.service;

import java.util.ArrayList;
import java.util.List;

//import org.proxima.common.mail.MailUtility;
import org.slf4j.LoggerFactory;

import centauri.academy.proxima.cerepro.entity.EntityInterface;
import centauri.academy.proxima.cerepro.entity.Roles;
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
	
	public SurveysReplies insert(SurveysReplies item) {
		logger.debug("insert - START - item: " + item);
        long insertedId = surveyRepliesRepository.create(item) ;
        logger.debug("insert - DEBUG - insertedId: " + insertedId);
        SurveysReplies itemToReturn = selectSurveyrepliesById(insertedId);
        return itemToReturn ;
	}
	
//	public List<SurveyReplies> selectAllSurveyreplies() {
//		return surveyRepliesRepository.selectAll();
//	}
	public List<EntityInterface> getAllSurveyReplies() {
		return surveyRepliesRepository.findAll();
	}
	
	public void deleteSurveyreplies(SurveysReplies surveyrepliesToDelete) {
		surveyRepliesRepository.delete(surveyrepliesToDelete.getId());
	}
	
	public SurveysReplies selectSurveyrepliesById(long id) {
		return surveyRepliesRepository.findById(id);
	}
	
	public boolean updateSurveyReplies(SurveysReplies item) {
		return surveyRepliesRepository.update(item);
	}
	
	public boolean deleteSurveyRepliesById(long id) {
		return surveyRepliesRepository.delete(id);
	}
}
