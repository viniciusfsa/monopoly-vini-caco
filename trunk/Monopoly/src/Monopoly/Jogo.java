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
     static List<String> cores = new ArrayList();

     static String[] coresPermitidas = {"black", "white", "red", "green", "blue", "orange", "yellow", "pink", "brown"};

     static boolean status = false;

     static private int vez = 0;



    private ArrayList<Jogador> Jogadores = null;
    Comandos cmds = new Comandos();



    //mudei a assinatura, em vez de listas, vetores de strings.
    public Jogo(int quantidade, String[] nomes_jogadores, String[] cores_jogadores) throws Exception {


        //só isso é suficiente
        if ((quantidade == 1) || (quantidade > 8)) {
            throw new Exception("Invalid number of players");
        }



        if (nomes_jogadores.length < quantidade) {
            throw new Exception("Too few player names");
        }

        if (nomes_jogadores.length > quantidade) {
            throw new Exception("Too many player names");
        }

        if (cores_jogadores.length<nomes_jogadores.length){
            throw new Exception("Too few token colors");
        }

        if (cores_jogadores.length>nomes_jogadores.length){
            throw new Exception("Too many token colors");
        }



        if (this.hasRepeatedName(nomes_jogadores)){
           throw new Exception("There mustn't be repeated player names");
        }

        if (this.hasRepeatedName(cores_jogadores)){
           throw new Exception("There mustn't be repeated token colors");
        }







    }


    private boolean hasRepeatedName (String[] v){


        for (int i=0; i<v.length;i++){
            for (int j = 0; j<v.length; j++){
                if ((i!=j) && (v[i].equals(v[j]))){
                    return true;
                }
            }
        }
        
        return false;        
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
