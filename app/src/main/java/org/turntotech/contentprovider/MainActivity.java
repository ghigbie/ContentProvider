/*
 * Sample application of content provider to access SQLite.
 * A content provider component supplies data from one application to others on request. 
 * Such requests are handled by the methods of the ContentResolver class. 
 * A content provider can use different ways to store its data and the data can be stored
 *  in a database, in files, or even over a network. 
 */


package org.turntotech.contentprovider;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
       Log.i("TurnToTech", "Project Name - ContentProvider-SQLite");
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }

   public void onClickAddName(View view) {
      // Add a new student record
	   
      ContentValues values = new ContentValues();//Creates an empty set of values using the default initial size 

      //Adds a value to the set.
      values.put(StudentsProvider.NAME, ((EditText)findViewById(R.id.txtName)).getText().toString());
      
      values.put(StudentsProvider.GRADE, ((EditText)findViewById(R.id.txtGrade)).getText().toString());

      values.put(StudentsProvider.AGE, ((EditText)findViewById(R.id.txtAge)).getText().toString());

      Uri uri = getContentResolver().insert(StudentsProvider.CONTENT_URI, values);
      
      Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
   }

   public void onClickRetrieveStudents(View view) {
      // Retrieve student records
      String URL = "content://org.turntotech.contentprovider.StudentsProvider/students";
      Uri students = Uri.parse(URL);
      Cursor cursor = getContentResolver().query(students, null, null, null, "name");
      if (cursor.moveToFirst()) {
         do{
            Toast.makeText(this, 
            cursor.getString(cursor.getColumnIndex(StudentsProvider._ID)) +
            ", " +  cursor.getString(cursor.getColumnIndex( StudentsProvider.NAME)) +
            ", " + cursor.getString(cursor.getColumnIndex( StudentsProvider.GRADE)) +
                    ", " + cursor.getString(cursor.getColumnIndex( StudentsProvider.AGE)),
            Toast.LENGTH_SHORT).show();
         } while (cursor.moveToNext());
      }
   }
}
