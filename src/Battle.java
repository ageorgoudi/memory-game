import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 * @author  ΑΜΑΛΙΑ ΓΕΩΡΓΟΥΔΗ ΑΕΜ - 3006 , ΚΙΟΣΗΣ ΕΥΑΓΓΕΛΟΣ ΑΕΜ - 2489
 * Αυτή η κλάση διαχειρίζεται την μονομαχία
 */
public class Battle {
    //Είναι παρόμοια διαδικασία με την κλάση memorgamelogic με κάποιες συμαντικές διαφορές.
    private Cards cards1,cards2; //κωδικοί καρτών πλέγματος 1, καρτών πλέγματος 2
    private int c1,c2,score=0,h1,h2; //Ανοιχτη κάρτα 1,2, συνολικό σκόρ, βοηθητικές μεταβλητες.
    private Players players;
    private ImageIcon backIcon = new ImageIcon("images/back.png"); //πίσω όψη
    private ImageIcon[] keyIcon; //Πίνακας που περιέχει τις εικόνες για κάθε διαφορετική κάρτα.
    private JFrame table; //Παραθυρο
    private GridLayout layout;
    private JButton cButtons1[],cButtons2[]; //Κουμπια πλέγματος 1, πλέγματος 2
    private JPanel scorepanel,tablepanel1,tablepanel2,tablepanel;//πανελ σκορ, βασικο πανελ, υποπανελ1 για 1η πλευρα, για 2η πλευρά
    private JLabel scoreLabel;
    private Timer t,t1; //τιμερ για παυσεις
    private ResourceBundle res;
    private String text;


    /**
     * ΚΑΤΑΣΚΕΥΑΣΤΗΣ ΚΛΑΣΗΣ
     */
    public Battle(ResourceBundle r){
        res=r;
        cards1 = new Cards(24,1);
        cards2 = new Cards(24,1);
        keyIcon = new ImageIcon[24];
        players = new Players(2);
        cButtons1 = new JButton[24];
        cButtons2 = new JButton[24];
        scoreLabel = new JLabel();
        AddKeys(); //Φορτωση εικόνων
        ShowTable(); //Εμφανιση καρτων
        c1=-1;
        c2=-1;
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
        //Ελεγχος παικτών για διαμόρφωση μηνύματος και ρυθμίσεις εμφανισεις.
        text = res.getString("game2");
        scoreLabel.setText(text);
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(new Font("Segoe Ui", Font.PLAIN, 18));
        scorepanel = new JPanel();
        scorepanel.setBackground(Color.darkGray);
        scorepanel.setBorder(BorderFactory.createEmptyBorder(5,0,0,0));
        scorepanel.add(scoreLabel, BorderLayout.CENTER);
        table = new JFrame("Battle");
        table.setSize(1000,700);
        layout = new GridLayout(6,4,8,8);

        tablepanel = new JPanel();
        tablepanel.setBackground(Color.darkGray);
        tablepanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        tablepanel.setLayout(new GridLayout(1,2,50,0));

        tablepanel1 = new JPanel();
        tablepanel1.setBackground(Color.gray);
        tablepanel1.setLayout(layout);
        tablepanel1.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        tablepanel2 = new JPanel();
        tablepanel2.setBackground(Color.darkGray);
        tablepanel2.setLayout(layout);
        tablepanel2.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        for(int i=0;i<24;i++){ //Ρυθμίσεις καρτών
            cButtons1[i] = new JButton(); //Δημιουργία κουμπιού
            cButtons1[i].setIcon(backIcon); //Εμφάνιση πίσω όψης
            cButtons1[i].setContentAreaFilled(false);
            cButtons1[i].setBorderPainted(false);
            cButtons1[i].setSize(50,50);
            final int n = i;
            cButtons1[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(cButtons1[n].getIcon()!=keyIcon[cards1.getCard(n)] && players.getplayer()==1){
                        Play(n);  //Νέο "βήμα"
                    }
                }
            });
            tablepanel1.add(cButtons1[i]); //Προσθήκη το πάνελ.
        }

        for(int i=0;i<24;i++){ //Ρυθμίσεις καρτών
            cButtons2[i] = new JButton(); //Δημιουργία κουμπιού
            cButtons2[i].setIcon(backIcon); //Εμφάνιση πίσω όψης
            cButtons2[i].setContentAreaFilled(false);
            cButtons2[i].setBorderPainted(false);
            final int n = i;
            cButtons2[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(cButtons2[n].getIcon()!=keyIcon[cards2.getCard(n)] && players.getplayer()==2){
                        Play(n); //Νέο "βήμα"
                    }
                }
            });
            tablepanel2.add(cButtons2[i]); //Προσθήκη το πάνελ.
        }

        //ρυθμίσεις παραθύρου
        tablepanel.add(tablepanel1);
        tablepanel.add(tablepanel2);
        table.setResizable(false);
        table.setLocationRelativeTo(null);
        table.add(scorepanel, BorderLayout.PAGE_START);
        table.add(tablepanel);
        table.setVisible(true);
    }


    /**
     *
     * @param open το κουμπί/κάρτα που έχουμε επιλέγει για να ανοίξει.
     */
    void Play(int open){
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
        if(players.getplayer()==1){
            c1=open;
            h1=c1;
            openCard(c1); //Ανοιξε την1 1η κάρτα
        }else{
            c2=open;
            h2=c2;
            openCard(c2); //Ανοιξε την 2η καρτα
        }
        if(c1!=-1 && c2!=-1){ //Αν εχουν ανοιχτεί και οι 2 κάρτες
            if(Match(c1,c2)){ //Ελεγχος αν ειναι ίδιες.
                players.addwin(); //Απόδοση νίκης
                score++; //αύξηση σκορ
                t1.start(); //Ξεκίνα τον πρωτο τίμερ που εξαφανίζει τις κάρτες.
            }else {
                t.start(); //Ξεκίνα τον τίμερ που κλείνει τις κάρτες.
            }
            c2=-1;
            c1=-1;
        }else{
            nextPlayer(); //Επόμενος παίκτης
        }
        Ends(); //Κλήση συνάρτησης για έλεγχο τερμαρισμού
    }

    /**
     * Αλλάζει παίκτη και αλλάζει τα χρώματα απο το πάνελ του ενεργού παικτη
     */
    void nextPlayer(){
        players.nextplayer();
        scoreLabel.setText(text+players.getplayer());
        if(players.getplayer()==1){
            tablepanel1.setBackground(Color.gray);
            tablepanel2.setBackground(Color.darkGray);
        }else{
            tablepanel2.setBackground(Color.gray);
            tablepanel1.setBackground(Color.darkGray);
        }
    }

    /**
     *
     * @param c1 Καρτα1
     * @param c2 Καρτα2
     * @return Αληθείς αν ειναι ιδιες, αλλιως ψευδής
     */
    boolean  Match(int c1, int c2)  {
        if (cards2.getCard(c2)==cards1.getCard(c1)){ //Σύγκριση κωδικών καρτών
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Ελέγχει αν το σκορ ειναι ίδιο με το πλήθος των καρτων, αν ειναι τότε τερματίζεται το παιχνίδι
     * και ορίζει ενα αντικείμενο της κλάσης EndAction ώστε να εμφανίσει
     * το παράθυρο τέλους.
     */
    void Ends() {
        if (score == 24) {
            int results = players.BestScore();
            int winner = players.winner();
            table.dispose();
            EndAction end = new EndAction(players.amoundPlayers(), winner, results,res);
        }
    }
    /**
     * Ανοιξει την κάρτα που δέχεται, δηλαδή αλλάζει την εικόνα που βλέπει ο χρήστης.
     * @param id η κάρτα
     */
    void openCard(int id){
        if(players.getplayer()==1) {
            cButtons1[id].setIcon(keyIcon[cards1.getCard(id)]);
        }else{
            cButtons2[id].setIcon(keyIcon[cards2.getCard(id)]);
        }
    }

    /**
     * Κλείνει τις καρτες που είναι ανοικτές, αλλάζει την εικόνα σε πίσω όψη.
     */
    void closeCards(){
        cButtons2[h2].setIcon(backIcon);
        cButtons1[h1].setIcon(backIcon);
        t.stop();
    }
    /**
     * Εξαφανίζει τις κάρτες που είναι ανοικτές.
     */
    void hideCards(){
        cButtons1[h1].setVisible(false);
        cButtons2[h2].setVisible(false);
        t1.stop();
    }
}