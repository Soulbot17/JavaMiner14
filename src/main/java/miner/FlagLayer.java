package miner;

class FlagLayer
{
    private Matrix flagMap;
    private int countOfClosedBoxes;

    void start()
    {
        flagMap = new Matrix(Box.CLOSED);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }

    Box get(Coordinates coordinates)
    {
        return flagMap.get(coordinates);
    }

    public void toggleFlagedToBox(Coordinates coordinates)
    {
        switch (flagMap.get(coordinates))
        {
            case FLAGED:
                setClosedToBox(coordinates);
                break;
            case CLOSED:
                setFlagedToBox(coordinates);
                break;
            default:
                break;
        }
    }

    void setOpenedToBox(Coordinates coordinates)
    {
        flagMap.set(coordinates, Box.OPENED);
    }

    private void setFlagedToBox(Coordinates coordinates)
    {
        flagMap.set(coordinates, Box.FLAGED);
    }

    private void setClosedToBox(Coordinates coordinates)
    {
        flagMap.set(coordinates, Box.CLOSED);
        countOfClosedBoxes--;
    }

    int getCountOfClosedBoxes()
    {
        return countOfClosedBoxes;
    }

    void setBombedToBox(Coordinates coordinates)
    {
        flagMap.set(coordinates, Box.BOMBED);
    }

    void setOpenedToClosedBox(Coordinates coordinate)
    {
        if (flagMap.get(coordinate) == Box.CLOSED)
            flagMap.set(coordinate, Box.OPENED);
    }

    void setNoBombToFlaggedSafeBox(Coordinates coordinate)
    {
        if (flagMap.get(coordinate) == Box.FLAGED)
            flagMap.set(coordinate, Box.NOBOMB);
    }

    void setOpenedToClosedBoxesAroundNumber(Coordinates coordinates)
    {

    }
}
