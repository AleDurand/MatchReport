package project.services.imp;

import static org.junit.Assert.*;
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
import project.models.ClubModel;
import project.repositories.ClubRepository;
import project.services.ClubService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
public class ClubServiceImpTest {

	@InjectMocks
	@Autowired
	private ClubService clubService;

	@Mock
	private ClubRepository clubRepositoryMock;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void create() throws Exception {
		// @formatter:off
		ClubModel expectedClub = new ClubModel();
		expectedClub.setId(1);
		expectedClub.setName("name");
		expectedClub.setAddress("address");
		expectedClub.setStadium(null);
		expectedClub.setUrl("url");
		expectedClub.setImage("image");
		when(clubRepositoryMock.save(expectedClub)).thenReturn(expectedClub);

		ClubModel actualClub = clubService.create(expectedClub);
		
		assertEquals(expectedClub.getId(), actualClub.getId());
		assertEquals(expectedClub.getName(), actualClub.getName());
		assertEquals(expectedClub.getAddress(), actualClub.getAddress());
		assertEquals(expectedClub.getStadium(), actualClub.getStadium());
		assertEquals(expectedClub.getUrl(), actualClub.getUrl());
		assertEquals(expectedClub.getImage(), actualClub.getImage());
		// @formatter:on
	}

	@Test
	public void read() throws Exception {
		// @formatter:off
		ClubModel expectedClub = new ClubModel();
		expectedClub.setId(1);
		expectedClub.setName("name");
		expectedClub.setAddress("address");
		expectedClub.setStadium(null);
		expectedClub.setUrl("url");
		expectedClub.setImage("image");
		when(clubRepositoryMock.exists(1)).thenReturn(true);
		when(clubRepositoryMock.findOne(1)).thenReturn(expectedClub);

		ClubModel actualClub = clubService.read(1);
		
		assertEquals(expectedClub.getId(), actualClub.getId());
		assertEquals(expectedClub.getName(), actualClub.getName());
		assertEquals(expectedClub.getAddress(), actualClub.getAddress());
		assertEquals(expectedClub.getStadium(), actualClub.getStadium());
		assertEquals(expectedClub.getUrl(), actualClub.getUrl());
		assertEquals(expectedClub.getImage(), actualClub.getImage());
		// @formatter:on
	}

	@Test(expected = EntityNotFoundException.class)
	public void readNotFound() throws Exception {
		// @formatter:off
		when(clubRepositoryMock.exists(1)).thenReturn(false);

		clubService.read(1);
		// @formatter:on
	}

	@Test
	public void delete() throws Exception {
		// @formatter:off
		when(clubRepositoryMock.exists(1)).thenReturn(true);

		clubService.delete(1);
		// @formatter:on
	}

	@Test(expected = EntityNotFoundException.class)
	public void deleteNotFound() throws Exception {
		// @formatter:off
		when(clubRepositoryMock.exists(1)).thenReturn(false);

		clubService.delete(1);
		// @formatter:on
	}

	@Test
	public void getAll() throws Exception {
		// @formatter:off
		List<ClubModel> expectedClubs = Arrays.asList(new ClubModel());
		when(clubRepositoryMock.findAll()).thenReturn(expectedClubs);

		List<ClubModel> actualClubs = clubService.getAll();
		
		assertEquals(expectedClubs.size(), actualClubs.size());
		assertEquals(expectedClubs, actualClubs);
		// @formatter:on
	}
}
