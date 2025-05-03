import java.awt.FlowLayout;
import javax.swing.JFrame;
import uk.ac.leedsbeckett.oop.LBUGraphics;
import java.util.Scanner;

public class TurtleGraphics extends LBUGraphics
{
    public static void main(String[] args)
    {
        new TurtleGraphics();
    }

    public TurtleGraphics()
    {
        JFrame MainFrame = new JFrame();
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setLayout(new FlowLayout());
        MainFrame.add(this);
        MainFrame.pack();
        MainFrame.setVisible(true);
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        if (input.toLowerCase().equals("about")) {
            about();
        }
        else{
            System.out.println(input+" is not a valid command.");
        }
    }


    public void processCommand(String command)
    {

    }
}