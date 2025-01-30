package com.clinica.model.animais;

import com.clinica.model.Tutor;

public abstract class Animal implements ICaracteristicas {
    protected int id;
    protected String nome;
    protected int idade;
    protected boolean saudavel;
    protected boolean limpo;
    protected Tutor tutor;

    private static int contador = 0;

    public Animal(String nome, int idade) {
        this.id = contador++;
        this.nome = nome;
        this.idade = idade;
    }

    public Animal(String nome, int idade, Tutor tutor) {
        this.id = contador++;
        this.nome = nome;
        this.idade = idade;
        this.tutor = tutor;
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
}
