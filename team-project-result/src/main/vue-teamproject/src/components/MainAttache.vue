<template>
    <div>
        
        <!-- Bootstrap Alert -->
        <!-- <b-alert
            v-model="accessBlockAlert"
            class="position-fixed fixed-top m-0 rounded-0"
            style="z-index:2000"
            variant="danger"
            dismissible>
            로그인이 필요한 접근입니다.
        </b-alert> -->
        <b-toast id="access-block" toaster="b-toaster-top-center" variant="danger" solide>
            <template #toast-title>
                <div class="d-flex flex-grow-1 align-items-baseline">
                    <!-- <b-img blank blank-color="#ff5555" class="mr-2" width="12" height="12"></b-img> -->
                    <strong class="mr-auto">접근 오류</strong>
                </div>
            </template>
            로그인이 필요한 접근입니다. 로그인을 해주세요.
        </b-toast>

        <!-- 사이드 메뉴 버튼 -->
        <div class="menu-btn public-shadow" v-b-toggle.sidebar @click="closeCardata();getUserCarReserveState();getUserCarReturnState();">
            <div class="menu-icon">
                <i class="fas fa-bars"></i>
            </div>
        </div>

        <!-- 사이드 메뉴 -->
        <b-sidebar
            id="sidebar"
            class="menu-sidebar"
            backdrop
            no-header
        >
            <div class="sidebar-body">
                <div @click="gotoMyPage()" style="cursor:pointer">
                    <div id="username">
                        {{isLoggedIn() ? getUsername() : "로그인을 해주세요."}}
                    </div>
                    <div id="useremail">
                        {{userId}}
                    </div>
                </div>
                <div id="sidebarlist">
                    <a @click="gotoMyCarReserve()">예약확인</a>
                    <span class="sidebar-badge" v-bind:class="{'badge-hide': carReserveState === 0, 'badge-success': carReserveState === 1}"></span>
                </div>
                <div id="sidebarlist">
                    <a @click="gotoMyCarReturn()">반납하기</a>
                    <span class="sidebar-badge" v-bind:class="{'badge-hide': carReturnState === 0, 'badge-success': carReturnState === 1, 'badge-danger': carReturnState === 2}"></span>
                </div>
                <div id="sidebarlist">
                    <a href="#">고객센터</a>
                </div>
                <div id="sidebarlist">
                    <a href="#">사고접수&amp;현황</a>
                </div>
                <div class="pt-5" v-if="userIsAdmin">
                    <button type="button" @click="gotoAdminPage()" id="sidebarbutton">관리자 페이지</button>
                </div>
            </div>

            <template #footer>
                <div class="sidebar-footer">
                    <div v-if="!isLoggedIn()" style="display:flex">
                        <div id="signin">
                            <router-link to="/userlogin">로그인</router-link>
                        </div>
                        <div id="signup">
                            <router-link to="/usersignup">회원가입</router-link>
                        </div>
                    </div>
                    <div id="signout" v-if="isLoggedIn()">
                        <a @click="signOut()" style="cursor:pointer">로그아웃</a>
                    </div>
                </div>
            </template>
        </b-sidebar>

        <!-- 주소 검색 폼 -->
        <div class="bottom-content">
        <div class="menu-address public-shadow" v-bind:class="{'menu-address-active': (searchAddress !== null)}" @click="gotoAddress()">
            <div class="menu-address-icon">
                <i class="fas fa-search"></i>
            </div>
            <div class="menu-address-text">
                {{searchAddress === null ? '어디에서 출발하나요？' : searchAddress}}
            </div>
        </div>

        <div class="menu-cardata" v-if="displayCarData">
            <div class="cardata-top">
                <div class="cardata-count">
                    해당 지역에 차량이 <span style="color:#83C1BC">{{carCounts}}</span> 대 있습니다.
                </div>
                <div class="cardata-close">
                    <i class="fas fa-times" @click="closeCardata()"></i>
                </div>
            </div>
            <div class="cardata">
                <!-- 대여 가능한 차량 목록 -->
                <div class="cardata-items" v-for="(cars) in carData" v-bind:key="cars.carIndex">
                    <!-- <div v-bind:class="{'cardata-reserved': carReserved[cars.carIndex]}"> -->
                    <div>
                        <div class="cardata-items-body">
                            <div class="cardata-items-image">
                                <div @click="gotoCarDetail(cars.carIndex)">
                                    <img v-if="cars.carImage" 
                                    :src="'http://localhost:8090/images/car_images/' + cars.carImage"
                                    style="width:100%">
                                </div>
                            </div>
                            <div class="cardata-items-info">
                                <div class="cardata-name">{{cars.carModel}}</div>
                                <div class="cardata-fuel"><i class="fas fa-gas-pump"></i> {{"휘발유 " + cars.carFuel + "%"}}</div>
                                <div class="cardata-clear">{{cars.carClear ? "최근세차" : ""}}</div>
                                <div class="cardata-price"><span>{{numberFormat(cars.carPrice) + "원"}}</span></div>
                            </div>
                        </div>
                        <div class="cardata-items-bottom">
                            <button class="cardata-button" @click="gotoCarReserve(cars.carIndex)">
                                예약하기
                            </button>
                        </div>
                    </div>
                </div>

                <!-- 대여한 차량 목록 -->
                <div class="cardata-items" v-for="(cars) in carReserveData" v-bind:key="cars.carIndex">
                    <div class="cardata-reserved">
                        <div class="cardata-items-body">
                            <div class="cardata-items-image">
                                <div @click="gotoCarDetail(cars.carIndex)">
                                    <img v-if="cars.carImage" 
                                    :src="'http://localhost:8090/images/car_images/' + cars.carImage"
                                    style="width:100%">
                                </div>
                            </div>
                            <div class="cardata-items-info">
                                <div class="cardata-name">{{cars.carModel}}</div>
                                <div class="cardata-fuel"><i class="fas fa-gas-pump"></i> {{"휘발유 " + cars.carFuel + "%"}}</div>
                                <div class="cardata-clear">{{cars.carClear ? "최근세차" : ""}}</div>
                                <div class="cardata-price"><span>{{numberFormat(cars.carPrice) + "원"}}</span></div>
                            </div>
                        </div>
                        <div class="cardata-items-bottom">
                            <button class="cardata-button btn-not-allow" @click="gotoCarReserve(cars.carIndex)">
                                예약하기
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </div>
</template>

<script>
export default {
    //  부모에게 가져온 변수
    props: ["carData", "carReserveData", "carCounts", "displayCarData", "searchAddress"],

    data: function() {
        return {
            // accessBlockAlert: false,

            userIndex: 0,
            userName: '',
            userId: '',
            userNickname: '',
            userRegnum: '',
            userRegnumGender: 1,
            userProfile: '',
            userPhone: '',
            userIsAdmin: 0,
            userIsBlock: 0,

            carReserveState: 0,
            carReturnState: 0
        }
    },
    mounted: function() {
        let addr = sessionStorage.getItem('_SearchAddress')
        if(addr !== null) {
            this.$emit("setSearchAddress", addr)
        }

        //  세션으로 넘어온 아이디와 비밀번호로 로그인 유지
        let sessionUserId = sessionStorage.getItem('_UserConn-ID')
        let sessionUserPw = sessionStorage.getItem('_UserConn-PASSWORD')
        if(sessionUserId === '' || sessionUserId === null ||
            sessionUserPw === '' || sessionUserPw === null) {
            console.log('Sign in failed.')
        } else {
            //  차단된 사용자 채크
            this.verifyBlockedUser(sessionUserId, sessionUserPw)

            const thisVue = this
            thisVue.$axios.get('http://localhost:8090/teamcarsharing/api/GetUserFromSession', {
                params: {
                    userId: sessionUserId,
                    userPw: sessionUserPw
                }
            }).then(function(response) {
                thisVue.userIndex = response.data.userIndex
                thisVue.userName = response.data.userName
                thisVue.userId = response.data.userId
                thisVue.userNickname = response.data.userNickname
                thisVue.userRegnum = response.data.userRegnum
                thisVue.userRegnumGender = response.data.userRegnumGender
                thisVue.userProfile = response.data.userProfile
                thisVue.userPhone = response.data.userPhone
                thisVue.userIsAdmin = response.data.isAdmin
                thisVue.userIsBlock = response.data.isBlock
            }).catch(function(error) {
                console.error(error)
            })
        }

        // console.log(JSON.stringify(this.carData))
        // console.log(this.displayCarData)
    },
    methods: {
        gotoAddress: function() {
            this.$router.push({
                name: 'AppAddress'
            })
        },
        closeCardata: function() {
            if(this.displayCarData) {
                let addr = sessionStorage.getItem('_SearchAddress')
                if(addr === undefined || addr === '')   addr = null
                this.$emit('setSearchAddress', addr)

                const DOC_CARDATA = document.querySelector('.menu-cardata')
                DOC_CARDATA.classList.add('menu-cardata-hide')
                setTimeout(function() {
                    DOC_CARDATA.classList.add('menu-cardata-hide')
                    this.$emit('toggleDisplayCarData', false)
                }.bind(this), 350)
            }
        },
        gotoCarDetail: function(param) {
            this.$router.push({name: 'AppCarInfo', params: {carIndex: param}})
        },
        /**
         * 선택한 차량 예약 페이지로 이동
         */
        gotoCarReserve: function(param) {
            const thisVue = this
            thisVue.$axios.get('http://localhost:8090/teamcarsharing/api/IsCarReserved', {
                params: {carIndex: param}
            }).then(function(response) {
                //  IsCarReserved에서 작동하는 메소드가 차량이 대여되어있으면
                //  1을 반환하므로 해당 if문에서 1일 경우 차량을 대여할 수 없게 한다.
                if(response.data)
                    alert('해당 차량은 대여할 수 없습니다.')
                else {
                    localStorage.setItem('_SelectedCar', param)
                    thisVue.$router.push({
                        name: 'AppCarReserve', params: {carIndex: param}
                    })
                }
            }).catch(function(error) {
                console.log(error)
            })
        },
        /**
         * 마이페이지로 이동
         */
        gotoMyPage: function() {
            if(this.isLoggedIn())
                this.$router.push({name: 'AppUserMyPage', params: {userIndex: this.userIndex}})
            else
                this.$router.push({name: 'AppUserLogin'})
        },
        /**
         * 예약 확인 페이지로 이동
         */
        gotoMyCarReserve: function() {
            if(!this.userIndex)
                // this.accessBlockAlert = true
                this.$bvToast.show('access-block')
            else
                this.$router.push({name: 'AppReserve'})
        },
        /**
         * 반납하기 페이지로 이동
         */
        gotoMyCarReturn: function() {
            if(!this.userIndex)
                // this.accessBlockAlert = true
                this.$bvToast.show('access-block')
            else
                this.$router.push({name: 'AppReturn'})
        },
        /**
         * 관리자페이지로 이동
         */
        gotoAdminPage: function() {
            if(this.userIndex)
                this.$router.push({name: 'AppAdminMain', params: {userIndex: this.userIndex}})
        },
        verifyBlockedUser: function(param1, param2) {
            const thisVue = this
            thisVue.$axios.get('http://localhost:8090/teamcarsharing/api/GetUserFromSession', {
                params: {
                    userId: param1,
                    userPw: param2
                }
            }).then(function(response) {
                if(response.data.isBlock) {
                    alert('차단된 계정입니다.')
                    thisVue.signOut()
                }
            }).catch(function(error) {
                console.error(error)
            })
        },
        signOut: function() {
            sessionStorage.removeItem('_UserConn-INDEX')
            sessionStorage.removeItem('_UserConn-ID')
            sessionStorage.removeItem('_UserConn-PASSWORD')
            this.$router.go()
        },
        isLoggedIn: function() {
            return (this.userId !== '')
        },
        getUsername: function() {
            if(this.userNickname === '')
                return this.userName
            return this.userNickname
        },
        /**
         * 차량 예약 상태
         */
        getUserCarReserveState: function() {
            if(this.userIndex) {
                this.axios.get('http://localhost:8090/teamcarsharing/api/IsUserCarReserved', {
                    params: {userIndex: this.userIndex}
                }).then(response => {
                    if(response.data) {
                        this.axios.get('http://localhost:8090/teamcarsharing/api/GetUserCarReservedData', {
                            params: {userIndex: this.userIndex}
                        }).then(response => {
                            let now = Math.floor(new Date().getTime() / 1000)
                            if(response.data.reserveDateStart > now)
                                this.carReserveState = 1
                            else
                                this.carReserveState = 0
                        }).catch(error => {
                            console.error(error)
                        })
                    }
                    else
                        this.carReserveState = 0
                }).catch(error => {
                    console.error(error)
                })
            }
        },
        /**
         * 차량 반납 상태
         */
        getUserCarReturnState: function() {
            if(this.userIndex) {
                this.axios.get('http://localhost:8090/teamcarsharing/api/IsUserCarReserved', {
                    params: {userIndex: this.userIndex}
                }).then(response => {
                    if(response.data) {
                        this.axios.get('http://localhost:8090/teamcarsharing/api/GetUserCarReservedData', {
                            params: {userIndex: this.userIndex}
                        }).then(response => {
                            let now = Math.floor(new Date().getTime() / 1000)
                            if(response.data.reserveDateEnd < now)
                                this.carReturnState = 2
                            else if(response.data.reserveDateEnd >= now && response.data.reserveDateStart <= now)
                                this.carReturnState = 1
                        }).catch(error => {
                            console.error(error)
                        })
                    }
                    else
                        this.carReturnState = 0
                }).catch(error => {
                    console.error(error)
                })
            }
        },
        numberFormat: function(num) {
            return num.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")
        }
    }
}
</script>

<style scoped>

.bottom-content {
    width: 100%;
    position: fixed;
    bottom: 0px;
    z-index: 1010;
}

.menu-address {
    cursor: pointer;

    display: flex;
    margin-left: 24px;
    margin-bottom: 22px;
    padding: 0 38px;
    line-height: 39px;
    font-size: 22px;
    width: 485px;
    
    /* text-align: center;
    justify-content: center; */

    background-color: #ffffff;
    color: #2c3e50;

    border-radius: 1.5em;
}
.menu-address-active {
    background-color: #83C1BC;
    color: #ffffff;
}
.menu-address .menu-address-text {
    width: 100%;
    padding-left: 16px;
    font-weight: 400;
    text-align: center;
}

.menu-cardata {
    white-space: nowrap;
    overflow-x: scroll;
    display: flex;
    flex-direction: column;
    margin: 0 auto;
    height: 280px;
    padding: 0 24px;
    padding-top: 2px;
    background-color: #ffffff;

    border-top-left-radius: 1em;
    border-top-right-radius: 1em;
    /* border: 4px solid #84C2BD;
    border-bottom: none; */

    animation-name: show-cardata;
    animation-duration: .4s;
    animation-direction: normal;
}
.menu-cardata-hide {
    animation-name: hide-cardata;
    animation-duration: .4s;
    animation-direction: normal;
}
.menu-cardata .cardata-top {
    position: fixed;
    -moz-box-sizing: border-box;
    box-sizing: border-box;

    padding: 0 20px;

    width: 460px;
    height: 42px;

    background-color: rgb(247, 247, 247);
    border-radius: 1em;

    display: flex;
    flex-direction: row;
}
.menu-cardata .cardata-top .cardata-count {
    width: 80%;
    text-align: left;
    font-size: 20px;
    line-height: 40px;
    font-weight: 500;
}
.menu-cardata .cardata-top .cardata-close {
    width: 20%;
    text-align: right;  
    font-size: 26px;
}
.menu-cardata .cardata-top .cardata-close i {
    cursor: pointer;
}
.menu-cardata .cardata {
    display: flex;
    flex-direction: row;
    padding-top: 48px;
}
.menu-cardata .cardata .cardata-items {
    display: flex;
    flex-direction: column;
    width: 350px;
    margin-right: 18px;
}
.menu-cardata .cardata .cardata-items .cardata-reserved {
    /* background-color: rgb(228, 228, 228); */
    opacity: 0.65;
}
.menu-cardata .cardata .cardata-items .cardata-items-body {
    display: flex;
    flex-direction: row;
    width: 350px;
}
.menu-cardata .cardata .cardata-items .cardata-items-bottom {
    width: 100%;
    margin-top: 12px;
}
.menu-cardata .cardata .cardata-items .cardata-button {
    background-color: rgb(131, 193, 188);
    text-align: center;
    line-height: 32px;
    border: none;
    border-radius: 2em;
    font-size: 18px;
    width: 100%;
    color: #ffffff;
    -moz-transition: color 0.2s ease;
    -o-transition: color 0.2s ease;
    -ms-transition: color 0.2s ease;
    -webkit-transition: color 0.2s ease;
    transition: background-color 0.2s ease;
}
.menu-cardata .cardata .cardata-items .cardata-button:active {
    background-color: rgb(118, 165, 161);
    border: none;
    outline: none;
}
.menu-cardata .cardata .cardata-items .cardata-items-image {
    width: 50%;
}
.menu-cardata .cardata .cardata-items .cardata-items-image div {
    cursor: pointer;
    width: 160px;
    height: 160px;
    /* background-color: #ccc; */
}
.menu-cardata .cardata .cardata-items .cardata-items-info {
    width: 48%;
    display: flex;
    margin-left: 6px;
    flex-direction: column;

    /* border-right: 1px solid #999; */
}
.menu-cardata .cardata .cardata-items .cardata-items-info .cardata-name {
    height: 20%;
    font-weight: 500;
    /* line-height: 32px; */
    /* margin-bottom: 12px; */
}
.menu-cardata .cardata .cardata-items .cardata-items-info .cardata-fuel,
.menu-cardata .cardata .cardata-items .cardata-items-info .cardata-clear {
    height: 15%;
    font-weight: 300;
    line-height: 28px;
}
.menu-cardata .cardata .cardata-items .cardata-items-info .cardata-price {
    height: 50%;
    font-weight: 500;
    font-size: 24px;
    text-align: end;

    padding-top: 42px;
}
/* .menucardata .cardata .cardata-items .cardata-items-info .cardata-price span {
    text-align: end;
    vertical-align: bottom;
} */

.menu-btn {
    cursor: pointer;

    background-color: #84C2BD;
    color: #ffffff;
    padding: 12px 12px;
    font-size: 24px;
    text-align: center;
    justify-content: center;
    line-height: 16px;

    border-radius: 5px;

    position: fixed;
    left: 24px;
    top: 22px;
    z-index: 1020;
}

#sidebar .sidebar-body {
    margin-left: 32px;
    margin-top: 24px;
}

#username {
    text-align: left;
    font-size: 28px;
    font-weight: 700;
}

#useremail {
    text-align: left;
    font-size: 16px;
    color: rgb(131, 193, 188);
    margin-bottom: 64px;
}

#sidebarlist {
    text-align: left;
    font-size: 18px;
    margin-bottom: 18px;
}
#sidebarlist a {
    cursor: pointer;
}

#sidebarbutton {
    margin-left: 32px;
    background-color: rgb(131, 193, 188);
    height: 3em;
    width: 12em;
    border: none;
    text-align: center;
    color: white;
    border-radius: 2em;
    -moz-transition: color 0.2s ease;
    -o-transition: color 0.2s ease;
    -ms-transition: color 0.2s ease;
    -webkit-transition: color 0.2s ease;
    transition: background-color 0.2s ease;
}
#sidebarbutton:active {
    background-color: rgb(118, 165, 161);
    border: none;
    outline: none;
}

a {
    text-decoration: none;
    color: black;
    transition: color 0.2s ease;
}
a:hover {
    text-decoration: none;
    color: rgb(109, 109, 109);
}

#sidebar .sidebar-footer {
    /* margin-left: 32px; */
    margin-bottom: 16px;
    display: flex;
    justify-content: center;
    text-align: center;
    font-size: 18px;
}
.sidebar-badge {
    margin-left: 4px;
    margin-bottom: 2px;
    height: 10px;
    width: 10px;
    background-color: #bbb;
    border-radius: 50%;
    display: inline-block;
}
.badge-hide {display: none;}
.badge-success {background-color: #80ce38;}
.badge-danger {background-color: #ca5757;}

#sidebar .sidebar-footer #signin {
    margin-right: 16px;
}

@keyframes show-cardata {
    0% {
        height: 0px;
    }
    100% {
        height: 280px;
    }
}

@keyframes hide-cardata {
    0% {
        height: 280px;
    }
    100% {
        height: 0px;
    }
}

@media screen and (max-width: 560px) {
    .menu-cardata .cardata {
        flex-direction: column;
        height: 300px;
        padding-top: 14px;
    }
    .menu-cardata .cardata {
        margin-top: 42px;
    }
    .menu-cardata  .cardata .cardata-items {
        width: 100%;
        margin-bottom: 38px;
        justify-content: center;
    }
    .menu-cardata .cardata .cardata-items .cardata-items-body {
        width: 100%;
    }
    .menu-cardata .cardata .cardata-items .cardata-items-body .cardata-items-image {
        width: 40%;
    }
    .menu-cardata .cardata .cardata-items .cardata-items-body .cardata-items-info {
        width: 60%;
    }
}

@media screen and (max-width: 600px) {
    .menu-address {
        margin: 0 auto;
        margin-bottom: 22px;
    }
}
</style>