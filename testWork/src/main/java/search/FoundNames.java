package search;

public class FoundNames {//класс для вывода фаимилей и имён по результатам поиска
	private String name;
	private String lastName;
	
	public FoundNames(String name, String lastName) {
		super();
		this.name = name;
		this.lastName = lastName;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "{\"name\": \"" + (name).replaceAll(" ", "") + "\", \"lastName\":\"" + (lastName).replaceAll(" ", "") + "\"}";
	}

}
