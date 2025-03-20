import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class AemetApi {
    private static final String API_URL = "https://opendata.aemet.es/opendata/api/valores/climatologicos/inventarioestaciones/todasestaciones/";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaWNoZWxsZS5wb3NsaWd1YS43ZThAaXRiLmNhdCIsImp0aSI6IjA2NTE3MzkzLWY3MmYtNGQwZS1iMmY0LTdkOGJmYzM5NWNjZiIsImlzcyI6IkFFTUVUIiwiaWF0IjoxNzQyMTk3NTU0LCJ1c2VySWQiOiIwNjUxNzM5My1mNzJmLTRkMGUtYjJmNC03ZDhiZmMzOTVjY2YiLCJyb2xlIjoiIn0.MofLefHrfSUQEtetHBqcWFJCGFgPbAd_1kxfZnGjxSU";

    public static void main(String[] args) {
        String jsonResponse = obtenerDatosAemet();
        if (jsonResponse != null) {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            if (jsonObject.has("datos")) {
                String datosUrl = jsonObject.getString("datos");
                String datosJson = cargarDatos(datosUrl);
                if (datosJson != null) {
                    obtenerNombresEstaciones(datosJson);
                }
            } else {
                System.out.println("El JSON no contiene la clave 'datos'");
            }
        }
    }

    public static String obtenerDatosAemet() {
        try {
            URL url = new URL(API_URL + "?api_key=" + API_KEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Cache-Control", "no-cache");

            if (conn.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();
            } else {
                System.out.println("Error al obtener datos de AEMET: " + conn.getResponseCode());
            }
        } catch (Exception e) {
            System.out.println("Error al conectar con la API: " + e.getMessage());
        }
        return null;
    }

    public static String cargarDatos(String urlDatos) {
        try {
            URL url = new URL(urlDatos);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();
            } else {
                System.out.println("Error al cargar los datos: " + conn.getResponseCode());
            }
        } catch (Exception e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
        return null;
    }

    public static void obtenerNombresEstaciones(String jsonDatos) {
        try {
            JSONArray estaciones = new JSONArray(jsonDatos);
            System.out.println("Nombres de las estaciones:");
            for (int i = 0; i < estaciones.length(); i++) {
                JSONObject estacion = estaciones.getJSONObject(i);
                String nombre = estacion.has("nombre") ? estacion.getString("nombre") : "Desconocido";
                String provincia = estacion.has("provincia") ? estacion.getString("provincia") : "Desconocida";
                System.out.println("Nombre: " + nombre + " | Provincia: " + provincia);
            }
        } catch (Exception e) {
            System.out.println("Error al procesar los datos: " + e.getMessage());
        }
    }
}
