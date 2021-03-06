package isa_api.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import isa_api.beans.Location;
import isa_api.beans.hotel.HotelAdditionalService;
import isa_api.beans.hotel.HotelAdditionalServiceEnum;
import isa_api.beans.hotel.HotelCompany;
import isa_api.beans.hotel.HotelFastReservationOffer;
import isa_api.beans.hotel.Room;
import isa_api.beans.hotel.RoomReservation;
import isa_api.beans.users.HotelAdmin;
import isa_api.beans.users.RegistredUser;
import isa_api.dao.HotelAdditionalServiceRepository;
import isa_api.dao.HotelAdminRepository;
import isa_api.dao.HotelCompanyRepository;
import isa_api.dao.HotelFastReservationOfferRepository;
import isa_api.dao.LocationRepository;
import isa_api.dao.RegistredUserRepository;
import isa_api.dao.RoomRepository;
import isa_api.dao.RoomReservationRepository;
import isa_api.dto.BasicDestinationDTO;
import isa_api.dto.BasicHotelCompanyDTO;
import isa_api.dto.HotelAdditionalServiceDTO;
import isa_api.dto.HotelFastReservationRoomDTO;
import isa_api.dto.HotelFastReserveOfferToReservationDTO;
import isa_api.dto.ResponseMessage;
import isa_api.dto.RoomHotelCompanyDTO;
import isa_api.dto.RoomReservationDTO;
import isa_api.dto.SearchedRoomsDTO;
import isa_api.dto.UserLoginDTO;

@Service
public class HotelCompanyServiceCon implements HotelCompanyService {

	@Autowired
	HotelCompanyRepository hotelRepository;

	@Autowired
	HotelAdminRepository hotelAdminRepository;

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	LocationRepository locationRepository;

	@Autowired
	HotelAdditionalServiceRepository hasRepository;

	@Autowired
	RoomReservationRepository rrRepository;

	@Autowired
	RegistredUserRepository regUserRepository;
	
	@Autowired
	HotelFastReservationOfferRepository hfroRepository;

	@Override
	public ResponseEntity<Object> getHotelCompany(Long id) {

		Optional<HotelCompany> hotel = hotelRepository.findById(id);
		if (!hotel.isPresent()) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Object>(hotel.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getHotelCompanyfromAdmin(UserLoginDTO user) {

		HotelAdmin hotelAdmin = hotelAdminRepository.findByUsername(user.getUsername());

		return new ResponseEntity<Object>(hotelAdmin.getHotelCompany(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> editCompany(BasicHotelCompanyDTO basic) {

		Optional<HotelCompany> hotel = hotelRepository.findById(basic.getId());
		if (!hotel.isPresent()) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}

		hotel.get().setName(basic.getName());
		hotel.get().setPromtiveDescription(basic.getPromotiveDescription());

		hotelRepository.saveAndFlush(hotel.get());

		return new ResponseEntity<Object>(hotel, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> addAndEditLocation(BasicDestinationDTO basic) {

		Optional<HotelCompany> hotel = hotelRepository.findById(basic.getCompany());
		if (!hotel.isPresent()) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}

		Optional<Location> editLocation = locationRepository.findById(hotel.get().getLocation().getId());
		editLocation.get().setAdress(basic.getLocation().getAdress());
		editLocation.get().setCode(basic.getLocation().getCode());
		editLocation.get().setLat(basic.getLocation().getLat());
		editLocation.get().setLng(basic.getLocation().getLng());

		locationRepository.saveAndFlush(editLocation.get());

		return new ResponseEntity<Object>(hotel.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> addRoom(RoomHotelCompanyDTO room) {

		Optional<HotelCompany> hotel = hotelRepository.findById(room.getHotelCompany().getId());
		if (!hotel.isPresent()) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}

		Room newRoom = new Room(room.getFloor(), room.getNumber(), room.getNumberOfBeds(), room.getNextMonthPrice(),
				room.getNextThreeMonthPrice(), room.getNextHalfYearPrice(), room.getHotelCompany(),
				room.isFastReserve());
		newRoom.setRate(0.0);
		newRoom.setRateCount(0);
		newRoom.setActive(true);

		hotel.get().addRooms(newRoom);

		hotelRepository.saveAndFlush(hotel.get());

		return new ResponseEntity<Object>(hotel.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> editRoom(RoomHotelCompanyDTO room) {

		Optional<HotelCompany> hotel = hotelRepository.findById(room.getHotelCompany().getId());
		if (!hotel.isPresent()) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}

		Optional<Room> editRoom = roomRepository.findById(room.getId());
		editRoom.get().setFloor(room.getFloor());
		editRoom.get().setNumber(room.getNumber());
		editRoom.get().setNumberOfBeds(room.getNumberOfBeds());
		editRoom.get().setNextMonthPrice(room.getNextMonthPrice());
		editRoom.get().setNextThreeMonthPrice(room.getNextThreeMonthPrice());
		editRoom.get().setNextHalfYearPrice(room.getNextHalfYearPrice());
		editRoom.get().setFastReserve(room.isFastReserve());

		roomRepository.saveAndFlush(editRoom.get());

		return new ResponseEntity<Object>(editRoom.get().getHotelCompany(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> deleteRoom(RoomHotelCompanyDTO room) {

		Optional<HotelCompany> hotel = hotelRepository.findById(room.getHotelCompany().getId());
		if (!hotel.isPresent())
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);

		Optional<Room> editRoom = roomRepository.findById(room.getId());
		editRoom.get().setActive(false);

		roomRepository.saveAndFlush(editRoom.get());

		for (Room r : hotel.get().getRooms()) {
			if (r.getId().equals(editRoom.get().getId())) {
				hotel.get().getRooms().remove(r);
				break;
			}
		}

		hotelRepository.saveAndFlush(hotel.get());

		return new ResponseEntity<Object>(hotel.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> updateAdditionalServices(List<HotelAdditionalServiceDTO> additionalServices,
			long hotelId) {

		for (HotelAdditionalServiceDTO hasdto : additionalServices) {
			System.out.println(hasdto.getAdditionalServiceType());
		}

		Optional<HotelCompany> hotel = hotelRepository.findById(hotelId);
		if (!hotel.isPresent()) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}

		for (HotelAdditionalService has : hotel.get().getAdditionalServices()) {
			has.setActive(false);
		}

		if (additionalServices.isEmpty()) {
			System.out.println("lista je prazna i nema servisa");
			hotel.get().removeAllAdditionalServices();
			hotelRepository.saveAndFlush(hotel.get());

			return new ResponseEntity<Object>(hotel.get(), HttpStatus.OK);
		}

		HashMap<HotelAdditionalServiceEnum, Long> currentServices = new HashMap<HotelAdditionalServiceEnum, Long>();
		for (HotelAdditionalService currentHas : hotel.get().additionalServices) {
			currentServices.put(currentHas.getAdditionalServiceType(), currentHas.getId());
			System.out.println(currentHas.getAdditionalServiceType() + " = "
					+ currentServices.get(currentHas.getAdditionalServiceType()));
		}

		hotel.get().removeAllAdditionalServices();

		if (hotel.get().additionalServices.isEmpty()) {
			System.out.println("ocekujem da je lista trenutno prazna pre pocetka punjenja");
		}

		for (HotelAdditionalServiceDTO hasdto : additionalServices) {
			System.out.println(hasdto.getAdditionalServiceType());
			if (currentServices.containsKey(hasdto.getAdditionalServiceType())) {
				System.out.println("vec postoji: " + hasdto.getAdditionalServiceType());
				Optional<HotelAdditionalService> editAdditionalService = hasRepository
						.findById(currentServices.get(hasdto.getAdditionalServiceType()));
				editAdditionalService.get().setAdditionalServiceType(hasdto.getAdditionalServiceType());
				editAdditionalService.get().setPrice(hasdto.getPrice());
				editAdditionalService.get().setHotelCompany(hasdto.getHotelCompany());
				editAdditionalService.get().setActive(true);

				hotel.get().addAdditionalServices(editAdditionalService.get());
				hasRepository.saveAndFlush(editAdditionalService.get());
			} else {
				System.out.println("parvi se novi jer ga nema: " + hasdto.getAdditionalServiceType());
				HotelAdditionalService newAdditionalService = new HotelAdditionalService();
				newAdditionalService.setAdditionalServiceType(hasdto.getAdditionalServiceType());
				newAdditionalService.setHotelCompany(hotel.get());
				newAdditionalService.setPrice(hasdto.getPrice());
				newAdditionalService.setActive(true);

				hotel.get().addAdditionalServices(newAdditionalService);
				hasRepository.saveAndFlush(newAdditionalService);
			}
		}

		for (HotelAdditionalService has : hotel.get().getAdditionalServices()) {
			System.out.println(has.getAdditionalServiceType());
		}

		hotelRepository.saveAndFlush(hotel.get());

		return new ResponseEntity<Object>(hotel.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getAllCompanies() {

		List<HotelCompany> allComp = hotelRepository.findAll();

		return new ResponseEntity<Object>(allComp, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getCompaniesByParameters(String hotelName, String hotelLocation, Date checkInDate,
			Date checkOutDate) {

		boolean dateIsOK = false;
		boolean hotelNameIsOK = false;
		boolean hotelLocationIsOK = false;

		List<Room> rooms = roomRepository.findAll();
		List<SearchedRoomsDTO> searchedRooms = new ArrayList<>();

		Calendar calCID = Calendar.getInstance();
		Calendar calCOD = Calendar.getInstance();

		calCID.setTime(checkInDate);
		calCOD.setTime(checkOutDate);
		
		System.out.println("--------------------------------------------");
		System.out.println(checkInDate);
		System.out.println(checkOutDate);

		if (calCOD.before(calCID)) {
			return new ResponseEntity<Object>(searchedRooms, HttpStatus.OK);
		}

		for (Room r : rooms) {
			List<RoomReservation> roomReservations = r.getRoomReservation();

			dateIsOK = false;
			hotelNameIsOK = false;
			hotelLocationIsOK = false;

			// provera da li je uopste unet datum
			if (calCID.equals(calCOD)) {
				System.out.println("nisi dirao datum ili si podesio da su isti");
				dateIsOK = true;
			} 
			
			if (roomReservations.isEmpty()) {
				System.out.println("datum je okej kako god jer nema rezervacija za ovu sobu");
				dateIsOK = true;
			} else {
				// ako jeste, onda videti da l za sobu postoje rezervacije u taj period
				for (RoomReservation rr : roomReservations) {
					
					Calendar resCID = Calendar.getInstance();
					Calendar resCOD = Calendar.getInstance();
					
					resCID.setTime(rr.getCheckIn());
					resCOD.setTime(rr.getCheckOut());
					
					System.out.println();
					
					if (calCID.after(resCOD)) {
						dateIsOK = true;
						System.out.println("check in je posle reservacije");
					} else if (calCOD.before(resCID)) {
						dateIsOK = true;
						System.out.println("check out je pre reservacije");
					} else {
						dateIsOK = false;
						System.out.println("nesto trece - jebem li ga");
						break;
					}
				}
			}

			System.out.println("prosao sam provere za datum");
			System.out.println("dateIsOK: " + dateIsOK);
			System.out.println("hotelNameIsOK: " + hotelNameIsOK);
			System.out.println("hotelLocationIsOK: " + hotelLocationIsOK);
			if (!dateIsOK) {
				continue;
			}

			System.out.println("provera naziva hotela");
			System.out.println("naziv hotela je: " + hotelName);
			if (("null").equals(hotelName)) {
				System.out.println("hotelName je null");
				hotelNameIsOK = true;
			} else {
				String roomCompanyName = r.getHotelCompany().getName().toLowerCase();
				if (roomCompanyName.contains(hotelName.toLowerCase())) {
					System.out.println(roomCompanyName + " contains " + hotelName.toLowerCase());
					hotelNameIsOK = true;
				}
			}

			System.out.println("prosao sam provere za naziv");
			System.out.println("hotelNameIsOK: " + hotelNameIsOK);
			System.out.println("hotelLocationIsOK: " + hotelLocationIsOK);
			if (!hotelNameIsOK) {
				continue;
			}

			if (("null").equals(hotelLocation)) {
				System.out.println("hotelLocation je null");
				hotelLocationIsOK = true;
			} else {
				String roomCompanyLocation = r.getHotelCompany().getLocation().getAdress().toLowerCase();
				if (roomCompanyLocation.contains(hotelLocation.toLowerCase())) {
					hotelLocationIsOK = true;
				}
			}

			if (!hotelLocationIsOK) {
				continue;
			}

			SearchedRoomsDTO srdto = new SearchedRoomsDTO(r.getHotelCompany(), r);

			if (!srdto.getRoom().isFastReserve()) {
				searchedRooms.add(srdto);
			}
		}

		return new ResponseEntity<Object>(searchedRooms, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> checkPriceRang(Date checkInDate) {

		int priceRang = 0;

		Calendar checkInDatew = Calendar.getInstance();
		checkInDatew.setTime(checkInDate);

		Date nowTime = new Date();
		Calendar now = Calendar.getInstance();
		now.setTime(nowTime);

		long start = now.getTimeInMillis();
		long end = checkInDatew.getTimeInMillis();
		long diff = TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));

		if (diff <= 31.0) {
			priceRang = 1;
		} else if (diff <= 92.0) {
			priceRang = 2;
		} else {
			priceRang = 3;
		}

		System.out.println(priceRang);

		return new ResponseEntity<Object>(priceRang, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> numberOfDays(Date checkInDate, Date checkOutDate) {

		Calendar cStart = Calendar.getInstance();
		cStart.setTime(checkInDate);

		Calendar cEnd = Calendar.getInstance();
		cEnd.setTime(checkOutDate);

		long start = cStart.getTimeInMillis();
		long end = cEnd.getTimeInMillis();
		long diff = TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));

		System.out.println(diff);

		return new ResponseEntity<Object>(diff, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> makeReservation(RoomReservationDTO rrdto) {

		System.out.println("------------------------------------");
		System.out.println(rrdto.getAdditionalServices().size());
		System.out.println(rrdto.getRoom());
		System.out.println(rrdto.getCheckInDate());
		System.out.println(rrdto.getCheckOutDate());
		System.out.println("------------------------------------");

		Optional<Room> editRoom = roomRepository.findById(rrdto.getRoom().getId());

		RegistredUser owner = null;
		if (rrdto.getOwner() != null) {
			owner = regUserRepository.findByUsername(rrdto.getOwner().getUsername());
		}
		
		Calendar calCID = Calendar.getInstance();
		Calendar calCOD = Calendar.getInstance();

		calCID.setTime(rrdto.getCheckInDate());
		calCOD.setTime(rrdto.getCheckOutDate());
		
		if (calCOD.before(calCID)) {
			return new ResponseEntity<Object>(new ResponseMessage("Check out can't be before check in!"),
					HttpStatus.OK);
		}
		
		boolean dateIsOK = false;
		
		List<RoomReservation> roomReservations = editRoom.get().getRoomReservation();
		
		if (roomReservations.isEmpty()) {
			dateIsOK = true;
		} else {
			for (RoomReservation rr : roomReservations) {
				
				Calendar resCID = Calendar.getInstance();
				Calendar resCOD = Calendar.getInstance();
				
				resCID.setTime(rr.getCheckIn());
				resCOD.setTime(rr.getCheckOut());
				
				if (calCID.after(resCOD)) {
					dateIsOK = true;
				} else if (calCOD.before(resCID)) {
					dateIsOK = true;
				} else {
					dateIsOK = false;
					break;
				}
			}
		}
		
		if (!dateIsOK) {
			return new ResponseEntity<Object>(new ResponseMessage("Room is reserved in that period!"),
					HttpStatus.OK);
		}

		// kreiramo rezervaciju
		RoomReservation newRoomReservation = new RoomReservation(owner);
		newRoomReservation.setRoom(rrdto.getRoom());
		newRoomReservation.setCheckIn(rrdto.getCheckInDate());
		newRoomReservation.setCheckOut(rrdto.getCheckInDate());
		
		for (HotelAdditionalService add : rrdto.getAdditionalServices()) {
			Optional<HotelAdditionalService> temp = hasRepository.findById(add.getId());
			newRoomReservation.getAdditionalServices().add(temp.get());
		}

		newRoomReservation.setDiscountAndPrice(newRoomReservation.getRoom(), newRoomReservation.getCheckIn(), newRoomReservation.getAdditionalServices());

		editRoom.get().getRoomReservation().add(newRoomReservation);
		roomRepository.saveAndFlush(editRoom.get());

		// syso
		System.out.println("da li uspes da save and flush");

		return new ResponseEntity<Object>(new ResponseMessage("Success! Reservation has moved to cart."),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> addHotelFastReservation(HotelFastReservationOffer hfro) {

		Calendar resCID = Calendar.getInstance();
		Calendar resCOD = Calendar.getInstance();
		
		resCID.setTime(hfro.getCheckInDate());
		resCOD.setTime(hfro.getCheckOutDate());
		
		if (resCID.after(resCOD)) {
			return new ResponseEntity<Object>(new ResponseMessage("Check out can't be before check in!"),
					HttpStatus.OK);
		}
		
		HotelFastReservationOffer newOffer = new HotelFastReservationOffer(hfro.getCheckInDate(), hfro.getCheckOutDate(), hfro.getRoom());
		
		for (HotelAdditionalService add : hfro.getAdditionalServices()) {
			Optional<HotelAdditionalService> temp = hasRepository.findById(add.getId());
			newOffer.getAdditionalServices().add(temp.get());
		}
		
		newOffer.setDiscountAndPrice(newOffer.getRoom(), newOffer.getCheckInDate(), newOffer.getAdditionalServices());
		
		System.out.println(newOffer.getPrice());
		
		hfroRepository.save(newOffer);
		
		System.out.println(hfroRepository.findAll().size());
		
		return new ResponseEntity<Object>(new ResponseMessage("Success! Fast reservation is active now."),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getFastResevationsForCompany(long id) {
		
		List<HotelFastReservationRoomDTO> companyFastOffers = new ArrayList<>();
		
		System.out.println("id je: " + id);		
		
		List<HotelFastReservationOffer> allFastOffers = hfroRepository.findAll();
		
		System.out.println(allFastOffers.size());
		
		for(HotelFastReservationOffer temp : allFastOffers) {
			if(temp.getRoom().getHotelCompany().getId() == id && temp.isActive()) {
				System.out.println("uso");
				HotelFastReservationRoomDTO hfrrdto = new HotelFastReservationRoomDTO();
				hfrrdto.setRoom(temp.getRoom());
				hfrrdto.setHfro(temp);
				companyFastOffers.add(hfrrdto);
			}
		}
		
		System.out.println(companyFastOffers.size());
		
		for (int i = 0; i < companyFastOffers.size(); i++) {
			for (HotelAdditionalService ads : companyFastOffers.get(i).getHfro().getAdditionalServices()) {
				System.out.println(ads.getAdditionalServiceType());
			}
		}
		
		return new ResponseEntity<Object>(companyFastOffers, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> reserveFastOffer(HotelFastReserveOfferToReservationDTO fastRwithOwner) {

		RegistredUser regUser = regUserRepository.findByUsername(fastRwithOwner.getOwner().getUsername());
		
		System.out.println(regUser);
		
		if (regUser == null) {
			System.out.println("rezervisao je neko ko trenutno nije ulogovan");
		}
		
		Optional<HotelFastReservationOffer> reservedOffer = hfroRepository.findById(fastRwithOwner.getHfro().getId());
		
		if(!reservedOffer.isPresent()) {
			return new ResponseEntity<Object>(new ResponseMessage("Choosen offer doesn't exist."),
					HttpStatus.OK);
		}
		
		reservedOffer.get().setActive(false);
		
		RoomReservation newRoomReservation = new RoomReservation(regUser, reservedOffer.get());
		
		Optional<Room> editRoom = roomRepository.findById(reservedOffer.get().getRoom().getId());
		
		if (!editRoom.isPresent()) {
			return new ResponseEntity<Object>(new ResponseMessage("Choosen room in offer doesn't exist."),
					HttpStatus.OK);
		}
		
		editRoom.get().getRoomReservation().add(newRoomReservation);
		roomRepository.saveAndFlush(editRoom.get());
		
		return new ResponseEntity<Object>(new ResponseMessage("Success! Reservation has moved to cart."),
				HttpStatus.OK);
	}

}
