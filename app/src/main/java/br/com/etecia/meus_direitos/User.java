package br.com.etecia.meus_direitos;

public class User {
    private int id;
    private String usuario;
    private String email;
    private String cidade;
    private String estado;
    private String numero_oab;
    private String telefone_cel;



    public User(int id, String usuario, String email, String cidade, String estado, String numero_oab, String telefone_cel ) {
        this.id = id;
        this.usuario = usuario;
        this.email = email;
        this.cidade = cidade;
        this.estado = estado;
        this.numero_oab = numero_oab;
        this.telefone_cel = telefone_cel;
    }


    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getEmail() {
        return email;
    }

    public String getcidade() {
        return  cidade;
    }

    public String getestado() {
        return estado;
    }

    public String getnumero_oab() {
        return numero_oab;
    }

    public String getTelefone_cel() {
        return telefone_cel;
    }
}
