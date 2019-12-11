package maze;
import javax.swing.JOptionPane;

/**
 * DialogBox Class.
 * This class is responsible for displaying a box of information.
 *
 * @author Joshua Richards
 */

class DialogBox
{

    static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}