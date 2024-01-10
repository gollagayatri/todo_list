package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapplication.Adapter.ToDoAdapter;
import com.example.myapplication.Model.ToDoModel;
import com.example.myapplication.Utils.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogCloseListener{
    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;
    private FloatingActionButton fab;
    private List<ToDoModel> taskList;
    private DatabaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


           getSupportActionBar().hide();
           db=new DatabaseHandler(this);
           db.openDatabase();
        taskList=new ArrayList<>();
        tasksRecyclerView =findViewById(R.id.tasksRecyclerView);
        Context context;
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter=new ToDoAdapter(db,this);
        taskList = db.getAllTasks();

        // Reverse the list to display the newest tasks first
        Collections.reverse(taskList);

        // Set tasks in the adapter
        tasksAdapter.setTasks(taskList);

        tasksRecyclerView.setAdapter(tasksAdapter);
        fab=findViewById(R.id.fab);
        ItemTouchHelper itemTouchHelper=new
                ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);
        Log.d("MainActivity", "Task list size: " + taskList.size());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(),AddNewTask.TAG);
            }
        });


    }
    @Override
    public void handleDialogClose(DialogInterface dialog){
        taskList=db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);
        tasksAdapter.notifyDataSetChanged();


    }
}