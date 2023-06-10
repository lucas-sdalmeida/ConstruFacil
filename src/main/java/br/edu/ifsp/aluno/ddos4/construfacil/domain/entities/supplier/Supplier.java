package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier;

import java.util.Objects;

public class Supplier {
    private Long id;
    private final String cnpj;
    private final String corporateName;
    private String phoneNumber;

    public Supplier(Long id, String cnpj, String corporateName, String phoneNumber) {
        this.id = id;
        this.cnpj = cnpj;
        this.corporateName = corporateName;
        this.phoneNumber = phoneNumber;
    }

    public Supplier(String cnpj, String corporateName, String phoneNumber) {
        this(null, cnpj, corporateName, phoneNumber);
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
