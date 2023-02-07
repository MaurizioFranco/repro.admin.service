package proxima.informatica.academy.seventh.service;

import java.util.ArrayList;
import java.util.List;

import org.proxima.common.mail.MailUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import centauri.academy.proxima.cerepro.entity.EntityInterface;
import centauri.academy.proxima.cerepro.entity.User;
import centauri.academy.proxima.cerepro.repository.UserRepository;
import proxima.informatica.academy.seventh.common.PropertiesManagerSingleton;
import proxima.informatica.academy.seventh.user.result.LoginResult;

public class UserService {

	private final static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	UserRepository userRepository = null ;
	
	private UserService() {
		userRepository = new UserRepository () ;
	}

	private static UserService instance;

	public static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}

	public LoginResult login(String email, String password) {
		LoginResult result = new LoginResult();
		User user = new User();
		user = (User)userRepository.findByEmail(email);

		if (user == null) {
			result.setLoginResponse(false);
			result.setLoginMessage("Email not found");
		} else if (user.getPassword() == null) {
			result.setLoginResponse(false);
			result.setLoginMessage("Password not found");
		} else if (!user.getPassword().equals(password)) {
			result.setLoginResponse(false);
			result.setLoginMessage("Password wrong");
		} else if (user.getenabled() == 0) {
			result.setLoginResponse(false);
			result.setLoginMessage("Account not activated");
		} else {
			result.setLoginResponse(true);
			result.setLoginMessage("Welcome");
		}
		return result;
	}

	public boolean insertFromRegistration(User user) {
		boolean response = false;
		String host = null;
		String port = null;
		String home = null;
		try {
			host = PropertiesManagerSingleton.getInstance().getProperty("properties.host");
			port = PropertiesManagerSingleton.getInstance().getProperty("properties.port");
			home = PropertiesManagerSingleton.getInstance().getProperty("properties.home");		
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		try {
			if (userRepository.create(user) > 0) {
				User userForId = (User)userRepository.findByEmail(user.getEmail());
				String email = userForId.getEmail();
				MailUtility.sendSimpleMail(email, "Create a new password",
						"Click <a href='http://"+host+":"+port+"/"+home+"/completeRegistration.jsp?id="
								+ userForId.getId() + "'>here</a> to complete your registration");

				response = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return response;
	}
	
	public User insert(User item) {
		logger.debug("insert - START - item: " + item);
        long insertedId = userRepository.create(item) ;
        logger.debug("insert - DEBUG - insertedId: " + insertedId);
        User itemToReturn = selectById(insertedId);
        return itemToReturn ;
	}

	public User selectById(long id) {
		User userRetrived = new User();
		userRetrived = (User)userRepository.findById(id);

		return userRetrived;
	}	

	public List<EntityInterface> getAll() {
		return userRepository.findAll();
	}
	
	public List<User> getAllUsersByRole() {
		List<User> listUsers = new ArrayList<User>();

		listUsers = userRepository.findByRole();

		return listUsers;
	}

	public User update(User item) {
		logger.debug("update - START - item: " + item);
        boolean returnValue = userRepository.update(item) ;
        logger.debug("update - DEBUG - updated result: " + returnValue);
        if (returnValue) {
        	return (User)userRepository.findById(item.getId());
        }
        return null ;
	}

	public boolean deleteById(int id) {
		boolean response = false;

		if (userRepository.delete(id))
			response = true;

		return response;
	}

}
