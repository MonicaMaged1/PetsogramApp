package com.example.group.petsogramapp.Backend;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.*;

public class AccountManager
{
    public static final int ERROR = -3;
    public static final int NONE = -2;
    public static final int SUCCESS = -1;

    public static final int NETWORK_ERROR = 0;

    //The custom token format is incorrect. Please check the documentation.
    public static final int INVALID_CUSTOM_TOKEN = 1;

    //The custom token corresponds to a different audience.
    public static final int CUSTOM_TOKEN_MISMATCH = 2;

    //The supplied auth credential is malformed or has expired.
    public static final int INVALID_CREDENTIAL = 3;

    //The email address is badly formatted.
    public static final int INVALID_EMAIL = 4;

    //The password is invalid or the user does not have a password.
    public static final int WRONG_PASSWORD = 5;

    //The supplied credentials do not correspond to the previously signed in user.
    public static final int USER_MISMATCH = 6;

    //This operation is sensitive and requires recent authentication.
    //Log in again before retrying this request.
    public static final int REQUIRES_RECENT_LOGIN = 7;

    //An account already exists with the same email address but different sign-in credentials.
    //Sign in using a provider associated with this email address.
    public static final int ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL = 8;

    //The email address is already in use by another account.
    public static final int EMAIL_ALREADY_IN_USE = 9;

    //This credential is already associated with a different user account.
    public static final int CREDENTIAL_ALREADY_IN_USE = 10;

    //The user account has been disabled by an administrator.
    public static final int USER_DISABLED = 11;

    //The user's credential is no longer valid. The user must sign in again.
    public static final int USER_TOKEN_EXPIRED = 12;

    //There is no user record corresponding to this identifier. The user may have been deleted.
    public static final int USER_NOT_FOUND = 13;

    //The user's credential is no longer valid. The user must sign in again.
    public static final int INVALID_USER_TOKEN = 14;

    //This operation is not allowed. You must enable this service in the console.
    public static final int OPERATION_NOT_ALLOWED = 15;

    //The given password is invalid.
    public static final int WEAK_PASSWORD = 16;


    private static AccountManager Instance;
    private Updatable Activity;
    private FirebaseAuth authenticationService;
    private int taskStatus;
    private int errorStatus;


    private AccountManager()
    {
        authenticationService = FirebaseAuth.getInstance();
        taskStatus = NONE;
        errorStatus = NONE;
    }

    public static AccountManager getInstance()
    {
        if(Instance == null)
            Instance = new AccountManager();

        return Instance;
    }

    public void setActivity(Updatable Activity){this.Activity = Activity;}

    public int getTaskStatus(){ return taskStatus; }

    public int getErrorStatus(){ return errorStatus; }

    public void handleError(Task task)
    {
        taskStatus = ERROR;
        try{throw task.getException();}
        catch(FirebaseNetworkException e){errorStatus = NETWORK_ERROR;}
        catch (Exception e)
        {
            String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
            setErrorStatus(errorCode);
        }
    }

    public void signUp(String emailAddress, String Password)
    {
        Task<AuthResult> signUpTask = authenticationService.createUserWithEmailAndPassword(emailAddress, Password);
        signUpTask.addOnCompleteListener(new onTaskComplete());
    }

    public void verifyEmail()
    {
        FirebaseUser currentUser = authenticationService.getCurrentUser();
        if(currentUser != null)
        {
            Task<Void> emailVerificationTask = currentUser.sendEmailVerification();
            emailVerificationTask.addOnCompleteListener(new onTaskComplete());
        }
    }

    public void signIn(String emailAddress, String Password)
    {
        Task<AuthResult> signInTask = authenticationService.signInWithEmailAndPassword(emailAddress, Password);
        signInTask.addOnCompleteListener(new onTaskComplete());
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
        passwordResetTask.addOnCompleteListener(new onTaskComplete());
    }

    public void changePassword(String newPassword)
    {
        FirebaseUser currentUser = authenticationService.getCurrentUser();
        if(currentUser != null)
        {
            Task<Void> passwordChangeTask = currentUser.updatePassword(newPassword);
            passwordChangeTask.addOnCompleteListener(new onTaskComplete());
        }
    }

    public void changeEmail(String newEmail)
    {
        FirebaseUser currentUser = authenticationService.getCurrentUser();
        if(currentUser != null)
        {
            Task<Void> emailChangeTask = currentUser.updateEmail(newEmail);
            emailChangeTask.addOnCompleteListener(new onTaskComplete());
        }
    }

    public void reAuthenticateUser(String emailAddress, String Password)
    {
        FirebaseUser currentUser = authenticationService.getCurrentUser();
        if(currentUser != null)
        {
            AuthCredential userCredentials = EmailAuthProvider.getCredential(emailAddress, Password);
            Task<Void> reauthenticationTask = currentUser.reauthenticate(userCredentials);
            reauthenticationTask.addOnCompleteListener(new onTaskComplete());
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
            case "ERROR_INVALID_CUSTOM_TOKEN":
                errorStatus = INVALID_CUSTOM_TOKEN;
                break;

            case "ERROR_CUSTOM_TOKEN_MISMATCH":
                errorStatus = CUSTOM_TOKEN_MISMATCH;
                break;

            case "ERROR_INVALID_CREDENTIAL":
                errorStatus = INVALID_CREDENTIAL;
                break;

            case "ERROR_INVALID_EMAIL":
                errorStatus = INVALID_EMAIL;
                break;

            case "ERROR_WRONG_PASSWORD":
                errorStatus = WRONG_PASSWORD;
                break;

            case "ERROR_USER_MISMATCH":
                errorStatus = USER_MISMATCH;
                break;

            case "ERROR_REQUIRES_RECENT_LOGIN":
                errorStatus = REQUIRES_RECENT_LOGIN;
                break;

            case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                errorStatus = ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL;
                break;

            case "ERROR_EMAIL_ALREADY_IN_USE":
                errorStatus = EMAIL_ALREADY_IN_USE;
                break;

            case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                errorStatus = CREDENTIAL_ALREADY_IN_USE;
                break;

            case "ERROR_USER_DISABLED":
                errorStatus = USER_DISABLED;
                break;

            case "ERROR_USER_TOKEN_EXPIRED":
                errorStatus = USER_TOKEN_EXPIRED;
                break;

            case "ERROR_USER_NOT_FOUND":
                errorStatus = USER_NOT_FOUND;
                break;

            case "ERROR_INVALID_USER_TOKEN":
                errorStatus = INVALID_USER_TOKEN;
                break;

            case "ERROR_OPERATION_NOT_ALLOWED":
                errorStatus = OPERATION_NOT_ALLOWED;
                break;

            case "ERROR_WEAK_PASSWORD":
                errorStatus = WEAK_PASSWORD;
                break;

            default:
                break;
        }
    }

    private class onTaskComplete implements OnCompleteListener
    {
        @Override
        public void onComplete(@NonNull Task task)
        {
            if(task.isSuccessful())
            {
                taskStatus = SUCCESS;
                errorStatus = NONE;
            }

            else
                handleError(task);
            Activity.updateUIFromAuthentication();
        }
    }
}
