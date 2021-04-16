package input;

public class MinMax {//класс для добавления в колекцию данных Min и Max
	private int max;
	private int min;
	
	public MinMax(int max, int min) {
		this.max = max;
		this.min = min;
	}
	
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}

	@Override
	public String toString() {
		return "\"min\": " + max + ", \"max\": " + min;
	}
	
}
