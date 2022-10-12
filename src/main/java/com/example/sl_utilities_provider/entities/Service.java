package com.example.sl_utilities_provider.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long serviceId;

    private String name;
    private String price;

    private String category;

    @Transient
    private List<String> workerIDs;

    @ManyToMany
    @JoinTable(
            name = "service_worker",
            joinColumns = @JoinColumn(name = "serviceId"),
            inverseJoinColumns = @JoinColumn(name = "workerId"))
    private List<Worker> workers = new ArrayList<>();

    @Column(nullable = true, length = 64)
    private String image;

    public Service() {
    }

    public Service(long serviceId, String name, String price, String category, List<String> workerIDs, List<Worker> workers, String image) {
        this.serviceId = serviceId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.workerIDs = workerIDs;
        this.workers = workers;
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getWorkerIDs() {
        return workerIDs;
    }

    public void setWorkerIDs(List<String> workerIDs) {
        this.workerIDs = workerIDs;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getId() {
        return serviceId;
    }

    public void setId(long id) {
        this.serviceId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
