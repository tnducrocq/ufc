package fr.tnducrocq.ufc.data.entity;

import org.junit.Test;

import fr.tnducrocq.ufc.data.entity.fighter.FighterDetails;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tony on 02/10/2017.
 */

public class FighterDetailsTest {


    @Test
    public void parseFighterDetails() throws Exception {
        String url = "http://fr.ufc.com/fighter/Conor-McGregor";

        Request request = new Request.Builder().url(url).get().build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Response response = okHttpClient.newCall(request).execute();
        String html = response.body().string();
        //System.out.println(html);

        FighterDetails details = FighterDetails.parse(html);
        System.out.println(details);

    }

}
