package sql2o;

import interfaces.ServiceDao;
import models.Client;
import models.Service;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oServiceDaoTest {

    private Service testService = new Service("Data Science",1500, 8, 1); //instantiate new user, next user.getId();
    private Service testService2 = new Service("Data Analysis",1000, 10, 1); //instantiate new user, next user.getId();
    private Sql2oServiceDao serviceDao;

    private Sql2oClientDao clientDao;
    private Connection conn;


    @BeforeEach
    public void setUp() {
//        String connect = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        String connectionString = "jdbc:postgresql://localhost:5432/tech_connect_test"; // connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString,"nina","kabila");
        serviceDao = new Sql2oServiceDao(sql2o);
        clientDao = new Sql2oClientDao(sql2o);
        conn = sql2o.open();
    }

    @AfterEach
    void tearDown() {
        clientDao.clearAll();
        serviceDao.clearAll();
        conn.close();
    }
    @Test
    public void  add_addsNewService_true(){
        serviceDao.add(testService);
        int id = testService.getId();
        System.out.println(id);
        assertEquals("Data Science",testService.getService());
    }

    @Test
    public void  get_getsAllServices_true(){
        serviceDao.add(testService);
        serviceDao.add(testService2);
        int id = testService.getId();
        int id2 = testService2.getId();
        System.out.println(id);
        System.out.println(id2);
        assertEquals( 2, serviceDao.getAll().size());
    }

    @Test
    public void  find_findsServiceById_true(){
        serviceDao.add(testService);
        serviceDao.add(testService2);
        int id = testService.getId();
        int id2 = testService2.getId();
        assertEquals( testService2, serviceDao.findById(id2));
    }

    @Test
    public void  update_updatesServiceById_true(){
        serviceDao.add(testService);
        serviceDao.add(testService2);
        int id = testService.getId();
        int id2 = testService2.getId();
        serviceDao.update( id2, "Web Dev", 800, 40, testService2.getUser_id());
        Service updatedService = serviceDao.findById(id2);
        assertEquals( "Web Dev", updatedService.getService());
    }

    @Test
    public void  delete_deleteByIdOfService_true(){
        serviceDao.add(testService);
        serviceDao.add(testService2);
        int id = testService.getId();
        int id2 = testService2.getId();
        System.out.println(id);
        System.out.println(id2);
        serviceDao.delete(id);
        serviceDao.delete(id2);
        assertEquals(0, serviceDao.getAll().size());
    }

    @Test
    public void  amount_totalAmountOfService_true(){
        serviceDao.add(testService);
        int hours = testService.getHours();
        int hourly_price = testService.getHourly_price();
        int total = serviceDao.totalAmount(hours,hourly_price);
        System.out.println(total);
        assertEquals(12000,total);
    }

    @Test
    public void getAllServicesByClient(){
        Client client = new Client("John","jane@gmail.com","0722252522");
        Client otherClient = new Client("Jane","john@gmail.com","0722862522");
        clientDao.add(client);
        clientDao.add(otherClient);
        Service service= setupServiceForClient(client);
        Service service2= setupServiceForClient(client);
        Service serviceForOtherClient= setupServiceForClient(otherClient);
        int clientId= client.getId();
        int client2Id= otherClient.getId();
        System.out.println(clientId);
        System.out.println(client2Id);
        assertEquals(2, serviceDao.getAllServicesByClient(client.getId()).size());
        assertEquals(1, serviceDao.getAllServicesByClient(client2Id).size());
    }

    //helper
    public Service setupServiceForClient(Client client) {
        Service service = new Service(500,10,client.getId());
        serviceDao.add(service);
        return service;
    }

}