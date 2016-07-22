package conor.obrien.jokedisplay;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.text.TextUtils;

import com.udacity.gradle.jokeApp.EndpointsAsyncTask;
import com.udacity.gradle.jokeApp.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(MockitoJUnitRunner.class)
public class EndpointsAsyncTaskTests extends ActivityInstrumentationTestCase2<com.udacity.gradle.jokeApp.MainActivity> {

    @Mock
    Context mContext;

    String mJokeString;

    public EndpointsAsyncTaskTests() {
        super(MainActivity.class);
    }

    @Test
    public void testSomeAsyncTask () throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);

        EndpointsAsyncTask.GetJokeListener getJokeListener = new EndpointsAsyncTask.GetJokeListener() {
            @Override
            public void onComplete(String joke, Exception e) {
                mJokeString = joke;
                signal.countDown();
            }
        };

        final EndpointsAsyncTask asyncTask = new EndpointsAsyncTask();
        asyncTask.setListener(getJokeListener);

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                asyncTask.execute(getActivity().getApplicationContext());
            }
        });

        signal.await(10, TimeUnit.SECONDS);

        assertFalse("Joke String is Empty!", TextUtils.isEmpty(mJokeString));
    }
}