package email;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormatSymbols;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.NoSuchProviderException;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**megabinman123@gmail.com
 *
 * @author Max Carter
 */
public class GUI extends javax.swing.JFrame {

    public final String FOLDER = "INBOX";
    
    Email email;
    JTextField[] fields;
    
    public void enterEmailData() {
        String name = enterUsername.getText(), pw = passwordToString(enterPassword.getPassword());
        
        try {
            if (graphTypeMonth.isSelected()) {
                createGraph(getTodaysMonth(), new int[] { email.getEmailCount(name, pw, FOLDER, getTodaysMonth()) });
            } else {
                
                createGraph("ALL", email.getEmailCountPerMonth(name, pw, FOLDER));
            }
            
        } catch (NoSuchProviderException ex) {
            
        } catch (Exception ex) {
            
        }
    }
    
    protected void checkTextField(JTextField field) {
        field.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
              warn();
            }
            public void removeUpdate(DocumentEvent e) {
              warn();
            }
            public void insertUpdate(DocumentEvent e) {
              warn();
            }

            public void warn() {
                enterSubmit.setEnabled(isSet(fields));
            }
        });
    }
    
    public boolean isSet(JTextField[] fields) {
        for (JTextField field : fields) {
            if (field.getText().equals("")) {
                return false;
            }
        }
        return true;
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
        
        final DefaultCategoryDataset datasetBar = new DefaultCategoryDataset();
        final DefaultPieDataset datasetPie = new DefaultPieDataset();
        
        
        if (month.equalsIgnoreCase("ALL")) {
            int index = 0;
            
            for (String monthTemp : new DateFormatSymbols().getMonths()) {
                if (!monthTemp.equals("")) {
                    datasetBar.addValue(values[index], "Quantity", monthTemp.substring(0, 3));
                    datasetPie.insertValue(index, monthTemp.substring(0, 3), values[index]);
                    index++;
                }
            }
        } else {
            datasetBar.addValue(values[0], "Quantity", month);
            datasetPie.insertValue(0, month, values[0]);
        }
        
        final String titleName = "Email Rates";
        
        JFreeChart chartBar = ChartFactory.createBarChart(titleName, "Month(s)", "Email #", datasetBar, PlotOrientation.VERTICAL, true, true, false);
        JFreeChart chartPie = ChartFactory.createRingChart(titleName, datasetPie, true, true, false);
        JFreeChart chart3D = ChartFactory.createPieChart3D(titleName, datasetPie);

        StandardChartTheme theme = (StandardChartTheme)org.jfree.chart.StandardChartTheme.createJFreeTheme();
        
        // ---- Editing default graph theme: Fonts ----
        
        String GRAPH_FONT_NAME = "Agency FB";
        
        theme.setTitlePaint(new Color(0,153,153));
        theme.setExtraLargeFont( new Font(GRAPH_FONT_NAME,Font.BOLD, 32) );
        theme.setLargeFont( new Font(GRAPH_FONT_NAME,Font.BOLD, 25));
        theme.setRegularFont( new Font(GRAPH_FONT_NAME,Font.BOLD, 20));
        
        // ---- Editing default graph theme: Colours ----
        
        theme.setAxisLabelPaint(new Color(0,153,153));
        theme.setChartBackgroundPaint(Color.WHITE);
        theme.setPlotBackgroundPaint(Color.white);
        theme.setRangeGridlinePaint(new Color(0,153,153));
        
        // ---- Editing default graph theme: Formatting ----
        
        chartBar.getCategoryPlot().setOutlineVisible(false);
        chartBar.getCategoryPlot().getRangeAxis().setAxisLineVisible(false);
        

        for (JFreeChart chart : new JFreeChart[] { chartBar, chartPie, chart3D }) {
            theme.apply(chart);
        }
        
        addGraphToPanel(graphPanel, chartBar);
        addGraphToPanel(graphPanelDonut, chartPie);
        addGraphToPanel(graphPanelScatter, chart3D);
    }
    
    public void addGraphToPanel(JPanel panel, JFreeChart chart) {
        panel.setLayout(new java.awt.BorderLayout());
        ChartPanel CP = new ChartPanel(chart);
        panel.add(CP,BorderLayout.CENTER);
        panel.validate();
    }
    
    public static void printError(String text) {
        errorOutput.setText("<html>" + text + "</html>");
    }
    
    public GUI() throws NoSuchProviderException {
        initComponents();
        email = new Email();
        fields = new JTextField[] { enterUsername, enterPassword };
        for (JTextField field : fields) {
            checkTextField(field);
        }
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
        panelTimeFrame = new javax.swing.JPanel();
        graphTypeMonth = new javax.swing.JRadioButton();
        graphTypeYear = new javax.swing.JRadioButton();
        graphPanel = new javax.swing.JPanel();
        panelsSettings = new javax.swing.JPanel();
        about = new javax.swing.JLabel();
        errorOutput = new javax.swing.JLabel();
        graphPanelScatter = new javax.swing.JPanel();
        graphPanelDonut = new javax.swing.JPanel();

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
        panelDetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Email Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 36), new java.awt.Color(0, 153, 153))); // NOI18N

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
        enterSubmit.setEnabled(false);
        enterSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterSubmitActionPerformed(evt);
            }
        });

        panelTimeFrame.setBackground(new java.awt.Color(255, 255, 255));
        panelTimeFrame.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Time Frame", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 18), new java.awt.Color(0, 153, 153))); // NOI18N

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
        graphTypeYear.setText("All Time");
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
                    .addComponent(graphTypeMonth, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
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
                    .addComponent(enterUsername)
                    .addComponent(enterPassword)
                    .addComponent(panelTimeFrame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(enterSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
        );
        panelDetailsLayout.setVerticalGroup(
            panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetailsLayout.createSequentialGroup()
                .addComponent(enterUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enterPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTimeFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enterSubmit))
        );

        graphPanel.setBackground(new java.awt.Color(255, 255, 255));
        graphPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bar Chart", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 36), new java.awt.Color(0, 153, 153))); // NOI18N

        javax.swing.GroupLayout graphPanelLayout = new javax.swing.GroupLayout(graphPanel);
        graphPanel.setLayout(graphPanelLayout);
        graphPanelLayout.setHorizontalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        graphPanelLayout.setVerticalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelsSettings.setBackground(new java.awt.Color(255, 255, 255));
        panelsSettings.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Other", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 36), new java.awt.Color(0, 153, 153))); // NOI18N

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

        errorOutput.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        errorOutput.setForeground(new java.awt.Color(204, 0, 0));
        errorOutput.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Error Output:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 24), new java.awt.Color(0, 153, 153))); // NOI18N

        javax.swing.GroupLayout panelsSettingsLayout = new javax.swing.GroupLayout(panelsSettings);
        panelsSettings.setLayout(panelsSettingsLayout);
        panelsSettingsLayout.setHorizontalGroup(
            panelsSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelsSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelsSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(errorOutput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(about, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelsSettingsLayout.setVerticalGroup(
            panelsSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelsSettingsLayout.createSequentialGroup()
                .addComponent(errorOutput, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(about, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        graphPanelScatter.setBackground(new java.awt.Color(255, 255, 255));
        graphPanelScatter.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "3D Pie", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 36), new java.awt.Color(0, 153, 153))); // NOI18N

        javax.swing.GroupLayout graphPanelScatterLayout = new javax.swing.GroupLayout(graphPanelScatter);
        graphPanelScatter.setLayout(graphPanelScatterLayout);
        graphPanelScatterLayout.setHorizontalGroup(
            graphPanelScatterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 488, Short.MAX_VALUE)
        );
        graphPanelScatterLayout.setVerticalGroup(
            graphPanelScatterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        graphPanelDonut.setBackground(new java.awt.Color(255, 255, 255));
        graphPanelDonut.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Donut Chart", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 36), new java.awt.Color(0, 153, 153))); // NOI18N

        javax.swing.GroupLayout graphPanelDonutLayout = new javax.swing.GroupLayout(graphPanelDonut);
        graphPanelDonut.setLayout(graphPanelDonutLayout);
        graphPanelDonutLayout.setHorizontalGroup(
            graphPanelDonutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        graphPanelDonutLayout.setVerticalGroup(
            graphPanelDonutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(graphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(backLayout.createSequentialGroup()
                        .addComponent(graphPanelScatter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(graphPanelDonut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(8, 8, 8))
        );
        backLayout.setVerticalGroup(
            backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backLayout.createSequentialGroup()
                .addComponent(top, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backLayout.createSequentialGroup()
                        .addComponent(panelDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelsSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(backLayout.createSequentialGroup()
                        .addComponent(graphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(graphPanelScatter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(graphPanelDonut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
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
    private javax.swing.JPasswordField enterPassword;
    private javax.swing.JButton enterSubmit;
    private javax.swing.JTextField enterUsername;
    public static javax.swing.JLabel errorOutput;
    public javax.swing.JPanel graphPanel;
    public javax.swing.JPanel graphPanelDonut;
    public javax.swing.JPanel graphPanelScatter;
    private javax.swing.JRadioButton graphTypeMonth;
    private javax.swing.JRadioButton graphTypeYear;
    private javax.swing.JPanel panelDetails;
    private javax.swing.JPanel panelTimeFrame;
    private javax.swing.JPanel panelsSettings;
    private javax.swing.JLabel title;
    private javax.swing.JPanel top;
    private javax.swing.ButtonGroup type;
    // End of variables declaration//GEN-END:variables
}
