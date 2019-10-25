package com.example.durgshop;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 用户管理
 * */
public class UsermanagerActivity extends Activity {
	private ListView lv;
    private DBAdapter dbAdepter;
    private Users[] users;
    private Button deleteuser;
    private TextView uid,uname,upwd; 
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermanager);
        dbAdepter = new DBAdapter(this);
        dbAdepter.open();
        init();
    }

    private void init() {
    	users = dbAdepter.queryAllData();
        if(users != null){
	        lv = (ListView)findViewById(R.id.userlist);
	        lv.setAdapter(new BaseAdapter() {
	            /*
	             * 为ListView设置一个适配器
	             * getCount()返回数据个数
	             * getView()为每一行设置一个条目
	             * */
	            @Override
	            public int getCount() {
	                return users.length;
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
	                /**对ListView的优化，convertView为空时，创建一个新视图；
	                 * convertView不为空时，代表它是滚出,
	                 * 放入Recycler中的视图,若需要用到其他layout，
	                 * 则用inflate(),同一视图，用fiindViewBy()
	                 * **/  
	                if(convertView == null)
	                {
	                    LayoutInflater inflater = UsermanagerActivity.this.getLayoutInflater();
	                    convertView = inflater.inflate(R.layout.users,null);
	                    deleteuser = (Button) convertView.findViewById(R.id.bt_udelete_users);
	                    uid = (TextView)convertView.findViewById(R.id.tv_uid_users);
	                    upwd = (TextView)convertView.findViewById(R.id.tv_upwd_users);
	                    uname = (TextView)convertView.findViewById(R.id.tv_uname_users);
	                    view = convertView; 
	                }
	                else
	                {
	                     view = convertView;
	                }
	                //position相当于数组下标,可以实现逐行取数据
	                uid.setText(String.valueOf(users[position].id));
	                uname.setText(users[position].name);
	                upwd.setText(users[position].password);
	                deleteuser.setTag(position);
	                deleteuser.setOnClickListener(new OnClickListener() {
	        			public void onClick(View v) {
	        				int pos = (Integer) v.getTag();
	        				String id = String.valueOf(users[pos].id);
	        				long result = dbAdepter.deleteOneData(id);
	        				String msg = "删除ID为"+id+"的数据" + (result>0?"成功":"失败");
	        				Toast.makeText(UsermanagerActivity.this, msg, Toast.LENGTH_SHORT).show();
	        			}
	        		});
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