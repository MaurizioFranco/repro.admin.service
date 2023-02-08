package proxima.informatica.academy.seventh.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import centauri.academy.proxima.cerepro.entity.CandidateStates;
import centauri.academy.proxima.cerepro.entity.EntityInterface;
import centauri.academy.proxima.cerepro.entity.Roles;
import centauri.academy.proxima.cerepro.repository.CandidateStatesRepository;

/**
 * @author MarcoFabretti
 */

public class CandidateStatesService {

	private final static Logger logger = LoggerFactory.getLogger(CandidateStatesService.class);
	private CandidateStatesRepository candidateStatesRepository;
	
	private CandidateStatesService() {
		candidateStatesRepository = new CandidateStatesRepository();
	}

	private static CandidateStatesService instance;

	
	public static CandidateStatesService getInstance() {
		if (instance == null) {
			instance = new CandidateStatesService();
		}
		return instance;
	}

	public CandidateStates update(CandidateStates item) {
		logger.debug("update - START - item: " + item);
        boolean returnValue = candidateStatesRepository.update(item) ;
        logger.debug("update - DEBUG - updated result: " + returnValue);
        if (returnValue) {
        	return (CandidateStates)candidateStatesRepository.findById(item.getId());
        }
        return null ;
	}
	
	
	public CandidateStates insert(CandidateStates item) {
		logger.debug("insert - START - item: " + item);
        long insertedId = candidateStatesRepository.create(item) ;
        logger.debug("insert - DEBUG - insertedId: " + insertedId);
        CandidateStates itemToReturn = selectById(insertedId);
        return itemToReturn ;
	}
	
	public CandidateStates selectById(long id) {
		CandidateStates candidateRetrived = new CandidateStates();
		candidateRetrived = (CandidateStates)candidateStatesRepository.findById(id);
		return candidateRetrived;
	}

//	public List<CandidateStates> getAllRoles() {
//		List<CandidateStates> listRoles = new ArrayList<CandidateStates>();
//
//		listRoles = candidateStatesRepository.findAll();
//
//		return listRoles;
//	}
	
	public boolean delete(CandidateStates candidate) throws ClassNotFoundException {
		boolean response = false;

		if (candidateStatesRepository.delete(candidate.getId()));
			response = true;

		return response;
	}
	
	public List<EntityInterface> selectAll(){	
		logger.debug("QuestionsService: selectAll - START");
		return candidateStatesRepository.findAll();
	}
	
	public boolean deleteById(Long id) {
		return candidateStatesRepository.delete(id) ;
	}

}
