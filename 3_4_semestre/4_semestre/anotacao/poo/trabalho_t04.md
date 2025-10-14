# T04

## Void da view Cliente

1. instanciar
    -   Add valor a: lbl, edt, btn, chk (CheckBox), cb (JComoBox), panel
    -    Cria as tabelas

2. adicionar
    -   add as coisas ao seu Jpanel

3. posicionar
    -   posiciona no Jpanel

4. configuracoes
    -   ActionListener dos btn

5. limparCampos
    -   Limpa os edt

6. mostrarRegistro
    -   Pega o registro selecionado na Tab e chama o void MOSTRAR, passando esse registro como paramentro
7. mostrar
    -   Recebe um registro como parametro e passa esses valores para: lbl, edt, ...

8. montarClienteDosCampos
    -   Para criar um cliente novo 

9. filtroConsulta
    - Filtros para a consulta no DB

10. consultar
    -   Consulta o DB

11. get/set
    


## Void da view Venda

**Geral**
1. instanciar
2. adicionar
3. posicionar
4. configurações
    -   add ação ao BTN
    -   add ação ao edt de ProdCod, edtQtde
    -   add ação de seleção as tabelas (transformar em 1 void e passar para as outra)
5. recomputarTotais
    - REVER por completo, pois passa valores em lugares incorretos 

**Itnes**

Fazer:
    -   add -> lblDescontoProduto, edtDescontoProduto

1. preencherProduto
2. recalSubtotal
    - È o total da vendaPRoduto, referente a quele produto
3. adicionarItem / removerItem
    -   Na tabela VendaProdutoTableModel
5. limparCamposProduto

**Pagamentos**
1. carregarFromasPagamento
2. adicionarPagamento
3. atualizarPagamentoSelecionado
4. removerPagamentoSelecionado

**Consulta**
1. filtroConsultaVendaProduto
2. consultarVendaProduto
3. limparTabelaConsulta
4. selecionarLinhaConsulta
    -   caso clique em 1 item ele carrega os dados dentro do EDT 

**Cabeçalho / Gravação**

Contem a ação de alguns BTN


**Seleção e editores**

Metoros para Preenche os campos dos EDT




