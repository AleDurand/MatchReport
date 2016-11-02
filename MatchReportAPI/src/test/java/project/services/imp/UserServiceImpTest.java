package project.services.imp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import project.Application;
import project.exceptions.EntityAlreadyExistsException;
import project.exceptions.EntityNotFoundException;
import project.models.QUserModel;
import project.models.UserModel;
import project.repositories.UserRepository;
import project.services.UserService;

import com.querydsl.core.types.dsl.BooleanExpression;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class UserServiceImpTest {

	@InjectMocks
	@Autowired
	private UserService userService;

	@Mock
	private UserRepository userRepositoryMock;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void create() throws Exception {
		// @formatter:off
		UserModel expectedUser = new UserModel();
		expectedUser.setUsername("username");
		expectedUser.setPassword("password");
		expectedUser.setEnabled(true);		
		when(userRepositoryMock.save(expectedUser)).thenReturn(expectedUser);

		UserModel actualUser = userService.create(expectedUser);

		assertEquals(expectedUser.getUsername(), actualUser.getUsername());
		assertEquals(expectedUser.getPassword(), actualUser.getPassword());
		assertEquals(expectedUser.isEnabled(), actualUser.isEnabled());
		// @formatter:on
	}

	@Test(expected = EntityAlreadyExistsException.class)
	public void createUserAlreadyExists() throws Exception {
		// @formatter:off
		UserModel expectedUser = new UserModel();
		expectedUser.setUsername("username");
		expectedUser.setPassword("password");
		expectedUser.setEnabled(true);
		when(userRepositoryMock.exists("username")).thenReturn(true);

		userService.create(expectedUser);
		// @formatter:on
	}

	@Test
	public void read() throws Exception {
		// @formatter:off
		UserModel expectedUser = new UserModel();
		expectedUser.setUsername("username");
		expectedUser.setPassword("password");
		expectedUser.setEnabled(true);
		when(userRepositoryMock.exists("username")).thenReturn(true);
		when(userRepositoryMock.findOne("username")).thenReturn(expectedUser);

		UserModel actualUser = userService.loadUserByUsername("username");

		assertEquals(expectedUser.getUsername(), actualUser.getUsername());
		assertEquals(expectedUser.getPassword(), actualUser.getPassword());
		assertEquals(expectedUser.isEnabled(), actualUser.isEnabled());
		// @formatter:on
	}

	@Test(expected = EntityNotFoundException.class)
	public void readNotFound() throws Exception {
		// @formatter:off
		when(userRepositoryMock.exists("username")).thenReturn(false);

		userService.loadUserByUsername("username");
		// @formatter:on
	}

	@Test
	public void delete() throws Exception {
		// @formatter:off
		when(userRepositoryMock.exists("username")).thenReturn(true);

		userService.delete("username");
		// @formatter:on
	}

	@Test(expected = EntityNotFoundException.class)
	public void deleteNotFound() throws Exception {
		// @formatter:off
		when(userRepositoryMock.exists("username")).thenReturn(false);

		userService.delete("username");
		// @formatter:on
	}

	@Test
	public void getAll() throws Exception {
		// @formatter:off
		Page<UserModel> expectedUsers = new PageImpl<UserModel>(Arrays.asList(new UserModel()));
		Pageable pageable = new PageRequest(0, 1000);
		QUserModel user = QUserModel.userModel;
		when(userRepositoryMock.findAll(user.instanceOfAny(), pageable)).thenReturn(expectedUsers);

		Page<UserModel> actualUsers = userService.getAll(null, null, null, pageable);

		assertEquals(expectedUsers.getTotalElements(), actualUsers.getTotalElements());
		assertEquals(expectedUsers, actualUsers);
		// @formatter:on
	}
	
	@Test
	public void getAllFilteredByUsername() throws Exception {
		// @formatter:off
		Page<UserModel> expectedUsers = new PageImpl<UserModel>(Arrays.asList(new UserModel()));
		Pageable pageable = new PageRequest(0, 1000);
		BooleanExpression expression = QUserModel.userModel.username.eq("username");	
			
		when(userRepositoryMock.findAll(expression, pageable)).thenReturn(expectedUsers);

		Page<UserModel> actualUsers = userService.getAll("username", null, null, pageable);
		
		assertEquals(expectedUsers.getTotalElements(), actualUsers.getTotalElements());
		assertEquals(expectedUsers, actualUsers);
		// @formatter:on
	}
	
	@Test
	public void getAllFilteredByStatus() throws Exception {
		// @formatter:off
		Page<UserModel> expectedUsers = new PageImpl<UserModel>(Arrays.asList(new UserModel()));
		Pageable pageable = new PageRequest(0, 1000);
		BooleanExpression expression = QUserModel.userModel.enabled.eq(true);	
			
		when(userRepositoryMock.findAll(expression, pageable)).thenReturn(expectedUsers);

		Page<UserModel> actualUsers = userService.getAll(null, true, null, pageable);
		
		assertEquals(expectedUsers.getTotalElements(), actualUsers.getTotalElements());
		assertEquals(expectedUsers, actualUsers);
		// @formatter:on
	}

	@Test
	public void getAllFilteredByIdRole() throws Exception {
		// @formatter:off
		Page<UserModel> expectedUsers = new PageImpl<UserModel>(Arrays.asList(new UserModel()));
		Pageable pageable = new PageRequest(0, 1000);
		BooleanExpression expression = QUserModel.userModel.authorities.any().id.eq(1);	
			
		when(userRepositoryMock.findAll(expression, pageable)).thenReturn(expectedUsers);

		Page<UserModel> actualUsers = userService.getAll(null, null, 1, pageable);
		
		assertEquals(expectedUsers.getTotalElements(), actualUsers.getTotalElements());
		assertEquals(expectedUsers, actualUsers);
		// @formatter:on
	}
	
}
