package proxima.informatica.academy.seventh.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import centauri.academy.proxima.cerepro.entity.EntityInterface;
import centauri.academy.proxima.cerepro.entity.Roles;
import centauri.academy.proxima.cerepro.repository.RolesRepository;

/**
 * 
 * @author maurizio.franco@ymail.com
 *
 */
public class RoleService {

	private final static Logger logger = LoggerFactory.getLogger(RoleService.class);
	
	RolesRepository roleRepository = null ;
	
	private RoleService() {
		roleRepository = new RolesRepository () ; 
	}

	private static RoleService instance;

	public static RoleService getInstance() {
		if (instance == null) {
			instance = new RoleService();
		}
		return instance;
	}

	public Roles insert(Roles item) {
		logger.debug("insert - START - item: " + item);
        long insertedId = roleRepository.create(item) ;
        logger.debug("insert - DEBUG - insertedId: " + insertedId);
        Roles itemToReturn = selectById(insertedId);
        return itemToReturn ;
	}

	public Roles selectById(long id) {
		Roles roleRetrived = new Roles();
		roleRetrived = (Roles)roleRepository.findById(id);

		return roleRetrived;
	}

	public List<EntityInterface> getAllRoles() {
		return roleRepository.findAll();
	}

	public boolean updateRole(Roles role) {
		return roleRepository.update(role) ;
	}

	public boolean deleteById(long id) {
		return roleRepository.delete(id) ;
	}

}
