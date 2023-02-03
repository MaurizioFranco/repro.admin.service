package proxima.informatica.academy.seventh.services;

import java.util.List;

import org.slf4j.LoggerFactory;

import centauri.academy.proxima.cerepro.entity.EntityInterface;
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

	public boolean insertCandidates(Candidates candidate) {
		boolean response = false;

		if (candidateRepository.create(candidate) > 0)
			response = true;
		return response;
	}

	public Candidates selectById(int id) {
		Candidates candidateRetrived = new Candidates();
		candidateRetrived = (Candidates)candidateRepository.findById((long)id);

		return candidateRetrived;
	}

	public List<EntityInterface> getAllCandidates() {
		return candidateRepository.findAll();
	}

	public boolean updateCandidates(Candidates candidate) {
		return candidateRepository.update(candidate) ;
	}

	public boolean deleteById(int id) {
		boolean response = false;

		if (candidateRepository.delete(id))
			response = true;
		return response;
	}
}
