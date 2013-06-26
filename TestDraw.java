/**
 * Paint Gui Assignment
 * Name: Jimmy Wang
 * Date: March 18 2013
 * 
 * Description: 
 * This program use inheritance and implementation to create a paint program.
 * The paint program lets you select a color, a shape (line, rectangle,oval) and whether or not the shape is filled.
 * You use the mouse to drag and draw a shape, the shape's coordinate re-adjust automatically with mouse's position
 * as your dragging. 
 * 
 * There is an undo button and you can undo as many shapes as you like and a redo button to redo them as well.
 * However, if you draw a new shape you can no longer redo.
 * There is also a clear button to clear the screen and you cannot undo that.
 */
public class TestDraw
{
    public static void main( String args[] )
    {
        DrawFrame paintGui = new DrawFrame(); //initalize DrawFrame object called paintGui     
    } // end main
} // end class TestDraw