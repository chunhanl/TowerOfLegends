package ChatRoom;


//--------------------------------------------------------------//
//MyClient.java 
//--------------------------------------------------------------//
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
//--------------------------------------------------------------//
//---MyClient   --   public class
//-2-MyClient()   
//-3-EstablishConnection() --   build connection
//-4-class IncomingReader --   receive data
//-5-actionPerformed()  
//--------------------------------------------------------------//
//MyClient
//--------------------------------------------------------------//
public class MyClient extends JPanel implements ActionListener
{  
	private JFrame application;

	private String name;				//player name
	private String ip;       			//IP
	private BufferedReader  reader;     //read data from Socket
	private PrintStream  writer;		//write data to Socket
	private Thread readerThread;

	private Socket sock;				//build Socket

	private JTextArea incoming; 		//chat area
	//JTextArea namecoming = new JTextArea(10,10); 			//show who are in the chat room

	private JTextField outgoing;     	//input area
	private JLabel jlmane;   
	//JLabel jlip = new JLabel("Input IP :");   
	private JTextField jfmane;
	private JTextField jfip;			//IP
	private JLabel state; 
	
	private JButton sendButton;			//send message
	private JButton setmaneip;			//connect to Server
	private JButton save;				//save file
	private JLabel leave;				//back to MyPanel
	private MyPanel panel;

	
//--------------------------------------------------------------//
//-2-MyClient()   
//--------------------------------------------------------------//
	public MyClient (JFrame f, MyPanel p)
	{ 
		this.setSize(500,750);
		application = f;
		panel = p;
		
		ip = "";
		incoming = new JTextArea(30,40);		//chat area
		outgoing = new JTextField(20);     		//input area
		//jlmane   = new JLabel("Your name :"); 
		//jfmane   = new JTextField("No name",10);
		jfip   = new JTextField("140.113.65.45",10);	//IP
		state  = new JLabel("Please input your name"); 
   
		JPanel maneipPanel = new JPanel();   
		
		setmaneip = new JButton("connect setup");		//connect button
		setmaneip.addActionListener(this);

		leave = new JLabel();							//leave label
		leave.setIcon(new ImageIcon("res/chatroom/leave.png"));
		leave.addMouseListener(							//if click the "leave" button
			new MouseAdapter()
			{
				public void  mouseClicked(MouseEvent event)
				{
					setVisible(false);					//close current panel(MyClient)
				
					panel.setVisible(true);				//show MyPanel
				}
			}
		);
		add(leave);
		
		//maneipPanel.add(jlmane);
		//maneipPanel.add(jfmane);         
		//maneipPanel.add(setmaneip); 
		add(maneipPanel);


		sendButton = new JButton("send");				//send button
		sendButton.addActionListener(this);       

		incoming.setLineWrap(true);         			//set the output format
		incoming.setWrapStyleWord(true); 
		incoming.setEditable(false); 

		JScrollPane qScroller = new JScrollPane(incoming);		//add scroller
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 		//set scroller format
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);		//set scroller format
		
		JPanel mainPanel = new JPanel(); 
		mainPanel.add(qScroller);
		add(mainPanel);
		
		outgoing.addActionListener(this);
				
		JPanel sendPanel  = new JPanel();   
		sendPanel.add(outgoing);
		sendPanel.add(sendButton);
		add(sendPanel);

		save = new JButton("save file");			//save file button
		save.addActionListener(this); 
		add(save);

		//add(BorderLayout.SOUTH,state);   
		connectsetup();    //connect immediately!!!!		
	}

	 private void connectsetup() {
		name=this.panel.fpanelREF.getPlayer().getName();	//get the speaker's name
		ip  = jfip.getText(); 			//get the IP

		EstablishConnection();          //connect to Server

		readerThread = new Thread(new IncomingReader());  
		readerThread.start();			//call IncomingReader().run()
	
	}
//--------------------------------------------------------------//
//-3-EstablishConnection()
//--------------------------------------------------------------//
	private void EstablishConnection()
	{
		try		//if connect success
		{
			sock = new Socket(ip,8888);   	//build Socket to connect Server
			
			InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());  	//get the InputStream from Socket
			reader = new BufferedReader(streamReader);    	//get the input from streamReader to Buffer
			
			writer = new PrintStream(sock.getOutputStream());		//get the OutputStream from Socket

			state.setText("Network build-connect success"); 
			System.out.println("Network build-connect success");    

		}
		catch(IOException ex )		//if not success
		{
			System.out.println("connect build fail");
		}
	}
//--------------------------------------------------------------//
//-4-class IncomingReader --   receive data
//--------------------------------------------------------------//
	public class IncomingReader implements Runnable
	{
		public void run()		//keep running
		{
			String message;
			try
			{
				while ((message = reader.readLine()) != null)	//read the data from Socket by reader
				{
					incoming.append(message + '\n');			//show message to chat area
					/*String s = message;						//get the name location
					int location = s.indexOf(":");
					
					String n = s.substring(0, location);		//get the name*/
				}
			}
			catch(Exception ex )
			{
				ex.printStackTrace();
			}
		}
	} 
//--------------------------------------------------------------//
//-5-actionPerformed()  
//--------------------------------------------------------------//
	public void actionPerformed(ActionEvent e)
	{
		/*if(e.getSource() == setmaneip)		//connect button is clicked
		{
			name = jfmane.getText();		//get the speaker's name
			ip  = jfip.getText(); 			//get the IP

			EstablishConnection();          //connect to Server

			readerThread = new Thread(new IncomingReader());  
			readerThread.start();			//call IncomingReader().run()
		}
		else*/ if((e.getSource() == sendButton) || (e.getSource() == outgoing))		//send button is clicked
		{    
			if((ip != null)&&(outgoing.getText() != ""))    
			{
				try
				{
					writer.println((name + ": " + outgoing.getText() )); 			//write message to Socket

					writer.flush();         										//clear writer
				}
				catch(Exception ex )
				{
					System.out.println("Send fail");
				}

				outgoing.setText("");        //clear input area
			}
		}
		else if (e.getSource() == save)       //save file button is clicked
		{               
			try
			{             
				FileWriter f = new FileWriter(name+".txt");  	//create File I/O
				/*for(int line=0;line<incoming.getRows();line++)
				{
					try{
						f.write(incoming.getText(incoming.getLineStartOffset(line),
								incoming.getLineEndOffset(line)-incoming.getLineStartOffset(line)-1)
								+"\r\n");
					}catch(Exception ee){}
				}*/
				
				f.write(incoming.getText()); 
				f.close();           
				state.setText("saveMsg file success");
			}
			catch (IOException e2)
			{
				state.setText("save file fail");
			}              
		} 
	}
}