package com.hotel.app;

import com.hotel.app.model.Hotel;
import com.hotel.app.model.Review;
import com.hotel.app.model.Room;
import com.hotel.app.model.Traveler;
import com.hotel.app.repository.HotelRepository;
import com.hotel.app.repository.TravelerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	CommandLineRunner initializeDatabase(HotelRepository hotelRepository, TravelerRepository travelerRepository) {
		return args -> {

			Traveler traveler = new Traveler();
			traveler.setGender("M");
			traveler.setFirstName("Aleksey");
			traveler.setLastName("Ivanovich");
			traveler.setResidentialCity("Moscow");

			Traveler traveler2 = new Traveler();
			traveler2.setGender("F");
			traveler2.setFirstName("Lisa");
			traveler2.setLastName("Malcolm");
			traveler2.setResidentialCity("Los Angeles");

			//start hotel1

			Hotel hotel = new Hotel();
			hotel.setHotelName("Mariott");
			hotel.setStars(4);
			hotel.setRestaurant(true);
			hotel.setRating(9.5d);
			hotel.setMeals(true);
			hotel.setCity("London");

			Room room = new Room();
			room.setAc(true);
			room.setWifi(true);
			room.setBeds(2);
			room.setPricePerNight(new BigDecimal("500"));
			room.setBookedStartDate(new GregorianCalendar(2021, 0, 3).getTime());
			room.setBookedEndDate(new GregorianCalendar(2021, 0, 4).getTime());
			room.setHotel(hotel);

			hotel.getRooms().add(room);

			room = new Room();
			room.setAc(true);
			room.setWifi(true);
			room.setBeds(3);
			room.setPricePerNight(new BigDecimal("250"));
			room.setBookedStartDate(new GregorianCalendar(2021, 5, 15).getTime());
			room.setBookedEndDate(new GregorianCalendar(2021, 5, 25).getTime());
			room.setHotel(hotel);

			hotel.getRooms().add(room);

			Review review = new Review();
			review.setHotel(hotel);
			review.setComment("Nice place.");
			review.setRatingValue(9);
			review.setTraveler(traveler);

			traveler.getReviews().addAll(Arrays.asList(review));

			review = new Review();
			review.setHotel(hotel);
			review.setComment("Bad service.");
			review.setRatingValue(4);
			review.setTraveler(traveler2);

			traveler2.getReviews().addAll(Arrays.asList(review));

			hotelRepository.save(hotel);

			// End hotel1

			// Start hotel2

			hotel = new Hotel();
			hotel.setHotelName("Hilton");
			hotel.setStars(2);
			hotel.setRestaurant(true);
			hotel.setRating(7.5d);
			hotel.setMeals(false);
			hotel.setCity("Bangalore");

			room = new Room();
			room.setAc(false);
			room.setWifi(false);
			room.setBeds(2);
			room.setPricePerNight(new BigDecimal("355"));
			room.setBookedStartDate(new GregorianCalendar(2021, 3, 3).getTime());
			room.setBookedEndDate(new GregorianCalendar(2021, 3, 4).getTime());
			room.setHotel(hotel);

			hotel.getRooms().add(room);

			room = new Room();
			room.setAc(true);
			room.setWifi(true);
			room.setBeds(3);
			room.setPricePerNight(new BigDecimal("200"));
			room.setBookedStartDate(new GregorianCalendar(2021, 5, 18).getTime());
			room.setBookedEndDate(new GregorianCalendar(2021, 5, 24).getTime());
			room.setHotel(hotel);

			hotel.getRooms().add(room);

			review = new Review();
			review.setHotel(hotel);
			review.setComment("Complimentary food was amazing.");
			review.setRatingValue(10);
			review.setTraveler(traveler);

			traveler.getReviews().addAll(Arrays.asList(review));

			review = new Review();
			review.setHotel(hotel);
			review.setComment("Pretty good.");
			review.setRatingValue(8);
			review.setTraveler(traveler2);

			traveler2.getReviews().addAll(Arrays.asList(review));

			hotelRepository.save(hotel);

			// End hotel2

			// Start hotel3

			hotel = new Hotel();
			hotel.setHotelName("Merlin International");
			hotel.setStars(3);
			hotel.setRestaurant(false);
			hotel.setRating(7.5d);
			hotel.setMeals(false);
			hotel.setCity("New York");

			room = new Room();
			room.setAc(true);
			room.setWifi(true);
			room.setBeds(2);
			room.setPricePerNight(new BigDecimal("799"));
			room.setBookedStartDate(new GregorianCalendar(2021, 4, 11).getTime());
			room.setBookedEndDate(new GregorianCalendar(2021, 4, 20).getTime());
			room.setHotel(hotel);

			hotel.getRooms().add(room);

			room = new Room();
			room.setAc(true);
			room.setWifi(true);
			room.setBeds(3);
			room.setPricePerNight(new BigDecimal("425"));
			room.setBookedStartDate(new GregorianCalendar(2021, 5, 20).getTime());
			room.setBookedEndDate(new GregorianCalendar(2021, 5, 25).getTime());
			room.setHotel(hotel);

			hotel.getRooms().add(room);

			hotelRepository.save(hotel);

			// End hotel3
			travelerRepository.save(traveler);
			travelerRepository.save(traveler2);
		};
	}

}
