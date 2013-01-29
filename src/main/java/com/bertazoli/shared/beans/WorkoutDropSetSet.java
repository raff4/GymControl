package com.bertazoli.shared.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import net.sf.gilead.pojo.java5.LightEntity;

@Entity
@Table(name="workout_dropset_set")
public class WorkoutDropSetSet extends LightEntity implements Serializable {

    @Id
    private Long id;
    
    private Integer dropsetId;
    
    private Integer sets;
    
    private Integer weight;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDropsetId() {
        return dropsetId;
    }

    public void setDropsetId(Integer dropsetId) {
        this.dropsetId = dropsetId;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
