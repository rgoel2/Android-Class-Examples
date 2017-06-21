package com.sargent.mark.githubreposearch;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.sargent.mark.githubreposearch.model.Repository;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    String TAG = "ExampleInstrumentedTest";
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        String json = "{\"items\": [{\"id\": 3402537,\"name\": \"Front-end-Developer-Interview-Questions\",\"full_name\": \"h5bp/Front-end-Developer-Interview-Questions\",\"owner\": {\"login\": \"h5bp\"},\"html_url\": \"https://github.com/h5bp/Front-end-Developer-Interview-Questions\"}]}";

        ArrayList<Repository> repo = NetworkUtils.parseJSON(json);

        Log.d(TAG, "name: " + repo.get(0).getName());
        assertTrue(repo.get(0).getName().equals("Front-end-Developer-Interview-Questions"));

        assertEquals("com.sargent.mark.githubreposearch", appContext.getPackageName());
    }
}
