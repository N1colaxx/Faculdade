
package POJO;


public class Livro {
    private String titulo;
    private String autor;
    private int anoPublicado;

    public Livro(){
    }

    public Livro(String titulo, String autor, int anoPublicado){
       this.titulo = titulo;
       this.autor = autor;
       this.anoPublicado = anoPublicado;
    }
    
   
//   Getters
    public String getTitulo (){
        return titulo;
    }

    public String getAutor(){
        return autor;
    }

    public int getAnoPublicado(){
        return anoPublicado;
    }

 //   Setters
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public void setAutor(String autor){
        this.autor = autor;
    }

    public void setAnoPublicado(int anoPublicado){
        this.anoPublicado = anoPublicado;
    }
   
   
    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", anoPublicado=" + anoPublicado +
                '}';
    }
}
