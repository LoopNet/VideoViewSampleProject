package com.example.videotest;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button b = (Button)this.findViewById(R.id.continueButton);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
					showVideoActivity();
			}
		});
		
		
	}
	
	void showVideoActivity(){
		EditText et = (EditText)this.findViewById(R.id.videoEditText);
		Intent i = new Intent(this, VideoActivity.class);
		i.putExtra("VIDEO_URL", et.getText().toString());
		this.startActivity(i);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
