/**
 * 
 */
package cmg.helloandroid;

import java.io.ObjectInputStream;

import org.drools.KnowledgeBase;
import org.drools.common.DroolsObjectInputStream;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * @author cmg
 *
 */
public class Main extends Activity {
	static final String TAG = "HelloAndroidMain";
	
	static class MyClassLoader extends ClassLoader {

		/**
		 * @param parentLoader
		 */
		public MyClassLoader(ClassLoader parentLoader) {
			super(parentLoader);
			// TODO Auto-generated constructor stub
		}

		/* (non-Javadoc)
		 * @see java.lang.ClassLoader#loadClass(java.lang.String, boolean)
		 */
		@Override
		protected Class<?> loadClass(String className, boolean resolve)
				throws ClassNotFoundException {
			// TODO Auto-generated method stub
			Log.i(TAG,"loadClass("+className+","+resolve+")");
			return super.loadClass(className, resolve);
		}

		/* (non-Javadoc)
		 * @see java.lang.ClassLoader#findClass(java.lang.String)
		 */
		@Override
		protected Class<?> findClass(String className)
				throws ClassNotFoundException {
			// TODO Auto-generated method stub
			Log.i(TAG,"findClass("+className+")");
			return super.findClass(className);
		}

		/* (non-Javadoc)
		 * @see java.lang.ClassLoader#loadClass(java.lang.String)
		 */
		@Override
		public Class<?> loadClass(String className)
				throws ClassNotFoundException {
			// TODO Auto-generated method stub
			Log.i(TAG,"loadClass("+className+")");
			return super.loadClass(className);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		DroolsObjectInputStream ois = null;
		try {
			Log.i(TAG, "Trying drools...");
			ClassLoader cl = new MyClassLoader(Main.class.getClassLoader());
			Log.i(TAG, "ClassLoader: "+cl);
			Log.i(TAG, "ClassLoader.getParent(): "+cl.getParent());
			TextView text = ((TextView)findViewById(R.id.main_text));
			Log.i(TAG, "Main: "+Class.forName("cmg.helloandroid.Main", true, cl));
			Log.i(TAG, "LatLng: "+Class.forName("uk.me.jstott.jcoord.LatLng", true, cl));
			Log.i(TAG, "KnowledgeBase: "+Class.forName("org.drools.KnowledgeBase", true, cl));
			Log.i(TAG, "KnowledgeBaseImpl: "+Class.forName("org.drools.impl.KnowledgeBaseImpl", true, cl));
			Log.i(TAG, "RuleBaseConfiguration: "+Class.forName("org.drools.RuleBaseConfiguration", true, cl));
			try {
				Log.i(TAG, "RuleBaseConfiguration in parent: "+Class.forName("org.drools.RuleBaseConfiguration", true, cl.getParent()));				
			}
			catch (Exception e) {
				Log.e(TAG, "RuleBaseConfiguration in parent", e);
			}
			// = "0" !!
			Log.i(TAG, "java.version = "+System.getProperty("java.version"));
			if(System.getProperty("java.version")==null || System.getProperty("java.version").length()<3) {
				System.setProperty("java.version", "1.5"); // ?!
				Log.i(TAG, "java.version = "+System.getProperty("java.version"));
			}
			// NB KnowledgeBase must be written to DroolsObjectOutputStream
			// NB requires bug fix to org.drools.util.CompositeClassLoader constructor to 
			// not pass null parent class loader]
			// See dalvik.system.DexClassLoader for loading Dex class files from filesystem.
			// Note: will need to run DX on generated class files on server.
			/*
			E/dalvikvm(  795): ERROR: defineClass(0x43c258f0, uk.ac.horizon.ug.hyperplace.fa
					cts.GeoDistance, 0x43bbb080, 0, 731, 0x0)
					E/HelloAndroidMain(  795): trying drools
					E/HelloAndroidMain(  795): java.lang.UnsupportedOperationException: can't load t
					his type of class file
					E/HelloAndroidMain(  795):      at java.lang.VMClassLoader.defineClass(Native Me
					thod)
					E/HelloAndroidMain(  795):      at java.lang.ClassLoader.defineClass(ClassLoader
					.java:338)
					E/HelloAndroidMain(  795):      at org.drools.rule.JavaDialectRuntimeData$Packag
					eClassLoader.fastFindClass(JavaDialectRuntimeData.java:459)
					E/HelloAndroidMain(  795):      at org.drools.rule.DroolsCompositeClassLoader.fa
					stFindClass(DroolsCompositeClassLoader.java:55)
					E/HelloAndroidMain(  795):      at org.drools.rule.DroolsCompositeClassLoader.lo
					adClass(DroolsCompositeClassLoader.java:71)
					E/HelloAndroidMain(  795):      at java.lang.ClassLoader.loadClass(ClassLoader.j
					ava:532)
					E/HelloAndroidMain(  795):      at org.drools.base.mvel.MVELCompilationUnit.load
					Class(MVELCompilationUnit.java:379)
					E/HelloAndroidMain(  795):      at org.drools.base.mvel.MVELCompilationUnit.getC
					ompiledExpression(MVELCompilationUnit.java:201)
*/
			
			//.setText("Hello "+System.currentTimeMillis());	
			ois = new DroolsObjectInputStream( this.getResources().openRawResource(R.raw.hyperplace), cl);
			KnowledgeBase kb = (KnowledgeBase)ois.readObject();
			ois.close();
			Log.i(TAG, "Read "+kb.getClass().getName()+" "+kb);
			text.setText("Read "+kb.getClass().getName()+" "+kb);
		}
		catch (Exception e) {
			Log.e(TAG,"trying drools", e);
			if (ois!=null) {
				Log.i(TAG, "Stream ClassLoader = "+ois.getClassLoader()+", parent="+ois.getParentClassLoader());
			}
		}
	}

}
