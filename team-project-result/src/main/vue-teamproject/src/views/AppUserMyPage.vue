<template>
    <div class="container-fluid">
      <div class="MyPage-header">
        <span type="button" v-on:click="moveBack" style="margin-bottom: 50px;">뒤로가기</span>
        <h2 style="font-weight: bold;">마이페이지</h2>
      </div>
      <div class="MyPage-profile">
        <p><input type="file" id="file" class="inputfile" @change="upload"><label for="file" class="input-plus">프로필 이미지 변경</label></p>
        <span v-if="userProfile !== null && userProfile">
          <img v-bind:src="userProfile" alt="" class="img-item">
        </span>
        <span v-else style="float: left;">
          <img src="../assets/img/user-icon.png" alt="" class="img-item">
        </span>
        <h3 style="font-weight: bold; margin: 60px 0px 0px 0px">{{ userNickName }}</h3>
        <span class="mypage-email">{{ userId }}</span>
      </div>
        <div class="MyPage-revise">
          <p style="font-weight: bold; background-color: #84C2BD; height: 30px; border-radius: 5px; color: white;text-align:center">개인정보</p>
          <div style="font-size: 20px; color: gray; font-weight: bold; cursor: pointer;text-align:center" v-on:click="moveProfileRevise">수정</div>
        </div>
        <div class="MyPage-driverLicense">
          <p style="font-weight: bold; background-color: #84C2BD; height: 30px; border-radius: 5px; color: white;text-align:center">면허정보</p>
          <div style="font-size: 20px; color: gray; font-weight: bold; cursor: pointer;text-align:center" @click="moveDriverLicense" v-if="licenseNum === '' || licenseNum === undefined || licenseNum === null">등록</div>
          <div style="font-size: 20px; color: black; font-weight: bold;text-align:center" v-else>
            등록완료
          </div>
        </div>
        <div class="MyPage-paymentMethod">
          <p style="background-color: #84C2BD; height: 30px; font-weight: bold; border-radius: 5px; color: white;text-align:center">결제수단</p>
          <span class="CardReg"  v-on:click="moveCardReg">등록</span>
          <div class="MyCard">
            <p style="text-align: left; border-bottom: 3px solid white; padding-bottom: 10px; font-size: 14px; color: white">결제 카드</p>
            <p style="margin-top: 55px; font-size: 30px; margin-bottom: 0; font-weight: bold;text-align:center" >{{ userCardNum }}</p>
            <div v-if="checkCard()" style="color: red; text-align:center; font-size: 22px;">결제 카드를 등록해주세요</div>
            <p class="userCardValidity-style">{{ userCardValidity }}</p>
            <div style="display:flex">
              <p class="card_name" v-if="!CheckUserName()">{{ userName }}</p>
              <i class="fab fa-cc-visa card_icon" v-if="!CheckIcon()"></i>
            </div>
          </div>
        </div>
        <div class="MyPage-comment" @click="moveReview">
          <span style="float: left;">내가 작성한 댓글 <span style="color: red; margin-left: 15px;">{{ reviews.length }}</span></span><img src="../assets/img/271228-removebg-preview.png" alt="" style="float: right;">
        </div>
        <div class="MyPage-Logout" @click="signOut()">
          <span style="float: left;">로그아웃</span><img src="../assets/img/271228-removebg-preview.png" alt="" style="float: right;">
        </div>
    </div>
</template>

<script>

export default {
  data() {
    return {
      userIndex: 0,
      userNickName: '',
      userId: '',
      userName: '',
      userCardNum: '',
      userCardValidity: '',

      licenseNum: '',
      userProfile: '',
      reviews: []
    }
  },

  methods: {
    upload (e) {
      let file = e.target.files;
      let reader = new FileReader();

      reader.readAsDataURL(file[0]);
      reader.onload = e => {
        this.userProfile = e.target.result;
        this.axios.put('http://localhost:8090/vue/setProfile', {
          userIndex: this.userIndex,
          userProfile: this.userProfile
        }).then(response => {
          console.log(response)
        }).catch(error => {
          console.error(error)
        })
      }
    },
    moveBack() {
      this.$router.push({ name: "AppMain" });
    },
    moveProfileRevise() {
      this.$router.push({ name: "AppUserProfileRevise" });
    },
    moveCardReg() {
      this.$router.push({ name: "AppUserCardReg" })
    },
    moveDriverLicense() {
      this.$router.push({ name: "AppUserDriverLicense", params: {userIndex: this.userIndex} })
    },
    moveReview() {
      this.$router.push({ name: "AppUserReview" })
    },
    signOut: function() {
        sessionStorage.removeItem('_UserConn-INDEX')
        sessionStorage.removeItem('_UserConn-ID')
        sessionStorage.removeItem('_UserConn-PASSWORD')
        this.$router.push({name: 'AppMain'})
    },

    checkCard: function() {
      if(this.userCardNum == '' || this.userCardNum == null) {
        return true;
      } else {
        this.userCardNum = this.userCardNum.substring(0, 4) + '-XXXX-XXXX-' + this.userCardNum.substring(15, 19);
      }
    },
    isEmpty(data) {
      if(data === "" || data === null || data === undefined) {
        return true;
      } else {
        return false;
      }
    },
    CheckUserName: function() {
      if(this.userCardNum == '' || this.userCardNum == null) {
        return true;
      } else {
        this.userName = this.userName.substring(0, 1) + "○○";
        return false;
      }
    },
    CheckIcon: function() {
      if(this.userCardNum == '' || this.userCardNum == null) {
        return true;
      } else {
        return false;
      }
    }
  },
  mounted() {
    let userPage = this;

    // 마이페이지 정보 가져오기
    userPage.$axios.get('http://localhost:8090/vue/userDetail', {
      params: { userIndex: sessionStorage.getItem('_UserConn-INDEX') }
    })
    .then(function(res) {
      userPage.userIndex = res.data.userIndex
      userPage.userNickName = res.data.userNickname
      userPage.userId = res.data.userId
      userPage.userName = res.data.userName
      userPage.userCardNum = res.data.userCardNum
      userPage.userCardValidity = res.data.userCardValidity

      // 면허정보 가져오기
      userPage.axios.get('http://localhost:8090/vue/licenseDetail', {
        params: {userIndex: userPage.userIndex}
      }).then(response => {
        userPage.licenseNum = response.data.licenseNum
      }).catch(error => {
        console.error(error)
      })

      // 프로필 사진 가져오기
      userPage.axios.get('http://localhost:8090/vue/getProfile', {
        params: {
          userIndex: userPage.userIndex
        }
      }).then(response => {
        console.log('성공')
        userPage.userProfile = response.data.userProfile
      }).catch(error => {
        console.error(error);
      })

      // 리뷰정보 가져오기
      userPage.$axios.get('http://localhost:8090/vue/getReview', {
        params: {
          userIndex: userPage.userIndex,
          carIndex: userPage.carIndex
        }
      })
      .then(response => {
        userPage.reviews = response.data
      })
      .catch(error => {
        console.error(error);
      })

    })
    .catch(function(error) {
      console.error(error);
    })
  }

}
</script>

<style scoped>
  @media (min-width: 900px) {
      .container-fluid {
        width: 900px;
      }
  }

  .container-fluid div {
    padding: 10px;
  }

  .container-fluid {
    display: grid;
    margin: 0 auto;
    margin-bottom: 120px;
    gap: 1.3em;
    grid-template-areas: 
    'header header header header'
    'profile profile profile profile'
    'revise revise driverLicense driverLicense'
    'paymentMethod paymentMethod paymentMethod paymentMethod'
    'comment comment comment comment'
    'Logout Logout Logout Logout'
    ;
  }

  .MyPage-header {
    grid-area: header;
    text-align: left;
    margin-top: 15px;
  }

  .MyPage-profile {
    grid-area: profile;
    text-align: left;
    margin-bottom: 30px;
    border-bottom: 3px solid silver;
  }

  .MyPage-revise {
    grid-area: revise;
    border: 1px solid silver;
    border-radius: 7px;
  }
  
  .MyPage-driverLicense {
    grid-area: driverLicense;
    border: 1px solid silver;
    border-radius: 7px;
  }
  
  .MyPage-paymentMethod {
    grid-area: paymentMethod;
    float: left;
    border: 1px solid silver;
    border-radius: 7px;
  }

  .MyPage-comment {
    grid-area: comment;
    border: 1px solid silver;
    border-radius: 7px;
    cursor: pointer;
  }

  .MyPage-Logout {
    cursor: pointer;
    grid-area: Logout;
    border: 1px solid silver;
    border-radius: 7px;
  }

  .mypage-email {
    color: gray;
  }

  .CardReg {
  float: right;
  border: 1px solid silver;
  border-radius: 5px;
  padding: 5px;
  background-color: #84C2BD;
  color: white;
  cursor: pointer;
  }

  .MyCard {
    font-family: 'Fjalla One', 'Noto Sans KR', sans-serif;
    border: 1px solid silver;
    width: 55%;
    margin-left: 22%;
    height: 280px;
    border-radius: 7px;
    background: linear-gradient(to right bottom, gray, silver 40%, black );
    margin-bottom: 10px;
  }

  .card_name {
    text-align: left;
    margin-right: 75%;
    margin-top: 40px;
    font-weight: bold;
  }
  .card_icon {
    font-size: 40px;
    margin: 30px 0px 0px 10px;
    color: #ccc;
  }

  .userCardValidity-style {
    text-align: left;
    margin-left: 13.5%;
    margin-top: 5px;
    /* color: white; */
  }

  .inputfile{
    width: 0px; 
    height: 0px; 
    overflow: hidden;
  }

  .img-item {
    width: 150px;
    height: 150px;
    float: left;
    border-radius: 50%;
    margin-right: 20px;
  }

  .input-plus {
    font-size: 12px;
    border: 1px solid silver;
    padding: 5px;
    margin-left: 20px;
    border-radius: 3px;
  }

</style>