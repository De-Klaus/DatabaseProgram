package testWork;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import input.JsonSimpleParser;
import output.FileExport;
import search.BaseModel;
import search.Config;

public class MyWorkDataBase {
		public static void main(String[] args) throws Exception {
		var reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Введите директорию расположения входного файла 'X:\\xxxxx\\xxx.json' ");
		Config.setPathIn(checkDirFile(reader.readLine()));
		System.out.println("Введите директорию расположения выходного файла 'X:\\xxxxx\\xxx.json' ");
		Config.setPathOut(checkDirFile(reader.readLine()));
		System.out.println("Введите тип операции");
		typeOperation(reader.readLine());
		reader.close();
	}
		
		private static void typeOperation(String type) throws IOException, ClassNotFoundException, SQLException { //выполенения команд соглассно указанному запросу (порядок соответствует пунктам указанным в ТЗ)
			JsonSimpleParser parser = new JsonSimpleParser();
			new BaseModel();
			FileExport fe = new FileExport();
			
			var reader = new BufferedReader(new InputStreamReader(System.in));
			while (!type.equals("lastName") && !type.equals("productNameCount")&& !type.equals("minMax")&& !type.equals("badCustomers")){
		        System.out.println("Вы указали неверный тип операции, повторите ввод");
		        type = reader.readLine();
		    	}
			if(type.equals("lastName")) {
				fe.start();
				fe.writerFileType(type);
				for(int i =0; i<parser.parseLN().size(); i++) {
					fe.writerFileResult(parser.parseLN(), BaseModel.getNameName(parser.parseLN().get(i).getLastName()));
				}				
				fe.close();
			}
			if(type.equals("productNameCount")) {
				fe.start();
				fe.writerFileType(type);
				for(int i =0; i<parser.parseTC().size(); i++) {
					fe.writerFileResult(parser.parseTC(), BaseModel.getNameTitle(parser.parseTC().get(i).getTitle(),parser.parseTC().get(i).getCount()));
				}				
				fe.close();
			}
			if(type.equals("minMax")) {
				fe.start();
				fe.writerFileType(type);
				for(int i =0; i<parser.parseMM().size(); i++) {
					fe.writerFileResult(parser.parseMM(), BaseModel.getRange(parser.parseMM().get(i).getMin(),parser.parseMM().get(i).getMax()));
				}				
				fe.close();
			}
			if(type.equals("badCustomers")) {
				fe.start();
				fe.writerFileType(type);
				for(int i =0; i<parser.parseCust().size(); i++) {
					fe.writerFileResult(parser.parseCust(), BaseModel.getPassive(parser.parseCust().get(i)));
				}				
				fe.close();
			}
			reader.close();
		}
		
		private static boolean checkFile(String directory) throws Exception {
			boolean t;
			File f = new File(directory);
				if(f.exists() && !f.isDirectory()) { 
		            t = true;
		        }
		        else{
		             t = false;
		        }
			return t;
		}
		
		private static String checkDirFile(String directory) throws Exception {//проекра кореектности указанной директории
			var reader = new BufferedReader(new InputStreamReader(System.in));
			
	      while(!checkFile(directory)) {
					System.out.println("Файла по указанному пути не существует, повторите ввод");
		            directory = reader.readLine();
			}
			return directory;
		}

}
