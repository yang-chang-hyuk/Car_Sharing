<template>
  <div id="login">
    <div class="row">
      <div class="col-md-6 mx-auto text-left">
        <div class="form-group mt-3">
          <label for="userId">이메일 : </label>
          <input type="text" class="form-control" name="userId" id="userId" placeholder="이메일을 입력하세요." required v-model="inputId">
        </div>
        <div class="from-group">
          <label for="userPw">비밀번호 : </label>
            <input type="password" class="form-control" id="userPw" name="userPw" placeholder="비밀번호를 입력하세요." required v-model="inputPw">
        </div>
        <div class="row mt-3">
          <div class="col-md-6">
            <button type="button" class="btn btn-my btn-block" @click="signIn()">로그인</button>
          </div>
          <div class="col-md-6">
            <button type="button" class="btn btn-secondary btn-block mb-3" v-on:click="moveMain">취소</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    data: function() {
      return {
        inputId: '',
        inputPw: '',
        userData: null
      }
    },
    methods: {
        signIn: function() {
            const thisVue = this
            thisVue.$axios.get('http://localhost:8090/teamcarsharing/api/GetUser', {
                params: {
                    userId: thisVue.inputId,
                    userPw: thisVue.inputPw
                }
            }).then(function(response) {
                thisVue.userData = response.data
                if(thisVue.userData.length === 0) {
                    alert('아이디 또는 비밀번호가 틀렸습니다.')
                }
                else {
                    // if(thisVue.userData.isBlock) {
                    //     alert('차단된 계정입니다.')
                    //     return
                    // }
                    sessionStorage.setItem('_UserConn-INDEX', thisVue.userData.userIndex)
                    sessionStorage.setItem('_UserConn-ID', thisVue.userData.userId)
                    sessionStorage.setItem('_UserConn-PASSWORD', thisVue.userData.userPw)
                    thisVue.$router.push({
                        name: 'AppMain'
                    })
                }
            }).catch(function(error) {
                console.error(error)
            })
        },
        moveMain() {
          this.$router.push({ name: 'AppMain' });
        }
    },
    mounted: function() {
        sessionStorage.removeItem('_UserConn-INDEX')
        sessionStorage.removeItem('_UserConn-ID')
        sessionStorage.removeItem('_UserConn-PASSWORD')
    }
}
</script>

<style scoped>
  #login {
    margin: 0 auto;
    margin-top: 56px;
    width: 900px;
  }
  .btn-my {
    background-color: rgb(131, 193, 188);
    color: #fff;
  }
</style>