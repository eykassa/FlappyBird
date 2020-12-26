import javax.swing.*;
import java.awt.*;
/**
 *BirdComponet is class that load an image icon and uses some methods to change the value of the y position of the bird
 *and make it look like the bird is jumping.
 *
 * @author (Kassa Essayas
 * @version (a version number or a date)
 */
public class BirdComponet
{
    // instance variables - replace the example below with your own
    private int x,y;
    private  int JUMP=-5;
    private final static int FAILING_RATE=1;
    private  int height=100;
    private static int width=100;
    private final  int increase=-10;
    private ImageIcon image;
    /**
     * Constructor for objects of class BirdComponet so that even another coder wanted to use this bird class all the
     * person needs to do is provid with start point where he or she will like to place the bird so that they can use it
     * with other class
     *
     */
    public BirdComponet(int startx, int starty)
    {
        x= startx;
        y= starty;
        image= new ImageIcon("src/bird.gif");
    }
    /*
     * this method doesnot return anything however it takes in a graphics g and Jcomponent. and when this method is called
     * it will draw the image
     */
    public void draw(Graphics g, JComponent comp)
    {
        image.paintIcon(comp,g,x,y);
    }
    /*
     * this method doent return anything and doent not take anything in the parameter however it will change the value of jump
     * so that it will look like the bird is jumping
     */
    public void jump()
    {
        JUMP=increase;
    }
    /*
     * this method does not return anything how ever when its called by the timer it will change the value of y so that will
     * look like it failing
     */
    public void update()
    {
        y=y+JUMP;

        JUMP+=FAILING_RATE;
        if(JUMP>FAILING_RATE)
        {
            JUMP =FAILING_RATE;
        }
    }
    /*
     * this method returns an integer and that is the value of y
     */
    public int getY()
    {
        return y;
    }
    /*
     * this method returns an integer and its the value of the x value of the bird
     */
    public int getX()
    {
        return x;
    }
    /*
     * this method returns an integer, the height of the bird
     */
    public int getHeight()
    {
        return height;
    }
    /*
     * this method returns an integer, the width of the bird
     */
    public int getWidth()
    {
        return width;
    }
    /*
     * this method returns a rectagle with the dimesions of the bird
     */
    public Rectangle getBoundingBox()
    {
        return new Rectangle(x,y,width,height);
    }





}