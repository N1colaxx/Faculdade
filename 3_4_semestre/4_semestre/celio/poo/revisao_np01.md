# Criar um FRAME

1. IMPORTS
    -   java.awt.*;
    -   javax.swing.*;

2. CLASS EXTENDS JFrame
    -   public class teste extends JFrame {}

3. CFG do Frame
    -   setTitle("");
    -   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    -   setLayout(null);
    -   setSize(1500, 850);
    -   setLocationRelatioveTo(true);
    -   setVisible(true);




<br><br>

# Criar JComboBox

1. Declarar e definir o tipo 
    -   JcomboBox<String> cbxUF;
    -   JcomboBox<int> cbxNumero;


2. Criar uma lisata par ale.
    -   String[] listaCbxUF = {"SP", "RJ", "MG"};
    -   int[] lsitaNumero = { 1, 2, 3};

3. Instaciar ele com a sua lista
    -   cbxUf = new Jcombobox<>(listaCbxUF);
    -   cbxNumero = new Jcombobox<>(lsitaNumero);





<br><br>

# JDBC

### Dicas para o DAO

1. DEFAULT no INSERT: 
    -   VALUES (?, DEFAULT, ?) ou omita a coluna para usar o default.

2. Nunca use coluna = ? dentro da lista de colunas do INSERT no Postgres.

`Resumão:`

-   INSERT → VALUES(...)

-   UPDATE/WHERE → coluna = ?





<br><br><br>

### Conneton 

> Cria a conexao com o banco de dados 

```java
Connetion conexao = DriveMeneger.getConnetion(BANCO,USUARIO,SENHA); 
```

    
### Statement - PreparedStatement

1. **Statement** 
    -   Executa SQL simples
    -   `OBS: risco de receber SQL injecion` 

    ```java
        // Cuidado: concatenando string direto pode causar SQL Injection!
        String sql = "INSERT INTO cliente (nome, idade, sexo) " +
                    "VALUES ('" + nome + "', " + idade + ", '" + sexo + "')";

        Statement stm = conexao.createStatement();
        stm.execute(sql);
        stm.close();
    ```

2. **PreparedStatiment** 
    -   Executa SQL simples, POREM mais seguro pois é usado com parametros de consulta

    ```java
        String sql = "INSERT INTO cliente (nome, idade, sexo) VALUES (?, ?, ?)";
        
        PreparedStatement ps = conexao.prepareStatement(sql);

        ps.setString(1, nome);
        ps.setInt(2, idade);
        ps.setString(3, sexo);

        ps.execute();
        ps.close();
    ```

3. **ResultSet**
    -   usado em um select, percorre lia por linha

    ``` java 
        ResultSet rs 
    ```
    



<br><br><br>

# MVC – Model, View, Controller

### Model 
> contém os dados e regras de negócio (ex: ClienteModel com atributos e getters/setters).

### View 
> interface gráfica (Swing: ClienteView, JFrame, JPanel).

### Controller 
> coordena a comunicação entre Model e View (chama DAO para salvar no banco e atualiza a View).

## Objetivo `separar responsabilidades, deixar o código organizado.`




<br><br><br>

# DAO – Data Access Object

1. Classe responsável apenas por acessar o banco.

2. Evita misturar SQL dentro da View ou Controller.

Exemplo:

```java 
    public class ClienteDAO {
    private Connection conn;

    public ClienteDAO(Connection conn){
        this.conn = conn;
    }

    public void inserir(ClienteModel c) throws SQLException {
        String sql = "INSERT INTO cliente (nome, idade) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, c.getNome());
        ps.setInt(2, c.getIdade());
        ps.executeUpdate();
    }

    public List<ClienteModel> listar() throws SQLException {
        List<ClienteModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            ClienteModel c = new ClienteModel();
            c.setNome(rs.getString("nome"));
            c.setIdade(rs.getInt("idade"));
            lista.add(c);
        }
        return lista;
    }
}
```

# 📌 Resumo rápido pra lembrar na hora da prova:

1. Swing/Frame/setBounds → montar interface.

2. JDBC → Connection (conexão), Statement/PreparedStatement (executar SQL), ResultSet (ler resultados).

3. MVC → separar lógica (Model), tela (View) e controle (Controller).

4. DAO → classe especializada em salvar, consultar e excluir dados no banco.