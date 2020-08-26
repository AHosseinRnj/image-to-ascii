package image_to_ascii;

import Classes.ASCII;
import java.awt.Image;
import java.awt.Dimension;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainForm extends javax.swing.JFrame {

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chooseFileBtn = new javax.swing.JButton();
        fileNameLbl = new javax.swing.JLabel();
        preViewLbl = new javax.swing.JLabel();
        convertBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Image To ASCII Converter");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        chooseFileBtn.setFont(new java.awt.Font("Microsoft Tai Le", 0, 15)); // NOI18N
        chooseFileBtn.setText("Choose File");
        chooseFileBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chooseFileBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chooseFileBtnMouseClicked(evt);
            }
        });

        fileNameLbl.setFont(new java.awt.Font("Microsoft Tai Le", 0, 15)); // NOI18N
        fileNameLbl.setText("No file chosen");

        preViewLbl.setFont(new java.awt.Font("Microsoft Tai Le", 0, 15)); // NOI18N
        preViewLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        preViewLbl.setText("Pre View");
        preViewLbl.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        preViewLbl.setMinimumSize(new Dimension (61,24));
        preViewLbl.setPreferredSize(new Dimension (61,24));
        preViewLbl.setMaximumSize(new Dimension (61,24));

        convertBtn.setFont(new java.awt.Font("Microsoft Tai Le", 0, 15)); // NOI18N
        convertBtn.setText(" Convert & Save");
        convertBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        convertBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                convertBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(chooseFileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fileNameLbl))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(144, 144, 144)
                                .addComponent(convertBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 138, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(preViewLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chooseFileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fileNameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(preViewLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(convertBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    File file;
    BufferedImage bImage;
    JFileChooser fileChooser;
    private void chooseFileBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chooseFileBtnMouseClicked
        Image resizedBImage;
        fileChooser = new JFileChooser();
        FileFilter imageFilter = new FileNameExtensionFilter("Image Files", ImageIO.getReaderFileSuffixes());

        fileChooser.setFileFilter(imageFilter);
        fileChooser.setAcceptAllFileFilterUsed(false);

        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();

            try {
                bImage = ImageIO.read(new File(file.getPath()));
                fileNameLbl.setText(file.getName());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e);
            }

            resizedBImage = bImage.getScaledInstance(preViewLbl.getWidth(), preViewLbl.getHeight(), Image.SCALE_SMOOTH);

            preViewLbl.setText("");
            preViewLbl.setIcon(new ImageIcon(resizedBImage));
        }
    }//GEN-LAST:event_chooseFileBtnMouseClicked

    private void convertBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_convertBtnMouseClicked
        if (bImage == null) {
            JOptionPane.showMessageDialog(null, "Please Select an Image First!");
        } else {
            ASCII ascii = new ASCII(bImage);
            String Text = ascii.convert();

            fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File("ascii.txt"));
            FileFilter textFilter = new FileNameExtensionFilter("Text Files", "txt");

            fileChooser.setFileFilter(textFilter);
            fileChooser.setAcceptAllFileFilterUsed(false);

            fileChooser.showSaveDialog(this);

            file = fileChooser.getSelectedFile();
            try {
                FileWriter FWritter = new FileWriter(file);
                FWritter.write(Text);
                FWritter.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                ascii.dispose();
            }
        }
    }//GEN-LAST:event_convertBtnMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chooseFileBtn;
    private javax.swing.JButton convertBtn;
    private javax.swing.JLabel fileNameLbl;
    private javax.swing.JLabel preViewLbl;
    // End of variables declaration//GEN-END:variables
}
