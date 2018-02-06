package miner;

class FlagLayer
{
    private Matrix flagMap;

    void start()
    {
        flagMap = new Matrix(Box.CLOSED);
    }

    Box get(Coordinates coordinates)
    {
        return flagMap.get(coordinates);
    }

    public void toggleFlagedToBox(Coordinates coordinates)
    {
        switch (flagMap.get(coordinates)) {
            case FLAGED: setClosedToBox(coordinates); break;
            case CLOSED: setFlagedToBox(coordinates); break;
            default: break;
        }
    }

    void setOpenedToBox(Coordinates coordinates)
    {
        flagMap.set(coordinates, Box.OPENED);
    }

    public void setFlagedToBox(Coordinates coordinates)
    {
        flagMap.set(coordinates, Box.FLAGED);
    }

    public void setClosedToBox(Coordinates coordinates) {
        flagMap.set(coordinates, Box.CLOSED);
    }

}
