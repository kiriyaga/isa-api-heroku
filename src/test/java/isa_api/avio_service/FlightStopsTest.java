package isa_api.avio_service;

import static org.junit.Assert.*;

import java.util.Date;
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
import isa_api.dto.FlightStopDTO;
import isa_api.services.AvioCompanyService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FlightStopsTest {
	
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
		FlightStop stop = new FlightStop(1L, new Date(), "2", new Location());
		flight.getFlightStop().add(stop);
		company.getFlights().add(flight);
		Mockito.when(avioRepository.findById(company.getId())).thenReturn(Optional.of(company));
		Mockito.when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));

	}
	

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddFlightStop() {
		AvioCompany company = new AvioCompany();
		company.setId((long) 1);
		company.setName("Avio kompanija");
		Flight flight = new Flight(5.0, 2, company);
		flight.setId(1L);
		company.getFlights().add(flight);
		
		FlightStopDTO stop = new FlightStopDTO();
		stop.setCompany(company);
		stop.setFlight(flight);
		stop.setFlightStop(new FlightStop(2L, new Date(), "4", new Location()));
		AvioCompany avio = avioService.addFlightStop(stop);
		assertNotNull(avio.getFlights().get(0).getFlightStop().get(1));
		assertEquals(2, avio.getFlights().get(0).getFlightStop().size());
		
	}

	@Test
	public void testDeleteFlightStop() {
		AvioCompany company = new AvioCompany();
		company.setId((long) 1);
		company.setName("Avio kompanija");
		Flight flight = new Flight(5.0, 2, company);
		flight.setId(1L);
		company.getFlights().add(flight);
		
		FlightStopDTO stop = new FlightStopDTO();
		stop.setCompany(company);
		stop.setFlight(flight);
		stop.setFlightStop(new FlightStop(1L, new Date(), "2", new Location()));
		AvioCompany avio = avioService.deleteFlightStop(stop);
		assertEquals(0, avio.getFlights().get(0).getFlightStop().size());
	}

}
