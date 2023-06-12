package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.util.RegistrationStatus;

import java.util.Objects;

public class Supplier {
    private Long id;
    private final String cnpj;
    private final String corporateName;
    private String phoneNumber;
    private RegistrationStatus status;

    public Supplier(Long id, String cnpj, String corporateName, String phoneNumber, RegistrationStatus status) {
        this.id = id;
        this.cnpj = cnpj;
        this.corporateName = corporateName;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public Supplier(String cnpj, String corporateName, String phoneNumber) {
        this(null, cnpj, corporateName, phoneNumber, RegistrationStatus.ACTIVE);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public String getCnpj() {
        return cnpj;
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
        Supplier supplier = (Supplier) o;
        return cnpj.equals(supplier.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }
}
