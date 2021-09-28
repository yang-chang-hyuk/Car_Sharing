<template>
    <div id="signin">
        <div class="form">
            <div>ID: <input type="text" v-model="inputId"/></div><br/>
            <div>PW: <input type="text" v-model="inputPw"/></div><br/>
            <div><button @click="signIn()">로그인</button></div>
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
                    sessionStorage.setItem('_UserConn-ID', thisVue.userData.userId)
                    sessionStorage.setItem('_UserConn-PASSWORD', thisVue.userData.userPw)
                    thisVue.$router.push({
                        name: 'AppMain'
                    })
                }
            }).catch(function(error) {
                console.error(error)
            })
        }
    },
    mounted: function() {
        sessionStorage.removeItem('_UserConn-ID')
        sessionStorage.removeItem('_UserConn-PASSWORD')
    }
}
</script>

<style scoped>
#signin {
    margin: 0 auto;
    width: 560px;
}
.form {
    margin: 32px;
}
.form div {
    margin-bottom: 4px;
}
</style>