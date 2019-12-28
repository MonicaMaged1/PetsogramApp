package com.example.group.petsogramapp.Backend;

import com.google.firebase.firestore.Query;

public class CompoundQuery extends FirebaseQuery
{
    private Query executingQuery;

    @Override
    public Query build(){return executingQuery; }

    @Override
    public void addQuery(SimpleQuery Query)
    {
        String fieldName = Query.getFieldName();
        Object queryValue = Query.getQueryValue();

        if(executingQuery == null)
        {
            executingQuery = Query.build();
            return;
        }

        switch(Query.getOperator())
        {
            case "=":
                executingQuery = executingQuery.whereEqualTo(fieldName, queryValue);
                break;

            case ">":
                executingQuery = executingQuery.whereGreaterThan(fieldName, queryValue);
                break;

            case "<":
                executingQuery = executingQuery.whereLessThan(fieldName, queryValue);
                break;

            case ">=":
                executingQuery = executingQuery.whereGreaterThanOrEqualTo(fieldName, queryValue);
                break;

            case "<=":
                executingQuery = executingQuery.whereLessThanOrEqualTo(fieldName, queryValue);
                break;

            case "!=":
                executingQuery = executingQuery.whereLessThan(fieldName, queryValue).whereGreaterThan(fieldName, queryValue);
                break;

            default:
                break;
        }
    }
}
