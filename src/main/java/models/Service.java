package models;

import java.util.Objects;

public class Service {
    private  int id;
    private String service;
    private int hourly_price;
    private int hours;

    private int user_id;

    public Service(int hourly_price, int hours, int user_id) {
        this.hourly_price = hourly_price;
        this.hours = hours;
        this.user_id = user_id;
    }

    public Service(String service, int hourly_price, int hours, int user_id) {
        this.service = service;
        this.hourly_price = hourly_price;
        this.hours = hours;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getHourly_price() {
        return hourly_price;
    }

    public void setHourly_price(int hourly_price) {
        this.hourly_price = hourly_price;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service1 = (Service) o;
        return id == service1.id && hourly_price == service1.hourly_price && hours == service1.hours && user_id == service1.user_id && service.equals(service1.service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, service, hourly_price, hours, user_id);
    }
}
