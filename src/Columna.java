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
    //declaramos los objetos e integer que utilizaremos mas adelante 
    Rectangle2D capitel, base;
    Ellipse2D circuloInferior, circuloSuperior;
    int hueco = 120;
    int altura_columna = 500;
    int ancho_columna = 79;
    private int ancho_pantalla;
    Image col_abajo,imagen3;
        
    public Columna (int _ancho, int _anchoPantalla){  
        // iniciamos la columna con su posicion inicial que se la pasaremos con un integer y el ancho de la pantalla
        posicionInicialColumna(_ancho);
        ancho_pantalla = _anchoPantalla;
        //precargamos la imagen que va a tener la columna en .png
        precargaImagenes();
    }
    
    private void posicionInicialColumna(int _ancho){
        //iniciamos el metodo de colocacion de las columnas que van a ser aleatorias
        Random aleatorio = new Random();
        //indicamos el int de aleatoriedad que van a teener
        int desplazamiento = aleatorio.nextInt(200)+200;
        // indicamos las medidas de la columna y su posicion 
        base = new Rectangle2D.Double(_ancho,desplazamiento - ancho_columna, ancho_columna, altura_columna);

    }
    
    private void precargaImagenes(){
//    cargamos las imagenes que va a tener la columna, en este caso tendra 2 la inicial y cuando el avion impacte
        col_abajo = (new ImageIcon(new ImageIcon(
                getClass().getResource("/imagenes/arabe.png"))
                .getImage().getScaledInstance(79, 500, Image.SCALE_DEFAULT)))
                .getImage();  
        //imagen del avion al impactar en la columna
        imagen3 = (new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/fidel.png"))
                .getImage().getScaledInstance(33, 23, Image.SCALE_DEFAULT))).getImage();
    }
    // iniciamos un metodo para mover las columnas con un boolean que nos indica si el avion impacta(true) o no toca
    //(false)
    public boolean mueve(Graphics2D g2, Helicoptero p, boolean explosion){
        mueveColumna();
        // si el avion impacta cambia la imagen de la columna 
        if(explosion){
        g2.drawImage(imagen3,(int) base.getX(),(int) base.getY(), null);
        }
        // si no impacta se mantiene la imagen 
        else{
        g2.drawImage(col_abajo, (int)base.getX(), (int)base.getY(), null);}
        //si el avion está en la columna, subo 1 el marcador
        return (base.getX() == p.x);
    
    }
    
    
    private void mueveColumna(){
        // metodo para indicar como se mueve la columna
        if (base.getX() + ancho_columna < 0){
            posicionInicialColumna(ancho_pantalla);
        }
        else{
            base.setFrame(base.getX()-1, base.getY(),base.getWidth(), base.getHeight());     
        }
    }
}
