package com.bertazoli.server.businesslogic;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.bertazoli.server.hibernate.HibernateUtil;
import com.bertazoli.shared.beans.Workout;
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
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        return workout;
	}

}
