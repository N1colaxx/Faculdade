# Tela de cadastro de funcionario

## 1 pg, Dados pessoais
-   nome completo
-   Data de nascimento
-   Sexo ⬇️
-   Estado civil ⬇️
-   Nacionalidade ⬇️
-   Etinia
-   Tipo sanguinio Fator RH ⬇️
-   Deficiencia (se houver)

## 2 pg, Informações de Contato
-   Endereço completo
-   Telefone (fixo e/ou Celular)
-   E-mail

## 3pg, Dados Contratuais
-   Matricula (RA)
-   Empresae e local de trabalho
-   Departamento ⬇️
-   Cargo/função ⬇️
-   Data de admissão
-   Tipo de contrato (determinado/indeterminado/apremdizagem, etc) ⬇️
-   Regime de trabalho (integral/parcial) ⬇️
-   Jornada de trabalho 
-   Salario 
-   Informações sobre ferias
-   Informações sobre afastamento
-   Informações sobre recisão 

<br><br><br>


# OBRIGATORIO

## Menu
1. Utilize abas (JTabbePane)
2. Utilize JComboBox com as respectivas opções nos campos:
-   Sexo, Estado Civil, Nacionalidade, Tipo Sanguinio, Departamento, Cargo/Função, Tipo de contrato, Regime de trabalho

---
<br><br>

## Regras de Negocio

1. Criar metodo `validar()` e adicione no evento do BTN Gravar. Esse metodo ira validar todas as regras abaixo.

2. Nos campos:  nascimento e data adimissão 
-   Usar componentes de data ou calendario 
-   Não aceitar datas inferior ao dia atual.
-   Valide data de admissão maior que data de nascimento 

3. No campo e-mail, verificar se existe o carecter `"@"`, caso não tenha, de a msg:
-    "E-mail precisa conter '@' "

4. Insira a formatação para o telefone. Ex: (XX) XXXXX-XXXX

5. Valide o salário para:
-   Aceitar valores acima de R$100,00
-   Somente números. Caso tenha outros caracteres, de a msg: "Campo salário precisa conter apenas números"


