package br.cajoludu.model;

public class Book {
    private String nome;
    private String imagem;
    private int curtidas;
    private String sinopse;
    private String url;

    public Book(String nome, int curtidas, String sinopse, String url) {
        this.nome = nome;
        this.curtidas = curtidas;
        this.sinopse = sinopse;
        this.url = url;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImage() {
        return imagem;
    }

    public void setImage(String image) {
        this.imagem = image;
    }

    public int getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(int curtidas) {
        this.curtidas = curtidas;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getUrl() {return url;}

    public void setUrl(String url) {this.url = url;}
}
