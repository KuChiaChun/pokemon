import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
//資管3A 古佳峻 104403019
public class PokemonFrame extends JFrame{
	private final BorderLayout layout;
	private boolean newFileFlag = true;
	private boolean SaveFlag = false;
	private boolean OpenFlag = true;
	private final JLabel PokemonLabel;
	private final JTextField PokemonName;
	private ImageIcon icon;
	private final JPanel PokemonPanel;
	private final Monsterpanel PicturePanel;
	private final JButton Candybutton;
	private final JButton Openbutton;
	private final JButton Savebutton;
	private final JButton SaveAsbutton;
	private final JLabel Candylabel;
	private final JLabel Filelabel;
	private final JLabel Namelabel;	
	private final JPanel Candypanel;
	private final JPanel Savepanel;
	private final JPanel Filepanel;
	private final JButton Mega;
	private final JButton deadMega;
	private final JLabel Megalabel;
	private static Scanner input;
	private String small="" ;
	private int smallCandy;
	private String medium ="";
	private int mediumCandy;
	private String large ="" ;
	private String Name ="" ;
	private String MegaPic ="mega.jpg";
	private String deadMegaPic ="deadmega.jpg";
	private String Monster = "";
	private ReadFile readFile = new ReadFile();
	private int CandyAmount = 0;
	private int TotalCandyAmount = 0;
	private Path Openpath;
	private Path Savepath;
	private String OpenName ="";	
	private String SaveName ="";
	private  ObjectOutputStream Ooutput;
	private  ObjectInputStream Oinput;
	private PokeSerializable pokeSerializable;
	private SaveFile savefile = new SaveFile();
	private OpenFile openfile = new OpenFile();
	private static int result;
	
	public PokemonFrame() {
		super("Pokemon!!!!!!!!");
		layout = new BorderLayout();
		AudioPlayer audio = null;
		audio = new AudioPlayer();
		audio.loadAudio("12.世界上最危險的東西就是希望 (online-audio-converter.com).wav");
        audio.setPlayCount(2);//一直播放
        audio.play();
		setLayout(layout);
		readFile.openFile();
		readFile.readRecords();
		readFile.closeFile();
		Monster = small;
	    icon = new ImageIcon(getClass().getResource(small));
	    PokemonLabel = new JLabel(icon);
	    PicturePanel = new Monsterpanel();
	    PicturePanel.add(PokemonLabel);
	    PicturePanel.setBackground(Color.WHITE);
		ButtonHandler handler = new ButtonHandler();
	    Namelabel = new JLabel("",JLabel.CENTER);
		Namelabel.setOpaque(true);
		Namelabel.setBackground(Color.WHITE);
	    add(Namelabel,BorderLayout.NORTH);
	    Namelabel.setFont(new Font("黑體",Font.BOLD,30));
	    Namelabel.setVisible(false);
		add(PicturePanel,BorderLayout.CENTER);
		PokemonName = new JTextField();
		PokemonName.setText("Name your monster");
		TextFiledHandler TextHandler = new TextFiledHandler();
		PokemonName.addActionListener(TextHandler);
		PokemonName.setFont(new Font("黑體",Font.BOLD,18));
		Candybutton = new JButton("Give Candy");
		Candybutton.setFont(new Font("黑體",Font.BOLD,18));
		Openbutton	= new JButton("OpenGame");
		Openbutton.addActionListener(handler);
		Openbutton.setFont(new Font("黑體",Font.BOLD,18));
		Savebutton = new JButton("Save");
		Savebutton.addActionListener(handler);
		Savebutton.setFont(new Font("黑體",Font.BOLD,18));
		SaveAsbutton = new JButton("Save As");
		SaveAsbutton.addActionListener(handler);
		SaveAsbutton.setFont(new Font("黑體",Font.BOLD,18));
		Candylabel = new JLabel("0/25");
		Candylabel.setFont(new Font("黑體",Font.BOLD,18));
		Filelabel = new JLabel("New File");
		Filelabel.setFont(new Font("黑體",Font.BOLD,18));
		Mega = new JButton("Mega 進化");
		Mega.setFont(new Font("黑體",Font.BOLD,18));
		Mega.setForeground(Color.RED);
		deadMega = new JButton("Poison");
		deadMega.setFont(new Font("黑體",Font.BOLD,18));
		deadMega.addActionListener(handler);
		Megalabel = new JLabel("你的噴火龍MEGA進化啦!!!!!!!!");
		Megalabel.setFont(new Font("黑體",Font.BOLD,35));
		Megalabel.setForeground(Color.RED);
		PokemonPanel = new JPanel();
		Candypanel = new JPanel();
		Candypanel.add(Mega);
		Mega.setVisible(false);
		Candypanel.add(deadMega);
		deadMega.setVisible(false);
		Candypanel.add(Megalabel);
		Megalabel.setVisible(false);
		Candypanel.add(Candybutton);
		Candypanel.add(Candylabel);
		Candybutton.addActionListener(handler);
		Savepanel = new JPanel();
		Savepanel.add(Openbutton);
		Savepanel.add(Savebutton);
		Savepanel.add(SaveAsbutton);
		Filepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Filepanel.add(Filelabel);
		Mega.addActionListener(handler);
		PokemonPanel.setLayout(new GridLayout(4,1));//設定 Panel的編排方式
		PokemonPanel.add(PokemonName);
		PokemonPanel.add(Candypanel);
		PokemonPanel.add(Savepanel);
		PokemonPanel.add(Filepanel);
	    
	    PokemonPanel.setPreferredSize(new Dimension(800,200));
	    PokemonPanel.setMaximumSize(new Dimension(800, 200));
	    add(PokemonPanel,BorderLayout.SOUTH);
	}
	
	//讀取pokemon.txt的內容 並且將內容存起來
	private class ReadFile
	{
		private void openFile() 
		{
			try
			{
				input = new Scanner(Paths.get("pokemon.txt"));//呼叫Scanner 並讀取pokemon.txt的路徑
				
			}
			catch(IOException ioException)
			{
				System.err.println(ioException.getMessage());
			}
		}
		
		//實際讀取pokemon.txt的內容 並存下
		private void readRecords()
		{
			try
			{
				while(input.hasNext())
				{
					 small =(String) input.next();
					 smallCandy = input.nextInt();
					 medium = input.next();
					 mediumCandy = input.nextInt();
					 large = input.next();
				}
			}
			catch(NoSuchElementException elementException )
			{
				System.err.println(elementException.getMessage());
			}
			catch(IllegalStateException stateException )
			{
				System.err.println(stateException.getMessage());
			}
		}
		private  void closeFile()
		{
			if(input!=null)
				input.close();
		}
	}
	//inner class ButtonHandler
	private class ButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			//按下 Give Candy Button會執行的動作       增加糖果數  判斷糖果數  設定圖片
			if(event.getActionCommand().equals("Give Candy"))
			{
				CandyAmount++;
				TotalCandyAmount++;	
				if(TotalCandyAmount<smallCandy)
				Candylabel.setText(CandyAmount+"/"+smallCandy);
				else if(TotalCandyAmount==smallCandy)//糖果數 達到進化條件  
				{
					Candylabel.setText(CandyAmount+"/"+smallCandy);
					JOptionPane.showMessageDialog(null, "Your monster is envolved");
					CandyAmount = 0;
					Candylabel.setText(CandyAmount+"/"+mediumCandy);
					Monster = medium;
					icon = new ImageIcon(getClass().getResource(Monster));
					PokemonLabel.setIcon(icon);
					
					
				}
				else if(TotalCandyAmount>smallCandy&&TotalCandyAmount<(smallCandy+mediumCandy))//總糖果數介於25~75
				{
					Candylabel.setText(CandyAmount+"/"+mediumCandy);
				}
				else if(TotalCandyAmount==(smallCandy+mediumCandy))//達到第二次進化條件
				{
					Candylabel.setText(CandyAmount+"/"+mediumCandy);
					JOptionPane.showMessageDialog(null, "Congratuation!! Your monster has final envolved");
					Candylabel.setFont(new Font("黑體",Font.BOLD,25));
					Candylabel.setText("∞/∞");
					Monster = large;
					icon = new ImageIcon(getClass().getResource(Monster));
					PokemonLabel.setIcon(icon);
					Monster = large;
					Mega.setVisible(true);
					Candybutton.setVisible(false);
					Candylabel.setVisible(true);
				}
			}
			if(event.getActionCommand().equals("Mega 進化")) //點選mega進化的button
			{
				icon = new ImageIcon(getClass().getResource(MegaPic));//設定圖片
				PokemonLabel.setIcon(icon);
				Candylabel.setVisible(false);
				Mega.setVisible(false);
				deadMega.setVisible(true);
				Megalabel.setVisible(true);
				Monster = MegaPic;
			}
			if(event.getActionCommand().equals("Poison")) 
			{
				icon = new ImageIcon(getClass().getResource(deadMegaPic));//設定圖片
				PokemonLabel.setIcon(icon);
				Mega.setVisible(false);
				deadMega.setVisible(false);
				Megalabel.setText("You killed "+(!Name.equals("")?Name:"your monseter"));
				Monster = deadMegaPic;
			}
			if(event.getActionCommand().equals("OpenGame")) 
			{
				openfile.open();
				openfile.readRecords();
				openfile.closeFile();
				if(pokeSerializable!=null)
				{
					Name = pokeSerializable.getNickname();
					Monster = pokeSerializable.getMonster();
					TotalCandyAmount = pokeSerializable.getCandy();
					if(!Name.equals("")) 
					{
					Namelabel.setVisible(true);
					Namelabel.setForeground(Color.RED);
					Namelabel.setText(Name);
					PokemonName.setText(Name);
					}
					else Namelabel.setText("Name your monster");
					icon = new ImageIcon(getClass().getResource(Monster));
					PokemonLabel.setIcon(icon);
					if(Monster.equals(MegaPic)) 
					{
						
						Mega.setVisible(false);
						Megalabel.setVisible(true);
						Megalabel.setText("你的噴火龍MEGA進化啦!!!!!!!!");
						Candybutton.setVisible(false);
						Candylabel.setVisible(false);
						deadMega.setVisible(true);
						Filelabel.setText(Openpath.toString());
					}
					else if(Monster.equals(deadMegaPic)) 
					{
						Name =pokeSerializable.getNickname();
						if(!Name.equals(""))
						{
							
							deadMega.setVisible(false);
							Megalabel.setVisible(true);
							Megalabel.setText("You killed "+Name);
							Candybutton.setVisible(false);
							Candylabel.setVisible(false);
							deadMega.setVisible(false);
							Filelabel.setText(Openpath.toString());
						}
						else if(Name.equals(""))
						{
							deadMega.setVisible(false);
							Megalabel.setVisible(true);
							Megalabel.setText("You killed your monster QQ");
							Candybutton.setVisible(false);
							Candylabel.setVisible(false);
							deadMega.setVisible(false);
							Filelabel.setText(Openpath.toString());
						}
					}
					else
					{
						//判斷讀取來的紀錄當前的糖果數 並依據糖果數顯示不同的圖片
						if(TotalCandyAmount<smallCandy)
						{
							deadMega.setVisible(false);
							Megalabel.setVisible(false);
							Candybutton.setVisible(true);
							Candylabel.setVisible(true);
							CandyAmount = TotalCandyAmount;
							Candylabel.setText(CandyAmount+"/"+smallCandy);
						
						}
						else if(TotalCandyAmount>=smallCandy&&TotalCandyAmount<(smallCandy+mediumCandy))
						{
							deadMega.setVisible(false);
							Megalabel.setVisible(false);
							Candybutton.setVisible(true);
							Candylabel.setVisible(true);
							CandyAmount = TotalCandyAmount-smallCandy;
							Candylabel.setText(CandyAmount+"/"+mediumCandy);
						}
						else if(TotalCandyAmount>=(smallCandy+mediumCandy))
						{
							Mega.setVisible(true);
							deadMega.setVisible(false);
							Megalabel.setVisible(false);
							Candybutton.setVisible(false);
							Candylabel.setVisible(true);
							Candylabel.setFont(new Font("黑體",Font.BOLD,25));
							Candylabel.setText("∞/∞");
						}
						Filelabel.setText(Openpath.toString());
					}
				}

			}
			
			if(event.getActionCommand().equals("Save")) 
			{

				if(Openpath!=null)
				{
					SaveFlag = true;
					result = 0;
				}
				if(SaveFlag == true) 
				{
					pokeSerializable = new PokeSerializable(Name,Monster,TotalCandyAmount);
					newFileFlag = false;
					savefile.open();
					savefile.addRecord();
					savefile.closeFile();
					newFileFlag = true;
				}
				else
				{
				JOptionPane.showMessageDialog(null, "You should Save as a new file first ");
				}

			}
			if(event.getActionCommand().equals("Save As"))
			{
				SaveFlag = true;
				newFileFlag = true;
				pokeSerializable = new PokeSerializable(Name,Monster,TotalCandyAmount);
				savefile.open();
				savefile.addRecord();
				savefile.closeFile();
			}
		}
		
	}
	
	private class TextFiledHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			if(!event.getActionCommand().equals("Name your monster"))
			{
			Name = event.getActionCommand();
			Namelabel.setVisible(true);
			Namelabel.setForeground(Color.RED);
			Namelabel.setText(Name);
			}
			
		}
	}
	
	private class OpenFile{
		private void open() {
			JFileChooser chooser = new JFileChooser();
			  result = chooser.showOpenDialog(null);
		      if (result == JFileChooser.APPROVE_OPTION)
		      {
		    	  OpenName =chooser.getSelectedFile().getName();
		    	  Openpath=chooser.getSelectedFile().toPath();
		    	  try {
			    	  Oinput = new ObjectInputStream(Files.newInputStream(Openpath));
			    	  SaveFlag = true;
			      }			     
			      catch(IOException ioException)
				     {
			    	  JOptionPane.showMessageDialog(null, "Wrong type of file. Please try another file");
				   	  System.err.println("Error opening file. Please try again");
				   	  OpenFlag = false;//判斷是否有產生例外 有產生則設為false 使得之後的讀檔動作不會繼續執行
			      }			
		      }
		      if (result == JFileChooser.CANCEL_OPTION) {
		        System.out.println("You pressed cancel");

		      }
		     		      
		}
		
		private  void readRecords()
		{
			 if (result != JFileChooser.CANCEL_OPTION) //判斷使用者先前是否選擇取消
			 {
				 if(OpenFlag)
				 {
					 try 
				 	 {					
					 	while(true) 
					 	{
						 	pokeSerializable = (PokeSerializable)Oinput.readObject();//讀取檔案中的pokeSerializable物件
					 	}
				 	}
				 	catch(EOFException endOfFileException) 
				 	{
					 	JOptionPane.showMessageDialog(null, "開啟檔案:"+OpenName);
					 	System.out.printf("開啟檔案:%s\n",OpenName);
					 
				 	}
				 	catch(ClassNotFoundException classNotFoundException)
				 	{
					 	System.out.println("檔案格式錯誤");
				 	}
				 	catch(IOException ioException)
				 	{
				 		System.err.println(ioException.getMessage());
				 	}
				 }
			 }
		
		}
		
		private void closeFile() {
			OpenFlag = true;
			try
			{
				if(Oinput!=null)
				{
					Oinput.close();
					Savepath = Openpath;//這邊是為了讓save button不會判斷為尚未存檔  並且也給予路徑
					SaveName = OpenName;
				}
			}
			catch(IOException ioException)
			{
				System.err.println("error closing file");
				System.exit(1);
			}
		}			
	}
	
	private class SaveFile
	{
		public void open()
		{
			 if(newFileFlag)//判斷為要另存檔案 則開啟filechooser 
			{
				JFileChooser chooser = new JFileChooser();
				 result = chooser.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION)
				{
					SaveName =chooser.getSelectedFile().getName();
					
					Savepath=chooser.getSelectedFile().toPath();
				
				
				try // open file
				{
					Ooutput = new ObjectOutputStream(          
					Files.newOutputStream(Savepath));
				} 
				catch (IOException ioException)
				{
					System.err.println("Error opening file.");
					System.exit(1);
				} 
				}
				if (result == JFileChooser.CANCEL_OPTION) 
				{
					System.out.println("You pressed cancel");
			        SaveFlag = false;
				}

			}
			
			else if(!newFileFlag)//不是另存新檔  因此直接依照原有路徑存檔即可 
			{
				try // open file
				{
					Ooutput = new ObjectOutputStream(          
					Files.newOutputStream(Savepath));
				} 
				catch (IOException ioException)
				{
					System.err.println("Error opening file.");
					System.exit(1);
				} 
			}
		
			
	    }
		public void addRecord()
		{			
				if (result == JFileChooser.APPROVE_OPTION)
				{
					try {
					Ooutput.writeObject(pokeSerializable);//將pokeSerializable物件輸出
					}
				
					catch (NoSuchElementException elementException)
					{
						System.err.println("Invalid input Please try again");
					}
					catch (IOException ioException)
					{
						System.err.println("Error writing");
					
					}	
				}
			}				
		public void closeFile()
		{
			try
			{
				if (result == JFileChooser.APPROVE_OPTION)
				{
					if(Ooutput!=null)
					{
						JOptionPane.showMessageDialog(null, "儲存檔名:"+SaveName);//提醒使用者儲存之檔名
						System.out.printf("儲存檔名:%s\n", SaveName);
						Ooutput.close();
					}
				}
			}
			catch(IOException ioException)
			{
				System.err.println("Error Closing file");
			}
		}
	}
	private class Monsterpanel extends JPanel
	{
		private ImageIcon backgroundicon;
		private Image background;
		
		public Monsterpanel()
		{
			backgroundicon = new ImageIcon(getClass().getResource("fire.png"));
			background = backgroundicon.getImage();
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(background,0,0,800,520,null);
		}
	}
}
	
	

	


