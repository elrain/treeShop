import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.Format;
import java.util.Vector;
import java.text.Format;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Kassa2 
{
	Kassa3 Gyt = new Kassa3();
	JPanel Kassa2_Panel = new JPanel();
	JFrame Kassa2_Frame = new JFrame("Касса");
	
	JTextField Ed_SelectID;		JLabel Lab_SelectID;	JButton BTN_Add_To_Check;		JButton BTN_Cancel;
	JTextField Ed_SelectKol;	JLabel Lab_SelectKol;	JButton BTN_Del_Last;			JButton BTN_Deal;
	JTextField Ed_InKase;		JLabel Lab_ResI;
								JLabel Lab_Out;
	int nKol2 = 0;
								
	Vector ToList1 = new Vector();		Vector ToList2 = new Vector();		Vector ToList3 = new Vector();
	int nId, nKol, i=1;
	double dPrice,dRes,Res=0.0, dBound;
	String stName, stRes;
	String ListLine = new String();
	
	public static void main(String[] args)
	{
		Kassa2 e = new Kassa2();
	}
	public Kassa2()
	{
		Kassa2_Panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(140,179,213)));
		
		Kassa2_Panel.setLayout(null);
		Lab_SelectID = new JLabel("ID растения");					Kassa2_Panel.add(Lab_SelectID);
		Lab_SelectKol = new JLabel("Кол-во");						Kassa2_Panel.add(Lab_SelectKol);
		Lab_ResI = new JLabel("Сумма: 0.0 грн");				Kassa2_Panel.add(Lab_ResI);
		final JLabel Lab_InKase = new JLabel("Деньги");				Kassa2_Panel.add(Lab_InKase);
		Lab_Out = new JLabel("Сдача: 0.0 грн");				Kassa2_Panel.add(Lab_Out);
		
		Ed_SelectID = new JTextField();								Kassa2_Panel.add(Ed_SelectID);
		Ed_SelectKol = new JTextField();							Kassa2_Panel.add(Ed_SelectKol);
		Ed_InKase = new JTextField();								Kassa2_Panel.add(Ed_InKase);
		
		BTN_Add_To_Check = new JButton("Добавить в заказ");			Kassa2_Panel.add(BTN_Add_To_Check);
		BTN_Del_Last = new JButton("Удалить");						Kassa2_Panel.add(BTN_Del_Last);
		BTN_Cancel = new JButton("Отменить заказ");					Kassa2_Panel.add(BTN_Cancel);
		BTN_Deal = new JButton("Оформить заказ");					Kassa2_Panel.add(BTN_Deal);
		JButton BTN_Erase = new JButton("Отчистить форму заказа");	Kassa2_Panel.add(BTN_Erase);
		JButton BTN_Oplata = new JButton("Оплатить");					Kassa2_Panel.add(BTN_Oplata);
		int nBTNWid=240;
		
		Lab_SelectID.setBounds(10, 10, 100, 20);
		Lab_SelectKol.setBounds(130, 10, 160, 20);
		Lab_ResI.setFont(new Font("Times New Roman",Font.PLAIN,16));
		Lab_ResI.setForeground(Color.red);
		Lab_ResI.setBounds(200, 70, 500, 25);
		Lab_InKase.setBounds(10, 100, 150, 25);
		Lab_Out.setBounds(215, 100, 200, 25);
		
		Ed_SelectID.setBounds(80, 10, 40, 20);
		Ed_SelectKol.setBounds(180, 10, 40, 20);
		Ed_InKase.setBounds(55, 103, 150, 20);
		
		BTN_Add_To_Check.setBounds(230, 10, 140, 25);
		BTN_Del_Last.setBounds(20, 40, 110, 25);
		BTN_Deal.setBounds(380, 10, 140, 25);
		BTN_Cancel.setBounds(380, 40, 140, 25);
		BTN_Erase.setBounds(165, 40, 180, 25);
		BTN_Oplata.setBounds(55, 128, 140, 25);
		
		BTN_Add_To_Check.addMouseListener(new MouseAdapter() 
		{
			   @Override
			   public void mouseClicked(MouseEvent Erase) 
			   {
				   nId = Integer.parseInt(Ed_SelectID.getText());
				   nKol = Integer.parseInt(Ed_SelectKol.getText());
				   try
				   {
					   Accessor ac=Accessor.getInstance("BRONDOR\\SQLEXPRESS", "TANYA");
						if(ac!=null)
							System.out.println("Connection successful");
						ac.propertyStatement();
						int r = ac.getRef_Plants1(nId,nKol);
						if(r == 1)
						{
							JOptionPane.showMessageDialog(null, "Не хватает на складе");
							Erase(2);
							
						}
						else
						{
							stName = ac.getName(nId);
							dPrice = ac.getPrice(nId);
							ToList1.add(nId);		ToList3.add(nKol);		ToList2.add(dPrice);
							dRes = dPrice*nKol;
							Res+=dRes;
							Erase(2);
							ListLine = " [" +stName+ "]  [" +dPrice+ "] * [" +nKol+ "] = [" +dRes+ "]";
							stRes = "Сумма: "+Res+" грн.";
							Lab_ResI.setText(stRes);
							dBound-=Res;
							Gyt.ADD(ListLine);
							i++;	
							ac.PlantsDeal(nId, nKol, 1);
						}
				   }
				   catch (Exception e) 
				   {
					// TODO Auto-generated catch block
					   
					   JOptionPane.showMessageDialog(null, "Connect failed");
					   e.printStackTrace();
				   }
				   
				  
			   }
			   
		});
		
		BTN_Del_Last.addMouseListener(new MouseAdapter() 
		{
		   @Override
		   public void mouseClicked(MouseEvent exit) 
		   {
			  int index=Gyt.Erase_One();
			  try
			  {
				  Accessor ac=Accessor.getInstance("BRONDOR\\SQLEXPRESS", "TANYA");
					if(ac!=null)
						System.out.println("Connection successful");
					ac.propertyStatement();
				  ac.PlantsDeal((Integer)ToList1.get(index), (Integer)ToList3.get(index), 2);
			  }
			  catch (Exception e) 
			  {
				  JOptionPane.showMessageDialog(null, "Connect failed");
			  }
			  dRes =(Double) ToList2.get(index)*((Integer) ToList3.get(index));
			  Res -= dRes;
			  ToList1.removeElementAt(index);
			  ToList2.removeElementAt(index);
			  ToList3.removeElementAt(index);
			  ListLine = " [" +stName+ "]         [" +dPrice+ "]       *       [" +nKol+ "]        =       [" +dRes+ "]";
			  stRes = "Сумма: "+Res+" грн.";
			  Lab_ResI.setText(stRes);
			  
		  }
		});
		
		BTN_Oplata.addMouseListener(new MouseAdapter() 
		{
		   @Override
		   public void mouseClicked(MouseEvent exit) 
		   {
			 dBound = Double.parseDouble(Ed_InKase.getText());
			 dBound -= Res; 
			    stRes = "Сдача: " +dBound+ " грн.";
				   Lab_Out.setText(stRes);
		   }
		});
		
		BTN_Deal.addMouseListener(new MouseAdapter() 
		{
		   @Override
		   public void mouseClicked(MouseEvent Erase) 
		   {
				 /*  try
				   {
					   Accessor ac=Accessor.getInstance("BRONDOR\\SQLEXPRESS", "TANYA");
						if(ac!=null)
							System.out.println("Connection successful");
						ac.propertyStatement();						
						ToList1.add(nId);		ToList3.add(nKol);		ToList2.add(dPrice);
						for(int i=0; i<ToList1.size()-1; i++ )
						{
							ac.PlantsDeal((Integer)ToList1.get(i), (Integer)ToList3.get(i));
						}
				   }
				   catch (Exception e) 
				   {
					// TODO Auto-generated catch block
					   
					   JOptionPane.showMessageDialog(null, "Connect failed");
					   e.printStackTrace();
				   }
				   Gyt.Erase_All();*/
				   Erase(1);
				   Gyt.Erase_All();
		   }
		   
		});
		
		BTN_Erase.addMouseListener(new MouseAdapter() 
		{
		   @Override
		   public void mouseClicked(MouseEvent exit) 
		   {
			   Gyt.Erase_All();
			   Erase(1);
		   }
		});
		BTN_Cancel.addMouseListener(new MouseAdapter() 
		{
		   @Override
		   public void mouseClicked(MouseEvent exit) 
		   {
			   Gyt.Erase_All();
				Kassa2_Frame.setVisible(false);
				Gyt.Kassa3_Frame.setVisible(false);
		   }
		});
		
		Kassa2_Panel.setBackground(new Color(202,220,236));
		Kassa2_Frame.add(Kassa2_Panel);
		Kassa2_Frame.setLocation(nBTNWid+60, 50);
		//Kassa2_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Kassa2_Frame.setResizable(false);
		Kassa2_Frame.setSize(550,200);
		Kassa2_Frame.setVisible(false);
	}
	public void Erase(int i)
	{
		switch(i)
		{
			case 1:
			{
				Ed_SelectID.setText("");
				Ed_SelectKol.setText("");
				Lab_Out.setText("Сдача: 0.0 грн.");
				Lab_ResI.setText("Сумма: 0.0 грн.");
				Ed_InKase.setText("");
				ToList1.setSize(0);
				ToList2.setSize(0);
				ToList3.setSize(0);
				dRes=0.0;
				Res = 0.0;
				i=1;
			}break;
			case 2:
			{
				Ed_SelectID.setText("");
				Ed_SelectKol.setText("");
			}break;
		}
	}
}
