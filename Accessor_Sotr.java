import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Accessor_Sotr 
{
	private static Accessor_Sotr singletonAccessor;
	private Connection con;
	Statement stat;
	// скрытый конструктор принимает драйвер и адрес БД
	// выбрасывает исключение Exception
	private Accessor_Sotr(String server, String urlDatabase) throws Exception 
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
	public static Accessor_Sotr getInstance(String _driver, String _urlDatabase)
	throws Exception 
	{
		if (singletonAccessor == null)
		singletonAccessor = new Accessor_Sotr(_driver, _urlDatabase);
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
//*****************************************************************************************************************************************************
	public Vector getSotr(String Pasport) throws SQLException
	{
		Vector arr1 = new Vector();	
		ResultSet rs = stat.executeQuery("Select PASPORT from SOTRUDNIK where PASPORT = '"+Pasport+"'");
		while (rs.next())
		{			
			arr1.add(rs.getInt("PASPORT"));
		}			
		rs.close();
		return arr1;
	}

	public boolean saveClient(String FIO, String Born_Date, String Otdel, String Pasport, String Doljnost, int Vremya, double Oklad) throws SQLException 
    {
		
		int id=0;
		
		ResultSet rs = stat.executeQuery("SELECT PASPORT FROM SOTRUDNIK WHERE PASPORT='"+Pasport+"'");
		if (rs.next())
			return false;
		
		rs = stat.executeQuery("SELECT FIO FROM SOTRUDNIK WHERE FIO = '"+FIO+ "'" );
		rs = stat.executeQuery("SELECT DATA FROM SOTRUDNIK WHERE DATA = '"+Born_Date+ "'" );
		rs = stat.executeQuery("SELECT OTDEL FROM SOTRUDNIK WHERE OTDEL = '"+Otdel+ "'" );
		rs = stat.executeQuery("SELECT SPEC FROM SOTRUDNIK WHERE SPEC = '"+Doljnost+ "'" );
		rs = stat.executeQuery("SELECT OKLAD FROM SOTRUDNIK WHERE OKLAD =  '"+Oklad+"'");
		rs = stat.executeQuery("SELECT TIMES FROM SOTRUDNIK WHERE TIMES =  '"+Vremya+"'");
				
		rs = stat.executeQuery("SELECT max(id_sotr) FROM SOTRUDNIK" );
		if (rs.next())
			id=rs.getInt(1);
		id++;
		//добавление нового клиента в базу данных. 	метод executeUpdate возращает количество обработанных строк
		int  n = stat.executeUpdate("INSERT INTO SOTRUDNIK (id_SOTR, FIO, DATA, PASPORT, OTDEL, SPEC, OKLAD, TIMES) VALUES ('"+id+"', '"+FIO+"', '"+Born_Date+"', '"+Pasport+"', '"+Otdel+"', '"+Doljnost+"', '"+Oklad+"', '"+Vremya+"')");
		/*
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
		*/
		return false;
    }
	
	public boolean DelClient(String Pasport) throws SQLException
	{
		int id=0, n=0;
		ResultSet rs = stat.executeQuery("select id_sotr from SOTRUDNIK where PASPORT = '"+Pasport+"'");
		if(rs.next())
		{
			id = rs.getInt("id_sotr");
		}
		n = stat.executeUpdate("delete from SOTRUDNIK where id_sotr = '"+id+"'");	
		
		if (n > 0)
			return true;
		
		return false;
	}
}