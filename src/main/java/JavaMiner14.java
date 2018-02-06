import miner.Box;
import miner.Coordinates;
import miner.Game;
import miner.Ranges;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JavaMiner14 extends JFrame
{
    private Game game;

    private JPanel panel;
    private JLabel label;

    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;
    private final int IMAGE_SIZE = 50;

    public static void main(String[] args)
    {
        new JavaMiner14();
    }

    private JavaMiner14()
    {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        Ranges.setSize(new Coordinates(COLS, ROWS));
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }

    private void initLabel()
    {
        label = new JLabel("Welcome!");
        add(label, BorderLayout.SOUTH);
    }

    private void initPanel()
    {
        panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                for (Coordinates coordinates : Ranges.getAllCoordinates())
                {
                    g.drawImage((Image) game.getBox(coordinates).image,
                            coordinates.x * IMAGE_SIZE, coordinates.y * IMAGE_SIZE, this);
                }
            }
        };

        panel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coordinates coordinates = new Coordinates(x, y);
                if (e.getButton() == MouseEvent.BUTTON1)
                    game.pressLeftButton(coordinates);
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton(coordinates);
                if (e.getButton() == MouseEvent.BUTTON2)
                    game.start();
                label.setText(getMessage());
                panel.repaint();
            }
        });

        panel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE, Ranges.getSize().y * IMAGE_SIZE));
        add(panel);
    }

    private String getMessage()
    {
        switch (game.getState()) {
            case PLAYED: return "Game in progress";
            case BOMBED: return "YOT LOSE! HA-HA! BIG-BADA-BOOM";
            case WINNER: return "CONGRATULATIONS!";
            default: return "Welcome!";
        }
    }

    private void initFrame()
    {
        setIconImage(getImage("icon"));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Miner 14");
        setResizable(false);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

    private void setImages()
    {
        for (Box box : Box.values())
            box.image = getImage(box.name());
    }

    private Image getImage(String name)
    {
        String filename = "img/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename)); //!
        return icon.getImage();
    }
}
