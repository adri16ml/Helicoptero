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
    
    Rectangle2D capitel, base;
    Ellipse2D circuloInferior, circuloSuperior;
    int hueco = 120;
    int altura_columna = 500;
    int ancho_columna = 79;
    private int ancho_pantalla;
    Image col_abajo, col_arriba,imagen3;
        
    public Columna (int _ancho, int _anchoPantalla){       
        posicionInicialColumna(_ancho);
        ancho_pantalla = _anchoPantalla;
        precargaImagenes();
    }
    
    private void posicionInicialColumna(int _ancho){
        Random aleatorio = new Random();
        int desplazamiento = aleatorio.nextInt(200)+200;
        base = new Rectangle2D.Double(_ancho,desplazamiento - ancho_columna/2, ancho_columna, altura_columna);

    }
    
    private void precargaImagenes(){
//         
        col_abajo = (new ImageIcon(new ImageIcon(
                getClass().getResource("/imagenes/build.png"))
                .getImage().getScaledInstance(79, 500, Image.SCALE_DEFAULT)))
                .getImage();  
        imagen3 = (new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/explosion.png"))
                .getImage().getScaledInstance(33, 23, Image.SCALE_DEFAULT))).getImage();
    }
    
    public boolean mueve(Graphics2D g2, Helicoptero p, boolean explosion){
        mueveColumna();
        if(explosion){
        g2.drawImage(imagen3,(int) base.getX(),(int) base.getY(), null);
        }
        else{
        g2.drawImage(col_abajo, (int)base.getX(), (int)base.getY(), null);}
        //si el pájaro está en la columna, subo 1 el marcador
        return (base.getX() == p.x);
    
    }
    
    
    private void mueveColumna(){
        
        if (base.getX() + ancho_columna < 0){
            posicionInicialColumna(ancho_pantalla);
        }
        else{
            base.setFrame(base.getX()-1, base.getY(),base.getWidth(), base.getHeight());     
        }
    }
}
