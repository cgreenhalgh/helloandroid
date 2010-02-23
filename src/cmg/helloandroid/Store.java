/**
 * 
 */
package cmg.helloandroid;

import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

/**
 * @author cmg
 *
 */
public class Store {
	protected List<Receipt> receipts;
	
	public Store() {
		receipts = new LinkedList<Receipt>();
		Receipt r;
		r = new Receipt(1, "1", "A receipt", "you did something, you know", System.currentTimeMillis(), TimeZone.getDefault().getID(), "http://someurl/12345");
		receipts.add(r);
		r = new Receipt(2, "2", "Another receipt", "you did something else, you know", System.currentTimeMillis(), TimeZone.getDefault().getID(), "http://someotherurl/12345");
		receipts.add(r);
	}
	
	private static Store store;
	static synchronized Store getStore() {
		if (store==null)
			store = new Store();
		return store;
	}
	
	public List<Receipt> getAllReceipts() {
		return receipts;
	}
	
	public Receipt getReceipt(long id) {
		for (Receipt receipt : receipts) {
			if (receipt.get_id()==id)
				return receipt;
		}
		return null;
	}
}
