package com.github.zakariandys.MarketingAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.reflect.Type;

import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.APINodeList;
import com.facebook.ads.sdk.Ad;
import com.facebook.ads.sdk.AdAccount;
import com.facebook.ads.sdk.AdSet;
import com.facebook.ads.sdk.Campaign;
import com.facebook.ads.sdk.Targeting;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RetrieveData {

	public static final String ACCES_TOKEN = "EAAEomGqMfMEBANYpgZBNZCu4iwrpPk1MfuJzvbqxHVEP9cA3jJVbpZC4CiecdTpJUVs3AUZB5IaEE2c2BZCvlW2Ap0grsdSZAcLmhK23zSaYAcZCWIs3y4EUAUZCg1M3rRHYRloQQZAE4tJ6oNbj13Ebcj84zT6lUIAiycZAjPK0PecF8QwjL7YIay";
	public static final String APP_SECRET = "afe203dce827aea1f16d3bb8cf6146ff";
	public static final String ACCOUNT_ID = "115690385586921";

	public static void main(String[] args) {
		APIContext context = new APIContext(ACCES_TOKEN);
		AdAccount account = new AdAccount(ACCOUNT_ID, context);
		Gson gson = new Gson();

		try {
			APINodeList<Campaign> campaigns = account.getCampaigns().requestAllFields().execute();
			APINodeList<AdSet> adsets = account.getAdSets().requestAllFields().execute();
			APINodeList<Ad> ads = account.getAds().requestAllFields().execute();

			for (Campaign campaign : campaigns) {
				System.out.println("====================================================================");
				System.out.println("Campaign Id           : " + campaign.getId());
				System.out.println("Campaign Name         : " + campaign.getFieldName());
				System.out.println("Campaign Objective    : " + campaign.getFieldObjective());
				System.out.println("Campaign Time Created : " + campaign.getFieldCreatedTime());
				System.out.println("Buying Type           : " + campaign.getFieldBuyingType());
				System.out.println("Budget Rebalance      : " + campaign.getFieldBudgetRebalanceFlag());
				for (AdSet adset : adsets) {
					System.out.println("====================================================================");
					if (campaign.getId().equals(adset.getFieldCampaignId())) {
						String targeting = adset.getFieldTargeting().toString();
						System.out.println("Adset Id              : " + adset.getId());
						System.out.println("Adset Name            : " + adset.getFieldName());
						System.out.println("Adset Goal            : " + adset.getFieldOptimizationGoal());
						System.out.println("Adset Time Created    : " + adset.getFieldCreatedTime());
						System.out.println("Budget Remaining      : Rp." + adset.getFieldBudgetRemaining());
						System.out.println("Daily Budget          : Rp." + adset.getFieldDailyBudget());
						System.out.println("Promoted Object : " + adset.getFieldTargeting());
					}
					for (Ad ad : ads) {
						System.out.println("====================================================================");
						if (campaign.getId().equals(adset.getFieldCampaignId())
								&& adset.getId().equals(ad.getFieldAdsetId())) {
							System.out.println("Adsnyaaaaaa " + ad.getId());
							System.out.println("Adsnyaaaaaa " + ad.getFieldName());
							System.out.println("Adsnyaaaaaa " + ad.getFieldBidInfo());
						}

					}
				}
			}
		} catch (APIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
