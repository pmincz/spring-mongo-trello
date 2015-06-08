package sample.domain.entry;

/**
 * Board entry for the rest services
 * @author pmincz
 *
 */
public class BoardEntry {
	
	private String title;
	
	public BoardEntry() {}
	
	public BoardEntry(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
