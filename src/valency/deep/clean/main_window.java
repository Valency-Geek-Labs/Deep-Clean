/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package valency.deep.clean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;
import java.net.Socket;
import java.net.SocketTimeoutException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author skali
 */
public class main_window extends javax.swing.JFrame {

    /**
     * Creates new form main_window
     */
    public main_window() {
        initComponents();
        makeready("Ready");
    }

    public String path;
    
    public String browsefile(){
        JFileChooser jfc = new JFileChooser();
        //Make chooser specific to file type
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text & CSV Files", "txt","csv");
        jfc.setFileFilter(filter);
        int result = jfc.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            return jfc.getSelectedFile().getAbsolutePath();
        }
        
        return null;
    }
    
    public String check_status() {
        //Get the location
        String filepath = location.getText();
        //Check the status of Checklboxes
        String num_box = numeric_box.isSelected() ? "checked":"unchecked";
        String cat_box = categorical_box.isSelected() ? "checked":"unchecked";
        
        //get the selected Item
        String technique = (String) this.technique.getSelectedItem();
        String operation = (String) this.operation.getSelectedItem();
        
        return filepath + ',' + num_box + ',' + cat_box + ',' + technique + ',' + operation;
    }
    
    public String SendforClean(String data){
        //Sending Data to python for cleaning
        //Host and port
        String host = "localhost";
        int port = 4040;
        String response = "";
        
        try(Socket socket = new Socket(host,port);
            PrintWriter transmit = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
            
            transmit.println(data);
            
            //Use String Builder to read all outputs
            StringBuilder builder = new StringBuilder();
           
            
            while ((response = reader.readLine()) != null) {                
                builder.append(response).append("\n");
                
            }
            
            log_area.setText(builder.toString());
            
           
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            response = "IOException occurred: " + e.getMessage();
            e.printStackTrace();
        }
        return response;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        location = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        numeric_box = new javax.swing.JCheckBox();
        categorical_box = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        technique = new javax.swing.JComboBox<>();
        operation = new javax.swing.JComboBox<>();
        browse_bt = new javax.swing.JButton();
        clean_csv = new javax.swing.JButton();
        veri_bt = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        log_area = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        status = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Valency Deep Clean");
        setIconImages(null);
        setLocationByPlatform(true);
        setResizable(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/valency/deep/clean/Images/Icon.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/valency/deep/clean/Images/VALENCY.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/valency/deep/clean/Images/DEEP CLEAN.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("File Path:");

        location.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        location.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Preferences:");

        numeric_box.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        numeric_box.setForeground(new java.awt.Color(0, 0, 0));
        numeric_box.setText("Numerical Values");

        categorical_box.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        categorical_box.setForeground(new java.awt.Color(0, 0, 0));
        categorical_box.setText("Categorical Values");

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Imputer Operation:");

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Cleaning Technique:");

        technique.setForeground(new java.awt.Color(0, 0, 0));
        technique.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Filling", "Removing" }));

        operation.setForeground(new java.awt.Color(0, 0, 0));
        operation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Median", "Mean" }));

        browse_bt.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        browse_bt.setText("Browse");
        browse_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browse_btActionPerformed(evt);
            }
        });

        clean_csv.setFont(new java.awt.Font("THE GLOBE PERSONAL USE", 1, 16)); // NOI18N
        clean_csv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/valency/deep/clean/Images/DEEP CLEAN.png"))); // NOI18N
        clean_csv.setEnabled(false);
        clean_csv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clean_csvActionPerformed(evt);
            }
        });

        veri_bt.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        veri_bt.setForeground(new java.awt.Color(0, 0, 0));
        veri_bt.setText("Verify File!");
        veri_bt.setEnabled(false);
        veri_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                veri_btActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Log:");

        log_area.setEditable(false);
        log_area.setColumns(20);
        log_area.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        log_area.setRows(5);
        jScrollPane1.setViewportView(log_area);

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Status:");

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));

        status.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        status.setForeground(new java.awt.Color(0, 0, 0));
        status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/valency/deep/clean/Images/loading.gif"))); // NOI18N
        status.setText("Not ready");

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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(139, 139, 139)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addGap(18, 18, 18)
                                                .addComponent(numeric_box)
                                                .addGap(18, 18, 18)
                                                .addComponent(categorical_box))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel8)
                                                    .addComponent(jLabel7))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(operation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(technique, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel2)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(location, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(browse_bt))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel10)
                                                                .addGap(98, 98, 98))
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel9)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(status)
                                                                .addGap(12, 12, 12)))
                                                        .addComponent(jLabel13)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel12)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel11))))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(532, 532, 532)
                                .addComponent(veri_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clean_csv))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5)))
                        .addGap(0, 79, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(status))
                                .addComponent(jLabel2))
                            .addComponent(jLabel3)))
                    .addComponent(jLabel1))
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(location, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(browse_bt))
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(numeric_box)
                    .addComponent(categorical_box))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(technique, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(operation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(clean_csv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(veri_bt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void makeready(String text){
    
        Timer timer = new Timer();
        
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                status.setText(text); 
                ImageIcon icon = new ImageIcon(getClass().getResource("/valency/deep/clean/Images/Done.png"));
                status.setIcon(icon);
                veri_bt.setEnabled(true);
            }
        }, 15000);
        
    }
    
    private void browse_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browse_btActionPerformed
        path = browsefile();
        location.setText(path);
    }//GEN-LAST:event_browse_btActionPerformed

    private void veri_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_veri_btActionPerformed
        
        if(location.getText().contains(".csv")){
           String data = check_status();
           System.out.println(data);
           SendforClean(data);
           System.out.println("Sent!");
           clean_csv.setEnabled(true);
        }
        else{
           JOptionPane.showMessageDialog(null, "Please select your preferences and file path","Warning" , JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_veri_btActionPerformed

    private void clean_csvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clean_csvActionPerformed
        
        //Check whether the csv contains missing values
        if(log_area.getText().contains("True")){
            String start = "CLEAN";
            SendforClean(start);
        }
        else{
            JOptionPane.showMessageDialog(null, "The selected CSV doesn't contain missing values","Alert",JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_clean_csvActionPerformed

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
            java.util.logging.Logger.getLogger(main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main_window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main_window().setVisible(true);
                
                
                
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton browse_bt;
    private javax.swing.JCheckBox categorical_box;
    private javax.swing.JButton clean_csv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextField location;
    public javax.swing.JTextArea log_area;
    private javax.swing.JCheckBox numeric_box;
    private javax.swing.JComboBox<String> operation;
    private javax.swing.JLabel status;
    private javax.swing.JComboBox<String> technique;
    public javax.swing.JButton veri_bt;
    // End of variables declaration//GEN-END:variables

   
}
