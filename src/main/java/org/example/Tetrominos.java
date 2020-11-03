package org.example;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Collection;

public class Tetrominos {
    Rectangle a;
    Rectangle b;
    Rectangle c;
    Rectangle d;
    Color color;
    private String name;
    public int shape = 1;

    public Tetrominos(Rectangle a, Rectangle b, Rectangle c, Rectangle d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public Tetrominos(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String name) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.name = name;


        //set shape color
        switch (name) {
            case "J":
                color = Color.DARKBLUE;
                break;
            case "L":
                color = Color.ORANGE;
                break;
            case "I":
                color = Color.SKYBLUE;
                break;
            case "O":
                color = Color.YELLOW;
                break;
            case "S":
                color = Color.LIMEGREEN;
                break;
            case "Z":
                color = Color.RED;
                break;
            case "T":
                color = Color.DARKBLUE;
                break;

        }
        this.a.setFill(color);
        this.b.setFill(color);
        this.c.setFill(color);
        this.d.setFill(color);
    }

    //accessors and mutators
    public String getName(){
        return name;
    }

    public void changeShape(){
        if(shape != 4){
            shape++;
        }else{
            shape = 1;
        }
    }










}
