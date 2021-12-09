package com.example.busstopapp.test;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class ClientSocket {
    public int CSocket(String id, String pw) throws IOException, ClassNotFoundException {

        String SQL = "select * from user where userId="+id+"and userPw="+pw;
        Socket socket = new Socket("localhost", 5000);

        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        ObjectInputStream ois = new ObjectInputStream(is);

        oos.writeObject(SQL);
        int n = (int)ois.readObject();

        if(n == 1) return 1;
        else return 0;
    }

    public String CSocket2(String id) throws IOException, ClassNotFoundException {

        String SQL = "select * from user where userId="+id;
        Socket socket = new Socket("localhost", 5001);

        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        ObjectInputStream ois = new ObjectInputStream(is);

        oos.writeObject(SQL);
        String n = (String)ois.readObject();

        if(n != "0") return n;
        else return "0";
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String CSocket3(String id, String pw) throws IOException, ClassNotFoundException {
        LocalDate now = LocalDate.now();

        String SQL = "insert into user values("+id+","+pw+","+now+")";
        String SQL2 = "select * from user";
        String useri = id;

        Socket socket = new Socket("localhost", 5002);

        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        ObjectInputStream ois = new ObjectInputStream(is);

        oos.writeObject(SQL);
        oos.writeObject(SQL2);
        oos.writeObject(useri);

        String n = (String)ois.readObject();
        if(n.equals("1")) return "1";
        else return "0";
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int MSocket(String id, String busNo) throws IOException, ClassNotFoundException{
        LocalDate now = LocalDate.now();
        String SQL = "insert into marklist values("+id+","+busNo+","+now+")";
        String SQL2 = "select * from marklist where date="+now;
        Socket socket = new Socket("localhost", 5003);

        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        ObjectInputStream ois = new ObjectInputStream(is);

        oos.writeObject(SQL);
        oos.writeObject(SQL2);
        oos.writeObject(now);
        String n = (String)ois.readObject();

        if(n.equals("0"))
            return 0;
        else if (n.equals("1"))
            return 1;
        else
            return 0;
    }

    public int MSocket2(String id, String busNo) throws IOException, ClassNotFoundException{
        String SQL = "delete from marklist where userId="+id+"and busNo="+busNo;
        String SQL2 = "select * from marklist where userId="+id+"and busNo="+busNo;
        Socket socket = new Socket("localhost", 5004);

        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        ObjectInputStream ois = new ObjectInputStream(is);

        oos.writeObject(SQL);
        oos.writeObject(SQL2);
        int n = (int)ois.readObject();

        if(n > 0){
            return 0;
        }
        else {
            return 1;
        }

    }
}
