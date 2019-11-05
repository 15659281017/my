package com.example.calculator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;




public class MainActivity extends ActionBarActivity {
private GestureOverlayView gestureOverlayView2;
private ArrayList<String> list = new ArrayList<String>();
private Button button1,button2,button3;
private TextView equation;


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        equation = (TextView)findViewById(R.id.textView2);

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
	   				else if(prediction.name.equals("÷")){
	   					Toast.makeText(MainActivity.this, "您输入了符号 ÷！", Toast.LENGTH_SHORT).show();
	   					list.add("/");
	   				}
	   				else{
	   					Toast.makeText(MainActivity.this, "请输入手势！", Toast.LENGTH_SHORT).show();
	   					
	   				}
	   				equation.setText(list.toString().replace("[","").replace("]","").replace(",","").replace(" ", ""));
	   			}
			}
		});
        
        
    }
	
	
	class MyStack { 
		 public LinkedList ll=new LinkedList();
		 public void push(Object o){
			 ll.addFirst(o);
		 }
		 public Object pop(){
			 return ll.removeFirst();
		 }
		 public Object peek(){
			 return ll.getFirst();
		 }
		 public boolean empty(){
			 return ll.isEmpty();
		 }
	}
	
	public void onClick_calculate(View view){//计算
		double[] num = new double[50];
		int flag=0;
		int sign=0;
		double number=0;
		MyStack s = new MyStack();
		double result=0;
		
		for(int i=0; i<list.size(); i++){
			if(list.get(i)!="+"&&list.get(i)!="-"&&list.get(i)!="x"&&list.get(i)!="÷"){//计算成每个数
				num[i]=Integer.parseInt(list.get(i));
				flag++;
				
			}
			else{
				for(int j=i-1; j<flag+i-1; j++){//error
					number+=num[j]*Math.pow(10, flag-1-j);
				}
				s.push(number);
				Toast.makeText(MainActivity.this, s.peek().toString(), Toast.LENGTH_SHORT).show();
				
				if(sign==3){//乘除直接计算
					double a=Double.parseDouble(s.peek().toString());
					Toast.makeText(MainActivity.this, "a:"+s.peek().toString(), Toast.LENGTH_SHORT).show();
					s.pop();
					Toast.makeText(MainActivity.this, "di1:"+s.peek().toString(), Toast.LENGTH_SHORT).show();
					s.pop();
					Toast.makeText(MainActivity.this, "di2:"+s.peek().toString(), Toast.LENGTH_SHORT).show();
					double b=Double.parseDouble(s.peek().toString());
					Toast.makeText(MainActivity.this, "b:"+s.peek().toString(), Toast.LENGTH_SHORT).show();
					result=a*b;
					s.push(result);
					Toast.makeText(MainActivity.this, s.peek().toString(), Toast.LENGTH_SHORT).show();
				}
				if(sign==4){
					double a=Double.parseDouble(s.peek().toString());
					s.pop();
					s.pop();
					double b=Double.parseDouble(s.peek().toString());
					result=b/a;
					s.push(result);
					Toast.makeText(MainActivity.this, s.peek().toString(), Toast.LENGTH_SHORT).show();
				}
				
				
				s.push(list.get(i));
				Toast.makeText(MainActivity.this, s.peek().toString(), Toast.LENGTH_SHORT).show();
				switch (s.peek().toString()) {
					case "+":
						sign=1;
						break;
					case "-":
						sign=2;
						break;
					case "x":
						sign=3;
						break;
					case "÷":
						sign=4;
						break;
	
					default:
						break;
				}
				
				flag=0;
				number=0;
			}
		}
	
	
	}
	
	public void onClick_back(View view){//删掉list最后一个元素
		int i = list.size()-1;
		list.remove(i);
		equation.setText(list.toString().replace("[","").replace("]","").replace(",","").replace(" ", ""));
	}
	
	public void onClick_empty(View view){//清空list
		list.clear();
		equation.setText(null);
	}
}
