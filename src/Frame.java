import javax.swing.*;
/**
 * class Frame is just a class to create the frame and add the landscape into it.
 *
 * @author (Kassa Essayas
 * @version (a version number or a date)
 */
public class Frame extends JFrame
{
    /**
     * this method is the main method this is where the program starts and this is where i create the frame and create the
     * landscape to add to the frame
     */

    public static void main(String []args)
    {
        JFrame frame= new JFrame("Dodgy Pipe Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400,700);
        LandScape panel= new LandScape();
        frame.add(panel);
        frame.setVisible(true);
    }


}