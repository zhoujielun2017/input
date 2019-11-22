package com.input.qz.input;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.input.qz.input.bean.Config;
import com.input.qz.input.dao.ConfigDao;
import com.input.qz.input.dao.DaoMaster;
import com.input.qz.input.dao.DaoSession;
import com.input.qz.input.bean.English;
import com.input.qz.input.dao.EnglishDao;
import com.input.qz.input.constants.ConfigInstance;

import org.greenrobot.greendao.database.Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

public class MyApp extends Application {

    public static final String TAG="MyAPP";
    public static MyApp instance;
    private SQLiteDatabase db;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        copyDataBase("input.db");

        setDatabase();
        initConfigSetting();
    }

    public static MyApp getInstance(){
        return instance;
    }

    private void setDatabase() {
        Log.i(TAG,"setDatabase");
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        // mHelper = new DaoMaster.DevOpenHelper(this, "test.db", null);
        DBHelper mHelper = new DBHelper(this, "input.db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        DaoMaster mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    private void initConfigSetting(){
        try {
            Log.i(TAG,"initConfigSetting");
            DaoSession daoSession = getDaoSession();
            ConfigDao configDao = daoSession.getConfigDao();
            List<Config> configs = configDao.queryRaw(null,null,null);
            ConfigInstance.getInstance().init(configs);
        } catch (Exception e) {
            Log.e(TAG,"initConfigSetting error",e);
        }
    }



    private void copyDataBase(String dbname) {
        InputStream myInput = null;
        OutputStream myOutput = null;
        try {

            // Path to the just created empty db
            File outFileName = this.getDatabasePath(dbname);
            Log.i(TAG,outFileName.getAbsolutePath());
            Log.i(TAG,outFileName.exists()+"");
            if (!outFileName.exists()) {
                outFileName.getParentFile().mkdirs();
                // Open your local db as the input stream
                myInput = this.getAssets().open(dbname);
                // Open the empty db as the output stream
                myOutput = new FileOutputStream(outFileName);
                // transfer bytes from the inputfile to the outputfile
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

            }
        } catch (IOException e) {
            Log.e(TAG,"",e);
        } finally {
            // Close the streams
            try {
                if(null!=myOutput&&null!=myInput){
                    myOutput.flush();
                    myOutput.close();
                    myInput.close();
                }
            } catch (IOException e) {
                Log.e(TAG,"",e);
            }
        }


    }
}


class DBHelper extends DaoMaster.OpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);

    }
}