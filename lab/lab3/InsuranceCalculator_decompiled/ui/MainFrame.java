// 
// Decompiled by Procyon v0.5.36
// 

package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.Component;
import javax.swing.border.Border;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.Rectangle;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JFrame;

public class MainFrame extends JFrame
{
    private JTextField textField;
    int gender;
    int claims;
    int age;
    private JComboBox comboBox;
    private JSpinner spinner;
    private JSpinner spinner_1;
    
    public MainFrame() {
        this.setResizable(false);
        this.setTitle("Insurance Premium Calculator");
        this.setSize(411, 254);
        this.getContentPane().setLayout(new BorderLayout(0, 0));
        final JPanel panel = new JPanel();
        panel.setBounds(new Rectangle(5, 5, 5, 5));
        panel.setBorder(new EtchedBorder(1, null, null));
        this.getContentPane().add(panel, "West");
        final GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 199, 0 };
        gbl_panel.rowHeights = new int[] { 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30 };
        gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);
        final JLabel lblGender = new JLabel("Gender");
        final GridBagConstraints gbc_lblGender = new GridBagConstraints();
        gbc_lblGender.anchor = 17;
        gbc_lblGender.insets = new Insets(0, 0, 5, 0);
        gbc_lblGender.gridx = 0;
        gbc_lblGender.gridy = 2;
        panel.add(lblGender, gbc_lblGender);
        (this.comboBox = new JComboBox()).setModel(new DefaultComboBoxModel<String>(new String[] { "Male", "Female" }));
        final GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.fill = 2;
        gbc_comboBox.anchor = 11;
        gbc_comboBox.insets = new Insets(0, 0, 5, 0);
        gbc_comboBox.gridx = 0;
        gbc_comboBox.gridy = 3;
        panel.add(this.comboBox, gbc_comboBox);
        final JLabel lblAge = new JLabel("Age");
        final GridBagConstraints gbc_lblAge = new GridBagConstraints();
        gbc_lblAge.anchor = 17;
        gbc_lblAge.insets = new Insets(0, 0, 5, 0);
        gbc_lblAge.gridx = 0;
        gbc_lblAge.gridy = 4;
        panel.add(lblAge, gbc_lblAge);
        (this.spinner = new JSpinner()).setModel(new SpinnerNumberModel(0, null, 150, 1));
        final GridBagConstraints gbc_spinner = new GridBagConstraints();
        gbc_spinner.fill = 2;
        gbc_spinner.insets = new Insets(0, 0, 5, 0);
        gbc_spinner.gridx = 0;
        gbc_spinner.gridy = 5;
        panel.add(this.spinner, gbc_spinner);
        final JLabel lblClaims = new JLabel("Claims");
        final GridBagConstraints gbc_lblClaims = new GridBagConstraints();
        gbc_lblClaims.anchor = 17;
        gbc_lblClaims.insets = new Insets(0, 0, 5, 0);
        gbc_lblClaims.gridx = 0;
        gbc_lblClaims.gridy = 6;
        panel.add(lblClaims, gbc_lblClaims);
        (this.spinner_1 = new JSpinner()).setPreferredSize(new Dimension(60, 20));
        this.spinner_1.setModel(new SpinnerNumberModel(0, null, 50, 1));
        final GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
        gbc_spinner_1.fill = 2;
        gbc_spinner_1.insets = new Insets(0, 0, 5, 0);
        gbc_spinner_1.gridx = 0;
        gbc_spinner_1.gridy = 7;
        panel.add(this.spinner_1, gbc_spinner_1);
        final JButton btnCalculate = new JButton("Calculate");
        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                MainFrame.this.gender = MainFrame.this.comboBox.getSelectedIndex();
                MainFrame.this.claims = (int)MainFrame.this.spinner_1.getValue();
                MainFrame.this.age = (int)MainFrame.this.spinner.getValue();
                MainFrame.this.calculate();
            }
        });
        final GridBagConstraints gbc_btnCalculate = new GridBagConstraints();
        gbc_btnCalculate.anchor = 13;
        gbc_btnCalculate.insets = new Insets(0, 0, 5, 0);
        gbc_btnCalculate.gridx = 0;
        gbc_btnCalculate.gridy = 8;
        panel.add(btnCalculate, gbc_btnCalculate);
        final JPanel panel_1 = new JPanel();
        this.getContentPane().add(panel_1, "East");
        final JLabel lblNewLabel = new JLabel("Premium: ");
        panel_1.add(lblNewLabel);
        (this.textField = new JTextField()).setEditable(false);
        panel_1.add(this.textField);
        this.textField.setColumns(10);
    }
    
    public void calculate() {
        if (this.gender == 0 && this.age < 25 && this.claims == 0) {
            this.getTextField().setText("$1500");
        }
        else if (this.gender == 0 && this.age >= 25 && this.age < 65 && this.claims == 0) {
            this.getTextField().setText("$1000");
        }
        else if (this.gender == 1 && this.age < 65 && this.claims == 0) {
            this.getTextField().setText("$750");
        }
        else if (this.age >= 65 && this.claims == 0) {
            this.getTextField().setText("$1500");
        }
        else if (this.claims >= 1) {
            this.getTextField().setText("$3000");
        }
        else {
            this.getTextField().setText("ERROR");
        }
    }
    
    public JTextField getTextField() {
        return this.textField;
    }
    
    public JComboBox getComboBox() {
        return this.comboBox;
    }
    
    public JSpinner getSpinner() {
        return this.spinner;
    }
    
    public JSpinner getSpinner_1() {
        return this.spinner_1;
    }
}
