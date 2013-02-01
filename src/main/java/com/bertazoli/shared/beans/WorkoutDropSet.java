package com.bertazoli.shared.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gwt.user.client.rpc.IsSerializable;

@Entity
@Table(name="workout_dropset")
public class WorkoutDropSet implements IsSerializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    
    @Transient
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
