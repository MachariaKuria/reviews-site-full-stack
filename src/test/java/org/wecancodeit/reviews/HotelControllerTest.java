package org.wecancodeit.reviews;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class HotelControllerTest {
	
	@InjectMocks
	private HotelController underTest;	

	@Mock
	private Model model;
	
	@Mock
	private Hotel hotel;

	@Mock
	private Hotel anotherHotel;

	@Mock
	private Review anotherReview;
	
	@Mock
	private HotelRepository hotelRepo;

	@Mock
	private Review review;
	
	@Mock
	private ReviewRepository reviewRepo;	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldAddSingleHotelToModel() throws HotelNotFoundException {
		long hotelId = 1;
		when(hotelRepo.findById(hotelId)).thenReturn(Optional.of(hotel));
		
		
		underTest.findOneHotel(hotelId, model);
		verify(model).addAttribute("hotels",hotel);
	}

	@Test
	public void shouldAddAllHotelsToModel() {
		
		Collection<Hotel> allHotels = Arrays.asList(hotel, anotherHotel);
		when(hotelRepo.findAll()).thenReturn(allHotels);
		
		underTest.findAllHotels(model);
		verify(model).addAttribute("hotels", allHotels);
		
	}
	
	@Test
	public void shouldAddSingleReviewToModel() throws ReviewNotFoundException {

		long arbitraryReviewId = 1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		
		underTest.findOneReview(arbitraryReviewId, model);
		
		verify(model).addAttribute("reviews",review);
	}
	
	@Test
	public void shouldAddAllReviewsToModel() {
		
		Collection<Review> allReviews = Arrays.asList(review, anotherReview);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		
		underTest.findAllReviews(model);
		
		verify(model).addAttribute("reviews", allReviews);
	}
	
	
}
