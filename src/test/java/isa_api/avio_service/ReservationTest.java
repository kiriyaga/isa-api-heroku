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
import isa_api.beans.users.RegistredUser;
import isa_api.dao.AvioCompanyRepository;
import isa_api.dao.FlightRepository;
import isa_api.dao.RegistredUserRepository;
import isa_api.dao.SeatRepository;
import isa_api.dto.AvioOrderDTO;
import isa_api.dto.UserLoginDTO;
import isa_api.services.AvioCompanyService;
import isa_api.services.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ReservationTest {

	@Autowired
	private AvioCompanyService avioService;

	@MockBean
	private AvioCompanyRepository avioRepository;

	@MockBean
	private FlightRepository flightRepository;
	
	@MockBean
	private SeatRepository seatRepository;
	
	@MockBean
	private RegistredUserRepository regUserRepository;
	
	@MockBean
	private MailService mailService;
	
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
		Seat s = new Seat();
		s.setId(1L);
		s.setFlight(flight);
		s.setSeatType(SeatTypeEnum.AVAILABLE);
		s.setSeatClass(SeatClassEnum.ECONOMIC);
		flight.getSeats().add(s);
		Seat s1 = new Seat();
		s1.setId(2L);
		s1.setFlight(flight);
		s1.setSeatType(SeatTypeEnum.AVAILABLE);
		s1.setSeatClass(SeatClassEnum.ECONOMIC);
		flight.getSeats().add(s1);
		company.getFlights().add(flight);
		
		RegistredUser regUser = new RegistredUser();
		regUser.setId(1L);
		regUser.setUsername("test");
		Mockito.when(avioRepository.findById(company.getId())).thenReturn(Optional.of(company));
		Mockito.when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
		Mockito.when(seatRepository.findById(1L)).thenReturn(Optional.of(s));
		Mockito.when(seatRepository.findById(2L)).thenReturn(Optional.of(s1));
		Mockito.when(regUserRepository.findById(1L)).thenReturn(Optional.of(regUser));
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReserveFlight() throws Exception {
		
		AvioCompany company = new AvioCompany();
		company.setId((long) 1);
		company.setName("Avio kompanija");
		Flight flight = new Flight(5.0, 2, company);
		flight.setId(1L);
		AvioOrderDTO avio = new AvioOrderDTO();
		avio.setFriends(new ArrayList<UserLoginDTO>());
		avio.setFlight(flight);
		avio.setCompany(company);
		UserLoginDTO user = new UserLoginDTO();
		user.setUsername("test");
		avio.setOwner(user);
		Seat s = new Seat();
		s.setId(1L);
		s.setFlight(flight);
		s.setSeatType(SeatTypeEnum.AVAILABLE);
		s.setSeatClass(SeatClassEnum.ECONOMIC);
		List<Seat> ss = new ArrayList<>();
		ss.add(s);
		avio.setSeats(ss);
		
		String proba = avioService.reserveFlight(avio);
		assertEquals("Successfuly reserved flight!", proba);
		
	}

}
