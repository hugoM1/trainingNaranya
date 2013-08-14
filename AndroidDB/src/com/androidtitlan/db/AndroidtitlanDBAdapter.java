package com.androidtitlan.db;


import com.androidtitlan.utility.Messages;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class AndroidtitlanDBAdapter {
	// Nombre de nuestra BD
	public static final String DATABASE_NAME = "androidtitlan";
	// Version de nuestra BD
	public static final int DATABASE_VERSION = 1;

	// Variable para mantener la instancia de la BD
	private SQLiteDatabase db;
	// Contexto de la appliacion para la BD
	private final Context context;
	// Heredamos de SQLiteOpenHelper
	// Helper para crear y actualizar DB
	private AndroidtitlanDBHelper dbHelper;
	
	// Instancia para saber si hay conexion con la BD 
	private boolean _isConnected = false;

	//CONSTRUCTOR
	public AndroidtitlanDBAdapter(Context _context) {
		context = _context;
		dbHelper = new AndroidtitlanDBHelper(context);
	}

	// Abrir conexion con BD
	public  void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
		_isConnected = true;
	}

	// Cerrar conexion con BD
	public void close() {
		db.close();
		_isConnected = false;
	}
	
	
	public boolean isConnected()  {
		return _isConnected;
	}
	
	// Insertar nuevo mensaje
	//metodo privado
		private void _insertNewMessage(ContentValues cv){
			Log.d("InsertMessage", cv.toString());
			db.insert("messages", null, cv);
		}

		//metodo publico
		public void insertNewMessage(Messages message){

			ContentValues cv = new ContentValues();

			cv.put("message",message.message);
			cv.put("date", message.timeStamp);
			

			Log.d("PublicInsertMessage", cv.toString());

			_insertNewMessage(cv);
		}
		
		// Recuperar los mensajes ya guardados
		public ArrayList<Messages> getMyMessages(){

			ArrayList<Messages> output = new ArrayList<Messages>();
			Cursor result =	db.query("messages", null, null, null, null, null, "date ASC");
			if(result.moveToFirst()){
				do{
					Messages nuevo = new Messages();
					nuevo.message = result.getString(1);
					nuevo.timeStamp = result.getString(2);
					output.add(nuevo);
					
				}while(result.moveToNext());
			}
			result.close();
			return output;	
		}
		
		public void deleteMessageByDate(String date){
			db.delete("messages", "date = "+ date, null);
		}
		
		// Vaciar la tabla
		public void emptyMessages(){
			db.delete("messages", null, null);
		}
		
		// Actualizar mensaje
		public void updateMessageByDate(String date){
			ContentValues cv = new ContentValues();
			String value = String.valueOf(System.currentTimeMillis());
			cv.put("date", value);
			db.update("messages", cv, "date = " + date, null);
		}
	
		// Helper
	private static class AndroidtitlanDBHelper extends SQLiteOpenHelper{
		public AndroidtitlanDBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			
		}
		
		// Crea  la BD
		@Override
		public void onCreate(SQLiteDatabase db) {
			
			db.execSQL("create table messages"+
					"(_id integer primary key autoincrement,"+
						"message text,"+
						"date text);");
		}

		// Actualiza BD
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
			db.execSQL("DROP TABLE IF EXISTS messages");
			onCreate(db);
		}
	}
}
