import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import java.io.IOException;

/*
Получить текстовый HTML ответ от Google.
Получить текстовый JSON ответ от GitHub.
Получить и распарсить JSON ответ от tiny-url.info.
 */

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.html();
            main.gson();
            main.tinyUrl();
        } catch (IOException e) {
            e.printStackTrace();
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private void html() throws IOException {
        System.out.println( "HTML от Google:");
        GoogleWebService service = new Retrofit.Builder()
                .baseUrl("https://google.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(GoogleWebService.class);
        Response<String> response = null;
        response = service.index().execute();
        if (response != null && response.isSuccessful()) {
            System.out.println(response.body());
        } else if (response != null) {
            System.out.println(response.errorBody().string());
        }
    }

    private void gson() {
        System.out.println("\n GSON от GitHub:");
        GitHubService service = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(GitHubService.class);
        Response<String> response = null;
        try {
            response = service.listRepos("octocat").execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response != null && response.isSuccessful()) {
            System.out.println(response.body());
        } else if (response != null) {
            try {
                System.out.println(response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void tinyUrl()  {
        final TinyUrlService service = new Retrofit.Builder()
                .baseUrl("http://tiny-url.info/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TinyUrlService.class);
        Response<TinyUrlResponse> response;
        try {
            response = service.random( "json",
                    "http://tiny-url.info/open_api.html").execute();
            if (response != null && response.isSuccessful()) {
                System.out.println(response.body().shortUrl);
            } else if (response != null) {
                System.out.println(response.errorBody().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
