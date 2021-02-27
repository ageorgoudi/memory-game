import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author  ΑΜΑΛΙΑ ΓΕΩΡΓΟΥΔΗ ΑΕΜ - 3006 , ΚΙΟΣΗΣ ΕΥΑΓΓΕΛΟΣ ΑΕΜ - 2489
 * Σε αυτή την κλάση πραγρατοποιείται η λήξη του παιχνιδιού που περιέχει την αποθήκευση του σκόρ.
 */
public class EndAction {

    private JDialog dialog; //Παράθυρο
    private JTextField saveText;
    private int players,winner,result; //Παικτης, νικητής, πόντοι
    private String fileName = "score.txt"; //Αρχείο διαχείρισης σκόρ.
    private ArrayList<String> scoreList =new ArrayList<String>(); //Λίστα με τα σκορ όλων των παικτών απο το αρχείο.
    private ResourceBundle res;
    private String text;

    /**
     * ΚΑΤΑΣΚΕΥΑΣΤΗΣ
     * @param p πλήθος παικτων
     * @param w νικητης
     * @param r πόντοι
     */
    public EndAction(int p, int w, int r, ResourceBundle r1) {
        res=r1;
        players = p;
        winner = w;
        result = r;
        dialog = new JDialog(); //Δημιουργία παραθύρου
        dialog.setSize(400, 200);

        JPanel endPanel = new JPanel();
        endPanel.setBackground(Color.darkGray);
        endPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        endPanel.setLayout(new BoxLayout(endPanel, BoxLayout.PAGE_AXIS));

        JLabel endTxt = new JLabel();
        endTxt.setForeground(Color.white);
        endTxt.setFont(new Font("Segoe Ui", Font.PLAIN, 12));
        endTxt.setAlignmentX(Component.CENTER_ALIGNMENT);

        text = res.getString("end");
        JLabel endTxt2 = new JLabel(text);
        endTxt2.setForeground(Color.white);
        endTxt2.setFont(new Font("Segoe Ui", Font.PLAIN, 14));
        endTxt2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel savePanel = new JPanel();
        savePanel.setBackground(Color.darkGray);
        savePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        text = res.getString("name");
        saveText = new JTextField(text);
        saveText.setForeground(Color.darkGray);
        saveText.setBackground(Color.white);
        savePanel.setFont(new Font("Segoe Ui", Font.PLAIN, 10));

        text = res.getString("save");
        JButton saveB = new JButton(text); // Κουμπί αποθήκευσης
        saveB.setBackground(Color.white);
        saveB.setForeground(Color.darkGray);
        saveB.setFocusPainted(false);
        saveB.setBorderPainted(false);

        //Επιλογή
        saveB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveScore(); //Κλήση συνάρτησης
                dialog.dispose(); //Σβήσιμο παραθύρου.
            }
        });

        //Ρύθμιση μηνύματος τερματισμού
        if (players== 1) {
            text = res.getString("endm1");
            endTxt.setText(text+" " + result);
        } else {
            if (winner!= -1) {
                text = res.getString("endm21");
                String text1 =res.getString("endm22");;
                endTxt.setText(text +" " + winner + " " + text1 +" "+result);
            } else {
                text = res.getString("endm3");
                endTxt.setText(text);
            }
        }
        savePanel.add(saveText);
        savePanel.add(saveB);
        endPanel.add(endTxt2);
        endPanel.add(endTxt);
        endPanel.add(savePanel);
        dialog.add(endPanel);
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(null);
    }

    /**
     * Αποθηκεύει το σκορ στο αρχείο
     */
    void saveScore(){
        String name = saveText.getText();
        String text;
        if(players==1) {
            text= name + " | Number of steps: " + result;
        }else {
            text = name + " | Score: " + result;
        }
        readFile(); //Φορτώνει την λίστα με τα προηγούμενα στοιχεία.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            String content = text;
            scoreList.add(content); //προσθέτει το καινούργιο.
            for (int i=0;i<scoreList.size();i++){
                bw.write(scoreList.get(i) + "\n"); //Αποθηκεύει την λίστα στο αρχείο.
            }
        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    /**
     * Αποθήκευση στην λίστα τα στοιχεία που υπάρχουν στο αρχείο
     */
    public void readFile() {
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

