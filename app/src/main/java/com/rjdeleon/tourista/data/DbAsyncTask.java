package com.rjdeleon.tourista.data;

import android.os.AsyncTask;

public abstract class DbAsyncTask<T> extends AsyncTask<T, Void, Void> {

    protected AppDao mAsyncAppDao;

    protected DbAsyncTask(AppDao appDao) {
        mAsyncAppDao = appDao;
    }
}
