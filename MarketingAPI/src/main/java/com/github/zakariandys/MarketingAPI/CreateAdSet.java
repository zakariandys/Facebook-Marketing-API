package com.github.zakariandys.MarketingAPI;

import java.util.ArrayList;
import java.util.Arrays;

import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.AdAccount;
import com.facebook.ads.sdk.AdSet;
import com.facebook.ads.sdk.Campaign;
import com.facebook.ads.sdk.Targeting;
import com.facebook.ads.sdk.TargetingGeoLocation;

public class CreateAdSet {

	public static final String ACCESS_TOKEN = "EAAZAosaFlcrABAHQcMYGSac9jZCojSUFjw3YuZBwl7TDn2LG9QaBPZCuxbtyUgdxlEdnr7rpcqN0GrzgMeRV9MUYD4ggzsx58uZCdpeNWLzoHBZAfZBkWzZCxgxtVuLjGZBnPfDvrKDAKZBt7QSjA8HjxyxvcmZCZC2WrNAiLo1ARm3VMwZDZD";
	public static final String ACCOUNT_ID = "346182607";
	public static final String APP_SECRET = "c7fb919e242e28521d5ab934c669c574";
	public static final String CAMPAIGN_ID = "6074546337366";
	public static final APIContext CONTEXT = new APIContext(ACCESS_TOKEN, APP_SECRET);
	

	public static void main(String[] args) {
		try {
			Targeting targeting = new Targeting()
					.setFieldAgeMin((long) 18)
					.setFieldAgeMax((long) 25)
					.setFieldUserDevice(Arrays.asList("iPhone 6s"))
					.setFieldGeoLocations(new TargetingGeoLocation().setFieldCountries(Arrays.asList("US")));

			Campaign campaign = new Campaign(CAMPAIGN_ID, CONTEXT);
			AdAccount ACCOUNT =  new AdAccount(ACCOUNT_ID, CONTEXT);
			ACCOUNT.createAdSet()
			.setName("Java SDK Ad Set")
			.setCampaignId(CAMPAIGN_ID)
			.setStatus(AdSet.EnumStatus.VALUE_PAUSED)
			.setBillingEvent(AdSet.EnumBillingEvent.VALUE_IMPRESSIONS)
			.setTargeting(targeting)
	        .setDailyBudget(10000L)
	        .setBidAmount(10000L)
			.setOptimizationGoal(AdSet.EnumOptimizationGoal.VALUE_IMPRESSIONS).execute();
			
			System.out.println("Ad Set Berhasil dibuat!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

	}

}
