package interfaces;

import models.Client;

import java.util.List;

public interface ClientDao {
    //CRUD
    //create
    void add(Client client);

    //Read
    List<Client> getAll();

    //Find
    Client findById(int id);

    //Update
    void update(int id, String name, String email, String contact);

    //Destroy
    void deleteById(int id);
//    void clearAll();

}
