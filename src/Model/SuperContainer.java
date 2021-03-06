package Model;

import Shapes.*;

import java.util.ArrayList;
import java.util.Collections;
/**
 * A general class for our solver classes
 * @author Blazej, Samuel, Yvar, Stijn
 */
public abstract class SuperContainer {

    public static int CONTAINERX = 5;
    public static int CONTAINERY = 8;
    public static int CONTAINERZ = 33;
    protected int containerX = CONTAINERX;
    protected int containerY = CONTAINERY;
    protected int containerZ = CONTAINERZ;
    protected ArrayList<Shape> parcelList;

    protected ArrayList<Shape> containedParcels = new ArrayList<>();

    protected int[] remainingParcelsEachType={1000,1000,1000};
    protected int totalRemainingParcels = remainingParcelsEachType[0] + remainingParcelsEachType[1] + remainingParcelsEachType[2];
    protected int AmountTypeA = 100;
    protected int AmountTypeB = 100;
    protected int AmountTypeC = 100;
    protected int totalGivenParcels = 300;
    protected int nonEmptyParcelType = 0;
    protected boolean[] triedParcel = new boolean[3];
    protected boolean finish = false;
    protected int delay;
    protected Subspace subspace;

    /**
     * Sorts the parcelList from the highest to the lowest value.
     * @param givenParcels
     * @return
     */
    public ArrayList<Shape> orderParcelListByValue(ArrayList<Shape> givenParcels) {
        ArrayList<Integer> parcelValues = new ArrayList<>();
        ArrayList<Shape> orderedParcelListbyValue = new ArrayList<>();
        for(int i = 0; i < givenParcels.size(); i++){
            if(givenParcels.get(i) instanceof ParcelShape){
                ParcelShape someShape = ((ParcelShape)(givenParcels.get(i))).clone();
                parcelValues.add(someShape.getValue());
            }
            else if(givenParcels.get(i) instanceof PentominoShape){
                PentominoShape someShape = ((PentominoShape)(givenParcels.get(i))).clone();
                parcelValues.add(someShape.getValue());
            }

        }
        Collections.sort(parcelValues, Collections.reverseOrder());
        int j=0;
        int i=0;
        while(i<givenParcels.size()&&j<parcelValues.size()){
            boolean find = false;
            if(givenParcels.get(i).getValue()==parcelValues.get(j)){

                j++;
                if(givenParcels.get(i) instanceof ParcelShape){
                    ParcelShape otherShape = ((ParcelShape)(givenParcels.get(i))).clone();
                    orderedParcelListbyValue.add(otherShape);
                }
                else if(givenParcels.get(i) instanceof PentominoShape){
                    PentominoShape otherShape = ((PentominoShape)(givenParcels.get(i))).clone();
                    orderedParcelListbyValue.add(otherShape);
                    System.out.println(otherShape.getValue());
                }
                find=true;
            }
            if(find){
                i=0;
            }
            else{
                i++;
            }
        }
        return orderedParcelListbyValue;
    }

    /**
     * Sorts the parcelList from the highest to the lowest value density.
     * @param givenParcels
     */
    public ArrayList<Shape> orderParcelListByRatio(ArrayList<Shape> givenParcels) {
        ArrayList<Double> parcelRatios = new ArrayList<>();
        ArrayList<Shape> orderedParcelListbyRatio = new ArrayList<>();
        for(int i = 0; i < givenParcels.size(); i++) {
            if (givenParcels.get(i) instanceof ParcelShape){

                ParcelShape someShape = ((ParcelShape)givenParcels.get(i)).clone();
                parcelRatios.add(someShape.getRatio());

            }
            if (givenParcels.get(i) instanceof PentominoShape){
                PentominoShape helpShape = (PentominoShape)givenParcels.get(i);
                PentominoShape someShape = helpShape.clone();
                parcelRatios.add(someShape.getRatio());
                System.out.println(someShape.getRatio());
            }
        }

        System.out.println();
        Collections.sort(parcelRatios, Collections.reverseOrder());
        System.out.println("Here"+parcelRatios.get(0));
        System.out.println("Size "+givenParcels.size());
        for(int j = 0, i = 0; i < givenParcels.size() && j < parcelRatios.size(); i++){
            System.out.println("j "+j);

            System.out.println("two"+parcelRatios.get(j));
            System.out.println("i "+i);
            System.out.println("One"+givenParcels.get(i).getRatio());
            System.out.println();
            if(givenParcels.get(i) instanceof ParcelShape){
                ParcelShape a = (ParcelShape) givenParcels.get(i);
                if(a.getRatio()  == parcelRatios.get(j)){
                    j++;
                    ParcelShape otherShape = a.clone();
                    orderedParcelListbyRatio.add(otherShape);
                    System.out.println(a.getRatio());
                    i = 0;
                }
            }
            else if(givenParcels.get(i) instanceof PentominoShape){
                PentominoShape a = (PentominoShape) givenParcels.get(i);

                if(a.getRatio()  == parcelRatios.get(j)){
                    j++;
                    PentominoShape otherShape = a.clone();
                    orderedParcelListbyRatio.add(otherShape);
                    i = -1;
                }
            }

        }
        return orderedParcelListbyRatio;

    }
    /**
     * Sets the parcels that are needed for solution
     * @param newContainedParcels 
     */
    public void setContainedParcels(ArrayList<Shape> newContainedParcels) {
        containedParcels = newContainedParcels;
    }
    /**
     * Set the list of parcels we can choose from for finding a solution
     * @param parcelList 
     */
    public void setParcelList(ArrayList<Shape> parcelList) {
        this.parcelList = parcelList;
    }
    /**
     * Set amount of parcels of each type that can be used for finding a solution
     * @param nrOfA
     * @param nrOfB
     * @param nrOfC 
     */
    public void setAmountOfParcels(int nrOfA, int nrOfB, int nrOfC) {
        AmountTypeA = nrOfA;
        AmountTypeB = nrOfB;
        AmountTypeC = nrOfC;
        totalGivenParcels = nrOfA + nrOfB + nrOfC;
        for(int parcelTypeIndex=0;parcelTypeIndex<parcelList.size();parcelTypeIndex++){
            if(parcelList.get(parcelTypeIndex) instanceof ParcelA){
                remainingParcelsEachType[parcelTypeIndex] = nrOfA;
            }
            if(parcelList.get(parcelTypeIndex) instanceof ParcelB){
                remainingParcelsEachType[parcelTypeIndex] = nrOfB;
            }
            if(parcelList.get(parcelTypeIndex) instanceof ParcelC){
                remainingParcelsEachType[parcelTypeIndex] = nrOfC;
            }
        }

    }
    /**
     * A default "infinite amount" parcels
     */
    public void setAmountOfParcels() {
        remainingParcelsEachType = new int[]{1000,1000,1000};
    }

    /**
     * Sets the amount of time after which the method is stopped and the best result so far is returned.
     * @param newDelay
     */
    public void setDelay(int newDelay){
        delay = newDelay;
    }

    /**
     * @return parcels in the container
     */
    public ArrayList<Shape> getContainedParcels() {
        return containedParcels;
    }
    /**
     * @return types of parcels
     */
    public ArrayList<Shape> getParcelList() {
        return parcelList;
    }
    /**
     * Print shapes in the container
     */
    public void printContainedShapes(){

        for(int  i = 0; i < containedParcels.size(); i++){
            System.out.println("Parcel: " + i);
            ParcelShape parcel = (ParcelShape) containedParcels.get(i);
            int z = parcel.getPosition().getZ();
            int y = parcel.getPosition().getY();
            int x = parcel.getPosition().getX();

            for(int zCoord = z; zCoord < z + parcel.getShapeVector().z; zCoord++){
                for(int yCoord = y; yCoord < y + parcel.getShapeVector().y; yCoord++){
                    for(int xCoord = x; xCoord < x + parcel.getShapeVector().x; xCoord++){
                        System.out.println("X: " + xCoord + " Y: " + yCoord + " Z: " + zCoord);
                    }
                }
            }
        }
    }
    /**
     * @return depth
     */
    public int getContainerZ(){
        return containerZ;
    }

    /**
     * @return height
     */
    public int getContainerY(){
        return containerY;
    }

    /**
     * @return width
     */
    public int getContainerX(){
        return containerX;
    }
}
