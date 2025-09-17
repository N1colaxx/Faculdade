package controller;

import dao.FormapagtoDao;
import java.sql.SQLException;
import java.util.ArrayList;

/** Controller de Forma de Pagamento. */
public class FormapagtoController {
    public ArrayList<String> listarNomesAtivos() throws SQLException {
        return new FormapagtoDao().listarNomesAtivos();
    }
    public int codigoPorNome(String nome) throws SQLException {
        return new FormapagtoDao().obterCodigoPorNome(nome);
    }
}
