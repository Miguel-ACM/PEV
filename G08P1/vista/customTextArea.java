package vista;

import java.io.IOException;
import java.io.OutputStream;
 
import javax.swing.JTextArea;
 
public class customTextArea extends OutputStream {
    private JTextArea textArea;
     
    public customTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }
     
    @Override
    public void write(int b) throws IOException {
        // redirects data to the text area
        textArea.append(String.valueOf((char)b));
    }
}