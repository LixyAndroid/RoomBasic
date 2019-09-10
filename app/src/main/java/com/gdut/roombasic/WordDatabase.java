package com.gdut.roombasic;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * @author Li Xuyang
 * date  2019/9/7 20:20
 * 版本迁移
 */
//singleton
    /*
        1.为什么要使用synchronized

        在并发编程中存在线程安全问题，主要原因有：1.存在共享数据 2.多线程共同操作共享数据。关键字synchronized可以保证在同一时刻，只有一个线程可以执行某个方法或某个代码块，同时synchronized可以保证一个线程的变化可见（可见性），即可以代替volatile。

        2.实现原理

        synchronized可以保证方法或者代码块在运行时，同一时刻只有一个方法可以进入到临界区，同时它还可以保证共享变量的内存可见性

        3.synchronized的三种应用方式

        Java中每一个对象都可以作为锁，这是synchronized实现同步的基础：

        普通同步方法（实例方法），锁是当前实例对象 ，进入同步代码前要获得当前实例的锁
        静态同步方法，锁是当前类的class对象 ，进入同步代码前要获得当前类对象的锁
        同步方法块，锁是括号里面的对象，对给定对象加锁，进入同步代码库前要获得给定对象的锁。
        4.synchronized的作用

        Synchronized是Java中解决并发问题的一种最常用最简单的方法 ，他可以确保线程互斥的访问同步代码
     */
@Database(entities = {Word.class}, version = 3, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    private static volatile WordDatabase INSTANCE;

    static synchronized WordDatabase getDatabase(Context context) {


        if (INSTANCE == null) {

            synchronized (WordDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordDatabase.class, "word_database")
                       // .fallbackToDestructiveMigration()
                        .addMigrations(MIGRATION_2_3)
                        .build();
            }

        }

        return INSTANCE;
    }

    public abstract WordDao getWordDao();


    static final Migration MIGRATION_2_3 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE word ADD COLUMN bar_data INTEGER NOT NULL DEFAULT 1");
        }
    };
}
