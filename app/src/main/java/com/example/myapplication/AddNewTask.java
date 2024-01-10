package com.example.myapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import androidx.core.content.ContextCompat;

import com.example.myapplication.Model.ToDoModel;
import com.example.myapplication.Utils.DatabaseHandler;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;

public class AddNewTask extends BottomSheetDialogFragment {
    public static final String TAG = "ActionBottomDialog";
    private EditText newTaskText;
    private Spinner prioritySpinner;
    private Button newTaskSaveButton, newTasksSetDueDateButton;
    private Date dueDate;
    private DatabaseHandler db;
    private Calendar selectedDueDate;

    public static AddNewTask newInstance() {
        return new AddNewTask();
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        Bundle savedInstanceState = null;
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.DialogStyle);
       //  Spinner prioritySpinner = getView().findViewById(R.id.spinnerPriority);
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//                requireContext(), R.array.priority_options, android.R.layout.simple_spinner_item);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //getContext(R.layout.activity_add_new_task);

        // Assuming you have an EditText named editTextTask in your activity layout
        //EditText taskEditText = findViewById(R.id.editTextTask);
        // Apply the adapter to the spinner
        //prioritySpinner.setAdapter(adapter);
    }
    private void addTask() {
        BreakIterator taskNameEditText;
       // String taskName = taskNameEditText.getText().toString();
        String selectedPriority = prioritySpinner.getSelectedItem().toString();

        // Create a new ToDoModel instance and set task details
       // EditText taskEditText = findViewById(R.id.editTextTask);

        ToDoModel task = new ToDoModel();
      //  String selectedPriority = prioritySpinner.getSelectedItem().toString();
        task.setPriority(selectedPriority);

        // Save the task to the database using your DatabaseHandler
        DatabaseHandler databaseHandler;
       // databaseHandler.insertTask(task);

        // ... rest of the code
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_task, container,false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View newTaskSetDueDateButton;
        newTaskSetDueDateButton = getView().findViewById(R.id.newTaskSetDueDateButton);

        selectedDueDate = Calendar.getInstance();
        newTaskText = getView().findViewById(R.id.newTaskText);
        newTaskSaveButton = getView().findViewById(R.id.newTaskButton);
        db = new DatabaseHandler(getActivity());
        db.openDatabase();
        boolean isUpdate = false;
        final Bundle bundle = getArguments();
        if (bundle != null) {
            isUpdate = true;
            String task = bundle.getString("task");
            newTaskText.setText(task);
            if (task.length() > 0)
                newTaskSaveButton.setTextColor(ContextCompat.getColor(getContext(),R.color.colorPrimaryDark));
        }
        newTaskText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")) {
                    newTaskSaveButton.setEnabled(false);
                    newTaskSaveButton.setTextColor(Color.GRAY);
                }
                    else{
                        newTaskSaveButton.setEnabled(true);
                        newTaskSaveButton.setTextColor(ContextCompat.getColor(getContext(),R.color.colorPrimaryDark));
                    }
                }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        newTaskSetDueDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }

            private void showDatePickerDialog() {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                Calendar selectedDate = new GregorianCalendar(year, month, dayOfMonth);
                                String formattedDate = formatDate(selectedDate.getTime());
                                newTaskText.setText(formattedDate);
                                ToDoModel task = new ToDoModel();
                                task.setTask(newTaskText.getText().toString());
                                task.setStatus(0);
                                //task.setDueDate(formattedDate);
                                //task.setPriority(PriorityLevels.HIGH);
                                handleTaskInsertOrUpdate(task);
                            }

                            private void handleTaskInsertOrUpdate(ToDoModel task) {
                                db.insertTask(task);
                            }
                        },
                        year,
                        month,
                        dayOfMonth
                );
                datePickerDialog.show();
            }

                            private String formatDate(Date time) {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                return dateFormat.format(time);
                            }


    });
        newTaskSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = newTaskText.getText().toString();
                boolean isUpdate = false; // Corrected variable name
            if (bundle != null) {
                    isUpdate = true;
                }
                if (isUpdate) {
                    db.updateTask(bundle.getInt("id"), text);
                } else {
                    ToDoModel task = new ToDoModel();
                    task.setTask(text);
                    task.setStatus(0);

                    if (selectedDueDate != null) {
                        String formattedDate = formatDate(selectedDueDate.getTime());
                        newTaskText.setText(formattedDate);
                    }

                    db.insertTask(task);
                }

                dismiss();
            }

            private String formatDate(Date time) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                return sdf.format(time);
            }
        });
        }
    @Override
    public void onDismiss(DialogInterface dialog){

        Activity activity = getActivity();

        if(activity instanceof DialogCloseListener) {

            ((DialogCloseListener)activity).handleDialogClose(dialog);

        }

    }
}




