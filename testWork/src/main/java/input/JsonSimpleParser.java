package input;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import search.Config;

public class JsonSimpleParser {
	private static List<SearchLastName> lastName = new ArrayList<>(); //колекция для запроса поиска по фамилииям
	private static List<TitleCount> titleCount = new ArrayList<>(); //колекция для запроса поиска по названию товара и его колличеству
	private static List<MinMax> minMax = new ArrayList<>(); //колекция для запроса поиска по диапазон цен
	private static List<Integer> customers = new ArrayList<>(); //колекция для запроса поиска покупателей по их количеству
	private static List<String> dateDiapason = new ArrayList<>(); //колекция для запроса диапазона дат
	
	public List<SearchLastName> parseLN() throws FileNotFoundException { //метод считывания запроса поиска по фамилииям
		JSONParser parser = new JSONParser();
		try(FileReader reader = new FileReader(Config.getPathIn())){
			JSONObject parseJsonObject = (JSONObject) parser.parse(reader);	
			JSONArray param = (JSONArray) parseJsonObject.get("last");
			lastName.clear();
			for(Object it: param) {
				JSONObject paramJSONObject = (JSONObject) it;
				String lastNames = (String) paramJSONObject.get("lastName");
				lastName.add(new SearchLastName("lastName",lastNames));
			}				
		} catch(Exception e) {
		}
		return lastName;
	}
	
	public List<TitleCount> parseTC() throws FileNotFoundException { //метод считывания по названию товара и его колличеству
		JSONParser parser = new JSONParser();
		//try(FileReader reader = new FileReader("C:/Users/Omen/Desktop/test.json")){
		try(FileReader reader = new FileReader(Config.getPathIn())){
			JSONObject parseJsonObject = (JSONObject) parser.parse(reader);	
			JSONArray param = (JSONArray) parseJsonObject.get("product");
			titleCount.clear();
			for(Object it: param) {
				JSONObject paramJSONObject = (JSONObject) it;
				String title = (String) paramJSONObject.get("productName");
				long count = (long) paramJSONObject.get("minTimes");
				titleCount.add(new TitleCount(title, (int)count));
			}				
		} catch(Exception e) {
		}
		return titleCount;
	}
	
	public List<MinMax> parseMM() throws FileNotFoundException { //метод считывания поиска по диапазону цен
		JSONParser parser = new JSONParser();
		try(FileReader reader = new FileReader(Config.getPathIn())){
			JSONObject parseJsonObject = (JSONObject) parser.parse(reader);	
			JSONArray param = (JSONArray) parseJsonObject.get("expenses");
			minMax.clear();
			for(Object it: param) {
				JSONObject paramJSONObject = (JSONObject) it;
				long min = (long) paramJSONObject.get("minExpenses");
				long max = (long) paramJSONObject.get("maxExpenses");
				minMax.add(new MinMax((int)min, (int)max));
			}				
		} catch(Exception e) {
		}
		return minMax;
	}
	
	public List<Integer> parseCust() throws FileNotFoundException { //метод считывания поиска покупателей по их количеству
		JSONParser parser = new JSONParser();
		try(FileReader reader = new FileReader(Config.getPathIn())){
			JSONObject parseJsonObject = (JSONObject) parser.parse(reader);	
			JSONArray param = (JSONArray) parseJsonObject.get("customers");
			customers.clear();
			for(Object it: param) {
				JSONObject paramJSONObject = (JSONObject) it;
				long min = (long) paramJSONObject.get("badCustomers");
				customers.add((int)min);
			}				
		} catch(Exception e) {
		}
		return customers;
	}
	
	public List<String> parseDate() throws FileNotFoundException { //метод считывания запроса диапазона дат
		JSONParser parser = new JSONParser();
		try(FileReader reader = new FileReader(Config.getPathIn())){
			JSONObject parseJsonObject = (JSONObject) parser.parse(reader);	
			String startDate = (String) parseJsonObject.get("startDate");
			String endDate = (String) parseJsonObject.get("endDate");
			dateDiapason.add(startDate + " " + endDate);			
		} catch(Exception e) {
		}
		return dateDiapason;
	}

}
