package com.example.group.petsogramapp;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.firestore.*;

public class DatabaseManager
{
    public static final int ERROR = -3;
    public static final int NONE = -2;
    public static final int SUCCESS = -1;

    public static final int NETWORK_ERROR = 0;

    //The operation was cancelled (typically by the caller).
    public static final int CANCELLED = 1;

    //Unknown error or an error from a different error domain.
    public static final int UNKNOWN = 2;

    //Client specified an invalid argument. Note that this differs from FAILED_PRECONDITION.
    //INVALID_ARGUMENT indicates arguments that are problematic regardless of the state of the system (e.g., an invalid field name).
    public static final int INVALID_ARGUMENT = 3;

    //Deadline expired before operation could complete. For operations that change the state of the system,
    //this error may be returned even if the operation has completed successfully. For example,
    //a successful response from a server could have been delayed long enough for the deadline to expire.
    public static final int DEADLINE_EXCEEDED = 4;

    //Some requested document was not found.
    public static final int NOT_FOUND = 5;

    //Some document that we attempted to create already exists.
    public static final int ALREADY_EXISTS = 6;

    //The caller does not have permission to execute the specified operation.
    public static final int PERMISSION_DENIED = 7;

    //Some resource has been exhausted, perhaps a per-user quota, or perhaps the entire file system is out of space.
    public static final int RESOURCE_EXHAUSTED = 8;

    //Operation was rejected because the system is not in a state required for the operation's execution.
    public static final int FAILED_PRECONDITION = 9;

    //The operation was aborted, typically due to a concurrency issue like transaction aborts, etc.
    public static final int ABORTED = 10;

    //Operation was attempted past the valid range.
    public static final int OUT_OF_RANGE = 11;

    //Operation is not implemented or not supported/enabled.
    public static final int UNIMPLEMENTED = 12;

    //Internal errors. Means some invariants expected by underlying system has been broken.
    //If you see one of these errors, something is very broken.
    public static final int INTERNAL = 13;

    //The service is currently unavailable.
    // This is a most likely a transient condition and may be corrected by retrying with a backoff.
    public static final int UNAVAILABLE = 14;

    //Unrecoverable data loss or corruption.
    public static final int DATA_LOSS = 15;

    //The request does not have valid authentication credentials for the operation.
    public static final int UNAUTHENTICATED = 16;


    private static DatabaseManager Instance;
    private Updatable Activity;
    private FirebaseFirestore databaseService;
    private int taskStatus;
    private int errorStatus;

    private DatabaseManager(Updatable Activity)
    {
        databaseService = FirebaseFirestore.getInstance();
        this.Activity = Activity;
        taskStatus = NONE;
        errorStatus = NONE;
    }

    public static DatabaseManager getInstance(Updatable Activity)
    {
        if(Instance == null)
            Instance = new DatabaseManager(Activity);

        return Instance;
    }

    public void setActivity(Updatable Activity){this.Activity = Activity;}

    public int getTaskStatus(){ return taskStatus; }

    public int getErrorStatus(){ return errorStatus; }

    public void add(String collectionName, Document document)
    {
        CollectionReference collection = databaseService.collection(collectionName);
        Task<DocumentReference> addTask = collection.add(document);
        addTask.addOnSuccessListener(new onTaskSuccessfulListener(document));
        addTask.addOnFailureListener(new onTaskFailedListener());
    }

    private class onTaskSuccessfulListener implements OnSuccessListener<DocumentReference>
    {
        private Document document;

        public onTaskSuccessfulListener(Document document) {this.document = document;}

        @Override
        public void onSuccess(DocumentReference documentReference)
        {
            taskStatus = SUCCESS;
            errorStatus = NONE;
            String documentID = documentReference.getId();
            documentReference.update("id", documentID);
            document.setID(documentID);
            Activity.updateUI();
        }
    }

    private class onTaskFailedListener implements OnFailureListener
    {
        @Override
        public void onFailure(@NonNull Exception e)
        {
            taskStatus = ERROR;
            try{throw e;}
            catch(FirebaseNetworkException exception){errorStatus = NETWORK_ERROR;}
            catch (Exception exception)
            {
                FirebaseFirestoreException.Code errorCode = ((FirebaseFirestoreException) exception).getCode();
                errorStatus = errorCode.value();
            }
        }
    }
}
