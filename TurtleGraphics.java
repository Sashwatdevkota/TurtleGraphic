import java.awt.*;
import javax.swing.JFrame;
import uk.ac.leedsbeckett.oop.LBUGraphics;


public class TurtleGraphics extends LBUGraphics
{
    private final Color defaultPenColor;
    private final int defaultPenWidth;

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
        drawOn();
        defaultPenColor = getPenColour();
        defaultPenWidth = Math.round(getStroke());
    }

    @Override
    public void about() {
        super.about();
        displayMessage("This program was created by Sashwat Devkota.");
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

            case "penup":
                drawOff();
                displayMessage("Drawing off");
                break;

            case "pendown":
                drawOn();
                displayMessage("Drawing on");
                break;

            case "move":
                if (commandParts.length != 2){
                    displayMessage("Additional or Missing Parameters. Use move <distance>.");
                }
                else{
                    try{
                        int parameter = Integer.parseInt(commandParts[1]);
                        if (parameter < 0){
                            displayMessage("The data is negative.");
                        }
                        else{
                            forward((parameter));
                            displayMessage("Moved by "+parameter+" distance forward.");
                        }
                    }catch (NumberFormatException e){
                        displayMessage("The data cannot be converted to an integer.");
                    }
                }
                break;

            case "reverse" :
                if (commandParts.length != 2){
                    displayMessage("Additional or Missing Parameters. Use reverse <distance>.");
                }
                else{
                    try{
                        int parameter = Integer.parseInt(commandParts[1]);
                        if (parameter < 0){
                            displayMessage("The data is negative.");
                            displayMessage("Moved by "+parameter+" distance reverse.");
                        }
                        else{
                            forward((parameter*-1));
                        }
                    }catch (NumberFormatException e){
                        displayMessage("The data cannot be converted to an integer.");
                    }
                }

                break;


            case "left":
                if (commandParts.length != 2) {
                    displayMessage("Additional or Missing Parameters. Use left <degrees>.");
                } else {
                    try {
                        int parameter = Integer.parseInt(commandParts[1]);
                        if (parameter < 0) {
                            displayMessage("The data is negative.");
                        } else {
                            left(parameter);
                            displayMessage("Turtle moved by "+parameter+" degrees to left.");
                        }
                    } catch (NumberFormatException e) {
                        displayMessage("The data cannot be converted to an integer.");
                    }
                }
                break;

            case "right":
                if (commandParts.length != 2) {
                    displayMessage("Additional or Missing Parameters. Use right <degrees>.");
                } else {
                    try {
                        int parameter = Integer.parseInt(commandParts[1]);
                        if (parameter < 0) {
                            displayMessage("The data is negative.");
                        } else {
                            right(parameter);
                            displayMessage("Turtle moved by "+parameter+" degrees to right.");
                        }
                    } catch (NumberFormatException e) {
                        displayMessage("The data cannot be converted to an integer.");
                    }
                }
                break;

            case "black":
                setPenColour(Color.black);
                displayMessage("Colour set to black.");
                break;

            case "green":
                setPenColour(Color.green);
                displayMessage("Color set to green.");
                break;

            case "red":
                setPenColour(Color.red);
                displayMessage("Color set to red.");
                break;

            case "blue":
                setPenColour(Color.blue);
                displayMessage("Color set to blue.");
                break;

            case "white":
                setPenColour(Color.white);
                displayMessage("Color set to white.");
                break;

            case "reset":
                reset();
                drawOn();
                setPenColour(defaultPenColor);
                setStroke(defaultPenWidth);
                displayMessage("Turtle Reset.");
                break;

            case "clear":
                clear();
                displayMessage("Screen Cleared.");
                break;

            case "square":
                if (commandParts.length != 2){
                    displayMessage("Additional or Missing Parameters. Use: square <length>");
                } else {
                    try {
                        int parameter = Integer.parseInt(commandParts[1]);
                        for (int i = 0; i < 4; i++) {
                            forward(parameter);
                            right(90);
                        }
                        displayMessage("Drew a square with length " + parameter);
                    } catch (NumberFormatException e){
                        displayMessage("The data cannot be converted to an integer.");
                    }
                }
                break;

            case "pencolour":
                if (commandParts.length != 4) {
                    displayMessage("Additional or Missing Parameters. Use: pencolour <red> <green> <blue>");
                } else {
                    try {
                        int r = Integer.parseInt(commandParts[1]);
                        int g = Integer.parseInt(commandParts[2]);
                        int b = Integer.parseInt(commandParts[3]);
                        setPenColour(new Color(r, g, b));
                        displayMessage("Pen colour set to RGB(" + r + "," + g + "," + b + ")");
                    } catch (NumberFormatException e){
                        displayMessage("The data cannot be converted to an integer.");
                    }
                }
                break;


            case "penwidth":
                if (commandParts.length != 2) {
                    displayMessage("Additional or Missing Parameters. Use: penwidth <width>");
                }else {
                    try{
                        int parameter = Integer.parseInt(commandParts[1]);
                        if (parameter < 0) {
                            displayMessage("The data is negative.");
                        }else{
                            setStroke(parameter);
                            displayMessage("Pen width set to " + parameter);
                        }
                    }catch (NumberFormatException e){
                        displayMessage("The data cannot be converted to an integer.");
                    }
                }
                break;

            case "triangle":
                if (commandParts.length != 2 && commandParts.length != 4) {
                    displayMessage("Additional or Missing Parameters. \n Use: triangle <size> or triangle <side1> <side2> <side3>");
                } else {
                    if (commandParts.length == 2) {
                        try {
                            int parameter = Integer.parseInt(commandParts[1]);
                            if (parameter < 0) {
                                displayMessage("The data is negative.");
                            } else {
                                for (int i = 0; i < 3; i++) {
                                    forward(parameter);
                                    right(120);

                                }
                                displayMessage("Drew a Triangle with length " + parameter);
                            }
                        } catch (NumberFormatException e) {
                            displayMessage("The data cannot be converted to an integer.");
                        }
                    } else {
                        try {
                            int[] sides = new int[3];
                            for (int i = 0; i < 3; i++) {
                                sides[i] = Integer.parseInt(commandParts[i + 1]);
                                if (sides[i] < 0) {
                                    displayMessage("The data is negative.");
                                    return;
                                }
                            }
                            for (int i = 0; i < 3; i++) {
                                forward(sides[i]);
                                right(120);
                            }
                            displayMessage("Drew a Triangle with length " + sides[0] + " " + sides[1] + " " + sides[2]);
                        } catch (NumberFormatException e) {
                            displayMessage("The data cannot be converted to an integer.");
                        }
                    }
                }
                break;


            default:
                displayMessage("Invalid command: " + cmd);

        }
    }
}