package MyServer;

//--------------------------------------------------------------//
//MyServer.java 
//--------------------------------------------------------------//
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;

//--------------------------------------------------------------//
//---MyServer  --   public class
//-1-main  --
//-2-go()   --   connect to port
//  --   if connect success
//-3-Process  --
//-3.1-Process  
//-3.2-run()  
//-3.3-tellApiece() 
//--------------------------------------------------------------//
//MyServer
//--------------------------------------------------------------//
public class MyServer extends JPanel implements ActionListener
{
	private JFrame application;

	private JTextField outgoing;     	//input area
	private JLabel userNameLabel;   
	private JLabel state; //state:people
	private JButton sendButton;			//send message	
	private JTextArea incoming;
	private JScrollPane scroll;
	
	private ServerSocket serverSock;
	private Vector output;		//output
	private Vector streamName;
	
	private Image backGround ;
	
//--------------------------------------------------------------//
//-1-main
//--------------------------------------------------------------//
	public MyServer(JFrame f)
	{
		application=f;  
		application.setResizable(false);
		application.addWindowListener(new WindowAdapter() {
	    	public void windowClosing(WindowEvent windowEvent) {
	    		System.out.println("EXIT");
	    		tellApiece("@Close%Close#Close");
	    	}
		}
		);
		
		try {
			backGround = ImageIO.read(new File("res/server/chatroom_bg.JPG"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		
		Thread scr=new Thread(new adjustScroll());
		scr.start();
		
		//backGround = new ImageIcon("res/server/chatroom_bg.JPG");
		
		
		sendButton = new JButton("send");				//send button
		sendButton.addActionListener(this);       
		
		outgoing = new JTextField(20);     		//input area
		outgoing.addActionListener(this);
		//outgoing.setBorder(BorderFactory.createLineBorder(Color.blue, 0));
		
		
		incoming = new JTextArea(30,40);
		incoming.setLineWrap(true);         			//set the output format
		incoming.setWrapStyleWord(true); 
		incoming.setEditable(false); 
		//incoming.setBorder(BorderFactory.createLineBorder(Color.blue, 0));
		
		JScrollPane qScroller = new JScrollPane(incoming);		//add scroller
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 		//set scroller format
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);		//set scroller format
		scroll = qScroller;
		
		JPanel mainPanel = new JPanel(); 
		mainPanel.add(qScroller);
		mainPanel.setOpaque(false);
		add(mainPanel);
		
		JPanel sendPanel  = new JPanel();   
		sendPanel.add(outgoing);
		sendPanel.add(sendButton);
		sendPanel.setOpaque(false);
		add(sendPanel);
		
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backGround, 0, 0, this);
    }
//--------------------------------------------------------------//
//-2-go()   --   connect to port
//--------------------------------------------------------------//
	public void go() 
	{
		output = new Vector();          //create object array
		streamName = new Vector();    
		try
		{
			serverSock = new ServerSocket(8888);  		//create ServerSocket and set port:8888
			System.out.println("Server running");
			while(true)			//keep tracing
			{
				Socket cSocket = serverSock.accept();    			//wait for socket build here
				
				PrintStream writer = new PrintStream(cSocket.getOutputStream());  	//get the OutputStream from Socket		
				System.out.println(writer); 
				output.add(writer);         
   				Thread t = new Thread(new Process(cSocket)); 
				t.start();        //call Process().run()   
				
				System.out.println(cSocket.getLocalSocketAddress() + " have " + (t.activeCount()-4) + " connects ");               
			}
		}
		catch(Exception ex)
		{
			System.out.println("connect fail");
		}
	}
//--------------------------------------------------------------//
//-3-Process
//--------------------------------------------------------------//
	public class Process implements Runnable
	{   
		BufferedReader reader;  
		Socket sock; 
		
		public Process(Socket cSocket)
		{
			try
			{
				sock = cSocket;
				InputStreamReader isReader = new InputStreamReader(sock.getInputStream()); 		//get the InputStream from Socket
				reader = new BufferedReader(isReader);			//get the input from streamReader to Buffer
			}
			catch(Exception ex)
			{
				System.out.println("connect fail Process");
			} 
		}

		public void run()
		{
			String message;
			try
			{
				while ((message = reader.readLine()) != null)		//read the data from Socket by reader
				{   
					incoming.append(message + '\n');
			
					int player = message.indexOf("@");			//stringtoken
					int command = message.indexOf("%");
					int data = message.indexOf("#");
					
					String PlayerName=message.substring(player+1,command);
					String Command=message.substring(command+1, data);
					String Data=message.substring(data+1,message.length());
					
					StringProcess(PlayerName,Command,Data);							
				}
			}
			catch(Exception ex)
			{
				System.out.println("a connect leave");
			}
		}
	}
//--------------------------------------------------------------//
//-5-處理切割字串
//--------------------------------------------------------------//	
	public void StringProcess(String PlayerName,String Command,String Data) //處理切割字串
	{
		//與client相反，PlayerName都是指令，Command都是名字
		String message="";
		
		//建立玩家資料
		if(PlayerName.compareTo("New") == 0)		
		{
			if(Command.compareTo("StreamName")==0)		//建立玩家水管	
			{
				incoming.append("test in StreamName: "+PlayerName+"/"+Command+"/"+Data+'\n');
				streamName.add(Data);
				incoming.append("outsize: " + output.size()+" streamName size: "+streamName.size()+"\n");
				message = "@" + PlayerName + "%streamName#true";
			}
			else if(Command.compareTo("Create") == 0)	//建立玩家資料
			{
				String testName=Data;
				incoming.append("New Create\n");
				if(canOpenFile(testName))
					message="@" + testName + "%Build#" + readFile(testName);
				else
					message="@" + testName + "%Build#Fail";
			}
			else if(Command.compareTo("Create2") == 0)
			{
				String testName=Data;
				incoming.append("New Create2\n");
				if(canOpenFile(testName))
				{
					incoming.append("Open file success!");
					message="@" + testName + "%Build2#" + readFile(testName);
				}
				else
				{
					incoming.append("Open file fail!");
					message="@" + testName + "%Build2#Fail";
				}
			}
			
			tellApiece(message);
		}	
		
		//離開伺服器
		else if(PlayerName.compareTo("Leave") == 0)
		{
			message = "@" + Command + "%Offline#true";
			tellApiece(message);
			
			//關掉離線的水管
			incoming.append("Processing leave in MyServer:"+Data+"\n");
			incoming.append("streamName.indexOf(Command)"+streamName.indexOf(Command)+'\n');
			for(int i=0;i<output.size();i++)
			{
				if(output.get(i) == null)
					output.remove(i);
			}
			if(streamName.size() == 0)
				output.removeAllElements();
			
			output.removeElementAt( streamName.indexOf(Command) );
			streamName.removeElement(Command);
			incoming.append("outsize: " + output.size()+" streamName size: "+streamName.size()+"\n");
			
			saveFile(Command,Data); //存檔名稱,資料
		}
		
		//其餘指令原封不動傳回去
		else
		{
			message = "@" + PlayerName + "%" + Command + "#" + Data;
			tellApiece(message);
		}
		incoming.append(message + '\n');
		
	}
 //--------------------------------------------------------------//
 //-7-開檔讀寫檔
 //--------------------------------------------------------------//
	public boolean canOpenFile(String URL)
	{
		File name=new File("res/server/player/"+URL+".txt");
		incoming.append("File: "+name+"\n");
		return name.exists();
	}
	public String readFile(String URL)
	{	
		incoming.append("reading file...\n");
		
		Scanner input;
		String data=""; 
		try
		{
			input=new Scanner(new File("res/server/player/"+URL+".txt"));
			int nowLine=0;
			while(input.hasNextLine())
			{
				String line = input.nextLine();
				String []splitData = line.split("\t");
				
				if(nowLine == 0)
				{
					for(int j = 0; j < splitData.length; j++)
					{
						
							data += (splitData[j]+"#");
					}
				}
				else
				{
					for(int j = 0; j < splitData.length; j++)
					{
						if(j == (splitData.length-1))
							data += splitData[j];
						else
							data += (splitData[j]+"#");
					}
				}
				System.out.println(line);
				nowLine++;
				/*
				for(int i = 0; i < splitData.length; i++)
				{
					if(i == (splitData.length-1))
						data += splitData[i];
					else
						data += (splitData[i] + "#");
				}				
				System.out.println(line);
				System.out.println(data);*/
			}
			System.out.println(data);
			input.close();
		}
		catch(NoSuchElementException no)
		{
			System.err.println("File improper");
			System.exit(1);
		}
		catch(IllegalStateException st)
		{
			System.err.println("error reading");
			System.exit(1);
		}
		catch(FileNotFoundException not)
		{
			System.err.println("File not found");
			System.exit(1);
		}

		return data;
	}
	
	public void saveFile(String URL, String data)
	{	
		incoming.append("saving file...\n");
		try
		{
			File file = new File("res/server/player/"+URL+".txt");
			if(!file.exists())
				file.getParentFile().mkdirs();
			file.createNewFile();
			FileWriter f =new FileWriter(file);
			
			String []splitData = data.split("#");
			for(int j = 0; j < splitData.length; j++)
			{
				if(j == 18)
					f.write(splitData[j]+"\r\n");
				else if(j == (splitData.length-1))
					f.write(splitData[j]+"");
				else
					f.write(splitData[j]+"\t");
			}
			incoming.append(data+'\n');
			f.close();
		}
		catch(NoSuchElementException no)
		{
			System.err.println("File improper");
			System.exit(1);
		}
		catch(IllegalStateException st)
		{
			System.err.println("error reading");
			System.exit(1);
		}
		catch(FileNotFoundException not)
		{
			System.err.println("File not found");
			System.exit(1);
		}
		catch (IOException e2)
		{
			System.out.println("save file fail");
		}
	}
	
 //--------------------------------------------------------------//
 //-7-tellApiece 傳到玩家的輸出水管~~~~~
 //--------------------------------------------------------------//		
	public void tellApiece(String message)
	{

		Iterator it = output.iterator(); 

		while(it.hasNext())
		{          
			try
			{
				PrintStream writer = (PrintStream) it.next();  
				writer.println(message); 		//write message to Socket
				writer.flush();           		//clear writer
				incoming.append("Send to Client!\n");
			}
			catch(Exception ex)
			{
				System.out.println("connect fail Process");
			}
		}
	}
 //--------------------------------------------------------------//
 //-9-調整視窗
 //--------------------------------------------------------------//			
	public class adjustScroll implements Runnable
	{
		public void run()
		{
			while(true)
			{
				try{
					Thread.sleep(100);
					JScrollBar bar = scroll.getVerticalScrollBar();
					bar.setValue(bar.getMaximum());	
				}
				catch(InterruptedException e){ }
			}
		}
	}
	
 //--------------------------------------------------------------//
 //-8-actionPerformed 按鈕
 //--------------------------------------------------------------//	
	public void actionPerformed(ActionEvent e)
	{
		if((e.getSource() == sendButton) || (e.getSource() == outgoing))		//send button is clicked
		{    
			try
			{
				if((outgoing.getText()).compareTo("@Clear#All%Stream") == 0)
				{
					output.clear();
					streamName.clear();
				}
				else if((outgoing.getText()).compareTo("@Clear#All%Text") == 0)
				{
					outgoing.setText("");
				}
				else
				{
					tellApiece(outgoing.getText());
				}
				
				incoming.append("Server: " +outgoing.getText() +"\n");
			}
			catch(Exception ex )
			{
				System.out.println("Send fail");
			}
			outgoing.setText("");        //clear input area
		}
	}
}