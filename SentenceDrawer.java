public class SentenceDrawer {

    private TurtleGraphics turtle;

    public SentenceDrawer(TurtleGraphics turtle) {
        this.turtle = turtle;
    }

    public void drawSentence(String word) {

        int xPosturtle = 20;
        int yPosturtle = 50;
        int lettercount = 1;
        turtle.forward(0);
        turtle.setTurtleSpeed(0);

        if (word.length() < 138) {
            for (char c : word.toUpperCase().toCharArray()) {

                turtle.pointTurtle(180);
                turtle.setxPos(xPosturtle);
                turtle.setyPos(yPosturtle);
                lettercount++;
                xPosturtle += 30;
                if (lettercount == 27) {
                    lettercount = 1;
                    xPosturtle = 20;
                    yPosturtle += 70;
                }
                switch (c) {
                    case 'A': drawA();break;
                    case 'B': drawB();break;
                    case 'C': drawC();break;
                    case 'D': drawD();break;
                    case 'E': drawE();break;
                    case 'F': drawF();break;
                    case 'G': drawG();break;
                    case 'H': drawH();break;
                    case 'I': drawI();break;
                    case 'J': drawJ();break;
                    case 'K': drawK();break;
                    case 'L': drawL();break;
                    case 'M': drawM();break;
                    case 'N': drawN();break;
                    case 'O': drawO();break;
                    case 'P': drawP();break;
                    case 'Q': drawQ(); break;
                    case 'R': drawR();break;
                    case 'S': drawS();break;
                    case 'T': drawT();break;
                    case 'U': drawU();break;
                    case 'V': drawV();break;
                    case 'W': drawW();break;
                    case 'X': drawX();break;
                    case 'Y': drawY();break;
                    case 'Z': drawZ();break;
                    case ' ': drawSpace();break;
                    default: turtle.displayMessage("Unsupported letter: " + c);
                }
            }
        }else{
            turtle.displayMessage("Please enter a sentence that is less than 138 characters");
        }

        turtle.setxPos(100000);
        turtle.setyPos(100000);

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

    }

    public void drawB() {
        turtle.forward(50);
        turtle.drawOff();
        turtle.forward(-50);
        turtle.left(90);
        turtle.drawOn();
        turtle.forward(20);
        turtle.right(90);
        turtle.forward(25);
        turtle.right(90);
        turtle.forward(20);
        turtle.drawOff();
        turtle.left(180);
        turtle.forward(20);
        turtle.right(90);
        turtle.drawOn();
        turtle.forward(25);
        turtle.right(90);
        turtle.forward(20);

    }

    public void drawC() {
        turtle.forward(50);
        turtle.drawOff();
        turtle.forward(-50);
        turtle.drawOn();
        turtle.left(90);
        turtle.forward(20);
        turtle.drawOff();
        turtle.forward(-20);
        turtle.right(90);
        turtle.forward(50);
        turtle.left(90);
        turtle.drawOn();
        turtle.forward(20);

    }

    public void drawD() {
        turtle.forward(50);
        turtle.forward(-50);
        turtle.left(50);
        turtle.forward(32);
        turtle.forward(-32);
        turtle.right(50);
        turtle.forward(50);
        turtle.left(140);
        turtle.forward(40);
    }

    public void drawE() {
        turtle.forward(50);
        turtle.drawOff();
        turtle.forward(-50);
        turtle.left(90);
        turtle.drawOn();
        turtle.forward(20);
        turtle.drawOff();
        turtle.forward(-20);
        turtle.right(90);
        turtle.forward(25);
        turtle.left(90);
        turtle.drawOn();
        turtle.forward(20);
        turtle.drawOff();
        turtle.forward(-20);
        turtle.right(90);
        turtle.forward(25);
        turtle.left(90);
        turtle.drawOn();
        turtle.forward(20);
    }

    public void drawF() {
        turtle.forward(50);
        turtle.drawOff();
        turtle.forward(-50);
        turtle.left(90);
        turtle.drawOn();
        turtle.forward(20);
        turtle.drawOff();
        turtle.forward(-20);
        turtle.right(90);
        turtle.forward(25);
        turtle.left(90);
        turtle.drawOn();
        turtle.forward(20);
    }

    public void drawG() {
        turtle.left(90);
        turtle.forward(20);
        turtle.drawOff();
        turtle.forward(-20);
        turtle.right(90);
        turtle.drawOn();
        turtle.forward(50);
        turtle.left(90);
        turtle.forward(10);
        turtle.left(90);
        turtle.forward(25);
        turtle.right(90);
        turtle.forward(10);
        turtle.right(90);
        turtle.forward(25);

    }

    public void drawH() {
        turtle.forward(50);
        turtle.drawOff();
        turtle.forward(-25);
        turtle.left(90);
        turtle.drawOn();
        turtle.forward(20);
        turtle.left(90);
        turtle.drawOff();
        turtle.forward(25);
        turtle.right(180);
        turtle.drawOn();
        turtle.forward(50);

    }

    public void drawI() {
        turtle.left(90);
        turtle.forward(20);
        turtle.drawOff();
        turtle.forward(-10);
        turtle.right(90);
        turtle.drawOn();
        turtle.forward(50);
        turtle.drawOff();
        turtle.right(90);
        turtle.forward(-10);
        turtle.drawOn();
        turtle.forward(20);

    }

    public void drawJ() {
        turtle.left(90);
        turtle.forward(20);
        turtle.drawOff();
        turtle.forward(-10);
        turtle.right(90);
        turtle.drawOn();
        turtle.forward(50);
        turtle.drawOff();
        turtle.right(90);
        turtle.drawOn();
        turtle.forward(10);
        turtle.right(90);
        turtle.forward(25);
    }

    public void drawK() {
        turtle.forward(50);
        turtle.drawOff();
        turtle.forward(-25);
        turtle.drawOn();
        turtle.left(51);
        turtle.forward(32);
        turtle.forward(-32);
        turtle.right(51);
        turtle.left(90);
        turtle.left(51);
        turtle.forward(32);


    }

    public void drawL() {
        turtle.forward(50);
        turtle.left(90);
        turtle.forward(20);
    }

    public void drawM() {
        turtle.forward(50);
        turtle.drawOff();
        turtle.forward(-50);
        turtle.left(22);
        turtle.forward(26);
        turtle.drawOn();
        turtle.forward(-26);
        turtle.right(22);
        turtle.left(90);
        turtle.drawOff();
        turtle.forward(20);
        turtle.right(112);
        turtle.drawOn();
        turtle.forward(26);
        turtle.drawOff();
        turtle.forward(-26);
        turtle.left(22);
        turtle.drawOn();
        turtle.forward(53);
    }

    public void drawN() {

        turtle.drawOff();
        turtle.forward(50);
        turtle.drawOn();
        turtle.forward(-50);
        turtle.left(22);
        turtle.forward(52);
        turtle.right(22);
        turtle.forward(-50);


    }

    public void drawO() {
        turtle.forward(50);
        turtle.left(90);
        turtle.forward(20);
        turtle.left(90);
        turtle.forward(50);
        turtle.left(90);
        turtle.forward(20);
    }

    public void drawP() {
        turtle.forward(50);
        turtle.drawOff();
        turtle.forward(-50);
        turtle.left(90);
        turtle.drawOn();
        turtle.forward(20);
        turtle.right(90);
        turtle.forward(25);
        turtle.right(90);
        turtle.forward(20);

    }

    public void drawQ() {
        turtle.forward(40);
        turtle.left(90);
        turtle.forward(20);
        turtle.left(90);
        turtle.forward(40);
        turtle.left(90);
        turtle.forward(20);
        turtle.drawOff();
        turtle.forward(-10);
        turtle.left(90);
        turtle.forward(40);
        turtle.left(45);
        turtle.drawOn();
        turtle.forward(20);
    }
    public void drawR() {
        turtle.forward(50);
        turtle.drawOff();
        turtle.forward(-50);
        turtle.left(90);
        turtle.drawOn();
        turtle.forward(20);
        turtle.right(90);
        turtle.forward(25);
        turtle.right(90);
        turtle.forward(20);
        turtle.left(135);
        turtle.forward(35);
    }

    public void drawS() {
        turtle.left(90);
        turtle.forward(20);
        turtle.drawOff();
        turtle.forward(-20);
        turtle.right(90);
        turtle.drawOn();
        turtle.forward(25);
        turtle.left(90);
        turtle.forward(20);
        turtle.right(90);
        turtle.forward(25);
        turtle.right(90);
        turtle.forward(20);

    }

    public void drawT() {
        turtle.left(90);
        turtle.forward(20);
        turtle.drawOff();
        turtle.forward(-10);
        turtle.right(90);
        turtle.drawOn();
        turtle.forward(50);

    }

    public void drawU() {
        turtle.forward(50);
        turtle.left(90);
        turtle.forward(20);
        turtle.left(90);
        turtle.forward(50);
    }

    public void drawV() {
        turtle.left(12);
        turtle.forward(60);
        turtle.right(12);
        turtle.left(112);
        turtle.right(22);
        turtle.left(78);
        turtle.forward(55);


    }

    public void drawW() {

        turtle.forward(50);
        turtle.left(135);
        turtle.forward(18);
        turtle.right(90);
        turtle.forward(18);
        turtle.left(135);
        turtle.forward(50);

    }

    public void drawX() {
        turtle.left(22);
        turtle.forward(52);
        turtle.right(22);
        turtle.drawOff();
        turtle.forward(-50);
        turtle.right(20);
        turtle.drawOn();
        turtle.forward(52);

    }

    public void drawY() {
        turtle.left(50);
        turtle.forward(30);
        turtle.left(120);
        turtle.forward(25);
        turtle.forward(-50);
    }

    public void drawZ() {
        turtle.left(90);
        turtle.forward(20);
        turtle.right(112);
        turtle.forward(52);
        turtle.left(112);
        turtle.forward(20);

    }

    public void drawSpace() {

        turtle.drawOff();
        turtle.forward(20);
        turtle.drawOn();
    }

}
