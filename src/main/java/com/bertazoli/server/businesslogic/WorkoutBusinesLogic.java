package com.bertazoli.server.businesslogic;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.bertazoli.server.hibernate.HibernateUtil;
import com.bertazoli.shared.beans.Workout;
import com.bertazoli.shared.beans.WorkoutCardio;
import com.bertazoli.shared.beans.WorkoutRegular;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class WorkoutBusinesLogic {
	
	@Inject
	public WorkoutBusinesLogic() {
		
	}

	public Workout add(Workout workout) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.save(workout);
            
            for (WorkoutCardio cardio : workout.getCardios()) {
                cardio.setWorkoutId(workout.getId());
                session.save(cardio);
            }
            
            for (WorkoutRegular regular : workout.getRegulars()) {
                regular.setWorkoutId(workout.getId());
                session.save(regular);
            }
            
            session.getTransaction().commit();
            
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        return workout;
	}

}
