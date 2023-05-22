package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier;

public class Supplier {
    private Long id;
    private final String cnpj;
    private String corporateName;
    private String phoneNumber;

    public Supplier(Long id, String cnpj, String corporateName, String phoneNumber) {
        this.id = id;
        this.cnpj = cnpj;
        this.corporateName = corporateName;
        this.phoneNumber = phoneNumber;
    }

    public Supplier(String cnpj, String corporateName, String phoneNumber) {
        this.cnpj = cnpj;
        this.corporateName = corporateName;
        this.phoneNumber = phoneNumber;
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

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
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
}
