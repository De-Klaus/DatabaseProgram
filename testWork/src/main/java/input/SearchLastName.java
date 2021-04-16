package input;

public class SearchLastName { //класс для добавления в колекцию данных по фамилиям
	private String criteria;
	private String lastName;
	
	public SearchLastName(String criteria, String lastName) {
		this.criteria = criteria;
		this.lastName = lastName;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "\""+criteria +"\": "  + lastName;
	}

}
