package project.services.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import project.exceptions.EntityAlreadyExistsException;
import project.exceptions.EntityNotFoundException;
import project.models.QUserModel;
import project.models.UserModel;
import project.repositories.UserRepository;
import project.services.UserService;

import com.querydsl.core.types.dsl.BooleanExpression;

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
	public Page<UserModel> getAll(String username, Boolean enabled, Integer role, Pageable pageable) {
		List<BooleanExpression> expressions = new ArrayList<BooleanExpression>();
		
		QUserModel user = QUserModel.userModel;
		BooleanExpression expression1 = (username != null) ? user.username.eq(username) : null;
		expressions.add(expression1);

		BooleanExpression expression2 = (enabled != null) ? user.enabled.eq(enabled) : null;
		expressions.add(expression2);
		
		BooleanExpression expression3 = (role != null) ? user.authorities.any().id.eq(role) : null;
		expressions.add(expression3);
		
		BooleanExpression expression = null;
		for (BooleanExpression ex : expressions) {
			if (expression == null) {
				expression = ex;
			} else {
				expression = expression.and(ex);
			}
		}
		
		return userRepository.findAll(expression, pageable);
	}

}
