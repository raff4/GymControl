package com.bertazoli.shared.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gwt.user.client.rpc.IsSerializable;


@Entity
@Table(name="workout")
public class Workout implements IsSerializable {
	
	@Id
	@GeneratedValue
	private Long id;
	private Long userId;
	private String name;
	private Date day;
	
	@Transient
	private Set<WorkoutCardio> cardios = new HashSet<WorkoutCardio>(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public Set<WorkoutCardio> getCardios() {
		return cardios;
	}

	public void setCardios(Set<WorkoutCardio> cardios) {
		this.cardios = cardios;
	}

	public String getName() {
	    return name;
	}
	
    public void setName(String name) {
        this.name = name;
    }
}