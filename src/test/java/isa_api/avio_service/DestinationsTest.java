package isa_api.avio_service;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import isa_api.beans.Location;
import isa_api.beans.flight.AvioCompany;
import isa_api.dao.AvioCompanyRepository;
import isa_api.dao.LocationRepository;
import isa_api.dto.BasicDestinationDTO;
import isa_api.services.AvioCompanyService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DestinationsTest {

	@Autowired
	private AvioCompanyService avioService;

	@MockBean
	private AvioCompanyRepository avioRepository;

	@MockBean
	private LocationRepository locationRepository;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		AvioCompany company = new AvioCompany();
		company.setId((long) 1);
		company.setName("Avio kompanija");
		company.setLocation(new Location(1L, 25.0, 25.0, "NEKA", "NK"));
		Mockito.when(avioRepository.findById(company.getId())).thenReturn(Optional.of(company));

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddAndEditDestination() {
		
		BasicDestinationDTO basic = new BasicDestinationDTO();
		basic.setCompany(1L);
		basic.setLocation(new Location(1l,25.0, 25.0, "NEKA", "NK"));
		AvioCompany avio = avioService.addDestination(basic);
		assertNotNull(avio.getDestinations());
		assertEquals(avio.getDestinations().get(0).getAdress(),"NEKA");
		assertEquals(avio.getDestinations().get(0).getCode(),"NK");
	}

	@Test
	public void testAddDestination() {
		
		BasicDestinationDTO basic = new BasicDestinationDTO();
		basic.setCompany(1L);
		basic.setLocation(new Location(1l,25.0, 25.0, "NEKA", "NK"));
		AvioCompany avio = avioService.addDestination(basic);
		assertNotNull(avio.getDestinations());
		assertEquals(avio.getDestinations().get(0).getAdress(),"NEKA");
		assertEquals(avio.getDestinations().get(0).getCode(),"NK");
	}

	@Test
	public void testDeleteDestination() {
		BasicDestinationDTO basic = new BasicDestinationDTO();
		basic.setCompany(1L);
		basic.setLocation(new Location(1l,25.0, 25.0, "NEKA", "NK"));
		AvioCompany avio = avioService.deleteDestination(basic);
		assertEquals(0, avio.getDestinations().size());
		
	}

}
