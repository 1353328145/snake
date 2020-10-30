package jesing;



import jesing.view.MyGameFrame;

import javax.swing.*;

public class Starter {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MyGameFrame::new);
    }
}
