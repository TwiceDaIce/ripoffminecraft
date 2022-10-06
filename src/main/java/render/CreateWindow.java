package render;

import javax.swing.*;
import java.awt.*;
//installer
@Deprecated // using glfw instead of jframe, but this exists primarily as a backup (maybe for launcher as no 3d?)
public class CreateWindow extends JFrame {
    public static JFrame createWindow (int scaleX, int scaleY, boolean setCenter) {
        return new CreateWindow().makeWindow(scaleX, scaleY, setCenter);
    }

    public JFrame makeWindow(int x, int y, boolean setCenter) {
        setSize(x, y);
        add(new JLabel("JFrame set to center of the screen", SwingConstants.CENTER), BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Test Frame");
        if (setCenter) {
            setLocationRelativeTo(null);
        }
        setVisible(true);
        return this;
    }
}
