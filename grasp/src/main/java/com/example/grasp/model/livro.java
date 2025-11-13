package com.example.grasp.model;

/**
 * Modelo simples de livro.
 * Alto nível de coesao: essa classe tem responsabilidade única (dados de um livro).
 * Baixo acoplamento: nao depende de outras classes do domínio.
 */
public class livro {
    private final String isbn;
    private final String titulo;
    private boolean disponivel = true;

    public livro(String isbn, String titulo) {
        this.isbn = isbn;
        this.titulo = titulo;
    }

    public String getIsbn() { return isbn; }
    public String gettitulo() { return titulo; }

    public boolean estadisponivel() { return disponivel; }
    public void setdisponivel(boolean disponivel) { this.disponivel = disponivel; }

    @Override
    public String toString() {
        return String.format("livro[isbn=%s, titulo=%s, disponivel=%s]", isbn, titulo, disponivel);
    }
}