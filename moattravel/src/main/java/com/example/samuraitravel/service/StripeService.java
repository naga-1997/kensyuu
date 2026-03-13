package com.example.samuraitravel.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.samuraitravel.form.ReservationRegisterForm;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionRetrieveParams;

import jakarta.servlet.http.HttpServletRequest;


@Service
public class StripeService {
	@Value("${stripe.api-key}")
	private String stripeApiKey;
	private final ReservationService reservationService;

	public StripeService(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	// セッションを作成し、Stripeに必要な情報を返す
	public String createStripeSession(
			String houseName,
			ReservationRegisterForm reservationRegisterForm,
			HttpServletRequest httpServletRequest) {

		Stripe.apiKey = stripeApiKey;

		String requestUrl = new String(httpServletRequest.getRequestURL().toString());

		SessionCreateParams params = SessionCreateParams.builder()
				.addPaymentMethodType(
						SessionCreateParams.PaymentMethodType.CARD)
				.addLineItem(
						SessionCreateParams.LineItem.builder()
								.setPriceData(
										SessionCreateParams.LineItem.PriceData.builder()
												.setCurrency("jpy")
												.setUnitAmount(
														(long) reservationRegisterForm.getAmount())
												.setProductData(
														SessionCreateParams.LineItem.PriceData.ProductData
																.builder()
																.setName(houseName)
																.build())
												.build())
								.setQuantity(1L)
								.build())
				.setMode(SessionCreateParams.Mode.PAYMENT)
				.setSuccessUrl(
						requestUrl.replaceAll(
								"/houses/[0-9]+/reservations/confirm",
								"") + "/reservations?reserved")
				.setCancelUrl(
						requestUrl.replace(
								"/reservations/confirm",
								""))
				.setPaymentIntentData(
						SessionCreateParams.PaymentIntentData.builder()
								.putMetadata(
										"houseId",
										reservationRegisterForm.getHouseId().toString())

								.putMetadata(
										"userId",
										reservationRegisterForm.getUserId().toString())
								.putMetadata(
										"checkinDate",
										reservationRegisterForm.getCheckinDate())
								.putMetadata(
										"checkoutDate",
										reservationRegisterForm.getCheckoutDate())
								.putMetadata(
										"numberOfPeople",
										reservationRegisterForm.getNumberOfPeople().toString())
								.putMetadata(
										"amount",
										reservationRegisterForm.getAmount().toString())
								.build())
				.build();
		try {
			Session session = Session.create(params);
			return session.getId();
		} catch (StripeException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	//セッションから予約情報を取得し、ReservationServiceクラスを介してデータベースに登録する
	public void processSessionCompleted(Event event) {
	    System.out.println("[WEBHOOK] processSessionCompleted() start");

	    String sessionId = null;
	    try {
	        // getRawJson()でJSON文字列を取得 → Jackson で id だけ抜く
	        String rawJson = event.getDataObjectDeserializer().getRawJson();
	        System.out.println("[WEBHOOK] rawJson=" + rawJson);

	        // ObjectMapperでidフィールドだけ取得
	        ObjectMapper mapper = new ObjectMapper();
	        JsonNode root = mapper.readTree(rawJson);
	        sessionId = root.get("id").asText();
	        System.out.println("[WEBHOOK] sessionId=" + sessionId);

	    } catch (Exception e) {
	        System.out.println("[WEBHOOK] Failed to extract sessionId");
	        e.printStackTrace();
	        return;
	    }

	    // Session.retrieve で payment_intent を展開して取得
	    try {
	        SessionRetrieveParams params = SessionRetrieveParams.builder()
	                .addExpand("payment_intent")
	                .build();
	        Session session = Session.retrieve(sessionId, params, null);

	        Map<String, String> metadata = session.getPaymentIntentObject().getMetadata();
	        System.out.println("[WEBHOOK] payment_intent metadata=" + metadata);

	        if (metadata == null || metadata.isEmpty()) {
	            System.out.println("[WEBHOOK] metadata is empty. 保存スキップ");
	            return;
	        }

	        reservationService.create(metadata);
	        System.out.println("[WEBHOOK] reservation saved successfully");

	    } catch (StripeException e) {
	        System.out.println("[WEBHOOK] StripeException during retrieve");
	        e.printStackTrace();
	    }
	}
}