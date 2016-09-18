package project.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import project.models.UserModel;
import project.repositories.imp.UserRepository;
import project.services.UserService;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserModel loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.getOne(username);
	}

	@Override
	public List<UserModel> getAll() {
		return userRepository.findAll();
	}

}
