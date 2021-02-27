import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * @author  ΑΜΑΛΙΑ ΓΕΩΡΓΟΥΔΗ ΑΕΜ - 3006 , ΚΙΟΣΗΣ ΕΥΑΓΓΕΛΟΣ ΑΕΜ - 2489
 * Η κλάση αυτή είναι η λογική του παιχνιδιού,για τις 3 πρώτες επιλογές.
 * Όλο το παιχνίδι δημιουργείται και ελέγχεται εδω. Η λογική λειτουργεί ως εξης.
 * Υπάρχει ένα αντικείμενο κλάσης των καρτων(ενας πίνακας με κωδικούς),ενας πίνακας με εικόνες και ένας πίνακας με κουμπιά.
 * Κάθε θέση από κάθε πίνακα αναφέρεται στην ιδια κάρτα, πχ. η 3η καρτα ειναι το κουμπί στην θέση 3, έχει εικόνα στην θεση 3 του πίνακα εικόνων
 * και κωδικο στην θεση 3 του πίνακα κωδικών.
 */

public class MemoryGameLogic {
    private Cards cards; //Αντικείμενο κλάσης cards, όπου το χρησιμοποιούμε για τους κωδικούς
    private int amountofcards; //Πλήθος καρτών.
    private int samecards; //πλήθος αντιγράφων.
    // Με σειρά που ορίζονται: Ανοιχτή κάρτα1,καρτα2,καρτα3,συνολίκό σκορ,κινήσεις-γύρος,αριθμός από στήλες για ρύθμιση του layout, βοηθητική μετ.για ανοιχτή καρτα1,2,3
    private int c1=-1,c2=-1,c3=-1,score,round,colums,h1,h2,h3;
    private Players players; //αντικείμενο κλάσεις players
    private JFrame table; //Το παράθυρο
    private GridLayout layout; //Το layout των καρτών
    private JButton cButtons[]; //Τα κουμπιά που αντιστοιχίζονται στις κάρτες
    private JPanel scorepanel,tablepanel; //Πανελ για το σκορ/κινήσεις , πανελ για τις κάρτες.
    private JLabel scoreLabel; //Μήνυμα σκόρ
    private ImageIcon backIcon = new ImageIcon("images/back.png"); //Εικόνα για την πίσω όψη των καρτών.
    private ImageIcon[] keyIcon; //Πίνακας που περιέχει τις εικόνες για κάθε διαφορετική κάρτα.
    private Timer t,t1; // Για παύση κατα την εξαφάνιση ή κλείσιμο καρτών.
    private ResourceBundle res;
    private String text;


    /**
     * Κατασκευαστής κλάσης, αρχικοποίηση μεταβλητών.
     * @param a πληθος καρτων
     * @param s πληθος αντιγράφων
     * @param n αριθμός στηλών για διάταξη των καρτών.
     * @param p πλήθος παικτών.
     */
    public MemoryGameLogic(int a, int s, int n, int p, ResourceBundle r1){
        res=r1;
        amountofcards = a;
        samecards = s;
        colums =n;
        cards = new Cards(amountofcards,samecards);
        keyIcon = new ImageIcon[amountofcards/samecards];
        cButtons = new JButton[amountofcards];
        scoreLabel = new JLabel();
        players = new Players(p);
        AddKeys(); //Φόρτωση εικόνων.
        ShowTable(); //Εμφάνιση καρτών.

    }

    /**
     * Φορτώνει τις εικόνες με όνομα keyX για την θέση Χ του πίνακα.
     */
    void AddKeys(){
        for(int i=0;i<keyIcon.length;i++){
            keyIcon[i] = new ImageIcon("images/key"+i+".png");
        }
    }

    /**
     * Εμφανίζει το ταμπλό των καρτών.
     */
    void ShowTable(){
        //Ελεγχος παικτών για διαμόρφωση μηνύματος σκόρ
        if(players.amoundPlayers()==1){
            text = res.getString("game1");
            scoreLabel.setText(text+" 0");
        }else{
            text = res.getString("game2");
            scoreLabel.setText(text+" 1");
        }
        //Ρυθμίσεις εμφανισεις.
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(new Font("Segoe Ui", Font.PLAIN, 18));
        scorepanel = new JPanel();
        scorepanel.setBackground(Color.darkGray);
        scorepanel.setBorder(BorderFactory.createEmptyBorder(5,0,0,0));
        scorepanel.add(scoreLabel, BorderLayout.CENTER);

        if(samecards==2 && colums==6){ //Ελεγχος για διαφόρφωση αν είμαστε στο βασικό παιχνίδι.
            table = new JFrame("Basic Game");
            table.setSize(800,500);
            layout = new GridLayout(4,6,8,8);
        }else if(samecards==2 && colums==8){ //Ελεγχος για διαφόρφωση αν είμαστε στο διπλάσιο παιχνίδι.
            table = new JFrame("Double Game");
            table.setSize(1000,700);
            layout = new GridLayout(6,8,8,8);
        }else{
            table = new JFrame("Triple Game"); //Ελεγχος για διαφόρφωση αν είμαστε στο τριπλό παιχνίδι.
            table.setSize(1000,700);
            layout = new GridLayout(6,6,8,8);
        }

        tablepanel = new JPanel();
        tablepanel.setBackground(Color.darkGray);
        tablepanel.setLayout(layout);
        tablepanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        for(int i=0;i<amountofcards;i++){ //Ρυθμίσεις καρτών
            cButtons[i] = new JButton(); //Δημιουργία κουμπιού
            cButtons[i].setIcon(backIcon); //Εμφάνιση πίσω όψης
            cButtons[i].setContentAreaFilled(false);
            cButtons[i].setBorderPainted(false);
            final int n = i;
            cButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(cButtons[n].getIcon()!=keyIcon[cards.getCard(n)]){ //Αν οταν το επιλέξουμε δεν είναι ήδη ανοικτό.
                        Play(n); //Νέο "βήμα"
                    }
                }
            });
            tablepanel.add(cButtons[i]); //Προσθήκη το πάνελ.
        }
        //ρυθμίσεις παραθύρου
        table.setResizable(false);
        table.setLocationRelativeTo(null);
        table.add(scorepanel, BorderLayout.PAGE_START);
        table.add(tablepanel, BorderLayout.CENTER);
        table.setVisible(true);


    }

    /**
     *
     * @param open το κουμπί/κάρτα που έχουμε επιλέγει για να ανοίξει.
     */
    void Play(int open) {
        //Ορισμός των Timer
        t = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                closeCards();
            }
        });
        t1 = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                hideCards();
            }
        });
        round++; //Νέος γύρος/κίνηση
        if(c1==-1){ //Αν δεν είναι ανοιχτή καμία κάρτα, ανοίξε την και αποθήκευσέ την ως η πρώτη.
            c1=open;
            h1=c1;
            openCard(c1);
        }else if(c2==-1){ //Αλλιώς ανοιξέ την, αποθήκευσέ της ως δεύτερη
            c2=open;
            openCard(c2);
            h2=c2;
            if(samecards==2){ //Αν τα αντίγραφα ειναι 2.//Αποθήκευσε τις βοηθητικές
                if (Match(c1, c2)) { //Ελεγχος αν ειναι ζευγάρη
                    players.addwin(); //Προσθήκη νίκης.
                    score++; //Αυξηση σκορ
                    t1.start(); //Ξεκίνα τον πρωτο τίμερ που εξαφανίζει τις κάρτες.
                } else {
                    players.nextplayer(); //Επόμενος παίκτης
                    t.start();//Ξεκίνα τον τίμερ που κλείνει τις κάρτες.
                }
                c1 = -1; //Αρχικοποίηση μεταβλητών.
                c2 = -1;
            }
        }else { //Αποθήκευσε και ανοιξε την 3η κάρτα
            c3=open;
            openCard(c3);
            h3=c3;
            if (Match(c1, c2) && Match(c1, c3)) { //ίδια διαδικασία με παραπάνω
                players.addwin();
                score++;
                t1.start();
            } else {
                players.nextplayer();
                t.start();
            }
            c1 = -1; //Αρχικοποίηση μεταβλητών.
            c2 = -1;
            c3 = -1;
        }
        Ends(); //Κλήση συνάρτησης για έλεγχο τερμαρισμού
        if(players.amoundPlayers()==1){ //Ανανέωση μηνύματος
            text = res.getString("game1");
            scoreLabel.setText(text +round);
        }else{
            text = res.getString("game2");
            scoreLabel.setText(text+ " " +players.getplayer());
        }
    }

    /**
     * Ανοιξει την κάρτα που δέχεται, δηλαδή αλλάζει την εικόνα που βλέπει ο χρήστης.
     * @param id η κάρτα
     */
    void openCard(int id){
        cButtons[id].setIcon(keyIcon[cards.getCard(id)]);
    }

    /**
     * Κλείνει τις καρτες που είναι ανοικτές, αλλάζει την εικόνα σε πίσω όψη.
     */
    void closeCards(){
        cButtons[h1].setIcon(backIcon);
        cButtons[h2].setIcon(backIcon);
        if(samecards==3) {
            cButtons[h3].setIcon(backIcon);
        }
        t.stop();
    }

    /**
     * Εξαφανίζει τις κάρτες που είναι ανοικτές.
     */
    void hideCards(){
        cButtons[h1].setVisible(false);
        cButtons[h2].setVisible(false);
        if(samecards==3) {
            cButtons[h3].setVisible(false);
        }
        t1.stop();
    }

    /**
     *
     * @param c1 Καρτα1
     * @param c2 Καρτα2
     * @return Αληθείς αν ειναι ιδιες, αλλιως ψευδής
     */

    boolean  Match(int c1, int c2)  {
        if (cards.getCard(c2)==cards.getCard(c1)){ //Σύγκριση κωδικών καρτών
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Ελέγχει αν το σκορ ειναι ίδιο με το πλήθος των καρτων, αν ειναι τότε τερματίζεται το παιχνίδι και ορίζει ενα αντικείμενο της κλάσης EndAction ώστε να εμφανίσει
     * το παράθυρο τέλους.
     */
    void Ends(){
        if(score==amountofcards/samecards) {
            int results,winner;
            if(players.getplayer()==1){
                results = round;
                winner =1;
            }else{
                results = players.BestScore();
                winner = players.winner();
            }
            table.dispose();
            EndAction end = new EndAction(players.amoundPlayers(),winner,results,res);
        }
    }

}