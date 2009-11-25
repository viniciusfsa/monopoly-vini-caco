/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package easy;

import Monopoly.Comandos;
import Monopoly.Jogador;
import Monopoly.Jogo;
import Monopoly.Tabuleiro;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Marcus
 */
public class facade {


    Jogo           jogo = null;
    Tabuleiro tabuleiro = null;

    
    //aqui agora eu passo as strings em vez da listas.
    public void createGame(int num, String playerNames, String tokenColors) throws Exception {
       
        String nomes_jogadores[] = playerNames.substring(1, playerNames.length() - 1).split(",");
        String cores_jogadores[] = tokenColors.substring(1, tokenColors.length() - 1).split(",");
        
        this.jogo = new Jogo(num ,nomes_jogadores,cores_jogadores);
        this.jogo.StartJogo();
        this.tabuleiro = new Tabuleiro();

    }

    public int getNumberOfPlayers() {

        return jogo.getNumberOfPlayers();
    }


    public String getPlayerToken(String playerName) throws Exception{
        return this.jogo.getPlayerToken(playerName);
    }

    public int getPlayerMoney(String playerName) throws Exception{
        return this.jogo.getPlayerMoney(playerName);
    }

    public int getPlayerPosition(String playerName) throws Exception{
        return this.jogo.getPlayerPosition(playerName);
    }

    public String getCurrentPlayer() {
        List<Jogador> listaJogador = this.jogo.getListaJogadores();

        Jogador a=listaJogador.get(jogo.jogadorAtual());
        return a.getNome();
    }

    public String getPlayerDeeds(String playerName) throws Exception{
        String propriedades  ="";
        List<Jogador> listaJogador = this.jogo.getListaJogadores();
        
        for (int i = 0; i < listaJogador.size(); i++) {
            Jogador jogador = listaJogador.get(i);
            if (jogador.getNome().equals(playerName)) {
                ArrayList prop =  jogador.getPropriedades();
                Iterator it = prop.iterator();
                while(it.hasNext()){
                    propriedades = propriedades + it.next();
                }
                return "{"+propriedades+"}";
            }
        }

        throw new Exception ("Player doesn't exist");
    }

    public String getCommands(){
        Comandos comandos = new Comandos();
        List a = comandos.getCmds();
        Iterator<String> it = a.iterator();        
        String b = "{";
        while(it.hasNext()){
            b = b+it.next()+",";
            if(!it.hasNext()){
                b = b.substring(0, b.length()-1);
                b=b+"}";}}
        
        return b;

        
    }

    public void quitGame() throws Exception{
        if (this.jogo!=null){
            this.jogo.QuitJogo();
        }
        else{
            throw new Exception("There's no game to quit");
        }
        
    }

    //A gente pode incorporar algumas exceções pra dentro dos objetos em vez do
    //façade
    public String getPlaceName(int placeID) throws Exception{
        return this.tabuleiro.getPlaceName(placeID);
    }
    
    public String getPlaceGroup(int placeID) throws Exception{
        return this.tabuleiro.getLugarGrupo(placeID);
    }

    public String getPlaceOwner(int placeID) throws Exception{
        return jogo.getOwnerPlace(placeID);
        //return this.tabuleiro.getLugarDono(placeID);
    }

    public int getPlacePrice(int placeID) throws Exception{
        return this.tabuleiro.getLugarPreçoCompra(placeID);
    }


    public int getPropertyRent(int placeID) throws Exception{
        return this.tabuleiro.getLugarPrecoAluguel(placeID);
    }
}
