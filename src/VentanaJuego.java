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
    // declaramos la variable del fondo de pantalla con sus diametros
    fondo miFondo = new fondo(ALTOPANTALLA, ANCHOPANTALLA);
    // declaramos el helicoptero 
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
        fin.setSize(ANCHOPANTALLA,ALTOPANTALLA);
        // y su posicion 
        fin.setLocation(10, 20);
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
        //añadimos la variable para poner el fondo en el jpanel 
        miFondo.ponImagenDeFondo(bufferGraphics);
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
        bufferGraphics.setFont(new Font("Courier New", Font.BOLD, 30));
        // el color de la fuente del marcador 
        bufferGraphics.setColor(Color.black);
        // texto que va a tener el marcador y posicion en la que se muestra
        bufferGraphics.drawString("Ilegales"+" " + puntuacion, ANCHOPANTALLA/5, 50);
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
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lose = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        reiniciar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        fin.setResizable(false);
        fin.setSize(new java.awt.Dimension(325, 200));
        fin.getContentPane().setLayout(null);

        continuar.setFont(new java.awt.Font("Wide Latin", 0, 12)); // NOI18N
        continuar.setText("CONTINUAR");
        continuar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                continuarMouseClicked(evt);
            }
        });
        fin.getContentPane().add(continuar);
        continuar.setBounds(200, 240, 240, 50);

        jButton1.setFont(new java.awt.Font("Wide Latin", 0, 12)); // NOI18N
        jButton1.setText("Nueva partida");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        fin.getContentPane().add(jButton1);
        jButton1.setBounds(220, 160, 200, 50);

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 3, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Donald trump debe identificar a los inmugrantes antes de la construccion de su muro");
        fin.getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 300, 620, 100);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bandera.jpg"))); // NOI18N
        fin.getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 0, 650, 400);

        lose.getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Bernard MT Condensed", 0, 36)); // NOI18N
        jLabel1.setText("Donald Trump te da las gracias");
        lose.getContentPane().add(jLabel1);
        jLabel1.setBounds(80, 10, 510, 180);

        jLabel2.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 48)); // NOI18N
        lose.getContentPane().add(jLabel2);
        jLabel2.setBounds(180, 130, 220, 140);

        reiniciar.setText("REINICIAR");
        reiniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reiniciarMouseClicked(evt);
            }
        });
        lose.getContentPane().add(reiniciar);
        reiniciar.setBounds(220, 300, 150, 60);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/juego .png"))); // NOI18N
        lose.getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 650, 400);

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
            clip.open(AudioSystem.getAudioInputStream( getClass().getResource("/sonidos/donald.wav") )); 
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JDialog lose;
    private javax.swing.JButton reiniciar;
    // End of variables declaration//GEN-END:variables
}
