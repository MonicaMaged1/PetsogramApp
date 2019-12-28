package com.example.group.petsogramapp.Backend;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.storage.*;

public class StorageManager
{
    public static final int ERROR = -3;
    public static final int NONE = -2;
    public static final int SUCCESS = -1;

    public static final int NETWORK_ERROR = 0;

    //An unknown error occurred.
    public static final int UNKNOWN = -13000;

    //No object exists at the desired reference.
    public static final int OBJECT_NOT_FOUND = -13010;

    //No bucket is configured for Cloud Storage.
    public static final int BUCKET_NOT_FOUND = -13011;

    //No project is configured for Cloud Storage.
    public static final int PROJECT_NOT_FOUND = -13012;

    //Quota on your Cloud Storage bucket has been exceeded.
    //If you're on the free tier, upgrade to a paid plan.
    //If you're on a paid plan, reach out to Firebase support.
    public static final int QUOTA_EXCEEDED = -13013;

    //User is unauthenticated, please authenticate and try again.
    public static final int NOT_AUTHENTICATED = -13020;

    //User is not authorized to perform the desired action, check your rules to ensure they are correct.
    public static final int NOT_AUTHORIZED = -13021;

    //The maximum time limit on an operation (upload, download, delete, etc.) has been excceded. Try again.
    public static final int RETRY_LIMIT_EXCEEDED = -13030;

    //File on the client does not match the checksum of the file received by the server. Try uploading again.
    public static final int INVALID_CHECKSUM = -13031;

    //User canceled the operation.
    public static final int CANCELED = -13040;

    private static StorageManager Instance;
    private Updatable Activity;
    private FirebaseStorage storageService;
    private int taskStatus;
    private int errorStatus;

    private StorageManager()
    {
        storageService = FirebaseStorage.getInstance();
        taskStatus = NONE;
        errorStatus = NONE;
    }

    public static StorageManager getInstance()
    {
        if(Instance == null)
            Instance = new StorageManager();

        return Instance;
    }

    public void setActivity(Updatable Activity){this.Activity = Activity;}

    public FirebaseStorage getStorageService(){ return storageService; }

    public int getTaskStatus(){ return taskStatus; }

    public int getErrorStatus(){ return errorStatus; }

    public void handleError(Task task)
    {
        taskStatus = ERROR;
        try{throw task.getException();}
        catch(FirebaseNetworkException e){errorStatus = NETWORK_ERROR;}
        catch (Exception e)
        {
            errorStatus = ((StorageException) task.getException()).getErrorCode();
        }
    }
}
