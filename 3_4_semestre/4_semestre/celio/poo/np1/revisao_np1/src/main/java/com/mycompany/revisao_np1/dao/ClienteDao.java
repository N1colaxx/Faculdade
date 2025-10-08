package com.mycompany.revisao_np1.dao;

import com.mycompany.revisao_np1.conexao.ConexaoJDBC;
import com.mycompany.revisao_np1.model.ClienteModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ClienteDao {
    
    private Connection conexao = null;

    public ClienteDao() {
        this.conexao = ConexaoJDBC.getConexao();
    }
    
    /** 
     * Orde para cadastrar cliente:
     *  pessoa -> cliente
     * 
     */
    public void incluir (ClienteModel cliente) throws SQLException {
        
        // desativa o auto commit  do DB
        conexao.setAutoCommit(false);
        
        final String sql_Pessoa = "INSERT INTO pessoa (" + 
                            "PES_NOME, PES_FISICA, PES_CPFCNPJ, PES_RGIE, PES_CADASTRO, " +
                            "PES_ENDERECO, PES_NUMERO, PES_COMPLEMENTO, PES_BAIRRO, PES_CIDADE, PES_UF, PES_CEP, " +
                            "PES_CELULAR, PES_SITE, PES_EMAIL, PES_ATIVO )" + 
                            "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " + 
                            "RETURING PES_CODIGO";
       
        final String sql_Cliente =
            "INSERT INTO cliente (pes_codigo, cli_limitecred) VALUES (?, ?)";
       
        
        try(PreparedStatement ps_stm = conexao.prepareStatement(sql_Pessoa)) {
            
            ps_stm.setString(1, cliente.getPES_NOME());
            ps_stm.setInt(2, cliente.getPES_FISICA());
            
            ps_stm.setString(3, cliente.getPES_CPFCNPJ());
            ps_stm.setString(4, cliente.getPES_RGIE());
            
            ps_stm.setDate(5, java.sql.Date.valueOf(cliente.getPES_CADASTRO()));
            
            ps_stm.setString(6, cliente.getPES_ENDERECO());
            ps_stm.setString(7, cliente.getPES_NUMERO());
            ps_stm.setString(8, cliente.getPES_COMPLEMENTO());
            ps_stm.setString(9, cliente.getPES_BAIRRO());
            ps_stm.setString(10, cliente.getPES_CIDADE());
            ps_stm.setString(11, cliente.getPES_UF());
            ps_stm.setString(12, cliente.getPES_CEP());
            ps_stm.setString(13, cliente.getPES_CELULAR());
            ps_stm.setString(14, cliente.getPES_SITE());
            ps_stm.setString(15, cliente.getPES_EMAIL());
            ps_stm.setInt(16, cliente.getPES_ATIVO());
            
            
            int fk_pes_codigo;
            try(ResultSet rs = ps_stm.executeQuery()) {

                if(!rs.next()) {
                    throw new SQLException(" ERRO falha no RETURING");
                }
                
                fk_pes_codigo = rs.getInt("PES_CODIGO");
            }
            
            try(PreparedStatement cli_stm = conexao.prepareStatement(sql_Cliente)) {
                
                cli_stm.setInt(1, fk_pes_codigo);
                cli_stm.setDouble(2, cliente.getCLI_LIMITECRED());
                
                int rows = cli_stm.executeUpdate(); 
                if (rows != 1) {
                    throw new SQLException("Insert em cliente não afetou 1 linha (rows = " + rows + ").");
                }
            } 
            
            conexao.commit();
            
        } throw new SQLException(" ERRO sem retorno de PES_CODIGO");
    }

    public void alterar(ClienteModel cliente) throws SQLException {
        
        
    }
}
