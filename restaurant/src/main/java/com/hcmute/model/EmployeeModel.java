package com.hcmute.model;

import org.springframework.web.multipart.MultipartFile;

public class EmployeeModel {
    private int id;
    private String name;
    private String address;
    private String phone;
    private int salary;
    private String img;

    private MultipartFile profileImage;

    public MultipartFile getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(MultipartFile profileImage) {
        this.profileImage = profileImage;
    }

    //constructor
    public EmployeeModel(){
    }
    public EmployeeModel(int id, String name, String address, String phone, int salary, String img){
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
        this.img = img;
    }

    //Getter and Setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "emp_model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", salary=" + salary +
                '}';
    }
}


