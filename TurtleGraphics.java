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
    }


    public void processCommand(String command)
    {
        command = command.toLowerCase();
        String[] commandParts = command.split(" ");
        String cmd = commandParts[0];
        switch(cmd){
            case "about":
                about();
                break;
            default:
                System.out.println("Unknown command: " + cmd);
        }
    }
}