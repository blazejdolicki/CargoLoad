package View;

import Shapes.ParcelShape;
import Shapes.PentominoShape;
import Shapes.Shape;
import java.util.ArrayList;
/**
 * This class is used to create an arrayList with already generated solutions for containers.
 * @author Jordan
 * @version 1.0
 */
public class ShownContainers {
    private ArrayList<Shape> containedParcels;
    private ArrayList<Shape> containedPentominoes;
    private int value;
    private String name;
    /**
     * Constructor
     * @param containedParcels an arrayList with parcels needed for a solution
     * @param value value of a container
     * @param name String with information about algorithm etc.
     */
    public ShownContainers(ArrayList<Shape> containedParcels, int value, String name){
        this.containedParcels = containedParcels;
        this.value = value;
        this.name = name;
    }
    /**
     * Constructor
     * @param containedPentominoes an arrayList with pentominoes needed for a solution
     * @param value value of a container
     * @param name String with information about algorithm etc.
     */
    public ShownContainers(int value, ArrayList<Shape> containedPentominoes, String name){
        this.containedPentominoes = containedPentominoes;
        this.value = value;
        this.name = name;
    }
    /**
     * Get the arraylist with parcels.
     * @return parcels
     */
    public ArrayList<Shape> getContainedParcels(){
        return containedParcels;
    }
    /**
     * Get the arrayList with pentominoes.
     * @return pentominoes
     */
     public ArrayList<Shape> getContainedPentominoes(){
        return containedPentominoes;
    }
     /**
      * Get the value of the container.
      * @return value of container
      */
    public int getValue(){
        return value;
    }
    /**
     * Get the information about the container.
     * @return name String with data
     */
    public String getName(){
        return name;
    }
}
