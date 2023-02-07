package proxima.informatica.academy.seventh.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import centauri.academy.proxima.cerepro.entity.Questions;
import centauri.academy.proxima.cerepro.entity.EntityInterface;
import centauri.academy.proxima.cerepro.repository.QuestionsRepository;

/**
 * 
 * @author DaimCod
 *
 */
public class QuestionsService {
	private final static Logger logger = LoggerFactory.getLogger(QuestionsService.class);
	private QuestionsRepository questionRepository = new QuestionsRepository();
	private static QuestionsService instance;

	private QuestionsService() {
		
	}

	public static QuestionsService getIstance() {
		if (instance == null) {
			instance = new QuestionsService();
		}
		return instance;
	}
	
	public List<EntityInterface> selectAll(){	
		logger.debug("QuestionsService: selectAll - START");
		return questionRepository.findAll();
	}
	
	public Questions selectById(long id) {
		logger.debug("QuestionsService: Select by ID - START");
		Questions question = null;
		question = questionRepository.findById(id);
		logger.debug("QuestionsService: Select by ID - END");
		return question;
	}
	
	public boolean deleteById(long id) {
		return questionRepository.delete(id) ;
	}
	
	public Questions insert(Questions question) {
		logger.debug("QuestionsService: INSERT - START");
		long response = questionRepository.create(question);
		logger.debug("QuestionsService: DEBUG - response: " + response);
		Questions questions = QuestionsService.getIstance().selectById(response);
		logger.debug("QuestionsService: END");
		return questions;
	}
	
	
	
	public Questions updateQuestion(Questions qs) {
		logger.debug("QuestionsService: UPDATE - START");
		boolean resultObj = false;
		Questions question = null;
		resultObj = questionRepository.update(qs);
		logger.debug("QuestionsService: UPDATE - DEBUG - resultObj: " + resultObj);
		if(resultObj)
			question = QuestionsService.getIstance().selectById(qs.getId());
		return question;
	}

}
