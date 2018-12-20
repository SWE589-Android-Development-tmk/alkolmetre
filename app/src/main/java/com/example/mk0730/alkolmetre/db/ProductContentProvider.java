package com.example.mk0730.alkolmetre.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.mk0730.alkolmetre.db.model.ApiQuery;
import com.example.mk0730.alkolmetre.db.model.ApiQueryDao;
import com.example.mk0730.alkolmetre.db.model.ApiQueryResult;
import com.example.mk0730.alkolmetre.db.model.ApiQueryResultDao;
import com.example.mk0730.alkolmetre.db.model.BaseModel;
import com.example.mk0730.alkolmetre.db.model.DaoMaster;
import com.example.mk0730.alkolmetre.db.model.DaoSession;

import org.greenrobot.greendao.database.Database;

public class ProductContentProvider extends ContentProvider {
    private static final UriMatcher sUriMatcher = buildUriMatcher();
//    private AlcoholDbHelper mOpenHelper;

    public static final int API_QUERY_RESULT = 100;
    public static final int API_QUERY = 300;

    private DaoSession daoSession;
    private SQLiteDatabase database;

    public static UriMatcher buildUriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(BaseModel.CONTENT_AUTHORITY, ApiQuery.PATH, API_QUERY);
        matcher.addURI(BaseModel.CONTENT_AUTHORITY, ApiQueryResult.PATH, API_QUERY_RESULT);
        return matcher;
    }

    public ProductContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        /* Creating an instance of the DB */
        //mOpenHelper = new AlcoholDbHelper(getContext());

        // regular SQLite database
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "alcohol-db");
        Database db = helper.getWritableDb();
        database = helper.getReadableDatabase();

        daoSession = new DaoMaster(db).newSession();

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        final int match = sUriMatcher.match(uri);
        Cursor retCursor;
        switch (match) {
            case API_QUERY: {
                ApiQueryDao dao = daoSession.getApiQueryDao();

                /*String table, String[] columns, String selection,
                    String[] selectionArgs, String groupBy, String having,
                    String orderBy*/
                retCursor = database.query(dao.getTablename(), projection,
                        selection, selectionArgs, null, null, sortOrder);
            }
            break;
            case API_QUERY_RESULT: {
                ApiQueryResultDao dao = daoSession.getApiQueryResultDao();

                retCursor = database.query(dao.getTablename(),
                        projection, selection,
                        selectionArgs, null, null,
                        sortOrder);
            }
            break;
            default: {
                throw new UnsupportedOperationException("Unknown uri:" + uri);
            }
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
