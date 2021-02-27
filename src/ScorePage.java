import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author  ΑΜΑΛΙΑ ΓΕΩΡΓΟΥΔΗ ΑΕΜ - 3006 , ΚΙΟΣΗΣ ΕΥΑΓΓΕΛΟΣ ΑΕΜ - 2489
 * Σε αυτήν την κλάση εμφανίζει ένα παράθυρο με τα σκορ που ειναι αποθηκευμένα στο αρχείο.
 */
public class ScorePage {

    private ArrayList<String> scoreList =new ArrayList<String>(); //Λίστα με τα σκορ
    private ResourceBundle res;
    private String text;

    public ScorePage(ResourceBundle r) {
        readFile(); //"Φορτωνει τα σκορ"
        res=r;
        JPanel panel = new JPanel(); //Το βασικό πάνελ
        panel.setBackground(Color.darkGray);
        panel.setBorder(BorderFactory.createEmptyBorder(20,40,0,0));
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        text = res.getString("score");
        JLabel scoreTitle = new JLabel(text); //Τίτλος
        scoreTitle.setFont(new Font("Segoe Ui", Font.PLAIN,20));
        scoreTitle.setForeground(Color.white);
        scoreTitle.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));

        panel.add(scoreTitle);

        JLabel scores[] = new JLabel[scoreList.size()]; //Καθε σκορ ειναι σε διαφορετικό Jlabel ωστε να εμφανίζεται ξεχωριστά.
        for(int i=0;i<scoreList.size();i++) { //Δημιουργία αντίστοιχων JLabel
            scores[i] = new JLabel(scoreList.get(i));
            scores[i].setFont(new Font("Segoe Ui", Font.PLAIN, 14));
            scores[i].setForeground(Color.white);
            panel.add(scores[i]);
        }

        JScrollPane scrollPane = new JScrollPane(panel); //Δημιουργία scrollPane γιατί υπάρχει περίπτωση να είναι πολλά σκορ και να μην χωράνε στο παράθυρο.
        JFrame frame = new JFrame("High Scores");
        frame.setSize(400,600);
        frame.getContentPane().add(scrollPane);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Αποθήκευση στην λίστα τα στοιχεία που υπάρχουν στο αρχείο
     */
    public void readFile() {
        String fileName = "score.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine())!=null){
                scoreList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}