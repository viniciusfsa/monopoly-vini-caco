package Monopoly;

import java.util.ArrayList;
import java.util.Hashtable;
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

    //pq static?
//     static List<Jogador> listaJogadores = new ArrayList();
    List<Jogador> listaJogadores = new ArrayList();
//     static List<String> listaCoresJogadores = new ArrayList();
    static String[] coresPermitidas = {"black", "white", "red", "green", "blue", "orange", "yellow", "pink", "brown"};
    static boolean status = false;
    static private int vez = 0;
    static int posicoes[] = {40, 40, 40, 40, 40, 40, 40, 40};
    static Hashtable Donos = new Hashtable();
//    private ArrayList<Jogador> Jogadores = null;
    Comandos cmds = new Comandos();

    //mudei a assinatura, em vez de listas, vetores de strings.
    public Jogo(int quantidade, String[] nomes_jogadores, String[] cores_jogadores) throws Exception {

        
        //só separei o tratamento de erros
        this.tratarErrosIniciais(quantidade, nomes_jogadores, cores_jogadores);
        resetInitDonos();

        for (int i = 0; i < nomes_jogadores.length; i++) {
            this.listaJogadores.add(new Jogador(nomes_jogadores[i], cores_jogadores[i], i + 1));
        }
        status = true;

    }

    public List<Jogador> getListaJogadores() {
        return this.listaJogadores;
    }

    static public void resetInitDonos() {
        Donos.clear();
        for(int i = 0; i<40;i++){
            Donos.put(i, "bank");
        }
        Donos.put(2,"noOwner");     
        Donos.put(4,"noOwner");
        Donos.put(7,"noOwner");
        Donos.put(10,"noOwner");
        Donos.put(17,"noOwner");
        Donos.put(20,"noOwner");
        Donos.put(22,"noOwner");
        Donos.put(30,"noOwner");
        Donos.put(33,"noOwner");
        Donos.put(36,"noOwner");
        Donos.put(38,"noOwner");
        Donos.put(40,"noOwner");

    }

    public String getPlayerToken(String playerName) throws Exception {
        return this.getJogadorByName(playerName).getCorPeao();
    }

    public int getPlayerMoney(String playerName) throws Exception {
        return this.getJogadorByName(playerName).getDinheiro();
    }

    public int getPlayerPosition(String playerName) throws Exception {
        int Id = this.getJogadorByName(playerName).getId();
        return posicoes[Id + 1];
    }

    private Jogador getJogadorByName(String playerName) throws Exception {
        for (int i = 0; i < this.listaJogadores.size(); i++) {
            Jogador j = this.listaJogadores.get(i);
            if (j.getNome().equals(playerName)) {
                return j;
            }
        }

        throw new Exception("Player doesn't exist");
    }

    private void tratarErrosIniciais(int quantidade, String[] nomes_jogadores, String[] cores_jogadores) throws Exception {
        //só isso é suficiente
        if ((quantidade == 1) || (quantidade > 8)) {
            throw new Exception("Invalid number of players");
        }


        if (this.hasInvalidName(nomes_jogadores)) {
            throw new Exception("Invalid player name");
        }


        if (nomes_jogadores.length < quantidade) {
            throw new Exception("Too few player names");
        }

        if (nomes_jogadores.length > quantidade) {
            throw new Exception("Too many player names");
        }

        if (cores_jogadores.length < nomes_jogadores.length) {
            throw new Exception("Too few token colors");
        }

        if (cores_jogadores.length > nomes_jogadores.length) {
            throw new Exception("Too many token colors");
        }



        if (this.hasRepeatedName(nomes_jogadores)) {
            throw new Exception("There mustn't be repeated player names");
        }

        if (this.hasRepeatedName(cores_jogadores)) {
            throw new Exception("There mustn't be repeated token colors");
        }


        boolean cor_valida = true;
        for (int i = 0; i < cores_jogadores.length && cor_valida; i++) {
            if (!this.isCorPermitida(cores_jogadores[i])) {
                cor_valida = false;
            }
        }

        if (!cor_valida) {
            throw new Exception("Invalid token color");
        }

    }

    /**
     * Verifica se uma cor é permitida
     * @param cor o nome da cor
     * @return a posiçã
     */
    private boolean isCorPermitida(String cor) {

        int pos = -1;
        for (int i = 0; i < Jogo.coresPermitidas.length && pos == -1; i++) {
            if (cor.equals(Jogo.coresPermitidas[i])) {
                pos = i;
            }
        }

        return (pos != -1);
    }

    private boolean hasRepeatedName(String[] v) {
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v.length; j++) {
                if ((i != j) && (v[i].equals(v[j]))) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasInvalidName(String[] n) throws Exception {
        boolean repeat = false;
        for (int i = 0; i < n.length; i++) {
            if (n[i].equals("bank")) {
                repeat = true;
            }
        }
        return repeat;
    }

    public void StartJogo() {
    }

    public void QuitJogo() throws Exception {
        if (status == false) {
            throw new Exception("There's no game to quit");
        }
    }

    public int getNumberOfPlayers() {
        return listaJogadores.size();
    }

    public String getOwnerPlace(int idPlace) throws Exception{
        if(idPlace>40||idPlace<1)
            throw new Exception("Place doesn't exist");
        else{
            String dono = (String) Donos.get(idPlace);
            if(dono.equals("noOwner"))
                throw new Exception("This place can't be owned");
            else
                return (String) Donos.get(idPlace);
        }
    }

    public void nextJogada() {
        if (vez == listaJogadores.size() - 1) {
            vez = 0;
        } else {
            vez++;
        }


    }

    public int jogadorAtual() {

        return vez;
    }
}
