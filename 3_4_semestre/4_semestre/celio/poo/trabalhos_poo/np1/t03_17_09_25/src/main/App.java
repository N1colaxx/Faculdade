package main;

import view.AppView;
import conexao.Conexao;

import javax.swing.*;

public class App {
    
    private Conexao conexao;
    private AppView appView;
    
    public App() {
        conexao = new Conexao();
        appView = new AppView();
    }
    
    public static void main(String[] args) {
        App obj_app = new App();
    }

}                                                                                       