// 
// Decompiled by Procyon v0.5.36
// 

package ui;

import javax.swing.JLabel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import java.awt.LayoutManager;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import javax.swing.JTextField;
import javax.swing.JFrame;

public class MainFrame extends JFrame
{
    private JTextField textField;
    boolean a;
    boolean b;
    boolean c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;
    boolean i;
    boolean j;
    
    public MainFrame() {
        this.setResizable(false);
        this.setTitle("Boiler Controller");
        final boolean b = false;
        this.j = b;
        this.i = b;
        this.h = b;
        this.g = b;
        this.f = b;
        this.e = b;
        this.d = b;
        this.c = b;
        this.b = b;
        this.a = b;
        this.setSize(229, 357);
        this.getContentPane().setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC }, new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC }));
        final JCheckBox chckbxA = new JCheckBox("A");
        chckbxA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                MainFrame.this.a = ((JCheckBox)arg0.getSource()).isSelected();
                MainFrame.this.check_status();
            }
        });
        this.getContentPane().add(chckbxA, "2, 2");
        final JCheckBox chckbxB = new JCheckBox("B");
        chckbxB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                MainFrame.this.b = ((JCheckBox)arg0.getSource()).isSelected();
                MainFrame.this.check_status();
            }
        });
        this.getContentPane().add(chckbxB, "2, 4");
        final JLabel lblBoilerStatus = new JLabel("Boiler Status:");
        this.getContentPane().add(lblBoilerStatus, "6, 4");
        final JCheckBox chckbxC = new JCheckBox("C");
        chckbxC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                MainFrame.this.c = ((JCheckBox)arg0.getSource()).isSelected();
                MainFrame.this.check_status();
            }
        });
        this.getContentPane().add(chckbxC, "2, 6");
        (this.textField = new JTextField()).setEditable(false);
        this.getContentPane().add(this.textField, "6, 6, left, default");
        this.textField.setColumns(10);
        final JCheckBox chckbxD = new JCheckBox("D");
        chckbxD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                MainFrame.this.d = ((JCheckBox)arg0.getSource()).isSelected();
                MainFrame.this.check_status();
            }
        });
        this.getContentPane().add(chckbxD, "2, 8");
        final JCheckBox chckbxE = new JCheckBox("E");
        chckbxE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                MainFrame.this.e = ((JCheckBox)arg0.getSource()).isSelected();
                MainFrame.this.check_status();
            }
        });
        this.getContentPane().add(chckbxE, "2, 10");
        final JCheckBox chckbxF = new JCheckBox("F");
        chckbxF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                MainFrame.this.f = ((JCheckBox)arg0.getSource()).isSelected();
                MainFrame.this.check_status();
            }
        });
        this.getContentPane().add(chckbxF, "2, 12");
        final JCheckBox chckbxG = new JCheckBox("G");
        chckbxG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                MainFrame.this.g = ((JCheckBox)arg0.getSource()).isSelected();
                MainFrame.this.check_status();
            }
        });
        this.getContentPane().add(chckbxG, "2, 14");
        final JCheckBox chckbxH = new JCheckBox("H");
        chckbxH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                MainFrame.this.h = ((JCheckBox)arg0.getSource()).isSelected();
                MainFrame.this.check_status();
            }
        });
        this.getContentPane().add(chckbxH, "2, 16");
        final JCheckBox chckbxI = new JCheckBox("I");
        chckbxI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                MainFrame.this.i = ((JCheckBox)arg0.getSource()).isSelected();
                MainFrame.this.check_status();
            }
        });
        this.getContentPane().add(chckbxI, "2, 18");
        final JCheckBox chckbxJ = new JCheckBox("J");
        chckbxJ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                MainFrame.this.j = ((JCheckBox)arg0.getSource()).isSelected();
                MainFrame.this.check_status();
            }
        });
        this.getContentPane().add(chckbxJ, "2, 20");
        this.textField.setText("OK");
    }
    
    public JTextField getTextField() {
        return this.textField;
    }
    
    public void check_status() {
        final boolean one = this.a & this.b;
        final boolean two = one | this.c | this.d | this.e;
        final boolean three = this.d | this.e | this.f | this.g | this.h;
        final boolean six = two & three;
        final boolean seven = six | three;
        final boolean eight = this.i | this.j;
        final boolean status = seven | eight;
        if (status) {
            this.getTextField().setText("FAIL");
        }
        else {
            this.getTextField().setText("OK");
        }
    }
}
