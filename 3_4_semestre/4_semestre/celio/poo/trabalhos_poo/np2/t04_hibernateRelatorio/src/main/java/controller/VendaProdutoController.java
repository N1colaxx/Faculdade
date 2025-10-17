package controller;

import model.VendaProdutoModel;
import dao.VendaProdutoDao;
import relatorios.VendaProdutoRelatorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendaProdutoController implements GenericController<VendaProdutoModel> {

    VendaProdutoDao vendaProdutoDao; 
    
    private String operacao;
    
    public VendaProdutoController() {
        vendaProdutoDao = new VendaProdutoDao();
        operacao = null;
    }

    @Override
    public void incluir(VendaProdutoModel obj) throws Exception {
        vendaProdutoDao.incluir(obj);
    }

    @Override
    public void alterar(VendaProdutoModel obj) throws Exception {
        vendaProdutoDao.alterar(obj);
    }

    @Override
    public void excluir(VendaProdutoModel obj) throws Exception {
        vendaProdutoDao.excluir(obj);
    }

    @Override
    public ArrayList<VendaProdutoModel> consultar(String filtro) {
        return vendaProdutoDao.consultar(filtro);
    }

    @Override
    public void gravar(VendaProdutoModel obj, String operacao) throws Exception {
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
            VendaProdutoRelatorio relatorio = new VendaProdutoRelatorio();

            Map<String, Object> parametros = new HashMap<>();

            // Adicione parâmetros necessários se houver
            // parametros.put("PARAMETRO_EXEMPLO", "valor");
            List<VendaProdutoModel> dados = consultar("");

            relatorio.gerarRelatorio(dados);

        } catch (Exception ex) {
            retorno = ex;
            ex.printStackTrace(); // Para debug
        }

        return retorno;
    }
    
    public VendaProdutoModel buscarProduto_ProCodigo(int cod) throws Exception {
        return vendaProdutoDao.get(cod);
    }
    
       
    // busca um lista completa
    public ArrayList<VendaProdutoModel> buscarPorVdaCodigo(Integer vda_cod, String op) throws Exception {
        if (op.equals("consultaPorVdaCodigo")) {
            System.out.println(" [VendaProdutoController] Operacao recebida = " + op);
            this.operacao = op;
        }
        
        String cond = " v.vda_codigo = :vda_codigo";
        return new ArrayList<>(vendaProdutoDao.consultar(cond));
    }
    
    //busca o 1 item da lista
    public VendaProdutoModel buscarPrimeiroPorVdaCodigo(Integer vda_cod, String op) throws Exception {
        if (!op.isEmpty() && op.equals("consultaPorVdaCodigo")) {
            operacao = op;
        }
        
        String cond = " v.vda_codigo = :vda_codigo";
        
        ArrayList<VendaProdutoModel> lista = new ArrayList<>(vendaProdutoDao.consultar(cond));
        return (lista == null || lista.isEmpty()) ? null : lista.get(0);
    }
    
    public void inserirItens(int vdaCodigo, ArrayList<VendaProdutoModel> itens) throws Exception {
        if (itens == null || itens.isEmpty()) {
            throw new Exception("Nenhum item para inserir.");
        }

        vendaProdutoDao.inserirItens(vdaCodigo, itens);
    }

    
    /**
     * Getters
     */
    
    public VendaProdutoDao getDao() {
        return vendaProdutoDao;
    }
    
    public String getOperacao() {
        return operacao;
    }
    
}


