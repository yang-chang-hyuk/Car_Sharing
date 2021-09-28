<template>
  <div class="mypage-body">
    <p type="button" v-on:click="moveMain" class="moveBack-btn">뒤로가기</p>
    <h2 class="container" style="height: 50px; font-weight: bold; margin-top: 25px; text-align: left">개인정보</h2>
    <div style="border: 1px solid #84C2BD"></div>
    <div  class="container">
      <div style="width: 100%">
        <p style="color: #50D594; font-weight: bold; margin-top: 25px; font-size: 25px;">기본정보</p>
      </div>
      <div style="border-bottom:2px solid silver; padding-bottom: 10px">
        <p style="font-weight: bold;">이메일</p>
        <span>{{ userId }}</span>
      </div>
      <div class="profile-Revise">
        <p style="font-weight: bold;">이름</p>
        <span>{{ userName }}</span>
      </div>
      <div class="profile-Revise">
        <p style="font-weight: bold;">생년월일</p>
        <span>{{ userRegNum }}</span>
      </div>
      <div class="profile-Revise">
        <p style="font-weight: bold;">닉네임</p>
        <input type="text" size="40px" v-model="userNickName">
      </div>
      <div class="profile-Revise">
        <p style="font-weight: bold;">휴대폰번호</p>
        <input type="text" size="40px" v-model="userPhone">
      </div>
      <button type="button" class="profile-ReviseButton" v-on:click="userUpdate">확인</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      DetailUserIndex: 2,

      userIndex: 0,
      userId: '',
      userName: '',
      userRegNum: '',
      userNickName: '',
      userPhone: ''
    }
    
  },
  methods: {
    moveMain() {
      this.$router.push({ name: "AppUserMyPage" });
    },
    userUpdate() {
      let thisVue = this;

      // 유저정보 수정하기
      thisVue.$axios.put('http://localhost:8090/vue/userRevise' ,{
        userIndex: thisVue.userIndex,
        userNickname: thisVue.userNickName,
        userPhone: thisVue.userPhone
      })
      .then(function(res) {
        console.log(res);
        thisVue.$router.push({ name : "AppUserMyPage" });
      })
      .catch(function(error) {
        console.log(error);
      })
    }
  },
  mounted() {
    let userObj = this;

    // 유저정보 가져오기
    userObj.$axios.get('http://localhost:8090/vue/userDetail', {
      params: { userIndex: sessionStorage.getItem('_UserConn-INDEX') }
    })
    .then(function(res) {
      
      userObj.userIndex = res.data.userIndex;
      userObj.userId = res.data.userId;
      userObj.userName = res.data.userName;
      userObj.userRegNum = res.data.userRegnum;
      userObj.userNickName = res.data.userNickname;
      userObj.userPhone = res.data.userPhone;
    })
    .catch(function(error) {
      console.error(error);
    })
  }
}
</script>

<style>
  .container {
    text-align: left;  
  }

  .profile-Revise {
    margin-top: 50px;
    border-bottom:2px solid silver;
    padding-bottom: 10px;
  }

  .profile-ReviseButton {
    margin-top: 30px;
    width: 100%;
    border: none;
    padding: 10px;
    border-radius: 10px;
    background-color: #84C2BD;
    color: white;
    border: 1px solid silver;
  }

  .mypage-body {
    margin: 0 auto;
    margin-bottom: 120px;
    width: 900px;
  }

  .moveBack-btn {
    text-align: left;
    margin: 25px 0px 50px 0px;
    cursor: pointer;
  }
</style>