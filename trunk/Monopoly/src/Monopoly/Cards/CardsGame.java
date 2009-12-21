/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Monopoly.Cards;

/**
 *
 * @author Marcus
 */
public class CardsGame {

    Card[] CardsCofresComunitarios = new Card[16];
    Card[] CardsSorteReves = new Card[15];
    

    public CardsGame(){        
        initCardsCofresComunitarios();
        initCardsSorteReves();
    }


    public static void main(String[] args) {
     
       
    }

   

    public void initCardsCofresComunitarios(){
        CardsCofresComunitarios[0] = new Card(1, "Avance para o Ponto de Partida (Go)", "Receba $200") ;
        CardsCofresComunitarios[1] = new Card(2, "Erro do banco em seu favor", "Receba $200") ;
        CardsCofresComunitarios[2] = new Card(3, "Taxa do médico", "Pague $50") ;
        CardsCofresComunitarios[3] = new Card(4, "Abertura da Grande Ópera", "Receba $50 de cada jogador pelas entradas") ;
        CardsCofresComunitarios[4] = new Card(5, "Da liquidação fora de estoque", "Receba $45") ;
        CardsCofresComunitarios[5] = new Card(6, "Saia livre da prisão, sem pagar", "Esta carta pode ser mantida até o uso ou venda.") ;
        CardsCofresComunitarios[6] = new Card(7, "Vá para a prisão", "Vá direto para a prisão – Não passe pelo ponto de partida – Não receba $200") ;
        CardsCofresComunitarios[7] = new Card(8, "Restituição do Imposto de Renda", "Receba $20") ;
        CardsCofresComunitarios[8] = new Card(9, "Aniversário do seguro de vida", "Receba $100") ;
        CardsCofresComunitarios[9] = new Card(10, "Pague o hospital", "Pague $100") ;
        CardsCofresComunitarios[10] = new Card(11, "Pague taxa da escola", "Pague $150") ;
        CardsCofresComunitarios[11] = new Card(12, "Receba por seus serviços", "Receba $25") ;
        CardsCofresComunitarios[12] = new Card(13, "Aniversário dos fundos de Natal", "Receba $100") ;
        CardsCofresComunitarios[13] = new Card(14, "Você tirou o segundo lugar no concurso de beleza", "Receba $10") ;
        CardsCofresComunitarios[14] = new Card(15, "Você herdou", "Receba $100") ;
        CardsCofresComunitarios[15] = new Card(16, "Você deve pagar os reparos da rua", "Pague $40 por cada casa, $115 por hotel") ;
       
    }

    
    public void initCardsSorteReves(){

        CardsSorteReves[0] = new Card(1, "Advance To Go - Collect $200", "Receba $200");
        CardsSorteReves[1] = new Card(2, "Advance To - Illinois Avenue", "Illinois Avenue");
        CardsSorteReves[2] = new Card(3, "Advance To St. Charles Place - If you pass Go, Collect $200", "Se passer pelo ponto de partida, receba $200");
        CardsSorteReves[3] = new Card(4, "Avance o peão para o serviço público mais próximo", "Se não tiver dono você pode comprá-lo do banco. Se tiver, lance os dados e pague ao dono 10 vezes o resultado do lançamento.");
        CardsSorteReves[4] = new Card(5, "Avance o peão para a ferrovia mais próxima", "Pague ao dono duas vezes o valor que ele deveria receber normalmente. Se a ferrovia não tiver dono, você pode comprá-la do banco.");
        CardsSorteReves[5] = new Card(6, "O banco lhe pagou dividendos de", "$50");
        CardsSorteReves[6] = new Card(7, "Volte 3 espaços", "");
        CardsSorteReves[7] = new Card(8, "Vá diretamente para a Prisão", "Não passe pelo ponto de partida, não receba $200.");
        CardsSorteReves[8] = new Card(9, "Faça a manutenção de todas as suas propriedades", "Para cada casa pague $25, para cada hotel $10.");
        CardsSorteReves[9] = new Card(10, "Pague imposto de pobreza de", "$15");
        CardsSorteReves[10] = new Card(11, "Esta carta pode ser mantida até seu uso ou venda", "Get Out Of Jail Free");
        CardsSorteReves[11] = new Card(12, "Dê uma volta na Reading", "Se você passer pelo Ponto de Partida receba $200");
        CardsSorteReves[12] = new Card(13, "Dê uma caminhada no Board Walk", "Avance o peão para Board Walk");
        CardsSorteReves[13] = new Card(14, "Você foi eleito presidente do tabuleiro", "Pague $50 para cada jogador");
        CardsSorteReves[14] = new Card(15, "Seus empréstimos e investimento fizeram aniversário", "Receba $150");

        
    }

    public void randomSorteReves(){
        
    }

    public void randomCofresComunitarios(){
        
    }


    public Card[] getSorteReves(){
        return CardsSorteReves;
    }

    public Card[] getCofresComunitarios(){
        return CardsCofresComunitarios;
    }

    public Card getCardSorteReves(int index) throws Exception{
        if(index>0 && index<=15){
            return CardsSorteReves[index-1];
        }
        else
            throw new Exception("Card doesn't exist");
        
    }

    public Card getCardCofresComunitarios(int index) throws Exception{
        if(index>0 && index<=16){
            return CardsCofresComunitarios[index-1];
        }
        else
            throw new Exception("Card doesn't exist");

    }
}
