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
        Object valueToQuery = Query.getValueToQuery();

        if(executingQuery == null)
        {
            executingQuery = Query.build();
            return;
        }

        switch(Query.getOperator())
        {
            case "=":
                executingQuery = executingQuery.whereEqualTo(fieldName, valueToQuery);
                break;

            case ">":
                executingQuery = executingQuery.whereGreaterThan(fieldName, valueToQuery);
                break;

            case "<":
                executingQuery = executingQuery.whereLessThan(fieldName, valueToQuery);
                break;

            case ">=":
                executingQuery = executingQuery.whereGreaterThanOrEqualTo(fieldName, valueToQuery);
                break;

            case "<=":
                executingQuery = executingQuery.whereLessThanOrEqualTo(fieldName, valueToQuery);
                break;

            case "!=":
                executingQuery = executingQuery.whereLessThan(fieldName, valueToQuery).whereGreaterThan(fieldName, valueToQuery);
                break;

            default:
                break;
        }
    }
}
