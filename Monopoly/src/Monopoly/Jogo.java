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
    List<Jogador> listaJogadores = new ArrayList();
    static String[] coresPermitidas = {"black", "white", "red", "green", "blue", "orange", "yellow", "pink", "brown"};
    static boolean status = false;
    private int vez = 0;
    private int posicoes[] = {40, 40, 40, 40, 40, 40, 40, 40};
    private Hashtable Donos = new Hashtable();
    private Tabuleiro tabuleiro = new Tabuleiro();
    Comandos cmds = new Comandos();
    private boolean compra_automatica = false;
    String[] DonosFerrovias = {"","","","","","","",""};
    
    private int dinheiroBanco = 0;

    //mudei a assinatura, em vez de listas, vetores de strings.



    public Jogo(int quantidade, String[] nomes_jogadores, String[] cores_jogadores) throws Exception {


        //só separei o tratamento de erros
        this.tratarErrosIniciais(quantidade, nomes_jogadores, cores_jogadores);
        resetInitDonos();

        for (int i = 0; i < nomes_jogadores.length; i++) {
            this.listaJogadores.add(new Jogador(nomes_jogadores[i], cores_jogadores[i], i));
        }
        status = true;

    }

    public void showPosicoes(){
        System.out.println("");
        for (int i = 0; i < listaJogadores.size(); i++) {
            System.out.print(posicoes[i]+"\t");
        }
        
    }

    public List<Jogador> getListaJogadores() {
        return this.listaJogadores;
    }

    public void resetInitDonos() {
        Donos.clear();
        for (int i = 1; i <= 40; i++) {
            Donos.put(i, "bank");
        }
        Donos.put(2, "noOwner");
        Donos.put(4, "Income Tax");
        Donos.put(7, "noOwner");
        Donos.put(10, "noOwner");
        Donos.put(17, "noOwner");
        Donos.put(20, "noOwner");
        Donos.put(22, "noOwner");
        Donos.put(30, "noOwner");
        Donos.put(33, "noOwner");
        Donos.put(36, "noOwner");
        Donos.put(38, "Luxury Tax");
        Donos.put(40, "noOwner");

    }

    public boolean posicaoCompravel(int posicao) {

        String dono = (String) this.Donos.get(posicao);
        if (dono.equals("bank")) {
            return true;
        }
        return false;
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

    public String getOwnerPlace(int idPlace) throws Exception {
        if (idPlace > 40 || idPlace < 1) {
            throw new Exception("Place doesn't exist");
        } else {
            String dono = (String) Donos.get(idPlace);
            if (dono.equals("noOwner")||dono.equals("Luxury Tax")||dono.equals("Income Tax")) {
                throw new Exception("This place can't be owned");
            } else {
                return (String) Donos.get(idPlace);
            }
        }
    }

    public void PrepareNextJogada() {
        if (vez == listaJogadores.size() - 1) {
            vez = 0;
        } else {
            vez++;
        }

    }

    public int jogadorAtual() {

        return vez;
    }

    public void setCompraAutomatica() {
        this.compra_automatica = true;
    }

    public void processarJogada(int resultadoDado1, int resultadoDado2) throws Exception {

        //
        if ((isResultadoDadoValido(resultadoDado1)) && (isResultadoDadoValido(resultadoDado2))) {
            this.moverJogadorDaVez(resultadoDado1 + resultadoDado2);
        }



    }

    public void pagarAluguel(int valor, int IdProprietario){
        listaJogadores.get(IdProprietario).addDinheiro(valor);        
    }



    private boolean isResultadoDadoValido(int resultadoDado) throws Exception {
        if ((resultadoDado > 6) || (resultadoDado < 1)) {
            throw new Exception("Invalid die result");
        }
        return true;
    }

    public void definirCompraAutomatica() {
        this.compra_automatica = true;
    }

    private void moverJogadorDaVez(int valorDados) throws Exception {
       

        //preciso saber se o jogador vai passar pela posição 40, o que significa
        //ganhar dinheiro
        int jogador = this.jogadorAtual();
        if ((this.posicoes[jogador] + valorDados) >= 40) {
            this.listaJogadores.get(jogador).addDinheiro(200);
        }
//
        
        //movendo à posição
        this.posicoes[jogador] = (this.posicoes[jogador] + valorDados);
        if(posicoes[jogador]>40)
            posicoes[jogador] = posicoes[jogador]-40;


        //realizando a compra


        if (this.isCompraAutomatica()) {
            this.efetuarCompra(this.posicoes[jogador], this.listaJogadores.get(jogador));
        }

        if(Donos.get(this.posicoes[jogador]).equals("income tax")){
            listaJogadores.get(vez).retirarDinheiro(200);
            dinheiroBanco = dinheiroBanco + 200;
        }
        else if(Donos.get(this.posicoes[jogador]).equals("luxury tax")){
            listaJogadores.get(vez).retirarDinheiro(75);
            dinheiroBanco = dinheiroBanco + 75;
        }

        

    

        System.out.println("Jogador "+(vez+1) +" agora tem"  + listaJogadores.get(vez).getDinheiro());

        this.PrepareNextJogada();

        //this.showPosicoes();



    }

    public boolean isCompraAutomatica() {
        return this.compra_automatica;
    }

    public void efetuarCompra(int posicaoTabuleiro, Jogador j) throws Exception {
        if (this.posicaoCompravel(posicaoTabuleiro)) {
            
            int preco = this.tabuleiro.getLugarPrecoCompra(posicaoTabuleiro);
         if (preco <= j.getDinheiro()) {
               j.retirarDinheiro(preco);
                this.Donos.put(posicaoTabuleiro, j.getNome());

                String nomeLugar = this.tabuleiro.getPlaceName(posicaoTabuleiro);
                if(nomeLugar.equals("Reading Railroad")||nomeLugar.equals("Pennsylvania Railroad")||
                        nomeLugar.equals("B & O Railroad")||nomeLugar.equals("Short Line Railroad")){
                   // this.DonosFerrovias[j.]=nomeLugar;
                }

                

            }

        }

    }
}
