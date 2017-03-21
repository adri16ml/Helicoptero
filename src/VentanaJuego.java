/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
/**
 *
 * @author Adrian
 */
public class VentanaJuego extends javax.swing.JFrame {
    
    boolean gameOver = false;
    
    Helicopter miHelicopter = new Helicopter(30, Color.WHITE);
    

    static int ANCHOPANTALLA = 700;
    static int ALTOPANTALLA = 400;
    static int SEPARACION_COLUMNAS = 170 ;
    int numColumnas = 4;
    int puntuacion = 0;
    
    //array de columnas
    Columna[] columnas = new Columna[numColumnas];
//
//    //los dos suelos para hacer el truco de que parezca infinito
//    Suelo miSuelo1 = new Suelo(0, ALTOPANTALLA * 0.60);
//    Suelo miSuelo2 = new Suelo(miSuelo1.getWidth(), ALTOPANTALLA * 0.60);
//    
    BufferedImage buffer = null;
    Graphics2D bufferGraphics, lienzoGraphics = null;

//    //TEMPORIZADOR DEL JUEGO: AQUI SE LLAMA A LA ANIMACIÓN
      Timer temporizador = new Timer(5,new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            bucleDelJuego();
        }
    });
    /**
     * Creates new form VentanaJuego
     */
    public VentanaJuego() {
        initComponents();
        inicializaBuffers();
        
        for (int i=0; i<numColumnas; i++){
            columnas[i] = new Columna(ANCHOPANTALLA + i*SEPARACION_COLUMNAS, ANCHOPANTALLA);
        }
        temporizador.start();
    }
    private void inicializaBuffers(){
        lienzoGraphics = (Graphics2D) jPanel1.getGraphics();
        buffer = (BufferedImage) jPanel1.createImage(ANCHOPANTALLA, ALTOPANTALLA);
        bufferGraphics = buffer.createGraphics();
        bufferGraphics.setColor(Color.BLACK);
        bufferGraphics.fillRect(0, 0, ANCHOPANTALLA, ALTOPANTALLA);
    }
    
    private void bucleDelJuego(){
        //limpio la pantalla
        bufferGraphics.setColor(Color.BLACK);
        bufferGraphics.fillRect(0, 0, ANCHOPANTALLA, ALTOPANTALLA); 
        //dibujo el pájaro en su nueva posición
        miHelicopter.mueve(bufferGraphics);
        //desplazo las columnas a la izquierda. Si alguna choca, incremento en 1 el marcador
        for (int i=0; i<numColumnas; i++){
            if (columnas[i].mueve(bufferGraphics, miHelicopter)){
                puntuacion++;
            }
//        }
//        //avanza el suelo 
//        miSuelo1.mueve(bufferGraphics);
//        miSuelo2.mueve(bufferGraphics);
//        //dibuja el marcador
        bufferGraphics.setFont(new Font("Courier New", Font.BOLD, 80)); 
        bufferGraphics.drawString(" " + puntuacion, ANCHOPANTALLA/2, 70);
//        //dibuja el resultado
        lienzoGraphics.drawImage(buffer, 0,0, null);
//        
//                //chequea si ha chocado con alguna columna
    //    for (int i=0; i<numColumnas; i++){
   //         if (miHelicopter.chequeaColision(columnas[i])){temporizador.stop();}
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_SPACE){
           miHelicopter.yVelocidad += 9;
       }      
    }//GEN-LAST:event_formKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaJuego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
