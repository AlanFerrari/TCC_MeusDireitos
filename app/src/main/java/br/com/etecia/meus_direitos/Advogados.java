package br.com.etecia.meus_direitos;

import java.util.Date;

public class Advogados {
    private String userName;
    private Date DataNacimento;
    private String email;
    private String telefone;
    private String cidade;
    private String estado;
    private String RegistroOAB;
    private String senha;

    public Advogados(String userName, Date dataNacimento, String email, String telefone, String cidade, String estado, String registroOAB, String senha) {
        this.userName = userName;
        DataNacimento = dataNacimento;
        this.email = email;
        this.telefone = telefone;
        this.cidade = cidade;
        this.estado = estado;
        RegistroOAB = registroOAB;
        this.senha = senha;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDataNacimento() {
        return DataNacimento;
    }

    public void setDataNacimento(Date dataNacimento) {
        DataNacimento = dataNacimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRegistroOAB() {
        return RegistroOAB;
    }

    public void setRegistroOAB(String registroOAB) {
        RegistroOAB = registroOAB;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
