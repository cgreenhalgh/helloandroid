/**
 * 
 */
package cmg.helloandroid;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author cmg
 *
 */
public class ReceiptDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.receipt_detail);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Intent intent = this.getIntent();
		try {
			long id = Long.valueOf(intent.getData().getSchemeSpecificPart());
			Receipt receipt = Store.getStore().getReceipt(id);
			((TextView)findViewById(R.id.receipt_detail_title)).setText(receipt.getTitle());
			Date date = new Date(receipt.getDatetimeMs());
			DateFormat format = SimpleDateFormat.getDateTimeInstance();
			format.setTimeZone(TimeZone.getTimeZone(receipt.getTimezoneId()));
			((TextView)findViewById(R.id.receipt_detail_date)).setText(format.format(date));	
			((TextView)findViewById(R.id.receipt_detail_description)).setText(receipt.getDescription());
		}
		catch (Exception e) {
			// error...			
		}
	}

}
