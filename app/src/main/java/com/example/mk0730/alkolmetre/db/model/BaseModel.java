package com.example.mk0730.alkolmetre.db.model;

import android.net.Uri;

public class BaseModel {
    public static final String CONTENT_AUTHORITY = "com.example.mk0730.alkolmetre";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
}
