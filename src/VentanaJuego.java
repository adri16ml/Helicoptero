/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.Timer;
/**
 *
 * @author Adrian
 */
public class VentanaJuego extends javax.swing.JFrame {
    
    Helicoptero miHelicoptero = new Helicoptero(30);
    // medidas para el ancho de la pantalla
    static int ANCHOPANTALLA = 650;
    // altura de la pantalla
    static int ALTOPANTALLA = 400;
    // separacion de las columnas 
    static int SEPARACION_COLUMNAS = 170 ;
    // numero de las columnas que van a aparecer
    int numColumnas = 3;
    // puntuacion en la que comienza el juego 
    int puntuacion = 0;
    // el contador de la animacion empieza en 0 
    int contadorAnimacion = 0;
    //imagenes de los adornos
    Image fondo;
    //array de columnas
    Columna[] columnas = new Columna[numColumnas];
    //variable que se ejecutara despues para las explosiones de los edificios
    boolean explosion = false;
    //inicializamos los buffer a null
    BufferedImage buffer = null;
    // como con el buffer inicializamos el lienzo y el B.Graphics a null 
    Graphics2D bufferGraphics, lienzoGraphics = null;
    //bolean para inicializar el juego
    boolean comienzojuego = false;
    //TEMPORIZADOR DEL JUEGO: AQUI SE LLAMA A LA ANIMACIÓN
    Timer temporizador = new Timer(10,new ActionListener(){
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
        // inicializamos el jdialog y su posicion 
        lose.setLocation(10, 20);
        // tamaño del jdialog con el mensaje al perder
        lose.setSize(ANCHOPANTALLA, ALTOPANTALLA);
        // indicamos el tamaño que va a tener el jDialog al presionar el boton escape 
        fin.setSize(ANCHOPANTALLA/2,ALTOPANTALLA/2);
        // y su posicion 
        fin.setLocation(100, 20);
        // comienza el juego al presionar el boton.
        temporizador.start();
        for (int i=0; i<numColumnas; i++){
            columnas[i] = new Columna(ANCHOPANTALLA + i*SEPARACION_COLUMNAS, ANCHOPANTALLA);
        }
    }
    private void inicializaBuffers(){
       lienzoGraphics = (Graphics2D) jPanel1.getGraphics();
        buffer = (BufferedImage) jPanel1.createImage(ANCHOPANTALLA, ALTOPANTALLA);
        bufferGraphics = buffer.createGraphics();
        bufferGraphics.setColor(Color.white);
        bufferGraphics.fillRect(0, 0, ANCHOPANTALLA, ALTOPANTALLA);
      
    }
    
      private void bucleDelJuego(){
          
          bufferGraphics.fillRect(0, 0, ANCHOPANTALLA, ALTOPANTALLA); 
          if(!comienzojuego){
              fin.setVisible(true);
              temporizador.stop();
          }
          // añadimos la variable contadorAnimacion++ para que el contador vaya aumentando a medida que superamos
          //columnas
        contadorAnimacion++;
        if (contadorAnimacion > 30) {contadorAnimacion = 0;}
        bufferGraphics.fillRect(0, 0, ANCHOPANTALLA, ALTOPANTALLA); 
        //dibujamos el avion en su posicion y el contador de la animacion 
        miHelicoptero.mueve(bufferGraphics, contadorAnimacion);
        //desplazo las columnas a la izquierda. Si alguna coincide con la posicion del pajaro, incremento en 1 el marcador
        for (int i=0; i<numColumnas; i++){
            // en este bucle conseguimos que el avion al chequear si a colisionado con una columna, entre en 
            // la variable booleana y active la explosion, cambiando el edificio por una explosion
            if (miHelicoptero.chequeaColision(columnas[i])){
                // true= ha habido una colision con el avion y un edificio
               explosion = true;
               columnas[i].mueve(bufferGraphics, miHelicoptero, explosion);
               // cuando hay una colision el juego se detiene
               temporizador.stop();
               i = numColumnas;
            }     
            else{
                // si no hay colision indicamos que tampoco va a haber una explosion posterior
            explosion =false;
                    }
        }
        // cada vez que el avion pase por encima de una columna la puntuacion ira aumentando 
        for (int i=0; i<numColumnas; i++){
            if (columnas[i].mueve(bufferGraphics, miHelicoptero, explosion)){
                // veces que aumenta la puntuacion +1
                puntuacion++;
            }
        }
        
        for (int i=0; i<numColumnas; i++){
            if (miHelicoptero.chequeaColision(columnas[i])){
                temporizador.stop();
                // jlabel en el que aparece la puntuacion al perder el juego 
                jLabel2.setText(Integer.toString(puntuacion));
                //mostramos el jlabel con el texto y la puntuacion final 
                lose.setVisible(true);
                
            }
        }
        //dibuja el marcador, con el tipo de fuente y el tamaño
        bufferGraphics.setFont(new Font("Courier New", Font.BOLD, 70));
        // el color de la fuente del marcador 
        bufferGraphics.setColor(Color.black);
        // texto que va a tener el marcador y posicion en la que se muestra
        bufferGraphics.drawString("virgenes" +" " + puntuacion, ANCHOPANTALLA/5, 80);
        // repintamos el buffer de blanco para que no se sobreescriba sobre el marcador
        bufferGraphics.setColor(Color.white);
        //dibujamos la imagen del buffer
        lienzoGraphics.drawImage(buffer, 0,0, null);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fin = new javax.swing.JDialog();
        continuar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        lose = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        reiniciar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        fin.setResizable(false);
        fin.setSize(new java.awt.Dimension(325, 200));

        continuar.setText("CONTINUAR");
        continuar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                continuarMouseClicked(evt);
            }
        });

        jButton1.setText("Nueva partida");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout finLayout = new javax.swing.GroupLayout(fin.getContentPane());
        fin.getContentPane().setLayout(finLayout);
        finLayout.setHorizontalGroup(
            finLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(finLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(finLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(continuar)
                    .addComponent(jButton1))
                .addContainerGap(174, Short.MAX_VALUE))
        );
        finLayout.setVerticalGroup(
            finLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(finLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(continuar)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Malayalam Sangam MN", 0, 24)); // NOI18N
        jLabel1.setText("ENHORABUENA TE ESPERAN ESTAS VIRGENES ");

        jLabel2.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 48)); // NOI18N

        reiniciar.setText("REINICIAR");
        reiniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reiniciarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout loseLayout = new javax.swing.GroupLayout(lose.getContentPane());
        lose.getContentPane().setLayout(loseLayout);
        loseLayout.setHorizontalGroup(
            loseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(loseLayout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loseLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(reiniciar)
                .addGap(54, 54, 54))
        );
        loseLayout.setVerticalGroup(
            loseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loseLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(reiniciar)
                .addGap(27, 27, 27))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
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
            .addGap(0, 399, Short.MAX_VALUE)
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
        // evento que actua al presionar el espacio aumentando la velocidad del avion 
        if (evt.getKeyCode() == KeyEvent.VK_SPACE){
           miHelicoptero.yVelocidad += 9;

       }   
        // si presionamos el ESCAPE entraremos en este bucle que nos muestra un jDialog con un menu de pausa
          if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){
              // menu que se muestra al presionar ESCAPE
           fin.setVisible(true);
           // el juego se detiene al presionar el boton 
           temporizador.stop();
       } 
    }//GEN-LAST:event_formKeyPressed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
      // evento para incluir un clip de sonido al presionar click sobre el jpanel 
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream( getClass().getResource("/sonidos/AllahuAkbar.wav") )); 
            clip.loop(0);
        } catch (Exception e) {      
        } 
    }//GEN-LAST:event_jPanel1MouseClicked

    private void continuarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_continuarMouseClicked
      // evento para el boton continuar que hara que el jdialog desaparezca y el juevo comience de nuevo
        fin.setVisible(false);
       temporizador.start();
    }//GEN-LAST:event_continuarMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
           fin.setVisible(false);
           //el juego esta parado hasta presionar el espacio
           comienzojuego = true;
           temporizador.start();
    }//GEN-LAST:event_jButton1MouseClicked

    private void reiniciarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reiniciarMouseClicked
        // metodo para reiniciar el juego 
        // reiniciamos las variables en primer lugar 
         puntuacion = 0;
        contadorAnimacion = 0;
        puntuacion = 0;    
        Helicoptero miHelicoptero = new Helicoptero(30);
        // volvemos a pintar la posicion del helicoptero y limpiar los buffer
        miHelicoptero.mueve(bufferGraphics, contadorAnimacion);
        for (int i=0; i<numColumnas; i++){
            // volvemos a colocar las columnas 
            columnas[i] = new Columna(ANCHOPANTALLA + i*SEPARACION_COLUMNAS, ANCHOPANTALLA);
        }
        // iniciamos el lienzo en blanco de nuevo 
        lienzoGraphics.drawImage(buffer, 0, 0, null);
        // cerramos la ventana del jdialog 
        lose.setVisible(false);
        // y volvemos a iniciar el juego 
        temporizador.start();
    }//GEN-LAST:event_reiniciarMouseClicked

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
    private javax.swing.JButton continuar;
    private javax.swing.JDialog fin;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JDialog lose;
    private javax.swing.JButton reiniciar;
    // End of variables declaration//GEN-END:variables
}
