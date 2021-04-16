package search;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BaseModel {
	private static List<FoundNames> nameLN = new ArrayList<>();

	static Connection getConnection() throws ClassNotFoundException, SQLException {//считываение данных из БД
		Class.forName(Config.driver);
		String url = "jdbc:postgresql://localhost:5432/" + Config.db;
		return DriverManager.getConnection(url, Config.login, Config.pass);
	}
	
	public static List<FoundNames> getNameName(String lastName) throws ClassNotFoundException, SQLException{//считывание фамилий по поиску фамилий
		Connection con = getConnection();
		Statement stat = con.createStatement();
		ResultSet result = stat.executeQuery("select name from customers where last_name='"+lastName+"';");
		String name;
		nameLN.clear();
		while(result.next()) {
			name = result.getString("name");
			nameLN.add(new FoundNames(name, lastName));
		}
		return nameLN;
	}
	
	public static List<FoundNames> getNameTitle(String title, int count) throws ClassNotFoundException, SQLException{ //считывание фамилий по поиску товара и и его колличеству
		Connection con = getConnection();
		Statement stat = con.createStatement();
		ResultSet result = stat.executeQuery("select name, last_name, title, count(*) from matching group by name, last_name, title having count(*) >" + count + " and title='" + title + "';");
		String name, last_name;
		nameLN.clear();
		while(result.next()) {
			name = result.getString("name");
			last_name = result.getString("last_name");
			nameLN.add(new FoundNames(name, last_name));
		}
		return nameLN;
	}
	
	public static List<FoundNames> getRange(int min, int max) throws ClassNotFoundException, SQLException{ //считывание фамилий по диапазону
		Connection con = getConnection();
		Statement stat = con.createStatement();
		ResultSet result = stat.executeQuery("select name, last_name, sum(price) from matching group by name, last_name having sum(price) >"+ min + " and sum(price) <" + max + ";");
		String name, last_name;
		nameLN.clear();
		while(result.next()) {
			name = result.getString("name");
			last_name = result.getString("last_name");
			nameLN.add(new FoundNames(name, last_name));
		}
		return nameLN;
	}
	
	public static List<FoundNames> getPassive(int count) throws ClassNotFoundException, SQLException{ //считывание фамилий по пасивным покупателям
		Connection con = getConnection();
		Statement stat = con.createStatement();
		ResultSet result = stat.executeQuery("select name, last_name, sum(price) from matching group by name, last_name;");
		String name, last_name;
		int col = 0;
		nameLN.clear();
		while(result.next() && col<count) {
			name = result.getString("name");
			last_name = result.getString("last_name");
			nameLN.add(new FoundNames(name, last_name));
			col++;
		}
		return nameLN;
	}
}
