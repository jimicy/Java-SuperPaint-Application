import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

/**
 * This class handles mouse events and uses them to draw shapes.
 * It contains a dynamic stack myShapes which is the shapes drawn on the panel.
 * It contains a dynamic stack clearedShape which is the shapes cleared from the panel.
 * It has many variables for the current shape [type, variable to store shape object, color, fill].
 * It contains a JLabel called statusLabel for the mouse coordinates
 * It has mutator methods for currentShapeType, currentShapeColor and currentShapeFilled.
 * It has methods for undoing, redoing and clearing shapes.
 * It has a private inner class MouseHandler which extends MouseAdapter and 
 * handles mouse and mouse motion events used for drawing the current shape.
 */

public class DrawPanel extends JPanel
{
    private LinkedList<MyShape> myShapes; //dynamic stack of shapes
    private LinkedList<MyShape> clearedShapes; //dynamic stack of cleared shapes from undo
    
    //current Shape variables
    private int currentShapeType; //0 for line, 1 for rect, 2 for oval
    private MyShape currentShapeObject; //stores the current shape object
    private Color currentShapeColor; //current shape color
    private boolean currentShapeFilled; //determine whether shape is filled or not
    
    JLabel statusLabel; //status label for mouse coordinates
    
    /**
     * This constructor initializes the dynamic stack for myShapes and clearedShapes.
     * It sets the current shape variables to default values.
     * It initalizes statusLabel from JLabel passed in.
     * Sets up the panel and adds event handling for mouse events.
     */
    public DrawPanel(JLabel statusLabel){
        
        myShapes = new LinkedList<MyShape>(); //initialize myShapes dynamic stack
        clearedShapes = new LinkedList<MyShape>(); //initialize clearedShapes dynamic stack
        
        //Initialize current Shape variables
        currentShapeType=0;
        currentShapeObject=null;
        currentShapeColor=Color.BLACK;
        currentShapeFilled=false;
        
        this.statusLabel = statusLabel; //Initialize statusLabel
        
        setLayout(new BorderLayout()); //sets layout to border layout; default is flow layout
        setBackground( Color.WHITE ); //sets background color of panel to white
        add( statusLabel, BorderLayout.SOUTH );  //adds a statuslabel to the south border
        
        // event handling for mouse and mouse motion events
        MouseHandler handler = new MouseHandler();                                    
        addMouseListener( handler );
        addMouseMotionListener( handler ); 
    }
    
    /**
     * Calls the draw method for the existing shapes.
     */
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        
        // draw the shapes
        ArrayList<MyShape> shapeArray=myShapes.getArray();
        for ( int counter=shapeArray.size()-1; counter>=0; counter-- )
           shapeArray.get(counter).draw(g);
        
        //draws the current Shape Object if it is not null
        if (currentShapeObject!=null)
            currentShapeObject.draw(g);
    }
    
    //Mutator methods for currentShapeType, currentShapeColor and currentShapeFilled
    
    /**
     * Sets the currentShapeType to type (0 for line, 1 for rect, 2 for oval) passed in.
     */
    public void setCurrentShapeType(int type)
    {
        currentShapeType=type;
    }
    
    /**
     * Sets the currentShapeColor to the Color object passed in.
     * The Color object contains the color for the current shape.
     */
    public void setCurrentShapeColor(Color color)
    {
        currentShapeColor=color;
    }
    
    /**
     * Sets the boolean currentShapeFilled to boolean filled passed in. 
     * If filled=true, current shape is filled. 
     * If filled=false, current shape is not filled.
     */
    public void setCurrentShapeFilled(boolean filled)
    {
        currentShapeFilled=filled;
    }
    
    
    /**
     * Clear the last shape drawn and calls repaint() to redraw the panel if clearedShapes is not empty
     */
    public void clearLastShape()
    {
        if (! myShapes.isEmpty())
        {
            clearedShapes.addFront(myShapes.removeFront());
            repaint();
        }
    }
    
    /**
     * Redo the last shape cleared if clearedShapes is not empty
     * It calls repaint() to redraw the panel.
     */
    public void redoLastShape()
    {
        if (! clearedShapes.isEmpty())
        {
            myShapes.addFront(clearedShapes.removeFront());
            repaint();
        }
    }
    
    /**
     * Remove all shapes in current drawing. Also makes clearedShapes empty since you cannot redo after clear.
     * It called repaint() to redraw the panel.
     */
    public void clearDrawing()
    {
        myShapes.makeEmpty();
        clearedShapes.makeEmpty();
        repaint();
    }
    
    /**
     * Private inner class that implements MouseAdapter and does event handling for mouse events.
     */
    private class MouseHandler extends MouseAdapter 
    {
        /**
         * When mouse is pressed draw a shape object based on type, color and filled.
         * X1,Y1 & X2,Y2 coordinate for the drawn shape are both set to the same X & Y mouse position.
         */
        public void mousePressed( MouseEvent event )
        {
            switch (currentShapeType) //0 for line, 1 for rect, 2 for oval
            {
                case 0:
                    currentShapeObject= new MyLine( event.getX(), event.getY(), 
                                                   event.getX(), event.getY(), currentShapeColor);
                    break;
                case 1:
                    currentShapeObject= new MyRectangle( event.getX(), event.getY(), 
                                                        event.getX(), event.getY(), currentShapeColor, currentShapeFilled);
                    break;
                case 2:
                    currentShapeObject= new MyOval( event.getX(), event.getY(), 
                                                   event.getX(), event.getY(), currentShapeColor, currentShapeFilled);
                    break;
                    
            }// end switch case
        } // end method mousePressed
        
        /**
         * When mouse is released set currentShapeObject's x2 & y2 to mouse pos.
         * Then addFront currentShapeObject onto the myShapes dynamic Stack 
         * and set currentShapeObject to null [clearing current shape object since it has been drawn].
         * Lastly, it clears all shape objects in clearedShapes [because you cannot redo after a new drawing]
         * and calls repaint() to redraw panel.
         */
        public void mouseReleased( MouseEvent event )
        {
            //sets currentShapeObject x2 & Y2
            currentShapeObject.setX2(event.getX());
            currentShapeObject.setY2(event.getY());
            
            myShapes.addFront(currentShapeObject); //addFront currentShapeObject onto myShapes
            
            currentShapeObject=null; //sets currentShapeObject to null
            clearedShapes.makeEmpty(); //clears clearedShapes
            repaint();
            
        } // end method mouseReleased
        
        /**
         * This method gets the mouse pos when it is moving and sets it to statusLabel.
         */
        public void mouseMoved( MouseEvent event )
        {
            statusLabel.setText(String.format("Mouse Coordinates X: %d Y: %d",event.getX(),event.getY()));
        } // end method mouseMoved
        
        /**
         * This method gets the mouse position when it is dragging and sets x2 & y2 of current shape to the mouse pos
         * It also gets the mouse position when it is dragging and sets it to statusLabel
         * Then it calls repaint() to redraw the panel
         */
        public void mouseDragged( MouseEvent event )
        {
            //sets currentShapeObject x2 & Y2
            currentShapeObject.setX2(event.getX());
            currentShapeObject.setY2(event.getY());
            
            //sets statusLabel to current mouse position
            statusLabel.setText(String.format("Mouse Coordinates X: %d Y: %d",event.getX(),event.getY()));
            
            repaint();
            
        } // end method mouseDragged
        
    }// end MouseHandler
    
} // end class DrawPanel