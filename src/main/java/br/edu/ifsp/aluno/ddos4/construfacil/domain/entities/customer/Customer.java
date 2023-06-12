package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.util.RegistrationStatus;

import java.util.Objects;

public class Customer {
    private Long id;
    private String name;
    private final String cpf;
    private String address;
    private String phoneNumber;
    private RegistrationStatus status;

    public Customer(Long id, String name, String cpf, String address, String phoneNumber, RegistrationStatus status) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public Customer(String name, String cpf, String address, String phoneNumber) {
        this(null, name, cpf, address, phoneNumber, RegistrationStatus.ACTIVE);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
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

    public RegistrationStatus getStatus() {
        return status;
    }

    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return cpf.equals(customer.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
