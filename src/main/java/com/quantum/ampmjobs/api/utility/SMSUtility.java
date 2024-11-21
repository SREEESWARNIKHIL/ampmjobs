    package com.quantum.ampmjobs.api.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SMSUtility {
   @Autowired
   private RestTemplate restTemplate;
   @Value("${sms.key}")
   private String key;

   @Async
   public void sentRegistrationOtp(final long mobileNo, final String otp) {
      String msisdnStr = String.valueOf(mobileNo);
      String jsonInput = "{\"filetype\": \"2\",\"msisdn\": [\"" + msisdnStr + "\"],\"language\": \"0\",\"credittype\": \"7\",\"senderid\": \"Quites\",\"templateid\": \"0\",\"message\": \"Dear Subscriber\r\nYour OTP for verifying your account with ampmjobs is:" + otp + "\\n\\n.Please enter this code to complete the verification process, within 5 minutes\r\nPlease do not share this OTP with anyone ---- support@ampmjobs.in QUITES\",\r\n\"ukey\": \"" + this.key + "\"}";
      String url = "https://api.voicensms.in/SMSAPI/webresources/CreateSMSCampaignPost";

      try {
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_JSON);
         HttpEntity<String> requestEntity = new HttpEntity(jsonInput, headers);
         ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class, new Object[0]);
         if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("SMS sent successfully");
         } else {
            System.out.println("SMS failed to sent");
         }
      } catch (Exception var10) {
         System.out.println("SMS failed to sent");
         var10.printStackTrace();
      }

   }

   @Async
   public void sendSMStoShortlistedStudent(final String companyName, final long studentMobile) {
      String msisdnStr = String.valueOf(studentMobile);
      String jsonInput = "{\"filetype\": \"2\",\"msisdn\": [\"" + msisdnStr + "\"],\"language\": \"0\",\"credittype\": \"7\",\"senderid\": \"Quites\",\"templateid\": \"0\",\"message\": \"\"Dear Applicant\\\\n\\\\n\";\r\n\"You have been shortlisted by \" + companyName + \" Employer please login to ampmjobs.in \";\r\n\"dashboard to know the details.\\\\n\\\\n\";\r\n\"All the best ----- support@ampmjobs.in\\\\n\\\\n\";\r\n\"QUITES\"\",\r\n\"ukey\": \"" + this.key + "\"+ \"\"isschd\": \"false\"}";
      String url = "https://api.voicensms.in/SMSAPI/webresources/CreateSMSCampaignPost";

      try {
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_JSON);
         HttpEntity<String> requestEntity = new HttpEntity(jsonInput, headers);
         ResponseEntity<String> responseEntity = this.restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class, new Object[0]);
         if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("SMS sent successfully");
         } else {
            System.out.println("SMS failed to sent");
         }
      } catch (Exception var10) {
         System.out.println("SMS failed to sent");
         var10.printStackTrace();
      }

   }
}
  