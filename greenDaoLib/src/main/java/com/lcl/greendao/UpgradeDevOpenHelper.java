package com.lcl.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.lcl.greendao.dao.DaoMaster;

/**
 * 自定义数据库升级的DevOpenHelper
 * Created by zhouL on 2016/12/27.
 */
public class UpgradeDevOpenHelper extends DaoMaster.DevOpenHelper {

    public UpgradeDevOpenHelper(Context context, String name) {
        super(context, name);
    }

    public UpgradeDevOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(GreenDaoManager.TAG, "Upgrading schema from version " + oldVersion + " to " + newVersion);
        if (oldVersion < newVersion) {//升级
            switch (oldVersion) {
                case 1:
                    version1to2(db);
                case 2:
                    version2to3(db);
                    break;
                case 3:
                    version3to4(db);
                    break;
                case 4:
                    version4to5(db);
                    break;
                default:
                    break;
            }
        }
    }
    private void version4to5(SQLiteDatabase db) {
    }

    private void version3to4(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE 'messagedata' ADD 'zdrybh' String");
    }

    /** 版本1升级到版本2 */
    private void version1to2(SQLiteDatabase db) {
        // 增加一个实体表
//        ClubDao.createTable(db, false);
        // 修改Note表
//        db.execSQL("ALTER TABLE 'User' ADD 'auther' String");

    }

    /** 版本2升级到版本3 */
    private void version2to3(SQLiteDatabase db) {
        //do something
    }
}
