import java.io.*;
import java.net.*;
import java.util.*;

public class Client{
  
  private Socket socket = null;

  public Client(String address, int port){
    
    try{
      
      Scanner sc = new Scanner(System.in);
      socket = new Socket(address, port);

      System.out.println("Client connected to server...");

      // Input and output stream objects
           boolean isContinue = true;

      while(isContinue){


        DataInputStream is = new DataInputStream(socket.getInputStream());

        DataOutputStream os = new DataOutputStream(socket.getOutputStream());


        System.out.println("Operations you can perform are : ");
        System.out.println("Addition :"); 
        System.out.println("Subtraction :");
        System.out.println("Multiplication :");
        System.out.println("Division :");

        System.out.println("Please enter your operation in the form 'operand , operand operation'");
        String input = sc.nextLine();

        os.writeUTF(input);
        
        String ans = is.readUTF();
        System.out.println("Answer = " + ans);

        System.out.println("Please enter any of the following :");
        System.out.println("1. Send another calculation request");
        System.out.println("2. Send one change request");
        System.out.println("3. Quit");

        int operation = sc.nextInt();
        if(operation == 3){
          isContinue = false;
        }

        if(operation == 2){
          System.out.println("Enter the change request below :");
          String changeReq = sc.nextLine();
          StringTokenizer st = new StringTokenizer(changeReq);
          String change = st.nextToken();
          String operand = st.nextToken();
          String to = st.nextToken();
          String changeOpValue = st.nextToken();
          if(operand.equals("x")){
            input = operand + input.substring(1);
          }
          else if(operand.equals("y")){
            input = input.substring(0, 2) + "y" + input.substring(4);
          }
          System.out.println(input);
          os.writeUTF(input);
          String changeReqans = is.readUTF();
          System.out.println("Answer = " + changeReqans);
        }
        
        if(operation == 1){
          continue;
        }

      }


    }catch(Exception e){
      System.out.println("Error in Connection...Please try later");
    }

  }




  public static void main(String args[]){
    Client client = new Client("127.0.0.1", 5000);
  }
}
