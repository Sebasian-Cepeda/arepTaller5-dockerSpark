package com.taller5.spark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class WebServer {

    private static Map<String, WebService> handlers = new HashMap<String, WebService>();

    private static String handleSparkRequest(String newUri, String query, OutputStream outputStream) {
        try {
            URI uri = new URI(newUri);
            String path = uri.getPath();

            if (handlers.containsKey(path)) {
                return petitionPage(handlers.get(path).handle(query), outputStream).replace("{name}", query);
            } else if (path.contains("css") || path.contains("jpg") || path.contains("js")) {
                return petitionPage(path, outputStream);
            } else {
                return errorPage("/NotFound.html", outputStream);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
        }
    }

    private static String ok() {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: ";
    }

    private static String not() {
        return "HTTP/1.1 404 NOT FOUND\r\n"
                + "Content-Type: ";
    }

    private static String errorPage(String file, OutputStream op) {
        return not() + getMimeType(file) + "\r\n"
                + "\r\n"
                + getStaticFile(file, op);
    }


    public static void handleGetRequest(String path, WebService handler) {
        handlers.put(path, handler);
    }


    private static String petitionPage(String filePetition, OutputStream op) {

        return ok() + getMimeType(filePetition) + "\r\n"
                + "\r\n"
                + getStaticFile(filePetition, op);
    }
    
    /**
     * returns the static file related with the request
     * 
     * @return string with all information insite the file
     */
    private static String getStaticFile(String file, OutputStream ops) {
        Path path = (file.equals("/"))
                ? Paths.get(getStaticFilesDirectory() + "/hello.html")
                : Paths.get(getStaticFilesDirectory() + file);

        try {
            Charset charset = Charset.forName("UTF-8");
            StringBuilder outputLine = new StringBuilder();
            byte[] bytes;

            if (file.endsWith(".jpg")) {
                bytes = getAnImage(file);
                String response = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: image/jpeg\r\n" +
                        "Content-Length: " + bytes.length + "\r\n" +
                        "\r\n";
                ops.write(response.getBytes());
                ops.write(bytes);
            } else {
                try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        outputLine.append(line).append("\n");
                    }
                }
            }

            return outputLine.toString();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return "HTTP/1.1 404 Not Found\r\n\r\n";
        }
    }


    private static String mainPage(String file, OutputStream ops) {
        String contentType = getMimeType(file);
        String content = getStaticFile(file, ops);

        String outputLine = "HTTP/1.1 200 OK\r\n"
                + "Content-Type:" + contentType + "\r\n"
                + "\r\n"
                + content;

        return outputLine;
    }

    private static String getMimeType(String file) {
        if (file.endsWith(".html") || file.endsWith("/")) {
            return "text/html";
        } else if (file.endsWith(".css")) {
            return "text/css";
        } else if (file.endsWith(".js")) {
            return "application/javascript";
        } else if (file.endsWith(".jpg")) {
            return "image/jpeg";
        } else {
            return "text/plain";
        }
    }

     /**
     * return the bytes of an image
     * 
     * @param file the route of the file to return to the browser
     * @return an array of bytes
     */
    private static byte[] getAnImage(String file) {

        Path image = Paths.get("target/classes/public/static" + file);

        try {
            return Files.readAllBytes(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getStaticFilesDirectory() {
        return "target/classes/public/static";
    }
}
