import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainFrame extends JFrame {

	private JButton verificaFile;
	private String pathFileSorgente = "D:\\WAYouth\\Elezioni 2020\\codici.txt"; //Inserire Path in Base al Garante
	private String pathFileVotanti = "D:\\WAYouth\\Elezioni 2020\\codicinew.txt"; //Inserire Path in Base al Garante
	private String pathFileEsiti = "D:\\WAYouth\\Elezioni 2020\\esiti.txt"; //Inserire Path in Base al Garante

	public MainFrame()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300,150);
		setLocation(200,200);
		verificaFile=new JButton();
		setTitle("Verificatore Codici Votanti");
		add(MainPanel());
		setResizable(false);
		setVisible(true);
	}

	public JPanel MainPanel()
	{
		verificaFile.setText("Verifica File");
		verificaFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					File fileSorgente = new File(pathFileSorgente);
					File fileVotanti = new File(pathFileVotanti);
					File fileEsiti = new File(pathFileEsiti);
					fileEsiti.createNewFile();
					PrintWriter pw = new PrintWriter(fileEsiti);
					ArrayList<Integer> sorgente = new ArrayList<Integer>();
					ArrayList<Integer> votanti = new ArrayList<Integer>();
					ArrayList<Integer> scrutinati = new ArrayList<Integer>();
					FileReader frs= new FileReader(fileSorgente);
					System.out.println("Sorgente ok");
					BufferedReader brs= new BufferedReader(frs);
					String codice = brs.readLine();
					while(codice!=null)
					{
						sorgente.add(Integer.parseInt(codice));
						codice = brs.readLine();
					}
					FileReader frv= new FileReader(fileVotanti);
					System.out.println("Votanti ok");
					BufferedReader brv= new BufferedReader(frv);
					codice = brv.readLine();
					while(codice!=null)
					{
						votanti.add(Integer.parseInt(codice));
						codice = brv.readLine();
					}
					for(int i=0;i<votanti.size();i++)
					{
						if(sorgente.contains(votanti.get(i)))
						{
							if(scrutinati.contains(votanti.get(i)))
								System.out.println("Codice "+votanti.get(i)+" alla posizione "+(i+1)+"  gi� usato");
							else
								pw.write("Codice Valido\n");
							scrutinati.add(votanti.get(i));
						}
						else
						{
							System.out.println("Codice "+votanti.get(i)+" alla posizione "+(i+1)+"  non valido");
							pw.write("Codice "+votanti.get(i)+" alla posizione "+(i+1)+"  non valido\n");
						}
					}
					pw.close();
					frs.close();
					frv.close();
				}
				catch(IOException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		JPanel mainPanel=new JPanel();
		mainPanel.add(verificaFile);
		return mainPanel;
	}

	public static void main (String args[])
	{
		MainFrame mf = new MainFrame();
	}
}
