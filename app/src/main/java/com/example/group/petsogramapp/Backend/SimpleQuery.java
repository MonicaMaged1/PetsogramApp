package com.example.group.petsogramapp.Backend;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class SimpleQuery extends FirebaseQuery
{
    private String collectionName;
    private String fieldName;
    private String Operator;
    private Object valueToQuery;

    public SimpleQuery(String collectionName, String fieldName, String Operator, Object valueToQuery)
    {
        this.collectionName = collectionName;
        this.fieldName = fieldName;
        this.Operator = Operator;
        this.valueToQuery = valueToQuery;
    }

    @Override
    public Query build()
    {
        Query executingQuery = null;
        FirebaseFirestore databaseService = DatabaseManager.getInstance().getDatabaseService();
        CollectionReference collection = databaseService.collection(collectionName);

        switch(Operator)
        {
            case "=":
                executingQuery = collection.whereEqualTo(fieldName, valueToQuery);
                break;

            case ">":
                executingQuery = collection.whereGreaterThan(fieldName, valueToQuery);
                break;

            case "<":
                executingQuery = collection.whereLessThan(fieldName, valueToQuery);
                break;

            case ">=":
                executingQuery = collection.whereGreaterThanOrEqualTo(fieldName, valueToQuery);
                break;

            case "<=":
                executingQuery = collection.whereLessThanOrEqualTo(fieldName, valueToQuery);
                break;

            case "!=":
                executingQuery = collection.whereLessThan(fieldName, valueToQuery).whereGreaterThan(fieldName, valueToQuery);
                break;

            default:
                break;
        }

        return executingQuery;
    }

    @Override
    public void addQuery(SimpleQuery Query) {}

    public String getCollectionName() {return collectionName;}

    public String getFieldName() {return fieldName;}

    public String getOperator() {return Operator;}

    public Object getValueToQuery() {return valueToQuery;}
}
