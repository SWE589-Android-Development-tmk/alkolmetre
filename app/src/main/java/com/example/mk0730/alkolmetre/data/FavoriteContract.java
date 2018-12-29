package com.example.mk0730.alkolmetre.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class FavoriteContract {

    public static final String CONTENT_AUTHORITY = "com.example.mk0730.alkolmetre";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_FAVORITE = "favorite";
    public static final String PATH_FAVORITE_DETAIL = "favoriteDetail";

    public static final class FavoriteEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_FAVORITE)
                .build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" +
                CONTENT_AUTHORITY + "/" + PATH_FAVORITE;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" +
                CONTENT_AUTHORITY + "/" + PATH_FAVORITE;

        public static final String TABLE_NAME = "favorite";

        public static final String COLUMN_API_ID = "apiId";
        public static final String COLUMN_IMAGE_URL = "imageUrl";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ORIGIN = "origin";

        public static Uri buildFavoriteUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        // This method sends a request to get API ID from DB.
        public static Uri buildFavoriteWithAPIId(Integer apiID) {
            return CONTENT_URI.buildUpon()
                    .appendPath(COLUMN_API_ID)
                    .appendPath(String.valueOf(apiID))
                    .build();
        }
    }

    public static final class FavoriteDetailEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_FAVORITE_DETAIL)
                .build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" +
                CONTENT_AUTHORITY + "/" + PATH_FAVORITE;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" +
                CONTENT_AUTHORITY + "/" + PATH_FAVORITE;

        public static final String TABLE_NAME = "favoriteDetail";

        public static final String COLUMN_FAVORITE_ID = "favoriteId";
        public static final String COLUMN_TASTING_NOTE = "tastingNote";
        public static final String COLUMN_RELEASE_DATE = "releaseDate";
        public static final String COLUMN_SUGAR_CONTENT = "sugarContent";
        public static final String COLUMN_ALCOHOL_CONTENT = "alcoholContent";

        public static Uri buildFavoriteDetailUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildFavoriteWithFavoriteId(Integer favoriteId) {
            return CONTENT_URI.buildUpon()
                    .appendPath(COLUMN_FAVORITE_ID)
                    .appendPath(String.valueOf(favoriteId))
                    .build();
        }
    }
}
