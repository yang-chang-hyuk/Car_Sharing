<template>
    <div class="mypage-body">
    <span type="button" v-on:click="moveMyPage" class="container">뒤로가기</span>
    <h2 class="container" style="height: 50px; font-weight: bold; margin-top: 25px; text-align: left">면허등록</h2>
    <div style="border: 1px solid #84C2BD"></div>
    <div  class="container">
      <div style="width: 100%">
        <p style="color: #50D594; font-weight: bold; margin-top: 25px; font-size: 25px;">면허증</p>
      </div>
      <div style="border: 1px solid silver; width: 690px; height: 400px; margin: 10px 150px 0px 0px; border-radius: 10px;" class="driver-license">
          <span style="float: left; margin: 10px;">
            <select name="license_type" style="border: none" v-model="licenseType">
              <option value="Class1Normal" selected>1종보통</option>
              <option value="Class2Normal">2종보통</option>
            </select>
            <div v-if="checkFlag && !licenseType" style="color: red">면허 종류를 입력해주세요.</div>
          </span>
          
          <div  style="margin-top: 10px; text-align: right; margin-right: 15px;">
            <span>자동차운전면허증</span>
            <span style="margin-right: 110px"> (Driver's License)</span>
            <input type="text" v-model="licenseNum" @change="checkLicenseNum()" maxlength="12" style="width: 400px; height: 40px; font-size: 24px; font-weight: bold; margin-top: 20px;" placeholder="'-' 제외한 면허 번호를 입력해주세요." oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
            <div v-if="checkFlag && !licenseNum" style="margin-right: 210px; color: red">면허 번호를 입력해주세요.</div>
            <div v-if="!licenseNumCheck() && this.licenseNum.length  > 0" style="margin-right: 230px; color: #84C2BD">12자리를 입력해주세요.</div>
            <input type="text" placeholder="이름을 입력해주세요." style="margin: 10px 178px; height: 40px;" v-model="inputUserName">
            <div v-if="checkFlag && !inputUserName" style="margin-right: 245px; color: red">이름을 입력해주세요.</div>
            <input type="text" v-model="licensePassDate" placeholder="취득일을 입력해주세요." style="margin: 150px 0px 0px 260px; float: left">
            <div v-if="checkFlag && !licensePassDate" style="margin: 152px 50px; color: red">취득일을 입력해주세요.</div>
          </div>
        </div>
        <button type="button" @click="moveReg" style="background-color: #84C2BD; color: white; height: 40px;border-radius: 5px; margin-right: 20px; margin-top: 50px; width: 100%; border: none">등록</button>
    </div>
    </div>
</template>

<script>
export default {
  data () {
    return {
      userIndex : 0,
      userName: '',
      inputUserName: '',
      licenseType: '',
      licenseNum: '',
      licensePassDate: '',
      checkFlag: false
    }
  },

  methods: {
    moveMyPage() {
      this.$router.push({ name: "AppUserMyPage" })
    },
    moveReg() {
      this.checkFlag = true;
      if(this.inputUserName !== this.userName)
        alert('사용자 이름이 일치하지 않습니다.')
      else if(
        !this.isEmpty(this.licenseType) &&
        !this.isEmpty(this.licenseNum) &&
        !this.isEmpty(this.licensePassDate)&&
        !this.isEmpty(this.inputUserName)
      ){

        // 면허정보 값 넣기
        let license = this;
        license.$axios.post('http://localhost:8090/vue/driverlicense', {
          userIndex: license.userIndex,
          licenseType: license.licenseType,
          licenseNum: license.licenseNum,
          licensePassDate: license.licensePassDate            
        })
        .then(function(res) {
          console.log(res);
          license.$router.push({ name: "AppUserMyPage" })
        })
        .catch(function(error) {
          console.log(error);
        })
      }
    },
    checkLicenseNum: function() {
      let first = this.licenseNum.substring(0, 2);
      let second = this.licenseNum.substring(2, 4);
      let third = this.licenseNum.substring(4, 10);
      let fourth = this.licenseNum.substring(10, 12);
      if((first.length + second.length + third.length + fourth.length) == 12) 
        this.licenseNum = first + "-" + second + "-" + third + "-" + fourth
      
    },
    isEmpty(data) {
      if(data == null || data == '' || data == undefined) {
        return true;
      } else {
        return false;
      }
    },
    licenseNumCheck() {
      if(this.licenseNum.length == 12 || this.licenseNum.length == 15) {
        return true;
      } else {
        return false;
      }
    }
  },

  mounted: function() {
    let thisVue = this;

    // 유저 정보 가져오기
    thisVue.$axios.get('http://localhost:8090/vue/userDetail', {
      params: { userIndex: sessionStorage.getItem('_UserConn-INDEX') }
    })
    .then(function(res) {
      thisVue.userIndex = res.data.userIndex
      thisVue.userName = res.data.userName
    })
    .catch(function(error) {
      console.error(error);
    })
  }
    
    
}
</script>

<style>
    .driver-license {
    background: linear-gradient(to right bottom, #D8EEF9, #F5F5DC 40%, #B9CD8E);
  }
</style>