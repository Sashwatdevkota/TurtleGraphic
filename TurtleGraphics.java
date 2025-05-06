import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import uk.ac.leedsbeckett.oop.LBUGraphics;

public class TurtleGraphics extends LBUGraphics {

    private final Color defaultPenColor;
    private final int defaultPenWidth;

    private JFileChooser chooser;
    private ArrayList<String> commands;
    private boolean isImageSaved = false;

    public static void main(String[] args) {
        new TurtleGraphics();
    }

    public TurtleGraphics() {
        JFrame MainFrame = new JFrame();
        chooser = new JFileChooser();
        commands = new ArrayList<>();

        JMenuBar menuBar = new JMenuBar();
        MainFrame.setJMenuBar(menuBar);

        JMenu menuFile = new JMenu("File");
        JMenu menuHelp = new JMenu("Help");
        menuBar.add(menuFile);
        menuBar.add(menuHelp);

        JMenuItem menuItemSavePng = new JMenuItem("Save PNG");
        JMenuItem menuItemSaveTxt = new JMenuItem("Save Txt");
        JMenuItem menuItemLoad = new JMenuItem("Load");
        JMenuItem menuItemHelp = new JMenuItem("List Commands");

        menuFile.add(menuItemSavePng);
        menuFile.add(menuItemSaveTxt);
        menuFile.add(menuItemLoad);
        menuHelp.add(menuItemHelp);

        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setLayout(new BorderLayout());
        MainFrame.add(this, BorderLayout.CENTER);

        MainFrame.setSize(800, 600);
        MainFrame.pack();
        MainFrame.setVisible(true);

        menuItemSavePng.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                saveFilePng();
            }
        });

        menuItemSaveTxt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                saveFileTxt();
            }
        });

        menuItemLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                loadFile();
            }
        });

        menuItemHelp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                String helpText =
                        "List of Available Commands: \n\n" +
                        "penup                    " + "pendown\n" +
                        "left                     " + "left\n" +
                        "right                    " + "right\n" +
                        "move                     " + "moven" +
                        "reverse                  " + "reverse\n" +
                        "black                    " + "green\n" +
                        "red                      " + "blue\n" +
                        "white                    " + "reset\n" +
                        "clear                    " + "about\n" +
                        "circle                   " + "clearinterface\n" +
                        "dance                    " + "displaymessage\n" +
                        "drawcircle               " + "getbackgroundcolor\n" +
                        "getdirection             " + "getpencolor\n" +
                        "getpenstate              " + "getstroke\n" +
                        "getcoords                " + "point\n" +
                        "setbackgroundcolor       " + "setturtle\n" +
                        "setpencolor              " + "setpenstate\n" +
                        "penwidth                 " + "setturtleimage\n" +
                        "setturtlespeed           " + "setxcoords\n" +
                        "setycoords               " + "square\n" +
                        "star                     " + "spiral\n" +
                        "triangle                 " + "displaysentence";


                JOptionPane.showMessageDialog(null, helpText, "Command List", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        drawOn();
        defaultPenColor = getPenColour();
        defaultPenWidth = Math.round(getStroke());
        forward(0);

    }

    /*---------------------------FILE SAVING SYSTEM------------------------------------------*/

    public void loadFile() {
        if (!isImageSaved) {
            int choice = JOptionPane.showConfirmDialog(
                    null,
                    "You have unsaved changes. Loading a file will discard them. Continue?",
                    "Unsaved Changes",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (choice != JOptionPane.YES_OPTION) {
                displayMessage("Load cancelled by user.");
                return;
            }
        }

        chooser.setDialogTitle("Load a file");
        chooser.setFileFilter(new FileNameExtensionFilter("Text or PNG Files", "txt", "png"));
        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File input = chooser.getSelectedFile();
            String extension = getFileExtension(input);
            if (extension.equalsIgnoreCase("txt")) {
                clear(); // 🧹 clear before loading
                commands.clear();
                loadTxtFile(input); // Loads text file and displays messages
            } else if (extension.equalsIgnoreCase("png")) {
                loadPngFile(input);
            } else {
                JOptionPane.showMessageDialog(null, "Unsupported file type");
            }
        } else {
            displayMessage("Cancelled");
        }
    }

    public String getFileExtension(File file) {
        String fileName = file.getName();
        int lastIndex = fileName.lastIndexOf(".");
        if (lastIndex > 0 && lastIndex < fileName.length() - 1) {
            return fileName.substring(lastIndex + 1).toLowerCase();
        }
        return "";
    }

    public void loadTxtFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                final String command = line.trim();
                commands.add(command); // Store the command
                displayMessage(command); // Show each command using displayMessage
                processCommand(command); // Execute the command
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading file: " + e.getMessage());
        }
    }

    public void loadPngFile(File inputFile) {
        try {
            BufferedImage image = ImageIO.read(inputFile);
            setBufferedImage(image);
            displayMessage("Image loaded successfully");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading image: " + e.getMessage());
        }
    }

    public void saveFileTxt() {
        chooser.setDialogTitle("Save commands as text");
        chooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int result = chooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File outputFile = chooser.getSelectedFile();
            if (!outputFile.getName().toLowerCase().endsWith(".txt")) {
                outputFile = new File(outputFile.getAbsolutePath() + ".txt");
            }

            try (PrintWriter writer = new PrintWriter(outputFile)) {
                for (String command : commands) {
                    writer.println(command);
                }
                JOptionPane.showMessageDialog(null, "Commands saved successfully");
                displayMessage("Commands saved to " + outputFile.getName());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error saving file: " + e.getMessage());
            }
        } else {
            displayMessage("Cancelled");
        }
    }

    public void saveFilePng() {
        chooser.setDialogTitle("Save as PNG");
        chooser.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
        int result = chooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File outputFile = chooser.getSelectedFile();
            if (!outputFile.getName().toLowerCase().endsWith(".png")) {
                outputFile = new File(outputFile.getAbsolutePath() + ".png");
            }

            try {
                ImageIO.write(getBufferedImage(), "png", outputFile);
                isImageSaved = true;
                JOptionPane.showMessageDialog(null, "Image saved successfully");
                displayMessage("Image saved to " + outputFile.getName());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error saving image: " + e.getMessage());
            }
        } else {
            displayMessage("Cancelled");
        }
    }

    /*---------------------------OBJECT DRAWING SYSTEM------------------------------------------*/

    @Override

    public void about() {
        super.about();
        displayMessage("This program was created by Sashwat Devkota.");
    }

    public void drawSquare(int parameter){
        for (int i = 0; i < 4; i++) {
            forward(parameter);
            right(90);
        }
    }

    public void drawTriangle(int parameter){
        for (int i = 0; i < 3; i++) {
            forward(parameter);
            right(120);
        }
    }

    public void drawTriangle(int a, int b, int c) {

        double angleA = Math.toDegrees(Math.acos((b*b + c*c - a*a) / (2.0 * b * c)));
        double angleB = Math.toDegrees(Math.acos((a*a + c*c - b*b) / (2.0 * a * c)));
        double angleC = 180.0 - angleA - angleB;

        forward(a);
        right(180 - (int)Math.round(angleC));
        forward(b);
        right(180 - (int)Math.round(angleA));
        forward(c);

    }

    public void drawStar(int a){
            drawOn();
            for (int i = 0; i < 5; i++) {
                forward(a);
                right(144);
            }
        }

    public void drawSpiral(int a) {
        drawOn();
        int step = 10;
        for (int i = 0; i < a; i++) {
            forward(step);
            right(90);
            step += 5; // controls how much the spiral expands
        }
    }

    /*---------------------------PROCESS COMMAND------------------------------------------*/

    public void processCommand(String command) {

        command = command.toLowerCase();
        String[] commandParts = command.split(" ");
        String cmd = commandParts[0];

        commands.add(command);

        switch(cmd){

            case "penup": {
                if (commandParts.length == 1) {
                    drawOff();
                    displayMessage("Drawing off");
                } else {
                    displayMessage("This command doesn't require any parameters.");
                }
                break;
            }

            case "pendown": {

                if (commandParts.length == 1) {
                    drawOn();
                    displayMessage("Drawing on");
                } else {
                    displayMessage("This command doesn't require any parameters.");
                }
                break;
            }

            case "left": {
                if (commandParts.length == 1) {
                    left();
                    displayMessage("Turtle turned left by default.");
                } else if (commandParts.length == 2) {
                    try {
                        int parameter = Integer.parseInt(commandParts[1]);
                        if (parameter < 0) {
                            displayMessage("The parameter is negative.");
                        } else {
                            left(parameter);
                            displayMessage("Turtle moved by " + parameter + " degrees to left.");
                        }
                    } catch (NumberFormatException e) {
                        displayMessage("Inputted parameter cannot be converted to an integer.");
                    }
                } else {
                    displayMessage("Additional or Missing Parameters. Use left <degrees> or left.");
                }
                break;
            }

            case "right": {
                if (commandParts.length == 1) {
                    right();
                    displayMessage("Turtle turned right by default.");
                } else if (commandParts.length == 2) {
                    try {
                        int parameter = Integer.parseInt(commandParts[1]);
                        if (parameter < 0) {
                            displayMessage("The parameter is negative.");
                        } else {
                            right(parameter);
                            displayMessage("Turtle moved by " + parameter + " degrees to right.");
                        }
                    } catch (NumberFormatException e) {
                        displayMessage("Inputted parameter cannot be converted to an integer.");
                    }
                } else {
                    displayMessage("Additional or Missing Parameters. Use right <degrees> or right.");
                }
                break;
            }

            case "move": {
                if (commandParts.length != 2) {
                    displayMessage("Additional or Missing Parameters. Use move <distance>.");
                } else {
                    try {
                        int parameter = Integer.parseInt(commandParts[1]);
                        if (parameter < 0) {
                            displayMessage("The parameter is negative.");
                        } else {
                            forward((parameter));
                            displayMessage("Moved by " + parameter + " distance forward.");
                        }
                    } catch (NumberFormatException e) {
                        displayMessage("Inputted parameter cannot be converted to an integer.");
                    }
                }
                break;
            }

            case "reverse" : {
                if (commandParts.length != 2) {
                    displayMessage("Additional or Missing Parameters. Use reverse <distance>.");
                } else {
                    try {
                        int parameter = Integer.parseInt(commandParts[1]);
                        if (parameter < 0) {
                            displayMessage("The parameter is negative.");
                        } else {
                            forward((parameter * -1));
                            displayMessage("Moved by " + parameter + " distance reverse.");
                        }
                    } catch (NumberFormatException e) {
                        displayMessage("Inputted parameter cannot be converted to an integer.");
                    }
                }
                break;
            }

            case "black": {
                if (commandParts.length == 1) {
                    setPenColour(Color.black);
                    displayMessage("Colour set to black.");
                } else {
                    displayMessage("This command doesn't require any parameters.");
                }
                break;
            }

            case "green": {
                if (commandParts.length == 1) {
                    setPenColour(Color.green);
                    displayMessage("Color set to green.");
                } else {
                    displayMessage("This command doesn't require any parameters.");
                }
                break;
            }

            case "red": {
                if (commandParts.length == 1) {
                    setPenColour(Color.red);
                    displayMessage("Color set to red.");
                } else {
                    displayMessage("This command doesn't require any parameters.");
                }
                break;
            }

            case "blue": {
                if (commandParts.length == 1) {
                    setPenColour(Color.blue);
                    displayMessage("Color set to blue.");
                } else {
                    displayMessage("This command doesn't require any parameters.");
                }
                break;

            }

            case "white": {
                if (commandParts.length == 1) {
                    setPenColour(Color.white);
                    displayMessage("Color set to white.");
                } else {
                    displayMessage("This command doesn't require any parameters.");
                }
                break;

            }

            case "reset": {
                if (commandParts.length == 1) {
                    reset();
                    drawOn();
                    setPenColour(defaultPenColor);
                    setStroke(defaultPenWidth);
                    displayMessage("Turtle Reset.");
                } else {
                    displayMessage("This command doesn't require any parameters.");
                }
                break;
            }

            case "clear": {
                if (commandParts.length == 1) {
                    clear();
                    displayMessage("Screen Cleared.");
                } else {
                    displayMessage("This command doesn't require any parameters.");
                }
                break;
            }

            case "about": {
                if (commandParts.length == 1) {
                    about();
                } else {
                    displayMessage("This command doesn't require any parameters.");
                }
                break;
            }

            case "circle": {
                if (commandParts.length != 2) {
                    displayMessage("Incorrect number of parameters. Use: circle <radius>");
                } else {
                    try {
                        int parameter = Integer.parseInt(commandParts[1]);
                        if (parameter < 0) {
                            displayMessage("Inputted parameter negative.");
                        } else {
                            circle(parameter);
                            displayMessage("Circle drawn with parameter:" + parameter);
                        }
                    } catch (NumberFormatException e) {
                        displayMessage("Inputted parameter cannot be converted to an integer.");
                    }
                }
                break;
            }

            case "clearinterface":{
                if (commandParts.length == 1) {
                    clearInterface();
                } else {
                    displayMessage("This command doesn't require any parameters.");
                }
                break;
            }

            case "dance":{
                if (commandParts.length != 2) {
                    displayMessage("Incorrect number of parameters. Use: dance <moves>");
                }else {
                    try {
                        int moves = Integer.parseInt(commandParts[1]);
                        if (moves < 0) {
                            displayMessage("The parameter is negative.");
                        } else {
                            dance(moves);
                            displayMessage("Dance done with moves: " + moves);
                        }
                    }catch (NumberFormatException e) {
                        displayMessage("Inputted parameter cannot be converted to an integer.");
                    }
                }
                break;
            }

            case "displaymessage": {
                if (commandParts.length < 2) {
                    displayMessage("Missing Parameters. Use: displaymessage <message>");
                } else {
                    String sentence = String.join(" ", Arrays.copyOfRange(commandParts, 1, commandParts.length));
                    displayMessage(sentence);
                }
                break;
            }

            case "drawcircle":{
                if (commandParts.length != 4) {
                    displayMessage("Incorrect number of parameters. Use: drawcircle <radius> <x-cordinate> <y-cordinate>");
                }
                else{
                    try {
                        int radius = Integer.parseInt(commandParts[1]);
                        int x = Integer.parseInt(commandParts[2]);
                        int y = Integer.parseInt(commandParts[3]);
                        if (radius < 0 || x < 0 || y < 0) {
                            displayMessage("The parameter is negative.");
                        }else{
                            drawCircle(radius, x, y);
                            displayMessage("Drew a circle with radius " + radius + " at x: " + x + " and y: " + y);
                        }
                    }catch (NumberFormatException e) {
                        displayMessage("Inputted parameter cannot be converted to an integer.");
                    }
                }
                break;
            }

            case "getbackgroundcolor":{
                if (commandParts.length == 1) {
                    Color bgcolor = getBackground_Col();
                    displayMessage("Background color is " + bgcolor);
                } else {
                    displayMessage("This command doesn't require any parameters.");
                }
                break;

            }

            case "getdirection": {
                if (commandParts.length == 1) {
                    int direction=getDirection();
                    displayMessage("Direction is " + direction);
                } else {
                    displayMessage("This command doesn't require any parameters.");
                }
                break;
            }

            case "getpencolor":{
                if (commandParts.length == 1) {
                    Color color= getPenColour();
                    displayMessage("Pen color is " + color);
                }else {
                    displayMessage("This command doesn't require any parameters.");
                }
                break;
            }

            case "getpenstate":{
                if (commandParts.length == 1) {
                    boolean state=getPenState();
                    if (state) {
                        displayMessage("Pen is on");
                    }else{
                        displayMessage("Pen is off");
                    }
                }else {
                    displayMessage("This command doesn't require any parameters.");
                }
                break;
            }

            case "getstroke":{
                if (commandParts.length == 1) {
                    float size=getStroke();
                    displayMessage("Pen stroke is " + size);
                }else {
                    displayMessage("This command doesn't require any parameters.");
                }
                break;
            }

            case "getcoords":{
                if (commandParts.length == 1) {
                    int x=getxPos();
                    int y=getyPos();
                    displayMessage("X-cordinate and Y-cordinate are equal."+ x + " and " + y);
                }
                else{
                    displayMessage("This command doesn't require any parameters.");
                }
                break;
            }

            case "pointturtle":{
                if (commandParts.length != 2) {
                    displayMessage("Incorrect number of parameters. Use: point <degrees>");
                }else {
                    try {
                        int degree = Integer.parseInt(commandParts[1]);
                        if (degree < 0) {
                            displayMessage("The parameter is negative.");
                        } else {
                            pointTurtle(degree);
                            displayMessage("Rotated turtle to: " + degree + " degrees");
                        }
                    }catch (NumberFormatException e) {
                        displayMessage("The parameter must be a valid integer.");
                    }
                }
                break;
            }

            case "setbackgroundcolor": {
                if (commandParts.length != 4) {
                    displayMessage("Incorrect number of parameters. Use: setbackgroundcolor <red> <green> <blue>");
                } else {
                    try {
                        int r = Integer.parseInt(commandParts[1]);
                        int g = Integer.parseInt(commandParts[2]);
                        int b = Integer.parseInt(commandParts[3]);
                        if (r < 0 || g < 0 || b < 0 || r > 255 || g > 255 || b > 255) {
                            displayMessage("Please enter values between 0 and 255.");
                        } else {
                            setBackground_Col(new Color(r, g, b));
                            clear();
                            displayMessage("Background color set to RGB(" + r + "," + g + "," + b + ")");
                        }
                    } catch (NumberFormatException e) {
                        displayMessage("The parameters must be valid integers.");
                    }
                }
                break;
            }

            case "setinternalturtle":{
                if (commandParts.length != 2) {
                    displayMessage("Incorrect number of parameters. Use: setturtle <mode>");
                }else{
                    try {
                        int mode = Integer.parseInt(commandParts[1]);
                        if (mode < 0 || mode > 4) {
                            displayMessage("Please enter the mode from 1 to 4.");
                        }else{
                            setInternalTurtle(mode);
                            forward(0);
                        }
                    }catch (NumberFormatException e) {
                        displayMessage("Inputted parameter cannot be converted to an integer.");
                    }
                }
                break;
            }

            case "setpencolor":{
                if (commandParts.length != 4)
                    displayMessage("Incorrect number of parameters. Use: setpencolor <red> <green> <blue>");
                else {
                    try{
                        int r = Integer.parseInt(commandParts[1]);
                        int g = Integer.parseInt(commandParts[2]);
                        int b = Integer.parseInt(commandParts[3]);
                        if (r < 0 || g < 0 || b < 0 || r > 255 || g > 255 || b > 255){
                            displayMessage("Please enter between 0 and 255.");
                        }else{
                            setPenColour(new Color(r, g, b));
                            clear();
                            displayMessage("Background colour set to RGB(" + r + "," + g + "," + b + ")");
                        }
                    } catch (NumberFormatException e) {
                        displayMessage("The parameter cannot be converted to an integer.");
                    }
                }
                break;
            }

            case "setpenstate": {
                if (commandParts.length != 2) {
                    displayMessage("Incorrect number of parameters. Use: setpenstate <true|false>");
                } else {

                    String state = commandParts[1].toLowerCase();
                    if (state.equals("true")) {
                        setPenState(true);
                        displayMessage("Pen set to on");
                    } else if (state.equals("false")) {
                        setPenState(false);
                        displayMessage("Pen set to off");
                    } else {
                        displayMessage("Please enter the state as true or false.");

                    }
                }
                break;
            }

            case "setstroke": {
                if (commandParts.length != 2) {
                    displayMessage("Incorrect number of parameters. Use: penwidth <width>");
                } else {
                    try {
                        int parameter = Integer.parseInt(commandParts[1]);
                        if (parameter < 0) {
                            displayMessage("The parameter is negative.");
                        } else {
                            setStroke(parameter);
                            displayMessage("Pen width set to " + parameter);
                        }
                    } catch (NumberFormatException e) {
                        displayMessage("The parameter must be a valid integer.");
                    }
                }
                break;
            }

            case "setturtleimage": {
                if (commandParts.length != 2) {
                    displayMessage("Incorrect number of parameters. Use: setturtleimage (cat|dog|football|fish|lbu)");
                } else {

                    String image = commandParts[1].toLowerCase();
                    switch (image) {
                        case "cat":
                            setTurtleImage("C:\\Users\\sashw\\OneDrive\\Desktop\\TurtleGraphics\\Images\\cat.png");
                            forward(0);
                            break;
                        case "dog":
                            setTurtleImage("C:\\Users\\sashw\\OneDrive\\Desktop\\TurtleGraphics\\Images\\dog.png");
                            forward(0);
                            break;
                        case "football":
                            setTurtleImage("C:\\Users\\sashw\\OneDrive\\Desktop\\TurtleGraphics\\Images\\football.png");
                            forward(0);
                            break;
                        case "fish":
                            setTurtleImage("C:\\Users\\sashw\\OneDrive\\Desktop\\TurtleGraphics\\Images\\fish.png");
                            forward(0);
                            break;
                        case "lbu":
                            setTurtleImage("C:\\Users\\sashw\\OneDrive\\Desktop\\TurtleGraphics\\Images\\lbu.png");
                            forward(0);
                            break;
                        default:
                            displayMessage("Please enter a valid image name. (cat|dog|football|fish|lbu)");
                    }
                }
                break;
            }

            case "setturtlespeed":{
                if (commandParts.length != 2) {
                    displayMessage("Incorrect number of parameters. Use: setturtlespeed <speed>");
                }else{
                    try {
                        int speed=Integer.parseInt(commandParts[1]);
                        if (speed >= 0) {
                            setTurtleSpeed(speed);
                            displayMessage("Turtle speed set to " + speed);
                        }else{
                            displayMessage("The parameter is negative.");
                        }
                    }catch (NumberFormatException e) {
                        displayMessage("Inputted parameter cannot be converted to an integer.");
                    }
                }
                break;
            }

            case "setxcoords":{
                if (commandParts.length != 2) {
                    displayMessage("Incorrect number of parameters. Use: setxcoords <x>");
                }else{
                    try {
                        int x=Integer.parseInt(commandParts[1]);
                        setxPos(x);
                        forward(0);
                        displayMessage("X-coordinate set to " + x);
                    }catch (NumberFormatException e) {
                        displayMessage("The parameter cannot be converted to an integer.");
                    }
                }
                break;
            }

            case "setycoords":{
                if (commandParts.length != 2) {
                    displayMessage("Incorrect number of parameters. Use: setycoords <y>");
                }else{
                    try {
                        int y=Integer.parseInt(commandParts[1]);
                        setyPos(y);
                        forward(0);
                        displayMessage("Y-coordinate set to " + y);
                    }catch (NumberFormatException e) {
                        displayMessage("The parameter cannot be converted to an integer.");
                    }
                }
                break;
            }

            case "square": {
                if (commandParts.length != 2) {
                    displayMessage("Incorrect number of parameters. Use: square <length>");
                } else {
                    try {
                        int parameter = Integer.parseInt(commandParts[1]);
                        drawSquare(parameter);
                        displayMessage("Drew a square with length " + parameter);
                    } catch (NumberFormatException e) {
                        displayMessage("The parameter cannot be converted to an integer.");
                    }
                }
                break;
            }

            case "star": {
                if (commandParts.length != 2) {
                    displayMessage("Incorrect number of parameters. Use: star <length>");
                } else {
                    try {
                        int parameter = Integer.parseInt(commandParts[1]);
                        drawStar(parameter);
                        displayMessage("Drew a star with length " + parameter);
                    } catch (NumberFormatException e) {
                        displayMessage("The parameter cannot be converted to an integer.");
                    }
                }
                break;
            }

            case "spiral": {
                    if (commandParts.length != 2) {
                    displayMessage("Incorrect number of parameters. Use: spiral <length>");
                } else {
                    try {
                        int parameter = Integer.parseInt(commandParts[1]);
                        drawSpiral(parameter);
                        displayMessage("Drew a spiral with length " + parameter);
                    } catch (NumberFormatException e) {
                        displayMessage("The parameter cannot be converted to an integer.");
                    }
                }
                    break;
            }

            case "triangle": {
                if (commandParts.length == 2) {
                    try{
                        int parameter=Integer.parseInt(commandParts[1]);
                        drawTriangle(parameter);
                        displayMessage("Drew a triangle with length " + parameter);
                    }catch (NumberFormatException e){
                        displayMessage("The parameter cannot be converted to an integer.");
                    }

                } else if (commandParts.length==4) {
                    try{
                        int a=Integer.parseInt(commandParts[1]);
                        int b=Integer.parseInt(commandParts[2]);
                        int c=Integer.parseInt(commandParts[3]);

                        if (a + b <= c || a + c <= b || c + b <= a) {
                            displayMessage("These sides do not form a valid triangle.");
                            break;
                        }

                        drawTriangle(a,b,c);

                        displayMessage("Drew a triangle with length " + a + " and " + b + " and " + c);
                    }catch (NumberFormatException e){
                        displayMessage("The parameter cannot be converted to an integer.");
                    }

                } else {
                    displayMessage("Incorrect number of parameters. Use: triangle <size> or triangle <size> <size> <size>");

                }
                break;
            }



            case "displaysentence": {
                if (commandParts.length < 2) {
                    displayMessage("Incorrect number of parameters. Use: displaysentence <sentence>");
                } else {
                    String sentence = String.join(" ", Arrays.copyOfRange(commandParts, 1, commandParts.length));
                    SentenceDrawer drawer = new SentenceDrawer(this);
                    drawer.drawSentence(sentence);
                }
                break;
            }


            default: {
                displayMessage("Invalid command: " + cmd);
            }

        }
    }
}

