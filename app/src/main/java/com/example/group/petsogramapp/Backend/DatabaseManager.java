package com.example.group.petsogramapp.Backend;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.firestore.*;
import java.util.ArrayList;

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
    public Document foundDocument;
    public ArrayList<Document> foundDocuments;

    private DatabaseManager()
    {
        databaseService = FirebaseFirestore.getInstance();
        foundDocument = null;
        foundDocuments = new ArrayList<>();
        taskStatus = NONE;
        errorStatus = NONE;
    }

    public static DatabaseManager getInstance()
    {
        if(Instance == null)
            Instance = new DatabaseManager();

        return Instance;
    }

    public void setActivity(Updatable Activity){this.Activity = Activity;}

    public FirebaseFirestore getDatabaseService(){ return databaseService; }

    public int getTaskStatus(){ return taskStatus; }

    public int getErrorStatus(){ return errorStatus; }

    public void handleError(Task task)
    {
        taskStatus = ERROR;
        try{throw task.getException();}
        catch(FirebaseNetworkException exception){errorStatus = NETWORK_ERROR;}
        catch (Exception exception)
        {
            FirebaseFirestoreException.Code errorCode = ((FirebaseFirestoreException) exception).getCode();
            errorStatus = errorCode.value();
        }
    }

    public Document getFoundDocument(){ return foundDocument; }

    public ArrayList<Document> getFoundDocuments(){ return foundDocuments; }

    public void addDocument(String collectionName, Document document)
    {
        CollectionReference collection = databaseService.collection(collectionName);
        Task<DocumentReference> addTask = collection.add(document);
        addTask.addOnCompleteListener(new onAddTaskComplete(document));
    }

    public void deleteDocument(String collectionName, String documentID)
    {
        CollectionReference collection = databaseService.collection(collectionName);
        DocumentReference document = collection.document(documentID);
        Task<Void> deleteTask = document.delete();
        deleteTask.addOnCompleteListener(new onDeleteTaskComplete());
    }

    public void findDocument(String collectionName, String documentID, String className)
    {
        CollectionReference collection = databaseService.collection(collectionName);
        DocumentReference document  = collection.document(documentID);
        Task<DocumentSnapshot> findTask = document.get();
        findTask.addOnCompleteListener(new onFindTaskComplete(className));
    }

    public void executeQuery(FirebaseQuery Query, String className)
    {
        Query executingQuery = Query.build();
        Task<QuerySnapshot> queryTask = executingQuery.get();
        queryTask.addOnCompleteListener(new onQueryTaskComplete(className));
    }

    private class onAddTaskComplete implements OnCompleteListener<DocumentReference>
    {
        private Document document;

        public onAddTaskComplete(Document document) {this.document = document;}

        @Override
        public void onComplete(@NonNull Task<DocumentReference> task)
        {
            if(task.isSuccessful())
            {
                DocumentReference documentReference = task.getResult();
                taskStatus = SUCCESS;
                errorStatus = NONE;
                String documentID = documentReference.getId();
                documentReference.update("id", documentID);
                document.setID(documentID);
                Activity.updateUI();
            }

            else
                handleError(task);
        }
    }

    private class onDeleteTaskComplete implements OnCompleteListener<Void>
    {

        @Override
        public void onComplete(@NonNull Task<Void> task)
        {
            if(task.isSuccessful())
            {
                taskStatus = SUCCESS;
                errorStatus = NONE;
                Activity.updateUI();
            }

            else
                handleError(task);
        }
    }

    private class onFindTaskComplete implements OnCompleteListener<DocumentSnapshot>
    {
        private String className;

        public onFindTaskComplete(String className){this.className = className;}

        @Override
        public void onComplete(@NonNull Task<DocumentSnapshot> task)
        {
            if(task.isSuccessful())
            {
                DocumentSnapshot documentSnapshot = task.getResult();

                switch(className)
                {
                    case "User":
                        foundDocument = documentSnapshot.toObject(User.class);
                        break;

                    default:
                        break;
                }

                Activity.updateUI();
            }

            else
                handleError(task);
        }
    }

    private class onQueryTaskComplete implements OnCompleteListener<QuerySnapshot>
    {
        private String className;

        public onQueryTaskComplete(String className){this.className = className;}

        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task)
        {
            if(task.isSuccessful())
            {
                QuerySnapshot queryDocumentSnapshots = task.getResult();
                foundDocuments.clear();

                switch(className)
                {
                    case "User":
                        for (QueryDocumentSnapshot Document : queryDocumentSnapshots)
                        {
                            User foundUser = Document.toObject(User.class);
                            foundDocuments.add(foundUser);
                        }
                        break;

                    default:
                        break;
                }

                Activity.updateUI();
            }

            else
                handleError(task);
        }
    }
}
