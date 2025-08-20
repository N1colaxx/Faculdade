# Verificação dentro de um `JTextField`

### `KeyListener` → mais simples, mas ignora colar texto.

```java
JTextField campo = new JTextField(10);

campo.addKeyListener(new KeyAdapter() {
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c)) { // só permite números
            e.consume(); // ignora a tecla
        }
    }
});
```
<br>

---
<br>



### `DocumentListener` → captura qualquer alteração.

```java
campo.getDocument().addDocumentListener(new DocumentListener() {
    @Override
    public void insertUpdate(DocumentEvent e) {
        validar();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        validar();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        validar();
    }

    private void validar() {
        String texto = campo.getText();
        System.out.println("Novo valor: " + texto);
    }
});

```
<br>

---
<br>



### `InputVerifier` → bom para validação ao sair do campo.

```java
campo.setInputVerifier(new InputVerifier() {
    @Override
    public boolean verify(JComponent input) {
        String texto = ((JTextField) input).getText();
        try {
            Integer.parseInt(texto); // verifica se é número
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Digite apenas números!");
            return false; // impede sair do campo
        }
    }
});

```
<br>

---
<br>



### `DocumentFilter` → mais poderoso, controla a entrada antes de aparecer.

```java
import javax.swing.text.*;

PlainDocument doc = (PlainDocument) campo.getDocument();
doc.setDocumentFilter(new DocumentFilter() {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        if (string.matches("\\d+")) { // só números
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        if (text.matches("\\d+")) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
});

```
<br>

---
<br>



### `ActionListener` → valida quando ENTER é pressionado.

```java
campo.addActionListener(e -> {
    String texto = campo.getText();
    System.out.println("Digitou: " + texto);
});

```

