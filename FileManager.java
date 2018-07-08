import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class FileManager extends JFrame implements ActionListener{

	private JFileChooser JFC1;
	private JPanel Panel1;
	private JButton Button1;

	
	public FileManager () {
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		setTitle ("Jemma's Test GUI");
		setSize (700, 300);
		setLocation (500,800);
		setResizable (false);
		ManagerComponents ();
	}

	
	public void ManagerComponents () {

		Panel1 = new JPanel ();
		Panel1.setBackground (Color.lightGray);
		add (Panel1);

		
		JFC1 = new JFileChooser ();
		JFC1.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		Panel1.add(JFC1);
		JFC1.setEnabled(true);

		
		Button1 = new JButton ("Button1");
		Button1.addActionListener (this);
		Panel1.add (Button1);
		Button1.setEnabled (true);
	}


	@Override
	public void actionPerformed (ActionEvent e) {
		
		if (e.getSource () == Button1) {
			int returnVal = JFC1.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = JFC1.getSelectedFile();
				System.out.println("Open File: " + file.getName());
			}
		}
	}
}
