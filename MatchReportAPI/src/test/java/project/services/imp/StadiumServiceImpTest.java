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
import project.exceptions.EntityNotFoundException;
import project.models.QStadiumModel;
import project.models.StadiumModel;
import project.repositories.StadiumRepository;
import project.services.StadiumService;

import com.querydsl.core.types.dsl.BooleanExpression;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class StadiumServiceImpTest {

	@InjectMocks
	@Autowired
	private StadiumService stadiumService;

	@Mock
	private StadiumRepository stadiumRepositoryMock;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void create() throws Exception {
		// @formatter:off
		StadiumModel expectedStadium = new StadiumModel();
		expectedStadium.setId(1);
		expectedStadium.setName("name");
		expectedStadium.setAddress("address");
		expectedStadium.setImage("image");
		when(stadiumRepositoryMock.save(expectedStadium)).thenReturn(expectedStadium);

		StadiumModel actualStadium = stadiumService.create(expectedStadium);

		assertEquals(expectedStadium.getId(), actualStadium.getId());
		assertEquals(expectedStadium.getName(), actualStadium.getName());
		assertEquals(expectedStadium.getAddress(), actualStadium.getAddress());
		assertEquals(expectedStadium.getImage(), actualStadium.getImage());
		// @formatter:on
	}

	@Test
	public void read() throws Exception {
		// @formatter:off
		StadiumModel expectedStadium = new StadiumModel();
		expectedStadium.setId(1);
		expectedStadium.setName("name");
		expectedStadium.setAddress("address");
		expectedStadium.setImage("image");
		when(stadiumRepositoryMock.exists(1)).thenReturn(true);
		when(stadiumRepositoryMock.findOne(1)).thenReturn(expectedStadium);

		StadiumModel actualStadium = stadiumService.read(1);

		assertEquals(expectedStadium.getId(), actualStadium.getId());
		assertEquals(expectedStadium.getName(), actualStadium.getName());
		assertEquals(expectedStadium.getAddress(), actualStadium.getAddress());
		assertEquals(expectedStadium.getImage(), actualStadium.getImage());
		// @formatter:on
	}

	@Test(expected = EntityNotFoundException.class)
	public void readNotFound() throws Exception {
		// @formatter:off
		when(stadiumRepositoryMock.exists(1)).thenReturn(false);

		stadiumService.read(1);
		// @formatter:on
	}

	@Test
	public void delete() throws Exception {
		// @formatter:off
		when(stadiumRepositoryMock.exists(1)).thenReturn(true);

		stadiumService.delete(1);
		// @formatter:on
	}

	@Test(expected = EntityNotFoundException.class)
	public void deleteNotFound() throws Exception {
		// @formatter:off
		when(stadiumRepositoryMock.exists(1)).thenReturn(false);

		stadiumService.delete(1);
		// @formatter:on
	}

	@Test
	public void getAll() throws Exception {
		// @formatter:off
		Page<StadiumModel> expectedStadiums = new PageImpl<StadiumModel>(Arrays.asList(new StadiumModel()));
		Pageable pageable = new PageRequest(0, 1000);
		QStadiumModel stadium = QStadiumModel.stadiumModel;
		when(stadiumRepositoryMock.findAll(stadium.instanceOfAny(), pageable)).thenReturn(expectedStadiums);

		Page<StadiumModel> actualStadiums = stadiumService.getAll(null, null, pageable);

		assertEquals(expectedStadiums.getTotalElements(), actualStadiums.getTotalElements());
		assertEquals(expectedStadiums, actualStadiums);
		// @formatter:on
	}

	@Test
	public void getAllFilteredById() throws Exception {
		// @formatter:off
		Page<StadiumModel> expectedStadiums = new PageImpl<StadiumModel>(Arrays.asList(new StadiumModel()));
		Pageable pageable = new PageRequest(0, 1000);
		BooleanExpression expression = QStadiumModel.stadiumModel.id.eq(1);	
			
		when(stadiumRepositoryMock.findAll(expression, pageable)).thenReturn(expectedStadiums);

		Page<StadiumModel> actualStadiums = stadiumService.getAll(1,  null, pageable);
		
		assertEquals(expectedStadiums.getTotalElements(), actualStadiums.getTotalElements());
		assertEquals(expectedStadiums, actualStadiums);
		// @formatter:on
	}

	@Test
	public void getAllFilteredByName() throws Exception {
		// @formatter:off
		Page<StadiumModel> expectedStadiums = new PageImpl<StadiumModel>(Arrays.asList(new StadiumModel()));
		Pageable pageable = new PageRequest(0, 1000);
		BooleanExpression expression = QStadiumModel.stadiumModel.name.contains("name");	
			
		when(stadiumRepositoryMock.findAll(expression, pageable)).thenReturn(expectedStadiums);

		Page<StadiumModel> actualStadiums = stadiumService.getAll(null, "name", pageable);
		
		assertEquals(expectedStadiums.getTotalElements(), actualStadiums.getTotalElements());
		assertEquals(expectedStadiums, actualStadiums);
		// @formatter:on
	}

}
