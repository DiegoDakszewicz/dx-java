package mercadopago.resources;

import com.google.gson.JsonObject;
import com.mercadopago.MercadoPago;
import com.mercadopago.core.MPApiResponse;
import com.mercadopago.core.MPBase;
import com.mercadopago.core.MPCoreUtils;
import com.mercadopago.core.annotations.rest.PayloadType;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.exceptions.MPRestException;
import com.mercadopago.net.HttpMethod;
import com.mercadopago.net.MPRestClient;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.payment.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Mercado Pago MercadoPago
 * Payment Resource Test
 *
 * Created by Eduardo Paoletta on 12/6/16.
 */
public class PaymentTest {

    @BeforeClass
    public static void beforeTest() throws MPException {
        MercadoPago.SDK.cleanConfiguration();
        MercadoPago.SDK.setConfiguration("credentials.properties");
    }

    @Test
    public void paymentGetterSetterTests() {
        Payment payment = new Payment()
                .setPayer(
                        new Payer()
                                .setType(Payer.type.guest)
                                .setId("id")
                                .setEmail("email@fakeemail.com")
                                .setIdentification(
                                        new Identification()
                                                .setType("type")
                                                .setNumber("number"))
                                .setAddress(
                                        new Address()
                                                .setStreetName("ARIAS")
                                                .setStreetNumber(3751)
                                                .setZipCode("C1430CRI")
                                                .setNeighborhood("CABA")
                                                .setCity("Buenos Aires")
                                                .setFederalUnit("BA")
                                )
                        )
                .setBinaryMode(Boolean.TRUE)
                .setOrder(
                        new Order()
                                .setId(1234l)
                                .setType(Order.Type.mercadopago))
                .setExternalReference("externalReference")
                .setDescription("description")
                .setMetadata(new JsonObject())
                .setTransactionAmount(.01f)
                .setCouponAmount(.01f)
                .setCampaignId(1)
                .setCouponCode("couponCode")
                .setDifferentialPricingId(1)
                .setApplicationFee(.01f)
                .setCapture(Boolean.TRUE)
                .setPaymentMethodId("paymentMethodId")
                .setIssuerId("issuerId")
                .setToken("token")
                .setStatementDescriptor("statementDescriptor")
                .setInstallments(1)
                .setNotificationUrl("notificationUrl")
                .setAdditionalInfo(
                        new AdditionalInfo()
                                .appendItem(
                                        new Item()
                                                .setId("id")
                                                .setTitle("title")
                                                .setDescription("description")
                                                .setPictureUrl("pictureUrl")
                                                .setCategoryId("categoryId")
                                                .setQuantity(1)
                                                .setUnitPrice(.01f))
                                .setPayer(
                                        new AdditionalInfoPayer()
                                                .setFirstName("firstName")
                                                .setLastName("lastName")
                                                .setPhone(
                                                        new Phone()
                                                                .setAreaCode("000")
                                                                .setNumber("0000-0000"))
                                                .setAddress(
                                                        new Address()
                                                                .setZipCode("0000")
                                                                .setStreetName("streetName")
                                                                .setStreetNumber(1234))
                                                .setRegistrationDate(new Date()))
                                .setShipments(
                                        new Shipments()
                                                .setReceiverAddress(
                                                        new AddressReceiver()
                                                                .setZipCode("0000")
                                                                .setStreetName("streetName")
                                                                .setStreetNumber(1234)
                                                                .setFloor("floor")
                                                                .setApartment("apartment"))));

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

        JsonObject jsonPayment = MPCoreUtils.getJsonFromResource(payment);
        assertNotNull(jsonPayment);

        JsonObject jsonPayer = (JsonObject) jsonPayment.get("payer");
        assertEquals(payment.getPayer().getType().toString(), jsonPayer.get("type").getAsString());
        assertEquals(payment.getPayer().getId(), jsonPayer.get("id").getAsString());
        assertEquals(payment.getPayer().getEmail(), jsonPayer.get("email").getAsString());
        JsonObject jsonPayerIdentification = (JsonObject) jsonPayer.get("identification");
        assertEquals(payment.getPayer().getIdentification().getType(), jsonPayerIdentification.get("type").getAsString());
        assertEquals(payment.getPayer().getIdentification().getNumber(), jsonPayerIdentification.get("number").getAsString());
        assertTrue(jsonPayment.get("binary_mode").getAsBoolean());
        JsonObject jsonOrder = (JsonObject) jsonPayment.get("order");
        assertEquals(payment.getOrder().getId().longValue(), jsonOrder.get("id").getAsLong());
        assertEquals(payment.getOrder().getType().toString(), jsonOrder.get("type").getAsString());
        assertEquals(payment.getExternalReference(), jsonPayment.get("external_reference").getAsString());
        assertEquals(payment.getDescription(), jsonPayment.get("description").getAsString());
        assertEquals(payment.getMetadata(), jsonPayment.get("metadata"));
        assertEquals(payment.getTransactionAmount(), jsonPayment.get("transaction_amount").getAsFloat(), 0f);
        assertEquals(payment.getCouponAmount(), jsonPayment.get("coupon_amount").getAsFloat(), 0f);
        assertEquals(payment.getDifferentialPricingId().longValue(), jsonPayment.get("differential_pricing_id").getAsLong());
        assertEquals(payment.getPaymentMethodId(), jsonPayment.get("payment_method_id").getAsString());
        assertEquals(payment.getIssuerId(), jsonPayment.get("issuer_id").getAsString());
        assertEquals(payment.getStatementDescriptor(), jsonPayment.get("statement_descriptor").getAsString());
        assertEquals(payment.getInstallments().longValue(), jsonPayment.get("installments").getAsLong());
        assertEquals(payment.getNotificationUrl(), jsonPayment.get("notification_url").getAsString());

    }

    @Test
    public void paymentLoadTest() throws MPException {
        Payment payment = Payment.findById("2278812", MPBase.WITH_CACHE);
        assertEquals(200, payment.getLastApiResponse().getStatusCode());
        assertEquals("2278812", payment.getId());
        assertEquals("regular_payment", payment.getOperationType().toString());
        assertEquals(Float.valueOf(100f), payment.getTransactionAmount());
        assertEquals("accredited", payment.getStatusDetail());
        assertTrue(payment.getCaptured());
        assertEquals(Integer.valueOf(1), payment.getInstallments());
        assertFalse(payment.getLastApiResponse().fromCache);

        payment = Payment.findById("2278812", MPBase.WITH_CACHE);
        assertTrue(payment.getLastApiResponse().fromCache);
    }

    @Test
    public void paymentPutTest() throws MPException {
        String token = getCardToken();

        Payer payer = new Payer();
        payer.setEmail("test_user_93364321@testuser.com");

        Payment payment = new Payment();
        payment.setTransactionAmount(100f);
        payment.setPaymentMethodId("visa");
        payment.setDescription("Payment test 1 peso");
        payment.setToken(token);
        payment.setInstallments(1);
        payment.setPayer(payer);
        payment.setCapture(Boolean.FALSE);

        payment.save();
        assertEquals(201, payment.getLastApiResponse().getStatusCode());
        assertNotNull(payment.getId());
        assertFalse(payment.getCaptured());

        payment.setCapture(Boolean.TRUE);
        payment.update();
        assertEquals(200, payment.getLastApiResponse().getStatusCode());
        assertTrue(payment.getCaptured());

    }

    private String getCardToken() throws MPException {
        JsonObject jsonPayload = new JsonObject();

        Random rnd = new Random();

        int expiration_year = rnd.nextInt(20) + 2019;
        int expiration_month = 1 + rnd.nextInt(10) + 1;
        int security_code = rnd.nextInt(900) + 100;

        jsonPayload.addProperty("card_number", "4509953566233704");
        jsonPayload.addProperty("security_code", String.valueOf(security_code));
        jsonPayload.addProperty("expiration_year", expiration_year);
        jsonPayload.addProperty("expiration_month", expiration_month);

        JsonObject identification = new JsonObject();
        identification.addProperty("type", "DNI");
        identification.addProperty("number", "12345678");

        JsonObject cardHolder = new JsonObject();

        cardHolder.addProperty("name", "APRO");
        cardHolder.add("identification", identification);

        jsonPayload.add("cardholder", cardHolder);

        MPApiResponse response;
        try {
            MPRestClient client = new MPRestClient();
            response = client.executeRequest(
                    HttpMethod.POST,
                    MercadoPago.SDK.getBaseUrl() + "/v1/card_tokens?public_key=" + System.getenv("PUBLIC_KEY_TEST_OK"),
                    PayloadType.JSON,
                    jsonPayload,
                    null);
        } catch (MPRestException rex) {
            throw new MPException(rex);
        }
        return ((JsonObject) response.getJsonElementResponse()).get("id").getAsString();
    }

    @Test
    public void paymentTest() throws MPException {
        //String token = getCardToken();

        Payer payer = new Payer();
        payer.setEmail("tSADF_93364321@testuser.com");

        Payment payment = new Payment();
        payment.setTransactionAmount(100f);
        payment.setPaymentMethodId("visa");
        payment.setDescription("Payment test 1 peso");
        payment.setToken(getCardToken());
        payment.setInstallments(1);
        payment.setPayer(payer);

        payment.save();

        assertEquals(201, payment.getLastApiResponse().getStatusCode());
        assertNotNull(payment.getId());
        assertEquals("approved", payment.getStatus().toString());
        assertEquals("accredited", payment.getStatusDetail());
        assertTrue(payment.getCaptured());
        assertEquals("credit_card", payment.getPaymentTypeId().toString());
    }

}

