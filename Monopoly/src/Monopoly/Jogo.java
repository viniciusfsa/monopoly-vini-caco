package Monopoly;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * A classe que gerencia todas as operacoes do jogo, alem de saber de quase tudo.
 * @author Marcus
 */
public class Jogo {

    /**
     * Lista dos jogadores participantes do jogo (nao-falidos)
     */
    private List<Jogador> listaJogadores = new ArrayList();

    /**
     * Lista dos jogadores falidos
     */
    private List<String> listaJogadoresFalidos = new ArrayList();

    /**
     * Cores permitidas para os peoes dos jogadores
     */
    static String[] coresPermitidas = {"black", "white", "red", "green", "blue", "orange", "yellow", "pink", "brown"};

    /**
     * O status do jogo (terminado?)
     */
    private boolean status = false;

    /**
     * A vez
     */
    private int vez = 0;

    /**
     * A posicao que cada um dos jogadores ocupa
     */
    private int posicoes[] = {40, 40, 40, 40, 40, 40, 40, 40};

    /**
     * Os donos das propriedades
     */
    private Hashtable Donos = new Hashtable();

    /**
     * O tabuleiro do jogo
     */
    private Tabuleiro tabuleiro = new Tabuleiro();

    /**
     * Lista de comandos
     */
    Comandos cmds = new Comandos();

    /**
     * definicao da compra automatica
     */
    private boolean compra_automatica = false;

    /**
     * Os donos das ferrovias
     */
    int[] DonosFerrovias = {0, 0, 0, 0, 0, 0, 0, 0};

    /**
     * O dinheiro do banco
     */
    private int dinheiroBanco = 0;


    /**
     * Identifica se a vez de um jogador ja terminou ou nao
     */
    private boolean terminouVez = true;

    /**
     * Instancia um jogo
     * @param quantidade quantidade de jogadores
     * @param nomes_jogadores nomes do jogadores
     * @param cores_jogadores cores dos peoes dos jogadores
     * @throws Exception
     */
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
        terminouVez = true;
        dinheiroBanco = 0;
        compra_automatica = false;

    }



    /**
     * Debug para mostrar as posicoes no console
     */
    public void showPosicoes() {
        this.print("\n");
        for (int i = 0; i < listaJogadores.size(); i++) {
            System.out.print(posicoes[i] + "\t");
        }
    }
    

    /**
     * Obtem uma lista dos jogadores 
     * @return
     */
    public List<Jogador> getListaJogadores() {
        return this.listaJogadores;
    }


    /**
     * Define os donos-padrao das propriedades
     */
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


    /**
     * Checa se uma posicao esta disponivel para compra (o banco e o dono)
     * @param posicao a posicao do lugar
     * @return true se a posicao esta disponivel, falso caso contrario
     */
    public boolean posicaoCompravel(int posicao) {

        String dono = (String) this.Donos.get(posicao);
        if (dono.equals("bank")) {
            return true;
        }
        return false;
    }


    /**
     * Obtem a cor do peao de um jogador
     * @param playerName o nome do jogador
     * @return o peao do jogador de nome playerName
     * @throws Exception
     */
    public String getPlayerToken(String playerName) throws Exception {
        return this.getJogadorByName(playerName).getCorPeao();
    }


    /**
     * Obtem o dinheiro de um jogador
     * @param playerName o nome do jogador
     * @return o dinheiro do jogador de nome playerName
     * @throws Exception
     */
    public int getPlayerMoney(String playerName) throws Exception {
        return this.getJogadorByName(playerName).getDinheiro();
    }


    /**
     * Obtem a posicao de um jogador
     * @param playerName o nome do jogador
     * @return a posicao do jogador de nome playerName
     * @throws Exception
     */
    public int getPlayerPosition(String playerName) throws Exception {

        if (listaJogadoresFalidos.contains(playerName)) {
            throw new Exception("Player no longer in the game");
        }

        int Id = this.getJogadorByName(playerName).getId();
        return posicoes[Id];
    }



    /**
     * Obtem um jogador atraves de seu nome
     * @param nomeJogador nome do jogador
     * @return Jogador de nome nomeJogador
     * @throws Exception
     */
    private Jogador getJogadorByName(String nomeJogador) throws Exception {
        for (int i = 0; i < this.listaJogadores.size(); i++) {
            Jogador j = this.listaJogadores.get(i);

            if (j.getNome().equals(nomeJogador)) {
                return j;
            }
        }

        throw new Exception("Player doesn't exist");
    }


    /**
     * Inicia uma nova vez (jogada);
     */
    private void iniciarNovaVez() {        
        this.terminouVez = false;
    }



    /**
     * Faz uma serie de verificacoes de integridade de inicializacao do jogo
     * @param quantidade quantidade de jogadores
     * @param nomes_jogadores nomes dos jogadores
     * @param cores_jogadores cores dos peoes dos jogadores
     * @throws Exception
     */
    private void tratarErrosIniciais(int quantidade, String[] nomes_jogadores, String[] cores_jogadores) throws Exception {
        if ((quantidade == 1) || (quantidade > 8)) {
            throw new Exception("Invalid number of players");
        }


        if (this.hasNomeInvalido(nomes_jogadores)) {
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



        if (this.hasNomeRepetido(nomes_jogadores)) {
            throw new Exception("There mustn't be repeated player names");
        }

        if (this.hasNomeRepetido(cores_jogadores)) {
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


    /**
     * Checa se uma lista de nomes tem algum nome repetido
     * @param listaNomes lista de nomes
     * @return true se ha algum nome repetido, false caso contrario
     */
    private boolean hasNomeRepetido(String[] listaNomes) {
        for (int i = 0; i < listaNomes.length; i++) {
            for (int j = 0; j < listaNomes.length; j++) {
                if ((i != j) && (listaNomes[i].equals(listaNomes[j]))) {
                    return true;
                }
            }
        }

        return false;
    }


    /**
     * Checa se a lista tem algum nome de jogador invalido
     * @param n a lista de nomes de jogadores
     * @return true se tem algum nome invalido, false caso contrario
     * @throws Exception
     */
    private boolean hasNomeInvalido(String[] n) throws Exception {
        boolean invalido = false;
        for (int i = 0; i < n.length; i++) {
            if (n[i].equals("bank")) {
                invalido = true;
            }
        }
        return invalido;
    }


    /**
     * Inicia um jogo
     */
    public void StartJogo() {
    }


    /**
     * Finaliza um jogo
     * @throws Exception
     */
    public void QuitJogo() throws Exception {
        if (status == false) {
            throw new Exception("There's no game to quit");
        }

        
    }

    /**
     * Checa se o jogo acabou (apenas um jogador restante)
     * @return true se o jogo acabou, false caso contrario
     */
    public boolean isGameFinished() {
        if (listaJogadores.size() - listaJogadoresFalidos.size() == 1) {
            return true;
        } else {
            return false;
        }

    }



    /**
     * Obtem o numero de jogadores nao-falidos
     * @return o numero de jogadores nao-falidos
     */
    public int getNumberOfPlayers() {
        return listaJogadores.size() - listaJogadoresFalidos.size();
    }

    

    /**
     * Obtem o dono de um lugar
     * @param idPlace o id do lugar
     * @return o nome do dono
     * @throws Exception
     */
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



    /**
     * Avanca a vez para a jogada de um jogador nao-falido
     */
    public void PrepareNextJogada() {
        if (vez == listaJogadores.size() - 1) {
            vez = 0;

        } else {
            vez++;
        }
    }

    

    /**
     * Obtem a vez atual
     * @return a vez
     */
    public int jogadorAtual() {

        return vez;
    }


    /**
     * Liga a compra automatica
     */
    public void setCompraAutomatica() {
        this.compra_automatica = true;
    }

    /**
     * Desliga a compra automatica
     */
    public void unsetCompraAutomatica() {
        this.compra_automatica = false;
    }



    
    /**
     * Realiza o movimento de um jogador, junto com as devidas consequencias
     * (pagar aluguel, ferrovia etc)
     * @param resultadoDado1 o resultado do dado 1
     * @param resultadoDado2 o resultado do dado 2
     * @throws Exception
     */
    public void processarJogada(int resultadoDado1, int resultadoDado2) throws Exception {

        if ((isResultadoDadoValido(resultadoDado1)) && (isResultadoDadoValido(resultadoDado2))) {

            if (!this.jogadorTerminouAVez()) {
                this.terminarAVez();
            }

            this.iniciarNovaVez();

            this.print("Jogador " + this.jogadorAtual());
            this.print("\tEstá em " + this.posicoes[this.jogadorAtual()]);
            this.print("\tvai andar " + (resultadoDado1 + resultadoDado2) + " casas.");

            this.moverJogadorDaVez(resultadoDado1, resultadoDado2);
        }


    }


    /**
     * Realiza o pagamento pelo uso de uma ferrovia
     * @param credor id do jogador a receber o pagamento
     * @param devedor id do jogador a pagar
     * @param valor valor do pagamento
     * @param NomePopriedade nome da propriedade
     */
    public void pagarFerrovia(int credor, int devedor, int valor, String NomePopriedade) {
        
        Jogador JogadorDevedor = listaJogadores.get(devedor);
        Jogador JogadorCredor = listaJogadores.get(credor);
        if ((NomePopriedade.equals("Reading Railroad")) ||
                (NomePopriedade.equals("Pennsylvania Railroad")) ||
                (NomePopriedade.equals("B & O Railroad")) ||
                (NomePopriedade.equals("Short Line Railroad"))) {
            int quantidadeFerrovias = DonosFerrovias[credor];
            int divida = quantidadeFerrovias * valor;
            this.print("Credor tem " + quantidadeFerrovias);
            this.print("Divida eh " + divida);

            if (listaJogadores.get(devedor).getDinheiro() >= divida) {
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


    /**
     * Realiza a cobranca de aluguel
     * @param credor id do jogador a receber o aluguel
     * @param devedor id do jogador a pagar o aluguel
     * @param valor o valor do aluguel
     * @param NomePopriedade o nome da propriedade
     */
    public void pagarAluguel(int credor, int devedor, int valor, String NomePopriedade) {

        Jogador JogadorDevedor = listaJogadores.get(devedor);
        Jogador JogadorCredor = listaJogadores.get(credor);

        
        if (listaJogadores.get(devedor).getDinheiro() >= valor) {
            JogadorDevedor.retirarDinheiro(valor);
            JogadorCredor.addDinheiro(valor);

        } else {
            int DinheiroRestante = listaJogadores.get(devedor).getDinheiro();
            JogadorDevedor.retirarDinheiro(DinheiroRestante);
            JogadorCredor.addDinheiro(DinheiroRestante);
            this.removePlayer(devedor);

        }

        
    }


    /**
     * Marca a vez (jogada) do jogador como terminada
     */
    public void terminarAVez(){

        if (!this.jogadorTerminouAVez()) {
            do {
                this.PrepareNextJogada();
            } while (this.listaJogadoresFalidos.contains(listaJogadores.get(vez).getNome()));

        }
        
        this.terminouVez = true;
        
    }


    /**
     * Checa se a vez (jogada) do jogador foi concluida (com uma compra, pagamento de imposto etc)
     * @return
     */
    private boolean jogadorTerminouAVez(){
        return this.terminouVez;
    }

    

    /**
     * Checa se o resultado do dado e valido
     * @param resultadoDado o resultado do dado
     * @return  true se o resultado e valido, falso caso contrario
     * @throws Exception
     */
    private boolean isResultadoDadoValido(int resultadoDado) throws Exception {
        if ((resultadoDado > 6) || (resultadoDado < 1)) {
            throw new Exception("Invalid die result");
        }
        return true;
    }


    /**
     * Seta a compra automatica para true
     */
    public void definirCompraAutomatica() {
        this.compra_automatica = true;
    }


    /**
     * Verifica se o jogador deve receber a bonificacao por passar pela casa 40
     * @param jogador
     * @param valorDados
     * @return
     */
    private boolean completouVolta(int jogador, int valorDados) {

        if ((this.posicoes[jogador] + valorDados) >= 40 && this.posicoes[jogador] != 40) {
            this.listaJogadores.get(jogador).addDinheiro(200);
            this.print("\tGanha $200 por passar pela casa 40.");
            return true;
        }
        return false;

    }


    /**
     * Move um jogador para uma posicao do tabuleiro
     * @param jogador o id do jogador
     * @param valorDados o valor dos dados
     */
    private void moverJogadorAPosicao(int jogador, int valorDados) {
        this.posicoes[jogador] = (this.posicoes[jogador] + valorDados);
        if (posicoes[jogador] > 40) {
            posicoes[jogador] = posicoes[jogador] - 40;
        }
    }


    /**
     * Tenta compra uma propriedade
     * @param jogador o jogador
     * @param lugar a propriedade a ser comprada
     * @return true se a compra foi feita, false caso contrario
     * @throws Exception
     */
    private boolean realizarCompra(int jogador, Lugar lugar) throws Exception {


        if (this.posicaoCompravel(this.posicoes[jogador])) {
            this.print("\tO lugar " + lugar.getNome() + " está à venda!");
            this.print("\tPreço:" + lugar.getPrecoCompra());
            this.print("\tAtual dinheiro:" + this.listaJogadores.get(jogador).getDinheiro());
            this.print("\tTenta realizar a compra");
            if (this.efetuarCompra(this.posicoes[jogador], this.listaJogadores.get(jogador))) {
                this.print("\tJogador compra " + lugar.getNome());
                return true;

            } else {
                this.print("\tCompra não realizada!");
                return false;
            }

        }

        return false;
    }



    /**
     * Checa se a posicao e uma ferrovia
     * @param posicao a posicao
     * @return
     */
    private boolean isPosicaoFerrovia(int posicao) {
        return (posicao == 5 || posicao == 15 || posicao == 25 || posicao == 35);
    }





    /**
     * Realiza o pagamento de um imposto
     * @param nomeImposto o nome do imposto
     * @param valorImposto o valor do imposto
     * @param jogador o id do jogador
     * @return true se foi necessario pagar o imposto, false caso contrario
     */
    private boolean pagarImposto(String nomeImposto, int valorImposto, int jogador) {
        if (Donos.get(this.posicoes[jogador]).equals(nomeImposto)) {
            this.print("\tPagando imposto " + nomeImposto);
            this.print("\tValor do imposto " + valorImposto);
            if (listaJogadores.get(jogador).getDinheiro() >= valorImposto) {
                listaJogadores.get(jogador).retirarDinheiro(valorImposto);
                dinheiroBanco = dinheiroBanco + valorImposto;
            } else {
                int DinheiroRestante = listaJogadores.get(jogador).getDinheiro();
                listaJogadores.get(jogador).retirarDinheiro(DinheiroRestante);
                dinheiroBanco = dinheiroBanco + DinheiroRestante;
                this.removePlayer(jogador);

            }
            return true;
        }
        return false;
    }




    /**
     * Paga impostos do jogador, se for necessario
     * @param jogador o id do jogador
     * @return
     */
    private boolean pagarEventuaisTaxas(int jogador) {
        boolean pagouIncomeTax = this.pagarImposto("Income Tax", 200, jogador);
        boolean pagouLuxuryTax = this.pagarImposto("Luxury Tax", 75, jogador);

        if (pagouIncomeTax || pagouLuxuryTax){
            return true;
        }
        else{
            return false;
        }
    }




    /**
     * Checa se nome informado e de um jogador
     * @param nome o nome
     * @return true se corresponde a um jogador, false caso contrario
     */
    private boolean isUmJogador(String nome) {
        for (int i = 0; i < listaJogadores.size(); i++) {
            Jogador jogador = listaJogadores.get(i);

            if (jogador.getNome().equals(nome)) {
                return true;
            }

        }

        return false;
    }






    /**
     * Move um jogador para uma posicao, procurando pagar tributos, alugueis etc.
     * Tambem compra automaticamente, se esse modo foi definido.
     * @param dado1 valor do dado 1
     * @param dado2 valor do dado 2
     * @throws Exception
     */
    private void moverJogadorDaVez(int dado1, int dado2) throws Exception {


        int valorDados = dado1 + dado2;

        int jogador = this.jogadorAtual();


        //preciso saber se o jogador vai passar pela posição 40, o que significa
        //ganhar dinheiro
        this.completouVolta(jogador, valorDados);

        //movendo à posição
        this.moverJogadorAPosicao(jogador, valorDados);
        this.print("\tAtual dinheiro antes de ver a compra:" + this.listaJogadores.get(jogador).getDinheiro());
        this.print("\tVai até a posição " + this.posicoes[jogador]);



        Lugar lugar = this.tabuleiro.get(this.posicoes[jogador] - 1);//busca em -1, pois eh um vetor

        if (this.isCompraAutomatica()){
            this.realizarCompra(jogador, lugar);
        }

            if (!this.posicaoCompravel(this.posicoes[jogador])) {
                this.print("\t" + lugar.getNome() + " não está à venda!");



                String nomeDono = (String) Donos.get(this.posicoes[jogador]);
                //não cobrar aluguel de si mesmo
                if (!nomeDono.equals(this.listaJogadores.get(this.jogadorAtual()).getNome())) {

                    if (this.isUmJogador(nomeDono)) {
                        Jogador possivelDono = this.getJogadorByName(nomeDono);

                        if (this.isPosicaoFerrovia(this.posicoes[jogador])) {
                            this.print("\tO dono eh " + possivelDono.getNome());
                            this.pagarFerrovia(possivelDono.getId(), jogador, 25, lugar.getNome());
                        } else {

                            this.print("\tO dono eh " + possivelDono.getNome());
                            int valorAluguel = this.tabuleiro.getLugarPrecoAluguel(this.posicoes[jogador]);
                            this.pagarAluguel(possivelDono.getId(), jogador, valorAluguel, lugar.getNome());

                        }

                    }
                }

            }


        this.pagarEventuaisTaxas(jogador);

        this.print("\tAtual dinheiro depois:" + this.listaJogadores.get(jogador).getDinheiro());

    }



    


    /**
     * Muda um jogador para a lista de falidos e passa suas posses para o banco
     * @param id o id do jogador
     */
    public void removePlayer(int id) {
        listaJogadoresFalidos.add(listaJogadores.get(id).getNome());
        //liberando os pertences
        String NomeFalido = listaJogadores.get(id).getNome();
        for (int i = 1; i <= Donos.size(); i++) {
            if (Donos.get(i).equals(NomeFalido)) {
                Donos.put(i, "bank");
            }

        }

    }

    /**
     * Checa se a compra automatica foi ligada
     * @return true se a compra e automatica, false caso contrario
     */
    public boolean isCompraAutomatica() {
        return this.compra_automatica;
    }


    /**
     * Realiza a compra de uma posse pelo jogador da vez
     * @return true se a compra foi efetuada, false caso contrario
     * @throws Exception
     */
    public boolean buy() throws Exception {

        int jogador = this.jogadorAtual();


        boolean posicaoCompravel = this.posicaoCompravel(posicoes[jogador]);
        boolean isEstatal = this.isPosicaoEstatal(this.posicoes[jogador]);

        if (posicaoCompravel && !isEstatal) {
            int posicaoTabuleiro = posicoes[jogador];
            int preco = this.tabuleiro.getLugarPrecoCompra(posicaoTabuleiro);
            Jogador j = listaJogadores.get(jogador);
            if (preco <= j.getDinheiro()) {
                this.print("\tPossui dinheiro para a compra!");
                j.retirarDinheiro(preco);
                this.Donos.put(posicaoTabuleiro, j.getNome());

                String nomeLugar = this.tabuleiro.getPlaceName(posicaoTabuleiro);
                j.addPropriedade(nomeLugar);
                if (nomeLugar.equals("Reading Railroad") || nomeLugar.equals("Pennsylvania Railroad") ||
                        nomeLugar.equals("B & O Railroad") || nomeLugar.equals("Short Line Railroad")) {
                    DonosFerrovias[jogador]++;
                }
                this.print("\tVocê adquiriu "+ nomeLugar + " por " + preco);
                this.print("\tAtual dinheiro: " + j.getDinheiro());
            } else {
                this.print("\tNão possui dinheiro para realizar a compra!");
                throw new Exception("Not enough money");
            }

        }
        else {

            if (this.jaTemDono(posicoes[jogador])){
                throw new Exception("Deed for this place is not for sale");
            }
            if (!posicaoCompravel){
                throw new Exception("Place doesn't have a deed to be bought");
            }
            if (isEstatal) {
                throw new Exception("Deed for this place is not for sale");
            }
            
        }

        

        return false;



    }


    /**
     * Checa se uma posicao eh uma estatal (Electric Comapny ou Water Works)
     * @param posicao
     * @return
     */
    private boolean isPosicaoEstatal(int posicao){
        return (posicao == 12) || (posicao == 28);
    }


    /**
     * Efetua a compra de uma propriedade
     * @param posicaoTabuleiro a posicao a ser feita a compra
     * @param jogador o jogador que faz a compra
     * @return true se a compra foi efetuada, false caso contrario
     * @throws Exception
     */
    public boolean efetuarCompra(int posicaoTabuleiro, Jogador jogador) throws Exception {
        if (this.posicaoCompravel(posicaoTabuleiro) && posicaoTabuleiro != 12 && posicaoTabuleiro != 28) {
            int preco = this.tabuleiro.getLugarPrecoCompra(posicaoTabuleiro);
            if (preco <= jogador.getDinheiro()) {
                this.print("\tPossui dinheiro para a compra!");
                jogador.retirarDinheiro(preco);
                this.Donos.put(posicaoTabuleiro, jogador.getNome());

                String nomeLugar = this.tabuleiro.getPlaceName(posicaoTabuleiro);
                jogador.addPropriedade(nomeLugar);
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
     * @param msg a mensagem a ser impressa no console
     */
    public void print(String msg) {
        this.print(msg, false);
    }

    /**
     * Apenas um encapsulador para o System.out.println(String)
     * @param msg a mensagem a ser impressa no console
     * @param reallyPrint habilitacao da impressao
     */
    public void print(String msg, boolean reallyPrint) {
        if (reallyPrint) {
            System.out.println(msg);
        }

    }


    /**
     * Checa se algum jogador ja e dono de uma determinada posicao
     * @param posicao a posicao
     * @return true se ja te dono, falso caso contrario
     */
    private boolean jaTemDono(int posicao) {
        String nomeDono = Donos.get(posicao).toString();
        return (this.isUmJogador(nomeDono));
        
    }
}
