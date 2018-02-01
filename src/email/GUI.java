package email;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormatSymbols;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.NoSuchProviderException;
import javax.swing.ImageIcon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**megabinman123@gmail.com
 *
 * @author Max Carter
 */
public class GUI extends javax.swing.JFrame {

    Email email;
    
    public void enterEmailData() {
        String name = enterUsername.getText(), pw = passwordToString(enterPassword.getPassword()), folder = enterFolder.getText();
        
        try {
            if (graphTypeMonth.isSelected()) {
                createGraph(getTodaysMonth(), new int[] { email.getEmailCount(name, pw, folder, getTodaysMonth()) });
            } else {
                
                createGraph("ALL", email.getEmailCountPerMonth(name, pw, folder));
            }
            
        } catch (NoSuchProviderException ex) {
            
        } catch (Exception ex) {
            
        }
    }
    
    public String getTodaysMonth() {
        return email.dateFormat.format(new Date());
    }
    
    public String passwordToString(char[] text) {
        StringBuilder sb = new StringBuilder(text.length);
        for (char c : text)
            sb.append(c);
        return sb.toString();
        
    }
    
    public void createGraph(String month, int[] values) throws Exception {
        
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        if (month.equalsIgnoreCase("ALL")) {
            int index = 0;
            
            for (String monthTemp : new DateFormatSymbols().getMonths()) {
                if (!monthTemp.equals("")) {
                    dataset.addValue(values[index], monthTemp.substring(0, 3), monthTemp.substring(0, 3));
                    index++;
                }
            }
        } else {
            dataset.addValue(values[0], month, month);
        }
        
        JFreeChart barChart = ChartFactory.createBarChart("Email Rates", "Month(s)", "Email #", dataset, PlotOrientation.VERTICAL, true, true, false);

        File BarChart = new File("BarChart.png"); 
        ChartUtilities.saveChartAsJPEG(BarChart , barChart , graphHold.getWidth() , graphHold.getHeight());
        
        BufferedImage image = barChart.createBufferedImage(graphHold.getWidth(), graphHold.getHeight());
        
        graphHold.setIcon(new ImageIcon(image));
    }
    
    public GUI() throws NoSuchProviderException {
        initComponents();
        email = new Email();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        type = new javax.swing.ButtonGroup();
        back = new javax.swing.JPanel();
        top = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        panelDetails = new javax.swing.JPanel();
        enterUsername = new javax.swing.JTextField();
        enterPassword = new javax.swing.JPasswordField();
        enterSubmit = new javax.swing.JButton();
        enterFolder = new javax.swing.JTextField();
        panelTimeFrame = new javax.swing.JPanel();
        graphTypeMonth = new javax.swing.JRadioButton();
        graphTypeYear = new javax.swing.JRadioButton();
        panelGraph = new javax.swing.JPanel();
        graphHold = new javax.swing.JLabel();
        panelsSettings = new javax.swing.JPanel();
        about = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Email Analysis");

        back.setBackground(new java.awt.Color(255, 255, 255));

        top.setBackground(new java.awt.Color(0, 153, 153));

        title.setFont(new java.awt.Font("Agency FB", 1, 56)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Email Analysis");

        javax.swing.GroupLayout topLayout = new javax.swing.GroupLayout(top);
        top.setLayout(topLayout);
        topLayout.setHorizontalGroup(
            topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 1380, Short.MAX_VALUE)
                .addContainerGap())
        );
        topLayout.setVerticalGroup(
            topLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelDetails.setBackground(new java.awt.Color(255, 255, 255));
        panelDetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Email Details", 0, 0, new java.awt.Font("Agency FB", 1, 36), new java.awt.Color(0, 153, 153))); // NOI18N

        enterUsername.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        enterUsername.setToolTipText("Enter username here...");
        enterUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        enterPassword.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        enterPassword.setToolTipText("Enter password here...");
        enterPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        enterSubmit.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        enterSubmit.setForeground(new java.awt.Color(0, 153, 153));
        enterSubmit.setText("Enter");
        enterSubmit.setToolTipText("Create graph");
        enterSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterSubmitActionPerformed(evt);
            }
        });

        enterFolder.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        enterFolder.setText("Inbox");
        enterFolder.setToolTipText("Enter folder name here...");
        enterFolder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        panelTimeFrame.setBackground(new java.awt.Color(255, 255, 255));
        panelTimeFrame.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Time Frame", 0, 0, new java.awt.Font("Agency FB", 1, 18), new java.awt.Color(0, 153, 153))); // NOI18N

        type.add(graphTypeMonth);
        graphTypeMonth.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        graphTypeMonth.setForeground(new java.awt.Color(0, 153, 153));
        graphTypeMonth.setSelected(true);
        graphTypeMonth.setText("This Month");
        graphTypeMonth.setToolTipText("Email data for this month");
        graphTypeMonth.setIconTextGap(8);
        graphTypeMonth.setOpaque(false);

        type.add(graphTypeYear);
        graphTypeYear.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        graphTypeYear.setForeground(new java.awt.Color(0, 153, 153));
        graphTypeYear.setText("This Year");
        graphTypeYear.setToolTipText("Email data for the whole year");
        graphTypeYear.setIconTextGap(8);
        graphTypeYear.setOpaque(false);

        javax.swing.GroupLayout panelTimeFrameLayout = new javax.swing.GroupLayout(panelTimeFrame);
        panelTimeFrame.setLayout(panelTimeFrameLayout);
        panelTimeFrameLayout.setHorizontalGroup(
            panelTimeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimeFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTimeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(graphTypeYear, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                    .addComponent(graphTypeMonth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelTimeFrameLayout.setVerticalGroup(
            panelTimeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimeFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(graphTypeMonth)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(graphTypeYear)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelDetailsLayout = new javax.swing.GroupLayout(panelDetails);
        panelDetails.setLayout(panelDetailsLayout);
        panelDetailsLayout.setHorizontalGroup(
            panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(enterSubmit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                    .addComponent(enterUsername)
                    .addComponent(enterPassword)
                    .addComponent(enterFolder)
                    .addComponent(panelTimeFrame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelDetailsLayout.setVerticalGroup(
            panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetailsLayout.createSequentialGroup()
                .addComponent(enterUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enterPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enterFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTimeFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(enterSubmit)
                .addContainerGap())
        );

        panelGraph.setBackground(new java.awt.Color(255, 255, 255));
        panelGraph.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Graph", 0, 0, new java.awt.Font("Agency FB", 1, 36), new java.awt.Color(0, 153, 153))); // NOI18N

        javax.swing.GroupLayout panelGraphLayout = new javax.swing.GroupLayout(panelGraph);
        panelGraph.setLayout(panelGraphLayout);
        panelGraphLayout.setHorizontalGroup(
            panelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGraphLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(graphHold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelGraphLayout.setVerticalGroup(
            panelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGraphLayout.createSequentialGroup()
                .addComponent(graphHold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelsSettings.setBackground(new java.awt.Color(255, 255, 255));
        panelsSettings.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Settions & Options", 0, 0, new java.awt.Font("Agency FB", 1, 36), new java.awt.Color(0, 153, 153))); // NOI18N

        about.setBackground(new java.awt.Color(0, 153, 153));
        about.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        about.setForeground(new java.awt.Color(255, 255, 255));
        about.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        about.setText("Max Carter - mxcrtr.com");
        about.setOpaque(true);
        about.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aboutMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelsSettingsLayout = new javax.swing.GroupLayout(panelsSettings);
        panelsSettings.setLayout(panelsSettingsLayout);
        panelsSettingsLayout.setHorizontalGroup(
            panelsSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelsSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(about, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelsSettingsLayout.setVerticalGroup(
            panelsSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelsSettingsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(about, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout backLayout = new javax.swing.GroupLayout(back);
        back.setLayout(backLayout);
        backLayout.setHorizontalGroup(
            backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(backLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelsSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        backLayout.setVerticalGroup(
            backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backLayout.createSequentialGroup()
                .addComponent(top, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backLayout.createSequentialGroup()
                        .addComponent(panelDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addComponent(panelsSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enterSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterSubmitActionPerformed
        enterEmailData();
    }//GEN-LAST:event_enterSubmitActionPerformed

    private void aboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutMouseClicked
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI("www.mxcrtr.com/"));
            } catch (URISyntaxException ex) {

            } catch (IOException ex) {

            }
        }
    }//GEN-LAST:event_aboutMouseClicked

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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GUI().setVisible(true);
                } catch (NoSuchProviderException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel about;
    private javax.swing.JPanel back;
    private javax.swing.JTextField enterFolder;
    private javax.swing.JPasswordField enterPassword;
    private javax.swing.JButton enterSubmit;
    private javax.swing.JTextField enterUsername;
    public javax.swing.JLabel graphHold;
    private javax.swing.JRadioButton graphTypeMonth;
    private javax.swing.JRadioButton graphTypeYear;
    private javax.swing.JPanel panelDetails;
    public javax.swing.JPanel panelGraph;
    private javax.swing.JPanel panelTimeFrame;
    private javax.swing.JPanel panelsSettings;
    private javax.swing.JLabel title;
    private javax.swing.JPanel top;
    private javax.swing.ButtonGroup type;
    // End of variables declaration//GEN-END:variables
}
