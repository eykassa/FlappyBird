import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
/**
 * LandScape is class that extends Jpanel so it has the futurs of a jpanel in this class this is where i paint the components
 * and hava inner class for the timer listener and mouse listeners and it will create bird by calling another class and
 * the pipes too.
 *
 * @author (Kassa Essayas and Oluwademilade Dammy Adisa)
 * @version (a version number or a date)
 */
public class LandScape extends JPanel
{

    private int x=700;
    private int space=500;
    private BirdComponet bird;
    private Pipes obstacle;
    private ArrayList<Pipes>Obstacle;
    private boolean Start;
    private boolean restart;
    private Timer t;
    private int Score;
    private int HighScore;

    /**
     * Constructor for objects of class LandScape2
     * is this constructor this is were i create the pipes create the bird and call a method to read the highscore from
     * a text file. And also i create the timer and give it a listener and a delay.
     * finally i create the mouse listener and add it to the frame
     */
    public LandScape()
    {

        Start=false;
        Obstacle= new ArrayList();
        bird= new BirdComponet(200,400);

        //obstacle= new Pipes(700);
        for(int i=0; i<4; i++)
        {
            Pipes pipe= new Pipes(x);
            Obstacle.add(pipe);
            x=x+space;
        }
        loadHighScore();

        ActionListener listener= new TimerListener();
        t= new Timer(10,listener);
        MouseListener mouseListener= new MouseListener();
        addMouseListener(mouseListener);


    }
    /*
     * this class implements ActionListener so it has all its methods, so when the timer goes off it will call this class
     * and this class is were i update the y position of the bird by calling a method and altenrate through the arraylist
     * of pipes and check if has intersected with it and for that i create a mthod in the pipe class. And also move the
     * polygons to the left by adding delta to the x values.
     * and also it updates the score, so i created anothor polygon in the gap so that when it intersets is will count as a score
     * and final if the score is greater than highscore it will set score equal to highscore.
     * and it will create pipes everytime so that it doesnt run out of pipes
     */
    class TimerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if(restart)
            {
                t.start();

                restart=false;
            }
            bird.update();
            for(Pipes pipe: Obstacle)
            {
                //int i =pipe.x;
                if(pipe.collidesWith(bird.getBoundingBox()))
                {

                    t.stop();
                    bird= new BirdComponet(200,400);
                    saveHighScore(HighScore);
                    Start=false;
                    restart=true;
                    Obstacle.clear();

                    x=700;
                    for(int i=0; i<4; i++)
                    {
                        Pipes anotherpipe= new Pipes(x);
                        Obstacle.add(anotherpipe);
                        x=x+space;

                    }

                    repaint();
                }

                pipe.moveLeft();

                if(pipe.checkScore(bird.getBoundingBox()))
                {

                    Score++;
                    if(Score>HighScore)
                    {
                        HighScore=Score;
                    }

                }

            }
            Pipes newObstacle= new Pipes(x);
            Obstacle.add(newObstacle);
            x=x+space;

            repaint();
        }
    }
    /*
     * This class is just class that extends MouseAdapter so that will shorter than implementing the interface
     * all this class does is when the mouse is pressed for the first time  it will start the timer and after that will class
     * a mothod in the bird class to move the bird the bird by changing the y axis
     */
    class MouseListener extends MouseAdapter
    {

        public void mousePressed(MouseEvent e)
        {
            if(!Start)
            {
                t.start();
                Start=true;
            }
            else if(Start)
            {
                bird.jump();
            }
        }

    }

    /*
     * this method is extended from jpanel and this is where we do the drawing so it will alternate though the
     * the arraylists of pipes and use the method draw that is provided in the class pipe and draw the pipes.
     * and the top if statement are just tell the user what to do play the game and tell the use his score and weather he
     * or she beat the highscore
     */
    public void paintComponent(Graphics  g)
    {
        super.paintComponent(g);
        if(!Start&& !restart)
        {
            g.setFont(new Font("Arial",1,30));
            g.drawString("Welcome, Tap to start the game",50,100);
        }

        if(restart)
        {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", 1, 30));
            g.drawString("Your Score was  "+Score,70,100);
            if(Score==HighScore)
            {
                g.drawString("You beat the highscore!!!",50,200);
            }
            else
            {
                g.drawString("Sorry you didnt beat the highScore",50,200);
            }
            g.drawString("Tap to play agian",70,300);

            Score=0;
        }
        setBackground(Color.CYAN);
        bird.draw(g,this);
        g.setFont(new Font("Arial", 1, 25));
        g.drawString("Score:"+Score,20,20);
        g.drawString("High Score:"+HighScore,1000,20);
        for(Pipes pipe: Obstacle)
        {
            pipe.draw(g);
        }

    }

    /*
     * In this it doesnt return anything but it will read from the file when its called and update the highscore;
     */
    public void loadHighScore()
    {
        try
        {

            File infile= new File("highscore.txt");
            Scanner in= new Scanner(infile);
            while(in.hasNextInt())
            {
                HighScore= in.nextInt();
            }

        }catch( FileNotFoundException e){}
    }

    /*
     * this method does not return anything but it takes in and integer in the parameter and writes the integer value when its
     * called.
     *
     *
     */
    public void saveHighScore(int highscore)
    {
        try
        {
            File file= new File("highscore.txt");
            PrintWriter writer= new PrintWriter("highscore.txt");
            writer.println(""+highscore);
            writer.close();

        }catch(FileNotFoundException e){}
    }
    public static void main(String []args){
            Scanner in =new Scanner (System.in);

    }
}