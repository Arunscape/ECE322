import javax.swing.SwingUtilities;
import ui.MainFrame;

// 
// Decompiled by Procyon v0.5.36
// 

public class Main
{
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final MainFrame mf = new MainFrame();
                mf.setVisible(true);
            }
        });
    }
}
