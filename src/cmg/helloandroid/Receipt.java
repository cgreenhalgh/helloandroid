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
	private long _id;
	private String uid;
	private String title;
	private String description;
	private long datetimeMs;
	private String timezoneId;
	private String privateUrl;
	public Receipt() {
		super();
	}
	public Receipt(long id, String uid, String title, String description, long datetimeMs,
			String timezoneId, String privateUrl) {
		super();
		_id = id;
		this.uid = uid;
		this.title = title;
		this.description = description;
		this.datetimeMs = datetimeMs;
		this.timezoneId = timezoneId;
		this.privateUrl = privateUrl;
	}
	public long get_id() {
		return _id;
	}
	public void set_id(long id) {
		_id = id;
	}	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	public long getDatetimeMs() {
		return datetimeMs;
	}
	public void setDatetimeMs(long datetimeMs) {
		this.datetimeMs = datetimeMs;
	}
	public String getTimezoneId() {
		return timezoneId;
	}
	public void setTimezoneId(String timezoneId) {
		this.timezoneId = timezoneId;
	}
	public String getPrivateUrl() {
		return privateUrl;
	}
	public void setPrivateUrl(String privateUrl) {
		this.privateUrl = privateUrl;
	}
	
}
