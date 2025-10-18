package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "COMPRA")
public class CompraModel {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "CPR_CODIGO", nullable = false)
    private Integer cpr_codigo;
    
    @ManyToOne
    @JoinColumn(name = "USU_CODIGO", nullable = false)
    private UsuarioModel usuario_compra;
    
    @ManyToOne
    @JoinColumn(name = "FOR_CODIGO", nullable = false)
    private FornecedorModel fornecedor_compra;
    
    @OneToMany (mappedBy = "compra_compraPro", cascade =  CascadeType.ALL, orphanRemoval = true)
    private List<CompraProdutoModel> listProduto_compra = new ArrayList<>();
    
    private LocalDate cpr_emissao;
    private double cpr_valor;
    private double cpr_desconto;
    private double cpr_total;
    private LocalDate cpr_dtentrada;
    private String cpr_obs;
  
    
    public CompraModel(){
        
    }

    public CompraModel(Integer cpr_codigo, UsuarioModel usuario_compra, FornecedorModel fornecedor_compra, LocalDate cpr_emissao, double cpr_valor, double cpr_desconto, double cpr_total, LocalDate cpr_dtentrada, String cpr_obs) {
        this.cpr_codigo = cpr_codigo;
        this.usuario_compra = usuario_compra;
        this.fornecedor_compra = fornecedor_compra;
        this.cpr_emissao = cpr_emissao;
        this.cpr_valor = cpr_valor;
        this.cpr_desconto = cpr_desconto;
        this.cpr_total = cpr_total;
        this.cpr_dtentrada = cpr_dtentrada;
        this.cpr_obs = cpr_obs;
    }
    
    
    public void addProduto_listProduto(CompraProdutoModel cp){
        this.listProduto_compra.add(cp);
//        cp.setCompra_compraProduto(this);
    }

    public void limparListaProduto(){
        this.listProduto_compra.clear();
    }
    /**
     * Getters
     * @return 
     */
    
    public Integer getCpr_codigo() {
        return cpr_codigo;
    }

    public UsuarioModel getUsuario_compra() {
        return usuario_compra;
    }

    public FornecedorModel getFornecedor_compra() {
        return fornecedor_compra;
    }

    public List<CompraProdutoModel> getListProduto_compra() {
        return listProduto_compra;
    }

    @Column (name = "CPR_EMISSAO", nullable = true)
    public LocalDate getCpr_emissao() {
        return cpr_emissao;
    }

    @Column (name = "CPR_VALOR", nullable = false, precision = 18, scale = 2)
    public double getCpr_valor() {
        return cpr_valor;
    }

    @Column (name = "CPR_DESCONTO", nullable = true, precision = 18, scale = 2)
    public double getCpr_desconto() {
        return cpr_desconto;
    }

    @Column (name = "CPR_TOTAL", nullable = false, precision = 18, scale = 2)
    public double getCpr_total() {
        return cpr_total;
    }

    @Column (name = "CPR_DTENTRADA", nullable = true)
    public LocalDate getCpr_dtentrada() {
        return cpr_dtentrada;
    }

    @Column (name = "CPR_OBS", nullable = true)
    public String getCpr_obs() {
        return cpr_obs;
    }

    
    /**
     * Setters
     * @param cpr_codigo
     */
    public void setCpr_codigo(Integer cpr_codigo) {
        this.cpr_codigo = cpr_codigo;
    }
    
    public void setUsuario_compra(UsuarioModel usuario_compra) {
        this.usuario_compra = usuario_compra;
    }

    public void setFornecedor_compra(FornecedorModel fornecedor_compra) {
        this.fornecedor_compra = fornecedor_compra;
    }

    public void setListProduto_compra(List<CompraProdutoModel> listProduto_compra) {
        this.listProduto_compra = listProduto_compra;
    }

    public void setCpr_emissao(LocalDate cpr_emissao) {
        this.cpr_emissao = cpr_emissao;
    }

    public void setCpr_valor(double cpr_valor) {
        this.cpr_valor = cpr_valor;
    }

    public void setCpr_desconto(double cpr_desconto) {
        this.cpr_desconto = cpr_desconto;
    }

    public void setCpr_total(double cpr_total) {
        this.cpr_total = cpr_total;
    }

    public void setCpr_dtentrada(LocalDate cpr_dtentrada) {
        this.cpr_dtentrada = cpr_dtentrada;
    }

    public void setCpr_obs(String cpr_obs) {
        this.cpr_obs = cpr_obs;
    }
    
}
