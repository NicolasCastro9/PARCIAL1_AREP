package edu.escuelaing.example;
import java.net.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class HttpServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(36000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        
        boolean running = true;
        Socket clientSocket = null;
        while (running) {
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            String URI = null;
            boolean fline = true;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (fline) {
                    URI = inputLine.split(" ")[1];
                    System.out.println(URI);
                    fline = false;
                }
                if (!in.ready()) {
                    break;
                }
            }
            if (URI.startsWith("/consulta")) {
                outputLine = Response(URI);
            } else if (URI.startsWith("/cliente")) {
                outputLine = index();
            } else {
                outputLine = error();
            }
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        
        serverSocket.close();
    }

    public static String Response(String uriS) throws ClassNotFoundException {
        String command = uriS.split("=")[1];
        String output = getCommand(command);
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + output;
    }

    public static String getCommand(String command) throws ClassNotFoundException {
        String output;

        if (command.startsWith("Class")) {
            output = classCommand(command);
        } else if (command.startsWith("invoke")) {
            output = invokeCommand(command);
        } else if (command.startsWith("unaryInvoke")) {
            output = unaryCommand(command);
        } else if (command.startsWith("binaryInvoke")) {
            output = binaryInvokeCommand(command);
        } else {
            output = error();
        }
        return output;
    }

    public static String classCommand(String command) throws ClassNotFoundException {
        String salida = "";
        System.out.println("Comando solicitado: " + command);
        String valor = command.split("\\(")[1];
        valor = valor.split("\\)")[0];
        System.out.println("Valor: " + valor);
        Class c = Class.forName(valor);
        Method[] methods = c.getDeclaredMethods();
        Field[] fields = c.getDeclaredFields();
        for(Method method : methods){
           salida += method.toString() + "\r\n";
        }
        for(Field field : fields){
           salida += field.toString() + "\r\n";
        }
        return salida;
    }

    public static String invokeCommand(String command) {

        String valor = command.substring(7, command.length() - 1);
        String className = valor.split(",")[0];
        String method = valor.split(",")[1];
        Method methods = null;

        try {
            try {
                methods = Class.forName(className).getDeclaredMethod(method);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        String salida =  " " + methods;
        System.out.println(className);
        return salida;
    }

    public static String unaryCommand(String command) {
        return "me falta";
    }

    public static String binaryInvokeCommand(String command) {
        return "me falta";
    }

    public static String index() {
        String output = Respond.index();
        return output;
    }

    public static String error(){
        String output = Respond.error();
        return output;
    }

}