package com.github.zakariandys.MarketingAPI;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.Ad;
import com.facebook.ads.sdk.AdAccount;
import com.facebook.ads.sdk.Campaign;
import com.facebook.ads.sdk.AdSet;
import com.facebook.ads.sdk.BatchRequest;
import com.facebook.ads.sdk.Targeting;
import com.facebook.ads.sdk.TargetingGeoLocation;
import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.APIResponse;

public class BatchMode {

	public static final String ACCESS_TOKEN = "EAAEomGqMfMEBANYpgZBNZCu4iwrpPk1MfuJzvbqxHVEP9cA3jJVbpZC4CiecdTpJUVs3AUZB5IaEE2c2BZCvlW2Ap0grsdSZAcLmhK23zSaYAcZCWIs3y4EUAUZCg1M3rRHYRloQQZAE4tJ6oNbj13Ebcj84zT6lUIAiycZAjPK0PecF8QwjL7YIay";
	public static final String ACCOUNT_ID = "115690385586921";
	public static final String APP_SECRET = "afe203dce827aea1f16d3bb8cf6146ff";
	public static final File imageFile = new File("D:/EclipseNeonProjects/MarketingAPI/src/main/java/com/github/zakariandys/MarketingAPI/pemandangan.jpg");

	public static final APIContext context = new APIContext(ACCESS_TOKEN, APP_SECRET).enableDebug(true);

	public static void main(String[] args) {
		try {
			Targeting targeting = new Targeting()
					.setFieldGeoLocations(new TargetingGeoLocation().setFieldCountries(Arrays.asList("US")));
			AdAccount account = new AdAccount(ACCOUNT_ID, context);

			// Creation of Ad
			BatchRequest batch = new BatchRequest(context);
			account.createCampaign().setName("Budi Luhur2")
					.setObjective(Campaign.EnumObjective.VALUE_LINK_CLICKS)
					.setStatus(Campaign.EnumStatus.VALUE_PAUSED).addToBatch(batch, "campaignRequest");
			account.createAdSet().setName("Adset Test2").setCampaignId("{result=campaignRequest:$.id}")
					.setStatus(AdSet.EnumStatus.VALUE_PAUSED).setBillingEvent(AdSet.EnumBillingEvent.VALUE_IMPRESSIONS)
					.setDailyBudget(10000L).setBidAmount(1000L)
					.setOptimizationGoal(AdSet.EnumOptimizationGoal.VALUE_IMPRESSIONS).setTargeting(targeting)
					.addToBatch(batch, "adsetRequest");
			account.createAdImage().addUploadFile("file", imageFile).addToBatch(batch, "imageRequest");
			account.createAdCreative().setTitle("Creative Ad Test2").setBody("Java SDK Batch Test Creative2")
					.setImageHash("{result=imageRequest:$.images.*.hash}").setLinkUrl("www.facebook.com")
					.setObjectUrl("www.facebook.com").addToBatch(batch, "creativeRequest");
			account.createAd().setName("Ad Test2").setAdsetId("{result=adsetRequest:$.id}")
					.setCreative("{creative_id:{result=creativeRequest:$.id}}").setStatus("PAUSED").setBidAmount(1000L)
					.addToBatch(batch);
			List<APIResponse> responses = batch.execute();

			// Obtain the IDs of the newly created objects
			Ad ad =  ((Ad) responses.get(4)).fetch();
			AdSet adSet = new AdSet(ad.getFieldAdsetId(), context).fetch();
			Campaign campaign = new Campaign(adSet.getFieldCampaignId(), context);

			// Load
			batch = new BatchRequest(context);
			ad.get().requestAllFields().addToBatch(batch);
			adSet.get().requestAllFields().addToBatch(batch);
			campaign.get().requestAllFields().addToBatch(batch);
			responses = batch.execute();

			System.out.println((Ad) responses.get(0));
			System.out.println((AdSet) responses.get(1));
			System.out.println((Campaign) responses.get(2));

//			 Delete
//			batch = new BatchRequest(context);
//			// ad.delete().addToBatch(batch); // Deleting campaign automatically
//			// deletes ad and adset.
//			// adSet.delete().addToBatch(batch); // Deleting campaign
//			// automatically deletes ad and adset.
////			campaign.delete().addToBatch(batch);
//			responses = batch.execute();
			System.out.println(responses.get(0));
		} catch (APIException e) {
			System.out.println("Errornya karena "+e);
			e.printStackTrace();
		}catch(Exception er){

		}
	}
}