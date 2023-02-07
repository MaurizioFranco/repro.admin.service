package proxima.informatica.academy.seventh.service;

import java.util.List;

import org.slf4j.LoggerFactory;

import centauri.academy.proxima.cerepro.entity.EntityInterface;
import centauri.academy.proxima.cerepro.entity.Candidates;
import centauri.academy.proxima.cerepro.entity.Candidates;
import centauri.academy.proxima.cerepro.repository.CandidatesRepository;

public class CandidatesService {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(CandidatesService.class);

	CandidatesRepository candidateRepository = null ;
	
	private CandidatesService() {
		candidateRepository = new CandidatesRepository () ; 
	}

	private static CandidatesService instance;

	public static CandidatesService getInstance() {
		if (instance == null) {
			instance = new CandidatesService();
		}
		return instance;
	}

	public Candidates insert(Candidates item) {
		logger.debug("insert - START - item: " + item);
        long insertedId = candidateRepository.create(item) ;
        logger.debug("insert - DEBUG - insertedId: " + insertedId);
        Candidates itemToReturn = selectById(insertedId);
        return itemToReturn ;
	}

	public Candidates selectById(long id) {
		Candidates candidateRetrived = new Candidates();
		candidateRetrived = (Candidates)candidateRepository.findById(id);

		return candidateRetrived;
	}

	public List<EntityInterface> getAllCandidates() {
		return candidateRepository.findAll();
	}

	public boolean updateCandidates(Candidates candidate) {
		return candidateRepository.update(candidate) ;
	}
	
	public Candidates update(Candidates item) {
		logger.debug("update - START - item: " + item);
        boolean returnValue = candidateRepository.update(item) ;
        logger.debug("update - DEBUG - updated result: " + returnValue);
        if (returnValue) {
        	return (Candidates)candidateRepository.findById(item.getId());
        }
        return null ;
	}

	public boolean deleteById(Long id) {
		boolean response = false;

		if (candidateRepository.delete(id))
			response = true;
		return response;
	}
}
