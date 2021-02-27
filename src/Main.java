import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author ΑΜΑΛΙΑ ΓΕΩΡΓΟΥΔΗ ΑΕΜ - 3006, ΚΙΟΣΗΣ ΕΥΑΓΓΕΛΟΣ ΑΕΜ - 2489
 * Η main κλάση όπου καλείται το παιχίδι και γίνεται η διασύνδεση με τον χρήστη. Κατα την εκτέλεση εμφανίζεται ένα μενού με τις
 * επιλογές των παιχνιδιών, ο χρήστης επίλεγει το παιχνίδι που θέλει και ένα άτομα παράθυρο όπου μπορεί να ρυθμίσει τους παίκτες
 * εμφανίζεται. Όταν τους ρυθμίσει το παράθυρο κλείνει και ένα νέο με το παιχίδι ανοίξει.
 */
public class Main {
    public static void main(String[] args) {
        //Ρυθμίσεις γλώσσας
        Locale l = Locale.getDefault();
        ResourceBundle r = ResourceBundle.getBundle("Bundle",l);
        String text;
        // Μενού Επιλογών
        JFrame mainMenu = new JFrame("Memory Game"); //Το παράθυρο του μενού
        mainMenu.setSize(400,500);
        mainMenu.setResizable(false);
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(); //Το πανελ του παραθύρου
        mainPanel.setBackground(Color.darkGray);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainMenu.add(mainPanel);

        ImageIcon icon = new ImageIcon("images/logo.png"); //Εικόνα ως λογότυπο της εφαρμογής
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(iconLabel);

        text = r.getString("game");
        JLabel mainTitle = new JLabel(text); //Τίτλος του παιχνιδιού
        mainTitle.setFont(new Font("Segoe Ui", Font.PLAIN, 36)); //Ρυθμίσεις γραμματοσείρας
        mainTitle.setForeground(Color.white);
        mainTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(mainTitle);

        JLabel sideTitle = new JLabel("© 2489-3006"); //Υπότιτλος του παιχνιδιού
        sideTitle.setFont(new Font("Segoe Ui", Font.PLAIN, 12)); //Ρυθμίσεις γραμματοσείρας
        sideTitle.setForeground(Color.gray);
        sideTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(sideTitle);

        JPanel gameOptions = new JPanel(); // Υπο-πάνελ για τις επιλογές(κουμπιά) του παιχνιδιού.
        gameOptions.setBackground(Color.darkGray);
        gameOptions.setBorder(BorderFactory.createEmptyBorder(40,0,0,0));
        gameOptions.setLayout(new BoxLayout(gameOptions, BoxLayout.PAGE_AXIS));
        gameOptions.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(gameOptions);

        //Επιλογή Βασικού παιχνιδιου-Κουμπί1
        text = r.getString("optionA");
        final JButton optionA = new JButton(text);
        optionA.setFont(new Font("Segoe Ui", Font.PLAIN, 18));
        optionA.setForeground(Color.white);
        optionA.setBorder(BorderFactory.createEmptyBorder(5,0,0,0));
        optionA.setFocusPainted(false);
        optionA.setContentAreaFilled(false);
        optionA.setBorderPainted(false);
        optionA.setOpaque(false);
        gameOptions.add(optionA);

        optionA.addMouseListener(new java.awt.event.MouseAdapter() { //Αλλαγή χρώματος όταν το ποντίκι είναι από πάνω του
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                optionA.setForeground(Color.gray);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                optionA.setForeground(Color.white);
            }
        });

        optionA.addActionListener(new ActionListener() {
            @Override
            //Δημιουργείται ένα αντικείμενο της κλάσης PlayerOption με τα κατάλληλα ορίσματα όπου αυτά θα περαστούν στην κλάση MemoryGameLogic
            public void actionPerformed(ActionEvent e) {
                PlayersOption game = new PlayersOption(24, 2, 6,r);
            }
        });

        //Επιλογή Διπλάσιου παιχνιδιού-Κουμπί2
        text = r.getString("optionB");
        final JButton optionB = new JButton(text);
        optionB.setFont(new Font("Segoe Ui", Font.PLAIN, 18));
        optionB.setForeground(Color.white);
        optionB.setBorder(BorderFactory.createEmptyBorder(5,0,0,0));
        optionB.setFocusPainted(false);
        optionB.setContentAreaFilled(false);
        optionB.setBorderPainted(false);
        optionB.setOpaque(false);
        gameOptions.add(optionB);

        optionB.addMouseListener(new java.awt.event.MouseAdapter() { //Αλλαγή χρώματος όταν το ποντίκι είναι από πάνω του
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                optionB.setForeground(Color.gray);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                optionB.setForeground(Color.white);
            }
        });

        optionB.addActionListener(new ActionListener() {
            @Override
            //Δημιουργείται ένα αντικείμενο της κλάσης PlayerOption με τα κατάλληλα ορίσματα όπου αυτά θα περαστούν στην κλάση MemoryGameLogic
            public void actionPerformed(ActionEvent e) {
                PlayersOption game = new PlayersOption(48, 2, 8,r);
            }
        });

        //Επιλογή Τριπλού παιχνιδιού -Κουμπί3
        text = r.getString("optionC");
        final JButton optionC = new JButton(text);
        optionC.setFont(new Font("Segoe Ui", Font.PLAIN, 18));
        optionC.setForeground(Color.white);
        optionC.setBorder(BorderFactory.createEmptyBorder(5,0,0,0));
        optionC.setFocusPainted(false);
        optionC.setContentAreaFilled(false);
        optionC.setBorderPainted(false);
        optionC.setOpaque(false);
        gameOptions.add(optionC);

        optionC.addMouseListener(new java.awt.event.MouseAdapter() { //Αλλαγή χρώματος όταν το ποντίκι είναι από πάνω του
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                optionC.setForeground(Color.gray);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                optionC.setForeground(Color.white);
            }
        });

        optionC.addActionListener(new ActionListener() { //Κατά την επιλογή
            @Override
            //Δημιουργείται ένα αντικείμενο της κλάσης PlayerOption με τα κατάλληλα ορίσματα όπου αυτά θα περαστούν στην κλάση MemoryGameLogic
            public void actionPerformed(ActionEvent e) {
                PlayersOption game = new PlayersOption(36, 3, 6,r);
            }
        });

        //Επιλογή Μονομαχία-Κουμπί4
        text = r.getString("optionD");
        final JButton optionD = new JButton(text);
        optionD.setFont(new Font("Segoe Ui", Font.PLAIN, 18));
        optionD.setForeground(Color.white);
        optionD.setBorder(BorderFactory.createEmptyBorder(5,0,0,0));
        optionD.setFocusPainted(false);
        optionD.setContentAreaFilled(false);
        optionD.setBorderPainted(false);
        optionD.setOpaque(false);
        gameOptions.add(optionD);

        optionD.addMouseListener(new java.awt.event.MouseAdapter() { //Αλλαγή χρώματος όταν το ποντίκι είναι από πάνω του
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                optionD.setForeground(Color.gray);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                optionD.setForeground(Color.white);
            }
        });

        optionD.addActionListener(new ActionListener() {
            @Override
            //Δημιουργείται ένα αντικείμενο της κλάσης Battle για την μονομαχία
            public void actionPerformed(ActionEvent e) {
                Battle battle = new Battle(r);
            }
        });

        JPanel scoreBar = new JPanel();
        scoreBar.setBackground(Color.darkGray);
        scoreBar.setBorder(BorderFactory.createEmptyBorder(60,0,0,0));
        scoreBar.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(scoreBar);

        //Κουμπί εμφάνισης των σκορ
        text = r.getString("score");
        final JButton scoreLabel = new JButton(text);
        scoreLabel.setFont(new Font("Segoe Ui", Font.PLAIN, 14));
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFocusPainted(false);
        scoreLabel.setMargin(new Insets(0, 0, 0, 0));
        scoreLabel.setContentAreaFilled(false);
        scoreLabel.setBorderPainted(false);
        scoreLabel.setOpaque(false);
        scoreBar.add(scoreLabel);

        scoreLabel.addMouseListener(new java.awt.event.MouseAdapter() { //Αλλαγή χρώματος όταν το ποντίκι είναι από πάνω του
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                scoreLabel.setForeground(Color.gray);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                scoreLabel.setForeground(Color.white);
            }
        });

        scoreLabel.addActionListener(new ActionListener() {
            @Override
            //Δημιουργείται ένα αντικείμενο της κλάσης ScorePage
            public void actionPerformed(ActionEvent e) {
                ScorePage scoreP = new ScorePage(r);
            }
        });


        //Εμφάνιση παράθυρου μενού
        mainMenu.setVisible(true);

    }

}
