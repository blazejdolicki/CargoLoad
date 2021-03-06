package Shapes;
/**
 * Class representing ParcelB
 */
public class ParcelB extends ParcelShape{
	
	protected static ShapeMaterial material = ShapeMaterial.BLUE;
	protected static int width = 2;
	protected static int height = 3;
	protected static int length = 4;
	
	protected static String name = "B";
	protected static int value = 4;
	/**
         * Construct parcel
         */
	public ParcelB() {
		super(material, name, value, width, height, length);
	}
	
	/*
	 * @see Shapes.ParcelShape#clone()
	 */
	@Override
	public ParcelB clone() {
		ParcelB clone = new ParcelB();
		clone.setOrientation(this.orientation);
		return clone;	
	}
        /**
         * @return clone with coordinates
         */
        @Override 
        public ParcelB cloneWithCoordinates(){
            ParcelB clone = new ParcelB();
            clone.setOrientation(this.orientation);
            clone.setCurrentCoordinates(this.positionParcelContainer.clone());
            return clone;
        }
}
