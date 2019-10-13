package com.michau.DbUtils;


import com.michau.Model.Sources;
import org.hibernate.SessionFactory;

public class SourceDao extends BaseDao {

    protected SourceDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Sources get(Long id) {
        return super.produceInTransaction(session -> session.get(Sources.class, id));
    }

    public void save(Sources source) {
        super.executeInTransaction(session -> session.save(source));
    }

    public void update(Sources source) {
        super.executeInTransaction(session -> session.update(source));
    }

    public void delete(Sources source) {
        super.executeInTransaction(session -> session.delete(source));
    }
}
