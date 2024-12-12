
package frm;

import Classes.Enrollment;
import Classes.Semester;
import Classes.Student;
import Classes.Subject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;


public class NiFer extends javax.swing.JFrame {

    public NiFer() {
        initComponents();
        MongoDatabase database = Student.connectToDatabase();
        List<Student> students = Student.getAllStudentsFromDB(database);
        
        for(Student stu : students){
            sname.addItem(stu.getStudentName());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        sname = new javax.swing.JComboBox<>();
        semes = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        sy = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        DisplayBTN = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        gradeeee = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Sitka Display", 1, 18)); // NOI18N
        jLabel17.setText("STUDENT NAME");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 28));

        jPanel2.add(sname, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 300, 30));

        semes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "First Sem", "Second Sem" }));
        jPanel2.add(semes, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 300, 30));

        jLabel18.setFont(new java.awt.Font("Sitka Display", 1, 18)); // NOI18N
        jLabel18.setText("SEMESTER");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 28));

        sy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023-2024", "2024-2025" }));
        jPanel2.add(sy, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 300, 30));

        jLabel19.setFont(new java.awt.Font("Sitka Display", 1, 18)); // NOI18N
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 610, 210, 28));

        DisplayBTN.setText("DISPLAY");
        DisplayBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisplayBTNActionPerformed(evt);
            }
        });
        jPanel2.add(DisplayBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 110, 40));

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Subject Code", "Description", "Units", "Grades"
            }
        ));
        jScrollPane1.setViewportView(tbl);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, 410));

        jLabel20.setFont(new java.awt.Font("Sitka Display", 1, 18)); // NOI18N
        jLabel20.setText("SCHOOL YEAR");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 28));

        jLabel21.setFont(new java.awt.Font("Sitka Display", 1, 18)); // NOI18N
        jLabel21.setText("GWA: ");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 210, 28));

        gradeeee.setFont(new java.awt.Font("Sitka Display", 1, 18)); // NOI18N
        gradeeee.setText("0.0000");
        jPanel2.add(gradeeee, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, 210, 28));

        jTabbedPane1.addTab("Grades", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 638, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab2", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DisplayBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisplayBTNActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        MongoDatabase database = Enrollment.connectToDatabase();
        List<Enrollment> enrollment = Enrollment.getAllEnrollmentFromDB(database);
        MongoDatabase database1 = Student.connectToDatabase();
        List<Student> students = Student.getAllStudentsFromDB(database1);
        MongoDatabase database2 = Student.connectToDatabase();
        List<Subject> subjects = Subject.getAllSubjectsFromDB(database2);

        String name = (String) sname.getSelectedItem();
        String semname = (String) semes.getSelectedItem();
        String syear = (String) sy.getSelectedItem();
        String id = null;
        double aiah = 0;
        double ttlgrades = 0;

        ArrayList<String> subcodes = new ArrayList<>();
        ArrayList<Double> subgrades = new ArrayList<>();
        ArrayList<String> subnames = new ArrayList<>();

        for(Student stu : students){
            if(name.equals(stu.getStudentName())){
                id = stu.getStudentID();
            }   
        }
        if(semname.equals("First Sem") && syear.equals("2023-2024")){
            aiah = 1;
        } else if (semname.equals("Second Sem") && syear.equals("2023-2024")){
            aiah = 2;
        } else if (semname.equals("First Sem") && syear.equals("2024-2025")){
            aiah = 3;
        } else {
            aiah = 4;
        }

        for(Enrollment roll : enrollment){
            if(id.equals(roll.getStudentID()) && aiah == roll.getSemesterID()){
                for (String code : roll.getSubjectCode()) {
                    subcodes.add(code);
                }
                for (double grade : roll.getGrade()) {
                    subgrades.add(grade);
                }
            }
        }
        for(Double gra : subgrades){
            ttlgrades += gra*3;
        }

        for (int i = 0; i < subcodes.size(); i++) {
            String subname = "";
            boolean found = false;
            for(Subject sub : subjects){
                if(subcodes.get(i).equals(sub.getSubjectCode())){
                    subname = sub.getDescription();
                    found = true;
                    break;
                }
            }
            if(!found){
                subname = "Subject not found";
            }
            subnames.add(subname);
        }

        // Sort the subcodes, subnames, and subgrades in ascending order
        for (int i = 0; i < subcodes.size(); i++) {
            for (int j = i + 1; j < subcodes.size(); j++) {
                if (subcodes.get(i).compareTo(subcodes.get(j)) > 0) {
                    String tempCode = subcodes.get(i);
                    subcodes.set(i, subcodes.get(j));
                    subcodes.set(j, tempCode);

                    String tempName = subnames.get(i);
                    subnames.set(i, subnames.get(j));
                    subnames.set(j, tempName);

                    double tempGrade = subgrades.get(i);
                    subgrades.set(i, subgrades.get(j));
                    subgrades.set(j, tempGrade);
                }
            }
        }

        model.setRowCount(0);

        // Add subcode and grade to the table
        for (int i = 0; i < subcodes.size(); i++) {
            Object[] rowData = {subcodes.get(i), subnames.get(i), "3.00" , subgrades.get(i)};
            model.addRow(rowData);
        }

        // Check if there is nothing in the subname
        boolean isEmpty = true;
        for (String subname : subnames) {
            if (!subname.isEmpty() && !subname.equals("Subject not found")) {
                isEmpty = false;
                break;
            }
        }

        if (isEmpty) {
            JOptionPane.showMessageDialog(null, "No subjects found for the selected student and semester.");
        } else {
            gradeeee.setText(String.format("%.2f", ttlgrades/27));
        }
    }//GEN-LAST:event_DisplayBTNActionPerformed

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
            java.util.logging.Logger.getLogger(NiFer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NiFer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NiFer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NiFer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NiFer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DisplayBTN;
    private javax.swing.JLabel gradeeee;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> semes;
    private javax.swing.JComboBox<String> sname;
    private javax.swing.JComboBox<String> sy;
    private javax.swing.JTable tbl;
    // End of variables declaration//GEN-END:variables
}
