package controller;

import model.VendapagtoModel;
import dao.VendapagtoDao;
import relatorios.VendapagtoRelatorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendapagtoController implements GenericController<VendapagtoModel> {

    VendapagtoDao vendapagtoDao; 
    private String operacao;
    
    public VendapagtoController() {
        vendapagtoDao = new VendapagtoDao();
        operacao = null;
    }

    @Override
    public void incluir(VendapagtoModel obj) throws Exception {
        vendapagtoDao.incluir(obj);
    }

    @Override
    public void alterar(VendapagtoModel obj) throws Exception {
        vendapagtoDao.alterar(obj);
    }

    @Override
    public void excluir(VendapagtoModel obj) throws Exception {
        vendapagtoDao.excluir(obj);
    }

    @Override
    public ArrayList<VendapagtoModel> consultar(String filtro) {
        return vendapagtoDao.consultar(filtro);
    }

    @Override
    public void gravar(VendapagtoModel obj, String operacao) throws Exception {
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
            VendapagtoRelatorio relatorio = new VendapagtoRelatorio();

            Map<String, Object> parametros = new HashMap<>();

            // Adicione parâmetros necessários se houver
            // parametros.put("PARAMETRO_EXEMPLO", "valor");
            List<VendapagtoModel> dados = consultar("");

            relatorio.gerarRelatorio(dados);

        } catch (Exception ex) {
            retorno = ex;
            ex.printStackTrace(); // Para debug
        }

        return retorno;
    }

    public VendapagtoModel buscarPorCodigo(Integer cod) throws Exception {
        return vendapagtoDao.get(cod); 
    }
    
    public VendapagtoDao getDao() {
        return vendapagtoDao;
    }
    
    public ArrayList<VendapagtoModel> buscarPorVdaCodigo(Integer VDA_CODIGO, String op) throws Exception {
        
        if (!op.isEmpty() && op.equals("consultaPorVdaCodigo")) {
            operacao = op;
        }
        String cond = " v.vda_codigo = :vda_codigo";
        return new ArrayList<>(vendapagtoDao.consultar(cond));
    }
    
    
    
    public void inserirPgtos(int vdaCodigo, ArrayList<VendapagtoModel> pgtos) throws Exception {
        if (pgtos == null || pgtos.isEmpty()) {
            throw new Exception("Nenhum pagamento para inserir.");
        }

        vendapagtoDao.inserirPgtos(vdaCodigo, pgtos);
    }
    
    public String getOperacao(){
        return operacao;
    }
    
}



