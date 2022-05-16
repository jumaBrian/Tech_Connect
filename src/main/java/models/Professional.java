package models;

import java.util.Objects;

public class Professional {

    private String professional_name;
    private String email;
    private String qualification;
    private String bio;
    private int service_id;
    private int id;
    public Professional(String professional_name, String email, String qualification, String bio, int service_id) {
        this.professional_name = professional_name;
        this.email = email;
        this.qualification = qualification;
        this.bio = bio;
        this.service_id = service_id;
    }

    public String getProfessional_name() {
        return professional_name;
    }

    public void setProfessional_name(String professional_name) {
        this.professional_name = professional_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Professional that = (Professional) o;

        if (service_id != that.service_id) return false;
        if (id != that.id) return false;
        if (!Objects.equals(professional_name, that.professional_name))
            return false;
        if (!Objects.equals(email, that.email)) return false;
        if (!Objects.equals(qualification, that.qualification))
            return false;
        return Objects.equals(bio, that.bio);
    }

    @Override
    public int hashCode() {
        int result = professional_name != null ? professional_name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (qualification != null ? qualification.hashCode() : 0);
        result = 31 * result + (bio != null ? bio.hashCode() : 0);
        result = 31 * result + service_id;
        result = 31 * result + id;
        return result;
    }
}
