package miner;

class Matrix
{
    private Box[][] matrix;

    Matrix(Box defaultBox)
    {
        matrix = new Box[Ranges.getSize().x][Ranges.getSize().y];
        for (Coordinates coordinates : Ranges.getAllCoordinates()) {
            matrix[coordinates.x][coordinates.y] = defaultBox;
        }
    }

    Box get(Coordinates coordinates) {
        if (Ranges.inRange(coordinates)) {
            return matrix[coordinates.x][coordinates.y];
        } else return null;
    }

    void set(Coordinates coordinates, Box box) {
        if (Ranges.inRange(coordinates))
        {
            matrix[coordinates.x][coordinates.y] = box;
        } else return;
    }

}
