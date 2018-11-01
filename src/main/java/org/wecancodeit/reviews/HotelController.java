package org.wecancodeit.reviews;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HotelController {
	
	@Resource
	HotelRepository hotelRepo;
	
	@Resource
	private ReviewRepository reviewRepo;

	@RequestMapping("/hotel")
	public String findOneHotel(@RequestParam(value="id")long id, Model model) throws HotelNotFoundException {
		
		Optional<Hotel> hotel = hotelRepo.findById(id);
	
		if(hotel.isPresent()) {
			model.addAttribute("hotels", hotel.get());
			return "hotel";
		}
		throw new HotelNotFoundException();
	}

	@RequestMapping("/show-hotels")
	public String findAllHotels(Model model) {
		model.addAttribute("hotels",hotelRepo.findAll());
		return ("hotels");
		
	}
	
	@RequestMapping("/review")
	public String findOneReview(long id, Model model) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepo.findById(id);
		
		if(review.isPresent()) {
			model.addAttribute("reviews", review.get());
			model.addAttribute("hotels", hotelRepo.findByReviewsContains(review.get()));
			return "review";
		}
		throw new ReviewNotFoundException();
		
	}

	@RequestMapping("/show-reviews")
	public String findAllReviews(Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());
		return ("reviews");
		
	}	

	
}
