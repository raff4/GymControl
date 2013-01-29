package com.bertazoli.shared.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="workout_dropset")
public class WorkoutDropSet implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="dropsetId")
    private Set<WorkoutDropSetSet> dropSetSet = new HashSet<WorkoutDropSetSet>(0);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<WorkoutDropSetSet> getDropSetSet() {
        return dropSetSet;
    }

    public void setDropSetSet(Set<WorkoutDropSetSet> dropSetSet) {
        this.dropSetSet = dropSetSet;
    }
}
