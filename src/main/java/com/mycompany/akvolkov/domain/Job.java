package com.mycompany.akvolkov.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String titleJobs ;
    private String salary;
    private String company;
    private String contact;
    private String phone;
    private String typeEmployment;
    private String description;

    public Job() {
    }

    public Job(String titleJobs, String salary, String company, String contact, String phone, String typeEmployment, String description) {
        this.titleJobs = titleJobs;
        this.salary = salary;
        this.company = company;
        this.contact = contact;
        this.phone = phone;
        this.typeEmployment = typeEmployment;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitleJobs() {
        return titleJobs;
    }

    public void setTitleJobs(String titleJobs) {
        this.titleJobs = titleJobs;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTypeEmployment() {
        return typeEmployment;
    }

    public void setTypeEmployment(String typeEmployment) {
        this.typeEmployment = typeEmployment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", titleJobs='" + titleJobs + '\'' +
                ", salary='" + salary + '\'' +
                ", company='" + company + '\'' +
                ", contact='" + contact + '\'' +
                ", phone='" + phone + '\'' +
                ", typeEmployment='" + typeEmployment + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
