package com.m2i.TL_Interne_Application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.m2i.entities.Reservation;
import com.m2i.entities.Restaurant;
import com.m2i.repositories.ReservationRepository;
import com.m2i.repositories.RestaurantRepository;

@SpringBootTest
class ReservationServiceTest {
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Sql("classpath:table_insertion.sql")
	void findByRestaurant_tableInsertionAndRestaurantUn_ReturnTroisReservations() {

		Restaurant restaurant = restaurantRepository.findById(1).get();
		List<Reservation> resaRestaurantUn = reservationRepository.findByRestaurant(restaurant);
		assertEquals(3, resaRestaurantUn.size());
		assertIterableEquals(Arrays.asList(new Integer[] { 1, 3, 5 }),
				resaRestaurantUn.stream().map(t -> t.getId()).collect(Collectors.toList()));
	}

	@Test
	@Sql("classpath:table_insertion.sql")
	void getAllReservationsByDate_tableInsertion_ReturnUneReservation() {
		
		List<Reservation> resaDuJour = reservationRepository.findByDateBetweenOrderByDate(LocalDateTime.of(2023, 02, 05, 00, 00), LocalDateTime.of(2023, 02, 05, 23, 59));
		assertEquals(1, resaDuJour.size());
		assertIterableEquals(
				Arrays.asList(new Integer[] {3}),
				resaDuJour.stream().map(t -> t.getId()).collect(Collectors.toList())
		);
		
	}
}


