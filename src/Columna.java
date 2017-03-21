/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 *
 * @author xp
 */
public class Columna {
    
    Rectangle2D capitel, base;
    Ellipse2D circuloInferior, circuloSuperior;
    int hueco = 120;
    int altura_columna = 200;
    int ancho_columna = 60;
    private int ancho_pantalla;
    Image col_abajo, col_arriba;
        
    public Columna (int _ancho, int _anchoPantalla){       
        posicionInicialColumna(_ancho);
        ancho_pantalla = _anchoPantalla;
        //precargaImagenes();
    }
    
    private void posicionInicialColumna(int _ancho){
        Random aleatorio = new Random();
        int desplazamiento = aleatorio.nextInt(200);
        base = new Rectangle2D.Double(_ancho, altura_columna + desplazamiento + ancho_columna/2, ancho_columna, altura_columna);
    }
    
//    private void precargaImagenes(){
//         
//        col_abajo = (new ImageIcon(new ImageIcon(
//                getClass().getResource("/imagenes/pipe_bottom.png"))
//                .getImage().getScaledInstance(79, 500, Image.SCALE_DEFAULT)))
//                .getImage();
       
//    }
    
    public boolean mueve(Graphics2D g2, Helicopter p){
        mueveColumna();
        g2.setColor(Color.BLUE);
        g2.fill(base);
//        g2.drawImage(col_abajo, (int)base.getX(), (int)base.getY()-ancho_columna/2, null);
//        g2.drawImage(col_arriba, (int)capitel.getX(), (int)capitel.getY()+ancho_columna/2, null);
        //si el pájaro está en la columna, subo 1 el marcador
        return (base.getX() == p.x);
       //oculto la fisica del juego
//        g2.fill(circuloInferior);
//        g2.fill(circuloSuperior);
//        g2.setColor(Color.PINK);
//        g2.fill(capitel);
//        g2.fill(base);
      
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
