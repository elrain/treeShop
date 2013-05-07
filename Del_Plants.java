import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Del_Plants 
{
		JLabel Lab_Name;		JTextField Ed_Name;
		JButton BTN_Del;
		JFrame Del_Plants_Frame = new JFrame();
		int nBTNWid = 240;
		String stName;
		
		public static void main(String[] args)
		{
			Del_Plants f = new Del_Plants();
		}
		
		public Del_Plants()
		{
			Lab_Name = new JLabel("Название растения");
			Ed_Name = new JTextField();
			BTN_Del = new JButton("Удалить");
			JPanel Del_Plants_Panel = new JPanel();
			
			Del_Plants_Panel.setLayout(null);
			Lab_Name.setBounds(10, 10, 200, 25);
			Ed_Name.setBounds(130, 10, 120, 25);
			BTN_Del.setBounds(260, 10, 100, 25);
			
			Del_Plants_Panel.add(Lab_Name);
			Del_Plants_Panel.add(Ed_Name);
			Del_Plants_Panel.add(BTN_Del);
		
			BTN_Del.addMouseListener(new MouseAdapter() 
			{
				   @Override
				   public void mouseClicked(MouseEvent Add) 
				   {
					   stName = Ed_Name.getText();
					   try
					   {
						   Accessor ac=Accessor.getInstance("BRONDOR\\SQLEXPRESS", "TANYA");
							if(ac!=null)
								System.out.println("Connection successful");
							ac.propertyStatement();
							ac.DelPlant(stName);
					   }
					   catch (Exception e) 
					   {
						// TODO Auto-generated catch block
						   
						   JOptionPane.showMessageDialog(null, "Растение не найдено");
						   e.printStackTrace();
					   }
					   
					 // Erase();
				   }
			});
			
			Del_Plants_Panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(140,179,213)));
			Del_Plants_Panel.setBackground(new Color(202,220,236));
			Del_Plants_Frame.add(Del_Plants_Panel);
			Del_Plants_Frame.setLocation(nBTNWid+60, 50);
			Del_Plants_Frame.setSize(380,72);
			Del_Plants_Frame.setResizable(false);
			Del_Plants_Frame.setVisible(false);
		}
		
}
