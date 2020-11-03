package org.example;

import javafx.scene.layout.Pane;
import javafx.scene.shape.*;

public class TetrisGameController {
    //access initialized tetris screen data
    public static final int fallHeightUnit = App.fallHeightUnit;
    public static final int blockUnit = App.blockUnit;
    public static int xSize = App.xSize;
    public static int ySize = App.ySize;
    public static int[][] screen = App.screen;

    //block animation for moving right
    public static void moveRight(Tetrominos shape){
        if(shape.a.getX() + fallHeightUnit <= xSize - blockUnit && shape.b.getX() + fallHeightUnit <= xSize - blockUnit
            && shape.c.getX() + fallHeightUnit <= xSize - blockUnit && shape.d.getX() + fallHeightUnit <= xSize - blockUnit){
            int shiftA = screen[((int) shape.a.getX() / blockUnit) +1][((int) shape.a.getY() / blockUnit)];
            int shiftB = screen[((int) shape.b.getX() / blockUnit) +1][((int) shape.b.getY() / blockUnit)];
            int shiftC = screen[((int) shape.c.getX() / blockUnit) +1][((int) shape.c.getY() / blockUnit)];
            int shiftD = screen[((int) shape.d.getX() / blockUnit) +1][((int) shape.d.getY() / blockUnit)];
            if(shiftA == 0 && shiftA == shiftB && shiftB == shiftC && shiftC == shiftD){
                shape.a.setX(shape.a.getX() + fallHeightUnit);
                shape.b.setX(shape.b.getX() + fallHeightUnit);
                shape.c.setX(shape.c.getX() + fallHeightUnit);
                shape.d.setX(shape.d.getX() + fallHeightUnit);
            }
        }
    }

    //block animation for moving left
    public static void moveLeft(Tetrominos shape){
        if(shape.a.getX() - fallHeightUnit >= 0 && shape.b.getX() - fallHeightUnit >= 0
                && shape.c.getX() - fallHeightUnit >= 0 && shape.d.getX() - fallHeightUnit >= 0){
            int shiftA = screen[((int) shape.a.getX() / blockUnit) - 1][((int) shape.a.getY() / blockUnit)];
            int shiftB = screen[((int) shape.b.getX() / blockUnit) - 1][((int) shape.b.getY() / blockUnit)];
            int shiftC = screen[((int) shape.c.getX() / blockUnit) - 1][((int) shape.c.getY() / blockUnit)];
            int shiftD = screen[((int) shape.d.getX() / blockUnit) - 1][((int) shape.d.getY() / blockUnit)];
            if(shiftA == 0 && shiftA == shiftB && shiftB == shiftC && shiftC == shiftD){
                shape.a.setX(shape.a.getX() - fallHeightUnit);
                shape.b.setX(shape.b.getX() - fallHeightUnit);
                shape.c.setX(shape.c.getX() - fallHeightUnit);
                shape.d.setX(shape.d.getX() - fallHeightUnit);
            }
        }
    }


    //making the tetrominos
    public static Tetrominos makeRect(){
        //generate color for shape
        int block = (int) (Math.random() * 100);
        String name;
        Rectangle a = new Rectangle(blockUnit - 1, blockUnit - 1),
                  b = new Rectangle(blockUnit - 1, blockUnit - 1),
                  c = new Rectangle(blockUnit - 1, blockUnit - 1),
                  d = new Rectangle(blockUnit - 1, blockUnit - 1);

        if(block < 15){
            name = "J";
            a.setX(xSize / 2 - blockUnit);
            b.setX(xSize / 2 - blockUnit);
            b.setY(blockUnit);
            c.setX(xSize / 2);
            c.setY(blockUnit);
            d.setX(xSize / 2 + blockUnit);
            d.setY(blockUnit);
        }else if (block < 30){
            name = "L";
            a.setX(xSize / 2 + blockUnit);
            b.setX(xSize / 2 - blockUnit);
            b.setY(blockUnit);
            c.setX(xSize / 2);
            c.setY(blockUnit);
            d.setX(xSize / 2 + blockUnit);
            d.setY(blockUnit);
        }else if (block < 45){
            name = "O";
            a.setX(xSize / 2 - blockUnit);
            b.setX(xSize / 2);
            c.setX(xSize / 2 - blockUnit);
            c.setY(blockUnit);
            d.setX(xSize / 2);
            d.setY(blockUnit);
        }else if (block < 60){
            name = "S";
            a.setX(xSize / 2 + blockUnit);
            b.setX(xSize / 2);
            c.setX(xSize / 2);
            c.setY(blockUnit);
            d.setX(xSize / 2 - blockUnit);
            d.setY(blockUnit);
        }else if (block < 75){
            name = "T";
            a.setX(xSize / 2 - blockUnit);
            b.setX(xSize / 2);
            c.setX(xSize / 2);
            c.setY(blockUnit);
            d.setX(xSize / 2 + blockUnit);
        }else if (block < 90){
            name = "Z";
            a.setX(xSize / 2 + blockUnit);
            b.setX(xSize / 2);
            c.setX(xSize / 2 + blockUnit);
            c.setY(blockUnit);
            d.setX(xSize / 2 + blockUnit + blockUnit);
            d.setY(blockUnit);
        }else{
            name = "I";
            a.setX(xSize / 2 - blockUnit - blockUnit);
            b.setX(xSize / 2 - blockUnit);
            c.setX(xSize / 2);
            d.setX(xSize / 2 + blockUnit);
        }

        return new Tetrominos(a, b, c, d, name);
    }





}
