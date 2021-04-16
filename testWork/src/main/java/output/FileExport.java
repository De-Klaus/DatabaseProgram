package output;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import search.Config;

public class FileExport { //запись файла JSON
	
	private FileWriter file;
	private JSONObject object = new JSONObject();
	private int count = 0;

	public void start() throws IOException {
		file = new FileWriter(Config.getPathOut());
	}
	
	@SuppressWarnings("unchecked")
	public void writerFileType(String type) throws IOException  { //добавления типа поиска
	    object.put(" type", type); 
	}
	
	@SuppressWarnings("unchecked")
	public void writerFileResult(List<?> criteria, List<?> results) throws IOException  { //добавления результатов поиска
		
		JSONArray list1 = new JSONArray();
		list1.add(criteria.get(count));
		
		JSONArray list2 = new JSONArray();
		for(int i =0; i<results.size(); i++) {
			list2.add(results.get(i));
		}
		String criterias = "criteria"+count;
		String result = "results"+count;
		object.put(criterias, list1);
		object.put(result, list2);
		count++;
	}
	
	public void close() throws IOException { //запись данных в файла и закртие потока записи
		file.write(object.toJSONString());
		file.close();
	}
	
}
