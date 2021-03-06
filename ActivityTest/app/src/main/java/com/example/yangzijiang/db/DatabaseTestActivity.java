package com.example.yangzijiang.db;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mylibrary.ProcessPhoenix;
import com.example.yangzijiang.activitytest.LogUtil;
import com.example.yangzijiang.activitytest.R;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteException;

import java.io.File;
import java.io.IOException;

//import com.jakewharton.processphoenix.ProcessPhoenix;
//未加密数据库升级到加密数据库
public class DatabaseTestActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "DatabaseTestActivity";
    private final String SDcardPath = "/mnt/sdcard/";

    private Button mEncryptButton;
    private Button mOpenButton;

    private MyEncryptDatabaseHelper openHelper;

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_test);

        mEncryptButton = (Button) findViewById(R.id.bt_encry);
        mEncryptButton.setOnClickListener(this);
        mOpenButton = (Button) findViewById(R.id.bt_en_open_none_en);
        mOpenButton.setOnClickListener(this);

        Button button1 = (Button) findViewById(R.id.bt_create_none_endb);
        button1.setOnClickListener(this);

        Button button11 = (Button) findViewById(R.id.bt_create_endb);
        button11.setOnClickListener(this);

        Button button2 = (Button) findViewById(R.id.bt_restart_app);
        button2.setOnClickListener(this);

        Button button3 = (Button) findViewById(R.id.bt_delete_db);
        button3.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: ");
        switch (v.getId()){
            case R.id.bt_create_none_endb:
                createDB();
                break;
            case R.id.bt_create_endb:
                createEnDB();
                break;
            case R.id.bt_encry:  // 加密未加密数据库
                SQLiteDatabase.loadLibs(this);//引用SQLiteDatabase的方法之前必须先添加这句代码
//                encrypt("BookStoreTest.db","BookStore.db","1234");
                encryptDatabase("BookStore.db","1234", this);
                break;
            case R.id.bt_en_open_none_en:  // 直接用加密方式打开未加密数据库会报异常
                SQLiteDatabase.loadLibs(this);
                try{
                    openHelper.getWritableDatabase("1234");
                }catch (SQLiteException e){
                    if (e.getMessage().indexOf("file is encrypted or is not a database") >= 0){
                        encrypt("BookStoreTest.db","BookStore.db","1234");
                    }

                }
                break;
            case R.id.bt_restart_app:  // 重启应用
                ProcessPhoenix.triggerRebirth(this);
                break;
            case R.id.bt_delete_db:
                boolean result = this.getDatabasePath("BookStore.db").delete();
                LogUtil.e(TAG, "delete result = " + String.valueOf(result));
                Toast.makeText(this, "delete result = " + String.valueOf(result), Toast.LENGTH_SHORT).show();
                break;
        }
    }


    public void createDB(){
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);
        dbHelper.getWritableDatabase();
    }

    public void createEnDB(){
        SQLiteDatabase.loadLibs(this);
        openHelper = new MyEncryptDatabaseHelper(this, "BookStore.db", null, 1);
        openHelper.getWritableDatabase("1234");

    }
    /**
     * 加密未加密数据库，加密前后名称不一样
     * @param encryptedName 加密后的数据库名称
     * @param decryptedName 要加密的数据库名称
     * @param key 密码
     */
    private void encrypt(String encryptedName,String decryptedName,String key) {
        try {
//            File databaseFile = getDatabasePath(SDcardPath + decryptedName);  // 【在模拟器上，此路径openOrCreateDatabase会失败】
            File databaseFile = getDatabasePath(decryptedName);
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFile, "", null);//打开要加密的数据库

            /*String passwordString = "1234"; //只能对已加密的数据库修改密码，且无法直接修改为“”或null的密码
            database.changePassword(passwordString.toCharArray());*/

//            File encrypteddatabaseFile = getDatabasePath(SDcardPath + encryptedName);//新建加密后的数据库文件
            File encrypteddatabaseFile = getDatabasePath(encryptedName);//新建加密后的数据库文件
            //deleteDatabase(SDcardPath + encryptedName);

            //连接到加密后的数据库，并设置密码
            database.rawExecSQL(String.format("ATTACH DATABASE '%s' as "+ encryptedName.split("\\.")[0] +" KEY '"+ key +"';", encrypteddatabaseFile.getAbsolutePath()));
            //输出要加密的数据库表和数据到加密后的数据库文件中
            database.rawExecSQL("SELECT sqlcipher_export('"+ encryptedName.split("\\.")[0] +"');");
            //断开同加密后的数据库的连接
            database.rawExecSQL("DETACH DATABASE "+ encryptedName.split("\\.")[0] +";");

            // 关闭、删除已经加密好的数据库
            database.close();
            databaseFile.delete();

            // 重命名已经加密的数据库
            encrypteddatabaseFile.renameTo(databaseFile);
//            encrypteddatabaseFile.delete();

            //打开加密后的数据库，测试数据库是否加密成功
            SQLiteDatabase encrypteddatabase = SQLiteDatabase.openOrCreateDatabase(encrypteddatabaseFile, key, null);
            //encrypteddatabase.setVersion(database.getVersion());
            encrypteddatabase.close();//关闭数据库



            Toast.makeText(this, "encrypt db success", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加密数据库，加密前后数据库名称一样
     *
     * @param decryptedName 要加密的数据库名称
     * @param key 密码
     */
    private void encryptDatabase(String uncryptedDBName, String key, Context context)
    {
        try
        {
            File databaseFile = context.getDatabasePath(uncryptedDBName);
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFile, "", null);// 打开要加密的数据库

            String decryptedNameTemp = uncryptedDBName + "_temp";

            File encrypteddatabaseFile = context.getDatabasePath(decryptedNameTemp);// 新建加密后的数据库文件

            // 连接到加密后的数据库，并设置密码
            database.rawExecSQL(
                    String.format("ATTACH DATABASE '%s' as " + decryptedNameTemp.split("\\.")[0] + " KEY '" + key + "';",
                            encrypteddatabaseFile.getCanonicalPath()));
            // 输出要加密的数据库表和数据到加密后的数据库文件中
            database.rawExecSQL("SELECT sqlcipher_export('" + decryptedNameTemp.split("\\.")[0] + "');");
            // 断开同加密后的数据库的连接
            database.rawExecSQL("DETACH DATABASE " + decryptedNameTemp.split("\\.")[0] + ";");

            // 关闭、删除已经加密的数据库
            database.close();
            boolean deleteUncryptedDBResult = databaseFile.delete();

            // 重命名临时数据库
            boolean renameDBResult = encrypteddatabaseFile.renameTo(databaseFile);

            LogUtil.i(TAG, "encryptDatabase: encrypt db success. deleteUncryptedDBResult = " + deleteUncryptedDBResult + ", renameDBResult = " + renameDBResult);
        } catch (IllegalStateException e) {
            LogUtil.e(TAG, "encryptDatabase IllegalStateException");
        } catch (IOException e) {
            LogUtil.e(TAG, "encryptDatabase IOException");
        }
    }

    /**
     * 解密数据库
     * @param encryptedName 要解密的数据库名称
     * @param decryptedName 解密后的数据库名称
     * @param key 密码
     */
    private void decrypt(String encryptedName,String decryptedName,String key) {
        try {
            File databaseFile = getDatabasePath(SDcardPath + encryptedName);
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFile, key, null);

            File decrypteddatabaseFile = getDatabasePath(SDcardPath + decryptedName);
            //deleteDatabase(SDcardPath + decryptedName);

            //连接到解密后的数据库，并设置密码为空
            database.rawExecSQL(String.format("ATTACH DATABASE '%s' as "+ decryptedName.split("\\.")[0] +" KEY '';", decrypteddatabaseFile.getAbsolutePath()));
            database.rawExecSQL("SELECT sqlcipher_export('"+ decryptedName.split("\\.")[0] +"');");
            database.rawExecSQL("DETACH DATABASE "+ decryptedName.split("\\.")[0] +";");

            SQLiteDatabase decrypteddatabase = SQLiteDatabase.openOrCreateDatabase(decrypteddatabaseFile, "", null);
            //decrypteddatabase.setVersion(database.getVersion());
            decrypteddatabase.close();

            database.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
