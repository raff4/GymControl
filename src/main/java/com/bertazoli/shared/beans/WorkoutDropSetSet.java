package com.bertazoli.shared.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gwt.user.client.rpc.IsSerializable;

@Entity
@Table(name="workout_dropset_set")
public class WorkoutDropSetSet implements IsSerializable {

    @Id
    @GeneratedValue
    private Long id;
    
    private Long dropsetId;
    
    private Integer sets;
    
    private Integer weight;
    
    private Integer repetitions;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDropsetId() {
        return dropsetId;
    }

    public void setDropsetId(Long dropsetId) {
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

    public Integer getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(Integer repetitions) {
        this.repetitions = repetitions;
    }
}
