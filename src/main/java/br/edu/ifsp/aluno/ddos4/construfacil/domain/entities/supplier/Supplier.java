package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier;

public class Supplier {

    private long id;
    private String companyName;
    private String cnpj;
    private String telephone;

    public Supplier(long id, String razaoSocial, String cnpj, String telefone) {
        this.companyName = razaoSocial;
        this.cnpj = cnpj;
        this.telephone = telefone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
