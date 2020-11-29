import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainFrame extends JFrame {

	private JTextArea numVotanti;
	private JLabel inserisciVotanti;
	private JButton generaFile;
	private ListaCodici ls;
	private String pathFile = "D:\\WAYouth\\Elezioni 2020\\codici.txt";
	
	public MainFrame()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300,150);
		setLocation(200,200);
		ls= new ListaCodici();
		numVotanti=new JTextArea();
		inserisciVotanti=new JLabel();
		generaFile=new JButton();
		setTitle("Generatore Codici Votanti");
		add(MainPanel());
		setResizable(false);
		setVisible(true);
	}
	
	public JPanel MainPanel()
	{
		numVotanti.setPreferredSize(new Dimension(60,20));
		numVotanti.setText("0");
		generaFile.setText("Genera File");
		generaFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				ls.setVotanti(Integer.parseInt(numVotanti.getText()));
				System.out.println("Votanti "+Integer.parseInt(numVotanti.getText()));
				ls.generaCodici();
				System.out.println("Codici Generati");
				try
				{
					File file = new File(pathFile);
					file.createNewFile();
					PrintWriter pw = new PrintWriter(file);
					for(int i=0; i<ls.getCodici().size();i++)
					{
						pw.write(ls.getCodici().get(i)+"\n");
						System.out.println("Scrittura numero "+i+" valore: "+ls.getCodici().get(i));
					}
					pw.close();
					System.out.println("File Chiuso");
				}
				catch(IOException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		inserisciVotanti.setText("Inserisci il Numero di Votanti:");
		JPanel mainPanel=new JPanel();
		mainPanel.add(inserisciVotanti);
		mainPanel.add(numVotanti);
		mainPanel.add(generaFile);
		return mainPanel;
	}
	
	public static void main (String args[])
	{
		MainFrame mf = new MainFrame();
	}
}
