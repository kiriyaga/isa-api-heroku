package isa_api.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
import isa_api.dto.ResponseMessage;
import isa_api.dto.SearchedFlightsDTO;
import isa_api.dto.UserLoginDTO;
import isa_api.services.AvioCompanyService;

@Controller
@CrossOrigin(allowedHeaders = { "Token-Authority",
		"Content-Type" }, origins = "http://localhost:4200", allowCredentials = "true")
public class AvioCompanyController {

	@Autowired
	AvioCompanyService avioCompanyService;

	@RequestMapping(method = RequestMethod.GET, value = "public/avio/getCompany/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCompany(@PathVariable Long id) {

		AvioCompany avioCompany = avioCompanyService.getAvioCompany(id);

		if (avioCompany == null) {
			new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(avioCompany, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "public/avio/SearchForFlights/{type}/{num}/{classs}/{from}/{to}/{takeoff}/{landing}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchForFlights(@PathVariable String type, @PathVariable int num,
			@PathVariable SeatClassEnum classs, @PathVariable String from, @PathVariable String to,
			@PathVariable Date takeoff, @PathVariable Date landing) {
		List<SearchedFlightsDTO> searchedFlights = avioCompanyService.searchForFlights(type, num, classs, from, to,
				takeoff, landing);
		return new ResponseEntity<Object>(searchedFlights, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "public/avio/ConfirmFlight/{answer}/{seat}/{flight}/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> confirmFlight(@PathVariable String answer, @PathVariable Long seat,
			@PathVariable Long flight, @PathVariable String token) {

		Boolean data = avioCompanyService.confirmFlight(answer, seat, flight, token);

		if (data == false) {
			return new ResponseEntity<Object>(new ResponseMessage("Verification link is not valid!"),
					HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Object>(new ResponseMessage("Your answer is sent!"), HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "public/avio/CheckConfirm/{seat}/{flight}/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> checkConfirm(@PathVariable Long seat, @PathVariable Long flight,
			@PathVariable String token) {

		String data = avioCompanyService.checkConfirm(seat, flight, token);
		if (data == null) {
			return new ResponseEntity<Object>(new ResponseMessage("Verification link is not valid!"),
					HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Object>(new ResponseMessage(data), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "public/avio/getCompanies", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCompanies() {

		List<AvioCompany> a = avioCompanyService.getAllCompanies();
		return new ResponseEntity<Object>(a, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "public/avio/getFlight/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getFlight(@PathVariable Long id) {

		Flight flight = avioCompanyService.getFlight(id);

		if (flight == null) {
			new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(flight, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "auth/avio/GetGraph/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> getCount(@PathVariable Long id) {

		Double[] data = avioCompanyService.getCompanyGraph(id);
		if (data == null) {
			new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(data, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/GetEarnings", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> getEarnings(@RequestBody EarningsDTO earnings) {
		Double data = avioCompanyService.getCompanyEarnings(earnings);
		if (data == null) {
			new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(data, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/getCompanyFromUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> getCopmanyfromAdmin(@RequestBody UserLoginDTO user) {
		
		AvioCompany avioCompany = avioCompanyService.getAvioCompanyfromAdmin(user);
		return new ResponseEntity<Object>(avioCompany, HttpStatus.OK);
		
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/editBasic", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> editBasic(@RequestBody BasicAvioCompanyDTO basic) {

		AvioCompany data = avioCompanyService.editCompany(basic);
		if (data == null) {
			new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(data, HttpStatus.OK);
	}
	

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/EditDestination", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> addAndEditDestination(@RequestBody BasicDestinationDTO destination) {

		AvioCompany data = avioCompanyService.addAndEditDestination(destination);
		if (data == null) {
			new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(data, HttpStatus.OK);
		
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/AddDestination", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> addDestination(@RequestBody BasicDestinationDTO destination) {
		AvioCompany data= avioCompanyService.addDestination(destination);
		if (data == null) {
			new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(data, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/DeleteDestination", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> deleteDestination(@RequestBody BasicDestinationDTO destination) {
		
		AvioCompany data = avioCompanyService.deleteDestination(destination);
		if (data == null) {
			new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(data, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "public/avio/ReserveFlight", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> reserveFlight(@RequestBody AvioOrderDTO order) {
		try {
		String data =  avioCompanyService.reserveFlight(order);
		return new ResponseEntity<Object>(new ResponseMessage(data), HttpStatus.OK);
		
		}catch (Exception e) {
			return new ResponseEntity<Object>(new ResponseMessage("Can not reserve flight!"), HttpStatus.NOT_FOUND);
		}
		
		
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/AddFlight", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> addFlight(@Valid @RequestBody FlightAvioCompanyDTO flight) {
		AvioCompany data = avioCompanyService.addFlight(flight);
		
		if (data == null) {
			new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(data, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/AddFlightStop", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> addFlightStop(@RequestBody FlightStopDTO flight) {
		AvioCompany data = avioCompanyService.addFlightStop(flight);
		if (data == null) {
			new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(data, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/DeleteFlightStop", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> deleteFlightStop(@RequestBody FlightStopDTO flight) {
		AvioCompany data = avioCompanyService.deleteFlightStop(flight);
		if (data == null) {
			new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(data, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/EditFlight", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> editFlight(@RequestBody FlightAvioCompanyDTO flight) {
		AvioCompany data = avioCompanyService.editFlight(flight);
		if (data == null) {
			new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(data, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/DeleteFlight", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> deleteFlight(@RequestBody DeleteFlightDTO flight) {
		AvioCompany data  = avioCompanyService.deleteFlight(flight);
		
		if (data == null) {
			new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(data, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/getFlightFromAdmin", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> getFlightFromAdmin(@RequestBody FlightFromUserDTO flight) {
		Flight data = avioCompanyService.getFlightFromUser(flight);
		if (data == null) {
			new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(data, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/SaveSeats", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> saveSeats(@RequestBody FlightSeatsDTO flight) {
		Flight data = avioCompanyService.saveSeats(flight);
	
		return new ResponseEntity<Object>(data, HttpStatus.OK);
	}

}
