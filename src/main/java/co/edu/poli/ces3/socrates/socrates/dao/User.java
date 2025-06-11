package co.edu.poli.ces3.socrates.socrates.dao;

import co.edu.poli.ces3.socrates.socrates.utils.annotations.Column;
import co.edu.poli.ces3.socrates.socrates.utils.annotations.Table;

import javax.persistence.Id;
import java.util.Date;

@Table(name = "users")
public class User {

    @Column(name = "id_user", primaryKey = true)
    private Integer id_user;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "lastname", nullable = false, length = 180)
    private String lastname;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "gender")
    private String gender;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;


    public User() {

    }

    public User(Integer id_user, String name, String lastname, String password, Date birthdate, String email, Boolean isActive, String phone, String gender, Date createdAt, Date updatedAt, Date deletedAt){
        this.id_user = id_user;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.birthdate = birthdate;
        this.email = email;
        this.isActive = isActive;
        this.phone = phone;
        this.gender = gender;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public User(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public String toString() {

        return "Name: " + this.name + "\n"
                + "LastName: " + this.lastname;
    }
}
