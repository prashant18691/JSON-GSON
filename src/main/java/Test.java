import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import com.google.gson.Gson;


public class Test {
    public static void main(String[] args) {
        String url = "https://jsonmock.hackerrank.com/api/movies/search/?Title=" + "spiderman";
        try {
            String raw = getData(url);
            Gson gson = new Gson();
            Page page = gson.fromJson(raw, Page.class);
            Movie[] movies = page.getData();
            Arrays.sort(movies, new Comparator<Movie>() {
                @Override public int compare(final Movie o1, final Movie o2) {
                    return o1.getTitle().compareTo(o2.getTitle());
                }
            });
            List<String> title = Arrays.stream(movies).map(Movie::getTitle).collect(Collectors.toList());
            System.out.println(Arrays.deepToString(title.toArray()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String getData(final String url) throws Exception {
        HttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        get.addHeader("accept", "application/json");
        HttpResponse response = client.execute(get);
        return readData(response);
    }

    private static String readData(final HttpResponse response) throws Exception{
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer stringBuffer = new StringBuffer();
            char[] datalength = new char[1024];
            int read;
            while ((read = bufferedReader.read(datalength)) != -1) {
                stringBuffer.append(datalength, 0, read);
            }
            return stringBuffer.toString();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            if (bufferedReader!=null)
                bufferedReader.close();
        }
        return null;
    }
}

 class Movie
{
    private String Year;

    private String Type;

    private String Poster;

    private String imdbID;

    private String Title;

    public String getYear ()
    {
        return Year;
    }

    public void setYear (String Year)
    {
        this.Year = Year;
    }

    public String getType ()
    {
        return Type;
    }

    public void setType (String Type)
    {
        this.Type = Type;
    }

    public String getPoster ()
    {
        return Poster;
    }

    public void setPoster (String Poster)
    {
        this.Poster = Poster;
    }

    public String getImdbID ()
    {
        return imdbID;
    }

    public void setImdbID (String imdbID)
    {
        this.imdbID = imdbID;
    }

    public String getTitle ()
    {
        return Title;
    }

    public void setTitle (String Title)
    {
        this.Title = Title;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Year = "+Year+", Type = "+Type+", Poster = "+Poster+", imdbID = "+imdbID+", Title = "+Title+"]";
    }
}

class Page
{
    private String total;

    private String per_page;

    private String page;

    private String total_pages;

    private Movie[] data;

    Movie[] getData() {
        return data;
    }

    void setData(final Movie[] data) {
        this.data = data;
    }

    public String getTotal ()
    {
        return total;
    }

    public void setTotal (String total)
    {
        this.total = total;
    }

    public String getPer_page ()
    {
        return per_page;
    }

    public void setPer_page (String per_page)
    {
        this.per_page = per_page;
    }

    public String getPage ()
    {
        return page;
    }

    public void setPage (String page)
    {
        this.page = page;
    }

    public String getTotal_pages ()
    {
        return total_pages;
    }

    public void setTotal_pages (String total_pages)
    {
        this.total_pages = total_pages;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [total = "+total+", per_page = "+per_page+", page = "+page+", total_pages = "+total_pages+"]";
    }
}


