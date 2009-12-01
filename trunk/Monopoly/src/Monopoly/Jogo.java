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

    private List<Jogador> listaJogadores = new ArrayList();
    private List<String> listaJogadoresFalidos = new ArrayList();
    static String[] coresPermitidas = {"black", "white", "red", "green", "blue", "orange", "yellow", "pink", "brown"};
    private boolean status = false;
    private int vez = 0;
    private int posicoes[] = {40, 40, 40, 40, 40, 40, 40, 40};
    private Hashtable Donos = new Hashtable();
    private Tabuleiro tabuleiro = new Tabuleiro();
    Comandos cmds = new Comandos();
    private boolean compra_automatica = false;
    int[] DonosFerrovias = {0, 0, 0, 0, 0, 0, 0, 0};
    private int dinheiroBanco = 0;

    //mudei a assinatura, em vez de listas, vetores de strings.
    public Jogo(int quantidade, String[] nomes_jogadores, String[] cores_jogadores) throws Exception {


        //só separei o tratamento de erros
        listaJogadoresFalidos.clear();
        listaJogadores.clear();
        this.tratarErrosIniciais(quantidade, nomes_jogadores, cores_jogadores);
        resetInitDonos();

        for (int i = 0; i < nomes_jogadores.length; i++) {
            this.listaJogadores.add(new Jogador(nomes_jogadores[i], cores_jogadores[i], i));
        }
        status = true;

    }

    public void showPosicoes() {
//        this.print("\n");
        System.out.println("");
        for (int i = 0; i < listaJogadores.size(); i++) {
            System.out.print(posicoes[i] + "\t");
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
        //Donos.put(12, "Electric Company");
        Donos.put(17, "noOwner");
        Donos.put(20, "noOwner");
        Donos.put(22, "noOwner");
        //Donos.put(28, "Water Works");
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

        if (listaJogadoresFalidos.contains(playerName)) {
            throw new Exception("Player no longer in the game");
        }

        int Id = this.getJogadorByName(playerName).getId();
        return posicoes[Id];
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

    public boolean isGameFinished() {
        if (listaJogadores.size() - listaJogadoresFalidos.size() == 1) {
            return true;
        } else {
            return false;
        }

    }

    public int getNumberOfPlayers() {
        return listaJogadores.size() - listaJogadoresFalidos.size();
    }

    public String getOwnerPlace(int idPlace) throws Exception {
        if (idPlace > 40 || idPlace < 1) {

            throw new Exception("Place doesn't exist");
        } else {
            String dono = (String) Donos.get(idPlace);
            if (dono.equals("noOwner") || dono.equals("Luxury Tax") || dono.equals("Income Tax")) {
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

        this.print("vez agora eh " + vez);
    }

    public int jogadorAtual() {

        return vez;
    }

    public void setCompraAutomatica() {
        this.compra_automatica = true;
    }

    public void unsetCompraAutomatica() {
        this.compra_automatica = false;
    }

    //processar Jogada já faz tudo... pergunta se a compra é automática e já compra.
    public void processarJogada(int resultadoDado1, int resultadoDado2) throws Exception {

        //
        if ((isResultadoDadoValido(resultadoDado1)) && (isResultadoDadoValido(resultadoDado2))) {
            this.print("Jogador " + this.jogadorAtual());
            this.print("\tEstá em " + this.posicoes[this.jogadorAtual()]);
            this.print("\tvai andar " + (resultadoDado1 + resultadoDado2) + " casas.");

            this.moverJogadorDaVez(resultadoDado1, resultadoDado2);
        }



    }

    public void pagarFerrovia(int credor, int devedor, int valor, String NomePopriedade) {
        Jogador JogadorDevedor = listaJogadores.get(devedor);
        Jogador JogadorCredor = listaJogadores.get(credor);
          if ((NomePopriedade.equals("Reading Railroad")) ||
                (NomePopriedade.equals("Pennsylvania Railroad")) ||
                (NomePopriedade.equals("B & O Railroad")) ||
                (NomePopriedade.equals("Short Line Railroad"))) {
            int quantidadeFerrovias = DonosFerrovias[credor];
            int divida = quantidadeFerrovias * valor;
                this.print("Credor tem "+ quantidadeFerrovias);
                this.print("Divida eh "+ divida);

            if (listaJogadores.get(devedor).getDinheiro() > divida) {
                JogadorDevedor.retirarDinheiro(divida);
                JogadorCredor.addDinheiro(divida);
                this.print("aqui");

            } else {
                int DinheiroRestante = listaJogadores.get(devedor).getDinheiro();
                JogadorDevedor.retirarDinheiro(DinheiroRestante);

                JogadorCredor.addDinheiro(DinheiroRestante);
                this.removePlayer(devedor);

            }

        }
    }

    public void pagarAluguel(int credor, int devedor, int valor, String NomePopriedade) {
        Jogador JogadorDevedor = listaJogadores.get(devedor);
        Jogador JogadorCredor = listaJogadores.get(credor);

      
            if (listaJogadores.get(devedor).getDinheiro() > valor) {
                JogadorDevedor.retirarDinheiro(valor);
                JogadorCredor.addDinheiro(valor);

            } else {
                int DinheiroRestante = listaJogadores.get(devedor).getDinheiro();
                JogadorDevedor.retirarDinheiro(DinheiroRestante);
                JogadorCredor.addDinheiro(DinheiroRestante);
                this.removePlayer(devedor);

            }
        

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

//    private void moverJogadorDaVez(int valorDados) throws Exception {
    private void moverJogadorDaVez(int dado1, int dado2) throws Exception {

        int valorDados = dado1+dado2;
//        this.print("Situacao de ferrovias");
//        for(int t = 0;t<DonosFerrovias.length; t++)
//            System.out.print(DonosFerrovias[t]+" ");


        //preciso saber se o jogador vai passar pela posição 40, o que significa
        //ganhar dinheiro
        int jogador = this.jogadorAtual();
        if ((this.posicoes[jogador] + valorDados) >= 40 && this.posicoes[jogador] != 40) {
            this.listaJogadores.get(jogador).addDinheiro(200);
            this.print("\tGanha $200 por passar pela casa 40.");
        }



        //movendo à posição
        this.posicoes[jogador] = (this.posicoes[jogador] + valorDados);
        if (posicoes[jogador] > 40) {
            posicoes[jogador] = posicoes[jogador] - 40;
        }
        this.print("\tAtual dinheiro antes de ver a compra:" + this.listaJogadores.get(jogador).getDinheiro());
        this.print("\tVai até a posição " + this.posicoes[jogador]);


        //realizando a compra
        Lugar lugar = this.tabuleiro.get(this.posicoes[jogador] - 1);//busca em -1, pois eh um vetor
        if (this.isCompraAutomatica() && this.posicaoCompravel(this.posicoes[jogador])) {
            this.print("\tO lugar " + lugar.getNome() + " está à venda!");
            this.print("\tPreço:" + lugar.getPrecoCompra());
            this.print("\tAtual dinheiro:" + this.listaJogadores.get(jogador).getDinheiro());
            this.print("\tTenta realizar a compra");
            if (this.efetuarCompra(this.posicoes[jogador], this.listaJogadores.get(jogador))) {
                this.print("\tJogador compra " + lugar.getNome());

            } else {
                this.print("\tCompra não realizada!");
            }


        } else if (!this.posicaoCompravel(this.posicoes[jogador])) {
            this.print("\t" + lugar.getNome() + " não está à venda!");

            String dono = (String) Donos.get(this.posicoes[jogador]);

            for (int i = 0; i < listaJogadores.size(); i++) {
                Jogador possivelDono = listaJogadores.get(i);
                if (possivelDono.getNome().equals(dono)&&posicoes[jogador]!=5 && posicoes[jogador]!=15&& posicoes[jogador]!=25&& posicoes[jogador]!=35) {
                    this.print("O dono eh " + possivelDono.getNome());
                    int valorAluguel = this.tabuleiro.getLugarPrecoAluguel(this.posicoes[jogador]);
                    if (listaJogadores.get(jogador).getDinheiro() > valorAluguel) {
                        this.pagarAluguel(possivelDono.getId(), jogador, valorAluguel, lugar.getNome());
                    } else {
                        int DinheiroRestante = listaJogadores.get(jogador).getDinheiro();
                        this.pagarAluguel(possivelDono.getId(), jogador, DinheiroRestante, lugar.getNome());
                        this.removePlayer(jogador);

                    }

                }
                if(possivelDono.getNome().equals(dono)){
                    this.print("O dono eh " + possivelDono.getNome());
                    this.pagarFerrovia(possivelDono.getId(), jogador, 25, lugar.getNome());
                }


            }

        }




        if (Donos.get(this.posicoes[jogador]).equals("Income Tax")) {
            this.print("\tpagando imposto");
            if (listaJogadores.get(jogador).getDinheiro() > 200) {
                listaJogadores.get(jogador).retirarDinheiro(200);
                dinheiroBanco = dinheiroBanco + 200;
            } else {
                int DinheiroRestante = listaJogadores.get(jogador).getDinheiro();
                listaJogadores.get(jogador).retirarDinheiro(DinheiroRestante);
                dinheiroBanco = dinheiroBanco + DinheiroRestante;
                this.removePlayer(jogador);

            }

        } else if (Donos.get(this.posicoes[jogador]).equals("Luxury Tax")) {
            if (listaJogadores.get(jogador).getDinheiro() > 75) {
                listaJogadores.get(jogador).retirarDinheiro(75);
                dinheiroBanco = dinheiroBanco + 75;
            } else {
                int DinheiroRestante = listaJogadores.get(jogador).getDinheiro();
                listaJogadores.get(jogador).retirarDinheiro(DinheiroRestante);
                dinheiroBanco = dinheiroBanco + DinheiroRestante;
                this.removePlayer(jogador);


            }
        }

        this.print("\tAtual dinheiro depois:" + this.listaJogadores.get(jogador).getDinheiro());

        for (int i = 0; i < this.listaJogadores.size(); i++) {
            Jogador j = this.listaJogadores.get(i);
            this.print("din " + 1 + " = " + j.getDinheiro());

        }


        do {
            this.PrepareNextJogada();
        } while (this.listaJogadoresFalidos.contains(listaJogadores.get(vez).getNome()));

        this.showPosicoes();



    }

    public void removePlayer(int id) {
        listaJogadoresFalidos.add(listaJogadores.get(vez).getNome());
        //liberando os pertences
        String NomeFalido = listaJogadores.get(vez).getNome();
        for (int i = 1; i <= Donos.size(); i++) {
            if (Donos.get(i).equals(NomeFalido)) {
                Donos.put(i, "bank");
            }

        }
        //this.listaJogadores.remove(id);
        //vez--;

    }

    public boolean isCompraAutomatica() {
        return this.compra_automatica;
    }

    
    public boolean buy() throws Exception{

        if (this.posicaoCompravel(posicoes[jogadorAtual()])&&posicoes[jogadorAtual()]!=12&&posicoes[jogadorAtual()]!=28) {
          int posicaoTabuleiro = posicoes[jogadorAtual()];
            int preco = this.tabuleiro.getLugarPrecoCompra(posicaoTabuleiro);
            Jogador j = listaJogadores.get(vez);
            if (preco <= j.getDinheiro()) {
                this.print("\tPossui dinheiro para a compra!");
                j.retirarDinheiro(preco);
                this.Donos.put(posicaoTabuleiro, j.getNome());

                String nomeLugar = this.tabuleiro.getPlaceName(posicaoTabuleiro);
                j.addPropriedade(nomeLugar);
                if (nomeLugar.equals("Reading Railroad") || nomeLugar.equals("Pennsylvania Railroad") ||
                        nomeLugar.equals("B & O Railroad") || nomeLugar.equals("Short Line Railroad")) {
                    DonosFerrovias[jogadorAtual()]++;
                }
                return true;
            } else {
                this.print("\tNão possui dinheiro para realizar a compra!");
                return false;
            }

        }
        else{
            throw new Exception("Place doesn't have a deed to be bought");

        }
        

        
    }

    public boolean efetuarCompra(int posicaoTabuleiro, Jogador j) throws Exception {
        if (this.posicaoCompravel(posicaoTabuleiro)&&posicaoTabuleiro!=12&&posicaoTabuleiro!=28) {
//            
            int preco = this.tabuleiro.getLugarPrecoCompra(posicaoTabuleiro);
            if (preco <= j.getDinheiro()) {
                this.print("\tPossui dinheiro para a compra!");
                j.retirarDinheiro(preco);
                this.Donos.put(posicaoTabuleiro, j.getNome());

                String nomeLugar = this.tabuleiro.getPlaceName(posicaoTabuleiro);
                j.addPropriedade(nomeLugar);
                if (nomeLugar.equals("Reading Railroad") || nomeLugar.equals("Pennsylvania Railroad") ||
                        nomeLugar.equals("B & O Railroad") || nomeLugar.equals("Short Line Railroad")) {
                    DonosFerrovias[jogadorAtual()]++;
                }
                return true;
            } else {
                this.print("\tNão possui dinheiro para realizar a compra!");
                return false;
            }

        }
        return false;

    }



    /**
     * Apenas um encapsulador para o System.out.println(String)
     * @param msg
     */
    public void print(String msg){
//        System.out.println(msg);
    }
}
