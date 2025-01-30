package com.clinica.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.clinica.model.*;
import com.clinica.model.animais.*;
import com.clinica.model.colaboradores.*;


public class Menu {
    // ArrayList<Tutor> tutores;
    // ArrayList<Animal> animais;
    // ArrayList<Colaborador> colaboradores;
    Cadastro cadastro;
    Login login;
    Scanner leitura;

    public Menu(ArrayList<Tutor> tutores, ArrayList<Animal> animais, ArrayList<Colaborador> colaboradores){
        // this.tutores = tutores;
        // this.colaboradores = colaboradores;
        // this.animais = animais;
        this.cadastro = new Cadastro(tutores, colaboradores, animais);
        this.login = new Login(tutores, colaboradores);
        this.leitura = new Scanner(System.in);
    }

    private static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }

    public void menuInicial(ArrayList<Tutor> tutores, ArrayList<Colaborador> colaboradores, ArrayList<Animal> animais){
        int opcao;
        do{
            System.out.println("__________________________________");
            System.out.println("|                                 |");
            System.out.println("| Bem-vindo à clínica Pet Repete! |");
            System.out.println("|                                 |");
            System.out.println("| Como deseja acessar?            |");
            System.out.println("|                                 |");
            System.out.println("| 1. Sou tutor de pet             |");
            System.out.println("| 2. Sou colaborador              |");
            System.out.println("| 0. Sair                         |");
            System.out.println("|_________________________________|");
            System.out.print("\n> Escolha uma opção: ");

            opcao = leitura.nextInt();

            switch (opcao){
                case 1:
                    menuTutor(tutores, animais);
                    break;
                case 2:
                    menuColaborador(colaboradores);
                    break;
                case 1357:
                    menuAdmin(tutores, colaboradores, animais);
                    break;
                case 0:
                    System.out.println("O sistema será encerrado. Obrigado por utilizar!");;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }while (opcao!=0);
    }

    //TUTOR
    public void menuTutor(ArrayList<Tutor> tutores, ArrayList<Animal> animais){
        int opcao;
        do{
            System.out.println("________________________");
            System.out.println("|                       |");
            System.out.println("| > Tutor de Pet        |");
            System.out.println("|                       |");
            System.out.println("| 1. Realizar login     |");
            System.out.println("| 2. Realizar cadastro  |");
            System.out.println("| 0. Voltar             |");
            System.out.println("|_______________________|");
            System.out.print("\n> Escolha uma opção: ");

            opcao = leitura.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("\nLOGIN DE TUTOR");
                    clearBuffer(leitura);
                    System.out.print("Login (CPF ou E-mail): ");
                    String identificador = leitura.nextLine();
                    System.out.print("Senha: ");
                    String senha = leitura.nextLine();
                    boolean saida = login.autenticar(identificador, senha, "TUTOR");
                    if(saida){
                        System.out.println("\nLogin realizado com sucesso!");
                        for(Tutor tutor : tutores){
                            if(tutor.getCpf().equals(identificador) || tutor.getEmail().equalsIgnoreCase(identificador)){
                                menuTutorLogado(tutor);
                            }
                        }
                    }else{
                        System.out.println("Credenciais inválidas");
                    }
                    break;
                case 2:
                    Tutor novoTutor = lerTutor();
                    cadastro.cadastrarTutor(novoTutor);
                    System.out.println("Tutor cadastrado com sucesso!");
                    do{
                        System.out.print("Deseja cadastrar um pet agora? (1 - Sim | 0 - Não): ");
                        opcao = leitura.nextInt();
                        switch (opcao) {
                            case 1:
                                Animal novoAnimal = lerAnimalComTutor(novoTutor);
                                cadastro.cadastrarAnimal(novoAnimal);
                                System.out.println("Pet cadastrado com sucesso!");
                            case 0:
                                System.out.println("Cadastro finalizado! Faça o login no menu inicial");
                                break;
                            default:
                                System.out.println("Opção inválida");
                                break;
                        }
                    }while(opcao<0 || opcao>1);
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }while (opcao!=0);
    }

    private Tutor lerTutor(){
        System.out.println("\nCADASTRO DE TUTOR");
        clearBuffer(leitura);
        System.out.print("Nome: ");
        String nome = leitura.nextLine();
        System.out.print("Email: ");
        String email = leitura.nextLine();
        System.out.print("CPF: ");
        String cpf = leitura.nextLine();
        System.out.print("Endereço: ");
        String endereco = leitura.nextLine();
        System.out.print("Senha: ");
        String senha = leitura.nextLine();
        System.out.print("Confirme a senha: ");
        String confirmacao = leitura.nextLine();
        while(!senha.equals(confirmacao) || senha.length()<4){
            System.out.println("As senhas não são iguais ou tem menos de 4 caracteres!");
            System.out.print("Senha: ");
            senha = leitura.nextLine();
            System.out.print("Confirme a senha: ");
            confirmacao = leitura.nextLine();
        }
        Tutor t = new Tutor(nome, email, cpf, endereco, senha);
        return t;
    }

    private void listarTutores(ArrayList<Tutor> tutores, ArrayList<Animal> animais){
        System.out.print("\n>> TUTORES E SEUS ANIMAIS");
        for(Tutor t : tutores){
            System.out.println("\n"+t.getNome()+" - "+t.getEmail());
            exibirAnimaisDoTutor(t, animais);
        }
    }

    public void menuTutorLogado(Tutor tutorLogado){
        int opcao;
        do{
            System.out.println("\n> Tutor Logado - "+tutorLogado.getNome());
            System.out.println("_________________________________________");
            System.out.println("|                                        |");
            System.out.println("| 1. Exibir perfil                       |");
            System.out.println("| 2. Cadastrar novo pet                  |");
            System.out.println("| 3. Animais disponíveis para adoção     |");
            System.out.println("| 4. Ver seus pets                       |");
            System.out.println("| 5. Atualizar estado do pet             |");
            System.out.println("| 0. Sair                                |");
            System.out.println("|________________________________________|");
            System.out.print("\n> Escolha uma opção: ");

            opcao = leitura.nextInt();

            switch(opcao){
                case 1:
                    tutorLogado.exibirPerfil();
                    break;
                case 0:
                    System.out.println("Saindo do perfil...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }

        }while(opcao!=0);
    }
    
    //COLABORADOR
    public void menuColaborador(ArrayList<Colaborador> colaboradores){
        System.out.println("\nLOGIN DE COLABORADOR");
        clearBuffer(leitura);
        System.out.print("CPF: ");
        String cpf = leitura.nextLine();
        System.out.print("Senha: ");
        String senha = leitura.nextLine();
        boolean saida = login.autenticar(cpf, senha, "COLABORADOR");
        
        if(saida){
            System.out.println("\nLogin realizado com sucesso!");
            for(Colaborador colaborador : colaboradores){
                if(colaborador.getCpf().equals(cpf)){
                    if (colaborador instanceof Groomer) {
                        menuGroomer((Groomer) colaborador);
                    } else if (colaborador instanceof Veterinario) {
                        menuVeterinario((Veterinario) colaborador);
                    }
                }
            }
        }else{
            System.out.println("Credenciais inválidas");
        }
    }

    public void menuGroomer(Groomer groomer){
        System.out.println("\n> Groomer Logado - "+groomer.getNome());
        System.out.println("__________________________________");
        System.out.println("|                                 |");
        System.out.println("| 1. Ver pets para banho ou tosa  |");
        System.out.println("| 2. Atender próximo pet          |");
        System.out.println("| 3. Ver meu perfil               |");
        System.out.println("| 0. Sair                         |");
        System.out.println("|_________________________________|");
    }

    public void menuVeterinario(Veterinario veterinario){
        System.out.println("\n> Veterinário Logado - "+veterinario.getNome());
        System.out.println("__________________________________");
        System.out.println("|                                 |");
        System.out.println("| 1. Ver lista de espera          |");
        System.out.println("| 2. Tratar próximo pet           |");
        System.out.println("| 3. Ver meu perfil               |");
        System.out.println("| 0. Sair                         |");
        System.out.println("|_________________________________|");
    }

    private void listarColaboradores(ArrayList<Colaborador> colaboradores){
        System.out.println("\n>> GROOMERS");
        for(Colaborador c : colaboradores){
            if(c instanceof Groomer){
                System.out.println("\n"+c.getNome());
            }
        }
        System.out.println("\n>> VETERINÁRIOS");
        for(Colaborador c : colaboradores){
            if(c instanceof Veterinario){
                System.out.println("\n"+c.getNome());
            }
        }
    }

    //ANIMAL
    private Animal lerAnimalComTutor(Tutor tutor){
        clearBuffer(leitura);
        System.out.println("\nCADASTRO DE PET");
        System.out.print("Nome: ");
        String nome = leitura.nextLine();
        System.out.print("Idade: ");
        int idade = leitura.nextInt();

        int especie;
        Animal a = null;
        do{
            System.out.println("\nESPÉCIES ATENDIDAS");
            System.out.println("1. Cachorro");
            System.out.println("2. Coelho");
            System.out.println("3. Gato");
            System.out.println("4. Jabuti");
            System.out.println("5. Pássaro");
            System.out.println("5. Pássaro");
            System.out.print("Indique a espécie (1-5): ");
            especie = leitura.nextInt();

            switch (especie) {
                case 1:
                    a = new Cachorro(nome, idade, tutor);
                    break;
                case 2:
                    a = new Coelho(nome, idade, tutor);
                    break;
                case 3:
                    a = new Gato(nome, idade, tutor);
                    break;
                case 4:
                    a = new Jabuti(nome, idade, tutor);
                    break;
                case 5:                    
                    a = new Passaro(nome, idade, tutor);
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }while(especie>5 || especie<1);
        return a;
    }

    private void exibirAnimaisDoTutor(Tutor tutor, ArrayList<Animal> animais){
        if(animais.isEmpty()){
            System.out.println("Não há animais cadastrados");
        }else{
            for(Animal pet : animais){
                if(pet.getTutor().equals(tutor)){
                    System.out.println(pet.getNome());
                }
            }
        }
    }

    private void exibirAnimaisAdocao(ArrayList<Animal> animais){
        if(animais.isEmpty()){
            System.out.println("Não há animais cadatsrados");
        }else{
            for(Animal pet : animais){
                if(pet.getTutor().equals(null)){
                    System.out.println(pet.getNome());
                }
            }
        }
    }

    private Animal lerAnimalAdocao(){
        clearBuffer(leitura);
        System.out.println("\nCADASTRO DE PET");
        System.out.print("Nome: ");
        String nome = leitura.nextLine();
        System.out.print("Idade: ");
        int idade = leitura.nextInt();

        int especie;
        Animal a = null;
        do{
            System.out.println("\nESPÉCIES ATENDIDAS");
            System.out.println("1. Cachorro");
            System.out.println("2. Coelho");
            System.out.println("3. Gato");
            System.out.println("4. Jabuti");
            System.out.println("5. Pássaro");
            System.out.println("5. Pássaro");
            System.out.print("Indique a espécie (1-5): ");
            especie = leitura.nextInt();

            switch (especie) {
                case 1:
                    a = new Cachorro(nome, idade);
                    break;
                case 2:
                    a = new Coelho(nome, idade);
                    break;
                case 3:
                    a = new Gato(nome, idade);
                    break;
                case 4:
                    a = new Jabuti(nome, idade);
                    break;
                case 5:                    
                    a = new Passaro(nome, idade);
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }while(especie>5 || especie<1);
        return a;
    }

    //ADMINISTRADOR
    public void menuAdmin(ArrayList<Tutor> tutores, ArrayList<Colaborador> colaboradores, ArrayList<Animal> animais){
        int opcao;
        do{
            System.out.println("__________________________________");
            System.out.println("|                                 |");
            System.out.println("| Bem-vindo, Administrador!       |");
            System.out.println("|                                 |");
            System.out.println("| O que deseja fazer?             |");
            System.out.println("|                                 |");
            System.out.println("| 1. Cadastrar novo colaborador   |");
            System.out.println("| 2. Cadastrar animal para adoção |");
            System.out.println("| 3. Listar todos os cadastros    |");
            System.out.println("| 0. Sair                         |");
            System.out.println("|_________________________________|");
            System.out.print("\n> Escolha uma opção: ");

            opcao = leitura.nextInt();

            switch (opcao){
                case 1:
                    Colaborador novColaborador = lerColaborador();
                    cadastro.cadastrarColaborador(novColaborador);
                    System.out.println("Colaborador cadastrado com sucesso!");
                    break;
                case 2:
                    Animal novoAnimal = lerAnimalAdocao();
                    cadastro.cadastrarAnimal(novoAnimal);
                    System.out.println("Pet para adoção cadastrado com sucesso!");
                    break;
                case 3:
                    System.out.println("\n----- TODOS OS CADASTROS DO SISTEMA -----");
                    listarTutores(tutores, animais);
                    System.out.println("\n>> ANIMAIS PARA ADOÇÃO");
                    exibirAnimaisAdocao(animais);
                    listarColaboradores(colaboradores);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }while (opcao!=0);
    }
    
    private Colaborador lerColaborador(){
        clearBuffer(leitura);
        System.out.println("\nCADASTRO DE COLABORADOR");
        System.out.print("Nome: ");
        String nome = leitura.nextLine();
        System.out.print("CPF: ");
        String cpf = leitura.nextLine();
        System.out.print("Senha: ");
        String senha = leitura.nextLine();
        System.out.print("Confirme a senha: ");
        String confirmacao = leitura.nextLine();
        while(!senha.equals(confirmacao) || senha.length()<4){
            System.out.println("As senhas não são iguais ou tem menos de 4 caracteres!");
            System.out.print("Senha: ");
            senha = leitura.nextLine();
            System.out.print("Confirme a senha: ");
            confirmacao = leitura.nextLine();
        }

        int cargo;
        Colaborador c = null;
        do{
            System.out.println("\nESCOLHA UM CARGO");
            System.out.println("1. Veterinário");
            System.out.println("2. Groomer");
            System.out.print("Indique o cargo (1 ou 2): ");
            cargo = leitura.nextInt();

            switch (cargo) {
                case 1:
                    c = new Veterinario(nome, cpf, senha);
                    break;
                case 2:
                    c = new Groomer(nome, cpf, senha);
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }while(cargo>2 || cargo<1);
        return c;
    }
}