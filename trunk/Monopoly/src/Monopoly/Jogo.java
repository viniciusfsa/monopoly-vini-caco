package Monopoly;


import java.util.ArrayList;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Marcus
 */
public class Jogo {

     static List<Jogador> listaJogador = new ArrayList();

     static private int vez = 0;



    private ArrayList<Jogador> Jogadores = null;
    Comandos cmds = new Comandos();



    public Jogo(List<Jogador> Jogadores) {
        this.Jogadores = (ArrayList<Jogador>) Jogadores;

    }


    public static void main(String[] args) {
        listaJogador.add(new Jogador("maria", "rosa"));
        listaJogador.add(new Jogador("maria2", "preto"));

        Jogo jogo = new Jogo(listaJogador);
        System.out.println(jogo.getNumberOfPlayers());
        System.out.println(jogo.jogadorAtual());
    }

    public void StartJogo() {
        System.out.println("O jogo Monopoly foi iniciado.");
        System.out.println("A jogada de " + Jogadores.get(0).getNome() + " começou:");
  
        System.out.print("Comandos disponíveis: ");
        cmds.showComandos();
    }

    public void QuitJogo(){
        System.exit(0);
    }

    public int getNumberOfPlayers(){
     return Jogadores.size();
    }


    public void nextJogada(){
        if(vez==listaJogador.size()-1)
            vez = 0;
        else
            vez++;

        
    }

    public int jogadorAtual(){

        return vez;
    }



    
}
