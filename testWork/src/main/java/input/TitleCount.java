package input;

public class TitleCount { //класс для добавления в колекцию о колличесве покупателй с низким прайсом
	private String title;
	private int count;
	
	public TitleCount(String title, int count) {
		this.title = title;
		this.count = count;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "\"productName\": " + title + ", \"minTimes\": " + count + "";
	}
}
