package sql2o;

import interfaces.ServiceDao;
import models.Service;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oServiceDao implements ServiceDao {

    private final Sql2o sql2o;

    public Sql2oServiceDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    @Override
    public void add(Service service) {
        String sql = "INSERT INTO services (service, hourly_price ,hours, user_id) VALUES (:service,:hourly_price, :hours, :user_id)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(service)
                    .executeUpdate()
                    .getKey();
            service.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Service> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM services;")
                    .executeAndFetch(Service.class);
        }
    }

    @Override
    public void update(Service service) {

    }

    @Override
    public void delete(int id) {
        try(Connection con = sql2o.open()){
                String sql = "DELETE FROM services WHERE id=:id;";
                con.createQuery(sql)
                        .addParameter("id",id)
                        .executeUpdate();
        }
    }
}
