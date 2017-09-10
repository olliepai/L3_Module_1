package intro_to_file_io;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Assignment4 implements ActionListener {
	ArrayList<String> toDoList = new ArrayList<String>();

	public static void main(String[] args) {
		Assignment4 toDo = new Assignment4();
		toDo.UI();
	}

	JButton addTask;
	JButton removeTask;
	JButton save;
	JButton load;

	public void UI() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/intro_to_file_io/test2.txt"));

			String line = br.readLine();
			String list = "";
			while (line != null) {			
				list += line + "\n";
				toDoList.add(line);
				line = br.readLine();
			}
			
			JOptionPane.showMessageDialog(null, list);
			
			

			br.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JFrame frame = new JFrame();
		frame.setVisible(true);
		JPanel panel = new JPanel();
		panel.setVisible(true);
		frame.add(panel);
		addTask = new JButton();
		addTask.setText("Add Task");
		removeTask = new JButton();
		removeTask.setText("Remove Task");
		save = new JButton();
		save.setText("Save");
		load = new JButton();
		load.setText("Load");
		panel.add(addTask);
		panel.add(removeTask);
		panel.add(save);
		panel.add(load);
		addTask.addActionListener(this);
		removeTask.addActionListener(this);
		save.addActionListener(this);
		load.addActionListener(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == addTask) {
			String task = JOptionPane.showInputDialog("Enter a task.");
			toDoList.add(task);
		}
		if (e.getSource() == removeTask) {
			String task = JOptionPane.showInputDialog("Enter a task that you would like to remove.");
			for (int i = 0; i < toDoList.size(); i++) {
				if (toDoList.get(i).equals(task)) {
					toDoList.remove(i);
				}
			}
		}
		if (e.getSource() == save) {
			String list = "";
			for (String element : toDoList) {
				list += element + "\n";
			}
			try {
				FileWriter fw = new FileWriter("src/intro_to_file_io/test2.txt", true);
				fw.write(list);

				fw.close();
			} catch (IOException x) {
				x.printStackTrace();
			}
		}
		if (e.getSource() == load) {
			String list = "";
			for (String element : toDoList) {
				list += element + "\n";
			}
			JOptionPane.showMessageDialog(null, list);
		}
	}
}
