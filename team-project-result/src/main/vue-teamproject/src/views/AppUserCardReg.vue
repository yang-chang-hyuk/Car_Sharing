<template>
    <div class="mypage-body">
        <div class="container-fluid">
            <div class="CardReg-header">
                <button type="button" v-on:click="moveMyPage" style="margin-bottom: 50px">뒤로가기</button>
                <h2 style="font-weight: bold;">카드등록</h2>
            </div>
            <div class="CardReg-CardNum">
                <p>카드번호</p>
                <input type="text" v-model="userCardNum" placeholder="본인명의 카드번호를 '-'없이 입력하세요." style="width: 95%; border: none; border-bottom: 1px solid silver; font-size: 20px;" maxlength="16" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"  @change="CardNumFormat()">
                <div v-if="!cardNumValidCheck() && userCardNum" style="color: red;">
                카드정보 16자리를 입력해주세요.
                </div>
            </div>
            <div class="CardReg-CardValidity">
                <p>유효기간</p>
                <input type="text" v-model="userCardValidity" placeholder="MM/YY" style="width: 90%; border: none; border-bottom: 1px solid silver; font-size: 20px;"
                @change="validityFormat()" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" maxlength="4">
            </div>
            <div class="CardReg-CardPw">
                <p>비밀번호</p>
                <input type="password" v-model="userCardPw" placeholder="비밀번호 앞 2자리" style="width: 90%; border: none; border-bottom: 1px solid silver; font-size: 20px;" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" maxlength="2">
            </div>
            <div class="CardGuidelines">
                <p>안내사항</p>
                <!-- Button trigger modal -->
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                전자금융거래 이용자 유의 사항
                </button>

                <!-- Modal -->
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" style="width: 100%">
                    <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">안내사항</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        전자금융감독규정 제 35조(이용자 유의사항 공지)에 의거하여 아래와 같이 전자금융거래 이용자 유의사항을 안내하오니, 서비스를 이용하시는 고객님께서는 참조하시기 바랍니다.<br><br>
                        1. 비밀번호 유출위험 및 관리에 관한 사항<br>
                        전자금융거래에 필요한 정보(결제 비밀번호 등)는 수첩, 지갑, 휴대폰 등 타인에게 쉽게 노출될 수 있는 매체에 기록하지 않고, 타인(금융회사지원 포함)에게 알려주지 않습니다.<br>
                        금융계좌, 공인인증서 등의 각종 비밀번호는 서로 다르게 설정하고 주지적으로 변경합니다.<br><br>
                        2. 비밀번호 변경에 관한 사항<br>
                        ID 비밀번호는 정기적으로 변경하시고, 비밀번호가 노출되었을 경우 즉시 비밀번호를 변경하시기 바랍니다.<br>
                        비밀번호 변경 시, 같은 번호를 재사용하지 않도록 합니다.<br><br>
                        3. 사고 예방을 위해서는 이용자들이 사전에 주의를 기울이는 것이 무엇보다도 중요하나 불가피하게 카드, 계좌번호나 비밀번호 노출, 예금인출 등 금융사고를 당하신 경우에는 즉시 해당 카드사, 은행이나 금융감독원(1332)에 신고하시어 도움을 받으시기 바랍니다.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                    </div>
                </div>
                </div>
            </div>
            <div class="CardReg-register">
                <button type="button" class="card-success" v-on:click="moveReg">등록하기</button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            userIndex: 0,
            userCardNum: '',
            userCardValidity: '',
            userCardPw: ''
        }     

    },
    mounted() {
        this.userIndex = sessionStorage.getItem('_UserConn-INDEX')
    },
    methods: {
        moveMyPage() {
            this.$router.push({ name: "AppUserMyPage" })
        },
        moveReg() {
            let obj = this;
            
            // 카드정보 입력
            obj.$axios.put('http://localhost:8090/vue/cardReg', {
                userIndex: obj.userIndex,
                userCardNum: obj.userCardNum,
                userCardValidity: obj.userCardValidity,
                userCardPw: obj.userCardPw 
            })
            .then(function(res) {
                console.log(res);
                
                obj.$router.push({ name: "AppUserMyPage" })
            })
            .catch(function(error) {
                console.error(error);
            })
        },
        validityFormat: function() {
            let mm = this.userCardValidity.substring(0, 2);
            let yy = this.userCardValidity.substring(2, 4);
            if((mm.length + yy.length) == 4)
                this.userCardValidity = mm + "/" + yy
        },
        cardNumValidCheck() {
            if(this.userCardNum.length === 16 || this.userCardNum.length === 19) {
                return true;
            } else {
                return false;
            }
        },
        CardNumFormat: function() {
            let first = this.userCardNum.substring(0, 4);
            let second = this.userCardNum.substring(4, 8);
            let third = this.userCardNum.substring(8, 12);
            let fourth = this.userCardNum.substring(12, 16);

            if((first.length + second.length + third.length + fourth.length) == 16) {
                this.userCardNum = first + "-" + second + "-" + third + "-" + fourth
            }
        }
    }
}
</script>

<style scoped>
    .mypage-body {
        margin: 0 auto;
        width: 900px;
    }

    .container-fluid {
        display: grid;
        grid-template-areas: 
            'RegHeader RegHeader RegHeader RegHeader'
            'RegCardNum RegCardNum RegCardNum RegCardNum'
            'RegCardValidity RegCardValidity RegCardPw RegCardPw'
            'Guidelines Guidelines Guidelines Guidelines'
            'RegCardRegister RegCardRegister RegCardRegister RegCardRegister'
        ;
    }

    .CardReg-header {
        grid-area: RegHeader;
        margin-bottom: 30px;
        border-bottom: 2px solid #84C2BD;
        text-align: left;
    }

    .CardReg-CardNum {
        grid-area: RegCardNum;
        text-align: left;
        font-weight: bold;
    }

    .CardReg-CardValidity {
        grid-area: RegCardValidity;
        text-align: left;
        margin-top: 50px;
        font-weight: bold;
    }

    .CardReg-CardPw {
        grid-area: RegCardPw;
        text-align: left;
        margin-top: 50px;
        font-weight: bold;
    }

    .CardGuidelines {
        grid-area: Guidelines;
        text-align: left;
        margin-top: 80px;
        font-weight: bold;
    }

    .CardReg-register {
        grid-area: RegCardRegister;
        text-align: left;  
    }

    .modal-title {
        font-weight: bold;
        padding: 0px 0px 10px 10px;
    }

    .modal-body {
        padding: 10px;
        text-align: left;
        font-size: 13px;
        font-weight: normal;
    }

    .btn-primary {
        background-color: silver;
        border: none;
    }

    .card-success {
        width: 100%;
        border: none;
        background-color: #84C2BD;
        border-radius: 7px;
        padding: 12px;
        color: white;
        margin-top: 70px;
    }

</style>