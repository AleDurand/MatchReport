package project.services.imp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import project.Application;
import project.exceptions.EntityNotFoundException;
import project.models.RoleModel;
import project.repositories.RoleRepository;
import project.services.RoleService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class RoleServiceImpTest {

	@InjectMocks
	@Autowired
	private RoleService roleService;

	@Mock
	private RoleRepository roleRepositoryMock;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void create() throws Exception {
		// @formatter:off
		RoleModel expectedRole = new RoleModel();
		expectedRole.setId(1);
		expectedRole.setName("name");
		when(roleRepositoryMock.save(expectedRole)).thenReturn(expectedRole);

		RoleModel actualRole = roleService.create(expectedRole);

		assertEquals(expectedRole.getId(), actualRole.getId());
		assertEquals(expectedRole.getName(), actualRole.getName());
		// @formatter:on
	}

	@Test
	public void read() throws Exception {
		// @formatter:off
		RoleModel expectedRole = new RoleModel();
		expectedRole.setId(1);
		expectedRole.setName("name");
		when(roleRepositoryMock.exists(1)).thenReturn(true);
		when(roleRepositoryMock.findOne(1)).thenReturn(expectedRole);

		RoleModel actualRole = roleService.read(1);

		assertEquals(expectedRole.getId(), actualRole.getId());
		assertEquals(expectedRole.getName(), actualRole.getName());
		// @formatter:on
	}

	@Test(expected = EntityNotFoundException.class)
	public void readNotFound() throws Exception {
		// @formatter:off
		when(roleRepositoryMock.exists(1)).thenReturn(false);

		roleService.read(1);
		// @formatter:on
	}

	@Test
	public void delete() throws Exception {
		// @formatter:off
		when(roleRepositoryMock.exists(1)).thenReturn(true);

		roleService.delete(1);
		// @formatter:on
	}

	@Test(expected = EntityNotFoundException.class)
	public void deleteNotFound() throws Exception {
		// @formatter:off
		when(roleRepositoryMock.exists(1)).thenReturn(false);

		roleService.delete(1);
		// @formatter:on
	}

	@Test
	public void getAll() throws Exception {
		// @formatter:off
		List<RoleModel> expectedRoles = Arrays.asList(new RoleModel());
		when(roleRepositoryMock.findAll()).thenReturn(expectedRoles);

		List<RoleModel> actualRoles = roleService.getAll();

		assertEquals(expectedRoles.size(), actualRoles.size());
		assertEquals(expectedRoles, actualRoles);
		// @formatter:on
	}

}
