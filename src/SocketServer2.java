import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SocketServer2 {
    public static void main(String[] args) {
		Connection con = null;
	    PreparedStatement pstmt = null;   
	    ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
            
            con = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/user",
                "root",
                "159753");
			try {
				ServerSocket serverSocket = new ServerSocket(5001);
				System.out.println("서버 가동됨");
				Socket socket = serverSocket.accept();
				System.out.println("클라이언트 연결 접수됨...");
				System.out.println("[client] : " + socket.getInetAddress());

				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				ObjectOutputStream oos = new ObjectOutputStream(os);

				String SQL = (String) ois.readObject();
				
				pstmt = con.prepareStatement(SQL);
				rs = pstmt.executeQuery();
				
				int i = 0;
				String a = null;
				
				while(rs.next()) {
	                a = rs.getString("userPw");
	                i++;
	            }
				if(i>0) oos.writeObject(a);
				else oos.writeObject(0);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
