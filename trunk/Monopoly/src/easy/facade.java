/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package easy;

import Monopoly.Jogador;
import Monopoly.Jogo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Marcus
 */
public class facade {

    static List<Jogador> listaJogador = new ArrayList();
    static Jogo jogo = null;

    public void createGame(int numPlayers, String playerNames, String tokenColors) {
        playerNames = playerNames.substring(1, playerNames.length() - 1);
        tokenColors = tokenColors.substring(1, tokenColors.length() - 1);
        String nome[] = playerNames.split(",");
        String corPeao[] = tokenColors.split(",");

       

        for (int i = 0; i < numPlayers; i++) {
            listaJogador.add(new Jogador(nome[i], corPeao[i]));
        }

        jogo = new Jogo(listaJogador);        
        jogo.StartJogo();

    }

    public int getNumberOfPlayers() {

        return jogo.getNumberOfPlayers();
    }

    public String getPlayerToken(String playerName) {
        for (int i = 0; i < listaJogador.size(); i++) {
            Jogador a = listaJogador.get(i);
            if (a.getNome().equals(playerName)) {
                return a.getCorPeao();
            }
        }
        return null;
    }

    public int getPlayerMoney(String playerName) {

        for (int i = 0; i < listaJogador.size(); i++) {
            Jogador a = listaJogador.get(i);
            if (a.getNome().equals(playerName)) {
                return a.getDinheiro();
            }
        }
        return 0;
    }

    public int getPlayerPosition(String playerName) {

        for (int i = 0; i < listaJogador.size(); i++) {
            Jogador a = listaJogador.get(i);
            if (a.getNome().equals(playerName)) {
                return a.getPosicao();
            }
        }
        return 0;
    }

    public String getCurrentPlayer() {


        Jogador a=listaJogador.get(jogo.jogadorAtual());
        return a.getNome();
    }

    public String getPlayerDeeds(String playerName){
        String propriedades  ="";
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

        return null;
    }
}
