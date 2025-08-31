package t01_02_09_25;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidadorDados {
    
    public String validarTodos(ComponentesValidacao componentes) {
        StringBuilder erros = new StringBuilder();
        
        // Validar Nome
        if (componentes.getTxtNome().getText().trim().isEmpty()) {
            erros.append("• Nome é obrigatório.\n");
        }
        
        // Validar CPF
        String cpf = componentes.getTxtCPF().getText().replaceAll("[^0-9]", "");
        if (cpf.length() != 11) {
            erros.append("• CPF deve ter 11 dígitos.\n");
        }
        
        // Validar Email
        if (componentes.getTxtEmail() != null) {
            String email = componentes.getTxtEmail().getText().trim();
            if (!validarEmail(email)) {
                erros.append("• Email inválido.\n");
            }
        }
        
        // Validar Data de Nascimento
        if (componentes.getTxtDataNasc() != null) {
            String erro = validarDataNascimento(componentes.getTxtDataNasc().getText());
            if (!erro.isEmpty()) {
                erros.append(erro);
            }
        }
        
        // Validar Salário
        if (componentes.getTxtSalario() != null) {
            String erro = validarSalario(componentes.getTxtSalario().getText());
            if (!erro.isEmpty()) {
                erros.append(erro);
            }
        }
        
        return erros.toString();
    }
    
    private boolean validarEmail(String email) {
        if (email.isEmpty() || !email.contains("@") || 
            email.startsWith("@") || email.endsWith("@")) {
            return false;
        }
        return true;
    }
    
    private String validarDataNascimento(String dataString) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        try {
            LocalDate data = LocalDate.parse(dataString, f);
            if (data.isAfter(LocalDate.now())) {
                return "• Data de nascimento no futuro.\n";
            }
        } catch (DateTimeParseException ex) {
            return "• Data de nascimento inválida (dd/MM/aaaa).\n";
        }
        return "";
    }
    
    private String validarSalario(String salarioString) {
        String s = salarioString.trim().replace(",", ".");
        try {
            BigDecimal val = new BigDecimal(s);
            if (val.signum() <= 0) {
                return "• Valor Salário deve ser > 0.\n";
            }
        } catch (Exception ex) {
            return "• Valor Salário inválido.\n";
        }
        return "";
    }
}