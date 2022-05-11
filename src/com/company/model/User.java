package com.company.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String status;
    private Role role;
    public User(){
    }

    public User(int id, String username, String password, String name, String phone, String email, String address, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.status = "unlock";
        this.role = Role.fromValue(role);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(String str){
        String[] strArr = str.split(",");
        id = Integer.parseInt(strArr[0]);
        username = strArr[1];
        password = strArr[2];
        name = strArr[3];
        phone = strArr[4];
        email = strArr[5];
        address = strArr[6];
        status = strArr[7];
        role = Role.fromValue(strArr[8]);
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    @Override
    public String toString() {
        return id + "," + username + "," + password + "," + name + ","
                + phone + "," + email + "," + address + "," + status + "," + role;
    }
}
