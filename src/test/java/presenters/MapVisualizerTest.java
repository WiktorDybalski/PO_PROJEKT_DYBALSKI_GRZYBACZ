package presenters;

import model.maps.GlobeMap;
import model.utils.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MapVisualizerTest {

    private MapVisualizer mapVisualiser;

    private GlobeMap map;

    @BeforeEach
    void setUp() {
        map = new GlobeMap(10, 10, 10, 0);
        ArrayList<Integer> genes = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            genes.add(i);
        }
        Genotype genotype = new Genotype(genes);

        map.placeAnimal(new Animal(new Vector2d(5, 5), 1,  genotype, 0, 10), new Vector2d(5, 5));
        map.placeAnimal(new Animal(new Vector2d(5, 5), 2,  genotype, 0, 10), new Vector2d(5, 6));
        map.placeAnimal(new Animal(new Vector2d(5, 5), 3,  genotype, 0, 10), new Vector2d(5, 5));

        map.placePlant(new Plant( new Vector2d(5, 5), 1, false,0), new Vector2d(5, 7));

    }

    @Test
    void drawTest() {
        System.out.println(map.toString());
        for (Tile tile : map.getMapTiles().values()) {
            System.out.println(tile.getPosition() + " " + tile.getObjects());
        }
    }
}
