package Repository;

import java.util.List;

public interface IMyRepository<T> {
 void insertEntity(T entity);
 int insertEntityAndReturnId(T entity);
 void deleteById( int id);
 T getById(int id);
 T getOneByQuery(String query);
 List<T> getAll();
 List<T> getByQuery(String query);
 void updateEntity(T entity);
 void executeQuery(String query);
 
}
