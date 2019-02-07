package isa_api.controller;

import java.util.Date;
import java.util.List;

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

import isa_api.dto.BasicDestinationDTO;
import isa_api.dto.BasicHotelCompanyDTO;
import isa_api.dto.HotelAdditionalServiceDTO;
import isa_api.dto.RoomHotelCompanyDTO;
import isa_api.dto.RoomReservationDTO;
import isa_api.dto.UserLoginDTO;
import isa_api.services.HotelCompanyService;

@Controller
@CrossOrigin(allowedHeaders = { "Token-Authority",
		"Content-Type" }, origins = "http://localhost:4200", allowCredentials = "true")
public class HotelCompanyController {

	@Autowired
	HotelCompanyService hotelCompanyService;

	@RequestMapping(method = RequestMethod.GET, value = "public/hotel/getCompany/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCompany(@PathVariable Long id) {
		return hotelCompanyService.getHotelCompany(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/hotel/getCompanyFromUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('HOTELADMIN')")
	public ResponseEntity<?> getCompanyfromAdmin(@RequestBody UserLoginDTO user) {
		return hotelCompanyService.getHotelCompanyfromAdmin(user);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/hotel/editBasic", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('HOTELADMIN')")
	public ResponseEntity<?> editBasic(@RequestBody BasicHotelCompanyDTO basic) {

		return hotelCompanyService.editCompany(basic);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/hotel/EditLocation", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('HOTELADMIN')")
	public ResponseEntity<?> addAndEditDestination(@RequestBody BasicDestinationDTO basic) {

		return hotelCompanyService.addAndEditLocation(basic);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/hotel/AddRoom", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('HOTELADMIN')")
	public ResponseEntity<?> addRoom(@RequestBody RoomHotelCompanyDTO room) {

		return hotelCompanyService.addRoom(room);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/hotel/EditRoom", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('HOTELADMIN')")
	public ResponseEntity<?> editRoom(@RequestBody RoomHotelCompanyDTO room) {

		return hotelCompanyService.editRoom(room);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/hotel/DeleteRoom", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('HOTELADMIN')")
	public ResponseEntity<?> deleteRoom(@RequestBody RoomHotelCompanyDTO room) {

		return hotelCompanyService.deleteRoom(room);
	}

	@RequestMapping(method = RequestMethod.POST, value = "auth/hotel/UpdateAdditionalServices/{hotelId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("@securityService.hasProtectedAccess('HOTELADMIN')")
	public ResponseEntity<?> updateAdditionalServices(@RequestBody List<HotelAdditionalServiceDTO> additionalServices,
			@PathVariable Long hotelId) {

		return hotelCompanyService.updateAdditionalServices(additionalServices, hotelId);
	}

	@RequestMapping(method = RequestMethod.GET, value = "public/hotel/getCompanies", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCompanies() {

		return hotelCompanyService.getAllCompanies();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/public/hotel/searchRooms/{hotelName}/{hotelLocation}/{checkInDate}/{checkOutDate}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCompaniesByParameters(@PathVariable String hotelName,
			@PathVariable String hotelLocation, @PathVariable Date checkInDate, @PathVariable Date checkOutDate) {

		return hotelCompanyService.getCompaniesByParameters(hotelName, hotelLocation, checkInDate, checkOutDate);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/public/hotel/getGraph", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getGraph(@RequestBody RoomHotelCompanyDTO room) {

		return null;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/public/hotel/checkPriceRang/{checkInDate}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> checkPriceRang(@PathVariable Date checkInDate) {

		return hotelCompanyService.checkPriceRang(checkInDate);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/public/hotel/makeReservation",
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> makeReservation(@RequestBody RoomReservationDTO rrdto) {

		return hotelCompanyService.makeReservation(rrdto);
	}

}
