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


//        String[] jogadores = {"player1","player2","player3","player4","player5","player6","player7","player8"};
//        String[] cores = {"black","white","red","green","blue","yellow","orange","pink"};
//        Jogo j = new Jogo(8, jogadores, cores);
//        j.setCompraAutomatica();
        String[] jogadores = {"player1","player2"};
        String[] cores = {"black","white"};
        Jogo j = new Jogo(2, jogadores, cores);


        //# User story 4 - Buying);
//
//###########################################
//# Buying all places up to Illinois Avenue #
//###########################################);
//
//createGame numPlayers=2 playerNames={player1,player2} tokenColors={black,white}
//
        j.processarJogada(1, 1); //1
//expect 2 getPlayerPosition playerName="player1"
//expecterror "Place doesn't have a deed to be bought" buy
        j.buy();
//
        j.processarJogada(1, 2); //2
//expect 3 getPlayerPosition playerName="player2"
        j.buy();
//
        j.processarJogada(1, 1);//3
//expect 4 getPlayerPosition playerName="player1"
//expect 1300 getPlayerMoney playerName="player1"
//expecterror "Place doesn't have a deed to be bought" buy
        j.buy();
//
        j.processarJogada(1, 1);//4
//expect 5 getPlayerPosition playerName="player2"
        j.buy();
//
        j.processarJogada(1, 1);//5
//expect 6 getPlayerPosition playerName="player1"
        j.buy();
//
        j.processarJogada(1, 1);//6
//expect 7 getPlayerPosition playerName="player2"
//expecterror "Place doesn't have a deed to be bought" buy
        j.buy();

        j.processarJogada(1, 1);//7
//expect 8 getPlayerPosition playerName="player1"
        j.buy();
//
        j.processarJogada(1, 1);//8
//expect 9 getPlayerPosition playerName="player2"
        j.buy();
//
        j.processarJogada(1, 1);//9
//expect 10 getPlayerPosition playerName="player1"
//expecterror "Place doesn't have a deed to be bought" buy
        j.buy();
        
        j.processarJogada(1, 1);//10
//expect 11 getPlayerPosition playerName="player2"
        j.buy();
//
        j.processarJogada(1, 1);//11
//expect 12 getPlayerPosition playerName="player1"
//expecterror "Deed for this place is not for sale" buy
        j.buy();
        
        j.processarJogada(1, 1);//12
//expect 13 getPlayerPosition playerName="player2"
        j.buy();
//
        j.processarJogada(1, 1);//13
//expect 14 getPlayerPosition playerName="player1"
        j.buy();
//
        j.processarJogada(1, 1);//14
//expect 15 getPlayerPosition playerName="player2"
        j.buy();
//
        j.processarJogada(1, 1);//15
//expect 16 getPlayerPosition playerName="player1"
        j.buy();
//
        j.processarJogada(1, 1);//16
//expect 17 getPlayerPosition playerName="player2"
//expecterror "Place doesn't have a deed to be bought" buy
        j.buy();
        
        j.processarJogada(1, 1);//17
//expect 18 getPlayerPosition playerName="player1"
        j.buy();
//
        j.processarJogada(1, 1);//18
//expect 19 getPlayerPosition playerName="player2"
        j.buy();
//
        j.processarJogada(1, 1);//19
//expect 20 getPlayerPosition playerName="player1"
//expecterror "Place doesn't have a deed to be bought" buy
        j.buy();

        j.processarJogada(1, 1);//20
//expect 21 getPlayerPosition playerName="player2"
        j.buy();
//
        j.processarJogada(1, 1);//21
//expect 22 getPlayerPosition playerName="player1"
//expecterror "Place doesn't have a deed to be bought" buy
        j.buy();
        
        j.processarJogada(1, 1);//22
//expect 23 getPlayerPosition playerName="player2"
        j.buy();
//
        j.processarJogada(1, 1);//23
//expect 24 getPlayerPosition playerName="player1"
        j.buy();
//
//# Player 2 must not be able to buy B & O Railroad, since he doesn't have enough money (has 0)
        j.processarJogada(1, 1);//24
//expect 25 getPlayerPosition playerName="player2"
//expecterror "Not enough money" buy
        j.buy();
        
//# Player 2 has 0 money - he must not have been excluded from the game
//expect false gameIsOver
//expect "bank" getPlaceOwner placeID=25);
//
//expect "{Oriental Avenue,Vermont Avenue,Virginia Avenue,St. James Place,Tennessee Avenue,Illinois Avenue}" getPlayerDeeds playerName="player1"
//expect 340 getPlayerMoney playerName="player1");
//
//expect "{Baltic Avenue,Reading Railroad,Connecticut Avenue,St. Charles Place,States Avenue,Pennsylvania Railroad,New York Avenue,Kentucky Avenue,Indiana Avenue}" getPlayerDeeds playerName="player2"
//expect 0 getPlayerMoney playerName="player2");
//
//expect "bank" getPlaceOwner placeID=1);
//expect "player1" getPlaceOwner placeID=6
//expect "player1" getPlaceOwner placeID=8
//expect "bank" getPlaceOwner placeID=12
//expect "player1" getPlaceOwner placeID=14
//expect "player1" getPlaceOwner placeID=16
//expect "player1" getPlaceOwner placeID=18
//expect "player1" getPlaceOwner placeID=24
//expect "player2" getPlaceOwner placeID=3
//expect "player2" getPlaceOwner placeID=5
//expect "player2" getPlaceOwner placeID=9
//expect "player2" getPlaceOwner placeID=11);
//expect "player2" getPlaceOwner placeID=13
//expect "player2" getPlaceOwner placeID=15
//expect "player2" getPlaceOwner placeID=19
//expect "player2" getPlaceOwner placeID=21);
//expect "player2" getPlaceOwner placeID=23
//
        j.processarJogada(1, 1);//25
//expect 26 getPlayerPosition playerName="player1");
//
//# Let's make player2, who has money 0, fall on Luxury Tax
        j.processarJogada(6, 5);//26
//expect 36 getPlayerPosition playerName="player2"
//
        j.processarJogada(1, 1);//27
//expect 28 getPlayerPosition playerName="player1"
//
        j.processarJogada(1, 1);//28
//
//# having fallen on Luxury Tax, player2 must have been excluded from the game
//expecterror "Player no longer in the game" getPlayerPosition playerName="player2");
//
//expect true gameIsOver);
//
        j.QuitJogo();

        
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
