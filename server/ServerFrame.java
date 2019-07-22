import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.comm.*;
import java.lang.*;
import java.util.*;
import java.io.*;

/**
 * Sample application using Frame.
 *
 * @author 
 * @version 1.00 05/03/03
 */
public class ServerFrame extends JFrame implements SerialPortEventListener {
    
    private CommPortIdentifier portId;
    private SerialPort serial;
    private OutputStream os;
    private InputStream is;
    private JTextArea ta;
    private JTextArea tb;
    private JTextArea tc;
    private JTextArea td;
    private JTextArea te;
    private JTextArea tf;
    /**
     * The constructor.
     */  
    public ServerFrame()
    {
    	setTitle("Server");
    	setSize(700,550);
    	
    	try
    	{
    		portId = CommPortIdentifier.getPortIdentifier("COM1");
    	}
    	
    	catch(NoSuchPortException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	
    	try
    	{
    		serial = (SerialPort)portId.open("Server", 2000);
    	}
    	
    	catch(PortInUseException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	
    	try
    	{
    		serial.setSerialPortParams(9600, SerialPort.DATABITS_8,
    		SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
    	}
    	
    	catch(UnsupportedCommOperationException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	
    	try
    	{
    		is = serial.getInputStream();
    		os = serial.getOutputStream();
    	}
    	
    	catch(IOException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	
    	JPanel panel = new JPanel();
		
		ta = new JTextArea(10,15);
		tb = new JTextArea(10,15);
		tc = new JTextArea(10,15);
		td = new JTextArea(10,15);
		te = new JTextArea(10,15);
		tf = new JTextArea(10,15);
		
		try
		{
			serial.addEventListener(this);
		}
		
		catch(TooManyListenersException e)
		{
			System.out.println(e.getMessage());
		}
		
		serial.notifyOnDataAvailable(true);
		
		
		JScrollPane scrollPane1 = new JScrollPane(ta);
		JScrollPane scrollPane2 = new JScrollPane(tb);
		JScrollPane scrollPane3 = new JScrollPane(tc);
		JScrollPane scrollPane4 = new JScrollPane(td);
		JScrollPane scrollPane5 = new JScrollPane(te);
		JScrollPane scrollPane6 = new JScrollPane(tf);
		
				
		JButton clear1 = new JButton("Clear");
		JButton clear2 = new JButton("Clear");
		JButton clear3 = new JButton("Clear");
		JButton clear4 = new JButton("Clear");
		JButton clear5 = new JButton("Clear");
		JButton clear6 = new JButton("Clear");
		
		clear1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				ta.setText(null);
				
			}
		});
		
		clear2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				tb.setText(null);
			}
		});
		
		clear3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				tc.setText(null);
			}
		});
		
		clear4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				td.setText(null);
			}
		});
		
		clear5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				te.setText(null);
			}
		});
		
		clear6.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				tf.setText(null);
			}
		});
		
		Border etched = BorderFactory.createEtchedBorder();
		Border titled1 = BorderFactory.createTitledBorder(etched, "Tabel 1");
		Border titled2 = BorderFactory.createTitledBorder(etched, "Tabel 2");
		Border titled3 = BorderFactory.createTitledBorder(etched, "Tabel 3");
		Border titled4 = BorderFactory.createTitledBorder(etched, "Tabel 4");
		Border titled5 = BorderFactory.createTitledBorder(etched, "Tabel 5");
		Border titled6 = BorderFactory.createTitledBorder(etched, "Tabel 6");

	
		Box panelBox1 = Box.createVerticalBox();
		panelBox1.add(scrollPane1);
		panelBox1.add(Box.createVerticalStrut(10));
		panelBox1.add(clear1);
		panelBox1.setBorder(titled1);
		
		Box panelBox2 = Box.createVerticalBox();
		panelBox2.add(scrollPane2);
		panelBox2.add(Box.createVerticalStrut(10));
		panelBox2.add(clear2);
		panelBox2.setBorder(titled2);
		
		Box panelBox3 = Box.createVerticalBox();
		panelBox3.add(scrollPane3);
		panelBox3.add(Box.createVerticalStrut(10));
		panelBox3.add(clear3);
		panelBox3.setBorder(titled3);
		
		Box panelBox4 = Box.createVerticalBox();
		panelBox4.add(scrollPane4);
		panelBox4.add(Box.createVerticalStrut(10));
		panelBox4.add(clear4);
		panelBox4.setBorder(titled4);
		
		Box panelBox5 = Box.createVerticalBox();
		panelBox5.add(scrollPane5);
		panelBox5.add(Box.createVerticalStrut(10));
		panelBox5.add(clear5);
		panelBox5.setBorder(titled5);
		
		Box panelBox6 = Box.createVerticalBox();
		panelBox6.add(scrollPane6);
		panelBox6.add(Box.createVerticalStrut(10));
		panelBox6.add(clear6);
		panelBox6.setBorder(titled6);

		
		Box innerPanel1 = Box.createHorizontalBox();
		innerPanel1.add(panelBox1);
		innerPanel1.add(Box.createHorizontalStrut(40));
		innerPanel1.add(panelBox2);
		innerPanel1.add(Box.createHorizontalStrut(40));
		innerPanel1.add(panelBox3);
		
		Box innerPanel2 = Box.createHorizontalBox();
		innerPanel2.add(panelBox4);
		innerPanel2.add(Box.createHorizontalStrut(40));
		innerPanel2.add(panelBox5);
		innerPanel2.add(Box.createHorizontalStrut(40));
		innerPanel2.add(panelBox6);
		
		Box finalPanel = Box.createVerticalBox();
		finalPanel.add(innerPanel1);
		finalPanel.add(Box.createVerticalStrut(40));
		finalPanel.add(innerPanel2);
		
		panel.add(finalPanel);
   		
   		Container contentPane = getContentPane();
    	contentPane.add(panel,BorderLayout.CENTER);
    	
    	
    }
    
    public void serialEvent(SerialPortEvent evt)
    {
    	switch(evt.getEventType())
		{
			case SerialPortEvent.DATA_AVAILABLE:
			{
				JTextArea t = new JTextArea();
				byte[] buffer = new byte[3];
				char[] received = new char[3]; 
				int remainder;
				String item1 = new String("Tuna Sandwhich\n");
				String item2 = new String("Cheese Burger\n");
				String item3 = new String("Boston Tea\n");
				String item4 = new String("Omellete\n");
				String item5 = new String("Mocha\n");
				String Order = new String();
				
				int numBytes = 0;
				
				try
				{
					while(is.available()>0)
					{
						numBytes = is.read(buffer);
					}
					
					System.out.println("Buffer is " + buffer[0]);
					
					for(int i=0; i<numBytes; i++)
					{
						received[i] = (char)buffer[i];
					}
					
					if(received[0] == '1')
					{
						t = ta;
					}
					
					else if(received[0] == '2')
					{
						t = tb;
					}
					
					else if(received[0] == '3')
					{
						t = tc;
					}
					
					else if(received[0] == '4')
					{
						t = td;
					}
					
					else if(received[0] == '5')
					{
						t = te;
					}
					
					else if(received[0] == '6')
					{
						t =tf;
					}
					
					else
					{
						os.write((byte)31);
					}
					
					switch((int)received[1])
					{
						case 1:
						{
							Order = item1;
						}
			
						case 2:
						{
							Order = item2;
						}
			
						case 3:
						{
							Order = item1 + item2;
						}
				
						case 4:
						{
							Order = item3;
						}
			
						case 5:
						{
							Order = item3 + item1;
						}
				
						case 6:
						{
							Order = item3 + item2;
						}
			
						case 7:
						{
							Order = item3 + item2 + item1;
						}
			
						case 8:
						{
							Order = item4;
						}
					
						case 9:
						{
							Order = item4 + item1;
						}
			
						case 10:
						{
							Order = item4 + item2;
						}
				
						case 11:
						{
							Order = item4 + item2 +item1;
						}
			
						case 12:
						{
							Order = item4 + item3;
						}
			
						case 13:
						{
							Order = item4 + item3 + item1;
						}
			
						case 14:
						{
							Order = item4 + item3 + item2;
						}
			
						case 15:
						{
							Order = item4 + item3 + item2 + item1;
						}
				
						case 16:
						{
							Order = item5;
						}
			
						case 17:
						{
							Order = item5 + item1;
						}
			
						case 18:
						{
							Order = item5 + item2;
						}
			
						case 19:
						{
							Order = item5 + item2 + item1;
						}
			
						case 20:
						{
							Order = item5 + item3;
						}
			
						case 21:
						{
							Order = item5 + item3 + item1;
						}
			
						case 22:
						{
							Order = item5 + item3 + item2;
						}
			
						case 23:
						{
							Order = item5 + item3 + item2 + item1;
						}
			
						case 24:
						{
							Order = item5 + item4;
						}
			
						case 25:
						{
							Order = item5 + item4 + item1;
						}
			
						case 26:
						{
							Order = item5 + item4 + item2;
						}
			
						case 27:
						{
							Order = item5 + item4 + item2 + item1;
						}
			
						case 28:
						{
							Order = item5 + item4 + item3;
						}
			
						case 29:
						{
							Order = item5 + item4 + item3 + item1;
						}
			
						case 30:
						{
							Order = item5 + item4 + item3 + item2;
						}
			
						case 31:
						{
							Order = item5 + item4 + item3 + item2 + item1;
						}
					}
					
					System.out.println("Order received: \n" + Order);
					
					t.append(Order);
				
					
					
					
				}
				
				catch(IOException e)
				{
					System.out.println(e.getMessage());
				}
				break;
			}
    	}
    	
    }
}



