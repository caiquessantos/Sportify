package com.jvcan.sportify.DB.controller;

import android.annotation.SuppressLint;
import android.content.Context;


import com.jvcan.sportify.DB.DAO.UsuarioDAO;
import com.jvcan.sportify.DB.model.Usuario;
import com.jvcan.sportify.DB.interfaces.iUsuarioDAO;
import com.jvcan.sportify.DB.utils.PasswordUtil;

import java.util.List;
/**A classe ControllerUsuario implementa metodos para manipular os dados de Usuario*/
public class ControllerUsuario {

    private iUsuarioDAO iusuarioDAO;
    /**
     * Construtor da classe
     * @param context
     * sem retorno
     * */
    public ControllerUsuario(Context context) {
        this.iusuarioDAO = new UsuarioDAO(context);
    }

    /**
     * Cria um novo Usuario e persiste no Banco de Dados
     * @param nome nome do usuario
     * @param email email do usuario (deve ser unico)
     * @param senha senha do usuario
     * */
    public boolean create(String nome, String email, String senha) {
        return iusuarioDAO.create(new Usuario(nome, email, senha));
    }

    /**
     * Procura pelo usuario especificado e retorna o objeto.
     * @param email email do usuario
     * @return Retorna o objeto Usuario
     * */
    public Usuario read(String email){
        return this.iusuarioDAO.read(email);
    }
    /**
     * Atualiza as informacoes do usuario (o email deve se manter o mesmo)
     * @param nome nome do usuario
     * @param email email do usuario
     * @param senha senha do usuario
     * @return Retorna true ou false
     * */
    public boolean update(String nome, String email, String senha){
        return this.iusuarioDAO.update(new Usuario(nome, email, senha));
    }
    /**
     * Deleta o usuario.
     * @param email email do usuario
     * @return Retorna true ou false.
     * */
    public boolean delete(String email){
        return this.iusuarioDAO.delete(email);
    }
    /**
     * Verifica se o email informado ja consta no banco de dados.
     * @param email email do usuario
     * @return Retorna true ou false
     * */
    public boolean testaUsuario(String email){
        Usuario usuario = this.iusuarioDAO.read(email);
        if(usuario != null){
            if(usuario.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }
    /**
     * Autentica o email e senha
     * @param email email do usuario
     * @param senha senha do usuario
     * @return Retorna true ou false
     * */

    public Boolean login(String email, String senha){
        Usuario usuario = this.iusuarioDAO.read(email);
        boolean logged = false;
        if(usuario!=null) {
            if (usuario.getEmail().equals(email) && PasswordUtil.checkPassword(senha, usuario.getSenha())) {
                logged = true;
            }
        }else {
            return false;
        }
        return logged;
    }
    /**
     * Busca a lista de todos os Usuarios contidos no Banco de Dados
     * @return Retorna lista de Usuarios
     * */
    public List<Usuario> getAll(){
        return iusuarioDAO.getData();
    }
}