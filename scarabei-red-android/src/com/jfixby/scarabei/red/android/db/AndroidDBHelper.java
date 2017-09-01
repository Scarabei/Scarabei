
package com.jfixby.scarabei.red.android.db;

import com.jfixby.scarabei.android.api.Android;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AndroidDBHelper extends SQLiteOpenHelper {
	static final int DATABASE_VERSION = 5;

	public AndroidDBHelper (final String dbName) {
		super(Android.getApplicationContext(), dbName, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate (final SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade (final SQLiteDatabase db, final int oldVersion, final int newVersion) {

	}

}
