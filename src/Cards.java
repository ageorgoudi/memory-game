import java.util.Random;

/**
 * @author  ΑΜΑΛΙΑ ΓΕΩΡΓΟΥΔΗ ΑΕΜ - 3006 , ΚΙΟΣΗΣ ΕΥΑΓΓΕΛΟΣ ΑΕΜ - 2489
 * Αυτή η κλάση δημιουργεί και διαχειρίζεται τους κωδικούς των καρτών.
 */

public class Cards {
    private int cards[]; // Πίνακας που περιέχει τους κωδικούς (id) των καρτών. id από 0 - πλήθος καρτων.
    private int amountofcards,samecards;

    public Cards(int amount,int same){
        amountofcards = amount;
        samecards = same;
        cards = new int[amountofcards];
        MakeRandomCodes();
    }

    /**
     *
     * @param i αριθμός κάρτας (θεση)
     * @return τον κωδικό της κάρτας
     */
    int getCard(int i){
        return cards[i];
    }


    /**
     * Αυτή η συνάρτηση δημιουργεί εναν πίνανα ίσο με το πλήθος των καρτών και έχει τιμές τους κωδικούς των καρτών.
     * Καθε ζευγάρι έχει τον ίδιο κωδικό. Ως κωδικοί ορίζονται αριθμοί απο το 0 εως το πλήθος καρτών-1. Επομένως στο τέλος της συνάρτησης οι τιμές που θα υπάρχουν στον πίνακα
     * θα ειναι ανακατεμένοι αριθμοί από το 0 εως το πληθος_καρτών-1 σε πλήθος ομοίων οσο και ο αριθμός των αντιγράφων.
     */
    public void MakeRandomCodes(){
        int r[] = new int[amountofcards];
        Random randomnumber = new Random();
        int n = randomnumber.nextInt(amountofcards/samecards); //Τυχαίος αριθμος απο 0 - πλήθος καρτών.
        r[0]=n;
        int i=1;
        while(i<amountofcards) {
            n = randomnumber.nextInt(amountofcards / samecards);
            int p = 0;
            for (int y = 0; y < i; y++) {
                if (r[y] == n) {
                    p++;
                }
            }
            if (p > samecards -1) { //Ο κωδικός δεν πρέπει να υπάρχει στον πίνακα πάνω από τις φορές των ζευγαριών.
                n = randomnumber.nextInt(amountofcards / samecards);
            }
            else {
                r[i] = n;
                i++;
            }
        }
        for (int y=0;y<amountofcards;y++){ //Αντιγραφή τιμών απο βοηθητικό πίνακα στον πίνακα cards.
            cards[y]=r[y];
        }
    }

}