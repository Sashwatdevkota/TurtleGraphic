public class LetterDrawer {

    private TurtleGraphics turtle;

    public LetterDrawer(TurtleGraphics turtle) {
        this.turtle = turtle;
    }

    public void drawLetters(String word) {

        int xPosturtle = 20;
        int yPosturtle = 50;
        int lettercount = 1;
        turtle.forward(0);


        if (word.length() < 134) {
            for (char c : word.toUpperCase().toCharArray()) {

                turtle.setTurtleSpeed(0);
                turtle.pointTurtle(180);
                turtle.setxPos(xPosturtle);
                turtle.setyPos(yPosturtle);
                lettercount++;
                xPosturtle += 30;
                if (lettercount == 27) {
                    lettercount = 0;
                    xPosturtle = 20;
                    yPosturtle += 70;
                }
                switch (c) {
                    case 'A':
                        drawA();
                        break;
                    case 'B':
                        drawB();
                        break;
                    case 'C':
                        drawC();
                        break;
                    case 'D':
                        drawD();
                        break;
                    case 'E':
                        drawE();
                        break;
                    case 'F':
                        drawF();
                        break;
                    case 'G':
                        drawG();
                        break;
                    case 'H':
                        drawH();
                        break;
                    case 'I':
                        drawI();
                        break;
                    case 'J':
                        drawJ();
                        break;
                    case 'K':
                        drawK();
                        break;
                    case 'L':
                        drawL();
                        break;
                    case 'M':
                        drawM();
                        break;
                    case 'N':
                        drawN();
                        break;
                    case 'O':
                        drawO();
                        break;
                    case 'P':
                        drawP();
                        break;
                    case 'Q':
                        drawQ();
                        break;
                    case 'R':
                        drawR();
                        break;
                    case 'S':
                        drawS();
                        break;
                    case 'T':
                        drawT();
                        break;
                    case 'U':
                        drawU();
                        break;
                    case 'V':
                        drawV();
                        break;
                    case 'W':
                        drawW();
                        break;
                    case 'X':
                        drawX();
                        break;
                    case 'Y':
                        drawY();
                        break;
                    case 'Z':
                        drawZ();
                        break;
                    case ' ':
                        drawSpace();
                        break;
                    default:
                        turtle.displayMessage("Unsupported letter: " + c);
                }
            }
        }else{
            turtle.displayMessage("Please enter a setence that is less than 133 characters");
        }
    }
    public void drawA() {

        turtle.forward(50);
        turtle.drawOff();
        turtle.forward(-50);
        turtle.drawOn();
        turtle.left(90);
        turtle.forward(20);
        turtle.right(90);
        turtle.forward(50);
        turtle.drawOff();
        turtle.forward(-25);
        turtle.right(90);
        turtle.drawOn();
        turtle.forward(20);
        //turtle.displayMessage("Drew Letter A");

    }

    public void drawB() {
        // Drawing code for B
    }

    public void drawC() {
        // Drawing code for C
    }

    public void drawD() {
        // Drawing code for D
    }

    public void drawE() {
        // Drawing code for E
    }

    public void drawF() {
        // Drawing code for F
    }

    public void drawG() {
        // Drawing code for G
    }

    public void drawH() {
        // Drawing code for H
    }

    public void drawI() {
        // Drawing code for I
    }

    public void drawJ() {
        // Drawing code for J
    }

    public void drawK() {
        // Drawing code for K
    }

    public void drawL() {
        // Drawing code for L
    }

    public void drawM() {
        // Drawing code for M
    }

    public void drawN() {
        // Drawing code for N
    }

    public void drawO() {
        // Drawing code for O
    }

    public void drawP() {
        // Drawing code for P
    }

    public void drawQ() {
        // Drawing code for Q
    }

    public void drawR() {
        // Drawing code for R
    }

    public void drawS() {
        // Drawing code for S
    }

    public void drawT() {
        // Drawing code for T
    }

    public void drawU() {
        // Drawing code for U
    }

    public void drawV() {
        // Drawing code for V
    }

    public void drawW() {
        // Drawing code for W
    }

    public void drawX() {
        // Drawing code for X
    }

    public void drawY() {
        // Drawing code for Y
    }

    public void drawZ() {
        // Drawing code for Z
    }

    public void drawSpace() {
        // Drawing code for
    }
}
