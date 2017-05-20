package braedynkenzie.sqliteaddressapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

/**
 * Created by Braedyn Kenzie on 2017-05-19.
 *
 */

public class EditContact extends AppCompatActivity{
    EditText firstName;
    EditText lastName;
    EditText phoneNumber;
    EditText emailAddress;
    EditText homeAddress;

    DBTools dbTools = new DBTools(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_contact);

        firstName = (EditText) findViewById(R.id.firstNameEditText);
        lastName = (EditText) findViewById(R.id.lastNameEditText);
        phoneNumber = (EditText) findViewById(R.id.phoneNumberEditText);
        emailAddress = (EditText) findViewById(R.id.emailEditText);
        homeAddress = (EditText) findViewById(R.id.homeAddressEditText);

        Intent theIntent = getIntent();
        String contactID = theIntent.getStringExtra("contactID");

        HashMap<String, String> contactMap = dbTools.getContactInfo(contactID);

        if(contactMap.size() != 0){
            firstName.setText(contactMap.get("firstName"));
            lastName.setText(contactMap.get("lastName"));
            phoneNumber.setText(contactMap.get("phoneNumber"));
            emailAddress.setText(contactMap.get("emailAddress"));
            homeAddress.setText(contactMap.get("homeAddress"));
        }

    }

    public void editContact(View view){
        HashMap<String, String> queryValuesMap = new HashMap<String, String>();

        firstName = (EditText) findViewById(R.id.firstNameEditText);
        lastName = (EditText) findViewById(R.id.lastNameEditText);
        phoneNumber = (EditText) findViewById(R.id.phoneNumberEditText);
        emailAddress = (EditText) findViewById(R.id.emailEditText);
        homeAddress = (EditText) findViewById(R.id.homeAddressEditText);

        Intent theIntent = getIntent();
        String contactID = theIntent.getStringExtra("contactID");

        queryValuesMap.put("contactID", contactID);
        queryValuesMap.put("firstName", firstName.getText().toString());
        queryValuesMap.put("lastName", lastName.getText().toString());
        queryValuesMap.put("phoneNumber", phoneNumber.getText().toString());
        queryValuesMap.put("homeAddress", homeAddress.getText().toString());

        dbTools.updateContact(queryValuesMap);
        this.callMainActivity(view);

    }

    public void removeContact(View view){
        Intent theIntent = getIntent();
        String contactID = theIntent.getStringExtra("contactID");
        dbTools.deleteContact(contactID);
        this.callMainActivity(view);
    }

    public void callMainActivity(View view){
        Intent theIntent = new Intent(getApplication(), MainActivity.class);
        startActivity(theIntent);

    }
}
