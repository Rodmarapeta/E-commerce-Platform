package ecommerce.service;

import ecommerce.util.Util;

import java.time.YearMonth;

/**
 * Simulated payment gateway. No money moves and no network calls are made.
 * Valid numbers follow the Luhn checksum, like real card forms expect.
 */
public class PaymentService {
    public static final String TEST_CARD = "4242 4242 4242 4242";
    public static final String DECLINE_CARD = "4000 0000 0000 0002";

    public record CardDetails(String holder, String number, String expiry, String cvv) {
    }

    public record PaymentResult(boolean approved, String reference, String message) {
    }

    public PaymentResult charge(double amount, CardDetails card) {
        if (card == null) {
            return new PaymentResult(false, null, "Enter your card details.");
        }
        String digits = Util.digitsOnly(card.number());
        if (digits.length() < 13 || digits.length() > 19) {
            return new PaymentResult(false, null, "Card number must be 13 to 19 digits.");
        }
        if (!Util.passesLuhn(digits)) {
            return new PaymentResult(false, null, "That card number is not valid (failed checksum).");
        }
        if (digits.equals(Util.digitsOnly(DECLINE_CARD))) {
            return new PaymentResult(false, null, "Card declined by the issuing bank. Try 4242 4242 4242 4242.");
        }
        if (expired(card.expiry())) {
            return new PaymentResult(false, null, "Card expiry must be a future date in MM/YY format.");
        }
        if (!Util.digitsOnly(card.cvv()).matches("\\d{3,4}")) {
            return new PaymentResult(false, null, "CVV must be 3 or 4 digits.");
        }
        if (Util.isBlank(card.holder())) {
            return new PaymentResult(false, null, "Enter the cardholder name.");
        }
        return new PaymentResult(true, Util.reference("PAY"), "Payment approved.");
    }

    public PaymentResult chargeOnDelivery(double amount) {
        return new PaymentResult(true, Util.reference("COD"), "Cash on delivery confirmed.");
    }

    private boolean expired(String expiry) {
        String digits = Util.digitsOnly(expiry);
        if (digits.length() != 4) {
            return true;
        }
        int month = Integer.parseInt(digits.substring(0, 2));
        int year = 2000 + Integer.parseInt(digits.substring(2, 4));
        if (month < 1 || month > 12) {
            return true;
        }
        return YearMonth.of(year, month).isBefore(YearMonth.now());
    }
}
