package com.tavisca.springapplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int uid;
    private String firstName;
    private String lastName;
    private String dob;
    private String email;
    private String address;
    private String phoneNumber;
    private int departmentId;
    private Long salary;
    private String dateOfJoining;
    private String createdBy;
    private String username;
    private String password;
    private String createdOn;

    public User(String firstName, String lastName, String dob, String email,
                String address, String phoneNumber, int departmentId, Long salary, String dateOfJoining, String createdBy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.departmentId = departmentId;
        this.salary = salary;
        this.dateOfJoining = dateOfJoining;
        this.createdBy = createdBy;
    }

    public User(String firstName, String lastName, String dob, String email, String address, String phoneNumber,
                int departmentId, Long salary, String dateOfJoining, String createdBy, String username, String password, String createdOn) {
//        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.departmentId = departmentId;
        this.salary = salary;
        this.dateOfJoining = dateOfJoining;
        this.createdBy = createdBy;
        this.username = username;
        this.password = password;
        this.createdOn = createdOn;
    }

    public User() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

}
