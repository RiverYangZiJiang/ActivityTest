package com.example.yangzijiang.activitytest;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;

public class FileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

//        sdcardPath();
//        getFilesPath();
//        getDataPath();
//        getPath("mateline", MODE_PRIVATE);

//        try {
//            saveData();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        makeDir();
//
//        moveDir();

        deleteDir();
    }

    public void saveData() throws IOException {
        String data = "data";
        FileOutputStream out = null;
        BufferedWriter writer = null;
        out = openFileOutput("data", Context.MODE_PRIVATE);
        writer = new BufferedWriter(new OutputStreamWriter(out));
        writer.write(data);
    }

    public void moveDir(){
        String dir = sdcardPath() + File.separator + "s";
        boolean flag = copyDirectory(dir, getPath("mateline", MODE_PRIVATE), false);
        LogUtil.d("moveDir", String.valueOf(flag));
    }

    public void makeDir(){
        String dir = sdcardPath() + File.separator + "s";
        File file = new File(dir);
        if (!file.exists()){
            file.mkdir();
        }

        dir = sdcardPath() + File.separator + "s" + File.separator + "s1";
        file = new File(dir);
        if (!file.exists()){
            file.mkdir();
        }
    }

    public void deleteDir(){
        String dir = sdcardPath() + File.separator + "s";
        File file = new File(dir);
        // delete方法：如果删除的是文件夹，则文件夹必须为空，否则就要自定义方法递归来删除文件夹了
        boolean result = file.delete();
        LogUtil.d("deleteDir", String.valueOf(result));
    }

    // 获得SD卡路径，如/storage/sdcard0
    public String sdcardPath(){
        String sdPath = null;
        try {
            sdPath = Environment.getExternalStorageDirectory().getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogUtil.d("sdcardPath", sdPath);
        return sdPath;
    }

    // 获得应用的files目录路径   /data/data/com.example.yangzijiang.activitytest/files
    public String getFilesPath(){
        String filesPath = null;
        Context cont = this.getApplicationContext();
        try {
            filesPath = cont.getFilesDir().getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogUtil.d("getFilesPath", filesPath);
        return filesPath;
    }

    // 自定义路径，如果不存在则创建，存在则返回   /data/data/com.example.yangzijiang.activitytest/app_mateline，会自动加上app前缀
    public String getPath(String name, int mode){
        String dir = null;
        Context cont = this.getApplicationContext();
        try {
            dir = cont.getDir(name, mode).getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogUtil.d("getPath", dir);
        return dir;
    }

    // 获得data路径   /data
    public String getDataPath(){
        String dataPath = null;
        try {
            dataPath = Environment.getDataDirectory().getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogUtil.d("getDataPath", dataPath);
        return dataPath;
    }


    // 复制文件
    public static boolean copyFile(String sourcePath, String targetPath)
    {
        if (null == sourcePath || null == targetPath)
        {
            LogUtil.e("copyFile", "sourcePath or targetPath is null");
            return false;
        }
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try
        {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourcePath));
            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetPath));
            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1)
            {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        }
        catch (IOException e)
        {
            LogUtil.e("copyFile error", e.getMessage());
            return false;
        }
        finally
        {
            closeQuietly(outBuff);
            closeQuietly(inBuff);
        }

        return true;
    }

    /**
     *
     * 关闭流，忽略异常<br>
     * 从commonIO抄过来的<br>
     *
     * @author l00208306
     * @see 相关函数，对于重要的函数建议注释
     * @since MatelineV100R001C00, 2015年12月3日
     * @param input
     */
    public static void closeQuietly(InputStream input)
    {
        closeQuietly((Closeable) input);
    }

    public static void closeQuietly(Closeable closeable)
    {
        try
        {
            if (closeable != null)
            {
                closeable.close();
            }
        }
        catch (IOException ioe)
        {
            // 忽略异常
            Log.e("closeQuietly", "closeQuietly error: " + ioe);
        }
    }

    public static void closeQuietly(Reader input)
    {
        closeQuietly((Closeable) input);
    }
    /***
     *
     * (复制文件夹)<br>
     * (功能详细描述)<br>
     *
     * @author
     * @see 相关函数，对于重要的函数建议注释
     * @since MatelineV100R001C00, 2016-3-1
     * @param sourcePath
     * @param targetPath
     * @param onlyFile 是否只复制文件
     * @return
     * @throws IOException
     */
    public static boolean copyDirectory(String sourcePath, String targetPath, boolean onlyFile)
    {
        // 要复制的文件目录
        File[] currentFiles;
        File root = new File(sourcePath);
        // 如同判断SD卡是否存在或者文件是否存在
        if (!root.exists())
        {
            LogUtil.e("copyDirectory", "sourcePath is not exists");
            return false;

        }
        // 如果存在则获取当前目录下的全部文件 填充数组
        currentFiles = root.listFiles();

        if (null == currentFiles)
        {
            LogUtil.e("copyDirectory", "Failed to list contents of " + root.getPath());
            return false;
        }

        File targetDir = new File(targetPath);
        if (!targetDir.exists())
        {
            targetDir.mkdirs();
        }
        // 遍历要复制该目录下的全部文件
        try
        {
            for (int i = 0; i < currentFiles.length; i++)
            {
                if (currentFiles[i].isDirectory()) // 如果当前项为子目录 进行递归
                {
                    copyDirectory(currentFiles[i].getCanonicalPath() + File.separator, onlyFile ? targetPath : targetPath
                            + File.separator + currentFiles[i].getName() + File.separator, onlyFile);
                }
                else
                // 如果当前项为文件则进行文件拷贝
                {
                    if (!copyFile(currentFiles[i].getCanonicalPath(), targetPath + File.separator + currentFiles[i].getName()))
                    {
                        LogUtil.e("copyDirectory", "copyDirectory -- error");
                        return false;
                    }
                }
            }
        }
        catch (IOException e)
        {
            LogUtil.e("copyDirectory", "error happens");
            return false;
        }

        return true;
    }


}
