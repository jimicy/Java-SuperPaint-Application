import java.awt.Color;
import java.awt.Graphics;

/**
 * This is an abstract class with an abstract draw method. It inherits from MyShape
 * and contains methods needed for drawing ovals and rectangles. It also contains an instance variable called fill.
 */
abstract class MyBoundedShape extends MyShape
{
    private boolean fill; //boolean variable that determines whether the shape is filled or not
    
    /**
     * public constructor that takes no parameters and calls the no parameter constructor for Myshape.
     * It sets fill to false.
     */
    public MyBoundedShape()
    {
        super();
        fill=false;
    }
    
    /**
     * public overloaded constructor that takes int coordinates and a boolean for fill.
     * It passes the coordinates and color into the constructor for Myshape and assigns
     * the fill to an instance variable fill.
     */
    public MyBoundedShape(int x1, int y1, int x2, int y2, Color color, boolean fill)
    {
        super(x1, y1, x2, y2, color);
        this.fill=fill;
    }
    
    //Mutator methods
    
    /**
     * set fill
     */
    public void setFill(boolean fill)
    {
        this.fill=fill;
    }
    
    /**
     * gets the upper left x
     */
    public int getUpperLeftX()
    {
        return Math.min(getX1(),getX2());
    }
    
    /**
     * gets the upper left y
     */
    public int getUpperLeftY()
    {
        return Math.min(getY1(),getY2());
    }
    
    /**
     * gets width
     */
    public int getWidth()
    {
        return Math.abs(getX1()-getX2());
    }
    
    //Accessor methods
    
    /**
     * gets height
     */
    public int getHeight()
    {
        return Math.abs(getY1()-getY2());
    }
    
    /**
     * return fill
     */
    public boolean getFill()
    {
        return fill;
    }
    
    /**
     * Abstract method for drawing the shape that must be overriden
     */
    abstract public void draw( Graphics g );
} // end class MyBoundedShape