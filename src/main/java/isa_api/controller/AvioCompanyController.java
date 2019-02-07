package isa_api.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
import isa_api.services.AvioCompanyService;

@Controller
@CrossOrigin(allowedHeaders = { "Token-Authority",
		"Content-Type" }, origins = "http://localhost:4200", allowCredentials = "true")
public class AvioCompanyController {

	@Autowired
	AvioCompanyService avioCompanyService;

	@RequestMapping(method = RequestMethod.GET, value = "public/avio/getCompany/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCompany(@PathVariable Long id) {

		return avioCompanyService.getAvioCompany(id);

	}

	@RequestMapping(method = RequestMethod.GET, value = "public/avio/SearchForFlights/{type}/{num}/{classs}/{from}/{to}/{takeoff}/{landing}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchForFlights(@PathVariable String type, @PathVariable int num,
			@PathVariable SeatClassEnum classs, @PathVariable String from, @PathVariable String to,
			@PathVariable Date takeoff, @PathVariable Date landing) {
		return avioCompanyService.searchForFlights(type, num, classs, from, to, takeoff, landing);
	}

	@RequestMapping(method = RequestMethod.GET, value = "public/avio/ConfirmFlight/{answer}/{seat}/{flight}/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> confirmFlight(@PathVariable String answer, @PathVariable Long seat,
			@PathVariable Long flight, @PathVariable String token) {
		return avioCompanyService.confirmFlight(answer, seat, flight, token);
	}

	@RequestMapping(method = RequestMethod.GET, value = "public/avio/CheckConfirm/{seat}/{flight}/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> checkConfirm(@PathVariable Long seat, @PathVariable Long flight,
			@PathVariable String token) {
		return avioCompanyService.checkConfirm(seat, flight, token);
	}

	@RequestMapping(method = RequestMethod.GET, value = "public/avio/getCompanies", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCompanies() {

		return avioCompanyService.getAllCompanies();

	}

	@RequestMapping(method = RequestMethod.GET, value = "public/avio/getFlight/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getFlight(@PathVariable Long id) {

		return avioCompanyService.getFlight(id);

	}

	@RequestMapping(method = RequestMethod.GET, value = "auth/avio/GetGraph/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> getCount(@PathVariable Long id) {

		return avioCompanyService.getCompanyGraph(id);

	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/GetEarnings", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> getEarnings(@RequestBody EarningsDTO earnings) {
		return avioCompanyService.getCompanyEarnings(earnings);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/getCompanyFromUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> getCopmanyfromAdmin(@RequestBody UserLoginDTO user) {
		return avioCompanyService.getAvioCompanyfromAdmin(user);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/editBasic", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> editBasic(@RequestBody BasicAvioCompanyDTO basic) {

		return avioCompanyService.editCompany(basic);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/EditDestination", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> addAndEditDestination(@RequestBody BasicDestinationDTO destination) {
		return avioCompanyService.addAndEditDestination(destination);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/AddDestination", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> addDestination(@RequestBody BasicDestinationDTO destination) {
		return avioCompanyService.addDestination(destination);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/DeleteDestination", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> deleteDestination(@RequestBody BasicDestinationDTO destination) {
		return avioCompanyService.deleteDestination(destination);
	}

	@RequestMapping(method = RequestMethod.POST, value = "public/avio/ReserveFlight", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> reserveFlight(@RequestBody AvioOrderDTO order) {
		return avioCompanyService.reserveFlight(order);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/AddFlight", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> addFlight(@Valid @RequestBody FlightAvioCompanyDTO flight) {
		return avioCompanyService.addFlight(flight);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/AddFlightStop", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> addFlightStop(@RequestBody FlightStopDTO flight) {
		return avioCompanyService.addFlightStop(flight);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/DeleteFlightStop", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> deleteFlightStop(@RequestBody FlightStopDTO flight) {
		return avioCompanyService.deleteFlightStop(flight);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/EditFlight", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> editFlight(@RequestBody FlightAvioCompanyDTO flight) {
		return avioCompanyService.editFlight(flight);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/DeleteFlight", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> deleteFlight(@RequestBody DeleteFlightDTO flight) {
		return avioCompanyService.deleteFlight(flight);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/getFlightFromAdmin", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> getFlightFromAdmin(@RequestBody FlightFromUserDTO flight) {
		return avioCompanyService.getFlightFromUser(flight);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/avio/SaveSeats", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('AVIOADMIN')")
	public ResponseEntity<?> saveSeats(@RequestBody FlightSeatsDTO flight) {
		return avioCompanyService.saveSeats(flight);
	}

}
