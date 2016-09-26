package project.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import project.exceptions.EntityAlreadyExistsException;
import project.exceptions.EntityNotFoundException;
import project.models.UserModel;
import project.repositories.UserRepository;
import project.services.UserService;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserModel create(UserModel user) {
		if (userRepository.exists(user.getUsername())) {
			throw new EntityAlreadyExistsException("resource.already_exists", null);
		}
		return userRepository.save(user);
	}

	@Override
	public UserModel loadUserByUsername(String username) throws UsernameNotFoundException {
		if (!userRepository.exists(username)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		return userRepository.findOne(username);
	}

	@Override
	public void delete(String username) {
		if (!userRepository.exists(username)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		userRepository.delete(username);
	}

	@Override
	public List<UserModel> getAll() {
		return userRepository.findAll();
	}

}
