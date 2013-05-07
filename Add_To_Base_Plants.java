import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.print.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D.Double;
import java.io.Console;
import java.io.ObjectInputStream.GetField;
import java.sql.BatchUpdateException;
import java.text.NumberFormat;
import java.util.Locale;
import java.applet.*;
import java.lang.*;

public class Add_To_Base_Plants 
{
	JComboBox ComBox_Otdel;				JLabel Lab_Otdel;			JTextField Ed_Price;
	JComboBox ComBox_Vid;				JLabel Lab_Vid;				JTextField Ed_Name;
	JComboBox ComBox_Tip;				JLabel Lab_Tip;				JFormattedTextField Ed_Buy;
	JComboBox ComBox_PodTip;			JLabel Lab_PodTip;			JFormattedTextField Ed_Sell;
	JComboBox ComBox_Kategoriya;		JLabel Lab_Kategotiya;		JFormattedTextField Ed_Kol;
	JComboBox ComBox_KlimatUsl;			JLabel Lab_KlimatUsl;		JFormattedTextField Ed_Kol_Vazonov;
										JLabel Lab_Price;			//JFormattedTextField Ed_Kol_v_Vazone;
										JLabel Lab_Name;			JTextField Ed_Visota_Dereva;
	JButton BTN_Add_To_Base;			JLabel Lab_Buy;				JTextField Ed_Prodol_Jizni;
	JButton BTN_Erase;					JLabel Lab_Sell;			JTextField Ed_Vozrast;
	JButton BTN_Cancel;					JLabel Lab_Help;
										JLabel Lab_Kol;
										JLabel Lab_Kol_Vazonov;
										//JLabel Lab_Kol_v_Vazone;
										JLabel Lab_Visota_Dereva;
										JLabel Lab_Prodol_Jizni;
										JLabel Lab_Vozrast;
	
	JFrame Add_To_Base_Plants_Frame = new JFrame("Добавление");
	String CB_Kategoriya[] = {"(Не выбрано)","Многолетние","Однолетние"}; 
	String CB_Vid[] = {"(Не выбрано)","Деревья","Кустарники","Цветы"};
	String CB_Otdel[] = {"(Не выбрано)","Комнатные","Уличные"};
	String CB_Tip[] = {"(Не выбрано)","Хвойные","Лиственные"};
	String CB_PodTip[] = {"(Не выбрано)","Плодоносные","Декоративные"};
	String CB_KlimatUsl[] = {"(Не выбрано)","Тропический","Субтропический","Умеренный","Северный"};
	String stProv, stOtdel, stVid, stTip, stPodTip, stKlimatUsl, stKategoriya, stName;
	double dPrice, dVisota_Dereva, dProdol_Jizni, dVozrast;
	int nBuy, nSell, nKol, nKol_Vazonov, nKol_v_Vazone;
	
	
	public static void main(String[] args)
	{
		Add_To_Base_Plants b = new Add_To_Base_Plants();
	}
	public Add_To_Base_Plants()
	{
		JPanel Add_To_Base_Plants_Panel = new JPanel();
		
		Color colFrame = new Color(202,220,236);
		Color colPanelBord = new Color(140,179,213);
				
		Lab_Name = new JLabel("Название");
		Lab_Price = new JLabel("Цена экземпляра               грн.");
		Lab_Buy = new JLabel("Покупка                  шт.");
		Lab_Sell = new JLabel("Продажа                шт.");
		Lab_Otdel = new JLabel("Отдел");  
		Lab_Vid = new JLabel("Вид");
		Lab_Tip = new JLabel("Тип");
		Lab_PodTip = new JLabel("Подтип");
		Lab_Kategotiya = new JLabel("Категория");
		Lab_KlimatUsl = new JLabel("Клим. условия");
		Lab_Kol = new JLabel("Количество                 шт.");
		Lab_Prodol_Jizni = new JLabel("Прод-ость жизни                 лет");
		//Lab_Kol_v_Vazone = new JLabel("Кол-во в вазоне                   шт.");
		Lab_Kol_Vazonov = new JLabel("Кол-во вазонов                     шт.");
		Lab_Visota_Dereva = new JLabel("Высота дерева                м");
		Lab_Vozrast = new JLabel("Возраст");
		
		Ed_Name = new JTextField();
		Ed_Kol = new JFormattedTextField(/*NumberFormat.getIntegerInstance()*/);
		Ed_Price = new JTextField();
		Ed_Buy = new JFormattedTextField(/*NumberFormat.getIntegerInstance()*/);
		Ed_Sell = new JFormattedTextField(NumberFormat.getIntegerInstance());
		Ed_Prodol_Jizni = new JTextField();
		//Ed_Kol_v_Vazone = new JFormattedTextField(NumberFormat.getIntegerInstance());
		Ed_Kol_Vazonov = new JFormattedTextField(NumberFormat.getIntegerInstance());
		Ed_Visota_Dereva = new JTextField();
		Ed_Vozrast = new JTextField();
		
		BTN_Add_To_Base = new JButton("Добавить");
		BTN_Cancel = new JButton("Отменить");
		BTN_Erase = new JButton("Очистить");
		
		ComBox_Kategoriya = new JComboBox(CB_Kategoriya);
		ComBox_KlimatUsl = new JComboBox(CB_KlimatUsl);
		ComBox_Otdel = new JComboBox(CB_Otdel);
		ComBox_PodTip = new JComboBox(CB_PodTip);
		ComBox_Tip = new JComboBox(CB_Tip);
		ComBox_Vid = new JComboBox(CB_Vid);
		
		Add_To_Base_Plants_Panel.add(Lab_Name);
		Add_To_Base_Plants_Panel.add(Lab_Price);
		//Add_To_Base_Plants_Panel.add(Lab_Buy);
		//Add_To_Base_Plants_Panel.add(Lab_Sell);
		Add_To_Base_Plants_Panel.add(Lab_Otdel);
		Add_To_Base_Plants_Panel.add(Lab_Vid);
		Add_To_Base_Plants_Panel.add(Lab_Tip);
		Add_To_Base_Plants_Panel.add(Lab_PodTip);
		Add_To_Base_Plants_Panel.add(Lab_Kategotiya);
		Add_To_Base_Plants_Panel.add(Lab_KlimatUsl);
		Add_To_Base_Plants_Panel.add(Lab_Kol);
		Add_To_Base_Plants_Panel.add(Lab_Prodol_Jizni);
		//Add_To_Base_Plants_Panel.add(Lab_Kol_v_Vazone);
		Add_To_Base_Plants_Panel.add(Lab_Kol_Vazonov);
		Add_To_Base_Plants_Panel.add(Lab_Visota_Dereva);
		Add_To_Base_Plants_Panel.add(Lab_Vozrast);
		
		Add_To_Base_Plants_Panel.add(Ed_Name);
		Add_To_Base_Plants_Panel.add(Ed_Price);
		//Add_To_Base_Plants_Panel.add(Ed_Buy);
		//Add_To_Base_Plants_Panel.add(Ed_Sell);
		Add_To_Base_Plants_Panel.add(Ed_Kol);
		Add_To_Base_Plants_Panel.add(Ed_Prodol_Jizni);
		//Add_To_Base_Plants_Panel.add(Ed_Kol_v_Vazone);
		Add_To_Base_Plants_Panel.add(Ed_Kol_Vazonov);
		Add_To_Base_Plants_Panel.add(Ed_Visota_Dereva);
		Add_To_Base_Plants_Panel.add(Ed_Vozrast);
		
		Add_To_Base_Plants_Panel.add(BTN_Add_To_Base);
		Add_To_Base_Plants_Panel.add(BTN_Cancel);
		Add_To_Base_Plants_Panel.add(BTN_Erase);
		
		Add_To_Base_Plants_Panel.add(ComBox_Kategoriya);
		Add_To_Base_Plants_Panel.add(ComBox_KlimatUsl);
		Add_To_Base_Plants_Panel.add(ComBox_Otdel);
		Add_To_Base_Plants_Panel.add(ComBox_PodTip);
		Add_To_Base_Plants_Panel.add(ComBox_Tip);
		Add_To_Base_Plants_Panel.add(ComBox_Vid);
		
		Add_To_Base_Plants_Panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, colPanelBord));
		
		Add_To_Base_Plants_Panel.setLayout(null);
		int nBTNWid=240;
		
		Lab_Name.setBounds(10, 10, 60, 20);
		Lab_Kol.setBounds(265, 10, 200, 20);
		Lab_Price.setBounds(450, 10, 220, 20);
		Lab_Buy.setBounds(650, 10, 200, 20);
		Lab_Sell.setBounds(650, 40, 200, 20);
		Lab_Otdel.setBounds(10, 40, 60, 20);
		Lab_Kategotiya.setBounds(10, 70, 60, 20);
		Lab_Vid.setBounds(10, 100, 40, 20);
		Lab_Tip.setBounds(10, 130, 40, 20);
		Lab_PodTip.setBounds(260, 130, 60, 20);
		Lab_KlimatUsl.setBounds(10, 160, 120, 20);
			
		Ed_Name.setBounds(70, 10, 180, 20);
		Ed_Kol.setBounds(340, 10, 40, 20);
		Ed_Price.setBounds(555, 10, 40, 20);
		Ed_Buy.setBounds(705, 10, 40, 20);
		Ed_Sell.setBounds(705, 40, 40, 20);
		
		ComBox_Otdel.setBounds(100, 40, 120, 20);
		ComBox_Kategoriya.setBounds(100, 70, 120, 20);
		ComBox_Vid.setBounds(100, 100, 120, 20);
		ComBox_Tip.setBounds(100, 130, 120, 20);
		ComBox_PodTip.setBounds(310, 130, 120, 20);
		ComBox_KlimatUsl.setBounds(100, 160, 120, 20);
		
		BTN_Add_To_Base.setBounds(670, 10, 100, 25);
		BTN_Erase.setBounds(670, 80, 100, 25);
		BTN_Cancel.setBounds(670, 155, 100, 25);
		
		BTN_Add_To_Base.addMouseListener(new MouseAdapter() 
		{
			   @Override
			   public void mouseClicked(MouseEvent Add) 
			   {
				  //Запись в базу
				   if(ComBox_Kategoriya.getSelectedIndex() == 1)
				   {
					   nKol_v_Vazone=0;
					   Ed_Kol_Vazonov.setText("0");
				   }
				   if(ComBox_Kategoriya.getSelectedIndex() == 2)
				   {
					   Ed_Visota_Dereva.setText("0");
					   Ed_Prodol_Jizni.setText("0");
					   Ed_Vozrast.setText("0");
				   }
				   if(ComBox_Vid.getSelectedIndex()==3)
				   {
					   stTip = "null";
					stPodTip = "null";
				   }
				   else
				   {   
					   stTip = (String)ComBox_Tip.getSelectedItem();
					   stPodTip = (String)ComBox_PodTip.getSelectedItem();
				   }
				   stVid = (String)ComBox_Vid.getSelectedItem();
				   stOtdel = (String)ComBox_Otdel.getSelectedItem();
				   stKlimatUsl = (String)ComBox_KlimatUsl.getSelectedItem();
				   stKategoriya = (String)ComBox_Kategoriya.getSelectedItem();
				   
				   stName = (String)Ed_Name.getText();
				   
				   dPrice = java.lang.Double.parseDouble(Ed_Price.getText());
				   dProdol_Jizni = java.lang.Double.parseDouble(Ed_Prodol_Jizni.getText());
				   dVisota_Dereva = java.lang.Double.parseDouble(Ed_Visota_Dereva.getText());
				   dVozrast = java.lang.Double.parseDouble(Ed_Vozrast.getText());
				   
				   nBuy = 0;
				   nSell = 0;
				   nKol = Integer.parseInt(Ed_Kol.getText());
				   nKol_Vazonov = Integer.parseInt(Ed_Kol_Vazonov.getText());
				   //nKol_v_Vazone = nKol/nKol_Vazonov;
				   
				   try
				   {
					   Accessor ac=Accessor.getInstance("BRONDOR\\SQLEXPRESS", "TANYA");
						if(ac!=null)
							System.out.println("Connection successful");
						ac.propertyStatement();
						ac.saveClient(stName, dPrice, nSell, stOtdel, stVid, stTip, stPodTip, stKlimatUsl, dVisota_Dereva, dProdol_Jizni, dVozrast, nKol_Vazonov, nKol, nKol_v_Vazone, stKategoriya);
						
						JOptionPane.showMessageDialog(null, "Растение добавлено в базу");					 
						Erase();
				   }
				   catch (Exception e) 
				   {
					// TODO Auto-generated catch block
					   
					   JOptionPane.showMessageDialog(null, "Растение не добавлено");
					   e.printStackTrace();
				   }
			   }
		});
		
		BTN_Erase.addMouseListener(new MouseAdapter() 
		{
			   @Override
			   public void mouseClicked(MouseEvent Erase) 
			   {
				   	Erase();
			   }
		});
		
		BTN_Cancel.addMouseListener(new MouseAdapter() 
		{
			   @Override
			   public void mouseClicked(MouseEvent Cancel) 
			   {
				   Add_To_Base_Plants_Frame.setVisible(false);
				   Erase();
			   }
		});
		
		ComBox_Vid.addItemListener(new ItemListener() 
		{
			@Override
			public void itemStateChanged(ItemEvent Vid) 
			{
				// TODO Auto-generated method stub
				if(ComBox_Vid.getSelectedIndex()==1)
				{
					ComBox_Tip.setEnabled(true);
					ComBox_PodTip.setEnabled(true);
				}
				if(ComBox_Vid.getSelectedIndex()==2)
				{
					ComBox_Tip.setEnabled(true);
					ComBox_PodTip.setEnabled(true);
				}
				if(ComBox_Vid.getSelectedIndex()==3)
				{
					ComBox_Tip.setEnabled(false);
					ComBox_PodTip.setEnabled(false);
				}
			}
		});
		ComBox_Kategoriya.addItemListener(new ItemListener() 
		{
			@Override
			public void itemStateChanged(ItemEvent Kateg) 
			{
				// TODO Auto-generated method stub
				stProv = (String)ComBox_Kategoriya.getSelectedItem();
				if ( stProv == "Многолетние" )
				{
					//Lab_Kol_v_Vazone.setVisible(false);
					//Ed_Kol_v_Vazone.setVisible(false);
					Lab_Kol_Vazonov.setVisible(false);
					Ed_Kol_Vazonov.setVisible(false);
					
					Lab_Visota_Dereva.setBounds(460, 50, 200, 20);
					Ed_Visota_Dereva.setBounds(555, 50, 40, 20);
					Lab_Prodol_Jizni.setBounds(460, 80, 300, 20);
					Ed_Prodol_Jizni.setBounds(565, 80, 40, 20);
					Lab_Vozrast.setBounds(460, 110, 300, 20);
					Ed_Vozrast.setBounds(515, 110, 40, 20);
					
					Lab_Visota_Dereva.setVisible(true);
					Ed_Visota_Dereva.setVisible(true);
					Lab_Prodol_Jizni.setVisible(true);
					Ed_Prodol_Jizni.setVisible(true);
					Lab_Vozrast.setVisible(true);
					Ed_Vozrast.setVisible(true);
				}
				if(stProv=="Однолетние")
				{
					Lab_Visota_Dereva.setVisible(false);
					Ed_Visota_Dereva.setVisible(false);
					Lab_Prodol_Jizni.setVisible(false);
					Ed_Prodol_Jizni.setVisible(false);
					Lab_Vozrast.setVisible(false);
					Ed_Vozrast.setVisible(false);
					
					Lab_Kol_Vazonov.setBounds(460, 50, 200, 20);
					Ed_Kol_Vazonov.setBounds(565, 50, 40, 20);
					//Lab_Kol_v_Vazone.setBounds(460, 80, 200, 20);
					//Ed_Kol_v_Vazone.setBounds(565, 80, 40, 20);
					
					//Lab_Kol_v_Vazone.setVisible(true);
					//Ed_Kol_v_Vazone.setVisible(true);
					Lab_Kol_Vazonov.setVisible(true);
					Ed_Kol_Vazonov.setVisible(true);
				}
				if(stProv=="(Не выбрано)")
				{
					//Lab_Kol_v_Vazone.setVisible(false);
					//Ed_Kol_v_Vazone.setVisible(false);
					Lab_Kol_Vazonov.setVisible(false);
					Ed_Kol_Vazonov.setVisible(false);
					Lab_Visota_Dereva.setVisible(false);
					Ed_Visota_Dereva.setVisible(false);
					Lab_Prodol_Jizni.setVisible(false);
					Ed_Prodol_Jizni.setVisible(false);
					Lab_Vozrast.setVisible(false);
					Ed_Vozrast.setVisible(false);
				}
			}
		});
		
		ComBox_Tip.addItemListener(new ItemListener() 
		{
			@Override
			public void itemStateChanged(ItemEvent Tip) 
			{
				//"Лиственные"
				if (ComBox_Tip.getSelectedIndex() == 1)
				{
					ComBox_PodTip.setSelectedIndex(2);
					ComBox_PodTip.setEnabled(false);
				}
				if (ComBox_Tip.getSelectedIndex() == 2)
				{
					ComBox_PodTip.setSelectedIndex(0);
					ComBox_PodTip.setEnabled(true);
					
				}
				if (ComBox_Tip.getSelectedIndex() == 0)
				{
					ComBox_PodTip.setSelectedIndex(0);
					ComBox_PodTip.setEnabled(true);
				}
			}
		});
		
		Add_To_Base_Plants_Panel.setBackground(colFrame);
		Add_To_Base_Plants_Frame.add(Add_To_Base_Plants_Panel);
		Add_To_Base_Plants_Frame.setLocation(nBTNWid+60, 50);
		Add_To_Base_Plants_Frame.setSize(800,230);
		Add_To_Base_Plants_Frame.setResizable(false);
		Add_To_Base_Plants_Frame.setVisible(false);
	}
	
	public void Erase()
	{
		//Lab_Kol_v_Vazone.setVisible(false);
		//Ed_Kol_v_Vazone.setVisible(false);
		Lab_Kol_Vazonov.setVisible(false);
		Ed_Kol_Vazonov.setVisible(false);
		Lab_Visota_Dereva.setVisible(false);
		Ed_Visota_Dereva.setVisible(false);
		Lab_Prodol_Jizni.setVisible(false);
		Ed_Prodol_Jizni.setVisible(false);
		Lab_Vozrast.setVisible(false);
		Ed_Vozrast.setVisible(false);
		
		ComBox_Kategoriya.setSelectedIndex(0);
		ComBox_Vid.setSelectedIndex(0);
		ComBox_KlimatUsl.setSelectedIndex(0);
		ComBox_Otdel.setSelectedIndex(0);
		ComBox_PodTip.setSelectedIndex(0);
		ComBox_Tip.setEnabled(true);
		ComBox_PodTip.setEnabled(true);
		ComBox_Tip.setSelectedIndex(0);
		
		Ed_Buy.setText("");
		Ed_Kol.setText("");
		//Ed_Kol_v_Vazone.setText("");
		Ed_Kol_Vazonov.setText("");
		Ed_Name.setText("");
		Ed_Price.setText("");
		Ed_Prodol_Jizni.setText("");
		Ed_Sell.setText("");
		Ed_Visota_Dereva.setText("");
		Ed_Vozrast.setText("");
	}
	
}
