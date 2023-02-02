package proxima.informatica.academy.seventh.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import centauri.academy.proxima.cerepro.entity.CandidateStates;
import centauri.academy.proxima.cerepro.entity.EntityInterface;
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

	public boolean update(CandidateStates candidate) {
		boolean response = false;
		if (candidateStatesRepository.update(candidate)!=false) 
			response = true;	
		return response;
	}
	
	
	public boolean insert(CandidateStates candidate) {
		boolean response = false;
		if (candidateStatesRepository.create(candidate)>1)
			response = true;
		return response;
	}
	
	public CandidateStates selectById(int id) {
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
	
	public boolean deleteById(int id) {
		return candidateStatesRepository.delete(id) ;
	}

}
