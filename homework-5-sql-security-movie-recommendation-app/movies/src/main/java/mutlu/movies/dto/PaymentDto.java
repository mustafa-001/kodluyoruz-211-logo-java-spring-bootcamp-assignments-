package mutlu.movies.dto;

/**
 * Dto to be send from client and to be send to payment service when requesting a payment process.
 * {@link mutlu.movies.controller.UserController#makePayment(PaymentDto)} 
 * {@link mutlu.movies.service.UserService#makePayment(PaymentDto)}
 */
public class PaymentDto {
    private Long userId;
    private PaymentType paymentType;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
