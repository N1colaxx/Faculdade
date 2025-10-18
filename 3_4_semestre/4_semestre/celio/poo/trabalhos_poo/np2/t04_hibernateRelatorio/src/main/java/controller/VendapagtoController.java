package controller;

import model.VendaPagtoModel;
import dao.VendaPagtoDao;
import relatorios.VendaPagtoRelatorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class VendapagtoController implements GenericController<VendaPagtoModel> {

    VendaPagtoDao vendapagtoDao; 
    private String operacao;
    
    public VendapagtoController() {
        vendapagtoDao = new VendaPagtoDao();
        operacao = null;
    }

    @Override
    public void incluir(VendaPagtoModel obj) throws Exception {
        vendapagtoDao.incluir(obj);
    }

    @Override
    public void alterar(VendaPagtoModel obj) throws Exception {
        vendapagtoDao.alterar(obj);
    }

    @Override
    public void excluir(VendaPagtoModel obj) throws Exception {
        vendapagtoDao.excluir(obj);
    }

    @Override
    public ArrayList<VendaPagtoModel> consultar(String filtro) {
        return vendapagtoDao.consultar(filtro);
    }

    @Override
    public void gravar(VendaPagtoModel obj, String operacao) throws Exception {
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
            VendaPagtoRelatorio relatorio = new VendaPagtoRelatorio();

            Map<String, Object> parametros = new HashMap<>();

            // Adicione parâmetros necessários se houver
            // parametros.put("PARAMETRO_EXEMPLO", "valor");
            List<VendaPagtoModel> dados = consultar("");

            relatorio.gerarRelatorio(dados);

        } catch (Exception ex) {
            retorno = ex;
            ex.printStackTrace(); // Para debug
        }

        return retorno;
    }

    public VendaPagtoModel buscarPorCodigo(Integer cod) throws Exception {
        return vendapagtoDao.get(cod); 
    }
    
    public VendaPagtoModel get(int cod) throws Exception {
        VendaPagtoModel pg_valido = vendapagtoDao.get(cod);
        if(pg_valido.getVdp_codigo() == null || pg_valido.getVdp_codigo() < 0){
            JOptionPane.showMessageDialog(null, "ERRO! Não encontramos em Forma de Pagamento, uma Venda  com esse Codigo.");
            return null;
        }
        return pg_valido; 
    }
    
    /**
     * Getters
     */
    
    public VendaPagtoDao getDao() {
        return vendapagtoDao;
    }
    
    public String getOperacao(){
        return operacao;
    }
    
}



