package com.example.arbabkhan.databaseintegration;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import DTOs.StudentInfo;

import static Helpers.MediaFetcher.PICK_FROM_CAMERA;
import static Helpers.MediaFetcher.PICK_FROM_GALLARY;

public class UserAuthenticationActivity extends AppCompatActivity {

    EditText txtUserName, txtSurname, txtMarks;
    Button btnInsertInfo, btnShowAll;
    DatabaseHelper db;
    public static List arr_StudentInfo;
    public Uri outPutfileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_authentication);
        this.db = new DatabaseHelper(this);
        Log.d("User Auth Activity", "onCreate: " + this.getApplicationInfo().dataDir);

        this.btnShowAll = (Button)findViewById(R.id.btnShowAll);
        this.btnInsertInfo = (Button)findViewById(R.id.btnInsertInfo);
        this.txtUserName =  (EditText) findViewById(R.id.txtUsername);
        this.txtSurname =  (EditText) findViewById(R.id.txtSurname);
        this.txtMarks =  (EditText) findViewById(R.id.txtMarks);
        AddData();
        getAllData();
    }

    public void AddData() {
        btnInsertInfo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                      boolean isInserted = db.insertData(txtUserName.getText().toString(), txtSurname.getText().toString(), txtMarks.getText().toString());
                      if (isInserted) {
                          Toast.makeText(UserAuthenticationActivity.this, "Data Inserted Successfully!",Toast.LENGTH_LONG).show();
                      }
                      else {
                          Toast.makeText(UserAuthenticationActivity.this, "Error occurred while inserting data!",Toast.LENGTH_LONG).show();
                      }

                    }
                }
        );
    }

    private void getDebugger() {
        String s = "getting debugger";
    }
    public void getAllData() {

        btnShowAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getDebugger();
                        arr_StudentInfo = db.getAllStudentsData();
                        if (arr_StudentInfo.size() == 0) {
                            showMessage("Error", "No Data Found!");
                            return;
                        }
                        else {

                            UserBioDataActivity userBio = new UserBioDataActivity();
                            userBio.list_RecyclerResource = arr_StudentInfo;

                            Intent intent = new Intent(UserAuthenticationActivity.this, UserBioDataActivity.class);
                            startActivity(intent);
                        }

                    }
                }
        );
    }

    public void showMessage(String strTitle, String strMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(strTitle);
        builder.setMessage(strMessage);
        builder.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap=null;
        switch (requestCode) {

            case PICK_FROM_CAMERA:

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                Bitmap bitmap1 = BitmapFactory.decodeFile(mPath, options);



                if (resultCode == Activity.RESULT_OK) {
                    //pic coming from camera

                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), outPutfileUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;

            case PICK_FROM_GALLARY:

                if (resultCode == Activity.RESULT_OK) {
                    //pick image from gallery
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };

                    // Get the cursor
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String imgDecodableString = cursor.getString(columnIndex);
                    cursor.close();
                    bitmap = BitmapFactory.decodeFile(imgDecodableString);
                }
                break;
        }
    }

}
