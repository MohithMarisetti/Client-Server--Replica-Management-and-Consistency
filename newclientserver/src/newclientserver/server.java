//Mohith Marisetti
//1001669337
package newclientserver;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class server
{ 
	ServerSocket ss = null; 
  Socket s = null; 
  JTextArea ta;
  JButton b2;
	  
@SuppressWarnings("resource")
public server(JTextArea ta, JButton b2)
	{
  this.serverRun(ta,b2);     //Call the server run method to turn on the server 
	
  } 

public void serverRun(JTextArea ta,JButton b2)
{
	 ServerSocket ss=this.ss;
      Socket s=this.s;
       this.ta= ta;
       this.b2 = b2;
      
		try {
			ss = new ServerSocket(5056);   //create a new server socket
		} catch (IOException e1) {
			e1.printStackTrace();
		} 

      while (true)  	
      { 
            
          try  {
          
              // socket object to receive incoming client requests 
              s = ss.accept(); 
                
         
              // obtaining input and out streams 
              DataInputStream dis = new DataInputStream(s.getInputStream()); 
              DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
                
              System.out.println("Assigning new thread for this client"); 
              // create a new thread i.e; clientHandler object 
              Thread t = new Thread(new ClientHandler(s, dis, dos, ta, b2)); 
              
             
              
			
              //Invoking the start() method 
              t.start(); 
             
                
          } 
          catch (Exception e){ 
              try {
					s.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
              e.printStackTrace(); 
          } 
         
      } 
}

} 

//ClientHandler class 
class ClientHandler implements Runnable  
{ 
   DataInputStream dis; 
   DataOutputStream dos; 
   Socket s; 
  String name="";
  JTextArea ta;
  JButton b2;
  boolean first = true;
  boolean keep_going = true;
  boolean done = false;
  static boolean isServerPoll= false;
  static String final_val = "";

// Constructor 
public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos, JTextArea ta, JButton b2)  
{ 
    this.s = s; 
    this.dis = dis; 
    this.dos = dos; 
    this.ta = ta;
    this.b2 = b2;
} 

  @Override
  public void run()  
  { 
      String received=null; 
      String toreturn; 
      String name;

	    try {
	    	//take the username from the user
      	dos.writeUTF("Give me your name");
			name = dis.readUTF();
			  if(this.first=true)
		      {
				//Display the username in real time in the server GUI
		      	ta.append("\n"+name+" has connected!");
		      	this.first = false;
		      }
			  
		      //store username
			this.name = name; 
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
      while (keep_going == true)  //keep accepting the requests of the client till he is available
      { 
    	  //ProcessRequest.processRequest(s,dis,dos,ta, this.name);  //Call the processRequest method to perform the task
    	  try {
			dos.writeUTF("Perform your operations below\n");
	
			
			
			
			
      		
		
			
			
			
			
			b2.addActionListener(new ActionListener()
					{
				
				

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
						public void actionPerformed(ActionEvent ev) {
											try {
												
												
												System.out.println("Button POLL pressed on the server GUI");
												
												isServerPoll = true;
												

												System.out.println("I'm in the Server.java file line num 284");

												
												try {
													TimeUnit.SECONDS.sleep(2);   //Wait for the client to stop execution of their own commands
													System.out.println("I'm in the Server.java file line num 289,, waiting for 2 sec logic");
												} catch (InterruptedException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												
											//	dos.writeUTF("wait ! you are polled by the server");
												
												System.out.println("I'm in the Server.java file line num 289,, waiting for 2 sec logic");

												
											//	String received_exp = dis.readUTF();
												

												
												
												
												
												String data = ""; 
											    data = new String(Files.readAllBytes(Paths.get("D:\\\\testout.txt"))); 
												
											    String each_exp = "1";
												StringTokenizer st = new StringTokenizer(data,"|");
												while(st.hasMoreTokens())
												{
													String temp = st.nextToken();
													each_exp += temp.substring(1, temp.length());
													
												}//while close
												
												System.out.println("The obtained final expression is "+each_exp);

												float finalFloatVal = evaluate(each_exp);
										
												/*
												
												System.out.println("The received final expression is "+received_exp);
												String cal_exp = "1 ";
												cal_exp+=received_exp;
											float finalFloatVal =	evaluate(cal_exp);

												 	*/

					                		 
												
												
												
												
												
												
												
												
												
												DecimalFormat df = new DecimalFormat("#.####");
					                		   df.setRoundingMode(RoundingMode.CEILING);
					                		       Double d =  (double) finalFloatVal;
					                		       //System.out.println(df.format(d));
					                		
					                		       final_val = String.valueOf(df.format(d));
											System.out.println("The server calculated new value is "+String.valueOf(df.format(d)));
										//	dos.writeUTF(String.valueOf(df.format(d)));
											
											done = true;
											
											} catch (IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											

						}
				
				
    	  });
		
			
			
			while(true)
			{
				String rec = dis.readUTF();
				if(rec.contains("done"))
				{
					if(isServerPoll!=true)
					{dos.writeUTF("okay");
					System.out.println("Server was waiting till now and just received a done confirmation from the client and it said okay proceed to next operation");
					break;}
					
					else
					{
						System.out.println("I entered the condition where isServerPoll is true!");
						dos.writeUTF("wait");
						
						
						dos.writeUTF("wait");
						
						dos.writeUTF(final_val);
						
					}
				}
				
			}
			
			
			
		} //Try block close
    	  
    	  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	  
    	  
    	  
       }//while close 
        
      try
      { 
          // closing resources 
          this.dis.close(); 
          this.dos.close(); 
            
      }catch(IOException e){ 
          e.printStackTrace(); 
      } 
  } 
} 


