package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

/**
 * JavaFX App
 */
public class App extends Application {


    static void setRoot(String fxml) throws IOException {
       /*tetris doesnt work through menu, currently working on it */
        //tetrisScene.setRoot();
        BBscene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }




    public static void main(String[] args) {
        launch();
    }


    //instances for blockbreaker
    private static Scene BBscene;
    public static boolean goLeft = false;
    public static boolean goRight = false;
    public static boolean start = false;

    //instances for Tetris
    public static final int fallHeightUnit = 25;
    public static final int blockUnit = 25;
    public static int xSize = blockUnit * 12;
    public static int ySize = blockUnit * 24;
    public static int[][] screen = new int[xSize/blockUnit][ySize/blockUnit];

    private static Pane group = new Pane();
    private static Tetrominos object;
    private static Scene tetrisScene = new Scene(group, xSize+150, ySize);
    public static int score = 0;
    public static int top = 0;
    public static boolean game = true;
    private static Tetrominos nextObj = TetrisGameController.makeRect();
    private static int lines = 0;
    //create scene to begin game


    @Override
    public void start(Stage stage) throws IOException {
        //Block Breaker
        BBscene = new Scene(loadFXML("mainMenu"));
        stage.setScene(BBscene);
        BBscene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()) {
                    case LEFT: goLeft = true;
                        break;
                    case RIGHT: goRight = true;
                        break;
                    case SPACE: start = true;
                        break;
                    default:
                        break;
                }
            }
        });

        BBscene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()) {
                    case LEFT: goLeft = false;
                        break;
                    case RIGHT: goRight = false;
                        break;
                    default:
                        break;
                }
            }
        });

        //tetris
        for(int[] a: screen){
            Arrays.fill(a,0);
        }
        //create score and number of lines
        Line line = new Line(xSize, 0, xSize, ySize);
        Text pointScore = new Text("Points: ");
        pointScore.setStyle("-fx-font: 20 American Typwriter");
        pointScore.setY(50);
        pointScore.setX(xSize + 5);
        Text level = new Text("Lines: ");
        level.setX(xSize);
        level.setY(100);
        level.setFill(Color.GREEN);
        group.getChildren().addAll(pointScore, line, level);


        //create first block and stage
        Tetrominos a = nextObj;
        group.getChildren().addAll(a.a, a.b, a.c, a.d);
        moveOnKeyPress(a);
        object = a;
        nextObj = TetrisGameController.makeRect();
        stage.setScene(tetrisScene);
        stage.setTitle("T E T R I S");

        stage.show();

        //Timer
        Timer countDown = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //this sets the boundaries for the max height the tetrominos can reach until game over
                        if(object.a.getY() == 0 || object.b.getY() == 0|| object.c.getY() == 0 || object.d.getY() == 0){
                            top++;
                        }else{
                            top = 0;
                        }
                        //Game Over
                        if(top == 2){
                            Text endGame = new Text("You Lost");
                            endGame.setFill(Color.RED);
                            endGame.setStyle("-fx-font: 20 American Typewriter;");
                            endGame.setY(250);
                            endGame.setX(10);
                            group.getChildren().add(endGame);
                            game = false;
                        }
                        //leave game
                        if(game){
                            MoveDown(object);
                            pointScore.setText("Score: " + pointScore);
                            level.setText("Lines: " + Integer.toString(lines));
                        }
                    }
                });
            }
        };
        countDown.schedule(task, 0, 300);

    }
    private void moveOnKeyPress(Tetrominos shape){
        tetrisScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch(keyEvent.getCode()){
                    case RIGHT:
                        TetrisGameController.moveRight(shape);
                        break;
                    case DOWN:
                        MoveDown(shape);
                        break;
                    case LEFT:
                        TetrisGameController.moveLeft(shape);
                        break;
                    case UP:
                        MoveRotate(shape);
                        break;


                }
            }
        });
    }


    private void MoveRotate(Tetrominos shape) {
        int f = shape.shape;
        Rectangle a = shape.a;
        Rectangle b = shape.b;
        Rectangle c = shape.c;
        Rectangle d = shape.d;
        switch (shape.getName()) {
            case "J":
                if (f == 1 && cB(a, 1, -1) && cB(c, -1, -1) && cB(d, -2, -2)) {
                    MoveRight(shape.a);
                    MoveDown(shape.a);
                    MoveDown(shape.c);
                    MoveLeft(shape.c);
                    MoveDown(shape.d);
                    MoveDown(shape.d);
                    MoveLeft(shape.d);
                    MoveLeft(shape.d);
                    shape.changeShape();
                    break;
                }
                if (f == 2 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, -2, 2)) {
                    MoveDown(shape.a);
                    MoveLeft(shape.a);
                    MoveLeft(shape.c);
                    MoveUp(shape.c);
                    MoveLeft(shape.d);
                    MoveLeft(shape.d);
                    MoveUp(shape.d);
                    MoveUp(shape.d);
                    shape.changeShape();
                    break;
                }
                if (f == 3 && cB(a, -1, 1) && cB(c, 1, 1) && cB(d, 2, 2)) {
                    MoveLeft(shape.a);
                    MoveUp(shape.a);
                    MoveUp(shape.c);
                    MoveRight(shape.c);
                    MoveUp(shape.d);
                    MoveUp(shape.d);
                    MoveRight(shape.d);
                    MoveRight(shape.d);
                    shape.changeShape();
                    break;
                }
                if (f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 2, -2)) {
                    MoveUp(shape.a);
                    MoveRight(shape.a);
                    MoveRight(shape.c);
                    MoveDown(shape.c);
                    MoveRight(shape.d);
                    MoveRight(shape.d);
                    MoveDown(shape.d);
                    MoveDown(shape.d);
                    shape.changeShape();
                    break;
                }
                break;
            case "L":
                if (f == 1 && cB(a, 1, -1) && cB(c, 1, 1) && cB(b, 2, 2)) {
                    MoveRight(shape.a);
                    MoveDown(shape.a);
                    MoveUp(shape.c);
                    MoveRight(shape.c);
                    MoveUp(shape.b);
                    MoveUp(shape.b);
                    MoveRight(shape.b);
                    MoveRight(shape.b);
                    shape.changeShape();
                    break;
                }
                if (f == 2 && cB(a, -1, -1) && cB(b, 2, -2) && cB(c, 1, -1)) {
                    MoveDown(shape.a);
                    MoveLeft(shape.a);
                    MoveRight(shape.b);
                    MoveRight(shape.b);
                    MoveDown(shape.b);
                    MoveDown(shape.b);
                    MoveRight(shape.c);
                    MoveDown(shape.c);
                    shape.changeShape();
                    break;
                }
                if (f == 3 && cB(a, -1, 1) && cB(c, -1, -1) && cB(b, -2, -2)) {
                    MoveLeft(shape.a);
                    MoveUp(shape.a);
                    MoveDown(shape.c);
                    MoveLeft(shape.c);
                    MoveDown(shape.b);
                    MoveDown(shape.b);
                    MoveLeft(shape.b);
                    MoveLeft(shape.b);
                    shape.changeShape();
                    break;
                }
                if (f == 4 && cB(a, 1, 1) && cB(b, -2, 2) && cB(c, -1, 1)) {
                    MoveUp(shape.a);
                    MoveRight(shape.a);
                    MoveLeft(shape.b);
                    MoveLeft(shape.b);
                    MoveUp(shape.b);
                    MoveUp(shape.b);
                    MoveLeft(shape.c);
                    MoveUp(shape.c);
                    shape.changeShape();
                    break;
                }
                break;
            case "O":
                break;
            case "S":
                if (f == 1 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2)) {
                    MoveDown(shape.a);
                    MoveLeft(shape.a);
                    MoveLeft(shape.c);
                    MoveUp(shape.c);
                    MoveUp(shape.d);
                    MoveUp(shape.d);
                    shape.changeShape();
                    break;
                }
                if (f == 2 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)) {
                    MoveUp(shape.a);
                    MoveRight(shape.a);
                    MoveRight(shape.c);
                    MoveDown(shape.c);
                    MoveDown(shape.d);
                    MoveDown(shape.d);
                    shape.changeShape();
                    break;
                }
                if (f == 3 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2)) {
                    MoveDown(shape.a);
                    MoveLeft(shape.a);
                    MoveLeft(shape.c);
                    MoveUp(shape.c);
                    MoveUp(shape.d);
                    MoveUp(shape.d);
                    shape.changeShape();
                    break;
                }
                if (f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)) {
                    MoveUp(shape.a);
                    MoveRight(shape.a);
                    MoveRight(shape.c);
                    MoveDown(shape.c);
                    MoveDown(shape.d);
                    MoveDown(shape.d);
                    shape.changeShape();
                    break;
                }
                break;
            case "T":
                if (f == 1 && cB(a, 1, 1) && cB(d, -1, -1) && cB(c, -1, 1)) {
                    MoveUp(shape.a);
                    MoveRight(shape.a);
                    MoveDown(shape.d);
                    MoveLeft(shape.d);
                    MoveLeft(shape.c);
                    MoveUp(shape.c);
                    shape.changeShape();
                    break;
                }
                if (f == 2 && cB(a, 1, -1) && cB(d, -1, 1) && cB(c, 1, 1)) {
                    MoveRight(shape.a);
                    MoveDown(shape.a);
                    MoveLeft(shape.d);
                    MoveUp(shape.d);
                    MoveUp(shape.c);
                    MoveRight(shape.c);
                    shape.changeShape();
                    break;
                }
                if (f == 3 && cB(a, -1, -1) && cB(d, 1, 1) && cB(c, 1, -1)) {
                    MoveDown(shape.a);
                    MoveLeft(shape.a);
                    MoveUp(shape.d);
                    MoveRight(shape.d);
                    MoveRight(shape.c);
                    MoveDown(shape.c);
                    shape.changeShape();
                    break;
                }
                if (f == 4 && cB(a, -1, 1) && cB(d, 1, -1) && cB(c, -1, -1)) {
                    MoveLeft(shape.a);
                    MoveUp(shape.a);
                    MoveRight(shape.d);
                    MoveDown(shape.d);
                    MoveDown(shape.c);
                    MoveLeft(shape.c);
                    shape.changeShape();
                    break;
                }
                break;
            case "Z":
                if (f == 1 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0)) {
                    MoveUp(shape.b);
                    MoveRight(shape.b);
                    MoveLeft(shape.c);
                    MoveUp(shape.c);
                    MoveLeft(shape.d);
                    MoveLeft(shape.d);
                    shape.changeShape();
                    break;
                }
                if (f == 2 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0)) {
                    MoveDown(shape.b);
                    MoveLeft(shape.b);
                    MoveRight(shape.c);
                    MoveDown(shape.c);
                    MoveRight(shape.d);
                    MoveRight(shape.d);
                    shape.changeShape();
                    break;
                }
                if (f == 3 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0)) {
                    MoveUp(shape.b);
                    MoveRight(shape.b);
                    MoveLeft(shape.c);
                    MoveUp(shape.c);
                    MoveLeft(shape.d);
                    MoveLeft(shape.d);
                    shape.changeShape();
                    break;
                }
                if (f == 4 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0)) {
                    MoveDown(shape.b);
                    MoveLeft(shape.b);
                    MoveRight(shape.c);
                    MoveDown(shape.c);
                    MoveRight(shape.d);
                    MoveRight(shape.d);
                    shape.changeShape();
                    break;
                }
                break;
            case "I":
                if (f == 1 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)) {
                    MoveUp(shape.a);
                    MoveUp(shape.a);
                    MoveRight(shape.a);
                    MoveRight(shape.a);
                    MoveUp(shape.b);
                    MoveRight(shape.b);
                    MoveDown(shape.d);
                    MoveLeft(shape.d);
                    shape.changeShape();
                    break;
                }
                if (f == 2 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1)) {
                    MoveDown(shape.a);
                    MoveDown(shape.a);
                    MoveLeft(shape.a);
                    MoveLeft(shape.a);
                    MoveDown(shape.b);
                    MoveLeft(shape.b);
                    MoveUp(shape.d);
                    MoveRight(shape.d);
                    shape.changeShape();
                    break;
                }
                if (f == 3 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)) {
                    MoveUp(shape.a);
                    MoveUp(shape.a);
                    MoveRight(shape.a);
                    MoveRight(shape.a);
                    MoveUp(shape.b);
                    MoveRight(shape.b);
                    MoveDown(shape.d);
                    MoveLeft(shape.d);
                    shape.changeShape();
                    break;
                }
                if (f == 4 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1)) {
                    MoveDown(shape.a);
                    MoveDown(shape.a);
                    MoveLeft(shape.a);
                    MoveLeft(shape.a);
                    MoveDown(shape.b);
                    MoveLeft(shape.b);
                    MoveUp(shape.d);
                    MoveRight(shape.d);
                    shape.changeShape();
                    break;
                }
                break;
        }
    }
    private void RemoveRows(Pane pane) {
        ArrayList<Node> rects = new ArrayList<Node>();
        ArrayList<Integer> row = new ArrayList<Integer>();
        ArrayList<Node> newrects = new ArrayList<Node>();
        int full = 0;
        for (int i = 0; i < screen[0].length; i++) {
            for (int j = 0; j < screen.length; j++) {
                if (screen[j][i] == 1)
                    full++;
            }
            if (full == screen.length)
                row.add(i);
            //row.add(i + lines.size());
            full = 0;
        }
        if (row.size() > 0)
            do {
                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle)
                        rects.add(node);
                }
                score += 50;
                lines++;

                for (Node node : rects) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() == row.get(0) * blockUnit) {
                        screen[(int) a.getX() / blockUnit][(int) a.getY() / blockUnit] = 0;
                        pane.getChildren().remove(node);
                    } else
                        newrects.add(node);
                }

                for (Node node : newrects) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() < row.get(0) * blockUnit) {
                        screen[(int) a.getX() / blockUnit][(int) a.getY() / blockUnit] = 0;
                        a.setY(a.getY() + blockUnit);
                    }
                }
                row.remove(0);
                rects.clear();
                newrects.clear();
                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle)
                        rects.add(node);
                }
                for (Node node : rects) {
                    Rectangle a = (Rectangle) node;
                    try {
                        screen[(int) a.getX() / blockUnit][(int) a.getY() / blockUnit] = 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
                rects.clear();
            } while (row.size() > 0);
    }

    private void MoveDown(Rectangle rect) {
        if (rect.getY() + fallHeightUnit < ySize)
            rect.setY(rect.getY() + fallHeightUnit);

    }

    private void MoveRight(Rectangle rect) {
        if (rect.getX() + fallHeightUnit <= xSize - blockUnit)
            rect.setX(rect.getX() + fallHeightUnit);
    }

    private void MoveLeft(Rectangle rect) {
        if (rect.getX() - fallHeightUnit >= 0)
            rect.setX(rect.getX() - fallHeightUnit);
    }

    private void MoveUp(Rectangle rect) {
        if (rect.getY() - fallHeightUnit > 0)
            rect.setY(rect.getY() - fallHeightUnit);
    }

    private void MoveDown(Tetrominos form) {
        if (form.a.getY() == ySize - blockUnit || form.b.getY() == ySize - blockUnit || form.c.getY() == ySize - blockUnit
                || form.d.getY() == ySize - blockUnit || moveA(form) || moveB(form) || moveC(form) || moveD(form)) {
            screen[(int) form.a.getX() / blockUnit][(int) form.a.getY() / blockUnit] = 1;
            screen[(int) form.b.getX() / blockUnit][(int) form.b.getY() / blockUnit] = 1;
            screen[(int) form.c.getX() / blockUnit][(int) form.c.getY() / blockUnit] = 1;
            screen[(int) form.d.getX() / blockUnit][(int) form.d.getY() / blockUnit] = 1;
            RemoveRows(group);

            Tetrominos a = nextObj;
            nextObj = TetrisGameController.makeRect();
            object = a;
            group.getChildren().addAll(a.a, a.b, a.c, a.d);
            moveOnKeyPress(a);
        }

        if (form.a.getY() + fallHeightUnit < ySize && form.b.getY() + fallHeightUnit < ySize && form.c.getY() + fallHeightUnit < ySize
                && form.d.getY() + fallHeightUnit < ySize) {
            int shiftA = screen[(int) form.a.getX() / blockUnit][((int) form.a.getY() / blockUnit) + 1];
            int shiftB = screen[(int) form.b.getX() / blockUnit][((int) form.b.getY() / blockUnit) + 1];
            int shiftC = screen[(int) form.c.getX() / blockUnit][((int) form.c.getY() / blockUnit) + 1];
            int shiftD = screen[(int) form.d.getX() / blockUnit][((int) form.d.getY() / blockUnit) + 1];
            if (shiftA == 0 && shiftA == shiftB && shiftB == shiftC && shiftC == shiftD) {
                form.a.setY(form.a.getY() + fallHeightUnit);
                form.b.setY(form.b.getY() + fallHeightUnit);
                form.c.setY(form.c.getY() + fallHeightUnit);
                form.d.setY(form.d.getY() + fallHeightUnit);
            }
        }
    }

    private boolean moveA(Tetrominos shape) {
        return (screen[(int) shape.a.getX() / blockUnit][((int) shape.a.getY() / blockUnit) + 1] == 1);
    }

    private boolean moveB(Tetrominos shape) {
        return (screen[(int) shape.b.getX() / blockUnit][((int) shape.b.getY() / blockUnit) + 1] == 1);
    }

    private boolean moveC(Tetrominos shape) {
        return (screen[(int) shape.c.getX() / blockUnit][((int) shape.c.getY() / blockUnit) + 1] == 1);
    }

    private boolean moveD(Tetrominos shape) {
        return (screen[(int) shape.d.getX() / blockUnit][((int) shape.d.getY() / blockUnit) + 1] == 1);
    }

    private boolean cB(Rectangle rect, int x, int y) {
        boolean xb = false;
        boolean yb = false;
        if (x >= 0)
            xb = rect.getX() + x * blockUnit <= xSize - blockUnit;
        if (x < 0)
            xb = rect.getX() + x * blockUnit >= 0;
        if (y >= 0)
            yb = rect.getY() - y * blockUnit > 0;
        if (y < 0)
            yb = rect.getY() + y * blockUnit < ySize;
        return xb && yb && screen[((int) rect.getX() / blockUnit) + x][((int) rect.getY() / blockUnit) - y] == 0;
    }



}