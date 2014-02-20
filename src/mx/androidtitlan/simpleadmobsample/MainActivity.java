package mx.androidtitlan.simpleadmobsample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends Activity {

	private static final String MY_AD_UNIT_ID = "ca-app-pub-9756726447441686/6270780460";
	private static final String MY_INTERSTITIAL_AD_ID = "ca-app-pub-9756726447441686/7747513665";
	protected static final String LOG_TAG = "SimpleAdmobSample";
	private AdView adView;
	private InterstitialAd interstitialAd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		displayAdInCode();
		displayAdInXML();
		displayAdInterstitial();

	}

	private void displayAdInterstitial() {
		interstitialAd = new InterstitialAd(this);
		interstitialAd.setAdUnitId(MY_INTERSTITIAL_AD_ID);
		AdRequest adRequest = new AdRequest.Builder().build();
		interstitialAd.loadAd(adRequest);

	}

	public void displayAd(View v) {
		if (interstitialAd.isLoaded()) {
			interstitialAd.show();
		} else {
			Log.d(LOG_TAG, "Interstitial ad was not ready to be shown.");
		}
	}

	public void displayInterstitial() {
		if (interstitialAd.isLoaded()) {
			interstitialAd.show();
		}
	}

	private void displayAdInXML() {
		AdView adView = (AdView) this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
	}

	private void displayAdInCode() {
		adView = new AdView(this);
		adView.setAdUnitId(MY_AD_UNIT_ID);
		adView.setAdSize(AdSize.BANNER);
		LinearLayout layout = (LinearLayout) findViewById(R.id.mainLayout);
		layout.addView(adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
	}

	@Override
	public void onPause() {
		if (adView != null) {
			adView.pause();
		}
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		if (adView != null) {
			adView.resume();
		}
	}

	@Override
	public void onDestroy() {
		if (adView != null) {
			adView.destroy();
		}
		super.onDestroy();
	}

}
