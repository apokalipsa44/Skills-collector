package com.michau.DbUtils;

import com.michau.Model.Skills;
import org.hibernate.SessionFactory;

import java.util.List;

public class SkillDao extends BaseDao {
    public SkillDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Skills get(Long id) {
        return super.produceInTransaction(session -> session.get(Skills.class, id));
    }

    public void save(Skills skill) {
        super.executeInTransaction(session -> session.save(skill));
    }

    public void update(Skills skill) {
        super.executeInTransaction(session -> session.update(skill));
    }

    public void delete(Skills skill) {
        super.executeInTransaction(session -> session.delete(skill));
    }

    public List<Skills> getAll() {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT s FROM skills s", Skills.class)
                        .getResultList());
    }

    public List<Skills> getAllBySkillName(String name) {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT s FROM skills s WHERE s.name = :name", Skills.class)
                        .setParameter("name", name)
                        .getResultList());
    }
}
