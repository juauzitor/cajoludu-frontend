package br.cajoludu.model;

import java.util.ArrayList;

public class Book {
    private String titulo; // Title
    private String subtitulo; // Subtitle
    private ArrayList<String> autores; // Authors
    private String publicadora; // Publisher
    private String dataPublicacao; // Published Date
    private String sinopse; // Description
    private int contagemDePagina; //Page Count
    private String imagem; // Thumbnail
    private String previewLink; //PreviewLink
    private String infoLink; // Info Link
    private String buyLink; //  Buy link

    public Book(String titulo, String subtitulo, ArrayList<String> autores, String publicadora, String dataPublicacao, String sinopse, int contagemDePagina, String imagem, String previewLink, String infoLink, String buyLink) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.autores = autores;
        this.publicadora = publicadora;
        this.dataPublicacao = dataPublicacao;
        this.sinopse = sinopse;
        this.contagemDePagina = contagemDePagina;
        this.imagem = imagem;
        this.previewLink = previewLink;
        this.infoLink = infoLink;
        this.buyLink = buyLink;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public ArrayList<String> getAutores() {
        return autores;
    }

    public void setAutores(ArrayList<String> autores) {
        this.autores = autores;
    }

    public String getPublicadora() {
        return publicadora;
    }

    public void setPublicadora(String publicadora) {
        this.publicadora = publicadora;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public int getContagemDePagina() {
        return contagemDePagina;
    }

    public void setContagemDePagina(int contagemDePagina) {
        this.contagemDePagina = contagemDePagina;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public String getBuyLink() {
        return buyLink;
    }

    public void setBuyLink(String buyLink) {
        this.buyLink = buyLink;
    }
}
