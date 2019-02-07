package isa_api.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import isa_api.beans.flight.AvioCompany;
import isa_api.beans.flight.AvioCompanyUsers;
import isa_api.beans.flight.Earnings;
import isa_api.beans.flight.Flight;
import isa_api.beans.flight.FlightReservation;
import isa_api.beans.flight.FlightStop;
import isa_api.beans.flight.Seat;
import isa_api.beans.flight.SeatClassEnum;
import isa_api.beans.flight.SeatTypeEnum;
import isa_api.beans.security.SecurityUser;
import isa_api.beans.users.AvioAdmin;
import isa_api.beans.users.RegistredUser;
import isa_api.dao.AvioAdminRepository;
import isa_api.dao.AvioCompanyRepository;
import isa_api.dao.FlightRepository;
import isa_api.dao.LocationRepository;
import isa_api.dao.RegistredUserRepository;
import isa_api.dao.SeatRepository;
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
import isa_api.security.TokenUtils;

@Service
@Transactional(readOnly = true)
public class AvioCompanyServiceCon implements AvioCompanyService {

	@Autowired
	AvioCompanyRepository avioRepository;

	@Autowired
	AvioAdminRepository avioAdminRepository;

	@Autowired
	private UserDetailsService userDetailService;

	@Autowired
	FlightRepository flightRepository;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	LocationRepository locationRepository;

	@Autowired
	SeatRepository seatRepository;

	@Autowired
	RegistredUserRepository regUserRepository;

	@Autowired
	private MailService mailService;

	@Override
	public ResponseEntity<Object> getAllCompanies() {

		List<AvioCompany> allComp = avioRepository.findAll();
		return new ResponseEntity<Object>(allComp, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Object> getAvioCompany(Long id) {
		Optional<AvioCompany> avio = avioRepository.findById(id);
		if (!avio.isPresent()) {

			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Object>(avio.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getFlight(Long id) {

		Optional<Flight> avio = flightRepository.findById(id);
		if (!avio.isPresent()) {

			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Object>(avio.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getAvioCompanyfromAdmin(UserLoginDTO user) {

		AvioAdmin avioAdmin = avioAdminRepository.findByUsername(user.getUsername());

		return new ResponseEntity<Object>(avioAdmin.getAvioCompany(), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Object> editCompany(BasicAvioCompanyDTO basic) {

		Optional<AvioCompany> avio = avioRepository.findById(basic.getId());
		if (!avio.isPresent()) {

			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}

		avio.get().setName(basic.getName());
		avio.get().setPromtiveDescription(basic.getPromotiveDescription());

		avioRepository.saveAndFlush(avio.get());

		return new ResponseEntity<Object>(avio, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> addAndEditDestination(BasicDestinationDTO basic) {

		Optional<AvioCompany> avio = avioRepository.findById(basic.getCompany());
		if (!avio.isPresent()) {

			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}
		System.out.println(basic.getLocation().getId());
		locationRepository.saveAndFlush(basic.getLocation());

		return new ResponseEntity<Object>(avio, HttpStatus.OK);

	}
	
	@Override
	public ResponseEntity<Object> addDestination(BasicDestinationDTO basic) {
		Optional<AvioCompany> avio = avioRepository.findById(basic.getCompany());
		if (!avio.isPresent()) {

			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}
		avio.get().getDestinations().add(basic.getLocation());
		avioRepository.saveAndFlush(avio.get());
		return new ResponseEntity<Object>(avio, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> deleteDestination(BasicDestinationDTO basic) {
		Optional<AvioCompany> avio = avioRepository.findById(basic.getCompany());
		if (!avio.isPresent()) {

			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}
		avio.get().getDestinations().remove(basic.getLocation().getId().intValue());

		avioRepository.saveAndFlush(avio.get());
		return new ResponseEntity<Object>(avio.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> addFlight(FlightAvioCompanyDTO flight) {
		Optional<AvioCompany> avio = avioRepository.findById(flight.getCompany().getId());
		System.out.println("ewqeq");
		if (!avio.isPresent()) {

			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}
		Flight flightTemp = new Flight(0.0, 0, avio.get());
		flightTemp.setPriceForTicket(flight.getPriceForTicket());
		flightTemp.setAdditionalPriceCarryBag(flight.getAdditionalPriceCarryBag());
		flightTemp.setAdditionalPriceCheckBag(flight.getAdditionalPriceCheckBag());
		flightTemp.setStartDestination(flight.getStartDestination());
		flightTemp.setEndDestination(flight.getEndDestination());
		flightTemp.setTravelTime(flight.getTravelTime());
		flightTemp.setMaxCarryBag(flight.getMaxCarryBag());
		flightTemp.setMaxCheckBag(flight.getMaxCheckBag());
		flightTemp.setTravelDistance(flight.getTravelDistance());
		// flightTemp.setBlackList(new ArrayList<>());
		avio.get().addFlights(flightTemp);

		avioRepository.saveAndFlush(avio.get());

		return new ResponseEntity<Object>(avio.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> addFlightStop(FlightStopDTO flight) {
		Optional<AvioCompany> avio = avioRepository.findById(flight.getCompany().getId());
		if (!avio.isPresent()) {

			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}
		for (Flight f : avio.get().flights) {
			if (f.getId().equals(flight.getFlight().getId())) {
				f.getFlightStop().add(flight.getFlightStop());
				flightRepository.saveAndFlush(f);
			}
		}

		return new ResponseEntity<Object>(avio.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> editFlight(FlightAvioCompanyDTO flight) {

		Optional<AvioCompany> avio = avioRepository.findById(flight.getCompany().getId());
		if (!avio.isPresent()) {

			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}

		Optional<Flight> flightTemp = flightRepository.findById(flight.getId());
		flightTemp.get().setPriceForTicket(flight.getPriceForTicket());
		flightTemp.get().setPriceForTicket(flight.getPriceForTicket());
		flightTemp.get().setAdditionalPriceCarryBag(flight.getAdditionalPriceCarryBag());
		flightTemp.get().setAdditionalPriceCheckBag(flight.getAdditionalPriceCheckBag());
		flightTemp.get().setStartDestination(flight.getStartDestination());
		flightTemp.get().setEndDestination(flight.getEndDestination());
		flightTemp.get().setTravelTime(flight.getTravelTime());
		flightTemp.get().setMaxCarryBag(flight.getMaxCarryBag());
		flightTemp.get().setMaxCheckBag(flight.getMaxCheckBag());
		flightTemp.get().setTravelDistance(flight.getTravelDistance());
		flightRepository.saveAndFlush(flightTemp.get());

		return new ResponseEntity<Object>(flightTemp.get().getAvioCompany(), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Object> deleteFlight(DeleteFlightDTO flight) {

		Optional<AvioCompany> avio = avioRepository.findById(flight.getCompany().getId());
		if (!avio.isPresent()) {

			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}

		for (Flight f : avio.get().getFlights()) {
			if (f.getId().equals(flight.getFlight().getId())) {
				f.setActive(false);
				avio.get().getFlights().remove(f);
				avioRepository.saveAndFlush(avio.get());
				break;

			}

		}

		return new ResponseEntity<Object>(avio.get(), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Object> deleteFlightStop(FlightStopDTO flight) {
		Optional<AvioCompany> avio = avioRepository.findById(flight.getCompany().getId());
		if (!avio.isPresent()) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}
		for (Flight f : avio.get().flights) {
			if (f.getId().equals(flight.getFlight().getId())) {

				for (FlightStop fs : f.getFlightStop()) {
					if (fs.getId().equals(flight.getFlightStop().getId())) {
						f.getFlightStop().remove(fs);
						flightRepository.saveAndFlush(f);
						break;
					}
				}

			}
		}

		return new ResponseEntity<Object>(avio.get(), HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Object> getFlightFromUser(FlightFromUserDTO info) {

		AvioAdmin avioAdmin = avioAdminRepository.findByUsername(info.getUser().getUsername());
		AvioCompany avioCompany = avioAdmin.getAvioCompany();

		for (Flight flight : avioCompany.getFlights()) {
			if (flight.getId().equals(info.getIndex())) {

				return new ResponseEntity<Object>(flight, HttpStatus.OK);
			}
		}

		return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ResponseEntity<Object> reserveFlight(AvioOrderDTO order) {
		Optional<Flight> flight = flightRepository.findById(order.getFlight().getId());
		if (order.getOwner() != null) {
			RegistredUser owner = regUserRepository.findByUsername(order.getOwner().getUsername());

			// kreiramo rezervaciju
			FlightReservation reservation = new FlightReservation(owner);
			reservation.setFlight(flight.get());

			// prodji kroz sedista i stavi ih u rezervaciju

			for (Seat seat : order.getSeats()) {
				Optional<Seat> s = seatRepository.findById(seat.getId());
				reservation.getSeats().add(s.get());
			}
			flight.get().getFlightReservation().add(reservation);
			flightRepository.saveAndFlush(flight.get());

			// treba pozvati prijatelje
			if (order.getFriends() != null) {
				int k = 1;
				for (UserLoginDTO user : order.getFriends()) {
					System.out.println("Treba posatli: " + user.getUsername());
					RegistredUser regUser = regUserRepository.findByUsername(user.getUsername());
					SecurityUser userDetails = (SecurityUser) userDetailService.loadUserByUsername(user.getUsername());
					String token = tokenUtils.generateToken(userDetails);
					mailService.sendFriendReservation(token, order.getSeats().get(k).getId(), regUser,
							flight.get().getId());
					k++;
				}
			}

		} else {
			// kreiramo rezervaciju
			FlightReservation reservation = new FlightReservation();
			reservation.setFlight(flight.get());

			// prodji kroz sedista i stavi ih u rezervaciju

			for (Seat seat : order.getSeats()) {
				Optional<Seat> s = seatRepository.findById(seat.getId());
				reservation.getSeats().add(s.get());
			}
			flight.get().getFlightReservation().add(reservation);
			flightRepository.saveAndFlush(flight.get());
		}

		double additionalprice = 0;
		double price = 0;
		List<String> seatsCodes = new ArrayList<>();
		for (Seat seat : order.getSeats()) {
			Optional<Seat> s = seatRepository.findById(seat.getId());
			seatsCodes.add(s.get().getSeatCode());
			additionalprice += seat.getAdditionalPriceForClass();
			price += seat.getPriceForTicket();
			price += seat.getCarryBagCount() * flight.get().getAdditionalPriceCarryBag();
			price += seat.getCheckBagCount() * flight.get().getAdditionalPriceCheckBag();
			s.get().setName(seat.getName());
			s.get().setLastname(seat.getLastname());
			s.get().setPassport(seat.getPassport());
			s.get().setSeatType(SeatTypeEnum.RESERVED);
			s.get().setCarryBagCount(seat.getCarryBagCount());
			s.get().setCheckBagCount(seat.getCheckBagCount());
			seatRepository.saveAndFlush(s.get());
		}
		if (order.getOwner() != null) {
			mailService.sendAvioReservationInfo(order.getOwner(), flight.get(), seatsCodes, price + additionalprice);
		}
		// prihodi i broj korisnika
		Optional<AvioCompany> comp = avioRepository.findById(flight.get().getAvioCompany().getId());
		comp.get().getEarnings().add(new Earnings(new Date(),
				price + additionalprice));
		comp.get().getUsers().add(new AvioCompanyUsers(new Date(), order.getSeats().size()));
		avioRepository.saveAndFlush(comp.get());

		return new ResponseEntity<Object>(new ResponseMessage("Successfuly reserved flight!"), HttpStatus.OK);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ResponseEntity<Object> saveSeats(FlightSeatsDTO data) {

		Optional<Flight> f = flightRepository.findById(data.getId());
		/*
		 * for (Seat seat : f.get().getSeats()) { seat.setActive(false); }
		 * flightRepository.saveAndFlush(f.get());
		 */
		for (Seat seat : data.getSeats()) {
			if (seat.getId() != null) {
				Optional<Seat> s = seatRepository.findById(seat.getId());
				s.get().setFlight(f.get());
				s.get().setActive(true);
				s.get().setStartDestination(f.get().getStartDestination());
				s.get().setEndDestination(f.get().getEndDestination());
				s.get().setPriceForTicket(f.get().getPriceForTicket());
				s.get().setSeatType(seat.getSeatType());
				s.get().setAdditionalPriceForClass(seat.getAdditionalPriceForClass());
				s.get().setSeatClass(seat.getSeatClass());
				if (seat.getSeatType() == SeatTypeEnum.FASTRESERVE) {
					s.get().setPriceForTicket(
							seat.getPriceForTicket() - (seat.getPriceForTicket() * seat.getDiscount() / 100));
				}
				seatRepository.save(s.get());
			}
			else {
				seat.setFlight(f.get());
				seat.setActive(true);
				seat.setStartDestination(f.get().getStartDestination());
				seat.setEndDestination(f.get().getEndDestination());
				seat.setPriceForTicket(f.get().getPriceForTicket());
				seat.setSeatType(seat.getSeatType());
				seat.setAdditionalPriceForClass(seat.getAdditionalPriceForClass());
				seat.setSeatClass(seat.getSeatClass());
				if (seat.getSeatType() == SeatTypeEnum.FASTRESERVE) {
					seat.setPriceForTicket(
							seat.getPriceForTicket() - (seat.getPriceForTicket() * seat.getDiscount() / 100));
				}
				f.get().getSeats().add(seat);
			}
		}
		System.out.println("42424");
		
		flightRepository.save(f.get());

		return new ResponseEntity<Object>(f.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> searchForFlights(String type, int num, SeatClassEnum classs, String from, String to,
			Date takeoff, Date landing) {
		System.out.println("Udjes li ovde");
		System.out.println(type + num + classs + from + to + takeoff + landing);
		List<Flight> flights = flightRepository.findAll();
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		cal1.setTime(takeoff);
		cal2.setTime(landing);
		List<SearchedFlightsDTO> searchedFlights = new ArrayList<>();
		for (Flight flight : flights) {
			int numB = 0;
			boolean classsB = false;
			boolean fromB = false;
			boolean toB = false;
			boolean takeoffB = false;
			// uslov jedan i dva
			for (Seat seat : flight.getSeats()) {
				if (seat.getSeatType() == SeatTypeEnum.AVAILABLE && seat.getSeatClass().equals(classs)) {
					numB++;
					classsB = true;
				}
			}
			// uslov tri
			if (flight.getStartDestination().getLocation().getAdress().toLowerCase().contains(from.toLowerCase())) {
				fromB = true;
			}
			// uslov cetiri
			if (flight.getEndDestination().getLocation().getAdress().toLowerCase().contains(to.toLowerCase())) {
				toB = true;
			}
			Calendar cal3 = Calendar.getInstance();
			cal3.setTime(flight.getStartDestination().getTakeOff());
			if (cal1.get(Calendar.YEAR) == cal3.get(Calendar.YEAR)
					&& cal1.get(Calendar.DAY_OF_YEAR) == cal3.get(Calendar.DAY_OF_YEAR)
					&& cal1.get(Calendar.MONTH) == cal3.get(Calendar.MONTH)) {
				takeoffB = true;
			}

			System.out.println(numB + "dsa" + num + classsB + fromB + toB + takeoffB);
			// jos uslov za tip puta proverimo da li ima puteva koji se vracaju kada je
			// njegov
			if (type.equals("ONE-WAY")) {
				if (numB >= num && classsB && fromB && toB && takeoffB) {
					searchedFlights.add(new SearchedFlightsDTO(flight.getAvioCompany().getName(), flight));
				}
			} else {
				if (numB >= num && classsB && fromB && toB && takeoffB) {
					for (Flight flight2 : flights) {
						if (flight2.getStartDestination().getLocation().getAdress().contains(to)) {
							cal3 = Calendar.getInstance();
							cal3.setTime(flight2.getStartDestination().getTakeOff());
							if (cal3.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
									&& cal3.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
									&& cal3.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {

								searchedFlights.add(new SearchedFlightsDTO(flight.getAvioCompany().getName(), flight));
								break;
							}
						}
					}
				}
			}

		}
		System.out.println(searchedFlights.size());
		return new ResponseEntity<Object>(searchedFlights, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> confirmFlight(String answer, Long seat, Long flight, String token) {

		String username = tokenUtils.getUsernameFromToken(token);

		if (!tokenUtils.validateToken(token, userDetailService.loadUserByUsername(username)))
			return new ResponseEntity<Object>(new ResponseMessage("Verification link is not valid!"),
					HttpStatus.NOT_ACCEPTABLE);

		Optional<Flight> f = flightRepository.findById(flight);
		for (String un : f.get().getBlackList()) {
			if (un.equals(username)) {
				return new ResponseEntity<Object>(new ResponseMessage("Verification link is not valid!"),
						HttpStatus.NOT_ACCEPTABLE);
			}

		}
		f.get().getBlackList().add(username);
		// dodaj ga u black lsitu
		if (answer.equals("NO")) {
			double addprice = 0;
			// ukini sediste iz rezervacije
			for (FlightReservation res : f.get().getFlightReservation()) {
				for (Seat s : res.getSeats()) {
					if (s.getId() == seat) {
						// nasao vrati mu tip
						s.setLastname(null);
						s.setPassport(null);
						s.setPassport(null);
						s.setSeatType(SeatTypeEnum.AVAILABLE);
						addprice += s.getAdditionalPriceForClass();
						res.getSeats().remove(s);
						break;

					}

				}

			}
			// zatim ukolni ljude i novac iz firme
			AvioCompany company = f.get().getAvioCompany();
			company.getEarnings().add(new Earnings(new Date(), -f.get().getPriceForTicket() - addprice));
			company.getUsers().add(new AvioCompanyUsers(new Date(), -1));
			avioRepository.saveAndFlush(company);
		}
		return new ResponseEntity<Object>(new ResponseMessage("Your answer is sent!"), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> checkConfirm(Long seat, Long flight, String token) {

		System.out.println("fsafsa");
		String username = tokenUtils.getUsernameFromToken(token);

		if (!tokenUtils.validateToken(token, userDetailService.loadUserByUsername(username)))
			return new ResponseEntity<Object>(new ResponseMessage("Verification link is not valid!"),
					HttpStatus.NOT_ACCEPTABLE);

		Optional<Flight> f = flightRepository.findById(flight);
		for (String un : f.get().getBlackList()) {
			if (un.equals(username)) {
				return new ResponseEntity<Object>(new ResponseMessage("Verification link is not valid!"),
						HttpStatus.NOT_ACCEPTABLE);
			}
		}
		return new ResponseEntity<Object>(new ResponseMessage(username), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getCompanyGraph(Long id) {

		Optional<AvioCompany> avio = avioRepository.findById(id);

		if (!avio.isPresent()) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}

		Date date = new Date();
		// trazimo za danas
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(date);
		// godina
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 17);
		cal.set(Calendar.MINUTE, 30);
		cal.set(Calendar.YEAR, cal3.get(Calendar.YEAR));
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);

		// treba proveriti i racunati ljude
		double count[] = { 0, 0, 0 };

		for (AvioCompanyUsers user : avio.get().getUsers()) {
			Calendar temp = Calendar.getInstance();
			temp.setTime(user.getDate());
			if (temp.get(Calendar.YEAR) == cal3.get(Calendar.YEAR)
					&& temp.get(Calendar.MONTH) == cal3.get(Calendar.MONTH)
					&& temp.get(Calendar.DAY_OF_MONTH) == cal3.get(Calendar.DAY_OF_MONTH)) {
				// to je pravi dan :D
				count[0] += user.getCount();
			}
			if (temp.get(Calendar.YEAR) == cal3.get(Calendar.YEAR)) {
				// to je prava godina:D
				count[2] += user.getCount();
			}
			if (temp.get(Calendar.MONTH) == cal3.get(Calendar.MONTH)) {
				// to je pravi mesec :D
				count[1] += user.getCount();
			}

		}

		return new ResponseEntity<Object>(count, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<Object> getCompanyEarnings(EarningsDTO earnings) {

		Optional<AvioCompany> avio = avioRepository.findById(earnings.getCompany());

		if (!avio.isPresent()) {
			return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
		}

		double earningss = (double) 0;

		for (Earnings e : avio.get().getEarnings()) {

			if (e.getDate().after(earnings.getEarnings1()) && e.getDate().before(earnings.getEarnings2())) {
				earningss += e.getMoney();
			}

		}

		return new ResponseEntity<Object>(earningss, HttpStatus.OK);

	}

}
