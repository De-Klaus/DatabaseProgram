package search;

public class Config { //класс подкоючения к базе данных и указания путей до файлов
	static String driver = "org.postgresql.Driver";
	static String db = "ShopOnline";
	static String login = "postgres";
	static String pass = "postgres";
	private static String pathIn;
	private static String pathOut;
	
	public static String getPathIn() {
		return pathIn;
	}
	public static void setPathIn(String pathIn) {
		Config.pathIn = pathIn;
	}
	public static String getPathOut() {
		return pathOut;
	}
	public static void setPathOut(String pathOut) {
		Config.pathOut = pathOut;
	}
}
