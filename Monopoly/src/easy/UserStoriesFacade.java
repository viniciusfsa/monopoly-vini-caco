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
 * Facade das user stories
 * @author Marcus
 */
public class UserStoriesFacade {


    Jogo           jogo = null;
    Tabuleiro tabuleiro = null;

    
    /**
     * Criacao de um jogo
     * @param num total de jogadores
     * @param playerNames nomes dos jogadores
     * @param tokenColors nomes das cores dos peoes dos jogadores
     * @throws Exception
     */
    public void createGame(int num, String playerNames, String tokenColors) throws Exception {
       
        String nomes_jogadores[] = playerNames.substring(1, playerNames.length() - 1).split(",");
        String cores_jogadores[] = tokenColors.substring(1, tokenColors.length() - 1).split(",");
        
        this.jogo = new Jogo(num ,nomes_jogadores,cores_jogadores);
        this.jogo.StartJogo();
        this.tabuleiro = new Tabuleiro();

    }

    /**
     * Seta a compra automatica
     */
    public void setAutomaticBuying(){
        this.jogo.setCompraAutomatica();
    }


    /**
     * Consulta o total de jogadores
     * @return o total de jogadores
     */
    public int getNumberOfPlayers() {
        return jogo.getNumberOfPlayers();
    }


    /**
     * Executa um movimento considerando os valores dos dados
     * @param firstDieResult resultado do primeiro dado
     * @param secondDieResult resultado do segundo dado
     * @throws Exception
     */
    public void rollDice(int firstDieResult, int secondDieResult) throws Exception{
        this.jogo.processarJogada(firstDieResult,secondDieResult);
    }

    /**
     * Set a compra automatica
     * @param auto 
     */
    public void setAutomaticBuying(boolean auto){
        this.jogo.definirCompraAutomatica();
    }



    /**
     * Obtem a cor do peao do jogador
     * @param playerName nome do jogador
     * @return retorna a cor do peao
     * @throws Exception
     */
    public String getPlayerToken(String playerName) throws Exception{
        jogo.terminarAVez();
        return this.jogo.getPlayerToken(playerName);
    }


    /**
     * Obtem o dinheiro do jogador
     * @param playerName nome do jogador
     * @return o dinheiro
     * @throws Exception
     */
    public int getPlayerMoney(String playerName) throws Exception{
        return this.jogo.getPlayerMoney(playerName);
    }


    /**
     * Obtem a posicao do jogador
     * @param playerName nome do jogador
     * @return a posicao
     * @throws Exception
     */
    public int getPlayerPosition(String playerName) throws Exception{
        return this.jogo.getPlayerPosition(playerName);
    }


    /**
     * Obtem o nome do jogador atual
     * @return o nome do jogador
     */
    public String getCurrentPlayer() {
        jogo.terminarAVez();
        List<Jogador> listaJogador = this.jogo.getListaJogadores();

        Jogador a=listaJogador.get(jogo.jogadorAtual());
        return a.getNome();
    }


    /**
     * Obtem as posses de um jogador
     * @param playerName nome do jogador
     * @return lista de posses do jogador
     * @throws Exception
     */
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
                    if(it.hasNext())
                        propriedades = propriedades+",";
                }
                return "{"+propriedades+"}";
            }
        }

        throw new Exception ("Player doesn't exist");
    }


    /**
     * Obtem os comandos possiveis
     * @return lista de comandos
     */

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


    /**
     * Finaliza um jogo
     * @throws Exception
     */
    public void quitGame() throws Exception{
        if (this.jogo!=null){
            this.jogo.QuitJogo();
        }
        else{
            throw new Exception("There's no game to quit");
        }
        
    }

    /**
     * Obtem o nome do lugar
     * @param placeID id do lugar
     * @return o nome do lugar
     * @throws Exception
     */
    public String getPlaceName(int placeID) throws Exception{
        return this.tabuleiro.getPlaceName(placeID);
    }


    /**
     * Obtem o grupo ao qual um lugar pertence
     * @param placeID o id do lugar
     * @return o nome do grupo
     * @throws Exception
     */
    public String getPlaceGroup(int placeID) throws Exception{
        return this.tabuleiro.getLugarGrupo(placeID);
    }


    /**
     * Obtem o dono de um lugar
     * @param placeID id do lugar
     * @return o nome do dono
     * @throws Exception
     */
    public String getPlaceOwner(int placeID) throws Exception{
        jogo.terminarAVez();
        return jogo.getOwnerPlace(placeID);
    }


    /**
     * Obtem o preco de um lugar
     * @param placeID id do lugar
     * @return o preco
     * @throws Exception
     */
    public int getPlacePrice(int placeID) throws Exception{
        return this.tabuleiro.getLugarPrecoCompra(placeID);
    }


    /**
     * Obtem o valor de aluguel de uma propriedade
     * @param placeID id do lugar
     * @return o preco
     * @throws Exception
     */
    public int getPropertyRent(int placeID) throws Exception{
        return this.tabuleiro.getLugarPrecoAluguel(placeID);
    }

    /**
     * Verifica se o jogo acabou (apenas um jogador restante)
     * @return true se o jogo acabou, false caso contrario
     */
    public boolean gameIsOver (){
        return this.jogo.isGameFinished();
    }


    /**
     * Realiza uma compra
     * @throws Exception
     */
    public void buy() throws Exception{
        this.jogo.buy();
    }
            
}
