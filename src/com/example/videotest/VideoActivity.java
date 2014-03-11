package com.example.videotest;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity implements OnCompletionListener,OnPreparedListener,OnTouchListener {

	public final static String VIDEO_URL_KEY = "VIDEO_URL";
	private VideoView _videoView;
	private MediaController _mediaController;
	private String _videoUrl;
	private Handler _handler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);	
		
		//offer any orientation for video play-back
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
		
		loadUrl();
		 
        _videoView = (VideoView)findViewById(R.id.myvideoview);
        
        //Use the built-in mediaController for this VideoView
        _mediaController = new MediaController(this);
        _mediaController.setAnchorView(_videoView);
        _mediaController.requestFocus();

        _videoView.setMediaController(_mediaController);
        _videoView.setOnCompletionListener(this);
        _videoView.setOnPreparedListener(this);
        _videoView.setOnTouchListener(this);        
        
 
        if (!playUrl(_videoUrl)) return;
 
        //_videoView.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.video, menu);
		return true;
	}
	
    protected void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        setIntent(intent);
        loadUrl();
        playUrl(_videoUrl);
    }
	
	private String loadUrl(){
		Bundle e = getIntent().getExtras();
		if (e!=null){
			_videoUrl = e.getString(VideoActivity.VIDEO_URL_KEY);
			return _videoUrl;
		}
		return null;
	}
             
    private boolean playUrl(String url){
        if (url == null || url.equals("null")){
            stopPlaying();
            return false;
        }else{
        	//vid.ly urls don't play
        	_videoView.setVideoURI(Uri.parse(url));
        	//this url plays no problem it seams
        	//_videoView.setVideoURI(Uri.parse("http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8"));
            return true;
        }
    }
 
    public void stopPlaying(){
        _videoView.stopPlayback();
        this.finish();             
    }
    
    @Override
    public void onCompletion(MediaPlayer mp){
        finish();
    }
             
    @Override
    public boolean onTouch(View v, MotionEvent event){
        //stopPlaying();
    	_mediaController.show();
        return true;
    }
 
    @Override
    public void onPrepared(MediaPlayer mp){
    	 
        

         
         _videoView.start();
        
/*         _handler.post(new Runnable(){
        	 public void run(){
        		 _mediaController.setEnabled(true);
        		 _mediaController.show();
             }
        });
        */
    }
}