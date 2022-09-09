package com.example.sl_utilities_provider.entities;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "workers")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long workerId;

    private String name;

    @ManyToMany(mappedBy = "workers")
    private List<Service> service;

    public Worker() {
    }

    public Worker(long id, String name, List<Service> service) {
        this.workerId = id;
        this.name = name;
        this.service = service;
    }

    public long getId() {
        return workerId;
    }

    public void setId(long id) {
        this.workerId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Service> getService() {
        return service;
    }

    public void setService(List<Service> service) {
        this.service = service;
    }
}
