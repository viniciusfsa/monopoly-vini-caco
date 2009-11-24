package Monopoly;


import java.lang.Exception;
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
     static List<String> cores = new ArrayList();
     static boolean status = false;

     static private int vez = 0;



    private ArrayList<Jogador> Jogadores = null;
    Comandos cmds = new Comandos();



//    public Jogo(int quantidade, List<Jogador> Jogadores,List<String> cores) throws Exception {
//
//
//        if((Jogadores.size()==1&&quantidade==1)||(Jogadores.size()>8&&quantidade>8))
//            throw new Exception("Invalid number of players");
//
//
//
//        if(Jogadores.size()<quantidade){
//            throw new Exception("Too few player names");}
//
//         if(Jogadores.size()>quantidade)
//            throw new Exception("Too many player names");
//
//
//
//        this.Jogadores = (ArrayList<Jogador>) Jogadores;
//
//    }


    public Jogo(int quantidade, String[] nomes_jogadores, String[] cores_jogadores) throws Exception {


//        if ((nomes_jogadores.length == 1 && quantidade == 1) || (nomes_jogadores.length > 8 && quantidade > 8)) {
        //só isso é suficiente?
        if ((quantidade == 1) || (quantidade > 8)) {
            throw new Exception("Invalid number of players");
        }



        if (nomes_jogadores.length < quantidade) {
            throw new Exception("Too few player names");
        }

        if (nomes_jogadores.length > quantidade) {
            throw new Exception("Too many player names");
        }


        for (int i=0 ; i<nomes_jogadores.length; i++){
            this.Jogadores.add(new Jogador(nomes_jogadores[i], cores_jogadores[i]));
        }





    }


   
    public void StartJogo() {
       
    }

    public void QuitJogo() throws Exception{
       if(status==false)
            throw new Exception("There's no game to quit");
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
