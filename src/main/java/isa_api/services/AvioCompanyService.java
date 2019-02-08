package isa_api.services;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import isa_api.beans.flight.AvioCompany;
import isa_api.beans.flight.Flight;
import isa_api.beans.flight.SeatClassEnum;
import isa_api.dto.AvioOrderDTO;
import isa_api.dto.BasicAvioCompanyDTO;
import isa_api.dto.BasicDestinationDTO;
import isa_api.dto.DeleteFlightDTO;
import isa_api.dto.EarningsDTO;
import isa_api.dto.FlightAvioCompanyDTO;
import isa_api.dto.FlightFromUserDTO;
import isa_api.dto.FlightSeatsDTO;
import isa_api.dto.FlightStopDTO;
import isa_api.dto.SearchedFlightsDTO;
import isa_api.dto.UserLoginDTO;

public interface AvioCompanyService {

	AvioCompany getAvioCompany(Long id);
	
	Flight getFlight(Long id);

	AvioCompany getAvioCompanyfromAdmin(UserLoginDTO user);

	AvioCompany editCompany(BasicAvioCompanyDTO user);
	
	AvioCompany addAndEditDestination(BasicDestinationDTO basic);
	
	AvioCompany addDestination(BasicDestinationDTO basic);

	AvioCompany deleteDestination(BasicDestinationDTO basic);
	
	AvioCompany addFlight(FlightAvioCompanyDTO flight);
	
	AvioCompany addFlightStop(FlightStopDTO flight);
	
	AvioCompany deleteFlightStop(FlightStopDTO flight);
	
	AvioCompany editFlight(FlightAvioCompanyDTO flight);
	
	AvioCompany deleteFlight(DeleteFlightDTO flight);
	
	Flight getFlightFromUser(FlightFromUserDTO info);
	
	Flight saveSeats(FlightSeatsDTO data);
	
	List<AvioCompany> getAllCompanies();
	
	String reserveFlight(AvioOrderDTO order) throws Exception;
	
	Boolean confirmFlight(String answer,Long seat,Long flight,String token);
	
	String checkConfirm(Long seat,Long flight,String token);
	
	List<SearchedFlightsDTO> searchForFlights(String type,int num,SeatClassEnum classs,String from, String to,Date takeoff,Date landing);
	
	Double[] getCompanyGraph(Long id);
	
	Double getCompanyEarnings(EarningsDTO earnings);
	
}
