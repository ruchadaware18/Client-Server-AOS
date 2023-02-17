import java.io.*;
import java.net.*;
import java.util.*;

public class Server{
  
  private Socket socket = null;

  public Server(int port){
    try {
      
        while(true){

        ServerSocket ss = new ServerSocket(port);
        Socket socket = ss.accept();

        DataInputStream is = new DataInputStream(socket.getInputStream());
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());



        String input = is.readUTF();
        int ans = 0;

        System.out.println("Calculation Request received...Processing...");

        StringTokenizer st = new StringTokenizer(input);
        
        int operand1 = Integer.parseInt(st.nextToken());
        String comma = st.nextToken();
        int operand2 = Integer.parseInt(st.nextToken());
        String operation = st.nextToken();

        if(operation.equals("add")){
          ans = operand1 + operand2;
        }
        else if(operation.equals("subtract")){
          ans = operand1 - operand2;
        }
        else if(operation.equals("multiply")){
          ans = operand1 * operand2;
        }
        else if(operation.equals("divide")){
          ans = operand1 / operand2;
        }

        System.out.println("Sending result...");
        os.writeUTF(Integer.toString(ans));

      }

    } catch (Exception e) {
      //TODO: handle exception
        System.out.println("Server Error...Please connect later");
    }
  }



  public static void main(String args[]){
    Server server = new Server(5000);
  }
}
