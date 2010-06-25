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
			/* 
I/HelloAndroidMain(  509): Trying drools...
I/HelloAndroidMain(  509): ClassLoader: dalvik.system.PathClassLoader@43d027e8
I/HelloAndroidMain(  509): Main: class cmg.helloandroid.Main
I/HelloAndroidMain(  509): LatLng: class uk.me.jstott.jcoord.LatLng
I/HelloAndroidMain(  509): KnowledgeBase: interface org.drools.KnowledgeBase
I/HelloAndroidMain(  509): KnowledgeBaseImpl: class org.drools.impl.KnowledgeBas
eImpl
I/HelloAndroidMain(  509): RuleBaseConfiguration: class org.drools.RuleBaseConfi
guration
E/HelloAndroidMain(  509): trying drools
E/HelloAndroidMain(  509): java.lang.ClassNotFoundException: org.drools.RuleBase
Configuration
E/HelloAndroidMain(  509):      at java.lang.Class.classForName(Native Method)
E/HelloAndroidMain(  509):      at java.lang.Class.forName(Class.java:237)
E/HelloAndroidMain(  509):      at org.drools.common.DroolsObjectInputStream.res
olveClass(DroolsObjectInputStream.java:72)
E/HelloAndroidMain(  509):      at org.drools.common.DroolsObjectInputStream.res
olveClass(DroolsObjectInputStream.java:84)
E/HelloAndroidMain(  509):      at java.io.ObjectInputStream.readNewClassDesc(Ob
jectInputStream.java:1860)
E/HelloAndroidMain(  509):      at java.io.ObjectInputStream.readClassDesc(Objec
tInputStream.java:840)
E/HelloAndroidMain(  509):      at java.io.ObjectInputStream.readNewObject(Objec
tInputStream.java:2080)
E/HelloAndroidMain(  509):      at java.io.ObjectInputStream.readNonPrimitiveCon
tent(ObjectInputStream.java:943)
E/HelloAndroidMain(  509):      at java.io.ObjectInputStream.readObject(ObjectIn
putStream.java:2299)
E/HelloAndroidMain(  509):      at java.io.ObjectInputStream.readObject(ObjectIn
putStream.java:2254)
E/HelloAndroidMain(  509):      at org.drools.common.AbstractRuleBase.readExtern
al(AbstractRuleBase.java:262)
E/HelloAndroidMain(  509):      at org.drools.reteoo.ReteooRuleBase.readExternal
(ReteooRuleBase.java:221)
E/HelloAndroidMain(  509):      at org.drools.impl.KnowledgeBaseImpl.readExterna
l(KnowledgeBaseImpl.java:87)
E/HelloAndroidMain(  509):      at java.io.ObjectInputStream.readNewObject(Objec
tInputStream.java:2141)
E/HelloAndroidMain(  509):      at java.io.ObjectInputStream.readNonPrimitiveCon
tent(ObjectInputStream.java:943)
E/HelloAndroidMain(  509):      at java.io.ObjectInputStream.readObject(ObjectIn
putStream.java:2299)
E/HelloAndroidMain(  509):      at java.io.ObjectInputStream.readObject(ObjectIn
putStream.java:2254)
E/HelloAndroidMain(  509):      at cmg.helloandroid.Main.onCreate(Main.java:83)
E/HelloAndroidMain(  509):      at android.app.Instrumentation.callActivityOnCre
ate(Instrumentation.java:1047)
E/HelloAndroidMain(  509):      at android.app.ActivityThread.performLaunchActiv
ity(ActivityThread.java:2459)
E/HelloAndroidMain(  509):      at android.app.ActivityThread.handleLaunchActivi
ty(ActivityThread.java:2512)
E/HelloAndroidMain(  509):      at android.app.ActivityThread.access$2200(Activi
tyThread.java:119)
E/HelloAndroidMain(  509):      at android.app.ActivityThread$H.handleMessage(Ac
tivityThread.java:1863)
E/HelloAndroidMain(  509):      at android.os.Handler.dispatchMessage(Handler.ja
va:99)
E/HelloAndroidMain(  509):      at android.os.Looper.loop(Looper.java:123)
E/HelloAndroidMain(  509):      at android.app.ActivityThread.main(ActivityThrea
d.java:4363)
E/HelloAndroidMain(  509):      at java.lang.reflect.Method.invokeNative(Native
Method)
E/HelloAndroidMain(  509):      at java.lang.reflect.Method.invoke(Method.java:5
21)
E/HelloAndroidMain(  509):      at com.android.internal.os.ZygoteInit$MethodAndA
rgsCaller.run(ZygoteInit.java:860)
E/HelloAndroidMain(  509):      at com.android.internal.os.ZygoteInit.main(Zygot
eInit.java:618)
E/HelloAndroidMain(  509):      at dalvik.system.NativeStart.main(Native Method)

E/HelloAndroidMain(  509): Caused by: java.lang.NoClassDefFoundError: org.drools
.RuleBaseConfiguration
E/HelloAndroidMain(  509):      ... 31 more
E/HelloAndroidMain(  509): Caused by: java.lang.ClassNotFoundException: org.droo
ls.RuleBaseConfiguration
E/HelloAndroidMain(  509):      at java.lang.Class.classForName(Native Method)
E/HelloAndroidMain(  509):      at java.lang.Class.forName(Class.java:237)
E/HelloAndroidMain(  509):      at org.drools.rule.DroolsCompositeClassLoader.lo
adClass(DroolsCompositeClassLoader.java:75)
E/HelloAndroidMain(  509):      at java.lang.ClassLoader.loadClass(ClassLoader.j
ava:532)
E/HelloAndroidMain(  509):      ... 31 more
E/HelloAndroidMain(  509): Caused by: java.lang.NoClassDefFoundError: org.drools
.RuleBaseConfiguration
E/HelloAndroidMain(  509):      ... 35 more
E/HelloAndroidMain(  509): Caused by: java.lang.ClassNotFoundException: org.droo
ls.RuleBaseConfiguration in loader dalvik.system.PathClassLoader@4001b500
E/HelloAndroidMain(  509):      at dalvik.system.PathClassLoader.findClass(PathC
lassLoader.java:243)
E/HelloAndroidMain(  509):      at java.lang.ClassLoader.loadClass(ClassLoader.j
ava:573)
E/HelloAndroidMain(  509):      at java.lang.ClassLoader.loadClass(ClassLoader.j
ava:532)
E/HelloAndroidMain(  509):      ... 35 more
D/dalvikvm(  509): GC freed 3022 objects / 247856 bytes in 75ms			 */
			
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
