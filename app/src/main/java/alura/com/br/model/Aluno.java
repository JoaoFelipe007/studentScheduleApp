package alura.com.br.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Aluno implements Serializable
{
    private Long id = 0L;
    private String nome;
    private String telefone;
    private String email;

    public Aluno() {
    }

    public Aluno(String nome, String telefone, String email) {
     this.nome = nome;
     this.telefone = telefone;
     this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }
}
