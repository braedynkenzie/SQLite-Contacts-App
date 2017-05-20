package braedynkenzie.sqliteaddressapp;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {

    Intent intent;
    TextView contactID;
    DBTools dbTools = new DBTools(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<HashMap<String, String>> contactList = dbTools.getAllContacts();

        if(contactList.size() != 0){
            ListView listView = getListView();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    contactID = (TextView) findViewById(R.id.contactID);
                    String contactIDValue = contactID.getText().toString();

                    Intent theIntent = new Intent(getApplication(), EditContact.class);
                    theIntent.putExtra("contactID", contactIDValue);
                    startActivity(theIntent);

                }
            });
        }

        ListAdapter listAdapter = new SimpleAdapter(MainActivity.this, contactList,
                R.layout.contact_entry, new String[] {"contactID", "lastName", "firstName"},
                new int[] {R.id.contactID, R.id.lastNameEditText, R.id.firstNameEditText});

        setListAdapter(listAdapter);

    }

    public void showAddContact(View view){
        Intent theIntent = new Intent(getApplication(), NewContact.class);
        startActivity(theIntent);
    }
}

