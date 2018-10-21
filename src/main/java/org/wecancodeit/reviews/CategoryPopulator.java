package org.wecancodeit.reviews;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategoryPopulator implements CommandLineRunner {

	@Resource
	private CategoryRepository categoryRepo;
	
	
	@Resource
	private ReviewRepository reviewRepo;


	@Override
	public void run(String... args) throws Exception {
		
		Review treeTops = new Review("Food","****","Variety");
		treeTops = reviewRepo.save(treeTops);

		Review maraSopa = new Review("Rooms","****","No of beds");
		maraSopa = reviewRepo.save(maraSopa);
		
		Review lakeSide = new Review("Customer Service","****","Prompt");
		lakeSide = reviewRepo.save(lakeSide);
		
		Category mountainHotel = new Category("Montain Hotels","/images/TreetopsLodge.jpg","Nyeri, Kenya",treeTops,maraSopa);
		Category beachHotel = new Category("Beach Hotels","/images/AmboseliSerena.jpg","Amboseli, Kenya",maraSopa,lakeSide);
		Category lakesideHotel = new Category("Lakeside Hotels","/images/MountainLodge.jpg","Nyeri, Kenya",treeTops,lakeSide);
		
		mountainHotel = categoryRepo.save(mountainHotel);
		beachHotel = categoryRepo.save(beachHotel);
		lakesideHotel = categoryRepo.save(lakesideHotel);
	
	}
	
	
}
