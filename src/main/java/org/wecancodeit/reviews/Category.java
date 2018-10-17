package org.wecancodeit.reviews;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;



@Entity
public class Category {
	
	@Id
	@GeneratedValue
	private long id;

	
	private String title;
	private String rating;
	private String basis;

	@ManyToMany
	private Collection<Review> reviews;

	public long getId() {
		return id;
	}

	public String getTitle() {
		
		return title;
	}

	public String getRating() {
		return rating;
	}

	public String getBasis() {
		return basis;
	}

	
	public Collection<Review> getReviews() {
		return reviews;
	}

	public Category() {
		
	}
	public Category(String title, String rating, String basis, Review...reviews) {

		this.title = title;
		this.rating = rating;
		this.basis = basis;
		this.reviews = new HashSet<>(Arrays.asList(reviews));

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}
