/**
 * 
 */
package cmg.helloandroid;

import android.app.ListActivity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.os.Bundle;
import android.net.Uri;
import java.util.List;
import java.util.LinkedList;
import java.util.TimeZone;

/**
 * @author cmg
 *
 */
public class ReceiptListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		final List<Receipt> receipts = Store.getStore().getAllReceipts();
		
		setListAdapter(new SimpleCursorAdapter(getBaseContext(), R.layout.receipt_item, 
				new ReceiptCursor(receipts), new String[] {"title","description"}, new int[] {R.id.receipt_title,R.id.receipt_description}));
		//, R.layout.receipt_item)); 
		ListView lv = getListView();  
		lv.setTextFilterEnabled(true);  
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// When clicked, show a toast with the TextView text      
//				Toast.makeText(getApplicationContext(), 
//						receipts.get(position).getDescription()+" (id="+id+")",
//						Toast.LENGTH_SHORT).show();  
				Intent intent = new Intent(ReceiptListActivity.this, ReceiptDetailActivity.class);
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(new Uri.Builder().scheme("receipt").opaquePart(""+id).build());
				startActivity(intent);
			}
		});
	}
}
