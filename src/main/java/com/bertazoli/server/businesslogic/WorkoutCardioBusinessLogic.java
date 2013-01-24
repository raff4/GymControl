package com.bertazoli.server.businesslogic;

import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.bertazoli.client.rpc.WorkoutCardioService;
import com.bertazoli.server.hibernate.HibernateUtil;
import com.bertazoli.shared.beans.WorkoutCardio;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class WorkoutCardioBusinessLogic implements WorkoutCardioService {
    
    @Inject
    public WorkoutCardioBusinessLogic() {
    }

    @Override
    public WorkoutCardio add(WorkoutCardio cardio) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.save(cardio);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        return cardio;
    }

    @Override
    public Set<WorkoutCardio> addAll(Set<WorkoutCardio> cardios) {
        for (WorkoutCardio cardio : cardios) {
            add(cardio);
        }
        return cardios;
    }

}
