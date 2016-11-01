package project.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import project.models.UserModel;

public interface UserService extends UserDetailsService {

	public UserModel create(UserModel user);

	public UserModel loadUserByUsername(String username) throws UsernameNotFoundException;

	public void delete(String username);

	public List<UserModel> getAll(String username, Boolean enabled, Integer role);

}
