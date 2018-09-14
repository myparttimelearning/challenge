package com.rumodigi.fanduelmobilechallenge.data.cache;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FileManager {

    @Inject
    FileManager(){}

    /**
     *
     * Write a file to disk, IO operation - should be done in a worker thread
     *
     * @param file The file to write to disk
     * @param content
     */

    void writeToFile(File file, String content){
        if(!file.exists()){
            try(final FileWriter fileWriter = new FileWriter(file)){
                fileWriter.write(content);
            } catch (IOException e ){
                e.printStackTrace();
            }
        }
    }

    /**
     * Read content from a file to disk, IO operation - should be done in a worker thread
     *
     * @param file The file to read from
     * @return A String containing the contents of the file
     */

    String readFileContent(File file){
        final StringBuilder stringBuilder = new StringBuilder();
        if(file.exists()){
            String line;
            try(final BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
                while ((line  = bufferedReader.readLine()) != null ){
                    stringBuilder.append(line).append("\n");
                }
            }catch (IOException e ){
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Delete all files from a directory, IO operation - should be done in a worker thread
     *
     * @param directory The directory to be cleared
     * @return True if any files are removed
     */

    boolean clearDirectory(File directory){
        boolean result = false;
        if(directory.exists() && directory.isDirectory()){
            for(File file : directory.listFiles()){
                result = file.delete();
            }
        }
        return result;
    }

    /**
     * Write a value to a user preferences file.
     *
     * @param context {@link android.content.Context} to retrieve android user preferences.
     * @param preferenceName A file name representing where data will be get from.
     * @param key A string for the key that will be used to retrieve the value in the future.
     * @param value A long representing the value to be inserted.
     */

    void writeToPreferences(Context context, String preferenceName, String key, long value){
        final SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(preferenceName, value);
        editor.apply();
    }

    /**
     *
     * Get a value from a user preferences file.
     *
     * @param context {@link android.content.Context} to retrieve android user preferences.
     * @param preferenceName A file name representing where data will be get from.
     * @param key A key that will be used to retrieve the value from the preference file.
     * @return A long representing the value retrieved from the preferences file.
     */

    long getFromPreferences(Context context, String preferenceName, String key){
        final SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, 0);

    }

}
