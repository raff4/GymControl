package com.bertazoli.server.rpc;

import java.util.Set;

import com.bertazoli.client.rpc.WorkoutCardioService;
import com.bertazoli.server.businesslogic.WorkoutCardioBusinessLogic;
import com.bertazoli.shared.beans.WorkoutCardio;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class WorkoutCardioServiceImpl extends RemoteServiceServlet implements WorkoutCardioService {

    private static final long serialVersionUID = -6174538132656746898L;
    private WorkoutCardioBusinessLogic workout;
    
    @Inject
    public WorkoutCardioServiceImpl(WorkoutCardioBusinessLogic workout) {
        this.workout = workout;
    }

    @Override
    public WorkoutCardio add(WorkoutCardio cardio) {
        return workout.add(cardio);
    }

    @Override
    public Set<WorkoutCardio> addAll(Set<WorkoutCardio> cardios) {
        return workout.addAll(cardios);
    }

}
