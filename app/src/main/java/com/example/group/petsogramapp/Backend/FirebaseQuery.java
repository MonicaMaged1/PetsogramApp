package com.example.group.petsogramapp.Backend;

import com.google.firebase.firestore.Query;

public abstract class FirebaseQuery
{
    public abstract Query build();
    public abstract void addQuery(SimpleQuery Query);
}
