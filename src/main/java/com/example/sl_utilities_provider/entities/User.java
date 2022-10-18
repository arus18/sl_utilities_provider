package com.example.sl_utilities_provider.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;


@Entity
@Table(name= "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;



    @Column(name = "first_name", length = 20)
    @NotNull
    private String firstName;

    @Column(name = "last_name",  length = 20)
    @NotNull
    private String lastName;

    @Column(name = "email", unique = true, length = 45 )
    @NotNull
    private String email;

    @Column(name = "password", length = 64)
    @NotNull
    private String password;


    @Column(name = "address", length = 150)
    @NotNull
    private String address;


    @Column(name = "gender", length = 10)
    @NotNull
    private String gender;




    public User() {
    }

    public User(long id, String firstName, String lastName, String email,String password,
                String address, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.gender = gender;

    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword(){return password;}

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }
}
