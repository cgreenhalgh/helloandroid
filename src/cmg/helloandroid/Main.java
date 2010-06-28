/**
 * 
 */
package cmg.helloandroid;

import java.io.ObjectInputStream;
import java.util.Collection;

import org.drools.KnowledgeBase;
import org.drools.common.DroolsObjectInputStream;
import org.drools.runtime.StatefulKnowledgeSession;

import uk.ac.horizon.ug.exserver.model.ClientConversation;
import uk.ac.horizon.ug.exserver.model.ConversationStatus;
//import uk.ac.horizon.ug.hyperplace.facts.GeoPosition;

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
			Class clazz = findLoadedClass(className);
			Log.i(TAG,"- findLoadedClass: "+clazz);
			if (clazz==null) {
				try {
					if (getParent()==null)
						clazz = ClassLoader.getSystemClassLoader().loadClass(className);
					else 
						clazz = getParent().loadClass(className);
				}
				catch (Exception e) {
					Log.i(TAG, "- Parent "+getParent()+" loadClass: "+clazz);
				}
			}
			if (clazz==null) {
				findClass(className);
			}
			if (clazz==null)
				throw new ClassNotFoundException(className+" in "+this);
			if (resolve)
				resolveClass(clazz);
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
			return loadClass(className, false);
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
			// make sure mvel won't try JIT (wouldn't work!)
			System.setProperty("mvel2.disable.jit", "true"); 
			
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
			Log.i(TAG, "System.properties = "+System.getProperties());
			// = "0" !!
			Log.i(TAG, "java.version = "+System.getProperty("java.version"));
			if(System.getProperty("java.version")==null || System.getProperty("java.version").length()<3) {
				System.setProperty("java.version", "1.5"); // ?!
				Log.i(TAG, "java.version = "+System.getProperty("java.version"));
			}
			Log.i(TAG, "GeoPosition: "+Class.forName("uk.ac.horizon.ug.hyperplace.facts.GeoPosition", true, cl));
			// NB KnowledgeBase must be written to DroolsObjectOutputStream
			// NB requires bug fix to org.drools.util.CompositeClassLoader constructor to 
			// not pass null parent class loader]
			// See dalvik.system.DexClassLoader for loading Dex class files from filesystem.
			// Note: will need to run DX on generated class files on server.
			/*
			 
I/dalvikvm(  278): Could not find method java.beans.Introspector.decapitalize, r
eferenced from method org.drools.core.util.asm.ClassFieldInspector.calcFieldName

W/dalvikvm(  278): VFY: unable to resolve static method 118: Ljava/beans/Introsp
ector;.decapitalize (Ljava/lang/String;)Ljava/lang/String;
D/dalvikvm(  278): VFY: replacing opcode 0x71 at 0x0004
D/dalvikvm(  278): Making a copy of Lorg/drools/core/util/asm/ClassFieldInspecto
r;.calcFieldName code (36 bytes)
*/
			
			//.setText("Hello "+System.currentTimeMillis());	
			ois = new DroolsObjectInputStream( this.getResources().openRawResource(R.raw.hyperplace), cl);
			KnowledgeBase kb = (KnowledgeBase)ois.readObject();
			ois.close();
			Log.i(TAG, "Read "+kb.getClass().getName()+" "+kb);
			text.setText("Read "+kb.getClass().getName()+" "+kb);
			
			// create a session
			StatefulKnowledgeSession ks = kb.newStatefulKnowledgeSession();
			text.setText(text.getText()+"\nCreated session: "+ks);
			
			// add a fact!
			ClientConversation cc = new ClientConversation();
			cc.setClientId("1234");
			cc.setClientType("Hyperplace");
			cc.setConversationId("c123");
			cc.setSessionId("s123");
			cc.setCreationTime(System.currentTimeMillis());
			cc.setStatus(ConversationStatus.ACTIVE);
			ks.insert(cc);
			text.setText(text.getText()+"\nAdded fact: "+cc);
			
			ks.fireAllRules();
			text.setText(text.getText()+"\nFired rules");
			
			Collection<Object> facts = ks.getObjects();
			StringBuffer sb = new StringBuffer();
			sb.append("Facts: ");
			for (Object fact : facts) {
				sb.append(fact);
				sb.append("; ");
			}
			text.setText(text.getText()+"\n"+sb.toString());
		}
		catch (Exception e) {
			Log.e(TAG,"trying drools", e);
			if (ois!=null) {
				Log.i(TAG, "Stream ClassLoader = "+ois.getClassLoader()+", parent="+ois.getParentClassLoader());
			}
		}
	}

}
