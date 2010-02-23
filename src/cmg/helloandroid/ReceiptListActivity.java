/**
 * 
 */
package cmg.helloandroid;

import android.app.ListActivity;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.os.Bundle;
import java.util.List;
import java.util.LinkedList;

/**
 * @author cmg
 *
 */
public class ReceiptListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		final List<Receipt> receipts = new LinkedList<Receipt>();
		Receipt r;
		r = new Receipt("1", "A receipt", "you did something, you know", "http://someurl/12345");
		receipts.add(r);
		r = new Receipt("2", "Another receipt", "you did something else, you know", "http://someotherurl/12345");
		receipts.add(r);
		
		setListAdapter(new SimpleCursorAdapter(getBaseContext(), R.layout.receipt_item, 
				new ReceiptCursor(receipts), new String[] {"title","description"}, new int[] {R.id.receipt_title,R.id.receipt_description}));
		//, R.layout.receipt_item)); 
		ListView lv = getListView();  
		lv.setTextFilterEnabled(true);  
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// When clicked, show a toast with the TextView text      
				Toast.makeText(getApplicationContext(), 
						receipts.get(position).getDescription()+" (id="+id+")",
						Toast.LENGTH_SHORT).show();  
			}
		});
	}

	static final String[] COUNTRIES = new String[] {    "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra" };
	
}
