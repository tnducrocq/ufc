package fr.tnducrocq.ufc.data.entity.fight;


import com.google.gson.Gson;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FightUnitTest {

    private static File getFileFromPath(Object obj, String fileName) {
        ClassLoader classLoader = obj.getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        return new File(resource.getPath());
    }

    @Test
    public void parseFight() throws Exception {
        try {
            URL url = getClass().getResource("/fight.json");
            Assert.assertNotNull(url);

            URI uri = url.toURI();
            File file = new File(uri);
            Assert.assertTrue(file.exists());

            String content = FileUtils.readFileToString(file, Charset.defaultCharset());

            Fight fight = new Gson().fromJson(content, Fight.class);
            Assert.assertNotNull(fight);
            Assert.assertNotNull(fight.getFightDetail());
            Assert.assertNotNull(fight.getFightDetail().getFighters());
            Assert.assertNotNull(fight.getFightDetail().getFighters().getBlue());
            Assert.assertNotNull(fight.getFightDetail().getFighters().getRed());

            Assert.assertNotNull(fight.getFightDetail().getFightStats().getBlue());
            Assert.assertNotNull(fight.getFightDetail().getFightStats().getBlue().getGrappling());
            Assert.assertNotNull(fight.getFightDetail().getFightStats().getBlue().getTip());
            Assert.assertNotNull(fight.getFightDetail().getFightStats().getBlue().getStrikes());

            Assert.assertNotNull(fight.getFightDetail().getFightStats().getRed());
            Assert.assertNotNull(fight.getFightDetail().getFightStats().getRed().getGrappling());
            Assert.assertNotNull(fight.getFightDetail().getFightStats().getRed().getTip());
            Assert.assertNotNull(fight.getFightDetail().getFightStats().getRed().getStrikes());


            Assert.assertNotNull(fight.getFightDetail().getRoundStats().getRound1());
            Assert.assertNotNull(fight.getFightDetail().getRoundStats().getRound1().getBlue());
            Assert.assertNotNull(fight.getFightDetail().getRoundStats().getRound1().getBlue().getStrikes());

            Assert.assertNotNull(fight.getFightDetail().getRoundStats().getRound1().getRed());
            Assert.assertNotNull(fight.getFightDetail().getRoundStats().getRound1().getRed().getStrikes());

            Assert.assertNotNull(fight.getFightDetail().getRoundStats().getRound2());
            Assert.assertNotNull(fight.getFightDetail().getRoundStats().getRound3());
            Assert.assertNotNull(fight.getFightDetail().getRoundStats().getRound4());
            Assert.assertNotNull(fight.getFightDetail().getRoundStats().getRound5());

            System.out.println(fight);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
