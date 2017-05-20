package braedynkenzie.sqliteaddressapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

public class NewContact extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    EditText phoneNumber;
    EditText emailAddress;
    EditText homeAddress;

    DBTools dbTools = new DBTools(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_contact);

        firstName = (EditText) findViewById(R.id.firstNameEditText);
        lastName = (EditText) findViewById(R.id.lastNameEditText);
        phoneNumber = (EditText) findViewById(R.id.phoneNumberEditText);
        emailAddress = (EditText) findViewById(R.id.emailEditText);
        homeAddress = (EditText) findViewById(R.id.homeAddressEditText);

    }

    public void addNewContact(View view){
        HashMap<String, String> queryValuesHashMap = new HashMap<String, String>();

        queryValuesHashMap.put("firstName", firstName.getText().toString());
        queryValuesHashMap.put("lastName", lastName.getText().toString());
        queryValuesHashMap.put("phoneNumber", phoneNumber.getText().toString());
        queryValuesHashMap.put("emailAddress", emailAddress.getText().toString());
        queryValuesHashMap.put("homeAddress", homeAddress.getText().toString());

        dbTools.insertContact(queryValuesHashMap);

        this.callMainActivity(view);

    }

    public void callMainActivity(View view){
        Intent theIntent = new Intent(getApplication(), MainActivity.class);
        startActivity(theIntent);

    }

}
