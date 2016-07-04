package saudeemcasa.view;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewAssertion;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import mds.gpp.saudeemcasa.R;
import mds.gpp.saudeemcasa.controller.DrugStoreController;
import mds.gpp.saudeemcasa.view.LoadingScreen;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestLoadingScreen extends ActivityInstrumentationTestCase2<LoadingScreen> {

    private LoadingScreen mActivity;

    public TestLoadingScreen() {
        super(LoadingScreen.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();
    }

    @Test
    public void showLoadingScreen() {
        onView(withId(R.id.saude_em_casa_logo)).check((ViewAssertion) isDisplayed());
    }

    @Test
    public void drugStoreNotNull(){

        assertNotNull(drugStoreController);
    }

}