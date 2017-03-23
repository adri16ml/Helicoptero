/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;

/**
 *
 * @author Adrian
 */
public class Helicoptero extends Ellipse2D.Double{
    int yVelocidad = -2;
    Image imagen1, imagen2;
    int rotacion = 0;
    //creamos el avion(helicoptero) del juego con sus dos imagenes escaladas a su tamaño
    public Helicoptero(int _radio){
        super(100, 100, 50, 50);
        imagen1 = (new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/plane.png"))
                .getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT))).getImage();
        imagen2 = (new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/plane.png"))
                .getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT))).getImage();
        
    }
    //iniciamos el movimiento del avion al subir, se aumentara en +9
    public void sube(){
        this.yVelocidad += 9;
    }
    
    public void mueve(Graphics2D g2, int imagenhelicoptero){
        AffineTransform trans = new AffineTransform();
        //indicamos la rotacion y la velocidad, con lo cual la imagen cambia 
        rotacion -= yVelocidad;
        if (rotacion < -45) { rotacion = -45;}//si la rotación es menor que -45 lo deja en -45
        if (rotacion > 45) { rotacion = 45; } //si la rotación es mayor que 45 lo deja en 45     
        trans.translate(x, y);  //mueve la imagen a la posición en que tiene que ser dibujada
        trans.rotate( Math.toRadians(rotacion), width/2, height/2 );  //gira la imagen tantos grados como ponga rotación
        
        this.y = this.y - yVelocidad;
        //pongo un tope para que no se salga por el techo
        if (this.y < 0) {this.y = 0;}
        
        if (imagenhelicoptero < 15){
            g2.drawImage(imagen1, trans,null);}
        else{
            g2.drawImage(imagen2, trans, null);
        }
        yVelocidad --;
        if (yVelocidad < -3){yVelocidad = -1;}  //si la velocidad es menor que -3 la deja en -1
    }
    
    public boolean chequeaColision(Columna c){
        return ( this.intersects(c.base));
    }

}
