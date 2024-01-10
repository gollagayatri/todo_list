OVERVIEW:
The To-Do List Application is a comprehensive task management tool that adheres to the specified requirements.
Users can efficiently manage tasks by assigning due dates, priority levels, and categories. 
The app supports the status tracking of tasks (New, In Progress, Completed) and provides a clean and responsive user interface for a seamless experience.
Thetasks are stored locally using SQLite database.

SETUP:
1.Prerequisites:
  Make sure you have the follolwing installed on your system:
  1:Android studio
  2:Java Development Kit(JDK)
2:Installation:
      1.clone the repository using the link or download the code
      2.Open the project in Android Studio
      3.Build the project to ensure all dependencies are downloaded

USAGE:
   1.Launch the application on a android device by using wifi or code
   2.The main screen displays a list of tasks and "+" symbol to add the new tasks
   3.On clicking that + symbol we can add the task and "set a date" as a remainder to complete the task and then save the task
   4.After saving the task on main screen we can select wether the task is a "NEW" task or "in-progress" task or "completed"task
   5.And on the main screen we can add the description to the task and also can categorize the task.
   6.We can also prioritize the task as MEDIUM,HIGH AND LOW.
   7.To DELETE a task we can swipe to left and to EDIT the task we can swipe right.
PROJECT STRUCTURE:
MainActivity.java:Handles the main screen with the task list
ToDoAdapter.java:Handles the deleting and editing the tasks
ToDoModel.java:It defines the blueprint for creating and managing to-do tasks within the application and  It encapsulates the attributes and behaviors associated with a to-do task, such as id (task ID), status (task status), task (task description or name), priority (task priority), and related getter and setter methods.
DatabaseHandler.java: It is responsible for managing a SQLite database used to store and retrieve to-do tasks for a to-do list application.
AddNewTask.java:For adding new tasks in a to-do list application.The code handles user input, date picking, and task saving functionalities, and interacts with a SQLite database through a DatabaseHandler to perform operations like inserting, updating, and retrieving tasks. 
RecyclerItemTouchHelper:For handling swipe gestures in a RecyclerView in the context of a to-do list application. 
SplashActivity.java:This code defines a SplashActivity in Android, which serves as a splash screen that is displayed when the app is launched. 
ACKNOWLEDGMENTS:
This project utilizes the SQLLite fir local storage and follows Android Development for best practices.



