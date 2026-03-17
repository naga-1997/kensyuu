const stripe = Stripe('pk_test_51T6mdAIuRW7fZPf97Z5dONg9NccXc1tOU8bzXzmTDgz2wZFT8bOYF3sqnnKfnkMzzx0K7Ysc4i8sl63TPpJPGpe200xRSMIzXB');
const paymentButton = document.querySelector('#paymentButton');

paymentButton.addEventListener('click', () => {
  stripe.redirectToCheckout({
    sessionId: sessionId
  })
});