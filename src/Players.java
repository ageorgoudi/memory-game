/**
 * @author  ΑΜΑΛΙΑ ΓΕΩΡΓΟΥΔΗ ΑΕΜ - 3006 , ΚΙΟΣΗΣ ΕΥΑΓΓΕΛΟΣ ΑΕΜ - 2489
 * Αυτη η κλάση διαχειρίζεται τους παίκτες.
 */
public class Players {

    private int playersScore[]; //Καθε θεση έχει και το σκορ του κάθε παιχτη, η πρωτη θεση για τον πρώτο κ.ο.κ.
    private int flag =0; //Ποιος παικτης ειναι ενεργός.
    private int winner=0; //Νικητής
    private int max =0; //Μεγιστο σκόρ

    /**
     *
     * @param n πληθος παικτων.
     */
    public Players(int n){
        playersScore = new int[n];
    }

    /**
     * προσθήκη ενος πόντου.
     */
    public void addwin(){
        playersScore[flag]++;
    }

    /**
     * Αλλαγή παίκτη στον επόμενο.
     */
    public void nextplayer(){
        if(flag==playersScore.length-1){
            flag=0;
        }else{
            flag++;
        }
    }

    /**
     *
     * @return των ενεργό παίκτη
     */
    int getplayer(){
        return flag+1;
    }

    /**
     * @return το μέγιστο σκορ
     */
    int BestScore(){
        max=playersScore[0];
        for (int i=1;i<playersScore.length;i++){
            if (max<playersScore[i]){
                max = playersScore[i];
                winner =i;
            }
        }
        return max;
    }

    /**
     *
     * @return τον παίκτη με το μέγιστο σκόρ
     */
    int winner(){
        int tmp=0;
        for(int i =0;i<playersScore.length;i++){
            if (playersScore[i]==max){
                tmp++;
            }
        }
        if(tmp==1){
            return winner+1;
        }
        else{
            return -1;
        }
    }

    /**
     *
     * @return πλήθος παικτών
     */
    int amoundPlayers(){
        return playersScore.length;
    }

}
