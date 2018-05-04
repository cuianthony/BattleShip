/******************************************************************
 * Description:
 * 
 * The DungeonLogStream class extends OutputStream. The idea is to
 * redirect the log info from the regular system output to a JTextArea.
 * The JTextArea is part of the game UI.
 * 
 * 
 * @author (Anthony Cui)
 * @version (v1.0)
 * @version (Dec 13, 2015)
 ******************************************************************/

import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JTextArea;

public class DungeonLogStream extends OutputStream {
    private JTextArea ta;
    
    /******************************************************************
     * Description:
     * The DungeonLogStream constructor.
     *****************************************************************/
    public DungeonLogStream(JTextArea ta) {
        this.ta = ta;
    }


    /******************************************************************
    * Description:
    * Overwrite the write method in OutputStream. Write the character
    * to the text area.
    * 
    * Limitations: none
    *****************************************************************/
    //overwrite the write method in OutputStream
    public void write(int ch) throws IOException {
        // send "ch" to the text area
        ta.append(String.valueOf((char)ch));
        // automatically move to the end of the print in the text area
        //scrolls the text area to the end of data
        ta.setCaretPosition(ta.getDocument().getLength());
    }
}