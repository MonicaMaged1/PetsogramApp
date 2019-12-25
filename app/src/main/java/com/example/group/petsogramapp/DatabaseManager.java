package com.example.group.petsogramapp;

import android.content.Context;

import com.google.firebase.firestore.*;

public class DatabaseManager
{
    public static final int ERROR = -1;
    public static final int NEUTRAL = 0;
    public static final int SUCCESS = 1;

    private static DatabaseManager Instance;
    private FirebaseFirestore databaseService;
    private boolean isTaskComplete;
    private int taskStatus;
    private int errorStatus;

    private DatabaseManager()
    {
        databaseService = FirebaseFirestore.getInstance();
        isTaskComplete = false;
        taskStatus = NEUTRAL;
        errorStatus = NEUTRAL;
    }

    public static DatabaseManager getInstance()
    {
        if(Instance == null)
            Instance = new DatabaseManager();

        return Instance;
    }

    public boolean isTaskComplete()
    {
        if(isTaskComplete)
        {
            isTaskComplete = false;
            return true;
        }

        return false;
    }

    public int getTaskStatus(){ return taskStatus; }

    public int getErrorStatus(){ return errorStatus; }
}
