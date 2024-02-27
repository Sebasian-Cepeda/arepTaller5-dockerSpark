package com.taller5;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

import java.io.IOException;

import com.taller5.spark.WebServer;

import static spark.Spark.get;

public class SparkWebServer {
    public static void main(String... args) throws IOException{
          port(getPort());
          staticFiles.location("/public/static");
          get("/hello", (req,res) -> "hello Docker!");
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}