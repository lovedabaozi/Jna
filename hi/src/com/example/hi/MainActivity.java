package com.example.hi;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	static {
		System.loadLibrary("jnitest");
		System.loadLibrary("wer");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView tv_view = (TextView) findViewById(R.id.tv_view);
		tv_view.setText(addtest(2,4)+"-");
	}

	public native int addtest(int x,int y);
}
