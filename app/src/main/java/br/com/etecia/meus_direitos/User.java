package br.com.etecia.meus_direitos;

public class User {
    private int id;
    private String username;
    private String email;
    private String cidade;
    private String estado;
    private String numero_oab;
    private String telefone_cel;
    private int idade;



    public User(int id, String username, String email, int idade, String cidade, String estado, String numero_oab, String telefone_cel ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.idade = idade;
        this.cidade = cidade;
        this.estado = estado;
        this.numero_oab = numero_oab;
        this.telefone_cel = telefone_cel;
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getIdade() {
        return idade;
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
