package com.jvcan.sportify.DB.interfaces;

import com.jvcan.sportify.DB.model.Usuario;

import java.util.List;

public interface iUsuarioDAO {

    public boolean create(Usuario usuario);
    public Usuario read(String email);
    public boolean update(Usuario usuario);
    public boolean delete(String email);
    public List<Usuario> getData();

}