package com.jvcan.sportify.DB.model;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.util.Objects;

public class Usuario {

    private String nome;
    private String email;
    private String senha;

    public Usuario(){};

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    public String getNome(){
        return nome;
    }

    public void setNome(){
        this.nome = nome;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(){
        this.email = email;
    }
    public String getSenha(){
        return senha;
    }

    public void setSenha(){
        this.senha = senha;
    }

}