package sql2o;

import interfaces.ProfessionalDao;
import models.Professional;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oProfessionalDao implements ProfessionalDao {
    private final Sql2o sql2o;

    public Sql2oProfessionalDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Professional professional) {
        String sql = "INSERT INTO professionals (professional_name, email, qualification, bio, service_id) VALUES (:professional_name, :email, :qualification, :bio, :service_id)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(professional)
                    .executeUpdate()
                    .getKey();
            professional.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<Professional> getAllProfessionals() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM professionals")
                    .executeAndFetch(Professional.class);
        }
    }

    @Override
    public Professional findProfessionalById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM professionals WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Professional.class);
        }
    }

    @Override
    public void update(int id, String professional_name, String bio, String qualification, String email, int service_id) {
        String sql = "UPDATE professionals SET (professional_name, bio, qualification, service_id, email) = (:professional_name, :bio, :qualification, :service_id, :email) WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("professional_name", professional_name)
                    .addParameter("bio", bio)
                    .addParameter("qualification", qualification)
                    .addParameter("service_id", service_id)
                    .addParameter("email", email)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from professionals WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void clearAll() {
        String sql = "DELETE from professionals";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
