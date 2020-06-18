//Mohith Marisetti
//1001669337
package newclientserver;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.math.RoundingMode;
import java.net.*;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JTextField; 

//Client1 class 
public class client1
{ 
	 String final_expression = "";
	final static String initial_value = "1";
	public static boolean done = false;
//	public static boolean isServerPoll = false;
	
  public client1(JTextField tf1,JTextField tf2,JTextField tf3,JButton b1) throws IOException  
  { 
    
            
          // getting localhost ip 
          InetAddress ip = InetAddress.getByName("localhost"); 
    
          // establish the connection with server port 5056 
          Socket s = new Socket(ip, 5056); 
    
          // obtaining input and out streams 
          DataInputStream dis = new DataInputStream(s.getInputStream()); 
          DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
    
          
          
          
          // the following loop performs the exchange of 
          // information between client and client handler 
          
          try
      {   
     
          
          
          tf1.setText(dis.readUTF());  //Says Give me your name on Client GUI
          b1.addActionListener(new ActionListener(){  //ACTION LISTENER TO LISTEN TO THE BUTTON SEND
          	@Override
          	public void actionPerformed(ActionEvent e){  
                 String name = tf2.getText();  
                  try {
						dos.writeUTF(name);  //When button is pressed send the username written in TextField(TF2)  to the server
						dos.flush();
						tf2.setText("");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 

          	        }  
          	    });

          
          
          
          
          
          while (true)  //PROCESS THE INCOMING CLIENT REQUESTS UNTIL THE CLIENT REPLIES "no"
          { 	
              
        	  String server_text = dis.readUTF(); 
              tf1.setText(server_text);
              tf2.setText(initial_value);
              if(!server_text.contains("wait")||server_text.equals("")) {
            	  
            	  System.out.println("\n\nPoll not pressed on server and im not waiting\n\n\n");
              b1.addActionListener(new ActionListener(){  
                	
            	  
            	  
            	  
            	  
            	  
            	  
            	  
            
            	  
            	  
            	  
            	  
            	  
            	  
            	  
            	  
            	  
            	  
            	  
            	  
            	  

            	  float evaluate(String expression) 
            	    { 
            	        char[] tokens = expression.toCharArray(); 
            	  
            	         // Stack for numbers: 'values' 
            	        Stack<Float> values = new Stack<Float>(); 
            	  
            	        // Stack for Operators: 'ops' 
            	        Stack<Character> ops = new Stack<Character>(); 
            	  
            	        for (int i = 0; i < tokens.length; i++) 
            	        { 
            	             // Current token is a whitespace, skip it 
            	            if (tokens[i] == ' ') 
            	                continue; 
            	  
            	            // Current token is a number, push it to stack for numbers 
            	            if (tokens[i] >= '0' && tokens[i] <= '9') 
            	            { 
            	                StringBuffer sbuf = new StringBuffer(); 
            	                // There may be more than one digits in number 
            	                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') 
            	                    sbuf.append(tokens[i++]); 
            	                values.push((float) Integer.parseInt(sbuf.toString())); 
            	            } 
            	  
            	            // Current token is an opening brace, push it to 'ops' 
            	            else if (tokens[i] == '(') 
            	                ops.push(tokens[i]); 
            	  
            	            // Closing brace encountered, solve entire brace 
            	            else if (tokens[i] == ')') 
            	            { 
            	                while (ops.peek() != '(') 
            	                  values.push(applyOp(ops.pop(), values.pop(), values.pop())); 
            	                ops.pop(); 
            	            } 
            	  
            	            // Current token is an operator. 
            	            else if (tokens[i] == '+' || tokens[i] == '-' || 
            	                     tokens[i] == '*' || tokens[i] == '/') 
            	            { 
            	                // While top of 'ops' has same or greater precedence to current 
            	                // token, which is an operator. Apply operator on top of 'ops' 
            	                // to top two elements in values stack 
            	                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek())) 
            	                  values.push((float) applyOp(ops.pop(), values.pop(), values.pop())); 
            	  
            	                // Push current token to 'ops'. 
            	                ops.push(tokens[i]); 
            	            } 
            	        } 
            	  
            	        // Entire expression has been parsed at this point, apply remaining 
            	        // ops to remaining values 
            	        while (!ops.empty()) 
            	            values.push((float) applyOp(ops.pop(), values.pop(), values.pop())); 
            	  
            	        // Top of 'values' contains result, return it 
            	        return values.pop(); 
            	    } 
            	  
            	    // Returns true if 'op2' has higher or same precedence as 'op1', 
            	    // otherwise returns false. 
            	    boolean hasPrecedence(char op1, char op2) 
            	    { 
            	        if (op2 == '(' || op2 == ')') 
            	            return false; 
            	        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) 
            	            return false; 
            	        else
            	            return true; 
            	    } 
            	  
            	    // A utility method to apply an operator 'op' on operands 'a'  
            	    // and 'b'. Return the result. 
            	    float applyOp(char op, Float float1, Float float2) 
            	    { 
            	        switch (op) 
            	        { 
            	        case '+': 
            	            return float2 + float1; 
            	        case '-': 
            	            return float2 - float1; 
            	        case '*': 
            	            return float2 * float1; 
            	        case '/': 
            	            if (float1 == 0) 
            	                throw new
            	                UnsupportedOperationException("Cannot divide by zero"); 
            	            return float2 / float1; 
            	        } 
            	        return 0; 
            	    } 
            	  
            	  
            	  
            	  
            	    
            	    
            	    
            	    
            	    
            	    
            	    
            	    
            	    
            	    
            	    
            	    
            	  
            	  
            	  
            	  
            	  
            	  
            	  @Override
                	public void actionPerformed(ActionEvent e){   //ACTION LISTENER TO LISTEN TO THE BUTTON PRESS  
                		
                		int number;
                		String given_exp = tf2.getText();
                	//	given_exp = initial_value +" "+given_exp;
                        //System.out.println("what you replied for 'give number?' is "+give_number);
                		System.out.println(given_exp);
                		
                		float client_result = evaluate(given_exp);
                		
                		System.out.println("Client result "+client_result);
                		
                		/*
                		 * 
                		 *  Writing data into a file to save it as a LOG
                		 *
                		 */
                		
                			final_expression += given_exp.substring(2, given_exp.length());

                		/*	
                		   try{    
                	             FileOutputStream fout=new FileOutputStream("D:\\testout.txt");    
                	                 given_exp+="\n";
                	             byte b[]=   given_exp.getBytes();//converting string into byte array    
                	             fout.write(b);
                	             fout.close();    
                	             System.out.println("success writing into file...");    
                	            }catch(Exception ex){System.out.println(ex);}    
                		
                		*/
                			
                		given_exp+="|";
                			BufferedWriter bw = null;
                			FileWriter fw = null;

                			try {


                				File file = new File("D:\\\\testout.txt");

                				// if file doesnt exists, then create it
                				if (!file.exists()) {
                					file.createNewFile();
                				}

                				// true = append file
                				fw = new FileWriter(file.getAbsoluteFile(), true);
                				bw = new BufferedWriter(fw);

                				bw.write(given_exp);
                				

                				System.out.println("Done");

                			} catch (IOException ioe) {


                			} finally {

                				try {

                					if (bw != null)   //------------------------------------------============================------------------------------------==========
                						bw.close();

                					if (fw != null)
                						fw.close();

                				} catch (IOException ex) {

                					ex.printStackTrace();

                				}
                			}
                			
                		
                			
                			
                			
                			
                			
                			
                			
                		
                		   DecimalFormat df = new DecimalFormat("#.####");
                		   df.setRoundingMode(RoundingMode.CEILING);
                		       Double d =  (double) client_result;
                		       System.out.println(df.format(d));
                		   
                		
                		
                		tf3.setText(String.valueOf(df.format(d)));
                	
                		try {
							TimeUnit.SECONDS.sleep(10);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}               
                        done = true;
         
            
                	}//action performed method closed
                	}); // Action listener closed
              
              while(true)
              {
            	  
            	  
					     
            	  if(done==true||dis.readUTF().contains("wait"))
            	  {
            		  System.out.println("I'm in the small loop you created to pause loop at each iteration\n");
            		  
            		  if (done == true)
            		  {System.out.println("Done is true so i changed it to false!");
            			  done = false;
            			  dos.writeUTF("done");
            			  break;
            		  }
            		  
            		 
            		  if(serverPoll.isServerPoll== true)
            		  {
            			  System.out.println("Done is true so i changed it to false!");
            			  serverPoll.isServerPoll = false;}
            		  tf2.setText("You are polled by the server!");
            		  tf3.setText("Your local Value has been updated!");
            		  break;
            		   /*
            		  String tmp  = dis.readUTF();
            		  
            		    System.out.println("value of tmp is = "+tmp);
            		  if(tmp.contains("okay"))
            			  {break;}
            		  else if (tmp.contains("wait"))
            		  {
            			  
            			  tf3.setText("server just updated the local copy!");
            		  }
            		  
            		  */
            	  }
            	  else
            	  {
            		  
            		  System.out.println("I am in the else block of the small while loop and am waiting for done to be = 'true' ");
                	  try {
    						TimeUnit.SECONDS.sleep(4);
    					} catch (InterruptedException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					}
    					
            	  }
              }
              
              
              
              }//if close
              else
              {
            	
            	  System.out.println("Main else block entered! which means server told to wait!");
            	  //dos.writeUTF(final_expression);
            	  String finalServerVal = dis.readUTF();
            	  tf3.setText("Server updated value is "+finalServerVal);
            	  
              }//Server sent a wait! message
              
              
                } //while close
            
        
      }catch(Exception e){ 
          e.printStackTrace(); 
      } 
      finally {
    	  dos.writeUTF("no");
			System.exit(0);
			
      }
  } 
} 