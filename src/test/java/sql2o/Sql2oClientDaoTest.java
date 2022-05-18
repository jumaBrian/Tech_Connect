package sql2o;

import models.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oClientDaoTest {
    private Connection conn;
    private Sql2oClientDao clientDao;

    @BeforeEach
    void setUp() {
//       String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        String connectionString = "jdbc:postgresql://localhost:5432/tech_connect_test"; // connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString, "nina", "kabila");
        clientDao = new Sql2oClientDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    void tearDown() {
        clientDao.clearAll();
        conn.close();
    }

    @Test
    void add_correctlySetsId() {
        Client client= new Client("John Doe","johndoe@gmail.com","0712345679");
        Client client2= new Client("Jane Doe","johndoe@gmail.com","0712345679");
        int originalClientId = client.getId();
        clientDao.add(client);
        clientDao.add(client2);
        System.out.println(originalClientId);
        System.out.println(client.getId());
        assertNotEquals(originalClientId, client.getId());
        assertTrue(clientDao.getAll().get(0).equals(client));
        assertTrue(clientDao.getAll().get(1).equals(client2));
    }

    @Test
    void getAll_returnsAllClients() {
        Client client= new Client("John Doe","johndoe@gmail.com","0712345679");
        Client client2= new Client("John Doe","johndoe@gmail.com","0712345679");
        Client client3= new Client("John Doe","johndoe@gmail.com","0712345679");
        clientDao.add(client);
        clientDao.add(client2);
        clientDao.add(client3);
        assertEquals(3, clientDao.getAll().size());
    }

    @Test
    void findById_returnsTheCorrectInstance() {
        Client client= new Client("John Doe","johndoe@gmail.com","0712345679");
        Client client2= new Client("Jane Doe","johndoe@gmail.com","0712345679");
        clientDao.add(client);
        clientDao.add(client2);
       Client foundClient = clientDao.findById(client.getId());
       assertEquals(foundClient,client);
    }

        @Test
    void update_CorrectlyUpdates() {
        Client client= new Client("John Doe","johndoe@gmail.com","0712345679");
        clientDao.add(client);
        clientDao.update(client.getId(),"Jane Doe","jane@gmail.com","0712345679");
        Client updatedClient = clientDao.findById(client.getId());
        assertEquals("John Doe", client.getName());
        assertEquals("Jane Doe", updatedClient.getName());
    }

    @Test
    void deleteById_correctlyRemovesThatParticularInstance() {
        Client client= new Client("John Doe","johndoe@gmail.com","0712345679");
        Client client2= new Client("John Doe","johndoe@gmail.com","0712345679");
        clientDao.add(client);
        clientDao.add(client2);
        clientDao.deleteById(client.getId());
        assertEquals(1,clientDao.getAll().size());
        Client [] clients= {client2};
        assertEquals(Arrays.asList(clients),clientDao.getAll());
    }
}