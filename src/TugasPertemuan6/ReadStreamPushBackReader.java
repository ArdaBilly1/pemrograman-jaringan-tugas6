/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TugasPertemuan6;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PushbackInputStream;
import java.io.PushbackReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;


/**
 *
 * @author hp
 */
public class ReadStreamPushBackReader {
    
    //Tugas 6
    private final ReadFileForm1 view;

    public ReadStreamPushBackReader(ReadFileForm1 view) {
        this.view = view;

        this.view.getBtnBaca().addActionListener((ActionEvent e) -> {
            try {
                proses();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReadStreamPushBackReader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadLocationException | IOException ex) {
                Logger.getLogger(ReadStreamPushBackReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.view.getBtnSave().addActionListener((ActionEvent e) -> {
            simpan();
        });
    }

    private void proses() throws FileNotFoundException, BadLocationException, IOException {
        JFileChooser loadFile = view.getLoadFile();
        StyledDocument doc = view.getjTextPane1().getStyledDocument();
        if (JFileChooser.APPROVE_OPTION == loadFile.showOpenDialog(view)) {
            PushbackReader  reader = new PushbackReader(new FileReader(loadFile.getSelectedFile()));
            LineNumberReader re = new LineNumberReader(new FileReader(loadFile.getSelectedFile()));
                
            char[] words = new char[(int) loadFile.getSelectedFile().length()];
            
                try {
                    reader.read(words);
                    
                    String data = null;
                    String data1 = null;
                    doc.insertString(0, "", null);
                    
                    int hitungKar = 0;
                    int hitungKata = 0;
           
                
                    
                    if((data = new String(words)) != null) {
                        String[] wordlist = data.split("\\s+");
                      
                        hitungKar += words.length;
                        hitungKata += wordlist.length;
                       
                        doc.insertString(doc.getLength(), "" + data + "\n", null);
                     
                    }
                    while((data1 = re.readLine()) != null){
                       
                    }
                     int hitungBar = re.getLineNumber();
                    
                JOptionPane.showMessageDialog(null, "File Berhasil dibaca." + "\n"
                        + "Jumlah baris = " + hitungBar + "\n"
                        + "Jumlah kata = " + hitungKata + "\n"
                        + "Jumlah karakter = " + hitungKar);
                    
                } catch (IOException ex) {
                    Logger.getLogger(ReadStreamPushBackReader.class.getName()).log(Level.SEVERE, null, ex);
                }
         
        }
    }

    private void simpan() {
        JFileChooser loadFile = view.getLoadFile();
        if (JFileChooser.APPROVE_OPTION == loadFile.showSaveDialog(view)) {
            BufferedWriter writer = null;
            try {
                String contents = view.getjTextPane1().getText();
                if (contents != null && !contents.isEmpty()) {
                    writer = new BufferedWriter(new FileWriter(loadFile.getSelectedFile()));
                    writer.write(contents);
                }
                JOptionPane.showMessageDialog(null, "File Berhasil disimpan.");

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ReadStreamPushBackReader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ReadStreamPushBackReader.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (writer != null) {
                    try {
                        writer.flush();
                        writer.close();
                        view.getjTextPane1().setText("");
                    } catch (IOException ex) {
                        Logger.getLogger(ReadStreamPushBackReader.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
