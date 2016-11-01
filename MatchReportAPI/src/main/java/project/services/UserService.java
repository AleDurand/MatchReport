package project.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import project.models.UserModel;

public interface UserService extends UserDetailsService {

	public UserModel create(UserModel user);

	public UserModel loadUserByUsername(String username) throws UsernameNotFoundException;

	public void delete(String username);

	public Page<UserModel> getAll(String username, Boolean enabled, Integer role, Pageable pageable);

}
