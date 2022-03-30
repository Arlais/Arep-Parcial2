package escuelaing.arep;

import static spark.Spark.get;
import static spark.Spark.port;

public class MathServices {

    public static void main(String[] args) {
        port(getPort());
        get("/cos", (req, res) ->{
            String input=req.queryParams("value");
            Double valor=cos(Double.parseDouble(input));
            return "{\n\n\"operation\": \"cos\",\n\"input\": "+ input +",\n\n\"output\": "+ valor +"\n\n}";
        }
        );
        get("/ln", (req, res) ->{
                    String input=req.queryParams("value");
                    Double valor=ln(Double.parseDouble(input));
                    return "{\n\n\"operation\": \"ln\",\n\"input\": "+ input +",\n\n\"output\": "+ valor +"\n\n}";
                }
        );
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }

    public static double cos(double number){
        return Math.cos(number);
    }

    public static double ln(double number){
        return Math.log(number);
    }
}