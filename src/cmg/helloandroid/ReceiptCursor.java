/**
 * 
 */
package cmg.helloandroid;

import android.database.AbstractCursor;
import java.util.List;

/**
 * @author cmg
 *
 */
public class ReceiptCursor extends AbstractCursor {

	private List<Receipt> rows;
	
	
	public ReceiptCursor(List<Receipt> rows) {
		super();
		this.rows = rows;
	}

	static final String [] columnNames = new String[] { "_id", "uid", "title", "description", "datetimeMs", "timezoneId", "privateUrl" };

	protected Object getObject(int col) {
		switch(col) {
		case 0:
			return new Long(rows.get(getPosition()).get_id());
		case 1:
			return rows.get(getPosition()).getUid();
		case 2:
			return rows.get(getPosition()).getTitle();
		case 3:
			return rows.get(getPosition()).getDescription();
		case 4: 
			return new Long(rows.get(getPosition()).getDatetimeMs());
		case 5:
			return rows.get(getPosition()).getTimezoneId();
		case 6:
			return rows.get(getPosition()).getPrivateUrl();
		}
		return null;
	}
	/* (non-Javadoc)
	 * @see android.database.AbstractCursor#getColumnNames()
	 */
	@Override
	public String[] getColumnNames() {
		return columnNames;
	}

	/* (non-Javadoc)
	 * @see android.database.AbstractCursor#getCount()
	 */
	@Override
	public int getCount() {
		return rows.size();
	}

	/* (non-Javadoc)
	 * @see android.database.AbstractCursor#getDouble(int)
	 */
	@Override
	public double getDouble(int col) {
		return getLong(col);
	}

	/* (non-Javadoc)
	 * @see android.database.AbstractCursor#getFloat(int)
	 */
	@Override
	public float getFloat(int col) {
		return getLong(col);
	}

	/* (non-Javadoc)
	 * @see android.database.AbstractCursor#getInt(int)
	 */
	@Override
	public int getInt(int col) {
		return (int)getLong(col);
	}

	/* (non-Javadoc)
	 * @see android.database.AbstractCursor#getLong(int)
	 */
	@Override
	public long getLong(int col) {
		Object value = getObject(col);
		if (value instanceof Long)
			return ((Long)value).longValue();
		return 0;
	}

	/* (non-Javadoc)
	 * @see android.database.AbstractCursor#getShort(int)
	 */
	@Override
	public short getShort(int col) {
		return (short)getLong(col);
	}
	/* (non-Javadoc)
	 * @see android.database.AbstractCursor#getString(int)
	 */
	@Override
	public String getString(int col) {
		Object value = getObject(col);
		if (value instanceof String)
			return (String)value;
		else if (value==null)
			return null;
		else
			return value.toString();
	}

	/* (non-Javadoc)
	 * @see android.database.AbstractCursor#isNull(int)
	 */
	@Override
	public boolean isNull(int col) {
		Object value = getObject(col);
		if (value==null)
			return true;
		return false;
	}
}
