package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;

class ProfessionalTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void objectInstantiatesCorrectly() {
        Professional professional = setupProfessional();
        assertNotNull(professional);
    }

    @Test
    void getProfessional_name() {
        Professional professional = setupProfessional();
        assertEquals("John Doe", professional.getProfessional_name());
    }

    @Test
    void setProfessional_name() {
        Professional professional = setupProfessional();
        professional.setProfessional_name("Jane Doe");
        assertNotEquals("John Doe", professional.getProfessional_name());
        assertEquals("Jane Doe", professional.getProfessional_name());
    }

    @Test
    void getEmail() {
        Professional professional= setupProfessional();
        String email = professional.getEmail();
        assertEquals("johndoe@gmail.com",email);
    }

    @Test
    void setEmail() {
        Professional professional = setupProfessional();
         professional.setEmail("doejohn@mail.com");
        assertNotEquals("johndoe@gmail.com", professional.getEmail());
    }

    @Test
    void getQualification() {
        Professional professional = setupProfessional();
        String qualify = professional.getQualification();
        assertEquals("Android Developer, Java Spark Specialist",qualify);

    }

    @Test
    void setQualification() {
        Professional professional = setupProfessional();
        professional.setQualification("Angular,Python,SQL");
        assertEquals("Angular,Python,SQL", professional.getQualification());

    }

    @Test
    void getBio() {
        Professional professional = setupProfessional();
        String bio= professional.getBio();
        assertEquals("Can work under pressure", bio);

    }

    @Test
    void setBio() {
        Professional professional = setupProfessional();
        professional.setBio("Team player");
        String newBio = professional.getBio();
        assertEquals("Team player",newBio);

    }

    @Test
    void getService_id() {
        Professional professional = setupProfessional();
        int serviceId= professional.getService_id();
        assertEquals(1, serviceId);

    }

    @Test
    void setService_id() {
        Professional professional = setupProfessional();
        professional.setService_id(2);
        assertNotEquals(1,professional.getService_id());
        assertEquals(2,professional.getService_id());

    }

    @Test
    void testEquals() {
        Professional professional = setupProfessional();
        Professional professional2 = setupProfessional();
        assertEquals(professional, professional2);
    }
//helper method
public Professional setupProfessional(){
    return new Professional("John Doe","johndoe@gmail.com","Android Developer, Java Spark Specialist","Can work under pressure",1);
}

}