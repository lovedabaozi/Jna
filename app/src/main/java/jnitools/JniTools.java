package jnitools;

public class JniTools {

	static {
		System.loadLibrary("wer");
		System.loadLibrary("jnitest");
	}
	  public  static native int  Add(int x,int y);
}