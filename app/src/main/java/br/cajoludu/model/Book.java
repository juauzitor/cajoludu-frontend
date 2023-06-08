package br.cajoludu.model;

public class Book {
    private String nome;
    private String image;
    private int curtidas;

    public Book(String nome, int curtidas) {
        this.nome = nome;
        this.curtidas = curtidas;
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
}
