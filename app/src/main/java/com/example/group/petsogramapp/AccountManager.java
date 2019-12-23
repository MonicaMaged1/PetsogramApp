package com.example.group.petsogramapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;

import javax.xml.transform.Templates;

public class AccountManager
{
    public static final int ERROR = -1;
    public static final int NEUTRAL = 0;
    public static final int SUCCESS = 1;

    public static final int INVALID_CUSTOM_TOKEN = 2;
    public static final int CUSTOM_TOKEN_MISMATCH = 3;
    public static final int INVALID_CREDENTIAL = 4;
    public static final int INVALID_EMAIL = 5;
    public static final int WRONG_PASSWORD = 6;
    public static final int USER_MISMATCH = 7;
    public static final int REQUIRES_RECENT_LOGIN = 8;
    public static final int ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL = 9;
    public static final int EMAIL_ALREADY_IN_USE = 10;
    public static final int CREDENTIAL_ALREADY_IN_USE = 11;
    public static final int USER_DISABLED = 12;
    public static final int USER_TOKEN_EXPIRED = 13;
    public static final int USER_NOT_FOUND = 14;
    public static final int INVALID_USER_TOKEN = 15;
    public static final int OPERATION_NOT_ALLOWED = 16;
    public static final int WEAK_PASSWORD = 17;

    private static AccountManager Instance;
    private Context referencingActivity;
    private FirebaseAuth authenticationService;
    private boolean isTaskComplete;
    private int taskStatus;
    private int errorStatus;


    private AccountManager(Context context)
    {
        authenticationService = FirebaseAuth.getInstance();
        referencingActivity = context;
        isTaskComplete = false;
        taskStatus = NEUTRAL;
        errorStatus = NEUTRAL;
    }

    public static AccountManager getInstance(Context context)
    {
        if(Instance == null)
            Instance = new AccountManager(context);

        return Instance;
    }

    public void setContext(Context context)
    {
        referencingActivity = context;
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

    public void signUp(String emailAddress, String Password)
    {
        Task<AuthResult> signUpTask = authenticationService.createUserWithEmailAndPassword(emailAddress, Password);
        signUpTask.addOnCompleteListener(new onTaskCompleteListener());
    }

    public void verifyEmail()
    {
        FirebaseUser currentUser = authenticationService.getCurrentUser();
        if(currentUser != null)
        {
            Task<Void> emailVerificationTask = currentUser.sendEmailVerification();
            emailVerificationTask.addOnCompleteListener(new onTaskCompleteListener());
        }
    }

    public void signIn(String emailAddress, String Password)
    {
        Task<AuthResult> signInTask = authenticationService.signInWithEmailAndPassword(emailAddress, Password);
        signInTask.addOnCompleteListener(new onTaskCompleteListener());
    }

    public void signOut()
    {
        FirebaseUser currentUser = authenticationService.getCurrentUser();
        if(currentUser != null)
            authenticationService.signOut();
    }

    public void resetPassword(String emailAddress)
    {
        Task<Void> passwordResetTask = authenticationService.sendPasswordResetEmail(emailAddress);
        passwordResetTask.addOnCompleteListener(new onTaskCompleteListener());
    }

    public void changePassword(String newPassword)
    {
        FirebaseUser currentUser = authenticationService.getCurrentUser();
        if(currentUser != null)
        {
            Task<Void> passwordChangeTask = currentUser.updatePassword(newPassword);
            passwordChangeTask.addOnCompleteListener(new onTaskCompleteListener());
        }
    }

    public void changeEmail(String newEmail)
    {
        FirebaseUser currentUser = authenticationService.getCurrentUser();
        if(currentUser != null)
        {
            Task<Void> emailChangeTask = currentUser.updateEmail(newEmail);
            emailChangeTask.addOnCompleteListener(new onTaskCompleteListener());
        }
    }

    public void reAuthenticateUser(String emailAddress, String Password)
    {
        FirebaseUser currentUser = authenticationService.getCurrentUser();
        if(currentUser != null)
        {
            AuthCredential userCredentials = EmailAuthProvider.getCredential(emailAddress, Password);
            Task<Void> reauthenticationTask = currentUser.reauthenticate(userCredentials);
            reauthenticationTask.addOnCompleteListener(new onTaskCompleteListener());
        }
    }

    public boolean isAccountVerified()
    {
        FirebaseUser currentUser = authenticationService.getCurrentUser();
        if(currentUser != null)
            return currentUser.isEmailVerified();

        return false;
    }

    private void setErrorStatus(String errorCode)
    {
        switch(errorCode)
        {
            //The custom token format is incorrect. Please check the documentation.
            case "ERROR_INVALID_CUSTOM_TOKEN":
                errorStatus = INVALID_CUSTOM_TOKEN;
                break;

            //The custom token corresponds to a different audience.
            case "ERROR_CUSTOM_TOKEN_MISMATCH":
                errorStatus = CUSTOM_TOKEN_MISMATCH;
                break;

            //The supplied auth credential is malformed or has expired.
            case "ERROR_INVALID_CREDENTIAL":
                errorStatus = INVALID_CREDENTIAL;
                break;

            //The email address is badly formatted.
            case "ERROR_INVALID_EMAIL":
                errorStatus = INVALID_EMAIL;
                break;

            //The password is invalid or the user does not have a password.
            case "ERROR_WRONG_PASSWORD":
                errorStatus = WRONG_PASSWORD;
                break;

            //The supplied credentials do not correspond to the previously signed in user.
            case "ERROR_USER_MISMATCH":
                errorStatus = USER_MISMATCH;
                break;

            //This operation is sensitive and requires recent authentication.
            // Log in again before retrying this request.
            case "ERROR_REQUIRES_RECENT_LOGIN":
                errorStatus = REQUIRES_RECENT_LOGIN;
                break;

            //An account already exists with the same email address but different sign-in credentials.
            // Sign in using a provider associated with this email address.
            case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                errorStatus = ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL;
                break;

            //The email address is already in use by another account.
            case "ERROR_EMAIL_ALREADY_IN_USE":
                errorStatus = EMAIL_ALREADY_IN_USE;
                break;

            //This credential is already associated with a different user account.
            case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                errorStatus = CREDENTIAL_ALREADY_IN_USE;
                break;

            //The user account has been disabled by an administrator.
            case "ERROR_USER_DISABLED":
                errorStatus = USER_DISABLED;
                break;

            //The user's credential is no longer valid. The user must sign in again.
            case "ERROR_USER_TOKEN_EXPIRED":
                errorStatus = USER_TOKEN_EXPIRED;
                break;

            //There is no user record corresponding to this identifier. The user may have been deleted.
            case "ERROR_USER_NOT_FOUND":
                errorStatus = USER_NOT_FOUND;
                break;

            //The user's credential is no longer valid. The user must sign in again.
            case "ERROR_INVALID_USER_TOKEN":
                errorStatus = INVALID_USER_TOKEN;
                break;

            //This operation is not allowed. You must enable this service in the console.
            case "ERROR_OPERATION_NOT_ALLOWED":
                errorStatus = OPERATION_NOT_ALLOWED;
                break;

            //The given password is invalid.
            case "ERROR_WEAK_PASSWORD":
                errorStatus = WEAK_PASSWORD;
                break;

            default:
                break;
        }
    }

    private class onTaskCompleteListener implements OnCompleteListener
    {
        @Override
        public void onComplete(@NonNull Task task)
        {
            isTaskComplete = true;
            if(task.isSuccessful())
            {
                taskStatus = SUCCESS;
                errorStatus = NEUTRAL;
            }

            else
            {
                String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                setErrorStatus(errorCode);
                taskStatus = ERROR;
            }
        }
    }
}
