package isa_api.services;

import java.util.Date;

import org.springframework.http.ResponseEntity;

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
import isa_api.dto.UserLoginDTO;

public interface AvioCompanyService {

	ResponseEntity<Object> getAvioCompany(Long id);
	
	ResponseEntity<Object> getFlight(Long id);

	ResponseEntity<Object> getAvioCompanyfromAdmin(UserLoginDTO user);

	ResponseEntity<Object> editCompany(BasicAvioCompanyDTO user);
	
	ResponseEntity<Object> addAndEditDestination(BasicDestinationDTO basic);
	
	ResponseEntity<Object> addDestination(BasicDestinationDTO basic);

	ResponseEntity<Object> deleteDestination(BasicDestinationDTO basic);
	
	ResponseEntity<Object> addFlight(FlightAvioCompanyDTO flight);
	
	ResponseEntity<Object> addFlightStop(FlightStopDTO flight);
	
	ResponseEntity<Object> deleteFlightStop(FlightStopDTO flight);
	
	ResponseEntity<Object> editFlight(FlightAvioCompanyDTO flight);
	
	ResponseEntity<Object> deleteFlight(DeleteFlightDTO flight);
	
	ResponseEntity<Object> getFlightFromUser(FlightFromUserDTO info);
	
	ResponseEntity<Object> saveSeats(FlightSeatsDTO data);
	
	ResponseEntity<Object> getAllCompanies();
	
	ResponseEntity<Object> reserveFlight(AvioOrderDTO order);
	
	ResponseEntity<Object> confirmFlight(String answer,Long seat,Long flight,String token);
	
	ResponseEntity<Object> checkConfirm(Long seat,Long flight,String token);
	
	ResponseEntity<Object> searchForFlights(String type,int num,SeatClassEnum classs,String from, String to,Date takeoff,Date landing);
	
	ResponseEntity<Object> getCompanyGraph(Long id);
	
	ResponseEntity<Object> getCompanyEarnings(EarningsDTO earnings);
	
}
