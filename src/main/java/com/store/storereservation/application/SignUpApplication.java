//package com.store.storereservation.application;
//
//
//import lombok.RequiredArgsConstructor;
////import org.apache.commons.lang3.RandomStringUtils;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//@RequiredArgsConstructor
//public class SignUpApplication {
//    private final SignUpCustomerService signUpCustomerService;
//
////    public void customerVerify(String email, String code) {
////        signUpCustomerService.verifyEmail(email,code);
////    }
//
//    public String customerSignUp(SignUpForm form) {
//        if (signUpCustomerService.isEmailExist(form.getEmail())) {
//            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
//        }else{
//            Customer customer = signUpCustomerService.signUp(form);
//            LocalDateTime now = LocalDateTime.now();
//
//            String code = getRandomCode();
//
//            SendMailForm sendMailForm = SendMailForm.builder()
//                    .from("tester@mytest.com")
//                    .to(form.getEmail())
//                    .subject("Verification Email")
//                    .text(getVerificationEmailBody(form.getEmail(), form.getName(), code))
//                    .build();
//
//
//            signUpCustomerService.ChangeCustomerValidateEmail(customer.getId(),code);
//
//            return "회원 가입 성공!";
//        }
//    }
//
//    private String getRandomCode() {
//        return RandomStringUtils.random(10, true, true);
//    }
//
//
//
//    private String getVerificationEmailBody(String email, String name, String code) {
//        StringBuilder sb = new StringBuilder();
//        return sb.append("Hello").append(name).append("! Please click LInke for verification.\n\n")
//                .append("http//localhost:8080/customer/signup/verify?email=")
//                .append(email)
//                .append("&code=")
//                .append(code).toString();
//    }
//
//}
