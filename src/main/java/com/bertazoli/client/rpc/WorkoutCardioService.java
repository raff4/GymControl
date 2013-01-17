package com.bertazoli.client.rpc;

import java.util.Set;

import com.bertazoli.shared.beans.WorkoutCardio;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("workoutcardio")
public interface WorkoutCardioService extends RemoteService {
    public WorkoutCardio add(WorkoutCardio cardio);
    public Set<WorkoutCardio> addAll(Set<WorkoutCardio> cardios);
}
