package com.bertazoli.shared.beans;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.sf.gilead.pojo.java5.legacy.LightEntity;


@Entity
@Table(name="workout")
public class Workout extends LightEntity implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Date day;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="id")
	private Set<WorkoutCardio> cardios = new HashSet<WorkoutCardio>(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}