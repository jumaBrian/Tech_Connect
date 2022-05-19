import models.Client;
import models.Service;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import sql2o.Sql2oClientDao;
import sql2o.Sql2oServiceDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    //Assign heroku port
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        String connectionString = "jdbc:postgresql://localhost:5432/tech_connect";
        Sql2o sql2o = new Sql2o(connectionString, "nina", "kabila");

        Sql2oClientDao clientDao = new Sql2oClientDao(sql2o);
        Sql2oServiceDao serviceDao = new Sql2oServiceDao(sql2o);

        //route to take us to homepage
        get("/",(request,respond)->{
            Map<String,Object>model=new HashMap<>();
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());


        //route for about navbar to take us to about page
        get("/about",(request,respond)->{
            Map<String,Object>model=new HashMap<>();
            return new ModelAndView(model,"about.hbs");
        }, new HandlebarsTemplateEngine());

        //route for services navbar text to take us to services page
        get("/services",(request,respond)->{
            Map<String,Object>model=new HashMap<>();
            return new ModelAndView(model,"services.hbs");
        }, new HandlebarsTemplateEngine());

        //route for contacts navbar text to take us to contact page
        get("/contacts",(request,respond)->{
            Map<String,Object>model=new HashMap<>();
            return new ModelAndView(model,"contacts.hbs");
        }, new HandlebarsTemplateEngine());

        //Client
        get("/clients/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "client-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to create a new client
        post("/clients/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            String email = req.queryParams("email");
            String contact = req.queryParams("contact");
            Client newClient = new Client(name,email,contact);
            clientDao.add(newClient);
            Client client =clientDao.findById(newClient.getId());
            model.put("clients", client);
            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());
        get("/clients/:id/services/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "billing-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/clients/:id/services/new", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            int hours = Integer.parseInt(req.queryParams("hours"));
            int user_id = Integer.parseInt(req.queryParams("user_id"));
            Service newService = new Service(1000,hours,user_id);
            serviceDao.add(newService);
            int total= serviceDao.totalAmount(1000,hours);
            clientDao.addService(newService);
            List<Service> list = clientDao.getAllServicesByClient(user_id);
            Client client = clientDao.findById(user_id);
            model.put("total", total);
            model.put("services", list);
            model.put("clients", client);
            model.put("service", newService);
            return new ModelAndView(model,"checkout.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
