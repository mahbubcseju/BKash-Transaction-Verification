# AutomatedBKashTransactionVerificationUsingAndroidForWebsite

**bKash Verification System**

In Bnagladesh most transaction system is based on bKash.  When someone wants to buy something from any website, he/she has to submit the transaction ID in the website. How website verified whether transaction ID is valid or not ?Answer is using some paid API provided by bKash. Sometimes it is too much hard for some people to get these API . So I build an Android App based on:


  1. Receiver must have an android mobile.
  2. When sender send the money to the receiver, Receiver will get an SMS automatically from bKash.
  3. This app will read the sms using Broadcast module which is only from bKash.
  4. Parse that sms to find the sender mobile number, Transaction ID, Transaction Balance, Transaction Date.
  5. After that this app will store this info in SQLlite database.
  6. And shows the data as like following.
  7. Send the data using API to the website.
  8. In the website, server can easily check whether sender's submitted transaction info and this info is same or not .

![alt text ](apps.png)


