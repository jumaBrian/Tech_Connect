package interfaces;

import models.Service;

import java.util.List;

public interface ServiceDao {
    //CRUD
    //create
    void add(Service service);

    //read
    List<Service> getAll();

    Service findById(int id);

    void update(int id, String service, int hourly_price, int hours, int user_id);

    //delete
    void delete(int id);
}
