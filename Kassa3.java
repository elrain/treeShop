import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Kassa3 
{
	JFrame Kassa3_Frame = new JFrame("Касса");
	
	final DefaultListModel listModel = new DefaultListModel();
	JList southList = new JList(listModel);
	
	public static void main(String[] args)
	{
		Kassa3 e = new Kassa3();
		//JScrollPane scrollableList = new JScrollPane(LCheck);
	}
	public Kassa3()
	{
		int nBTNWid=240;
		JPanel Kassa3_Panel = new JPanel();
		Kassa3_Panel.setLayout(new BoxLayout(Kassa3_Panel, BoxLayout.Y_AXIS));
			
		JPanel Kassa_Panel3 = new JPanel();
				
        southList.setLayoutOrientation(JList.VERTICAL);
        southList.setVisibleRowCount(0);
        
        JScrollPane southScroll = new JScrollPane(southList);
        Kassa3_Panel.add(southScroll);
       	
		Kassa3_Panel.setBackground(new Color(202,220,236));
		
		Kassa3_Frame.add(Kassa3_Panel);
		Kassa3_Frame.setLocation(nBTNWid+60, 260);
		Kassa3_Frame.setResizable(false);
		Kassa3_Frame.setSize(400,300);
		Kassa3_Frame.setVisible(false);
	}
	public void ADD(String stName)
	{
		Kassa3_Frame.setVisible(true);
		listModel.addElement(stName);
		int index = listModel.size()-1;
		southList.ensureIndexIsVisible(index);
	}
	
	public int Erase_One()
	{
		int index;
		index = southList.getSelectedIndex();
		listModel.remove(southList.getSelectedIndex());
		return index;
	}
	
	public void Erase_All()
	{
		
		listModel.clear();
	}
	
}
