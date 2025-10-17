package model;

import jakarta.persistence.CascadeType;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "VENDA")
public class VendaModel {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "VDA_CODIGO")
    private Integer vda_codigo;

    // Venda 1:N -> {} 1 <- Usuario
    @ManyToOne
    @JoinColumn (name = "USU_CODIGO")
    private UsuarioModel usuario;
    
    // Venda 1:N -> {} <- 1 Cliente
    @ManyToOne 
    @JoinColumn (name = "CLI_CODIGO")
    private ClienteModel cliente;
    
    @OneToMany(mappedBy = "venda_vendaPro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VendaProdutoModel> listItens = new ArrayList<>();

    @OneToMany(mappedBy = "venda_vendaPagto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VendaPagtoModel> listPagtos = new ArrayList<>();
    
    private LocalDate vda_data;
    private double vda_valor;
    private double vda_desconto;
    private double vda_total;
    private String vda_obs;
    
    public VendaModel () {
    }

    public VendaModel(Integer vda_codigo, UsuarioModel usu_codigo, ClienteModel cli_codigo, LocalDate vda_data, double vda_valor, double vda_desconto, double vda_total, String vda_obs) {
        this.vda_codigo = vda_codigo;
        this.usuario = usu_codigo;
        this.cliente = cli_codigo;
        this.vda_data = vda_data;
        this.vda_valor = vda_valor;
        this.vda_desconto = vda_desconto;
        this.vda_total = vda_total;
        this.vda_obs = vda_obs;
    }
    
      // Método auxiliar para adicionar produtos
    public void adicionarVendaProduto(VendaProdutoModel vp) {
        this.listItens.add(vp);
        vp.setVenda_VendaProduto(this);
    }
    
    // Método auxiliar para adicionar pagamentos
    public void adicionarVendaPagto(VendaPagtoModel vp) {
        this.listPagtos.add(vp);
//        vp.setVenda_VendaPagto(this);
    }
    
    
    /**
     *  GETTERS
     */
     

    public Integer getVda_codigo() {
        return vda_codigo;
    }

    public UsuarioModel getUsu_venda() {
        return usuario;
    }

    public ClienteModel getCli_venda() {
        return cliente;
    }
    
    public List<VendaProdutoModel> getListItens_venda() {
        return listItens;
    }
    
    public List<VendaPagtoModel> getListPagtos_venda() {
        return listPagtos;
    }

    @Column (name = "VDA_DATA", nullable = false)
    public LocalDate getVda_data() {
        return vda_data;
    }

    @Column (name = "VDA_VALOR", nullable = true, precision = 18, scale = 2)
    public double getVda_valor() {
        return vda_valor;
    }

    @Column (name = "VDA_DESCONTO", nullable = true, precision = 18, scale = 2)
    public double getVda_desconto() {
        return vda_desconto;
    }
    
    @Column (name = "VDA_VALOR", nullable = true, precision = 18, scale = 2)
    public double getVda_total() {
        return vda_total;
    }

    @Column (name = "VDA_DESCONTO", nullable = true, precision = 18, scale = 2)
    public String getVda_obs() {
        return vda_obs;
    }
    
    /**
     * Setters
     */

    public void setVda_codigo(Integer vda_codigo) {
        this.vda_codigo = vda_codigo;
    }

    public void setUsu_venda(UsuarioModel usu_codigo) {
        this.usuario = usu_codigo;
    }

    public void setCli_venda(ClienteModel cli_codigo) {
        this.cliente = cli_codigo;
    }
    
    public void setVenda_vendaPro(List<VendaProdutoModel> itens) {
        this.listItens = itens;
    }

    public void setVenda_vendaPagto(List<VendaPagtoModel> pagtos) {        
        this.listPagtos = pagtos;
    }
    
    public void setVda_data(LocalDate vda_data) {
        this.vda_data = vda_data;
    }

    public void setVda_valor(double vda_valor) {
        this.vda_valor = vda_valor;
    }

    public void setVda_desconto(double vda_desconto) {
        this.vda_desconto = vda_desconto;
    }

    public void setVda_total(double vda_total) {
        this.vda_total = vda_total;
    }

    public void setVda_obs(String vda_obs) {
        this.vda_obs = vda_obs;
    }

    

    
    
    
    
}
