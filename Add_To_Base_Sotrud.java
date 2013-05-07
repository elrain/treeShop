import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Add_To_Base_Sotrud 
{
	JFrame Add_To_Base_Sotrud_Frame = new JFrame("Добавление");
	
	JLabel Lab_FIO;			JTextField Ed_FIO;				JButton BTN_Add_To_Base;
	JLabel Lab_Oklad;		JTextField Ed_Oklad;			JButton BTN_Erase;
	JLabel Lab_Otdel;		JTextField Ed_Doljnost;			JButton BTN_Cancel;
	JLabel Lab_Vremya;		JTextField Ed_Pasport;
	JLabel Lab_Born_Date;	JTextField Ed_Born_Date;
	JLabel Lab_Doljnost;	JFormattedTextField Ed_Vremya;
	JLabel Lab_Pasport;		JComboBox Com_Otdel;
	JLabel Lab_Born_Date_H;
	int nSch=0;
	
	public static void main(String[] args)
	{
		Add_To_Base_Sotrud c = new Add_To_Base_Sotrud();
	}
	
	public Add_To_Base_Sotrud() 
	{
		int nBTNWid = 240;
		//String CB_Otdel;
		final Add_To_Base_Plants Frame_Plants = new Add_To_Base_Plants();
		
		// TODO Auto-generated constructor stub
		JPanel Add_To_Base_Sotrud_Panel = new JPanel();
		Add_To_Base_Sotrud_Panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(140,179,213)));
		
//*********************************************************************************************************	
		Lab_FIO = new JLabel("Ф.И.О.");
		Lab_Oklad = new JLabel("Оклад                    грн.");
		Lab_Otdel = new JLabel("Отдел");
		Lab_Doljnost = new JLabel("Должность");
		Lab_Vremya = new JLabel("Время работы        ч.");
		Lab_Pasport = new JLabel("Серия и № паспорта");
		Lab_Born_Date = new JLabel("Дата рождения");
		Lab_Born_Date_H = new JLabel("ДД.ММ.ГГГГ");
		Add_To_Base_Sotrud_Panel.add(Lab_FIO);
		Add_To_Base_Sotrud_Panel.add(Lab_Oklad);
		Add_To_Base_Sotrud_Panel.add(Lab_Otdel);
		Add_To_Base_Sotrud_Panel.add(Lab_Doljnost);
		Add_To_Base_Sotrud_Panel.add(Lab_Vremya);
		Add_To_Base_Sotrud_Panel.add(Lab_Born_Date);
		Add_To_Base_Sotrud_Panel.add(Lab_Pasport);
		Add_To_Base_Sotrud_Panel.add(Lab_Born_Date_H);
		
		Ed_FIO = new JTextField();
		Ed_Oklad = new JTextField();
		Ed_Doljnost = new JTextField();
		Ed_Vremya = new JFormattedTextField(NumberFormat.getIntegerInstance());
		Ed_Born_Date = new JTextField("ДД.ММ.ГГГГ");
		Ed_Pasport = new JTextField();
		Add_To_Base_Sotrud_Panel.add(Ed_FIO);
		Add_To_Base_Sotrud_Panel.add(Ed_Oklad);
		Add_To_Base_Sotrud_Panel.add(Ed_Doljnost);
		Add_To_Base_Sotrud_Panel.add(Ed_Vremya);
		Add_To_Base_Sotrud_Panel.add(Ed_Born_Date);
		Add_To_Base_Sotrud_Panel.add(Ed_Pasport);
		
		Com_Otdel = new JComboBox(Frame_Plants.CB_Kategoriya);
		Add_To_Base_Sotrud_Panel.add(Com_Otdel);
		
		BTN_Add_To_Base = new JButton("Добавить");
		BTN_Cancel = new JButton("Отменить");
		BTN_Erase = new JButton("Очистить");
		Add_To_Base_Sotrud_Panel.add(BTN_Add_To_Base);
		Add_To_Base_Sotrud_Panel.add(BTN_Cancel);
		Add_To_Base_Sotrud_Panel.add(BTN_Erase);
		
		Add_To_Base_Sotrud_Panel.setLayout(null);
		
		Lab_FIO.setBounds(10, 10, 60, 20);
		Lab_Otdel.setBounds(10, 40, 60, 20);
		Lab_Doljnost.setBounds(10, 70, 100, 20);
		Lab_Vremya.setBounds(10, 100, 200, 20);
		Lab_Oklad.setBounds(10, 130, 200, 20);
		Lab_Born_Date.setBounds(415, 10, 170, 20);
		Lab_Pasport.setBounds(330, 70, 120, 20);
		//Lab_Born_Date_H.setFont(new Font("Times New Roman",Font.PLAIN,10));
		//Lab_Born_Date_H.setBounds(520, 30, 120, 20);
				
		Ed_FIO.setBounds(60, 10, 300, 20);
		Ed_Doljnost.setBounds(80, 70, 200, 20);
		Ed_Vremya.setBounds(95, 100, 20, 20);
		Ed_Oklad.setBounds(55, 130, 50, 20);
		Ed_Born_Date.setBounds(508, 10, 75, 20);
		Ed_Pasport.setBounds(458, 68, 120, 20);
		
		Com_Otdel.setBounds(80, 40, 120, 20);
		
		BTN_Add_To_Base.setBounds(480, 100, 100, 25);
		BTN_Erase.setBounds(480, 130, 100, 25);
		BTN_Cancel.setBounds(480, 160, 100, 25);
//*********************************************************************************************************		
		Ed_Born_Date.addMouseListener(new MouseListener() 
		{
			@Override
			public void mouseReleased(MouseEvent arg0){/* TODO Auto-generated method stub*/}
			
			@Override
			public void mousePressed(MouseEvent arg0){/* TODO Auto-generated method stub*/}
			
			@Override
			public void mouseExited(MouseEvent arg0){/* TODO Auto-generated method stub*/}
			
			@Override
			public void mouseEntered(MouseEvent arg0){/* TODO Auto-generated method stub*/}
			
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				// TODO Auto-generated method stub
				if(nSch==0)
				{
					Ed_Born_Date.setText("");
				}
				
			}
		});
		
		BTN_Add_To_Base.addMouseListener(new MouseAdapter() 
		{
			   @Override
			   public void mouseClicked(MouseEvent Add) 
			   {
				  //Запись в базу
				   String stFIO, stDoljnost, stBorn_Date, stPasport, stOtdel;
				   int nVremya;
				   double dOklad;
				   
				   stFIO = Ed_FIO.getText();
				   stBorn_Date = Ed_Born_Date.getText();
				   stDoljnost = Ed_Doljnost.getText();
				   stPasport = Ed_Pasport.getText();
				   stOtdel = (String)Com_Otdel.getSelectedItem();
				   
				   nVremya = Integer.parseInt(Ed_Vremya.getText());
				   dOklad = Double.parseDouble(Ed_Oklad.getText());
				   
				   try
				   {
					   Accessor_Sotr ac=Accessor_Sotr.getInstance("BRONDOR\\SQLEXPRESS", "TANYA");
						if(ac!=null)
							System.out.println("Connection successful");
						ac.propertyStatement();
						ac.saveClient(stFIO, stBorn_Date, stOtdel, stPasport, stDoljnost, nVremya, dOklad);
						JOptionPane.showMessageDialog(null, "Сотрудник добавлен");
						Erase();
				   }
				   catch (Exception e) 
				   {
					// TODO Auto-generated catch block
					   
					   JOptionPane.showMessageDialog(null, "Сотрудник не добавлен");
					   e.printStackTrace();
				   }
			   }
		});
		
		BTN_Erase.addMouseListener(new MouseAdapter() 
		{
			   @Override
			   public void mouseClicked(MouseEvent Add) 
			   {
				  Erase();
			   }
		});
		
		BTN_Cancel.addMouseListener(new MouseAdapter() 
		{
			   @Override
			   public void mouseClicked(MouseEvent Add) 
			   {
				   Add_To_Base_Sotrud_Frame.setVisible(false);
				   Erase();
			   }
		});
//*********************************************************************************************************			
		Add_To_Base_Sotrud_Panel.setBackground(new Color(202,220,236));
		Add_To_Base_Sotrud_Frame.add(Add_To_Base_Sotrud_Panel);
		Add_To_Base_Sotrud_Frame.setLocation(nBTNWid+60, 50);
		Add_To_Base_Sotrud_Frame.setSize(605,230);
		Add_To_Base_Sotrud_Frame.setResizable(false);
		Add_To_Base_Sotrud_Frame.setVisible(false);
	}
	
	public void Erase()
	{
		Ed_FIO.setText("");
		Ed_Oklad.setText("");
		Ed_Doljnost.setText("");
		Ed_Vremya.setText("");
		Ed_Pasport.setText("");
		Ed_Born_Date.setText("ДД.ММ.ГГГГ");
		
		nSch=0;
		
		Com_Otdel.setSelectedIndex(0);
		
	}
}
