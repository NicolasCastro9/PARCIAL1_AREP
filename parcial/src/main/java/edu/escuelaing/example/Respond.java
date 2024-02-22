package edu.escuelaing.example;

public class Respond {
    public static String index() {
        String output = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>Form Example</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <h1>CONSULTA CHATGPT GET</h1>\n"
                + "        <form action=\"/consulta\">\n"
                + "            <label for=\"name\">Name:</label><br>\n"
                + "            <input type=\"text\" id=\"name\" name=\"comando\" value=\"John\"><br><br>\n"
                + "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n"
                + "        </form> \n"
                + "        <div id=\"getrespmsg\"></div>\n"
                + "\n"
                + "        <script>\n"
                + "            function loadGetMsg() {\n"
                + "                let nameVar = document.getElementById(\"name\").value;\n"
                + "                const xhttp = new XMLHttpRequest();\n"
                + "                xhttp.onload = function() {\n"
                + "                    document.getElementById(\"getrespmsg\").innerHTML =\n"
                + "                    this.responseText;\n"
                + "                }\n"
                + "                xhttp.open(\"GET\", \"/consulta?comando=\"+nameVar);\n"
                + "                xhttp.send();\n"
                + "            }\n"
                + "        </script>\n"
                + "\n"
                + "        <h1>Form with POST</h1>\n"
                + "        <form action=\"/hellopost\">\n"
                + "            <label for=\"name\">Name:</label><br>\n"
                + "            <input type=\"text\" id=\"name\" name=\"comando\" value=\"John\"><br><br>\n"
                + "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg(postname)\">\n"
                + "        </form> \n"
                + "        <div id=\"postrespmsg\"></div>\n"
                + "\n"
                + "        <script>\n"
                + "        </script>\n"
                + "\n"
                + "    </body>\n"
                + "</html>";
        return output;
    }

    public static String error(){
        String output = "HTTP/1.1 200 OK\r\n"
        + "Content-Type: text/html\r\n"
        + "\r\n"
        + "<!DOCTYPE html>\n"
        + "<html>\n"
        + "    <head>\n"
        + "        <meta charset=\"UTF-8\">\n"
        + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
        + "    </head>\n"
        + "    <body>\n"
        + "        <h1>ERROR</h1>\n"
        + "    </body>\n"
        + "</html>";
    return output;
    }
}
