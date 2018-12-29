package com.example.mk0730.alkolmetre.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.example.mk0730.alkolmetre.data.FavoriteContract.FavoriteEntry;
import com.example.mk0730.alkolmetre.data.FavoriteContract.FavoriteDetailEntry;

public class FavoriteProvider extends ContentProvider {
    public static final int FAVORITE = 100;
    public static final int FAVORITE_WITH_API_ID = 101;
    public static final int FAVORITE_DETAIL = 200;
    public static final int FAVORITE_DETAIL_WITH_FAVORITE_ID = 202;

    private static final String sFavoriteApiIdSelection =
            FavoriteEntry.TABLE_NAME + "." + FavoriteEntry.COLUMN_API_ID + " = ? ";
    private static final String sFavoriteDetailFavoriteIdSelection =
            FavoriteDetailEntry.TABLE_NAME + "." + FavoriteDetailEntry.COLUMN_FAVORITE_ID + " = ? ";

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private FavoriteDbHelper mOpenHelper;

    SQLiteDatabase db;

    public static UriMatcher buildUriMatcher() {

        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        String authority = FavoriteContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, FavoriteContract.PATH_FAVORITE, FAVORITE);
        matcher.addURI(authority, FavoriteContract.PATH_FAVORITE + "/*/#", FAVORITE_WITH_API_ID);
        matcher.addURI(authority, FavoriteContract.PATH_FAVORITE_DETAIL, FAVORITE_DETAIL);
        matcher.addURI(authority, FavoriteContract.PATH_FAVORITE_DETAIL + "/*/#", FAVORITE_DETAIL_WITH_FAVORITE_ID);

        return matcher;
    }

    private static final SQLiteQueryBuilder sFavoriteDetailsByFavoritesQueryBuilder;
    static{
        sFavoriteDetailsByFavoritesQueryBuilder = new SQLiteQueryBuilder();
        sFavoriteDetailsByFavoritesQueryBuilder.setTables(
                FavoriteEntry.TABLE_NAME + " INNER JOIN " +
                        FavoriteDetailEntry.TABLE_NAME +
                        " ON " + FavoriteDetailEntry.TABLE_NAME +
                        "." + FavoriteDetailEntry.COLUMN_FAVORITE_ID +
                        " = " + FavoriteEntry.TABLE_NAME +
                        "." + FavoriteEntry._ID);
    }

    public FavoriteProvider() {
    }


    @Override
    public boolean onCreate() {
        mOpenHelper = new FavoriteDbHelper(getContext());
        db = mOpenHelper.getWritableDatabase();
        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;

        if (null == selection) selection = "1";
        switch (match) {
            case FAVORITE_WITH_API_ID: {
                String apiId = uri.getPathSegments().get(2);
                if (apiId != null) {
                    selection = sFavoriteApiIdSelection;
                    selectionArgs = new String[]{apiId};
                }
                rowsDeleted = db.delete(FavoriteEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }
            case FAVORITE_DETAIL_WITH_FAVORITE_ID: {
                String favoriteId = uri.getPathSegments().get(2);
                if (favoriteId != null) {
                    selection = sFavoriteDetailFavoriteIdSelection;
                    selectionArgs = new String[]{favoriteId};
                }
                rowsDeleted = db.delete(FavoriteDetailEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }
            default: {
                throw new UnsupportedOperationException("Unknown uri:" + uri);
            }
        }
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {

        final int match = sUriMatcher.match(uri);

        switch (match) {
            case FAVORITE:
                return FavoriteEntry.CONTENT_TYPE;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case FAVORITE: {
                long _id = db.insert(FavoriteEntry.TABLE_NAME, null, values);

                if (_id > 0) {
                    returnUri = FavoriteEntry.buildFavoriteUri(_id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into" + uri);
                }
            }
            break;
            case FAVORITE_DETAIL: {
                long _id = db.insert(FavoriteDetailEntry.TABLE_NAME, null, values);

                if (_id > 0) {
                    returnUri = FavoriteDetailEntry.buildFavoriteDetailUri(_id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into" + uri);
                }
            }
            break;
            default: {
                throw new UnsupportedOperationException("Unknown uri:" + uri);
            }
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        final int match = sUriMatcher.match(uri);
        Cursor retCursor;

        switch (match) {
            case FAVORITE: {
                retCursor = sFavoriteDetailsByFavoritesQueryBuilder.query(db,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
            }
            break;
            case FAVORITE_WITH_API_ID: {
                String apiId = uri.getPathSegments().get(2);
                if (apiId != null) {
                    selection = sFavoriteApiIdSelection;
                    selectionArgs = new String[]{apiId};
                }
                retCursor = db.query(FavoriteEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
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
