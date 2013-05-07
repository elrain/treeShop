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


public class Del_Sotrud 
{
		JLabel Lab_Pasport;		JTextField Ed_Pasport;
		JButton BTN_Del;
		JFrame Del_Sotrud_Frame = new JFrame("Удалить сотрудника");
		int nBTNWid = 240;
		String stName;
		
		public static void main(String[] args)
		{
			Del_Sotrud f = new Del_Sotrud();
		}
		
		public Del_Sotrud()
		{
			Lab_Pasport = new JLabel("Номер паспорта");
			Ed_Pasport = new JTextField();
			BTN_Del = new JButton("Удалить");
			JPanel Del_Sotrud_Panel = new JPanel();
			
			Del_Sotrud_Panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(140,179,213)));
			
			Del_Sotrud_Panel.setLayout(null);
			Lab_Pasport.setBounds(10, 10, 200, 25);
			Ed_Pasport.setBounds(120, 10, 65, 25);
			BTN_Del.setBounds(195, 10, 180, 25);
			
			Del_Sotrud_Panel.add(Lab_Pasport);
			Del_Sotrud_Panel.add(Ed_Pasport);
			Del_Sotrud_Panel.add(BTN_Del);
		
			BTN_Del.addMouseListener(new MouseAdapter() 
			{
				   @Override
				   public void mouseClicked(MouseEvent Add) 
				   {
					   stName = Ed_Pasport.getText();
					   try
					   {
						   Accessor_Sotr ac=Accessor_Sotr.getInstance("BRONDOR\\SQLEXPRESS", "TANYA");
							if(ac!=null)
								System.out.println("Connection successful");
							ac.propertyStatement();
							ac.DelClient(stName);
					   }
					   catch (Exception e) 
					   {
						// TODO Auto-generated catch block
						   
						   JOptionPane.showMessageDialog(null, "Сотрудник не найден");
						   e.printStackTrace();
					   }
					   
					 // Erase();
				   }
			});
			
			Del_Sotrud_Panel.setBackground(new Color(202,220,236));
			Del_Sotrud_Frame.add(Del_Sotrud_Panel);
			Del_Sotrud_Frame.setLocation(nBTNWid+60, 50);
			Del_Sotrud_Frame.setSize(392,72);
			Del_Sotrud_Frame.setResizable(false);
			Del_Sotrud_Frame.setVisible(false);
		}
		
}
