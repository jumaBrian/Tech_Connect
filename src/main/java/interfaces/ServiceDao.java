package interfaces;

import models.Service;

import java.util.List;

public interface ServiceDao {
    //CRUD
    //create
    void add(Service service);

    //read
    List<Service> getAll();

    //update
    void update(Service service);
    //delete
    void delete(int id);
}
