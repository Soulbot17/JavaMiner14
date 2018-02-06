package miner;

public class Game
{
    private BombLayer bomb;
    private FlagLayer flag;

    public Game(int cols, int rows, int bombs)
    {
        Ranges.setSize(new Coordinates(cols, rows));
        bomb = new BombLayer(bombs);
        flag = new FlagLayer();
    }

    public void start()
    {
        bomb.start();
        flag.start();
    }

    public Box getBox(Coordinates coordinates)
    {
        if (flag.get(coordinates) == Box.OPENED)
            return bomb.get(coordinates);
        else
            return flag.get(coordinates);
    }

    public void pressLeftButton(Coordinates coordinates)
    {
        flag.setOpenedToBox(coordinates);
    }

    public void pressRightButton(Coordinates coordinates)
    {
        flag.toggleFlagedToBox(coordinates);
    }
}
