package Monopoly;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Marcus
 */
public class principal {

//    static List<Jogador> listaJogador = new ArrayList();
//    static List<String> cores = new ArrayList();

    public static void main(String[] args) throws Exception {


        String[] jogadores = {"player1","player2","player3","player4","player5","player6","player7","player8"};
        String[] cores = {"black","white","red","green","blue","yellow","orange","pink"};
        Jogo j = new Jogo(8, jogadores, cores);
        j.setCompraAutomatica();

        // 1st round
        j.processarJogada( 1, 1 );
        j.processarJogada( 1, 2 );
        j.processarJogada( 1, 3 );
        j.processarJogada( 1, 4 );
        j.processarJogada( 1, 5 );
        j.processarJogada( 1, 6 );
        j.processarJogada( 2, 6 );
        j.processarJogada( 3, 6 );

        // 2nd round
        j.processarJogada( 4, 5 );
        j.processarJogada( 4, 5 );
        j.processarJogada( 4, 5 );
        j.processarJogada( 4, 5 );
        j.processarJogada( 4, 5 );
        j.processarJogada( 4, 5 );
        j.processarJogada( 4, 5 );
        j.processarJogada( 4, 5 );

        // 3rd round
        j.processarJogada( 4, 4 );
        j.processarJogada( 4, 5 );
        j.processarJogada( 4, 5 );
        j.processarJogada( 4, 5 );
        j.processarJogada( 4, 5 );
        j.processarJogada( 4, 5 );
        j.processarJogada( 4, 5 );
        j.processarJogada( 4, 5 );

        // 4th round
        j.processarJogada( 4, 5 );
        j.processarJogada( 4, 4 );
        j.processarJogada( 4, 5 );
        j.processarJogada( 4, 5 );
        j.processarJogada( 4, 5 );
        j.processarJogada( 4, 5 );
        j.processarJogada( 4, 5 );
        j.processarJogada( 4, 5 );

        // 5th round
        j.processarJogada( 4, 5 );
        j.processarJogada( 4, 5 );
        j.processarJogada( 4, 4 );
        j.processarJogada( 4, 4 );
        j.processarJogada( 4, 4 );

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Entre com o número de jogadores [2-8]:");
//        int n = Integer.parseInt(scanner.nextLine());
//
//        if(n<2)
//            n=2;
//        if(n>8)
//            n=8;
//
//        for (int i = 1; i <= n; i++) {
//
//
//            System.out.println("Entre com o nome do jogador no." + i + ":");
//            String nome = scanner.nextLine();
//
//            System.out.println("Escolha a cor do peão do jogador no. " + i + " entre as opções seguintes:");
//            System.out.println("[preto][branco][vermelho][verde][azul][amarelo][laranja][rosa]");
//            String corPeao = scanner.nextLine();
//            listaJogador.add(new Jogador(nome, corPeao));
//

//        }

//        Jogo jogo = new Jogo(listaJogador.size(),listaJogador,cores);
//        jogo.StartJogo();

    }
}
