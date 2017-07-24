package MyClient;


import java.io.*;
import java.net.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.JOptionPane;

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

	private String name;				//Wplayer name
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
	
	private Image backGround ;
	
 //--------------------------------------------------------------//
 //-2-MyClient()   
 //--------------------------------------------------------------//
	public MyClient (JFrame f, MyPanel p)
	{   
		this.setSize(500, 750);
		application = f;
		panel = p;
		
		try {
			backGround = ImageIO.read(new File("res/server/chatroom_bg.JPG"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		
		incoming = new JTextArea(30,40);		//chat area
		outgoing = new JTextField(20);     		//input area
		//jlmane   = new JLabel("Your name :"); 
		//jfmane   = new JTextField("No name",10);
		jfip   = new JTextField("127.0.0.1",10);	//IP
		state  = new JLabel(); 
        
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
		leave.setOpaque(false);
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
		mainPanel.setOpaque(false);
		add(mainPanel);
		
		outgoing.addActionListener(this);
				
		JPanel sendPanel  = new JPanel();   
		sendPanel.add(outgoing);
		sendPanel.add(sendButton);
		sendPanel.setOpaque(false);
		add(sendPanel);
  
		//save = new JButton("save file");			//save file button
		//save.addActionListener(this); 
		//add(save);
  
		//add(BorderLayout.SOUTH,state);    
		connectsetup();    //connect immediately!!!!
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backGround, 0, 0, this);
    }
//--------------------------------------------------------------//
//-3-connectsetup()  建立連線
//--------------------------------------------------------------//
	private void connectsetup() 
	{
		 EstablishConnection();
		 Login();
		 myData=new Player();
		 //myData=new Player("Guest#10#0#0#10#10#1#1#1#2#1#3#1#4#1#5#1#6#1");
		 otherData=new ArrayList<Player>();
		 //name=this.panel.fpanelREF.getPlayer().getName();	//get the speaker's name
	}
	
	private void Login()
	{
		String account;
		account=JOptionPane.showInputDialog("輸入玩家名稱");
		name=account;
		tellApiece("@New%Create#"+account);
	}
	
	private void Login2()
	{
		String account;
		account=JOptionPane.showInputDialog("查無此帳戶，請重新輸入");
		name=account;
		tellApiece("@New%Create#"+account);
	}
	
	private void EstablishConnection()
	{
		try		//if connect success
		{
			ip  = jfip.getText(); 			//get the IP
			
			sock = new Socket(ip,8888);   	//build Socket to connect Server
			InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());  	//get the InputStream from Socket
			reader = new BufferedReader(streamReader);    	//get the input from streamReader to Buffer
			writer = new PrintStream(sock.getOutputStream());		//get the OutputStream from Socket
   
			state.setText("Network build-connect success"); 
			System.out.println("Network build-connect success");    
		}
		catch(IOException ex )		//if not success
		{
			System.exit(0);
			System.out.println("connect build fail");
		}
		finally
		{
			readerThread = new Thread(new IncomingReader());  
			readerThread.start();			//call IncomingReader().run()	
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
				System.exit(1);
			}
		}
	} 
//--------------------------------------------------------------//
//-5-處理切割字串
//--------------------------------------------------------------//	
	public void StringProcess(String PlayerName,String Command,String Data) //處理切割字串
	{
		//testblock
			System.out.print("arraylist size for other:"+otherData.size()+'\n');
		//建立玩家
		if(Command.compareTo("Build") == 0)	
		{
			if(Data.compareTo("Fail") == 0)	//沒此玩家
			{
				/*name="Guest";
				System.out.println("No Player");
				myData=new Player("Guest#10#0#0#10#10#1#1#1#2#1#3#1#4#1#5#1#6#1");
				tellApiece("@New%StreamName#" + name);*/
				Login2();
			}
			else
			{
				System.out.println("In create Player!");
				if(PlayerName.compareTo(name) == 0)	//是本人
				{
					System.out.println("ME");
					//otherData=new ArrayList<Player>();
					myData=new Player(Data);
					tellApiece("@New%StreamName#" + name);
				}
				else if(PlayerName.compareTo(name) != 0)	//其他玩家建立資料
				{
					System.out.println("OTHER");
					otherData.add(new Player(Data));
					incoming.append("add new friend:"+PlayerName+'\n');
					tellApiece("@New%Create2#" + name);	
				}
				else
					System.out.print("Wrong Build!");
			}
		}
		
		//後來玩家建立前面進來的玩家
		if(Command.compareTo("Build2") == 0 && PlayerName.compareTo(name) != 0)
		{
			incoming.append("add new friend:"+PlayerName+'\n');
			otherData.add(new Player(Data));
		}
		
		//離線，要告訴JFrame說可以離開視窗了
		else if(Command.compareTo("Offline")==0)
		{
			System.out.println("PlayerName:"+PlayerName+'\n');
			if(PlayerName.compareTo(name)==0)
				myData.setOnline(false);
			else
			{
				int nowClient = findClient(PlayerName);
				System.out.println(nowClient);
				if(otherData.get(nowClient)!=null)
					otherData.remove(nowClient);
				incoming.append(PlayerName + " is offlined from game\n");
			}
		}
		
		else if(Command.compareTo("Close")==0)
		{ 
			System.exit(0);
		}
		
		else if(Command.compareTo("streamName")==0)
		{ }
		
		//戰鬥處理
		else	
		{
			int nowClient = -1;
			
			//找玩家，非server和自己
			if(PlayerName.compareTo("Server")!=0 || PlayerName.compareTo(name)!=0)		
				nowClient=findClient(PlayerName); 	
	
			//聊天處理
			if(Command.compareTo("Say") == 0)
			{
				incoming.append(PlayerName + ": " + Data + '\n');
			}
			
			//攻擊指令處理
			else if(Command.compareTo("Attack") == 0)
			{
				int hp=Integer.parseInt(Data);
				if(nowClient==-1)	//代表是自己
				{
					//自己發動攻擊，其他玩家扣寫
					for(int i=0;i<otherData.size();i++)
					{
						otherData.get(i).setHp(-hp);
						if(otherData.get(i).getHp()<=0)
							otherData.get(i).resetHp();
						incoming.append("I attacked player! "+otherData.get(i).getName()+"! his Hp:"+otherData.get(i).getHp()+'\n');
					}
				}
				else
				{
					//別的玩家發動攻擊，自己扣寫
					myData.setHp(-hp);
					if(myData.getHp()<=0)
						myData.resetHp();
					incoming.append("I'm attacked by a player:" + PlayerName + "; my Hp:"+ myData.getHp() + "\n");
				}
			}
			
			//回復指令處理
			else if(Command.compareTo("Recover") == 0)
			{
				if(nowClient==-1)	//代表是自己
				{
					myData.setHp(Integer.parseInt(Data));
					incoming.append("Recover my HP:" + 
							myData.getHp() + "\n");
				}
				else
				{
					otherData.get(nowClient).setHp(Integer.parseInt(Data));
					incoming.append(otherData.get(nowClient).getName() + " is recovering! HP:" + otherData.get(nowClient).getHp() + "\n");
				}
			}
			
			//加卡片
			else if(Command.compareTo("Addcard")==0)
			{
				boolean addCardSucess;
				if(nowClient==-1)	//代表是自己
				{
					 addCardSucess = myData.addNewCard(Integer.parseInt(Data));
					 if( !addCardSucess )
						 JOptionPane.showMessageDialog(null,"背包空間不足");
				}
				else
				{
					addCardSucess = otherData.get(nowClient).addNewCard(Integer.parseInt(Data));
					 if( !addCardSucess )
						 JOptionPane.showMessageDialog(null,"背包空間不足");
				}
			}
			
			//加石頭
			else if(Command.compareTo("Addstone")==0)
			{
				if(nowClient==-1)	//代表是自己
				{
					 myData.addStone(Integer.parseInt(Data));
				}
				else
				{
					otherData.get(nowClient).addStone(Integer.parseInt(Data));
				}
			}
			
			//加體力
			else if(Command.compareTo("Addlife")==0)
			{
				if(nowClient==-1)	//代表是自己
				{
					 myData.addLife(Integer.parseInt(Data));
				}
				else
				{
					otherData.get(nowClient).addLife(Integer.parseInt(Data));
				}
			}
			
			//加石頭
			else if(Command.compareTo("Addcurrentlife")==0)
			{
				if(nowClient==-1)	//代表是自己
				{
					 myData.addCurrentlife(Integer.parseInt(Data));
				}
				else
				{
					otherData.get(nowClient).addCurrentlife(Integer.parseInt(Data));
				}
			}
			
			//加包包空間
			else if(Command.compareTo("Addbagcapacity")==0)
			{
				if(nowClient==-1)	//代表是自己
				{
					 myData.addBagCapacity(Integer.parseInt(Data));
				}
				else
				{
					otherData.get(nowClient).addBagCapacity(Integer.parseInt(Data));
				}
			}
			
			//加錢錢
			else if(Command.compareTo("Addmoney")==0)
			{
				if(nowClient==-1)	//代表是自己
				{
					 myData.addMoney(Integer.parseInt(Data));
				}
				else
				{
					otherData.get(nowClient).addMoney(Integer.parseInt(Data));
				}
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
					System.out.print("It's old client:" + findName + "index" + i +'\n'); //測試目前是新還舊的客戶，之後砍掉
					return i;
				}
			return -1;
			/*
			otherData.add(new Player(findName)); //找不到所以新增玩家資料，並回傳index值
			incoming.append("Add one client:" + findName + "index" + (otherData.size()-1) + '\n'); //測試目前是新還舊的客戶，之後砍掉
			return otherData.size()-1;*/
		}
	}
 //--------------------------------------------------------------//
 //-5-actionPerformed()  
 //--------------------------------------------------------------//
	public void actionPerformed(ActionEvent e)
	{
		if((e.getSource() == sendButton) || (e.getSource() == outgoing))		//send button is clicked
		{    
			if((ip != null)&&(outgoing.getText() != ""))    
			{
				try
				{
					tellApiece("@" + name + "%Say#" + outgoing.getText()); 			//write message to Socket
				}
				catch(Exception ex )
				{
					System.out.println("Send fail");
				}
				outgoing.setText("");        //clear input area
			}
		}
		/*else if (e.getSource() == save)       //save file button is clicked
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
		}*/
	}
 //--------------------------------------------------------------//
 //-7-tellApiece 傳到伺服器囉 水管~~~~~
 //--------------------------------------------------------------//		
	public void tellApiece(String message)
	{
		try
		{
			writer.println(message); 		//write message to Socket
			writer.flush();           		//clear writer
			System.out.println("send to server");
		}
		catch(Exception ex)
		{
			System.out.println("fail send to server");
		}
	}
 //--------------------------------------------------------------//
 //-8-GetPlayer()
 //--------------------------------------------------------------//	
	public Player getPlayer()
	{
		return myData;
	}
	
	public Player getOtherPlayer(String name)
	{
		for(int i=0;i<otherData.size();i++)
			if(otherData.get(i).getName()==name)
				return otherData.get(i);
		return null;
	}
	
	public Player getOtherPlayer(int i)
	{
		return otherData.get(i);
	}
	
	public void leave()
	{
		String mes = myData.getSaveData();
		System.out.println("In MyClient.leave()\n"+mes);
		if(name.compareTo("Guest")==0)
			mes="@Leave%Guest#Guest#10#0#0#10#10#1#1#1#2#1#3#1#4#1#5#1#6#1";
		tellApiece(mes);
	}
	public PrintStream getStream()
	{
		return writer;
	}
}
