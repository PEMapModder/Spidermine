package quantumWorks.spidermine;

import java.io.File;

import android.os.Environment;

public class Utils {
	public static File getAppPath(){
		return new File(Environment.getExternalStorageDirectory(), "SpiderMine/");
	}
	public static File getServerDir(SpiderServer server){
		File f=new File(getAppPath(), server.getServerName());
		f.mkdirs();
		return f;
	}
}
