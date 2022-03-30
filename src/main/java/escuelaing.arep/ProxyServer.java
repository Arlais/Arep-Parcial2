package escuelaing.arep;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import static spark.Spark.get;
import static spark.Spark.port;

public class ProxyServer {
    public static void main(String[] args) {
        port(getPort());
        get("/cos", (req, res) ->{
            JSONObject output=callMethod(req.queryParams("value"),"cos");
            return output;
        });
        get("/ln", (req, res) ->{
            JSONObject output=callMethod(req.queryParams("value"),"ln");
            return output;
        });
    }
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 7654; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
    public static JSONObject callMethod(String value,String oper) throws IOException {
        URL url = new URL(System.getenv("PORT")+oper+"?value="+value);
        String outputLine="";
        try (BufferedReader reader= new BufferedReader(new InputStreamReader(url.openStream()))){
            String input;
            while ((input=reader.readLine())!=null){
                outputLine+=input;
                System.out.println(input);
            }
        }
        return new JSONObject(outputLine);
    }
}
