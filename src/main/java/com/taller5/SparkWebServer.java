package com.taller5;

import static spark.Spark.port;
import static spark.Spark.get;
import static spark.Spark.staticFiles;

import java.io.IOException;

public class SparkWebServer {
    public static void main(String... args) throws IOException {
        port(getPort());
        staticFiles.location("/public/static");
        get("/hello", (req, res) -> "hello Docker!");
        // seno
        get("sin/:angulo", (req, res) -> {
            String numStr = req.params(":angulo");
            double result;
            try {
                double num = Double.parseDouble(numStr);
                result = Math.sin(Math.toRadians(num));
            } catch (NumberFormatException e) {
                return "Entrada invalida: '" + numStr + "'  no es un número valido";
            }
            return "El seno de '" + numStr + "' es " + result;
        });
        // coseno
        get("cos/:num", (req, res) -> {
            String numStr = req.params(":num");
            double result;
            try {
                double num = Double.parseDouble(numStr);
                result = Math.cos(Math.toRadians(num));
            } catch (NumberFormatException e) {
                return "Entrada invalida: '" + numStr + "'  no es un número valido";
            }
            return "El coseno de '" + numStr + "' es " + result;
        });

        // palindrome
        get("palindrome/:pal", (req, res) -> {
            String result = "la palabra '" + req.params(":pal") + "'";
            String palindrome = isPalindrome(req.params(":pal")) ? " Es palindrome"
                    : " no es palindrome";
            return result + palindrome;
        });

        // magnitud de un vector 2D
        get("magnitud/:x/:y", (req, res) -> {
            double x = Double.parseDouble(req.params(":x"));
            double y = Double.parseDouble(req.params(":y"));
            double magnitud = magnitud(x, y);
            return "La magnitud del vector (" + x + ", " + y + ") es: " + magnitud;
        });

    }

    /**
     * Calcular la magintud de un vector 2d
     * 
     * @param x valor del vector en x
     * @param y valor del vector en y
     * @return un double que representa la manginut del vector
     */
    public static double magnitud(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * verificar si una palabra es palindrome
     * 
     * @param pal String a verificar
     * @return verdadero si la palabra es palindrome y falso si no lo es
     */
    private static boolean isPalindrome(String pal) {
        // Eliminar espacios en blanco y se convierte todo a minusculas
        pal = pal.replaceAll("\\s+", "").toLowerCase();

        // Comprobar si la palabra es igual a su reverso
        return pal.equals(new StringBuilder(pal).reverse().toString());
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}