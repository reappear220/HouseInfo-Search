package com.mycom.myapp.dto;

import java.util.Date;

public class MemberDto {

   private String userId;
   private String userPwd;
   private String userName;
   //private String userPhone;
   private String email;
   private String addressGu;
   private String addressDong;
   private String joindate;
   private Date userRegisterDate;
   
   private String userClsf;
   private String userClsfName;

   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getUserPwd() {
      return userPwd;
   }

   public void setUserPwd(String userPwd) {
      this.userPwd = userPwd;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

//   public String getUserPhone() {
//      return userPhone;
//   }
//
//   public void setUserPhone(String userPhone) {
//      this.userPhone = userPhone;
//   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getAddressGu() {
      return addressGu;
   }

   public void setAddressGu(String addressGu) {
      this.addressGu = addressGu;
   }
   
   
   public String getAddressDong() {
      return addressDong;
   }

   public void setAddressDong(String addressDong) {
      this.addressDong = addressDong;
   }

   public String getJoindate() {
      return joindate;
   }

   public void setJoindate(String joindate) {
      this.joindate = joindate;
   }
//   public String getUserProfileImageUrl() {
//      return "/";
//   }
//   public void setUserProfileImageUrl(String userProfileImageUrl) {
//      if( userProfileImageUrl == null || "null".equals(userProfileImageUrl) || "".equals(userProfileImageUrl)) {
//         this.userProfileImageUrl = "/img/noProfile.png";
//      }else {
//         this.userProfileImageUrl = userProfileImageUrl;
//      }
//   }
   
   public Date getUserRegisterDate() {
      return userRegisterDate;
   }
   public void setUserRegisterDate(Date userRegisterDate) {
      this.userRegisterDate = userRegisterDate;
   }

   public String getUserClsf() {
      return userClsf;
   }

   public void setUserClsf(String userClsf) {
      this.userClsf = userClsf;
   }

   public String getUserClsfName() {
      return userClsfName;
   }

   public void setUserClsfName(String userClsfName) {
      this.userClsfName = userClsfName;
   }
   
   
}