package interfaces;

import models.Professional;

import java.util.List;

public interface ProfessionalDao {
    //create
    void add (Professional professional);

    //read
    List<Professional> getAllProfessionals();
    Professional findProfessionalById(int id);

    //update
    void update(int id, String professional_name, String bio, String qualification,String email, int service_id );

    //delete
    void deleteById(int id);
    void clearAll();
}
