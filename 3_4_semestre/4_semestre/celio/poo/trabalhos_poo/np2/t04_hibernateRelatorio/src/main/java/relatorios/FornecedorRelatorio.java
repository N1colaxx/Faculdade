package relatorios;


import model.FornecedorModel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.util.List;


public class FornecedorRelatorio {
   
    private String nomeArquivo = "relatorioFornecedor.pdf";
    private String titulo = "Relatório de Fornecedor";
    private PDDocument doc;
    private PDPage pagina;
    private PDPageContentStream cs;
    private int numeroPagina = 0;

    public void gerarRelatorio(List<FornecedorModel> dados) {
        try {
            doc = new PDDocument();
            gerarPagina();
            gerarTabela(dados);

            cs.close();
            doc.save(nomeArquivo);
            doc.close();

            boolean aberto = AbrePDF.abrirPDF(nomeArquivo);

            if (aberto) {
                System.out.println("✅ PDF gerado e aberto com sucesso!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void gerarPagina() throws Exception {   
        pagina = new PDPage(PDRectangle.A4);
        doc.addPage(pagina);
        cs = new PDPageContentStream(doc, pagina);
        
        numeroPagina++;

        // Calcula a largura do texto para centralizar
        float larguraTexto = PDType1Font.HELVETICA_BOLD.getStringWidth(titulo) / 1000 * 18;
        float larguraPagina = pagina.getMediaBox().getWidth();
        float posicaoX = (larguraPagina - larguraTexto) / 2;

        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA_BOLD, 12);
        cs.newLineAtOffset(500, 810); // Centralizado horizontalmente
        cs.showText("Página: " + numeroPagina);
        cs.endText();

        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA_BOLD, 18);
        cs.newLineAtOffset(posicaoX, 800); // Centralizado horizontalmente
        cs.showText(titulo);
        cs.endText();
    }

    private void gerarTabela(List<FornecedorModel> dados) throws Exception {
        int y = 750; // posição vertical inicial

        // Cabeçalho da tabela
        cs.beginText();
        cs.setFont(PDType1Font.COURIER_BOLD, 12);
        cs.newLineAtOffset(50, y);

        String cabecalho = String.format("%-10s %-30s %-40s",
                "Código",
                "Nome",
                "Ativo");
        cs.showText(cabecalho);
        cs.endText();

        cs.setFont(PDType1Font.COURIER, 12);
        for (FornecedorModel objModel : dados) {
            y -= 18; // espaço entre linhas

            // Se chegou no final da página, cria nova página
            if (y < 50) {
                cs.close();
                gerarPagina();
                cs.setFont(PDType1Font.COURIER, 12);
                y = 750;
            }
            cs.beginText();
            cs.newLineAtOffset(50, y);
            
            String nomePessoa = objModel.getPESSOA_FORNECEDOR().getPES_NOME();
            
            // Limitar o nome a 50 caracteres para não quebrar o layout
            if (nomePessoa.length() > 48) {
                nomePessoa = nomePessoa.substring(0, 48) + "...";
            }

            // String formatada com os dados para impressão
            String linha = String.format("%-10d %-50s %-10s",
                    objModel.getFOR_CODIGO(),
                    objModel.getPESSOA_FORNECEDOR().getPES_NOME(),
                    objModel.getPESSOA_FORNECEDOR().getPES_ATIVO() == 1 ? "Ativo" : "Inativo"
            );

            cs.showText(linha);
            cs.endText();
        }
    }

}


