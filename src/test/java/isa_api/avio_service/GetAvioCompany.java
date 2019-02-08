package isa_api.avio_service;

import static org.assertj.core.api.Assertions.assertThat;

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
import isa_api.dao.AvioCompanyRepository;
import isa_api.services.AvioCompanyService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class GetAvioCompany {
	
	
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
	    
	    
	    
	    Mockito.when(avioRepository.findById(company.getId())).thenReturn(Optional.of(company));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAvioCompany() {
		
		AvioCompany result=  avioService.getAvioCompany(1L);
	    String name = "Avio kompanija";
	    
	     assertThat(result.getName())
	      .isEqualTo(name);
	}
	

}
