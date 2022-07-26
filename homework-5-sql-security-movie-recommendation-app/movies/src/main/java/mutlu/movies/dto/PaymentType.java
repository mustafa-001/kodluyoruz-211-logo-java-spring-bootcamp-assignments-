package mutlu.movies.dto;

import java.util.Arrays;

public enum PaymentType {
    ONE_MONTH(1),
    THREE_MONTHS(3),
    SIX_MONTHS(6),
    TWELVE_MONTHS(12);

    private final int value;

    PaymentType(int value) {
        this.value = value;
    }

    /**
     * @param months Number of months in integer.
     * @return Corresponding enum value to given months.
     */
    public static PaymentType valueOf(int months) {
        return Arrays.stream(values())
                .filter(paymentType -> paymentType.value == months)
                .findFirst()
                .orElse(null);
    }

    public int getValue() {
        return this.value;

    }
}
