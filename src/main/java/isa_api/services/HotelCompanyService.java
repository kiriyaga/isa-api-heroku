package isa_api.services;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import isa_api.dto.BasicDestinationDTO;
import isa_api.dto.BasicHotelCompanyDTO;
import isa_api.dto.HotelAdditionalServiceDTO;
import isa_api.dto.RoomHotelCompanyDTO;
import isa_api.dto.RoomReservationDTO;
import isa_api.dto.UserLoginDTO;

public interface HotelCompanyService {

	ResponseEntity<Object> getHotelCompany(Long id);

	ResponseEntity<Object> getHotelCompanyfromAdmin(UserLoginDTO user);

	ResponseEntity<Object> editCompany(BasicHotelCompanyDTO basic);

	ResponseEntity<Object> addAndEditLocation(BasicDestinationDTO basic);

	ResponseEntity<Object> addRoom(RoomHotelCompanyDTO room);

	ResponseEntity<Object> editRoom(RoomHotelCompanyDTO room);

	ResponseEntity<Object> deleteRoom(RoomHotelCompanyDTO room);

	ResponseEntity<Object> updateAdditionalServices(List<HotelAdditionalServiceDTO> additionalServices, long hotelId);

	ResponseEntity<Object> getAllCompanies();

	ResponseEntity<Object> getCompaniesByParameters(String hotelName, String hotelLocation, Date checkInDate,
			Date checkOutDate);

	ResponseEntity<Object> checkPriceRang(Date checkInDate);

	ResponseEntity<Object> makeReservation(RoomReservationDTO rrdto);
}
