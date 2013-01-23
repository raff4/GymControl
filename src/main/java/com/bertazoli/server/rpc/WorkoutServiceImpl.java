package com.bertazoli.server.rpc;

import java.util.Set;

import javax.inject.Singleton;

import com.bertazoli.client.rpc.WorkoutService;
import com.bertazoli.server.businesslogic.WorkoutBusinesLogic;
import com.bertazoli.shared.beans.Workout;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;

@Singleton
public class WorkoutServiceImpl extends RemoteServiceServlet implements WorkoutService {

	private static final long serialVersionUID = -3951578709612058937L;
	private WorkoutBusinesLogic workoutBusiness;
	
	@Inject
	public WorkoutServiceImpl(WorkoutBusinesLogic workoutBusiness) {
		this.workoutBusiness = workoutBusiness;
	}

	@Override
	public Workout add(Workout workout) {
		return workoutBusiness.add(workout);
	}

	@Override
	public Set<Workout> addAll(Set<Workout> workouts) {
		return null;
	}

}
