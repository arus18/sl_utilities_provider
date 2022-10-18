package com.example.sl_utilities_provider.entities;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@Entity
@Table(name = "workers")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long worker_id;


    private String nic;

    private String name;
    private String address;
    private String age;
    private String contact;
    private String email;

    private String category;
    private String transport;

    public long getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(long worker_id) {
        this.worker_id = worker_id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    private String serviceName;
    private String img;

    public Worker() {
    }


    public Worker(String serviceName,long id, String nic, String name, String address, String age, String contact, String email, List<Service> list, String category, String transport, String img) {
        this.worker_id = id;
        this.nic = nic;
        this.name = name;
        this.address = address;
        this.age = age;
        this.contact = contact;
        this.email = email;
        this.category = category;
        this.transport = transport;
        this.img = img;
        this.service = list;
        this.serviceName = serviceName;
    }

    @ManyToMany(mappedBy = "workers")
    private List<Service> service;
    public long getId() {
        return worker_id;
    }

    public void setId(long id) {
        this.worker_id = id;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Service> getService() {
        return service;
    }

    public void setService(List<Service> list) {
        this.service = list;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}