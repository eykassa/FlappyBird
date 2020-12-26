import java.awt.*;
import java.awt.Rectangle.*;
/**
 * Pipes class is the class that creates polygons so that i will be able to use them as obstcle in my landscape class.
 *
 * @author (Kassa Essayas
 * @version (a version number or a date)
 */
public class Pipes
{
    // instance variables - replace the example below with your own
    private final int DELTA=-2;
    private final int WIDTH=100;
    private Polygon upper;
    private Polygon lower;
    private Polygon middle;
    private boolean intersectOnlyOnce;
    int upX;
    /**
     * Constructor for objects of class Pipes and it takes in a parameter of an integer so that it knows where to start
     */
    public Pipes(int xPostition)
    {
        // initialise instance variables
        intersectOnlyOnce= true;
        int startX=xPostition;

        int upY=0;
        int SPACE=300;
        int newHeight=(int)(Math.random()*400)+40;


        int []upXs= {startX,startX+WIDTH,startX+WIDTH,startX+WIDTH+20,startX+WIDTH+20,startX-20,startX-20,startX};
        int []upYs = {0,0,newHeight,newHeight,newHeight+20,newHeight+20,newHeight,newHeight};
        int []dnXs= {startX-20,startX+WIDTH+20,  startX+WIDTH+20,  startX+WIDTH,  startX+WIDTH,startX,startX,startX-20};
        int []dnYs= {newHeight+SPACE,newHeight+SPACE,newHeight+SPACE+20,newHeight+SPACE+20,1200,1200,newHeight+SPACE+20,newHeight+SPACE+20};


        int[]midXs={upX,upX+WIDTH,upX+WIDTH,upX};
        int []midYs={newHeight,newHeight,newHeight+SPACE,newHeight+SPACE};


        upper= new Polygon(upXs, upYs,upXs.length);
        lower= new Polygon(dnXs, dnYs,dnXs.length);
        middle= new Polygon(midXs,midYs,midXs.length);

    }
    /*
     * this method doesnot return anything however when its called to will move the poylgons with delata tha is specified
     */
    public void moveLeft()
    {
        upper.translate(DELTA,0);
        middle.translate(DELTA,0);

        lower.translate(DELTA,0);
    }
    /*
     * This method doesnot return anything how ever when its called it will draw the poylgons
     */
    public void draw(Graphics g)
    {
        g.setColor(Color.green.darker());
        g.fillPolygon(upper);
        g.fillPolygon(lower);

    }

    /**
     * return true if either upper or lower intersects with the rectangle r of if it goes of the screen
     */
    public boolean collidesWith(Rectangle r)
    {
        r.translate(10,0);
        if(upper.intersects(r))
        {
            return true;

        }
        else if(lower.intersects(r))
        {
            return true;
        }
        else if(r.y<0 || r.y>=700)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    /**
     * this method when called it will check if it intersected with polygon in the middle so it will return a boolean varable
     *
     */

    public boolean checkScore(Rectangle r)
    {
        if(middle.intersects(r)&& intersectOnlyOnce)
        {
            intersectOnlyOnce=false;
            return true;
        }
        else
        {
            return false;
        }
    }

}
