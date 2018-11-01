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
		
//		Review treeTops = new Review("Food");
//		treeTops = reviewRepo.save(treeTops);
//
//		Review maraSopa = new Review("Rooms");
//		maraSopa = reviewRepo.save(maraSopa);
//		
//		Review lakeSide = new Review("Gym");
//		lakeSide = reviewRepo.save(lakeSide);
//		
//		Review seaResort = new Review("Pool");
//		seaResort = reviewRepo.save(seaResort);
//		
//		Review hilton = new Review("Free WiFi");
//		hilton = reviewRepo.save(hilton);		
		
//		Category lakesideHotel = new Category("Lakeside Hotels - Mountain Lodge","/images/MountainLodge.jpg","Nanyuki, Kenya",treeTops,lakeSide);
//		Category mountainHotel = new Category("Montain Hotels - TreeTops","/images/TreetopsLodge.jpg","Nyeri, Kenya",treeTops,maraSopa);
//		Category plainHotel = new Category("Plain Hotels - Mara Sopa Lodge","/images/AmboseliSerena.jpg","Amboseli, Kenya",maraSopa,lakeSide);
//		Category beachHotel = new Category("Beach Hotels - Nyali Beach Hotel","/images/NyaliBeach.jpg","Mombasa, Kenya",lakeSide,seaResort);
//		Category luxuryHotel = new Category("City Hotels - Hilton Hotel","/images/Hilton.jpg","New York, USA",seaResort,hilton);
//		
//		mountainHotel = categoryRepo.save(mountainHotel);
//		plainHotel = categoryRepo.save(plainHotel);
//		lakesideHotel = categoryRepo.save(lakesideHotel);
//		beachHotel = categoryRepo.save(beachHotel);
//		luxuryHotel = categoryRepo.save(luxuryHotel);
		
	}
	
	
}
