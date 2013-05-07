

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.JLabel;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.awt.*;
import java.awt.print.*;
import java.awt.event.*;
import java.sql.BatchUpdateException;
import java.applet.*;

public class Main_vibor 
{
	JButton BTN_Baza_cvetov;
	JButton BTN_Baza_sotrudnikov;
	JButton BTN_Kasa;
	JButton BTN_Exit;
	JButton BTN_Baza_del_cvet;
	JButton BTN_Baza_del_sotr;
	JFrame Main_Frame = new JFrame("Солнечный Рай");
	
	final Add_To_Base_Plants Frame_add = new Add_To_Base_Plants();
	final Add_To_Base_Sotrud Frame_add2 = new Add_To_Base_Sotrud();
	final Kassa3 Frame_add3 = new Kassa3();
	final Kassa2 Frame_add4 = new Kassa2();
	final Del_Sotrud Frame_add5 = new Del_Sotrud();
	final Del_Plants Frame_add6 = new Del_Plants();
	
	public static void main(String[] args)
	{
		Main_vibor a = new Main_vibor();
	}
	public Main_vibor()
	{
		// TODO Auto-generated constructor stub
		
		JPanel Main_Panel = new JPanel();
		
		
		Color colFrame = new Color(202,220,236);
		Color colPanelBord = new Color(140,179,213);
				
		BTN_Baza_cvetov = new JButton("Закупки");
		BTN_Baza_sotrudnikov = new JButton("Принять на работу");
		BTN_Baza_del_cvet = new JButton("Удалить данные");
		BTN_Baza_del_sotr = new JButton("Уволить");
		BTN_Kasa = new JButton("Касса");
		BTN_Exit = new JButton("Выход");
		
		Main_Panel.add(BTN_Baza_cvetov);
		Main_Panel.add(BTN_Baza_del_cvet);
		Main_Panel.add(BTN_Baza_del_sotr);
		Main_Panel.add(BTN_Baza_sotrudnikov);
		Main_Panel.add(BTN_Kasa);
		Main_Panel.add(BTN_Exit);
		
		Main_Panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, colPanelBord));
				
		Main_Panel.setLayout(null);
		int nBTNWid = 240, nBTNHei = 40;
		BTN_Baza_cvetov.setBounds(10, 10, nBTNWid, nBTNHei);		
		BTN_Baza_del_cvet.setBounds(10, 20+nBTNHei, nBTNWid, nBTNHei);
		BTN_Baza_sotrudnikov.setBounds(10, 30+nBTNHei*2, nBTNWid, nBTNHei);
		BTN_Baza_del_sotr.setBounds(10, 40+nBTNHei*3, nBTNWid, nBTNHei);
		BTN_Kasa.setBounds(10, 50+nBTNHei*4, nBTNWid, nBTNHei);
		BTN_Exit.setBounds(10, 60+nBTNHei*5, nBTNWid, nBTNHei);
				
		BTN_Baza_cvetov.addMouseListener(new MouseAdapter() 
		{
		   @Override
		   public void mouseClicked(MouseEvent Base_Ras) 
		   {
			   Okno(1);
		   }
		});
		
		BTN_Baza_sotrudnikov.addMouseListener(new MouseAdapter() 
		{
		   @Override
		   public void mouseClicked(MouseEvent Base_Sot) 
		   {
			   Okno(2);
		   }
		});
		
		BTN_Kasa.addMouseListener(new MouseAdapter() 
		{
		   @Override
		   public void mouseClicked(MouseEvent Kasa) 
		   {
			   Okno(3);
		   }
		});
		
		BTN_Baza_del_sotr.addMouseListener(new MouseAdapter() 
		{
		   @Override
		   public void mouseClicked(MouseEvent Base_Sot_Del) 
		   {
			   Okno(4);
		   }
		});
		
		BTN_Baza_del_cvet.addMouseListener(new MouseAdapter() 
		{
		   @Override
		   public void mouseClicked(MouseEvent Base_Sot_Del) 
		   {
			   Okno(5);
		   }
		});
		
		BTN_Exit.addMouseListener(new MouseAdapter() 
		{
		   @Override
		   public void mouseClicked(MouseEvent exit) 
		   {
			   System.exit(0);
		   }
		});
		
		Main_Panel.setBackground(colFrame);
		Main_Frame.add(Main_Panel);
		Main_Frame.setBackground(colFrame);
		Main_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Main_Frame.setLocation(20,50);
		Main_Frame.setSize(nBTNWid+35,nBTNHei*8+20);
		Main_Frame.setResizable(false);
		Main_Frame.setVisible(true);
		
	}
	public static JLabel createEmptyLabel() {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(100, 30));
        return label;
   }
	
   public void Okno(int i)
   {
	   switch(i)
	   {
	   		case 1:
	   		{
	   			Frame_add.Add_To_Base_Plants_Frame.setVisible(true);
				Frame_add2.Add_To_Base_Sotrud_Frame.setVisible(false);
				Frame_add4.Kassa2_Frame.setVisible(false);
				Frame_add5.Del_Sotrud_Frame.setVisible(false);
				Frame_add6.Del_Plants_Frame.setVisible(false);
	   		}break;
	   		case 2:
	   		{
	   			Frame_add.Add_To_Base_Plants_Frame.setVisible(false);
				Frame_add2.Add_To_Base_Sotrud_Frame.setVisible(true);
				Frame_add4.Kassa2_Frame.setVisible(false);
				Frame_add5.Del_Sotrud_Frame.setVisible(false);
				Frame_add6.Del_Plants_Frame.setVisible(false);
	   		}break;
	   		case 3:
	   		{
	   			Frame_add.Add_To_Base_Plants_Frame.setVisible(false);
				Frame_add2.Add_To_Base_Sotrud_Frame.setVisible(false);
				Frame_add4.Kassa2_Frame.setVisible(true);
				Frame_add5.Del_Sotrud_Frame.setVisible(false);
				Frame_add6.Del_Plants_Frame.setVisible(false);
	   		}break;
	   		case 4:
	   		{
	   			Frame_add.Add_To_Base_Plants_Frame.setVisible(false);
				Frame_add2.Add_To_Base_Sotrud_Frame.setVisible(false);
				Frame_add4.Kassa2_Frame.setVisible(false);
				Frame_add5.Del_Sotrud_Frame.setVisible(true);
				Frame_add6.Del_Plants_Frame.setVisible(false);
	   		}break;
	   		case 5:
	   		{
	   			Frame_add.Add_To_Base_Plants_Frame.setVisible(false);
				Frame_add2.Add_To_Base_Sotrud_Frame.setVisible(false);
				Frame_add4.Kassa2_Frame.setVisible(false);
				Frame_add5.Del_Sotrud_Frame.setVisible(false);
				Frame_add6.Del_Plants_Frame.setVisible(true);
	   		}break;
	   		
	   }
   }
}

