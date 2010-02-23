/**
 * 
 */
package cmg.helloandroid;

/** A data object representing a "receipt", i.e. a record of doing something.
 * Part notification, part capability.
 * 
 * @author cmg
 *
 */
public class Receipt {
	private String _id;
	private String title;
	private String description;
	private String privateUrl;
	public Receipt() {
		super();
	}
	public Receipt(String id, String title, String description,
			String privateUrl) {
		super();
		_id = id;
		this.title = title;
		this.description = description;
		this.privateUrl = privateUrl;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String id) {
		_id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrivateUrl() {
		return privateUrl;
	}
	public void setPrivateUrl(String privateUrl) {
		this.privateUrl = privateUrl;
	}
	
}
