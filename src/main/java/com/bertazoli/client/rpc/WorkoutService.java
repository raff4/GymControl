package com.bertazoli.client.rpc;

import java.util.Set;

import com.bertazoli.shared.beans.Workout;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("workout")
public interface WorkoutService extends RemoteService {
	public Workout add(Workout workout);
	public Set<Workout> addAll(Set<Workout> workouts);
}
