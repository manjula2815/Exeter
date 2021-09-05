package exeter;

public class Words {
	private String english;
	private String french;
	private int count;

	public Words(String english, String french, int count) {
		super();
		this.english = english;
		this.french = french;
		this.count = count;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getFrench() {
		return french;
	}

	public void setFrench(String french) {
		this.french = french;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Words [english=" + english + ", french=" + french + ", count=" + count + "]";
	}

	public Words() {
		super();
	}

}
