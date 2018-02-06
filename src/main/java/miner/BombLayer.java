package miner;

class BombLayer
{
    private Matrix bombMap;
    private int totalBombs;

    public BombLayer(int totalBombs)
    {
        this.totalBombs = totalBombs;
        fixBombsCount();
    }

    void start()
    {
        bombMap = new Matrix(Box.ZERO);
        for (int i = 0; i < totalBombs; i++)
            placeBomb();
    }

    private void fixBombsCount() {
        int maxBombs = (Ranges.getSize().x * Ranges.getSize().y) /2; // Ranges.getSize().x * Ranges.getSize().y /2
        if (totalBombs > maxBombs)
            totalBombs = maxBombs;
    }

    private void placeBomb()
    {
        while (true) {
            Coordinates coordinates = Ranges.getRandomCoordinates();
            if (Box.BOMB == bombMap.get(coordinates))
                continue;
            bombMap.set(coordinates, Box.BOMB);
            incNumberAroundBomb(coordinates);
            break;
        }
    }

    private void incNumberAroundBomb(Coordinates coordinates) {
        for (Coordinates around : Ranges.getCoordsAround(coordinates))
            if (Box.BOMB != bombMap.get(around))
            bombMap.set(around, bombMap.get(around).nextNumberBox());
    }

    Box get(Coordinates coordinates)
    {
        return bombMap.get(coordinates);
    }

    public int getTotalBombs()
    {
        return totalBombs;
    }

}
