import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException; 

import java.util.ArrayList;
/**
 * Esta clase se realizó para interactuar con el usuario
 * IMPORTANTE, para probar clases de otros grupos, dirigirse a la línea 30
 * @author Pedro Marroquín 21801, Sebastian Reyes 21139 & Roberto Ríos 20979
 * @version 1.0
 * @since 30/01/2022
 */
public class MyFrame extends JFrame implements ActionListener {
    
    private JPanel bg;
    private JLabel label, tittle, result;
    private JButton chooseButton;
    private Pila pila = new Pila();

    public MyFrame() {
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Calculadora Postfix");
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        putPanel();
        iniciateText();
        putButtons();

    }

    private void putPanel() {
        bg = new JPanel();
        bg.setLayout(null);
        bg.setBackground(new Color(56, 152, 255));
        this.getContentPane().add(bg);
    }

    private void iniciateText() {
        label = new JLabel("Resultado: ");
        label.setBounds(20, 100, 100, 20);
        label.setAlignmentX(CENTER_ALIGNMENT);
        bg.add(label);

        tittle = new JLabel("Calculadora Grupo 2");
        tittle.setBounds(20, 20, 250, 50);
        tittle.setFont(new Font("Arial", 2, 20));
        tittle.setAlignmentX(CENTER_ALIGNMENT);
        bg.add(tittle);

        result = new JLabel("(Seleccione un archivo)");
        result.setBounds(120, 100, 150, 20);
        result.setAlignmentX(LEFT_ALIGNMENT);
        bg.add(result);
    }

    private void putButtons() {
        chooseButton = new JButton("Escoger txt");
        chooseButton.setBounds(240, 30, 100, 30);
        chooseButton.addActionListener(this);
        bg.add(chooseButton);
    }
    
   @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chooseButton) {
            JFileChooser txtchooser = new JFileChooser();
            int response = txtchooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(txtchooser.getSelectedFile().getAbsolutePath());
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    ArrayList<String> Operaciones = new ArrayList<String>();
                    boolean existe_siguiente = true;
                    //lee cada línea del documento, los guarda en un ArrayList de Strings
                    do{
                        String input = reader.readLine();
                        if(input == null)
                        {
                            existe_siguiente = false;
                        }else{
                            Operaciones.add(input);
                        }
                    }while(existe_siguiente);
                    //se realiza la operación de todos los Strings dentro del ArrayList creado
                    try{
                        for(int i = 0; i<Operaciones.size(); i++)
                        {
                            // aqui se coloca actualiza el label para dar el resultado que devuelve calculate
                            System.out.println("El resultado de la linea "+(i+1)+" es: "+pila.calculate(Operaciones.get(i)));
                        }
                        result.setText("Resultados en terminal");
                    }catch(Exception error)
                    {
                        error.printStackTrace();
                    }
                    reader.close(); 
                } catch (IOException err) {
                    err.printStackTrace();
                }
	     }

        }
        
    }
	
}