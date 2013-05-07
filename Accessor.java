import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Accessor 
{
	private static Accessor singletonAccessor;
	private Connection con;
	Statement stat;
	// скрытый конструктор принимает драйвер и адрес БД
	// выбрасывает исключение Exception
	private Accessor(String server, String urlDatabase) throws Exception 
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		// формирование строки подключения
		String connectionString = "jdbc:odbc:Driver={SQL Server};Server="+server+";Database="+urlDatabase+";";
		
		// подключение к БД
		con = DriverManager.getConnection(connectionString);
		stat = con.createStatement();
	}

	// открытый метод получения единственного экземпляра аксесора
	// выбрасывает исключение Exception
	public static Accessor getInstance(String _driver, String _urlDatabase)
	throws Exception 
	{
		if (singletonAccessor == null)
		singletonAccessor = new Accessor(_driver, _urlDatabase);
		return singletonAccessor;
	}
	
	//закрывает соединение с БД
	public  void closeConnection()
	throws SQLException 
	{
		if (con!= null)
		{
			stat.close();
			con.close();
		}
	}
	void propertyStatement()throws SQLException 
	{
		//проверка, реализует ли драйвер JDBC тот или иной тип выборки
		//TYPE_FORWARD_ONLY - курсор выборки перемещается только вперед
		//TYPE_SCROLL_INSENSITIVE - курсор перемещается в обеих направлениях, выборка не изменяется
		//TYPE_SCROLL_SENSITIVE - курсор перемещается в обеих направлениях, выборка изменяется при изменении строк в БД
		
		boolean ro=con.getMetaData().supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY);
		System.out.println("TYPE_FORWARD_ONLY - "+ro);
		
		ro=con.getMetaData().supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE);
		System.out.println("TYPE_SCROLL_INSENSITIVE - "+ro);
			
		ro=con.getMetaData().supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE);
		System.out.println("TYPE_SCROLL_SENSITIVE - "+ro);
		
		//проверка, поддерживает ли драйвер JDBC тот или иной режим изменения выборки
		//CONCUR_READ_ONLY - выборку нельзя изменять
		//CONCUR_UPDATABLE - выборку можно изменять
		ro=con.getMetaData().supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		System.out.println("CONCUR_READ_ONLY - "+ro);
		
		ro=con.getMetaData().supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		System.out.println("CONCUR_UPDATABLE - "+ro);
	}
	
//*********************************************** Добавление растения **************************************************************	
	public Vector getIdCopys(int id_plants) throws SQLException
	{
		Vector arr1 = new Vector();	
		ResultSet rs = stat.executeQuery("Select id_copy from copys, plant where ref_copy = '"+id_plants+"'");
		while (rs.next())
		{			
			arr1.add(rs.getInt("id_copy"));
		}			
		rs.close();
		return arr1;
	}
	public Vector getRefPlants(String Name) throws SQLException
	{
		Vector arr2 = new Vector();	
		ResultSet rs1 = stat.executeQuery("select ref_plants1 from PLANT where NAME_PLANTS = '"+Name+"'");
	    if(rs1.next())
	    {
	    	arr2.add(rs1.getInt("ref_plants1"));
	    }
	    rs1 = stat.executeQuery("select ref_plants2 from PLANT where NAME_PLANTS = '"+Name+"'");
	    if(rs1.next())
	    {
	    	arr2.add(rs1.getInt("ref_plants2"));
	    }
	    rs1 = stat.executeQuery("select ref_plants3 from PLANT where NAME_PLANTS = '"+Name+"'");
	    if(rs1.next())
	    {
	    	arr2.add(rs1.getInt("ref_plants3"));
	    }
	    rs1 = stat.executeQuery("select ref_plants4 from PLANT where NAME_PLANTS = '"+Name+"'");
	    if(rs1.next())
	    {
	    	arr2.add(rs1.getInt("ref_plants4"));
	    }
	    rs1 = stat.executeQuery("select ref_plants5 from PLANT where NAME_PLANTS = '"+Name+"'");
	    if(rs1.next())
	    {
	    	arr2.add(rs1.getInt("ref_plants5"));
	    }
		rs1.close();
		return arr2;
	}
	public Vector getComponents(String Otdel, String Vid, String Tip, String PodTip, String Klim_usl, String Kategor) throws SQLException
	{
		Vector arr3 = new Vector();	
		ResultSet rs = stat.executeQuery("SELECT id_categ FROM categ WHERE name_categ = '"+Kategor+ "'" );
		if (rs.next())
		{
			arr3.add(rs.getInt("id_categ"));
		}
		rs = stat.executeQuery("SELECT id_climatic FROM climatic WHERE name_climatic = '"+Klim_usl+ "'" );
		if (rs.next())
		{
			arr3.add(rs.getInt("id_climatic"));
		}
		rs = stat.executeQuery("SELECT id_department FROM department WHERE name_department = '"+Otdel+ "'" );
		if (rs.next())
		{
			arr3.add(rs.getInt("id_department"));
		}
		rs = stat.executeQuery("SELECT id_kind FROM kind WHERE name_kind = '"+Vid+ "'" );
		if (rs.next())
		{
			arr3.add(rs.getInt("id_kind"));
		}
		rs = stat.executeQuery("SELECT id_type FROM types WHERE name_type =  '"+Tip+"' and ref_type = (SELECT id_podtype FROM podtype WHERE name_podtype =  '"+PodTip+"')");
		if (rs.next())
		{
			arr3.add(rs.getInt("id_type"));
		}		
		rs.close();
		return arr3;
	}
	
	public boolean saveClient(String Name, double Price, int Sell, String Otdel, String Vid, String Tip, String PodTip, String Klim_usl,
			                   double Visota, double Prod_Jiz, double Vozrast, int Kol_Vazonov, int Kol, int Kol_v_Vazone,
			                   String Kategor) throws SQLException 
	{
		int id=0;
		double pr, h, died, year;
		int kv, kvz;
		//Vector s1 = getComponents(Otdel, Vid, Tip, PodTip, Klim_usl, Kategor);
		//Vector s2 = getRefPlants(Name);
			
		//проверка, не существует ли растения с таким именем
		Vector v1 = getComponents(Otdel, Vid, Tip, PodTip, Klim_usl, Kategor);
		ResultSet rs = stat.executeQuery("SELECT name_plants FROM Plant WHERE name_plants='"+Name+"'");
		if (rs.next())
		{
			rs = stat.executeQuery("SELECT id_plants FROM Plant WHERE name_plants='"+Name+"'");
			if(rs.next())
			{
				id = rs.getInt("id_plants");
			}
			Vector v = getIdCopys(id);
			Vector v2 = getRefPlants(Name);
			int nShag = 0;
			switch(nShag)
			{
				case 0:
				{
					 if (v2.get(0) == v1.get(0))
						 nShag = 1;
				     else break;
				}
				case 1:
				{
					 if (v2.get(1) == v1.get(1))
						 nShag = 2;
				     else break;			
				}
				case 2:
				{
					 if (v2.get(2) == v1.get(2))
						 nShag = 3;
				     else break;			
				}
				case 3:
				{
					 if (v2.get(3) == v1.get(3))
						 nShag = 4;
				     else break;			
				}
				case 4:
				{
					 if (v2.get(4) == v1.get(4))
						 nShag = 5;
				     else break;			
				}
				case 5:
				{
					int nShag1 = 0;
					for(int i=0; i < v.size(); i++)
					{
						ResultSet rs1;
						switch(nShag1)
						{
							case 0:
							{
								pr = 0;
								rs1 = stat.executeQuery("SELECT PRICE FROM COPYS WHERE  id_copy = '"+v.get(i)+"'");
								/*for(int j=0;j<v.size();j++)
									System.out.println(v.get(j));*/
								if(rs1.next())
								{
									pr = rs1.getFloat("Price");
								}
								if(Price == pr)								
									nShag1 = 1;
								else break;
							}
							case 1:
							{
								h = 0;
								rs1 = stat.executeQuery("SELECT Height FROM COPYS WHERE  id_copy = '"+v.get(i)+"'");
								if(rs1.next())
								{
									h = rs1.getFloat("Height");
								}
								if(Visota == h)
									nShag1 = 2;
								else break;
							}
							case 2:
							{
								died = 0;
								rs1 = stat.executeQuery("SELECT died FROM COPYS WHERE  id_copy = '"+v.get(i)+"'");
								if(rs1.next())
								{
									died = rs1.getFloat("died");
								}
								if(Prod_Jiz == died)
									nShag1 = 3;
								else break;	
							}
							case 3:
							{
								year = 0;
								rs1 = stat.executeQuery("SELECT years FROM COPYS WHERE  id_copy = '"+v.get(i)+"'");
								if(rs1.next())
								{
									year = rs1.getFloat("years");
								}
								if(Vozrast == year)
									nShag1 = 4;
								else break;
							}
							case 4:
							{
								kv = 0;
								rs1 = stat.executeQuery("SELECT kv FROM COPYS WHERE  id_copy = '"+v.get(i)+"'");
								if(rs1.next())
								{
									kv = rs1.getInt("kv");
								}
								if(Kol_Vazonov == kv)
									nShag1 = 5;
								else break;
								
							}
							case 5:
							{
								kvz = 0;
								rs1 = stat.executeQuery("SELECT kvz FROM COPYS WHERE  id_copy = '"+v.get(i)+"'");
								if(rs1.next())
								{
									kvz = rs1.getInt("kvz");
								}
								if(Kol_v_Vazone == kvz)
									nShag1 = 6;
								else break;
							}
							case 6:
							{
								int n = 0;
								int kol = 0, sale = 0;
								rs1 = stat.executeQuery("SELECT kol FROM COPYS WHERE  id_copy = '"+v.get(i)+"'");
								if(rs1.next())
								{
									kol = rs1.getInt("kol");
								}
								rs1 = stat.executeQuery("SELECT sale FROM COPYS WHERE  id_copy = '"+v.get(i)+"'");
								if(rs1.next())
								{
									sale = rs1.getInt("sale");
								}
								Kol = Kol + kol;
								Sell = Sell + sale;
								n = stat.executeUpdate("DELETE from Copys WHERE id_copy = '"+v.get(i)+"'");
								n = 0;
								n = stat.executeUpdate("INSERT INTO copys (id_copy, kol, sale, price, kvz, kv, years, died, height, ref_copy) VALUES ('"+v.get(i)+"', '"+Kol+"', '"+Sell+"', '"+Price+"', '"+Kol_v_Vazone+"', '"+Kol_Vazonov+"', '"+Vozrast+"', '"+Prod_Jiz+"', '"+Visota+"', '"+id+"')");
								if(n>0)
									return true;
							}
						}
					}
				}
				case 6:
				{
				     int id1=0;
				     rs = stat.executeQuery("SELECT max(id_copy) FROM copys" );
				     if (rs.next())
				     {
				     	id1 = rs.getInt(1);
				     }
				     id1++;
				     int n = 0;
				     n = stat.executeUpdate("INSERT INTO copys (id_copy, kol, sale, price, kvz, kv, years, died, height, ref_copy) VALUES ('"+id1+"', '"+Kol+"', '"+Sell+"', '"+Price+"', '"+Kol_v_Vazone+"', '"+Kol_Vazonov+"', '"+Vozrast+"', '"+Prod_Jiz+"', '"+Visota+"', '"+id+"')");
				     if (n>0)
				    	 return true;
				}
			}
		}

		rs = stat.executeQuery("SELECT max(id_plants) FROM plant" );
		if (rs.next())
			id=rs.getInt(1);
		//System.out.print(id);
		id++;
		//добавление нового растения в базу данных. 	метод executeUpdate возращает количество обработанных строк
		int  n = stat.executeUpdate("INSERT INTO plant (id_plants, name_plants, ref_plants1, ref_plants2, ref_plants3, ref_plants4, ref_plants5) VALUES ('"+id+"', '"+Name+"', '"+v1.get(0)+"', '"+v1.get(1)+"', '"+v1.get(2)+"', '"+v1.get(3)+"', '"+v1.get(4)+"')");
		
		int id1=0;
		if(n>0)
		{
			rs = stat.executeQuery("SELECT max(id_copy) FROM copys" );
			if (rs.next())
			{
				id1 = rs.getInt(1);
				
			}
			id1++;
			n = stat.executeUpdate("INSERT INTO copys (id_copy, kol, sale, price, kvz, kv, years, died, height, ref_copy) VALUES ('"+id1+"', '"+Kol+"', '"+Sell+"', '"+Price+"', '"+Kol_v_Vazone+"', '"+Kol_Vazonov+"', '"+Vozrast+"', '"+Prod_Jiz+"', '"+Visota+"', '"+id+"')");
			if (n>0)
				return true;
		}
		return false;
	}
	
	public String getName(int id_copy) throws SQLException
	{
		String arr1 = new String();	
		ResultSet rs = stat.executeQuery("select name_plants from copys, plant where id_copy = '"+id_copy+"' and ref_copy = id_plants");
		if (rs.next())
		{			
			arr1 = rs.getString("name_plants");
		}			
		rs.close();
		return arr1;
	}
	public double getPrice(int id_copy) throws SQLException
	{
		double arr2 = 0.0;	
		ResultSet rs = stat.executeQuery("select PRICE from copys where id_copy = '"+id_copy+"'");
		if (rs.next())
		{			
			arr2 = rs.getDouble("PRICE");
		}			
		rs.close();
		return arr2;
	}
	public boolean PlantsDeal(int ID, int Kol, int nKey) throws SQLException
	{
		int Sale=0;
		Vector arr = new Vector();
		ResultSet rs = stat.executeQuery("select id_copy from copys where id_copy = '"+ID+"'");	
		if (rs.next())
		{			
			rs = stat.executeQuery("select SALE from copys where id_copy = '"+ID+"'");
			if(rs.next())
			{
				Sale = rs.getInt("SALE");
				if (nKey == 1)
					Sale += Kol;
				if (nKey == 2)
					Sale -= Kol;
			}
			rs = stat.executeQuery("select *from COPYS where ID_COPY = '"+ID+"'");
			if(rs.next())
			{
				
				arr.add(rs.getInt("Kol"));
				arr.add(rs.getDouble("Price"));
				arr.add(rs.getInt("KVZ"));
				arr.add(rs.getInt("KV"));
				arr.add(rs.getDouble("Years"));
				arr.add(rs.getDouble("Died"));
				arr.add(rs.getDouble("Height"));
				arr.add(rs.getInt("ref_copy"));
			}
			int n = stat.executeUpdate("DELETE from Copys WHERE id_copy = '"+ID+"'");
			n = 0;
			n = stat.executeUpdate("INSERT INTO copys (id_copy, kol, sale, price, kvz, kv, years, died, height, ref_copy) VALUES ('"+ID+"', '"+arr.get(0)+"', '"+Sale+"', '"+arr.get(1)+"', '"+arr.get(2)+"', '"+arr.get(3)+"', '"+arr.get(4)+"', '"+arr.get(5)+"', '"+arr.get(6)+"', '"+arr.get(7)+"')");
			if(n>0)
			{
				return true;
			}
		}	
		
		return false;
	}

	public int getRef_Plants1(int ID, int Kol) throws SQLException
	{
		int r=0 , rr=0, RSale=0;
		ResultSet rs = stat.executeQuery("select SALE from copys where id_copy = '"+ID+"'");
		if(rs.next())
		{
			RSale = rs.getInt("SALE");
		}
		rs = stat.executeQuery("select ref_copy from copys where id_copy = '"+ID+"'");
		if(rs.next())
		{
			r = rs.getInt("ref_copy");
		}
		rs = stat.executeQuery("select ref_plants1 from plant where id_plants = '"+r+"'");
		if(rs.next())
		{
			r = rs.getInt("ref_plants1");
		}
		if(r == 1)
		{
			rs = stat.executeQuery("select KV from copys where id_copy = '"+ID+"'");
			if(rs.next())
			{
				rr = rs.getInt("KV");
			}
			if (rr-RSale >= Kol) 
			{
				return 0;
			}
			else
			{
				return 1;
			}
		}
		if (r == 2)
		{
			rs = stat.executeQuery("select Kol from copys where id_copy = '"+ID+"'");
			if(rs.next())
			{
				rr = rs.getInt("Kol");
			}
			if (rr-RSale >= Kol)
			{
				return 0;
			}
			else
			{
				return 1;
			}
		}		
		return 1;
	}
	
	public boolean DelPlant(String Name) throws SQLException
	{
		int id=0, n=0;
		ResultSet rs = stat.executeQuery("select id_plants from plant where NAME_PLANTS = '"+Name+"'");
		if(rs.next())
		{
			id = rs.getInt("id_plants");
		}
		rs = stat.executeQuery("select id_copy from copys where ref_copy = '"+id+"'");
		Vector arr = new Vector();
		while (rs.next())
		{
			arr.add(rs.getInt("id_copy"));
		}
		for(int i=0; i< arr.size(); i++)
		{
			n = stat.executeUpdate("delete from copys where id_copy = '"+arr.get(i)+"'");	
		}
		if (n > 0)
			return true;
		
		return false;
	}
}
