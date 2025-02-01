package com.clinica.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.clinica.model.*;
import com.clinica.model.animais.*;
import com.clinica.model.colaboradores.*;


public class Menu {
    Cadastro cadastro;
    Login login;
    Scanner leitura;

    public Menu(ArrayList<Tutor> tutores, ArrayList<Animal> animais, ArrayList<Colaborador> colaboradores){
        this.cadastro = new Cadastro(tutores, colaboradores, animais);
        this.login = new Login(tutores, colaboradores);
        this.leitura = new Scanner(System.in);
    }

    private static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }

    public void menuInicial(ArrayList<Tutor> tutores, ArrayList<Colaborador> colaboradores, ArrayList<Animal> animais, ArrayList<Animal> animaisSujos, ArrayList<Animal> animaisDoentes){
        int opcao;
        do {
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
                    menuTutor(tutores, animais, animaisSujos, animaisDoentes);
                    break;
                case 2:
                    menuColaborador(colaboradores, animaisSujos, animaisDoentes);
                    break;
                case 1357:
                    menuAdmin(tutores, colaboradores, animais);
                    break;
                case 0:
                    System.out.println("\nO sistema será encerrado. Obrigado por utilizar!");;
                    break;
                default:
                    System.out.println("\nOpção inválida. Tente novamente.");
                    break;
            }
        } while (opcao!=0);
    }

    //TUTOR
    public void menuTutor(ArrayList<Tutor> tutores, ArrayList<Animal> animais, ArrayList<Animal> animaisSujos, ArrayList<Animal> animaisDoentes){
        int opcao;
        do {
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
                                menuTutorLogado(tutor, animais, animaisSujos, animaisDoentes);
                            }
                        }
                    }else{
                        System.out.println("\nCredenciais inválidas");
                    }
                    break;
                case 2:
                    Tutor novoTutor = lerTutor(tutores);
                    cadastro.cadastrarTutor(novoTutor);
                    System.out.println("\nTutor cadastrado com sucesso!");
                    do{
                        System.out.println("________________________________________");
                        System.out.println("|                                      |");
                        System.out.println("| > Deseja cadastrar um animal agora?  |");
                        System.out.println("|                                      |");
                        System.out.println("| 1. Sim                               |");
                        System.out.println("| 0. Não                               |");
                        System.out.println("|______________________________________|");
                        System.out.print("\n> Escolha uma opção: ");
                        opcao = leitura.nextInt();
                        clearBuffer(leitura);
                        switch (opcao) {
                            case 1:
                                Animal novoAnimal = lerAnimalComTutor(novoTutor);
                                cadastro.cadastrarAnimal(novoAnimal);
                                System.out.println("\nPet cadastrado com sucesso!");
                            case 0:
                                System.out.println("\nCadastro finalizado! Faça o login no menu inicial.");
                                break;
                            default:
                                System.out.println("\nOpção inválida");
                                break;
                        }
                    }while(opcao<0 || opcao>1);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpção inválida");
                    break;
            }
        } while (opcao!=0);
    }

    private Tutor lerTutor(ArrayList<Tutor> tutores){
        System.out.println("\nCADASTRO DE TUTOR");
        clearBuffer(leitura);
        System.out.print("Nome: ");
        String nome = leitura.nextLine();

        String email;
        boolean emailExiste;
        do {
            emailExiste = false;
            System.out.print("Email: ");
            email = leitura.nextLine();

            for (Tutor t : tutores) {
                if (t.getEmail().equalsIgnoreCase(email)) {
                    emailExiste = true;
                    System.out.println("Este e-mail já está cadastrado! Tente outro ou faça login.");
                    break;
                }
            }
        } while (emailExiste);

        String cpf;
        boolean cpfExiste;
        do {
            cpfExiste = false;
            System.out.print("CPF: ");
            cpf = leitura.nextLine();

            for (Tutor t : tutores) {
                if (t.getCpf().equals(cpf)) {
                    cpfExiste = true;
                    System.out.println("Este CPF já está cadastrado! Tente outro ou faça login.");
                    break;
                }
            }
        } while (cpfExiste);

        System.out.print("Endereço: ");
        String endereco = leitura.nextLine();
        String senha;
        String confirmacao = "";
        
        do {
            System.out.print("Digite a senha (mínimo 4 caracteres): ");
            senha = leitura.nextLine();
            
            if (senha.length() < 4) {
                System.out.println("\nA senha deve ter pelo menos 4 caracteres.");
                continue;
            }
        
            System.out.print("Confirme a senha: ");
            confirmacao = leitura.nextLine();
        
            if (!senha.equals(confirmacao)) {
                System.out.println("\nAs senhas devem ser iguais!");
            }
        } while (senha.length() < 4 || !senha.equals(confirmacao));

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

    public void menuTutorLogado(Tutor tutorLogado, ArrayList<Animal> animais, ArrayList<Animal> animaisSujos, ArrayList<Animal> animaisDoentes){
        int opcao;
        
        do {
            System.out.println("\n> Tutor Logado - "+tutorLogado.getNome());
            System.out.println("_________________________________________");
            System.out.println("|                                        |");
            System.out.println("| 1. Exibir perfil                       |");
            System.out.println("| 2. Cadastrar novo pet                  |");
            System.out.println("| 3. Animais disponíveis para adoção     |");
            if(tutorTemAnimal(tutorLogado, animais)){
                System.out.println("| 4. Ver seus pets                       |");
                System.out.println("| 5. Atualizar estado do pet             |");
            }
            System.out.println("| 0. Sair                                |");
            System.out.println("|________________________________________|");
            System.out.print("\n> Escolha uma opção: ");

            opcao = leitura.nextInt();
            clearBuffer(leitura);

            switch(opcao){
                case 1:
                    tutorLogado.exibirPerfil();
                    System.out.println("\nPressione a tecla ENTER para retornar ao menu anterior...");
                    leitura.nextLine();
                    break;
                case 2:
                    Animal novoAnimal = lerAnimalComTutor(tutorLogado);
                    cadastro.cadastrarAnimal(novoAnimal);
                    System.out.println("\nPet cadastrado com sucesso!");
                    System.out.println("\nPressione a tecla ENTER para retornar ao menu anterior...");
                    leitura.nextLine();
                    break;
                case 3:
                    int opcaoAdocao;
                    System.out.println("\nANIMAIS DISPONÍVEIS PARA ADOÇÃO");
                    exibirAnimaisAdocao(animais);
                    
                    if (temAnimaisDisponiveis(animais)) {
                        System.out.println("________________________________________");
                        System.out.println("|                                      |");
                        System.out.println("| > Deseja adotar um animal agora?     |");
                        System.out.println("|                                      |");
                        System.out.println("| 1. Sim                               |");
                        System.out.println("| 0. Não                               |");
                        System.out.println("|______________________________________|");
                        System.out.print("\n> Escolha uma opção: ");
                        opcaoAdocao = leitura.nextInt();
                    
                        while (opcaoAdocao == 1) {
                            int id;
                            boolean disponivel = false;
                            boolean existe = false;
                            do {
                                System.out.print("\nDigite o ID do pet que deseja adotar: ");
                                id = leitura.nextInt();
                    
                                existe = false;
                                for (Animal pet : animais) {
                                    if (pet.getId() == id) {
                                        existe = true;
                                        break;
                                    }
                                }
                    
                                if (!existe) {
                                    System.out.println("\nO ID digitado não existe! Tente novamente.");
                                    continue;
                                }
                    
                                disponivel = disponivelAdotar(animais, id);
                                if (!disponivel) {
                                    System.out.println("\nEste pet não está disponível para adoção! Escolha outro.");
                                }
                    
                            } while (!existe || !disponivel);
                    
                            adotar(tutorLogado, animais, id);
                            opcaoAdocao = 0; 
                        }
                    }
                    break;
                case 4:
                    if(!tutorTemAnimal(tutorLogado, animais)) {
                        System.out.println("\nOpção inválida");
                        break;
                    }
                    System.out.println("\nSEUS PETS");
                    exibirAnimaisDoTutor(tutorLogado, animais);
                    System.out.println("\nPressione a tecla ENTER para retornar ao menu anterior...");
                    leitura.nextLine();
                    break;
                case 5:
                    if(!tutorTemAnimal(tutorLogado, animais)) {
                        System.out.println("\nOpção inválida");
                        break;
                    }
                    int opcaoPet;
                    int opcaoEstado;
                    Animal animalParaAlterar = null;

                    exibirAnimaisDoTutor(tutorLogado, animais);

                    do{
                    System.out.print("\nDigite o id do pet que deseja alterar o estado: ");
                        opcaoPet = leitura.nextInt();
                        
                        for(Animal pet : animais){
                            if(pet.getId() == opcaoPet && pet.getTutor() == tutorLogado){
                                animalParaAlterar = pet;
                            }
                        }

                        if(animalParaAlterar == null){
                            System.out.println("\nEsse animal não é seu ou o ID está incorreto. Tente novamente.");
                        }
                    }while(animalParaAlterar == null);

                    do{
                        System.out.println("________________________");
                        System.out.println("|                       |");
                        System.out.println("| > Definir pet como    |");
                        System.out.println("|                       |");
                        System.out.println("| 1. Sujo               |");
                        System.out.println("| 2. Doente             |");
                        System.out.println("| 0. Voltar             |");
                        System.out.println("|_______________________|");
                        System.out.print("\n> Escolha uma opção: ");

                        opcaoEstado = leitura.nextInt();

                        switch (opcaoEstado) {
                            case 1:
                                animalParaAlterar.setLimpo(false);
                                animaisSujos.add(animalParaAlterar);
                                System.out.println("Você alterou o estado do seu pet para sujo!");
                                break;
                            case 2:
                                animalParaAlterar.setSaudavel(false);
                                animaisDoentes.add(animalParaAlterar);
                                System.out.println("Você alterou o estado do seu pet para doente!");
                                break;
                            default:
                                System.out.println("\nOpção inválida");
                                break;
                        }
                    }while(opcaoEstado!=0);

                    break;
                case 0:
                    System.out.println("\nSaindo do perfil...");
                    break;
                default:
                    System.out.println("\nOpção inválida");
                    break;
            }

        } while(opcao!=0);
    }

    private boolean tutorTemAnimal(Tutor tutor, ArrayList<Animal> animais){
        boolean temPet = false;
        for(Animal pet : animais){
            if(pet.getTutor() != null && pet.getTutor().equals(tutor)){
                temPet = true;
            }
        }
        return temPet;
    }
    
    private void adotar(Tutor tutor, ArrayList<Animal> animais, int id){
        clearBuffer(leitura);
        for(Animal pet : animais){
            if(pet.getTutor() == null && pet.getId() == id){
                pet.setTutor(tutor);
                System.out.println("\nVocê adotou "+pet.getNome()+"! ("+pet.getClass().getSimpleName()+")");
                pet.exibirFoto();
            }
        }
        System.out.println("\nPressione a tecla ENTER para retornar ao menu anterior...");
        leitura.nextLine();
    }

    //COLABORADOR
    public void menuColaborador(ArrayList<Colaborador> colaboradores, ArrayList<Animal> animaisSujos, ArrayList<Animal> animaisDoentes){
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
                        menuGroomer((Groomer) colaborador, animaisSujos);
                    } else if (colaborador instanceof Veterinario) {
                        menuVeterinario((Veterinario) colaborador, animaisDoentes);
                    }
                }
            }
        }else{
            System.out.println("\nCredenciais inválidas");
        }
    }

    public void menuGroomer(Groomer groomer, ArrayList<Animal> animaisSujos){
        int opcao;
        do{
            System.out.println("\n> Groomer Logado - "+groomer.getNome());
            System.out.println("__________________________________");
            System.out.println("|                                 |");
            System.out.println("| 1. Ver pets para banho ou tosa  |");
            System.out.println("| 2. Atender próximo pet          |");
            System.out.println("| 3. Ver meu perfil               |");
            System.out.println("| 0. Sair                         |");
            System.out.println("|_________________________________|");
            System.out.print("\n> Escolha uma opção: ");

            opcao = leitura.nextInt();
            clearBuffer(leitura);

            switch(opcao){
                case 1:
                    if(animaisSujos.isEmpty()){
                        System.out.println("\nNão há animais para dar banho ou tosar agora");
                        System.out.println("\nPressione a tecla ENTER para retornar ao menu anterior...");
                        leitura.nextLine();
                    }else{
                        System.out.println("________________________________________________");
                        System.out.println("|                                               |");
                        System.out.println("|          PETS PARA DAR BANHO OU TOSAR         |");
                        System.out.println("|_______________________________________________|");
                        System.out.printf("| %-15s | %-12s | %-5s | %-4s |\n", "Nome", "Espécie", "Idade", "ID");
                        System.out.println("|-----------------|--------------|-------|------|");
                
                        for (Animal petSujo : animaisSujos) {
                            System.out.printf("| %-15s | %-12s | %-5d | %-4d |\n", 
                            petSujo.getNome(), petSujo.getClass().getSimpleName(), petSujo.getIdade(), petSujo.getId());
                        }
                        System.out.println("|_______________________________________________|");
                        System.out.println("\nPressione a tecla ENTER para retornar ao menu anterior...");
                        leitura.nextLine();
                    }
                    break;
                case 2:
                    if(animaisSujos.isEmpty()){
                        System.out.println("\nNão há animais para dar banho ou tosar agora");
                        System.out.println("\nPressione a tecla ENTER para retornar ao menu anterior...");
                        leitura.nextLine();
                    }else{
                        System.out.println("\nATENDIMENTO");
                        Animal pet = animaisSujos.remove(0);
                        pet.setLimpo(true);
                        String tipoAnimal = pet.getClass().getSimpleName();
                        if (tipoAnimal.equals("Cachorro") || tipoAnimal.equals("Gato") || tipoAnimal.equals("Coelho")) {
                            System.out.println("\nVocê deu banho e tosou " + pet.getNome());
                        } else {
                            System.out.println("\nVocê deu banho em " + pet.getNome());
                        }
                        pet.exibirFoto();
                        pet.som();
                    }
                    break;
                case 3:
                    groomer.exibirPerfil();
                    System.out.println("\nPressione a tecla ENTER para retornar ao menu anterior...");
                    leitura.nextLine();
                    break;
            }

        }while(opcao!=0);
    }

    public void menuVeterinario(Veterinario veterinario, ArrayList<Animal> animaisDoentes) {
        int opcao;
        do {
            System.out.println("\n> Veterinário Logado - " + veterinario.getNome());
            System.out.println("__________________________________");
            System.out.println("|                                 |");
            System.out.println("| 1. Ver lista de espera          |");
            System.out.println("| 2. Tratar próximo pet           |");
            System.out.println("| 3. Ver meu perfil               |");
            System.out.println("| 0. Sair                         |");
            System.out.println("|_________________________________|");
            System.out.print("\n> Escolha uma opção: ");
    
            opcao = leitura.nextInt();
            clearBuffer(leitura);
    
            switch (opcao) {
                case 1:
                    if (animaisDoentes.isEmpty()) {
                        System.out.println("\nNão há animais aguardando tratamento agora.");
                        System.out.println("\nPressione a tecla ENTER para retornar ao menu anterior...");
                        leitura.nextLine();
                    } else {
                        System.out.println("________________________________________________");
                        System.out.println("|                                               |");
                        System.out.println("|          PETS AGUARDANDO TRATAMENTO           |");
                        System.out.println("|_______________________________________________|");
                        System.out.printf("| %-15s | %-12s | %-5s | %-4s |\n", "Nome", "Espécie", "Idade", "ID");
                        System.out.println("|-----------------|--------------|-------|------|");
                
                        for (Animal petDoente : animaisDoentes) {
                            System.out.printf("| %-15s | %-12s | %-5d | %-4d |\n", 
                            petDoente.getNome(), petDoente.getClass().getSimpleName(), petDoente.getIdade(), petDoente.getId());
                        }
                        System.out.println("|_______________________________________________|");
                        System.out.println("\nPressione a tecla ENTER para retornar ao menu anterior...");
                        leitura.nextLine();
                    }   
                break;
    
                   
                case 2:
                    if (animaisDoentes.isEmpty()) {
                        System.out.println("\nNão há animais aguardando tratamento agora.");
                        System.out.println("\nPressione a tecla ENTER para retornar ao menu anterior...");
                        leitura.nextLine();
                    } else {
                        System.out.println("\nATENDIMENTO");
                        Animal pet = animaisDoentes.remove(0);
                        pet.setSaudavel(true);
                        System.out.println("\nVocê tratou " + pet.getNome() + "!");
                        pet.exibirFoto();
                        pet.som();
                    }
                    break;
                case 3:
                    veterinario.exibirPerfil();
                    System.out.println("\nPressione a tecla ENTER para retornar ao menu anterior...");
                    leitura.nextLine();
                    break;
            }
        } while (opcao != 0);
    }    

    private void listarColaboradores(ArrayList<Colaborador> colaboradores){
        System.out.println("\n>> GROOMERS\n");
        for(Colaborador c : colaboradores){
            if(c instanceof Groomer){
                System.out.println(c.getNome()+" - "+c.getCpf());
            }
        }
        System.out.println("\n>> VETERINÁRIOS\n");
        for(Colaborador c : colaboradores){
            if(c instanceof Veterinario){
                System.out.println(c.getNome()+" - "+c.getCpf());
            }
        }
        clearBuffer(leitura);
        System.out.println("\nPressione a tecla ENTER para retornar ao menu anterior...");
        leitura.nextLine();
    }

    //ANIMAL
    private Animal lerAnimalComTutor(Tutor tutor) {
        System.out.println("\nCADASTRO DE PET");
        System.out.print("Nome: ");
        String nome = leitura.nextLine();
        //clearBuffer(leitura);
        System.out.print("Idade: ");
        int idade = leitura.nextInt();
        clearBuffer(leitura);
    
        int especie;
        Animal a = null;
        do {
            System.out.println("\nESPÉCIES ATENDIDAS");
            System.out.println("1. Cachorro");
            System.out.println("2. Coelho");
            System.out.println("3. Gato");
            System.out.println("4. Jabuti");
            System.out.println("5. Pássaro");
            System.out.print("Indique a espécie (1-5): ");
            especie = leitura.nextInt();
            clearBuffer(leitura);
    
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
                    System.out.println("\nOpção inválida");
                    break;
            }
        } while (especie > 5 || especie < 1);
        return a;
    }
    

    private void exibirAnimaisDoTutor(Tutor tutor, ArrayList<Animal> animais) {
        if (tutorTemAnimal(tutor, animais)) {
            System.out.println("_______________________________________________________________________");
            System.out.println("|                                                                     |");
            System.out.println("|                     Animais do Tutor                                |");
            System.out.println("|_____________________________________________________________________|");
            System.out.printf("| %-15s | %-12s | %-5s | %-5s | %-18s |\n", "Nome", "Espécie", "Idade", "ID", "Estado");
            System.out.println("|-----------------|--------------|-------|-------|--------------------|");
    
            for (Animal pet : animais) {
                if (pet.getTutor() != null && pet.getTutor().equals(tutor)) {
                    String estado = (pet.isLimpo() ? "Limpo" : "Sujo") + " e " + (pet.isSaudavel() ? "Saudável" : "Doente");
                    System.out.printf("| %-15s | %-12s | %-5d | %-5d | %-18s |\n", 
                        pet.getNome(), pet.getClass().getSimpleName(), pet.getIdade(), pet.getId(), estado);
                }
            }
            System.out.println("|_____________________________________________________________________|");
        } else {
            System.out.println("\nNão há animais cadastrados");
        }
    }
    

    private void exibirAnimaisAdocao(ArrayList<Animal> animais) {
        if (animais.isEmpty()) {
            System.out.println("\nNão há animais cadastrados.");
        } else if (temAnimaisDisponiveis(animais)) {
            System.out.println("_________________________________________________");
            System.out.println("|                                                |");
            System.out.println("|              Animais para Adoção               |");
            System.out.println("|________________________________________________|");
            System.out.printf("| %-15s | %-12s | %-5s | %-5s |\n", "Nome", "Espécie", "Idade", "ID");
            System.out.println("|-----------------|--------------|-------|-------|");
    
            for (Animal pet : animais) {
                if (pet.getTutor() == null) {
                    System.out.printf("| %-15s | %-12s | %-5d | %-5d |\n", 
                        pet.getNome(), pet.getClass().getSimpleName(), pet.getIdade(), pet.getId());
                }
            }
            System.out.println("|________________________________________________|");
        } else {
            System.out.println("\nNão há animais disponíveis para adotar.");
        }
    }

    private boolean temAnimaisDisponiveis(ArrayList<Animal> animais){
        boolean disponivel = false;
        for(Animal pet : animais){
            if(pet.getTutor() == null){
                disponivel = true;
            }
        }
        return disponivel;
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
                    System.out.println("\nOpção inválida");
                    break;
            }
        }while(especie>5 || especie<1);
        return a;
    }

    private boolean disponivelAdotar(ArrayList<Animal> animais, int id){
        boolean disponivel = false;
        for(Animal pet : animais){
            if(pet.getTutor() == null && pet.getId() == id){
                disponivel = true;
            }
        }
        return disponivel;
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
                    Colaborador novColaborador = lerColaborador(colaboradores);
                    cadastro.cadastrarColaborador(novColaborador);
                    System.out.println("\nColaborador cadastrado com sucesso!");
                    break;
                case 2:
                    Animal novoAnimal = lerAnimalAdocao();
                    cadastro.cadastrarAnimal(novoAnimal);
                    System.out.println("\nPet para adoção cadastrado com sucesso!");
                    break;
                case 3:
                    System.out.println("\n----- TODOS OS CADASTROS DO SISTEMA -----\n");
                    listarTutores(tutores, animais);
                    System.out.println("\n>> ANIMAIS PARA ADOÇÃO");
                    exibirAnimaisAdocao(animais);
                    listarColaboradores(colaboradores);
                    break;
                default:
                    System.out.println("\nOpção inválida. Tente novamente.");
                    break;
            }
        }while (opcao!=0);
    }
    
    private Colaborador lerColaborador(ArrayList<Colaborador> colaboradores){
        clearBuffer(leitura);
        System.out.println("\nCADASTRO DE COLABORADOR");
        System.out.print("Nome: ");
        String nome = leitura.nextLine();
        String cpf;
        boolean cpfExiste;
        do {
            cpfExiste = false;
            System.out.print("CPF: ");
            cpf = leitura.nextLine();

            for (Colaborador c : colaboradores) {
                if (c.getCpf().equals(cpf)) {
                    cpfExiste = true;
                    System.out.println("Este CPF já está cadastrado! Tente outro.");
                    break;
                }
            }
        } while (cpfExiste);
        String senha;
        String confirmacao = "";
        
        do {
            System.out.print("Digite a senha (mínimo 4 caracteres): ");
            senha = leitura.nextLine();
            
            if (senha.length() < 4) {
                System.out.println("\nA senha deve ter pelo menos 4 caracteres.");
                continue;
            }
        
            System.out.print("Confirme a senha: ");
            confirmacao = leitura.nextLine();
        
            if (!senha.equals(confirmacao)) {
                System.out.println("\nAs senhas devem ser iguais!");
            }
        } while (senha.length() < 4 || !senha.equals(confirmacao));

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
                    System.out.println("\nOpção inválida");
                    break;
            }
        }while(cargo>2 || cargo<1);
        return c;
    }
}