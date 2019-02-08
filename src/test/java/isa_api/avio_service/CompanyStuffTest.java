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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import isa_api.beans.flight.AvioCompany;
import isa_api.beans.flight.Flight;
import isa_api.dao.AvioCompanyRepository;
import isa_api.dto.EarningsDTO;
import isa_api.services.AvioCompanyService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CompanyStuffTest {
	
	@Autowired
	private AvioCompanyService avioService;
	
	@MockBean
	private AvioCompanyRepository avioRepository;
	

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

		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCompanyGraph() {
		
		Double[] count = avioService.getCompanyGraph(1L);
		assertEquals(0.0, count[0].doubleValue(),0);
		assertEquals(0.0, count[1].doubleValue(),0);
		assertEquals(0.0, count[2].doubleValue(),0);
		
		
	}

	@Test
	public void testGetCompanyEarnings() {
		EarningsDTO e = new EarningsDTO();
		e.setEarnings1(new Date());
		e.setEarnings2(new Date());
		e.setCompany(1L);
		Double earnings = avioService.getCompanyEarnings(e);
		assertEquals(0.0, earnings.doubleValue(),0);
	}

}
