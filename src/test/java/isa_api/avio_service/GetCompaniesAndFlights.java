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
import isa_api.beans.users.AuthorityEnum;
import isa_api.beans.users.AvioAdmin;
import isa_api.dao.AvioAdminRepository;
import isa_api.dao.AvioCompanyRepository;
import isa_api.dao.FlightRepository;
import isa_api.dto.UserLoginDTO;
import isa_api.services.AvioCompanyService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class GetCompaniesAndFlights {

	
	@Autowired
	private AvioCompanyService avioService;
	
	
    @MockBean
    private AvioCompanyRepository avioRepository;

    @MockBean
    private AvioAdminRepository adminRepository;
    
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
	    Mockito.when(avioRepository.findById(company.getId())).thenReturn(Optional.of(company));
	    
	    //Find all
	    AvioCompany company1 = new AvioCompany();
	    company1.setId(1L);
	    AvioCompany company2 = new AvioCompany();
	    company2.setId(2L);
	    AvioCompany company3 = new AvioCompany();
	    company3.setId(3L);
	    List<AvioCompany> companies = new ArrayList<>();
	    companies.add(company1);
	    companies.add(company2);
	    companies.add(company3);
	    
	    Mockito.when(avioRepository.findAll()).thenReturn(companies);
	    
	    //Find company from admin
	    AvioAdmin admin = new AvioAdmin("Admin", "Admin", "admin", "admin", "admin@admin.com", "ada", "adresa", AuthorityEnum.AVIOADMIN);
	    admin.setAvioCompany(company);
	    
	    Mockito.when(adminRepository.findByUsername("admin")).thenReturn(admin);
	    //Get flight
	    
	    Flight flight = new Flight(10.0, 5, company);
	    
	    Mockito.when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
	    Mockito.when(flightRepository.findById(2L)).thenReturn(null);
	    
	    
	    
	    
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllCompanies() {
		
		List<AvioCompany> avios = avioService.getAllCompanies();
		assertNotNull(avios);
		assertEquals(3, avios.size());
		
	}

	@Test
	public void testGetFlight() {
		
		Flight flight = avioService.getFlight(1L);
		assertNotNull(flight);
		assertEquals(5, flight.getRateCount());
		
	}

	@Test
	public void testGetAvioCompanyfromAdmin() {
		
		UserLoginDTO user = new UserLoginDTO();
		user.setUsername("admin");
		AvioCompany avio = avioService.getAvioCompanyfromAdmin(user);
		assertNotNull(avio);
		assertEquals("Avio kompanija", avio.getName());
		
	}

}
