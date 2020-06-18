 //Mohith Marisetti
//1001669337

package newclientserver;
import javax.swing.*;  
	import java.awt.*;  
	import java.awt.event.*; 
@SuppressWarnings("serial")
public class server_gui extends Frame implements ActionListener{  //Creating a GUI for the server
	    JTextField tf; JLabel l; JButton b,b2; JTextArea ta; 
	    server s;
	    
	    server_gui(){  
	      
	    	setLayout(new GridLayout());   
	        l=new JLabel("Server has started");  
	        l.setBounds(50,100, 250,20);      
	        b=new JButton("Exit");  
	        b.setBounds(530,455,120,50);  
	        b2=new JButton("Poll");  
	        b2.setBounds(530,400,120,50);  
	        ta = new JTextArea(40,70);
	        ta.setBounds(150,150,200,200);
	        b.addActionListener(this);    
	        add(ta);
	        add(b);
	        add(b2);
	        add(l);    
	        setSize(700,600);  
	        setLayout(null);  
	        setVisible(true);  
	        new server(ta,b2);
	        
	      
	    }  
	    public void actionPerformed(ActionEvent e) {  
		
			System.exit(0);
		

	    }  
	    public static void main(String[] args) {  
	        new server_gui();  
	    } }  

	

