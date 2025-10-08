package com.mycompany.revisao_np1.model.controller;

import com.mycompany.revisao_np1.model.UsuarioModel;
import com.mycompany.revisao_np1.dao.UsuarioDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UsuarioController {
    
    private UsuarioDao dao;
    
    private List<UsuarioModel> listaUsuarios;
    
    private UsuarioController () {
        this.dao = new UsuarioDao();
    }

    public void adicionar(UsuarioModel usuario) throws SQLException {
        dao.adicionar(usuario);
    }
    
    public void alterar(UsuarioModel usuario) throws SQLException {
        dao.alterar(usuario);
    }
    
    public void excluir(UsuarioModel usuario) throws SQLException {
        dao.excluir(usuario);
    }
    
    public ArrayList<UsuarioModel> consultar(String condicao) throws SQLException {
        listaUsuarios = dao.consultar(condicao);
        return  (ArrayList<UsuarioModel>) listaUsuarios;
    }
}
