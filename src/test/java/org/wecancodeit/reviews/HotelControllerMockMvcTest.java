package org.wecancodeit.reviews;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(HotelController.class)
public class HotelControllerMockMvcTest {
	
	@Resource
	private MockMvc mvc;
	
	@Mock
	private Hotel hotel;

	@Mock
	private Hotel anotherHotel;
	
	@MockBean
	private HotelRepository hotelRepo;
	
	
	@Mock
	private Review review;
	
	@Mock
	private Review anotherReview;	
	
	@MockBean
	private ReviewRepository reviewRepo;
	
	

	@Test
	public void shouldRouteToSingleHotelView() throws Exception {
		long hotelId = 651;
		when(hotelRepo.findById(hotelId)).thenReturn(Optional.of(hotel));
		mvc.perform(get("/hotel?id=651")).andExpect(view().name(is("hotel")));
	}
	
	@Test
	public void shouldBeOkForSingleHotel() throws Exception{
		long hotelId = 111;
		when(hotelRepo.findById(hotelId)).thenReturn(Optional.of(hotel));
		mvc.perform(get("/hotel?id=111")).andExpect(status().isOk());
	}


	@Test
	public void shouldNotBeOkForSingleHotel() throws Exception{
		mvc.perform(get("/hotel?id=45")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldPutSingleHotelIntoModel() throws Exception {
		
		when(hotelRepo.findById(111L)).thenReturn(Optional.of(hotel));
		
		mvc.perform(get("/hotel?id=111")).andExpect(model().attribute("hotels", is(hotel)));
	}
	
	@Test
	public void shouldRouteToAllHotelsView() throws Exception {
		
		mvc.perform(get("/show-hotels")).andExpect(view().name(is("hotels")));
	}
	
	@Test
	public void shouldBeOkForAllHotels() throws Exception {
		mvc.perform(get("/show-hotels")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldPutAllHotelsIntoModel() throws Exception{
		Collection<Hotel> allHotels = Arrays.asList(hotel, anotherHotel);
		
		when(hotelRepo.findAll()).thenReturn(allHotels);
		
		mvc.perform(get("/show-hotels")).andExpect(model().attribute("hotels", is(allHotels)));
	}
	
	
	@Test
	public void shouldRouteToSingleReviewView() throws Exception {
		long arbitraryReviewId=34;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=34")).andExpect(view().name(is("review")));
	}
	
	@Test
	public void shouldBeOkForSingleReview() throws Exception{
		long arbitraryCategoryId = 109;
		when(reviewRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=109")).andExpect(status().isOk());
	}


	@Test
	public void shouldNotBeOkForSingleReview() throws Exception{
		mvc.perform(get("/review?id=1")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldPutSingleReviewIntoModel() throws Exception {
		
		when(reviewRepo.findById(111L)).thenReturn(Optional.of(review));
		
		mvc.perform(get("/review?id=111")).andExpect(model().attribute("reviews", is(review)));
	}
	
	@Test
	public void shouldRouteToAllReviewsView() throws Exception {
		
		mvc.perform(get("/show-reviews")).andExpect(view().name(is("reviews")));
	}
	
	@Test
	public void shouldBeOkForAllReviews() throws Exception {
		mvc.perform(get("/show-reviews")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldPutAllreviewsIntoModel() throws Exception{
		Collection<Review> allReviews = Arrays.asList(review, anotherReview);
		
		when(reviewRepo.findAll()).thenReturn(allReviews);
		
		mvc.perform(get("/show-reviews")).andExpect(model().attribute("reviews", is(allReviews)));
	}
}
