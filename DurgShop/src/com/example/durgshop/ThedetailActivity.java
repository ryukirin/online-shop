package com.example.durgshop;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 药品详情页面
 * */
public class ThedetailActivity extends Activity {
    private ListView lv;
	private String durgname,uid,dprice,did;
	private DBAdapter dbAdepter;
	private Button addcart;
	private TextView username,cont;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thedetail);
		
        uid = Datauser.userid;
        System.out.println(uid);
        did = Datadurg.durgid;
        System.out.println(did);
        durgname = Datadurg.durgname;
        System.out.println(durgname);
        dprice = Datadurg.durgprice;
        System.out.println(dprice);
        TextView name = (TextView)findViewById(R.id.tv_dname_thedetail);
        TextView price  = (TextView)findViewById(R.id.tv_dprice_thedetail);
        name.setText(durgname);
        price.setText(dprice+"元");
		addcart = (Button)findViewById(R.id.bt_addcart_thedetail);
		addcart.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Datauser.userid = uid;
		        System.out.println(Datauser.userid);
				Paiddurg buy = new Paiddurg();
				buy.setDid(did);
				buy.setUid(uid);
				long result = dbAdepter.addpaid(buy);
				String msg = "购买" + (result>0?"成功":"失败");
				Toast.makeText(ThedetailActivity.this, msg, Toast.LENGTH_SHORT).show();
				Intent intent1 = new Intent(ThedetailActivity.this,TabActivity.class);
				startActivity(intent1);
			}
		});
        dbAdepter = new DBAdapter(this);
        dbAdepter.open();
        init();
	}
	
	private void init() {
        final Dmess[] mess = dbAdepter.queryDmessge(did);
        if(mess != null){
	        lv = (ListView)findViewById(R.id.durgcommlist);
	        lv.setAdapter(new BaseAdapter() {
	            /*
	             * 为ListView设置一个适配器
	             * getCount()返回数据个数
	             * getView()为每一行设置一个条目
	             * */
	            @Override
	            public int getCount() {
	                return mess.length;
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
	                if(convertView == null )
	                {
	                	LayoutInflater inflater = ThedetailActivity.this
								.getLayoutInflater();
						convertView = inflater.inflate(R.layout.comment, null);
		                username = (TextView)convertView.findViewById(R.id.tv_uname_comm);
		                cont = (TextView)convertView.findViewById(R.id.tv_cont_comm);
		                view = convertView;
	                }
	                else
	                {
	                     view = convertView;
	                }
	                //position相当于数组下标,可以实现逐行取数据
	                Users[] user = dbAdepter.queryUserbyid(mess[position].uid);
	                username.setText(user[0].name);
	                cont.setText(mess[position].cont);
	                return view;
	            }
	        });
        }
        else{
        	Toast.makeText(ThedetailActivity.this, "该药品还没有评价", Toast.LENGTH_SHORT).show();
        }
    }
}
