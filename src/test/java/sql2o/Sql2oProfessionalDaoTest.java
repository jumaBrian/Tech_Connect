package sql2o;

import models.Professional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oProfessionalDaoTest {
    private Connection conn;
    private Sql2oProfessionalDao professionalDao;

    @BeforeEach
    void setUp() {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        professionalDao = new Sql2oProfessionalDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    void tearDown() {
        conn.close();
    }

    @Test
    void addingProfessionalSetsId() {
        Professional professional = setupProfessional();
        professionalDao.add(professional);
        assertEquals(1,professional.getId());
    }

    @Test
    void addedProfessionalsAreReturnedByGetAllProfessionals() {
        Professional professional = setupProfessional();
        Professional professional2 = setupProfessional();
        professionalDao.add(professional);
        professionalDao.add(professional2);
        assertEquals(2,professionalDao.getAllProfessionals().size());
    }

    @Test
    void findProfessionalByIdReturnsCorrectProfessional() {
        Professional professional = setupProfessional();
        Professional professional2 = setupProfessional();
        Professional professional3 = setupProfessional();
        professionalDao.add(professional);
        professionalDao.add(professional2);
        professionalDao.add(professional3);
        assertEquals(professional2,professionalDao.findProfessionalById(professional2.getId()));
    }

    @Test
    void updateCorrectlyUpdatesAllFields() {
        Professional professional= setupProfessional();
        professionalDao.add(professional);
        professionalDao.update(professional.getId(),"Sky","Team player","Java","sky@gmail.com",2);
        Professional updatedProfessional = professionalDao.findProfessionalById(professional.getId());
        assertEquals("Sky", updatedProfessional.getProfessional_name());
        assertEquals("Team player", updatedProfessional.getBio());
        assertEquals("Java", updatedProfessional.getQualification());
    }

    @Test
    void deleteById() {
        Professional professional = setupProfessional();
        Professional professional2 = setupProfessional();
        professionalDao.add(professional);
        professionalDao.add(professional2);
        professionalDao.deleteById(professional.getId());
        assertEquals(1,professionalDao.getAllProfessionals().size());
    }

    @Test
    void clearAll() {
        Professional professional = setupProfessional();
        Professional professional2 = setupProfessional();
        professionalDao.add(professional);
        professionalDao.add(professional2);
        professionalDao.clearAll();
        assertEquals(0,professionalDao.getAllProfessionals().size());
    }
    //helper method
    public Professional setupProfessional(){
        return new Professional("John Doe","johndoe@gmail.com","Android Developer, Java Spark Specialist","Can work under pressure",1);
    }
}