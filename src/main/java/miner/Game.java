package miner;

public class Game
{
    private BombLayer bomb;
    private FlagLayer flag;
    private GameState state;

    public GameState getState()
    {
        return state;
    }

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
        state = GameState.PLAYED;
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
        if (gameOver()) return;
        openBox(coordinates);
        checkWinner();
    }

    public void pressRightButton(Coordinates coordinates)
    {
        if (gameOver()) return;
        flag.toggleFlagedToBox(coordinates);
    }

    private boolean gameOver()
    {
        if (state == GameState.PLAYED)
            return false;
        start();
        return true;
    }

    private void checkWinner()
    {
        if (state == GameState.PLAYED)
            if (flag.getCountOfClosedBoxes() == bomb.getTotalBombs())
                state = GameState.WINNER;
    }

    private void openBox(Coordinates coordinates)
    {
        switch (flag.get(coordinates))
        {
            case OPENED:
                setOpenedToClosedBoxesAroundNumber(coordinates);
                return;
            case FLAGED:
                return;
            case CLOSED:
                switch (bomb.get(coordinates))
                {
                    case ZERO:
                        openBoxesAround(coordinates);
                        return;
                    case BOMB:
                        openBombs(coordinates);
                        return;
                    default:
                        flag.setOpenedToBox(coordinates);
                }
        }
    }

    void setOpenedToClosedBoxesAroundNumber(Coordinates coordinates)
    {
        if (bomb.get(coordinates) != Box.BOMB)
            if (flag.getCountOfFlagedBoxesAround(coordinates) == bomb.get(coordinates).getNumber())
                for (Coordinates around : Ranges.getCoordsAround(coordinates))
                    if (flag.get(around) == Box.CLOSED)
                        openBox(around);
    }

    private void openBombs(Coordinates bombed)
    {
        state = GameState.BOMBED;
        flag.setBombedToBox(bombed);
        for (Coordinates coordinate : Ranges.getAllCoordinates())
        {
            if (bomb.get(coordinate) == Box.BOMB)
            {
                flag.setOpenedToClosedBox(coordinate);
            } else flag.setNoBombToFlaggedSafeBox(coordinate);
        }
    }

    private void openBoxesAround(Coordinates coordinates)
    {
        flag.setOpenedToBox(coordinates);
        for (Coordinates around : Ranges.getCoordsAround(coordinates))
            openBox(around);
    }


}
