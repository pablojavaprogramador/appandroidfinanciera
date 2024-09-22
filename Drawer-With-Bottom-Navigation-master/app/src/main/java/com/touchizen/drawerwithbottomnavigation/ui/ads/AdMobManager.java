package com.touchizen.drawerwithbottomnavigation.ui.ads;


import android.content.Context;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import android.app.Activity; // Importa la clase Activity


import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class AdMobManager {
    private InterstitialAd mInterstitialAd;
    private RewardedAd mRewardedAd;
    private AdView mBannerAd;

    // Inicializa Google Mobile Ads
    public void initializeAds(Context context) {
        MobileAds.initialize(context, initializationStatus -> {});
        loadInterstitialAd(context);
        loadRewardedAd(context);
    }

    // Carga un InterstitialAd
    public void loadInterstitialAd(Context context) {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context, "ca-app-pub-3940256099942544/1033173712", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                mInterstitialAd = null;
            }
        });
    }

    // Carga un RewardedAd
    public void loadRewardedAd(Context context) {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(context, "ca-app-pub-3940256099942544/5224354917", adRequest, new RewardedAdLoadCallback() {
            @Override
            public void onAdLoaded(RewardedAd rewardedAd) {
                mRewardedAd = rewardedAd;
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                mRewardedAd = null;
            }
        });
    }

    // Muestra un InterstitialAd
    public void showInterstitialAd(Activity activity) {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(activity);
        } else {
            loadInterstitialAd(activity);
        }
    }

    // Muestra un RewardedAd
    public void showRewardedAd(Activity activity) {
        if (mRewardedAd != null) {
            mRewardedAd.show(activity, rewardItem -> {
                // Logica para cuando se recompensa al usuario
                int rewardAmount = rewardItem.getAmount();
                String rewardType = rewardItem.getType();
                // Haz algo con la recompensa
            });
        } else {
            loadRewardedAd(activity);
        }
    }

    // Carga y muestra un BannerAd
    public void loadBannerAd(Context context, FrameLayout bannerContainer) {
        mBannerAd = new AdView(context);
        mBannerAd.setAdSize(com.google.android.gms.ads.AdSize.BANNER);
        mBannerAd.setAdUnitId("ca-app-pub-3940256099942544/6300978111");

        AdRequest adRequest = new AdRequest.Builder().build();
        mBannerAd.loadAd(adRequest);

        bannerContainer.addView(mBannerAd, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
    }
}
