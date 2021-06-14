package dao;

import java.util.ArrayList;

/**
 *
 * @author Dansomon
 */
public interface IDao<T> {
    public String create(T obj);
    public boolean update(T obj);
    public boolean delete(int id);
    public ArrayList<T> findAll();
    public T findOne(int id);
    public T findByRef(String ref);
}