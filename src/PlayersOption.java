import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

/**
 * @author  ΑΜΑΛΙΑ ΓΕΩΡΓΟΥΔΗ ΑΕΜ - 3006 , ΚΙΟΣΗΣ ΕΥΑΓΓΕΛΟΣ ΑΕΜ - 2489
 * Αυτή η κλάση διαχειρίζεται την επιλογή των παικτών
 */
public class PlayersOption {
    private JFrame frame; //Παράθυρο
    private JPanel panel,optionpanel; //Βασικα πάνελ
    private JLabel title, label1,label2;
    private JButton b1,b2,b3,b4,b; //Κουμπια επιλογών
    private int players; //Τιμή για πλήθος παικτών
    private ResourceBundle res;
    private String text;

    /**
     * ΚΑΤΑΣΚΕΥΑΣΤΗΣ ΚΛΑΣΗΣ. Εδώ δημιουργείται το παράθυρο που μπορέι ο χρήστης να επιλέξει τους παίκτες που θέλει.
     * @param amountofcards πλήθος καρτών
     * @param samecards πλήθος αντιγράφων
     * @param columns αριθμός στηλών
     */

    public PlayersOption(int amountofcards, int samecards, int columns, ResourceBundle r1){
        res=r1;
        players=0;
        frame= new JFrame("Select Players"); //Το παράθυρο
        frame.setSize(500,400);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        panel = new JPanel(); //Πανελ
        panel.setBackground(Color.darkGray);
        panel.setBorder(BorderFactory.createEmptyBorder(50,20,0,20));
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        text = res.getString("Splayers");
        title = new JLabel(text);
        title.setFont(new Font("Segoe Ui", Font.PLAIN, 36)); //Ρυθμίσεις γραμματοσείρας
        title.setForeground(Color.white);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);

        text = res.getString("Nplayers");
        label1 = new JLabel(text);
        label1.setFont(new Font("Segoe Ui", Font.PLAIN, 16)); //Ρυθμίσεις γραμματοσείρας
        label1.setForeground(Color.white);
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        label1.setBorder(BorderFactory.createEmptyBorder(15,0,5,0));
        panel.add(label1);

        optionpanel = new JPanel(); //Υπο-πανελ επιλογών
        optionpanel.setBackground(Color.darkGray);
        optionpanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        optionpanel.setLayout(new BoxLayout(optionpanel, BoxLayout.X_AXIS));

        //1 παίκτης
        text = res.getString("p1");
        b1 = new JButton(text);
        b1.setBackground(Color.gray);
        b1.setForeground(Color.white);
        b1.setFocusPainted(false);
        b1.setBorderPainted(false);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Για λόγους αισθητικής, αλλάζουν τα χρώματα του επιλεγμένου κουμπιού,
                // αν είχε πατηθεί ήδη κάποιο τότε τα χρώματα επιστρέφουν στην αρχική μορφή.
                b1.setBackground(Color.white);
                b1.setForeground(Color.darkGray);
                b2.setBackground(Color.gray);
                b2.setForeground(Color.white);
                b3.setBackground(Color.gray);
                b3.setForeground(Color.white);
                b4.setBackground(Color.gray);
                b4.setForeground(Color.white);
                players =1; //Απόδοση τιμής
            }
        });
        optionpanel.add(b1);

        //2 παίκτες
        text = res.getString("p2");
        b2 = new JButton(text);
        b2.setBackground(Color.gray);
        b2.setForeground(Color.white);
        b2.setFocusPainted(false);
        b2.setBorderPainted(false);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Για λόγους αισθητικής, αλλάζουν τα χρώματα του επιλεγμένου κουμπιού,
                // αν είχε πατηθεί ήδη κάποιο τότε τα χρώματα επιστρέφουν στην αρχική μορφή.
                b2.setBackground(Color.white);
                b2.setForeground(Color.darkGray);
                b1.setBackground(Color.gray);
                b1.setForeground(Color.white);
                b3.setBackground(Color.gray);
                b3.setForeground(Color.white);
                b4.setBackground(Color.gray);
                b4.setForeground(Color.white);
                players =2;//Απόδοση τιμής
            }
        });
        optionpanel.add(b2);

        //3 παίκτες
        text = res.getString("p3");
        b3 = new JButton(text);
        b3.setBackground(Color.gray);
        b3.setForeground(Color.white);
        b3.setFocusPainted(false);
        b3.setBorderPainted(false);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Για λόγους αισθητικής, αλλάζουν τα χρώματα του επιλεγμένου κουμπιού,
                // αν είχε πατηθεί ήδη κάποιο τότε τα χρώματα επιστρέφουν στην αρχική μορφή.
                b3.setBackground(Color.white);
                b3.setForeground(Color.darkGray);
                b2.setBackground(Color.gray);
                b2.setForeground(Color.white);
                b1.setBackground(Color.gray);
                b1.setForeground(Color.white);
                b4.setBackground(Color.gray);
                b4.setForeground(Color.white);
                players =3;//Απόδοση τιμής
            }
        });
        optionpanel.add(b3);

        //4 παίκτες
        text = res.getString("p4");
        b4 = new JButton(text);
        b4.setBackground(Color.gray);
        b4.setForeground(Color.white);
        b4.setFocusPainted(false);
        b4.setBorderPainted(false);
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Για λόγους αισθητικής, αλλάζουν τα χρώματα του επιλεγμένου κουμπιού,
                // αν είχε πατηθεί ήδη κάποιο τότε τα χρώματα επιστρέφουν στην αρχική μορφή.
                b4.setBackground(Color.white);
                b4.setForeground(Color.darkGray);
                b2.setBackground(Color.gray);
                b2.setForeground(Color.white);
                b3.setBackground(Color.gray);
                b3.setForeground(Color.white);
                b1.setBackground(Color.gray);
                b1.setForeground(Color.white);
                players =4;//Απόδοση τιμής
            }
        });
        optionpanel.add(b4);

        panel.add(optionpanel);

        //Κουμπί έναρξης παιχνιδιού.
        text = res.getString("ok");
        b = new JButton(text);
        b.setBackground(Color.white);
        b.setForeground(Color.darkGray);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(players!=0) { //Πρέπει να έχει πατηθεί κάποιο κουμπί αρχικά
                    frame.dispose();
                    //Δημιουργείται ένα αντικείμενο της κλάσης MemoryGameLogic με τα κατάλληλα ορίσματα
                    MemoryGameLogic game = new MemoryGameLogic(amountofcards, samecards, columns, players,res);
                }
            }
        });
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(b);

        text = res.getString("PCplayers");
        label2 = new JLabel(text);
        label2.setFont(new Font("Segoe Ui", Font.PLAIN, 12)); //Ρυθμίσεις γραμματοσείρας
        label2.setForeground(Color.gray);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
        panel.add(label2);

        frame.add(panel);
        frame.setVisible(true);
    }
}
