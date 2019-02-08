package isa_api.avio_service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
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

import isa_api.beans.flight.AvioCompany;
import isa_api.beans.flight.Flight;
import isa_api.beans.flight.Seat;
import isa_api.beans.flight.SeatClassEnum;
import isa_api.beans.flight.SeatTypeEnum;
import isa_api.dao.AvioCompanyRepository;
import isa_api.dao.FlightRepository;
import isa_api.dao.SeatRepository;
import isa_api.dto.FlightSeatsDTO;
import isa_api.services.AvioCompanyService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SeatsTest {

	@Autowired
	private AvioCompanyService avioService;
	
	@MockBean
	private AvioCompanyRepository avioRepository;
	
	@MockBean
	private FlightRepository flightRepository;
	
	@MockBean
	private SeatRepository seatRepository;
	
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
		Seat s = new Seat();
		s.setId(1L);
		s.setFlight(flight);
		s.setSeatType(SeatTypeEnum.AVAILABLE);
		s.setSeatClass(SeatClassEnum.ECONOMIC);
		flight.getSeats().add(s);
		Mockito.when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
		
		Mockito.when(seatRepository.findById(1L)).thenReturn(Optional.of(s));
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveSeats() {
		Seat s = new Seat();
		s.setId(null);
		s.setSeatType(SeatTypeEnum.AVAILABLE);
		s.setSeatClass(SeatClassEnum.ECONOMIC);
		ArrayList<Seat> seats = new ArrayList<>();
		seats.add(s);
		
		FlightSeatsDTO seat = new FlightSeatsDTO();
		seat.setId(1L);
		seat.setSeats(seats);
		Flight f = avioService.saveSeats(seat);
		assertNotNull(f.getSeats().get(1));
		assertEquals(2, f.getSeats().size());
	}
	
	@Test
	public void testSaveSeatsUpdate() {
		Seat s = new Seat();
		s.setId(1L);
		s.setSeatType(SeatTypeEnum.RESERVED);
		s.setSeatClass(SeatClassEnum.ECONOMIC);
		ArrayList<Seat> seats = new ArrayList<>();
		seats.add(s);
		
		FlightSeatsDTO seat = new FlightSeatsDTO();
		seat.setId(1L);
		seat.setSeats(seats);
		Flight f = avioService.saveSeats(seat);
		assertNotNull(f.getSeats().get(0));
		assertEquals(1, f.getSeats().size());
	}

}
