package testes;

import javax.swing.*;
import java.awt.*;

public class AbaDocumentacao {
    
    public JPanel criarAba() {
        JPanel aba = new JPanel(new GridLayout(1, 2, 8, 8));
        aba.setBorder(BorderFactory.createEmptyBorder(8, 10, 10, 10));
        
        JPanel esq = new JPanel(new GridLayout(4, 1, 8, 8));
        esq.add(criarGrupoRG());
        esq.add(criarGrupoInfoMilitar());
        esq.add(criarGrupoCNH());
        esq.add(criarGrupoConselho());
        
        JPanel dir = new JPanel(new GridLayout(5, 1, 8, 8));
        dir.add(criarGrupoCTPS());
        dir.add(criarGrupoCPF());
        dir.add(criarGrupoPIS());
        dir.add(criarGrupoTitulo());
        dir.add(criarGrupoRIC());
        
        aba.add(esq);
        aba.add(dir);
        return aba;
    }
    
    private JPanel criarGrupoRG() {
        JPanel g = UIHelper.criarBloco("RG", 2);
        g.add(UIHelper.criarLinha("Número", new JTextField(12)));
        
        JPanel l = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 4));
        l.add(new JLabel("Orgão Expedidor"));
        l.add(new JComboBox<>(new String[]{"SSP","DGPC","DETRAN","IFP","OAB"}));
        l.add(new JLabel("UF"));
        l.add(UIHelper.criarComboUF());
        l.add(new JLabel("Data Expedição"));
        l.add(UIHelper.criarCampoMascara("##/##/####"));
        g.add(l);
        
        return g;
    }
    
    private JPanel criarGrupoCTPS() {
        JPanel g = UIHelper.criarBloco("CTPS", 2);
        g.add(UIHelper.criarLinha("Número", new JTextField(12)));
        
        JPanel l = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 4));
        l.add(new JLabel("Série"));
        l.add(new JTextField(8));
        l.add(new JLabel("Orgão"));
        l.add(new JTextField(8));
        l.add(new JLabel("UF"));
        l.add(UIHelper.criarComboUF());
        g.add(l);
        
        return g;
    }
    
    private JPanel criarGrupoInfoMilitar() {
        JPanel g = UIHelper.criarBloco("Informação Militar", 2);
        g.add(UIHelper.criarLinha("Situação", 
            new JComboBox<>(new String[]{"Dispensado","Reservista","Isento"})));
        
        JPanel l = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 4));
        l.add(new JLabel("Número"));
        l.add(new JTextField(12));
        l.add(new JLabel("Categoria"));
        l.add(new JTextField(10));
        l.add(new JLabel("Data Baixa"));
        l.add(UIHelper.criarCampoMascara("##/##/####"));
        g.add(l);
        
        return g;
    }
    
    private JPanel criarGrupoCPF() {
        JPanel g = UIHelper.criarBloco("CPF", 1);
        g.add(UIHelper.criarLinha("Número", UIHelper.criarCampoMascara("###.###.###-##")));
        return g;
    }
    
    private JPanel criarGrupoPIS() {
        JPanel g = UIHelper.criarBloco("PIS", 1);
        JPanel l = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 4));
        l.add(new JLabel("Número"));
        l.add(UIHelper.criarCampoMascara("###.#####.##-#"));
        l.add(new JLabel("Data Cadastro"));
        l.add(UIHelper.criarCampoMascara("##/##/####"));
        g.add(l);
        return g;
    }
    
    private JPanel criarGrupoCNH() {
        JPanel g = UIHelper.criarBloco("CNH", 2);
        g.add(UIHelper.criarLinhaDupla("Número", new JTextField(16), 
            "Categoria", new JComboBox<>(new String[]{"A","B","C","D","E","AB"})));
        g.add(UIHelper.criarLinhaDupla("Data Cadastro", UIHelper.criarCampoMascara("##/##/####"), 
            "Data Vencimento", UIHelper.criarCampoMascara("##/##/####")));
        return g;
    }
    
    private JPanel criarGrupoTitulo() {
        JPanel g = UIHelper.criarBloco("Título de Eleitor", 2);
        g.add(UIHelper.criarLinha("Número", new JTextField(16)));
        g.add(UIHelper.criarLinhaDupla("Zona", new JTextField(6), "Seção", new JTextField(6)));
        return g;
    }
    
    private JPanel criarGrupoConselho() {
        JPanel g = UIHelper.criarBloco("Conselho Regional", 1);
        JPanel l1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 4));
        l1.add(new JLabel("Número"));
        l1.add(new JTextField(12));
        l1.add(new JLabel("Sigla"));
        l1.add(new JTextField(6));
        l1.add(new JLabel("Reg. Região"));
        l1.add(new JTextField(10));
        g.add(l1);
        return g;
    }
    
    private JPanel criarGrupoRIC() {
        JPanel g = UIHelper.criarBloco("RIC", 2);
        g.add(UIHelper.criarLinha("Número", new JTextField(16)));
        JPanel l = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 4));
        l.add(new JLabel("Orgão Expedidor"));
        l.add(new JTextField(14));
        l.add(new JLabel("Data Expedição"));
        l.add(UIHelper.criarCampoMascara("##/##/####"));
        g.add(l);
        return g;
    }
}