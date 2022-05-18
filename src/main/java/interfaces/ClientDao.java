package interfaces;

import models.Client;
import models.Service;

import java.util.List;

public interface ClientDao {
    //CRUD
    //create
    void add(Client client);

    //Read
    List<Client> getAll();
    List<Service> getAllServicesByClient(int user_id);

    //Find
    Client findById(int id);

    //Update
    void update(int id, String name, String email, String contact);

    //Destroy
    void deleteById(int id);
    void clearAll();

}
