/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import vista.vista;

/**
 *
 * @author alvaro
 */
public class controlador implements ActionListener, MouseListener {

    //instancia a la interfaz vista
    vista vista;
    
    
    int turno,contp,contimp;
    boolean gano=false;//Variable que indica si hay ganador o no
    public JLabel[][] cuadros;//matriz para guardar los label
    public Color colorx=Color.RED;//color del jugador X
    public Color coloro=Color.BLUE;//color del jugador O
    
    
    public controlador (vista vista){
        
        this.vista = vista;
        this.vista.setLocationRelativeTo(null);
        
        System.out.println("Construyendo el controlador");
    
    }
    
    
    public enum AccionMVC{
        btnJugar,
        btnSalir
    }
    
    
    public enum MouseMVC{
        label1,
        label2,
        label3,
        label4,
        label5,
        label6,
        label7,
        label8,
        label9
        
    }
    
    public void marcar(JLabel cuadro){
        if (!gano)//if para comprobar si hay ganador o no 
        {
            if(cuadro.getText().equals(""))//comprueba que el label este vacio
            {
                if (turno%2==1)//comprueba el valor de la variable "turno" para saber a cual corresponde el turno
                {
                    cuadro.setText("x");
                    cuadro.setForeground(colorx);//asigna el color al jugador x
                    if (contimp>=3)//comprueba que haya al menos 3 x  
                    {
                        verificar("x");//comprueba si hay 3 x en raya
                    }
                    contimp++;//contador de x
                } 
                else
                {
                    cuadro.setText("o");
                    cuadro.setForeground(coloro);//asigna el color al jugador 0
                    if (contp>=3) //comprueba que haya al menos 3 0 
                    {
                        verificar("o");//comprueba si hay 3 0 en raya
                    }
                    contp++;
                }
                turno++;//contador de 0
            }
        } 
    }
    
    public void verificar(String parametro) 
    {
        int contador=0;
        int a,b;  
        boolean reinicio=false;
        for (int x = 0; x < 2; x++) 
        {
            for (int i = 0; i < cuadros.length; i++) 
            {  
                for (int j = 0; j < cuadros.length; j++) 
                {
                    if (x==0) {
                      a=i;//posicion fila
                      b=j;//posicion columna 
                    }else
                    {
                        a=j;//posicion fila
                        b=i;//posicion olumna 
                    }
                    if (cuadros[a][b].getText().equals(parametro)) {
                        contador++;//cuenta las de una misma letra
                    }
                }
                if (!gano&&contador==3) 
                {
                    JOptionPane.showMessageDialog(null, "¡¡¡GANADOR!!!");
                    if(JOptionPane.showConfirmDialog(null, "¿Quiere Jugar de nuevo? ")==0)
                    {
                        nuevo();//reset
                        reinicio=true;
                    }
                    else
                    {
                        gano=false;                        
                    }
                    break;
                }
                contador=0;
            } 
        }
        if (!reinicio) {
            int pos;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < cuadros.length; j++) {
                   if (i==0) {
                        pos=j;
                    } else {
                        pos=2-j;
                    }
                    if (cuadros[j][pos].getText().equals(parametro)) {
                        contador++;
                    }
                }
                if (contador==3) {
                    JOptionPane.showMessageDialog(null, "¡¡¡GANADOR!!!");
                    if(JOptionPane.showConfirmDialog(null, "¿Quiere volver a jugar?")==0)
                    {
                        nuevo();
                        break;
                    }
                    else
                    {
                        gano=false;
                        break;
                    }
                }
                contador=0;
            }
        }
    }
    //metodo de reset del juego
    
    public void nuevo() {
        gano=false;
        turno=contp=contimp=2;
        Color colorx=Color.red;
        Color coloro=Color.YELLOW;
        for (int i = 0; i < cuadros.length; i++) {
            for (int j = 0; j < cuadros.length; j++) {
                cuadros[i][j].setText("");
            }
        }
    }
    
    public void iniciar(){
        
        turno=contp=contimp=1;
        
        cuadros=new JLabel[][]{{this.vista.label1,this.vista.label2,this.vista.label3},{this.vista.label4,this.vista.label5,this.vista.label6},{this.vista.label7,this.vista.label8,this.vista.label9}};//Este arreglo almacena lo label        
    
        System.out.println("iniciando");
        
        this.vista.setVisible(true);
        this.vista.setLocationRelativeTo(null);
        
        this.vista.btnJugar.setActionCommand("btnJugar");
        this.vista.btnJugar.addActionListener(this);
        
        this.vista.btnSalir.setActionCommand("btnSalir");
        this.vista.btnSalir.addActionListener(this);
        
        
        
        this.vista.label1.addMouseListener(this);
        this.vista.label1.setName("label1");
    
        this.vista.label2.addMouseListener(this);
        this.vista.label2.setName("label2");
        
        this.vista.label3.addMouseListener(this);
        this.vista.label3.setName("label3");
        
        this.vista.label4.addMouseListener(this);
        this.vista.label4.setName("label4");
        
        this.vista.label5.addMouseListener(this);
        this.vista.label5.setName("label5");
        
        this.vista.label6.addMouseListener(this);
        this.vista.label6.setName("label6");
        
        this.vista.label7.addMouseListener(this);
        this.vista.label7.setName("label7");
        
        this.vista.label8.addMouseListener(this);
        this.vista.label8.setName("label8");
        
        this.vista.label9.addMouseListener(this);
        this.vista.label9.setName("label9");
        
    }
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch ( AccionMVC.valueOf(e.getActionCommand()) ){
            
            case btnJugar:
                
                this.vista.label1.setText("");
                this.vista.label2.setText("");
                this.vista.label3.setText("");
                this.vista.label4.setText("");
                this.vista.label5.setText("");
                this.vista.label6.setText("");
                this.vista.label7.setText("");
                this.vista.label8.setText("");
                this.vista.label9.setText("");
                
                break;
                
            case btnSalir:
                
                System.exit(1);
                
                break;
            
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        switch (MouseMVC.valueOf(e.getComponent().getName())){
            
            case label1:
                
                this.marcar(this.vista.label1);
                
                
                break;
                
            case label2:
                
               this.marcar(this.vista.label2); 
                
                break;
                
            case label3:
                
                this.marcar(this.vista.label3);
                
                break;
                
            case label4:
                
                this.marcar(this.vista.label4);
                
                break;
                
            case label5:
                
                this.marcar(this.vista.label5);
                
                break;    
            
            case label6:
                
                this.marcar(this.vista.label6);
                
                break;
                
            case label7:
                
                this.marcar(this.vista.label7);
                
                break;
                
            case label8:
                
                this.marcar(this.vista.label8);
                
                break;  
                
            case label9:
                
                this.marcar(this.vista.label9);
                
                
                break;    
                
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
