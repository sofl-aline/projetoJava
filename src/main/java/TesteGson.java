import com.google.gson.Gson;

public class TesteGson {
    public static void main(String[] args) {
        Gson gson = new Gson();
        String json = gson.toJson("Funcionou!");
        System.out.println(json);
    }
}
