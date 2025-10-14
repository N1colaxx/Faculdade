package controller;

import dao.UsuarioDao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.SessionModel;
import model.UsuarioModel;
import relatorios.UsuarioRelatorio;

public class UsuarioController implements GenericController<UsuarioModel> {
    
    UsuarioDao usuariodao;

    public UsuarioController() {
        usuariodao = new UsuarioDao();
    }

    @Override
    public void incluir(UsuarioModel obj) throws Exception {
        usuariodao.incluir(obj);
    }

    @Override
    public void alterar(UsuarioModel obj) throws Exception {
        usuariodao.alterar(obj);
    }

    @Override
    public void excluir(UsuarioModel obj) throws Exception {
        usuariodao.excluir(obj);
    }

    @Override
    public ArrayList<UsuarioModel> consultar(String filtro) {
        return usuariodao.consultar(filtro);
    }

    @Override
    public void gravar(UsuarioModel obj, String operacao) throws Exception {
        if (operacao.equals("incluir")) {
            incluir(obj);
        } else {
            alterar(obj);
        }
    }

    @Override
    public Exception imprimir() {
        Exception retorno = null;
        try {
            UsuarioRelatorio relatorio = new UsuarioRelatorio();

            Map<String, Object> parametros = new HashMap<>();

            // Adicione parâmetros necessários se houver
            // parametros.put("PARAMETRO_EXEMPLO", "valor");
            List<UsuarioModel> dados = consultar("");

            relatorio.gerarRelatorio(dados);

        } catch (Exception ex) {
            retorno = ex;
            ex.printStackTrace(); // Para debug
        }

        return retorno;
    }
    
    public boolean autenticar(String email, String senha) {
        String filtro = "usu_email = :email AND usu_senha = :senha"; 
        
        try {
            UsuarioModel u = usuariodao.consultar(filtro);
            return (u != null && u.getUSU_ATIVO() == 1); // << ativo = 1
        } catch (Exception e) {
            System.out.println("Erro ao autenticar: " + e.getMessage());
            return false;
        }
    }
    
}
