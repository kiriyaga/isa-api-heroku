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
import isa_api.beans.flight.Flight;
import isa_api.beans.flight.FlightStop;
import isa_api.dao.AvioCompanyRepository;
import isa_api.dao.FlightRepository;
import isa_api.dto.DeleteFlightDTO;
import isa_api.dto.FlightAvioCompanyDTO;
import isa_api.services.AvioCompanyService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FlightsTest {
	
	@Autowired
	private AvioCompanyService avioService;
	
	@MockBean
	private AvioCompanyRepository avioRepository;
	
	@MockBean
	private FlightRepository flightRepository;


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
		Flight flight = new Flight(5.0, 2, company);
		flight.setId(1L);
		company.getFlights().add(flight);
		Mockito.when(avioRepository.findById(company.getId())).thenReturn(Optional.of(company));
		Mockito.when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddFlight() {
		AvioCompany company = new AvioCompany();
		company.setId((long) 1);
		company.setName("Avio kompanija");
		company.getFlights().add(new Flight(5.0, 2, company));
		FlightAvioCompanyDTO flight = new FlightAvioCompanyDTO(company, 1L	, 2	, 4, 4, 4, 4, new FlightStop(), new FlightStop(), "2", "2");
		AvioCompany avio = avioService.addFlight(flight);
		assertNotNull(avio.getFlights().get(1));
		assertEquals(2, avio.getFlights().size());
	}

	@Test
	public void testEditFlight() {
		
		AvioCompany company = new AvioCompany();
		company.setId((long) 1);
		company.setName("Avio kompanija");
		company.getFlights().add(new Flight(5.0, 2, company));
		FlightAvioCompanyDTO flight = new FlightAvioCompanyDTO(company, 1L	, 2	, 4, 4, 4, 4, new FlightStop(), new FlightStop(), "2", "2");
		AvioCompany avio = avioService.editFlight(flight);
		assertNotNull(avio.getFlights().get(0));
		assertEquals(1, avio.getFlights().size());
		assertEquals("2", avio.getFlights().get(0).getTravelDistance());
		
	}

	@Test
	public void testDeleteFlight() {
		
		AvioCompany company = new AvioCompany();
		company.setId((long) 1);
		company.setName("Avio kompanija");
		Flight flight = new Flight(5.0, 2, company);
		flight.setId(1L);
		company.getFlights().add(flight);
		DeleteFlightDTO del = new DeleteFlightDTO();
		del.setCompany(company);
		del.setFlight(flight);
		
		AvioCompany avio = avioService.deleteFlight(del);
		assertEquals(0, avio.getFlights().size());

	}

}
