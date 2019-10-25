package com.example.durgshop;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
/**
 * 管理员留言
 * */
public class AmessActivity extends Activity {
	private ListView lv;
    private DBAdapter dbAdepter;
    private Appmess[] amess;
    private TextView uname,cont; 
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amess);
        dbAdepter = new DBAdapter(this);
        dbAdepter.open();
        init();
    }

    private void init() {
    	amess = dbAdepter.queryAllAppmessge();
        if(amess != null){
	        lv = (ListView)findViewById(R.id.ameslist);
	        lv.setAdapter(new BaseAdapter() {
	            /*
	             * 为ListView设置一个适配器
	             * getCount()返回数据个数
	             * getView()为每一行设置一个条目
	             * */
	            @Override
	            public int getCount() {
	                return amess.length;
	            }
	 
	            @Override
	            public Object getItem(int position) {
	                return null;
	            }
	 
	            @Override
	            public long getItemId(int position) {
	                return 0;
	            }
	 
	            @Override
	            public View getView(int position, View convertView, ViewGroup parent) {
	 
	                View view ;
	                /*对ListView的优化，convertView为空时，创建一个新视图；
	                 * convertView不为空时，代表它是滚出,
	                 * 放入Recycler中的视图,若需要用到其他layout，
	                 * 则用inflate(),同一视图，用fiindViewBy()
	                 * **/  
	                if(convertView == null)
	                {
	                    LayoutInflater inflater = AmessActivity.this.getLayoutInflater();
	                    convertView = inflater.inflate(R.layout.amess,null);
	                    uname = (TextView)convertView.findViewById(R.id.tv_uname_amess);
	                    cont = (TextView)convertView.findViewById(R.id.tv_cont_amess);
	                    view = convertView; 
	                }
	                else
	                {
	                     view = convertView;
	                }
	                
	                //position相当于数组下标,可以实现逐行取数据
	                Users[] users = dbAdepter.queryUserbyid(amess[position].uid);
	                uname.setText(String.valueOf(users[0].name));
	                cont.setText(amess[position].cont);
	                return convertView;
	            }
	        });
        }
        else{
        	Intent intent1 = new Intent(this,NullActivity.class);
			startActivity(intent1);
        }
    } 
}