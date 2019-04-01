package com.example.myapplication;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {

    EditText id, name, amount;
    TextView listitem;
    DB_Controller controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        amount = findViewById(R.id.amount);
        listitem = findViewById(R.id.list);
        controller=new DB_Controller(this,"",null,1);


    }

    public void btn_click(View view) {
        switch (view.getId()){
            case R.id.add:
                try{
                    controller.add(id.getText().toString(),name.getText().toString(),amount.getText().toString());
                }catch (SQLException e){
                    Toast.makeText(MainActivity.this,"ALREADY EXISTS",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.delete:
                controller.delete(name.getText().toString());
                break;
            case R.id.update:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("ENTER NEW NAME ");

                final EditText new_name = new EditText(this);
                dialog.setView(new_name);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        controller.update(name.getText().toString(),new_name.getText().toString());
                    }
                });
                dialog.show();

                break;
            case R.id.read:
                controller.list(listitem);
                break;
        }
    }
}