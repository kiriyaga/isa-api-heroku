package isa_api.services;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import isa_api.beans.flight.Flight;
import isa_api.beans.users.RegistredUser;
import isa_api.dto.UserLoginDTO;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	/**
	 * Metoda koja salje aktivacioni link(token) korisniku koji se registrovao
	 * 
	 * @param regUser
	 * @param token
	 * @throws MailException
	 */

	@Async
	public void sendRegistrationActivation(RegistredUser regUser, String token) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("majkemijevrosime@gmail.com");
		mail.setTo(regUser.getEmail());
		mail.setSubject("Activate account:");
		mail.setText("To activate account click on link: " + "http://localhost:8080/public/register/" + token);
		mailSender.send(mail);
	}

	@Async
	public void sendFriendReservation(String token, Long id, RegistredUser regUser, Long id2) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("majkemijevrosime@gmail.com");
		mail.setTo(regUser.getEmail());
		mail.setSubject("Confirm your reservation");
		mail.setText("To confirm flight reservation click on link: " + "http://localhost:4200/avio/confirm/" + token
				+ "/" + id + "/" + id2);
		mailSender.send(mail);
	}

	@Async
	public void sendAvioReservationInfo(UserLoginDTO userLoginDTO, Flight flight, List<String> seatsCodes, double price)
			throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("majkemijevrosime@gmail.com");
		mail.setTo(userLoginDTO.getEmail());
		mail.setSubject("Successfull flight reservation");
		mail.setText("Reservation informations\n" + "Seats:" + seatsCodes.toString() + "\n" + "Flight informations \n"
				+ "Start Destination: " + flight.getStartDestination().getLocation().getAdress() + " "
				+ flight.getStartDestination().getTakeOff() + "\n" + "End Destination: "
				+ flight.getEndDestination().getLocation().getAdress() + " " + flight.getEndDestination().getTakeOff()
				+ "\n" + "Total price: " + price);
		mailSender.send(mail);
	}

	@Async
	public void sendTest(String subject, String message) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("makiarambasic@gmail.com");
		mail.setFrom("majkemijevrosime@gmail.com");
		mail.setSubject(subject);
		mail.setText(message);

		mailSender.send(mail);
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("majkemijevrosime@gmail.com");
		mailSender.setPassword("teamrocketisa");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "false");

		return mailSender;
	}

}
