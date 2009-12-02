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

    public static void main(String[] args) throws Exception {


//        String[] jogadores = {"player1","player2","player3","player4","player5","player6","player7","player8"};
//        String[] cores = {"black","white","red","green","blue","yellow","orange","pink"};
//        Jogo j = new Jogo(8, jogadores, cores);
//        j.setCompraAutomatica();
        String[] jogadores = {"player1","player2"};
        String[] cores = {"black", "white"};
        Jogo j = new Jogo(2, jogadores, cores);

        j.processarJogada(1, 1);
//        j.buy();
        j.processarJogada(1, 2);
        j.buy();

     
    }
}
