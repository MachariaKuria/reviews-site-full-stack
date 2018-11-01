package org.wecancodeit.reviews;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Review {
    @Id
    @GeneratedValue
    private long id;

    private String reviewType;

    @ManyToMany(mappedBy="review")
    private Collection<Hotel> hotels;

    public long getId(){
    return id;
    }

    public String getReviewType(){
    return reviewType;
    }

    public Collection<Hotel> getHotels(){
        return hotels;
    }


    //default constructor
    public Review () {

    }
    public Review (String reviewType){

    this.reviewType = reviewType;
 
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
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
