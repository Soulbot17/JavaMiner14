package miner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ranges
{
    private static Coordinates size;
    private static List<Coordinates> allCoordinates;
    private static Random random = new Random();

    public static void setSize(Coordinates _size)
    {
        size = _size;
        allCoordinates = new ArrayList<>();
        for (int y = 0; y < size.y; y++)
        {
            for (int x = 0; x < size.x; x++)
            {
                allCoordinates.add(new Coordinates(x, y));
            }
        }
    }

    public static Coordinates getSize()
    {
        return size;
    }

    public static List<Coordinates> getAllCoordinates()
    {
        return allCoordinates;
    }

    static boolean inRange(Coordinates coordinates)
    {
        return coordinates.x >= 0 && coordinates.x < size.x &&
                coordinates.y >= 0 && coordinates.y < size.y;
    }

    static Coordinates getRandomCoordinates()
    {
        return new Coordinates(random.nextInt(size.x), random.nextInt(size.y));
    }

    static ArrayList<Coordinates> getCoordsAround(Coordinates coordinates)
    {
        Coordinates around;
        ArrayList<Coordinates> list = new ArrayList<>();
        for (int x = coordinates.x - 1; x <= coordinates.x + 1; x++)
            for (int y = coordinates.y - 1; y <= coordinates.y + 1; y++)
                if (inRange(around = new Coordinates(x, y)))
                    if (!around.equals(coordinates))
                        list.add(around);
        return list;
    }
}
