package com.delivery.kaufen.kaufen.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Client {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("cpf")
    private String CPF;
    @SerializedName("birthdate")
    private String birthdate;

    public Client(String id, String name, String CPF, String birthdate) {
        this.id = id;
        this.name = name;
        this.CPF = CPF;
        this.birthdate = birthdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
