/**
* Client SuperWaba Application
* Author: 		Ahmad Hafeezi bin Ali Amat
* Nickname: 	Ryu Hidematsu
* Student id: 	4663
* Version: 		2.00
**/

import waba.ui.*;
import waba.io.*;
import waba.fx.*;

public class Client extends MainWindow 
{
	MenuBar mBar;
	TabPanel tabPanel;
	Button sendButton;
	Button checkButton;
	Button payButton;
	Button picButton;
	Button queryButton;
	ComboBox combo1,combo2;
	Check chk1, chk2, chk3, chk4, chk5;
	Label line1, line2, line3;
	private String item1, item2, item3, item4, item5, list, helpMessage;
	private double price1, price2, price3, price4, price5, price6, total;
	private byte buf[];
	private byte sendBuf[];
	private byte readBuf[];
	private byte payBuf[];
	private byte queryBuf[];
	private char table;
	private char rtable;
	private char sReply[];
	private boolean sent;
	private boolean ready;
	SerialPort port;
	
	
	public Client()
	{
		/**
		* Sets the title, and the title type  
		**/
		super("HiDeMatsU No Resutoran", ROUND_BORDER);
		setDoubleBuffer(true);
	}
	
	public void onStart()
	{
		/**
		*  Creates the items and place them on a menu bar
		**/
		
		String column1[] = 
		{
			"Help",
			"How to Order...",
			
		};
		
		String column2[] = 
		{
			"Location",
			"Table 1",
			"Table 2",
			"Table 3",
			"Table 4",
			"Table 5",
			"Table 6",
		};
		
		setMenuBar (mBar = new MenuBar(new String[][]{column1,column2}));
		
		/**
		* Variable declarations
		**/
		
		table = '1';
		rtable = '!';
		sReply = new char[10];
		
		line1 = new Label("Welcome to Hidematsu's Restaurant!");
		
		item1 = new String("Tuna Sandwhich");
		item2 = new String("Cheese Burger");
		item3 = new String("Boston Tea");
		item4 = new String("Omolette");
		item5 = new String("Mocha");
		list = new String();
		helpMessage = new String("Welcome to Hidematsu's Restaurant!||To see our menu, please tap|on the Menu tab button.||Select your choices and press|the CHECK button to check| your choices and the total.||Then tap on the SEND |button to send the order. ||At any time, you may query |about your order by tapping|the QUERY button.||If you're done, you may |tap the PAY button to process|the bill.");
		
		price1 = 2.50;
		price2 = 3.00;
		price3 = 1.00;
		price4 = 2.00;
		price5 = 1.50;
		total = 0;
		
		chk1 = new Check(item1);
		chk2 = new Check(item2);
		chk3 = new Check(item3);
		chk4 = new Check(item4);
		chk5 = new Check(item5);
		
		sendButton = new Button("Send");
		checkButton = new Button("Check");
		payButton = new Button("Pay");
		queryButton = new Button("Query");
		picButton = new Button(new Image("ama03g.bmp"));
		
		
		buf = new byte[2];
		sendBuf = new byte[2];
		readBuf = new byte[10];
		queryBuf = new byte[2];
		payBuf = new byte[2];
		
		/**
		* Creates the tab panels
		**/
		String []captions = {"Welcome", "Menu"};
		add(tabPanel = new TabPanel(captions));
		tabPanel.setGaps(2,2,2,2);
		tabPanel.setRect(getClientRect().modifiedBy(0,0,0,0));
		
		/**
		* Creates the first tab panel  
		**/
		Container c1 = tabPanel.getPanel(0);
      	c1.add(line1,CENTER,TOP+5);
      	c1.add(picButton,CENTER,AFTER+10);
      	
      	
      	/**
      	* Creates the second tab panel 
      	**/
      	Container c2 = tabPanel.getPanel(1);
      	c2.add(chk1,LEFT+5,TOP+5);
      	c2.add(new Label("RM2.50"),RIGHT,SAME);
      	c2.add(chk2,LEFT+5,AFTER+10);
      	c2.add(new Label("RM3.00"),RIGHT,SAME);
      	c2.add(chk3,LEFT+5,AFTER+10);
      	c2.add(new Label("RM1.00"),RIGHT,SAME);
      	c2.add(chk4,LEFT+5,AFTER+10);
      	c2.add(new Label("RM2.00"),RIGHT,SAME);
      	c2.add(chk5,LEFT+5,AFTER+10);
      	c2.add(new Label("RM1.50"),RIGHT,SAME);
      	c2.add(checkButton, LEFT, BOTTOM);
      	c2.add(queryButton, AFTER+15, SAME);
      	c2.add(sendButton, AFTER+15, SAME);
      	c2.add(payButton, RIGHT, SAME);
     }
     /**
     * Event declarations  
     **/
     
     public void onEvent(Event evt)
     {
     	switch(evt.type)
     	{
     		/**
     		* The processes upon selection of menu bar items. 
     		**/
     		case ControlEvent.WINDOW_CLOSED:
     		{
     			
     			
     			if(evt.target == mBar)
     			{
     				switch (mBar.getSelectedMenuItem())
     				{
     					case -1: break;
     					case  1:
     					{
     						popupModal(new MessageBox("How To Order...", helpMessage));
     						break;
     					}
     					
     					case 101:
     					{
     						popupModal(new MessageBox("Alert", "Location is set to Table 1"));
     						table = '1';
     						rtable = '!';
     						break;
     					}
     					
     					case 102:
     					{
     						popupModal(new MessageBox("Alert", "Location is set to Table 2"));
     						table = '2';
     						rtable = '@';
     						break;
     					}
     					
     					case 103:
     					{
     						popupModal(new MessageBox("Alert", "Location is set to Table 3"));
     						table = '3';
     						rtable = '#';
     						break;
     					}
     					
     					case 104:
     					{
     						popupModal(new MessageBox("Alert", "Location is set to Table 4"));
     						table = '4';
     						rtable = '$';
     						break;
     					}
     					
     					case 105:
     					{
     						popupModal(new MessageBox("Alert", "Location is set to Table 5"));
     						table = '5';
     						rtable = '%';
     						break;
     					}
     					
     					case 106:
     					{
     						popupModal(new MessageBox("Alert", "Location is set to Table 6"));
     						table = '6';
     						rtable = '^';
     						break;
     					}
     				}
     			}
     		}
     		
     		/**
     		* The processes upon pressing the corresponding command button on the second tab panel
     		**/
     		case ControlEvent.PRESSED:
     		{
     			if(evt.target == picButton)
     			{
     				popupModal(new MessageBox("Greetings from the chef", "Irashaimase!||Watashi wa Amy desu.|Kyo no kawaii chef desu.|Yoroshiku Ne! (^_^)"));
     				break;
     			} 
     			
     			if(evt.target == checkButton)
     			{
     				if(chk1.getChecked() == true && chk2.getChecked() == false && chk3.getChecked() == false && chk4.getChecked() == false && chk5.getChecked() == false)
     				{
     					total = price1;
     					popupModal(new MessageBox("Choices",item1 + "||Total is RM" + total));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'A';
     					
     				}
     				
     				else if(chk2.getChecked() == true && chk1.getChecked() == false && chk3.getChecked() == false && chk4.getChecked() == false && chk5.getChecked() == false)
     				{
     					total = price2;
     					popupModal(new MessageBox("Choices",item2 + "||Total is RM" + price2));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'B';
     				}
     				
     				else if(chk2.getChecked() == true && chk1.getChecked() == true && chk3.getChecked() == false && chk4.getChecked() == false && chk5.getChecked() == false)
     				{
     					total = price1 + price2;
     					popupModal(new MessageBox("Choices",item1 + "|" + item2 + "||Total is RM" + total));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'C';
     				}
     				
     				else if(chk3.getChecked() == true && chk1.getChecked() == false && chk2.getChecked() == false && chk4.getChecked() == false && chk5.getChecked() == false)
     				{
     					total = price3;
     					popupModal(new MessageBox("Choices", item3 + "||Total is RM" + total));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'D';
     				}
     				
     				else if(chk3.getChecked() == true && chk1.getChecked() == true && chk2.getChecked() == false && chk4.getChecked() == false && chk5.getChecked() == false)
     				{
     					total = price1 + price3;
     					popupModal(new MessageBox("Choices",item1 + "|" + item3 +"||Total is RM" + total));     					
     					buf[0] = (byte)table;
     					buf[1] = (byte)'E';
     				}
     				
     				else if(chk3.getChecked() == true && chk2.getChecked() == true && chk1.getChecked() == false && chk4.getChecked() == false && chk5.getChecked() == false)
     				{
     					total = price2 + price3;
     					list = item2 + "|" + item3;
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'F';
     				}
     				
     				else if(chk3.getChecked() == true && chk2.getChecked() == true && chk1.getChecked() == true && chk4.getChecked() == false && chk5.getChecked() == false)
     				{
     					total = price1 + price2 + price3;
     					list = (item1 + "|" + item2 + "|" + item3);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'G';
     				}
     				
     				else if(chk4.getChecked() == true && chk2.getChecked() == false && chk3.getChecked() == false && chk1.getChecked() == false && chk5.getChecked() == false)
     				{
     					total = price4;
     					list = (item4);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'H';
     				}
     				
     				else if(chk4.getChecked() == true && chk1.getChecked() == true && chk2.getChecked() == false && chk3.getChecked() == false && chk5.getChecked() == false)
     				{
     					total = price1 + price4;
     					list = (item1 + "|" + item4);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'I';
     				}
     				
     				else if(chk4.getChecked() == true && chk2.getChecked() == true && chk1.getChecked() == false && chk3.getChecked() == false && chk5.getChecked() == false)
     				{
     					total = price2 + price4;
     					list = (item2 + "|" + item4);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'J';
     				}
     				
     				else if(chk4.getChecked() == true && chk2.getChecked() == true && chk1.getChecked() == true && chk3.getChecked() == false && chk5.getChecked() == false)
     				{
     					total = price1 + price2 + price4;
     					list = (item1 + "|" + item2 + "|" + item4);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'K';
     				}
     				
     				else if(chk4.getChecked() == true && chk3.getChecked() == true && chk1.getChecked() == false && chk2.getChecked() == false && chk5.getChecked() == false)
     				{
     					total = price3 + price4;
     					list = (item3 + "|" + item4);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'L';
     				}
     				
     				else if(chk4.getChecked() == true && chk3.getChecked() == true && chk1.getChecked() == true && chk2.getChecked() == false && chk5.getChecked() == false)
     				{
     					total = price1 + price3 + price4;
     					list = (item1 + "|" + item3 + "|" + item4);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'M';
     				}
     				
     				else if(chk4.getChecked() == true && chk3.getChecked() == true && chk2.getChecked() == true && chk1.getChecked() == false && chk5.getChecked() == false)
     				{
     					total = price2 + price3 + price4;
     					list = (item2 + "|" + item3 + "|" + item4);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'N';
     				}
     				
     				else if(chk4.getChecked() == true && chk3.getChecked() == true && chk2.getChecked() == true && chk1.getChecked() == true && chk5.getChecked() == false)
     				{
     					total = price1 + price2 + price3 + price4;
     					list = (item1 + "|" + item2 + "|" + item3 + "|" + item4);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'O';
     				}
     				
     				else if(chk5.getChecked() == true && chk1.getChecked() == false && chk2.getChecked() == false && chk3.getChecked() == false && chk4.getChecked() == false)
     				{
     					total = price5;
     					list = (item5);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'P';
     				}
     				
     				else if(chk5.getChecked() == true && chk1.getChecked() == true && chk2.getChecked() == false && chk3.getChecked() == false && chk4.getChecked() == false)
     				{
     					total = price1 + price5;
     					list = (item1 + "|" + item5);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'Q';
     				}
     				
     				else if(chk5.getChecked() == true && chk2.getChecked() == true && chk1.getChecked() == false && chk3.getChecked() == false && chk4.getChecked() == false)
     				{
     					total = price2 + price5;
     					list = (item2 + "|" + item5);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'R';
     				}
     				
     				else if(chk5.getChecked() == true && chk2.getChecked() == true && chk1.getChecked() == true && chk3.getChecked() == false && chk4.getChecked() == false)
     				{
     					total = price1 + price2 + price5;
     					list = (item1 + "|" + item2 + "|" + item5);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'S';
     				}
     				
     				else if(chk5.getChecked() == true && chk3.getChecked() == true && chk1.getChecked() == false && chk2.getChecked() == false && chk4.getChecked() == false)
     				{
     					total = price3 + price5;
     					list = (item3 + "|" + item5);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'T';
     				}
     				
     				else if(chk5.getChecked() == true && chk3.getChecked() == true && chk1.getChecked() == true && chk2.getChecked() == false && chk4.getChecked() == false)
     				{
     					total = price1 + price3 + price5;
     					list = (item1 + "|" + item3 + "|" + item5);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'U';
     				}
     				
     				else if(chk5.getChecked() == true && chk3.getChecked() == true && chk2.getChecked() == true && chk1.getChecked() == false && chk4.getChecked() == false)
     				{
     					total = price2 + price3 + price5;
     					list = (item2 + "|" + item3 + "|" + item5);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'V';
     				}
     				
     				else if(chk5.getChecked() == true && chk3.getChecked() == true && chk2.getChecked() == true && chk1.getChecked() == true && chk4.getChecked() == false)
     				{
     					total = price1 + price2 + price3 + price5;
     					list = (item1 + "|" + item2 + "|" + item3 + "|" + item5);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'W';
     				}
     				
     				else if(chk5.getChecked() == true && chk4.getChecked() == true && chk1.getChecked() == false && chk2.getChecked() == false && chk3.getChecked() == false )
     				{
     					total = price4 + price5;
     					list = (item4 + "|" + item5);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'X';
     				}
     				
     				else if(chk5.getChecked() == true && chk4.getChecked() == true && chk1.getChecked() == true && chk2.getChecked() == false && chk3.getChecked() == false )
     				{
     					total = price1 + price4 + price5;
     					list = (item1 + "|" + item4 + "|" + item5);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));
     					buf[0] = (byte)table;
     					buf[1] = (byte)'Y';
     				}
     				
     				else if(chk5.getChecked() == true && chk4.getChecked() == true && chk2.getChecked() == true && chk1.getChecked() == false && chk3.getChecked() == false )
     				{
     					total = price2 + price4 + price5;
     					list = (item2 + "|" + item4 + "|" + item5);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));;
     					buf[0] = (byte)table;
     					buf[1] = (byte)'Z';
     				}
     				
     				else if(chk5.getChecked() == true && chk4.getChecked() == true && chk2.getChecked() == true && chk1.getChecked() == true && chk3.getChecked() == false )
     				{
     					total = price1 + price2 + price4 + price5;
     					list = (item1 + "|" + item2 + "|" + item4 + "|" + item5);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));;
     					buf[0] = (byte)table;
     					buf[1] = (byte)'a';
     				}
     				
     				else if(chk5.getChecked() == true && chk4.getChecked() == true && chk3.getChecked() == true && chk1.getChecked() == false && chk2.getChecked() == false)
     				{
     					total = price3 + price4 + price5;
     					list = (item3 + "|" + item4 + "|" + item5);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));;
     					buf[0] = (byte)table;
     					buf[1] = (byte)'b';
     				}
     				
     				else if(chk5.getChecked() == true && chk4.getChecked() == true && chk3.getChecked() == true && chk1.getChecked() == true && chk2.getChecked() == false)
     				{
     					total = price1 + price3 + price4 + price5;
     					list = (item1 + "|" + item3 + "|" + item4 + "|" + item5);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));;
     					buf[0] = (byte)table;
     					buf[1] = (byte)'c';
     				}
     				
     				else if(chk5.getChecked() == true && chk4.getChecked() == true && chk3.getChecked() == true && chk2.getChecked() == true && chk1.getChecked() == false)
     				{
     					total = price2 + price3 + price4 + price5;
     					list = (item2 + "|" + item3 + "|" + item4 + "|" + item5);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));;
     					buf[0] = (byte)table;
     					buf[1] = (byte)'d';
     				}
     				
     				else if(chk5.getChecked() == true && chk4.getChecked() == true && chk3.getChecked() == true && chk2.getChecked() == true && chk1.getChecked() == true)
     				{
     					total = price1 + price2 + price3 + price4 + price5;
     					list = (item1 + "|" + item2 + "|" + item3 + "|" + item4 + "|" + item5);
     					popupModal(new MessageBox("Choices", list + "||Total is RM" + total));;
     					buf[0] = (byte)table;
     					buf[1] = (byte)'e';
     					
     				}
     				break;
     			}
     			
     			if (evt.target == sendButton)
     			{
     				port = new SerialPort(0,9600);
     				int iterator = 0;
     				sent = false;
     				
     				
     				for(int i=0; i<4;i++)  
     				{
     					send(buf);
     					receive();
     					
     					
     					if(sent == true) 
     					{
     					popupModal(new MessageBox("Succes!", "Order sent!"));
     					break;
     					}
     					
     					

     					
     				}	
     				
     				if (sent == false)
     				{
     					popupModal(new MessageBox("Failed!", "Problem during transmission.|Please try again"));
     				}
     							
     				port.close();
     				break;
     			}
     			
     			if(evt.target == queryButton)
     			{
     				port = new SerialPort(0,9600);
     				sent = false;
     				int iterator = 0;
     				queryBuf[0] = (byte)table;
     				queryBuf[1] = (byte)'q';
     				
     				
     				for(int i=0; i<4;i++)  
     				{
     					send(queryBuf);
     					queryReceive();
     				}
     				port.close();
     				break;
     			}
     			
     			if(evt.target == payButton)
     			{
     				port = new SerialPort(0,9600);
     				sent = false;
     				int iterator = 0;
     				payBuf[0] = (byte)table;
     				payBuf[1] = (byte)'p';
     				
     				for(int i=0; i<4;i++)  
     				{
     					send(payBuf);
     					receive();
     					
     					if(sent == true) 
     					{
     						popupModal(new MessageBox("Succes!", "Message sent!"));
     						break;
     					}
     				}	
     				
     				if (sent == false)
     				{
     					popupModal(new MessageBox("Failed!", "Problem during transmission.|Please try again"));
     				}
     				
     				port.close();
     				break;
     			}
     			
     			

     		}
     	}
     }
     /**
     * Serial port transmit function 
     **/
     public void send(byte b[])
     {
     	
     	sendBuf = b;
     	
     	if(port != null)
     	{
     		port.writeBytes(sendBuf, 0, 2);
     	}
     	
     	else
     	{
     		popupModal(new MessageBox("Error!", "Port couldn't be openned!"));
     	}
     }
     
     /**
     * Serial Port receive function
     **/
     public void receive()
     {
     	
     	int count = port.readBytes(readBuf, 0 ,10);
     	
     	if(count>0)
     	{
     		for(int i=0; i<count ; i++)
     		{
     			sReply[i] = (char)readBuf[i];
     		}
     		
     		for(int j=0; j<count; j++)
     		{
     			if(sReply[j] == rtable && sReply[j+1] == 'k')
     			{
     				sent = true;
     				break;
     			}
     		}
     	}
     	
     	else
     	{
     		sent = false;
     	}
     }
     
     /**
     * Customized Serial Port receive function for the Query command
     **/
     public void queryReceive()
     {
     	
     	int count = port.readBytes(readBuf, 0, 10);
     	
     	if(count>0)
     	{
     		for (int i=0; i<count ; i++)
     		{
     			sReply[i] = (char)readBuf[i];
     		}
     		
     		for (int j=0; j<count; j++)
     		{
     			if(sReply[j] == rtable && sReply[j+1] == 'u')
     			{
     				popupModal(new MessageBox("Query", "Order not ready"));
     				break;
     			}
     			
     			else if(sReply[j] == rtable && sReply[j+1] == 'r')
     			{
     				popupModal(new MessageBox("Query", "Order is ready!|Your order will arrive shortly!"));
     				break;	
     			}
     		}
     	}
     	
     	else
     	{
     		popupModal(new MessageBox("Query", "No reply from kitchen.|Please query again in a few minutes"));
     	}
     }
     			
     		
     		
    
}
