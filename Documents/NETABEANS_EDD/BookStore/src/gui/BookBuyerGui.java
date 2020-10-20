/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author samsclub
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import behaviours.RequestPerformer;

import agents.BookBuyerAgent;
import agents.BookSellerAgent;

public class BookBuyerGui extends JFrame{
    private BookBuyerAgent myAgent;
	public String title;
	public JTextField bTitle;
        public JTextArea resultados; 
	private RequestPerformer accion;
        
	public BookBuyerGui(BookBuyerAgent a) {
		super(a.getLocalName());
		//this.title = "";
		myAgent = a;
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 2));
		p.add(new JLabel("Titulo del Libro:"));
		bTitle = new JTextField(15);
		p.add(bTitle);
                //p.add(resultados);
	
		getContentPane().add(p, BorderLayout.CENTER);
		
		JButton addButton = new JButton("Comprar");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					 title = bTitle.getText().trim();
				
					
					//myAgent.getBookTitle();
					bTitle.setText("");
					//priceField.setText("");
				}catch(Exception e) {
					JOptionPane.showMessageDialog(BookBuyerGui.this, "Invalid values","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
                JTextArea textArea = new JTextArea("Espera un moemnto estamos buscando su Libro");
                    //textArea.setText();
                    textArea.setFont(new Font("Serif", Font.ITALIC, 16));
                    textArea.setLineWrap(true);
                    textArea.setWrapStyleWord(true);
                    p.add(textArea);
		p = new JPanel();
		p.add(addButton);
		getContentPane().add(p, BorderLayout.SOUTH);
		
		addWindowListener(new WindowAdapter() {
		  public void windowClosing(WindowEvent e) {
		    myAgent.doDelete();
		  }
		});
		
		setResizable(false);
	}
	/*public String getDatos(){
            return title= bookTitle.getText();
        }*/
	public void showGui() {
	  pack();
	  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	  int centerX = (int)screenSize.getWidth() / 2;
	  int centerY = (int)screenSize.getHeight() / 2;
	  
	  setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
	  super.setVisible(true);
	}
}
