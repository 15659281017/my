package com.example.caculator;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
private GestureOverlayView gestureOverlayView2;
private ArrayList<String> list = new ArrayList<String>();

	TextView equation ;
	private Button del;
	private Button cl;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        del = (Button)findViewById(R.id.button2);
        cl = (Button)findViewById(R.id.button1);
        equation= (TextView)findViewById(R.id.textView2);
        
        gestureOverlayView2=(GestureOverlayView)findViewById(R.id.gestureOverlayView2);
        final GestureLibrary library = (GestureLibrary)GestureLibraries.fromRawResource(MainActivity.this, R.raw.gestures);
        library.load();
        
        gestureOverlayView2.addOnGesturePerformedListener(new OnGesturePerformedListener() {
			
			@Override
			public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
				// TODO Auto-generated method stub
				ArrayList<Prediction>predictions=library.recognize(gesture);//识别手势: 通过 library 读取手势文件 ，在这里读取
	   			Prediction prediction=predictions.get(0);//取到第一个
	   			
	   			if(prediction.score>=1.0){//更加相似度 来 取得 区间（0.0~10.0 大致区间）
	   				if(prediction.name.equals("1")){//通过name来判断值
	   					Toast.makeText(MainActivity.this, "您输入了数字 1！", Toast.LENGTH_SHORT).show();
	   					list.add("1");
	   				}
	   				else if(prediction.name.equals("2")){
	   					Toast.makeText(MainActivity.this, "您输入了数字 2！", Toast.LENGTH_SHORT).show();
	   					list.add("2");
	   				}
	   				else if(prediction.name.equals("3")){
	   					Toast.makeText(MainActivity.this, "您输入了数字 3！", Toast.LENGTH_SHORT).show();
	   					list.add("3");
	   				}
	   				else if(prediction.name.equals("4")){
	   					Toast.makeText(MainActivity.this, "您输入了数字 4！", Toast.LENGTH_SHORT).show();
	   					list.add("4");
	   				}
	   				else if(prediction.name.equals("5")){
	   					Toast.makeText(MainActivity.this, "您输入了数字 5！", Toast.LENGTH_SHORT).show();
	   					list.add("5");
	   				}
	   				else if(prediction.name.equals("6")){
	   					Toast.makeText(MainActivity.this, "您输入了数字 6！", Toast.LENGTH_SHORT).show();
	   					list.add("6");
	   				}
	   				else if(prediction.name.equals("7")){
	   					Toast.makeText(MainActivity.this, "您输入了数字 7！", Toast.LENGTH_SHORT).show();
	   					list.add("7");
	   				}
	   				else if(prediction.name.equals("8")){
	   					Toast.makeText(MainActivity.this, "您输入了数字 8！", Toast.LENGTH_SHORT).show();
	   					list.add("8");
	   				}
	   				else if(prediction.name.equals("9")){
	   					Toast.makeText(MainActivity.this, "您输入了数字 9！", Toast.LENGTH_SHORT).show();
	   					list.add("9");
	   				}
	   				else if(prediction.name.equals("0")){
	   					Toast.makeText(MainActivity.this, "您输入了数字 0！", Toast.LENGTH_SHORT).show();
	   					list.add("0");
	   				}
	   				else if(prediction.name.equals("+")){
	   					Toast.makeText(MainActivity.this, "您输入了符号 +！", Toast.LENGTH_SHORT).show();
	   					list.add("+");
	   				}
	   				else if(prediction.name.equals("-")){
	   					Toast.makeText(MainActivity.this, "您输入了符号 -！", Toast.LENGTH_SHORT).show();
	   					list.add("-");
	   				}
	   				else if(prediction.name.equals("x")){
	   					Toast.makeText(MainActivity.this, "您输入了符号 x！", Toast.LENGTH_SHORT).show();
	   					list.add("x");
	   				}
	   				else if(prediction.name.equals("/")){
	   					Toast.makeText(MainActivity.this, "您输入了符号 /！", Toast.LENGTH_SHORT).show();
	   					list.add("/");
	   				}
	   				else if(prediction.name.equals("a")){
	   					Toast.makeText(MainActivity.this, "您输入了数字 2！", Toast.LENGTH_SHORT).show();
	   					list.add("a");
	   				}
	   				else{
	   					Toast.makeText(MainActivity.this, "请输入手势！", Toast.LENGTH_SHORT).show();
	   					
	   				}
	   				equation.setText(list.toString().replace("[","").replace("]","").replace(",","").replace(" ", ""));
	   			}
			}
		});
    }
	public void clear_click(View view){
		list.clear();
		equation.setText(list.toString().replace("[","").replace("]","").replace(",","").replace(" ", ""));
	}
	
	public void delete_click(View view){
		int i = list.size()-1;
		list.remove(i);
		equation.setText(list.toString().replace("[","").replace("]","").replace(",","").replace(" ", ""));
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
