package com.androidtitlan.utility;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.androidtitlan.usingdb.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class MessageAdapter extends BaseAdapter{
	
	private Context _ctx;
	private ArrayList<Messages> messages_list = new ArrayList<Messages>();
	
	public MessageAdapter(Context ctx, ArrayList<Messages> myMessages){
		_ctx = ctx;
		messages_list = myMessages;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return messages_list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return messages_list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	public void add(Messages item){
		messages_list.add(item);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// El view
		View rowView = convertView;
		
		Messages msg = messages_list.get(position);
		
		if(rowView == null){
			LayoutInflater vi = (LayoutInflater) _ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			rowView = vi.inflate(R.layout.gb_chat_list_item_me, null);
			
			//set the properties
	        TextView displayName = (TextView) rowView.findViewById(R.id.chat_msg);  
	        Log.w("message", msg.message);
	      //  Log.w("extras", code.extras);
	        displayName.setText(msg.message);
	        displayName.setVisibility(View.VISIBLE);
	        
	        TextView dateText = (TextView) rowView.findViewById(R.id.timestamp);
	        Date dieDate = new Date(Long.valueOf(msg.timeStamp));
	        
	        String dasDate = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.DEFAULT).format(dieDate);
	        dasDate = DateFormat.getTimeInstance(DateFormat.SHORT).format(dieDate);
	        
	        dateText.setText(dasDate);
	        
	        TextView user = (TextView) rowView.findViewById(R.id.tv_user_name);
	        user.setText("Me");
		}
		return rowView;
	}
}
