/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package TreeGenerator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author andy
 */
public class BSTGui extends javax.swing.JFrame {
    
    private BinarySearchTree bst = new BinarySearchTree();  // Instance of BinarySearchTree
    private JPanel drawingPanel;
    
    class TreeNode {
        int value;
        TreeNode left, right;

        public TreeNode(int value) {
            this.value = value;
            left = right = null;
        }
    }
    
    class BinarySearchTree {
        TreeNode root;

        public void insert(int value) {
            root = insertRec(root, value);
        }
        
        public void delete(int value) {
    root = deleteRec(root, value);
}

private TreeNode deleteRec(TreeNode root, int value) {
    if (root == null) {
        return root;
    }

    // Traverse the tree to find the node to delete
    if (value < root.value) {
        root.left = deleteRec(root.left, value);
    } else if (value > root.value) {
        root.right = deleteRec(root.right, value);
    } else {
        // Node with only one child or no child
        if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        }

        // Node with two children: get the in-order successor (smallest in the right subtree)
        root.value = minValue(root.right);

        // Delete the in-order successor
        root.right = deleteRec(root.right, root.value);
    }

    return root;
}

        private int minValue(TreeNode root) {
            int minValue = root.value;
            while (root.left != null) {
                root = root.left;
                minValue = root.value;
            }
            return minValue;
        }

                private TreeNode insertRec(TreeNode root, int value) {
                    if (root == null) {
                        root = new TreeNode(value);
                        return root;
                    }
                    if (value < root.value) {
                        root.left = insertRec(root.left, value);
                    } else if (value > root.value) {
                        root.right = insertRec(root.right, value);
                    }
                    return root;
                }
    }

    // Constructor for setting up the GUI
    public BSTGui() {
        setTitle("Binary Search Tree");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Input field for number
        numField = new JTextField(10);
        
        // Button to add the number
        JButton addButton = new JButton("Add Number");
        jButton1 = new JButton("DELETE");
        jButton1.addActionListener(this::jButton1ActionPerformed);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                addnumActionPerformed(evt);
            }
        });

        // Panel for controls
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Enter Number:"));
        controlPanel.add(numField);
        controlPanel.add(addButton);
        controlPanel.add(jButton1);

        // Drawing panel to display the tree
        drawingPanel = new JPanel() {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bst.root != null) {
            drawTree(g, bst.root, getWidth() / 2, 30, getWidth() / 4, 0);  // Start from level 0
        }
    }

    private void drawTree(Graphics g, TreeNode node, int x, int y, int xOffset, int level) {
        // Change node color based on the level
        if (level == 0) {
            g.setColor(Color.GREEN);  // Level 0 (root node)
        } else if (level == 1) {
            g.setColor(Color.BLUE);  // Level 1
        } else if (level == 2) {
            g.setColor(Color.RED);   // Level 2
        } else if (level == 3) {
            g.setColor(Color.ORANGE); // Level 3
        } else {
            g.setColor(Color.YELLOW); // For deeper levels, continue with different colors
        }

        // Fill the circle with the chosen color
        g.fillOval(x - 15, y - 15, 30, 30);  // Fill the circle

        // Set the text color to white
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(node.value), x - 7, y + 4);  // Draw text inside the circle

        // Set the color for the lines to black
        g.setColor(Color.BLACK);

        // Draw left and right children recursively
        if (node.left != null) {
            g.drawLine(x, y, x - xOffset, y + 50); // Draw line to left child
            drawTree(g, node.left, x - xOffset, y + 50, xOffset / 2, level + 1);  // Increase level for left child
        }
        if (node.right != null) {
            g.drawLine(x, y, x + xOffset, y + 50); // Draw line to right child
            drawTree(g, node.right, x + xOffset, y + 50, xOffset / 2, level + 1);  // Increase level for right child
        }
    }
};
        drawingPanel.setPreferredSize(new Dimension(800, 500));

        // Layout
        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
    }
    /**
     * Creates new form TreeGen
     */
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addnum = new javax.swing.JButton();
        numField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addnum.setText("ADD");
        addnum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addnumActionPerformed(evt);
            }
        });

        numField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numFieldActionPerformed(evt);
            }
        });

        jButton1.setText("REMOVE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(408, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addnum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                    .addComponent(numField))
                .addGap(54, 54, 54))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addComponent(numField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addnum)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(322, 322, 322))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
        // Get the number from numField and parse it
        int number = Integer.parseInt(numField.getText());

        // Delete the number from the BST
        bst.delete(number);

        // Clear the text field
        numField.setText("");

        // Repaint the panel to display the updated tree
        drawingPanel.repaint();

        JOptionPane.showMessageDialog(this, "Deleted " + number + " from the tree.");
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Please enter a valid integer.");
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void addnumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addnumActionPerformed
        // TODO add your handling code here:
        try {
            // Get the number from numField and parse it
            int number = Integer.parseInt(numField.getText());

            // Insert the number into the BST
            bst.insert(number);

            // Clear the text field
            numField.setText("");

            // Repaint the panel to display the updated tree
            drawingPanel.repaint();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer.");
        }
    }//GEN-LAST:event_addnumActionPerformed

    private void numFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BSTGui frame = new BSTGui();
            frame.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addnum;
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField numField;
    // End of variables declaration//GEN-END:variables
}
