package shapes;

// A Shape can be painted

import java.awt.Graphics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public abstract class Shape {    

    // public abstract Shape(BufferedReader br, String fileVersion) throws IOException {
    public abstract void save(BufferedWriter bw) throws IOException;
 
    public abstract void paintComponent(Graphics g);
}
