package com.clinica.model.animais;

import com.clinica.model.Tutor;

public abstract class Animal implements ICaracteristicas {
    protected int id;
    protected String nome;
    protected int idade;
    protected boolean limpo;
    protected boolean saudavel;
    protected Tutor tutor;

    private static int contador = 0;

    public Animal(String nome, int idade) {
        this.id = contador++;
        this.nome = nome;
        this.idade = idade;
        this.limpo = true;
        this.saudavel = true;
    }

    public Animal(String nome, int idade, Tutor tutor) {
        this.id = contador++;
        this.nome = nome;
        this.idade = idade;
        this.tutor = tutor;
        this.limpo = true;
        this.saudavel = true;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public boolean isLimpo() {
        return limpo;
    }

    public boolean isSaudavel() {
        return saudavel;
    }

    public void setLimpo(boolean limpo) {
        this.limpo = limpo;
    }

    public void setSaudavel(boolean saudavel) {
        this.saudavel = saudavel;
    }
}
