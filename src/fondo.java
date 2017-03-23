import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adrian
 */
public class fondo{
    Rectangle2D fondoPantalla;
    Image fondo;
    int anchoPantalla, altoPantalla;
    public fondo(int _altoPantalla, int _anchoPantalla){
        altoPantalla = _altoPantalla;
        anchoPantalla = _anchoPantalla;
        
        fondoPantalla = new Rectangle2D.Double(0, 0, _anchoPantalla, _altoPantalla);
        precargaImagen();
    }
    
    private void precargaImagen(){
        fondo = (new ImageIcon(getClass().getResource("/imagenes/juego .png")).getImage());  
    }
    
    public void ponImagenDeFondo(Graphics2D g2){
        fondoPantalla.setFrame(0,0, anchoPantalla, altoPantalla);
        g2.setColor(Color.red);
        g2.fill(fondoPantalla);
        
        g2.drawImage(fondo, (int)fondoPantalla.getX(), (int)fondoPantalla.getY(), null);
    }
}