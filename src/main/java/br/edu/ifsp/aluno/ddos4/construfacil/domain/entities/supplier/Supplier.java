package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier;

public class Supplier {
    private long id;
    private String corporateName;
    private String phoneNumber;

    public Supplier(long id, String corporateName, String phoneNumber) {
        this.id = id;
        this.corporateName = corporateName;
        this.phoneNumber = phoneNumber;
    }

    public Supplier(String corporateName, String phoneNumber) {
        this.corporateName = corporateName;
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
