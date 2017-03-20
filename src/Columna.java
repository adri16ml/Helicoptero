/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.ImageIcon;
/**
 *
 * @author Adrian
 */
public class Columna {
    Rectangle2D  base;
    int altura_columna = 300;
    int ancho_columna = 40;
    private int ancho_pantalla;
    
    public Columna (int _ancho){
        base = new Rectangle2D.Double(_ancho, 0 , ancho_columna, altura_columna);
        ancho_pantalla = _ancho;
    }
    
    
    
    public void mueve(Graphics2D g2){
        mueveColumna(base);
        g2.setColor(Color.WHITE);
        g2.fill(base);

    }
    
    private void mueveColumna(Rectangle2D r){
            if (r.getX() + ancho_columna < 0){
            r.setFrame(ancho_pantalla, r.getY(),r.getWidth(), r.getHeight());
        }
        else{
            r.setFrame(r.getX()-1, r.getY(),r.getWidth(), r.getHeight());
        }
    }
}
