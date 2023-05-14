package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer;

public class Customer {

    private Long ID;
    private String Name;
    private String CPF;
    private String Address;
    private String PhoneNumber;

    public Customer(Long ID, String name, String CPF, String address, String phoneNumber) {
        this.ID = ID;
        Name = name;
        this.CPF = CPF;
        Address = address;
        PhoneNumber = phoneNumber;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}
