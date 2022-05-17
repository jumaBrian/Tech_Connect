package models;

import interfaces.ServiceDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import sql2o.Sql2oServiceDao;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    private Service testService = new Service("Data Science",1500, 8, 1); //instantiate new user, next user.getId();
    private Service testService2 = new Service("Data Analysis",1000, 10, 1); //instantiate new user, next user.getId();
    private Sql2oServiceDao serviceDao;
    private Connection conn;


    @BeforeEach
    public void setUp() {
            String connect = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
            Sql2o sql2o = new Sql2o(connect,"","");
            serviceDao = new Sql2oServiceDao(sql2o);
            conn = sql2o.open();
    }

    @AfterEach
    void tearDown() {
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


}