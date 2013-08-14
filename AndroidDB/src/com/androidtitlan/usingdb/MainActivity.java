package com.androidtitlan.usingdb;

import java.util.ArrayList;

import com.androidtitlan.db.AndroidtitlanDBAdapter;
import com.androidtitlan.utility.MessageAdapter;
import com.androidtitlan.utility.Messages;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;

public class MainActivity extends ListActivity {

	private EditText editTextMessage;
	private ArrayList<Messages> messages;
	private MessageAdapter messageAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ListView lv = getListView();
        lv.setTextFilterEnabled(false);
        
        editTextMessage = (EditText) findViewById(R.id.messageText);
        
        this.getListView().setLongClickable(true);
        this.getListView().setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				performLongClick(arg2);
				return false;
			}
		});
        
        refreshMessageList();
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	refreshMessageList();
    }
    
    private void refreshMessageList(){
    	
    	AndroidtitlanDBAdapter db = new AndroidtitlanDBAdapter(getApplicationContext());
    	db.open();
    	
    	messages = db.getMyMessages();

    	db.close();
    	messageAdapter = new MessageAdapter(this, messages);
    	setListAdapter(messageAdapter);
    	messageAdapter.notifyDataSetChanged();
    	
    }
    
    public void onSendClick(View view){
    	String message = editTextMessage.getText().toString();
    	sendMessage(message);
    }
    
    private void sendMessage(String message){
    	
    	if(message.length() <=0 || message.equals("")){
			Toast.makeText(getApplicationContext(), "Escribe algo por favor...", Toast.LENGTH_LONG).show();
		}else{
			Messages msg = new Messages();
			msg.message = message;
			msg.timeStamp = String.valueOf(System.currentTimeMillis());
			
			AndroidtitlanDBAdapter db = new AndroidtitlanDBAdapter(getApplicationContext());
			db.open();
			db.insertNewMessage(msg);
			db.close();
			
			
			messageAdapter.add(msg);
			refreshMessageList();
			messageAdapter.notifyDataSetChanged();
			
			editTextMessage.setText("");
		}
    }
    
    private void performLongClick(int pos){
    	Log.d("longclick  ok!",""+pos);
		//menu items
		final CharSequence[] items = {"Borrar Mensaje","Actualizar Mensaje"};
		final int position = pos;
		
		//build the list dialog
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setItems(items, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
		    	if(item == 0){
		    		Messages message = (Messages) messageAdapter.getItem(position);
		    		
		    		if(message.timeStamp !=null){
		    			deleteMessage(message.timeStamp);
		    		}else{
		    			Log.d("Nada", "null");
		    		}
		    	}else{
		    		Messages message = (Messages) messageAdapter.getItem(position);
		    		
		    		if(message.timeStamp !=null){
		    			updateMessage(message.timeStamp);
		    		}else{
		    			Log.d("Nada", "null");
		    		}
		    		
		    	}
		    }
		});
		
		AlertDialog alert = builder.create();
		alert.show();
    }
    
    public void deleteMessage(String date){
    	AndroidtitlanDBAdapter db = new AndroidtitlanDBAdapter(getApplicationContext());
    	db.open();
    	db.deleteMessageByDate(date);
    	db.close();
    	refreshMessageList();
    	messageAdapter.notifyDataSetChanged();
    }
    
    public void updateMessage(String date){
    	AndroidtitlanDBAdapter db = new AndroidtitlanDBAdapter(getApplicationContext());
    	db.open();
    	db.updateMessageByDate(date);
    	db.close();
    	refreshMessageList();
    	messageAdapter.notifyDataSetChanged();
    }
}
