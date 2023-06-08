package br.cajoludu.model;

public class Book {
    private String nome;
    private String image;
    private int curtidas;
    private String sinopse;

    public Book(String nome, int curtidas, String sinopse) {
        this.nome = nome;
        this.curtidas = curtidas;
        this.sinopse = sinopse;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}
