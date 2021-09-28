<template>
  <div class="mypage-body">
      <h3 style="margin-bottom: 50px; margin-top: 40px; border-bottom: 3px solid #84C2BD; padding-bottom: 30px;">회원가입</h3>
      <div class="Sign-contents">
        <label for="userId">
          이메일 * 
          <span v-if="checkFlag && !userId" style="color: #84C2BD; margin-left: 10px;">
            이메일을 입력해주세요
          </span>
          <span v-if="!isUserIdValid && userId" style="color: red; margin-left: 10px;">
            이메일 형식을 확인해주세요
          </span>
          <span v-if="isVerifyEmail == 2 && userId" style="color:red; margin-left: 10px;">
            중복된 이메일입니다
          </span>
          <span v-if="isVerifyEmail == 1 && isUserIdValid" style="color: #84C2BD; margin-left: 10px;">
            사용 가능한 이메일입니다
          </span>
        </label>
        <br>
        <input type="email" id="userId" name="userId" v-model="userId" placeholder="이메일" style="width: 80%; height: 40px; margin-right: 20px;">
        <button type="button" @click="verifyEmail()" style="background-color: #84C2BD; color: white; height: 40px;border-radius: 5px; border: 1px solid silver">중복확인</button>
      </div>
      <div class="Sign-contents">
        <label for="userPw">
          비밀번호 *
          <span v-if="checkFlag && !userPw" style="color: #84C2BD; margin-left: 10px;">
            비밀번호를 입력해주세요
          </span>
          <span v-if="!CheckPw() && userPw" style="color: red; margin-left: 10px;">8자리 이상 입력해주세요</span>
        </label>
        <br>
        <input type="password" id="userPw" name="userPw" v-model="userPw" maxlength="16" placeholder="8자리 이상으로 입력해주세요" style="width: 80%; height: 40px;">
      </div>
      <div class="Sign-contents">
        <label for="userPw">
          비밀번호 확인 *
          <span v-if="!passwordCheckFlag" style="color: red; margin-left: 10px;">
          비밀번호가 동일하지 않습니다.
        </span>
        </label>
        <br>
        <input type="password" id="passwordCheck" name="passwordCheck" v-model="passwordCheck" maxlength="16" @blur="passwordCheckValid" placeholder="비밀번호 확인" style="width: 80%; height: 40px">
      </div>
      <div class="Sign-contents1">
        <label for="userNickName">
          닉네임 *
          <span v-if="checkFlag && !userNickName" style="color: #84C2BD; margin-left: 10px;">
          닉네임을 입력해주세요
        </span>
        </label>
        <br>
        <input type="text" id="userNickName" name="userNickName" v-model="userNickName" placeholder="닉네임" style="height: 40px">
      </div>
      <div class="Sign-contents1">
        <label for="userName">
          이름 *
          <span v-if="checkFlag && !userName" style="color: #84C2BD; margin-left: 10px;">
          이름을 입력하세요
        </span>
        </label>
        <br>
        <input type="text" id="userName" name="userName" v-model="userName" placeholder="이름" style="height: 40px; ">
      </div>
      
      <div class="Sign-contents">
        <label for="userRegNum">
          주민등록 번호 *
          <span v-if="checkFlag && !userRegNum" style="color: #84C2BD; margin-left: 10px;">
          주민등록 번호를 입력해주세요
        </span>
        </label>
        <br>
        <input type="text" id="userRegNum" name="userRegNum" v-model="userRegNum" placeholder="생년월일" style="height: 40px;"
        oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" maxlength="6"> ㅡ
        <input type="text" maxlength="1" id="userRegNumGender" name="userRegNumGender"
        v-model="userRegNumGender" style="height: 40px; width: 40px;"
        oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
        <span style="font-size: 30px">●●●●●●</span>
      </div>
      <div class="Sign-contents">
        <label for="userPhone">
          전화번호 *
          <span v-if="checkFlag && !this.userPhone" style="color: #84C2BD; margin-left: 10px;">
          휴대폰 번호를 입력해주세요.
        </span>
        <span v-if="!checkPhoneNumber() && this.userPhone.length > 0" style="color:#84c2bd; margin-left: 10px;">
          휴대폰 번호를 정확히 입력해주세요.
        </span>
        </label>
        <br>
        <input type="text" id="userPhone" name="userPhone" v-model="userPhone" placeholder="휴대폰 번호 (' - '제외)" style="width: 50%; height: 40px" maxlength="11" @focus="checkFlag = false"
        oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
      </div>
      <div style="margin-top: 30px">
        <button type="button" @click="moveJoin" style="background-color: #84C2BD; color: white; height: 40px;border-radius: 5px; margin-right: 20px; border: 1px solid silver; border: 1px solid silver; padding: 0px 40px 0px 40px;">회원가입</button>
        <button type="button" v-on:click="moveMain" style="background-color: #84C2BD; color: white; height: 40px;border-radius: 5px; border: 1px solid silver; padding: 0px 50px 0px 50px;">
          취소
        </button>
      </div>
  </div>
</template>

<script>

export default {
    data() {
        return {
            userId: '',
            userPw: '',
            userName: '',
            userRegNum: '',
            userRegNumGender: '',
            userNickName: '',
            userPhone: '',
            passwordCheck: null,
            passwordCheckFlag: true,
            checkFlag: false,
            isVerifyEmail: 0,
            licenseType: '',
            licenseNum: '',
            licensePassDate: ''
        }
    },
    methods: {
        moveJoin() {
          this.checkFlag = true;
          if(
            !this.isEmpty(this.userName)&&
            !this.isEmpty(this.userRegNum)&&
            !this.isEmpty(this.userPhone)&&
            !this.isEmpty(this.userId)&&
            !this.isEmpty(this.userNickName)&&
            !this.isEmpty(this.userPw)&&
            !this.isEmpty(this.userPhone)
            ){
            let obj = this;

            // 회원가입
            obj.$axios.post('http://localhost:8090/vue/userJoin', {
              userId: this.userId,
              userPw: this.userPw,
              userName : this.userName,
              userRegnum : this. userRegNum,
              userRegnumGender : this.userRegNumGender,
              userNickname : this.userNickName,
              userPhone : this.userPhone
            })
            .then(function(res) {
                console.log(res)

                //  면허 테이블에 데이터 삽입

                obj.$router.push({ name: "AppMain" });
            })
            .catch(function(err) {
                console.log("비동기 통신 실패");
                console.log(err);
            });
          }  
        },
        moveMain() {
            this.$router.push({ name: 'AppMain' });
        },
        passwordCheckValid() {
          if (this.userPw === this.passwordCheck) {
            this.passwordCheckFlag = true
          } else {
            this.passwordCheckFlag = false
          }
        },
        isEmpty(data) {
          if(data === "" || data === null || data === undefined || this.userPw != this.passwordCheck || !this.isUserIdValid || (this.isVerifyEmail == 0 || this.isVerifyEmail == 2) || this.checkPhoneNumber() == 0 || this.CheckPw() == 0) {
            return true;
          } else {
            return false;
          }
        },
        
        verifyEmail() {
            let thisVue = this
            thisVue.$axios.get('http://localhost:8090/vue/verifyEmail', {
            params: {
                userId: this.userId
            }
            }).then(function(response) {
                if(response.data == 0) {
                    thisVue.isVerifyEmail = 1
                }
                else
                    thisVue.isVerifyEmail = 2
            }).catch(function(error) {
            console.error(error)
            })
        },
        validateEmail(email) {
            var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(String(email).toLowerCase())
        },
        
        checkPhoneNumber() {
          if(this.userPhone.length == 11) {
            return true;
          } else {
            return false;
          }
        },

        CheckPw() {
          if(this.userPw.length >= 8) {
            return true;
          } else {
            return false;
          }
        }
    },
    computed: {
      isUserIdValid() {
        return this.validateEmail(this.userId);
      }
    }
}
</script>

<style>

  .mypage-body {
    margin: 0 auto;
    width: 900px;
  }

  .Sign-contents {
    text-align: left;
    margin-left: 110px;
    margin-top: 20px;
  }

  .Sign-contents1 {
    text-align: left;
    margin-left: 110px;
    margin-top: 40px;
  }
</style>
