package ChatRoom;


import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import MyClient.Player;

import java.awt.*;
import java.awt.event.*;
//--------------------------------------------------------------//
//---MyClient   --   public class
//-2-MyClient()   
//-3-EstablishConnection() --   build connection
//-4-class IncomingReader --   receive data
//-5-actionPerformed()  
//-6-GetCommandFromOtherClient() 
//-7-GetPlayer()
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
 
	//存放我的資料和其他玩家資訊
	private Player myData;
	private ArrayList<Player> otherData;
		
 //--------------------------------------------------------------//
 //-2-MyClient()   
 //--------------------------------------------------------------//
	MyClient (JFrame f, MyPanel p)
	{ 
		name="W";
		this.setSize(500, 750);
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
  
		//save = new JButton("save file");			//save file button
		//save.addActionListener(this); 
		//add(save);
  
		//add(BorderLayout.SOUTH,state);    
		
		connectsetup();    //connect immediately!!!!
	}
 
	 private void connectsetup() 
	 {
		 	myData=new Player(name);
		 	otherData=new ArrayList<Player>();
			//name=this.panel.fpanelREF.getPlayer().getName();	//get the speaker's name
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
					int player = message.indexOf("@");			//stringtoken
					int command = message.indexOf("%");
					int data = message.indexOf("#");
					
					String PlayerName=message.substring(player+1,command);
					String Command=message.substring(command+1, data);
					String Data=message.substring(data+1,message.length());
	
					StringProcess(PlayerName,Command,Data);				
				}
			}
			catch(Exception ex )
			{
				ex.printStackTrace();
			}
		}
		
		//處理切割字串
		public void StringProcess(String PlayerName,String Command,String Data) //處理切割字串
		{
			int nowClient = -1;
			if(PlayerName.compareTo("SERVER") != 0)		
				nowClient=findClient(PlayerName); 	

			if(Command.compareTo("SAY") == 0)
			{
				if(nowClient==-1)	//代表是自己
				{
					incoming.append(PlayerName + ": " + Data + '\n');		//show message to chat area
				}
				else
				{
					incoming.append(PlayerName + ": " + Data + '\n');
				}
			}
			else if(Command.compareTo("ATTACK") == 0)
			{
				if(nowClient==-1)	//代表是自己
				{
					myData.setHp(-100);
					incoming.append("You're being attacked!	my HP:" + 
							myData.getHp() + "\n");
				}
				else
				{
					otherData.get(nowClient).setHp(-100);
					incoming.append(otherData.get(nowClient).getName() + " is being attacked!	" + otherData.get(nowClient).getName() + "'s HP:" +
							otherData.get(nowClient).getHp() + "\n");
				}
			}
		}
		
		//在ArrayList<ClientData>找玩家名字 
		public int findClient(String findName)	 
		{
			if(findName.compareTo(name)==0)
				return -1;
			else
			{
				for(int i=0;i<otherData.size();i++)
					if(otherData.get(i).getName().compareTo(findName)==0)
					{
						incoming.append("It's old client:" + findName + "index" + i +'\n'); //測試目前是新還舊的客戶，之後砍掉
						return i;
					}
				
				otherData.add(new Player(findName)); //找不到所以新增玩家資料，並回傳index值
				incoming.append("Add one client:" + findName + "index" + (otherData.size()-1) + '\n'); //測試目前是新還舊的客戶，之後砍掉
				return otherData.size()-1;
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
			name=this.panel.fpanelREF.getPlayer().getName();	//get the speaker's name
			ip  = jfip.getText(); 			//get the IP
   
			EstablishConnection();          //connect to Server
   
			readerThread = new Thread(new IncomingReader());  
			readerThread.start();			//call IncomingReader().run()
		}*/
		if((e.getSource() == sendButton) || (e.getSource() == outgoing))		//send button is clicked
		{    
			if((ip != null)&&(outgoing.getText() != ""))    
			{
				try
				{
					writer.println(("@" + name + "%SAY#" + outgoing.getText() )); 			//write message to Socket
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
				for(int line=0;line<incoming.getRows();line++)
				{
					try{
						f.write(incoming.getText(incoming.getLineStartOffset(line),
								incoming.getLineEndOffset(line)-incoming.getLineStartOffset(line)-1)
								+"\r\n");
					}catch(Exception ee){}
				}
				f.close();           
				state.setText("saveMsg file success");
			}
			catch (IOException e2)
			{
				state.setText("save file fail");
			}              
		}
	}
 //--------------------------------------------------------------//
 //-6-GetCommandFromOtherClient()  
 //--------------------------------------------------------------//	
	public void sendToOtherClient(String message)
	{
		try
		{
			writer.println(("@" + name + "%ATTACK#" + message)); 			//write message to Socket
			writer.flush();         										//clear writer
		}
		catch(Exception ex )
		{
			System.out.println("Send fail");
		}
	}
 //--------------------------------------------------------------//
 //-7-GetPlayer()
 //--------------------------------------------------------------//	
	public Player getPlayer()
	{
		return myData;
	}
	
	public Player getOtherPlayer(int i)
	{
		if(otherData.get(i)!=null)
			return otherData.get(i);
		return null;
	}
}
