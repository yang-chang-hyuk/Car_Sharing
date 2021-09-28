<template>
    <div id="reserve">
        <div class="reserve-title">
            <div id="back">
                <router-link to="/" style="color: #000;">
                    <i class="fas fa-long-arrow-alt-left"></i>
                </router-link>
            </div>
            <div id="title">
                예약 정보
            </div>
            <div>

            </div>
        </div>

        <div class="reserve-main">
            <div v-if="hasReservedCar">
                <div class="reserve-my">
                    <div id="car-pic">
                        <img v-if="carImage" :src="'http://localhost:8090/images/car_images/' + carImage" width="100%"/>
                    </div>
                    <div id="car-info">
                        <div id="car-name">{{carModel}}</div>
                        <div id="car-num">{{carNumber}}</div>
                        <div id="car-location"><span style="color:#777">차량 위치: </span>{{carLocation}}</div>
                        <div id="car-reservedate">{{unixToDate(reserveDateStart)}} ~ {{unixToDate(reserveDateEnd)}}</div>
                    </div>
                    <div id="car-cancel">
                        <b-button @click="reserveCancelModal">
                            예약 취소
                        </b-button>
                    </div>
                </div>
                <div id="cancel-info">
                    ※ 예약을 취소하게 되면 10% 수수료 비용이 발생 됩니다.
                </div>
                <div id="car-cancel-min">
                    <b-button @click="reserveCancelModal">
                        예약 취소
                    </b-button>
                </div>
            </div>
            <div class="reserve-nodata" v-else>
                예약된 차량이 없습니다.
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data: function() {
        return {
            userIndex: 0,
            isUserBlocked: 0,

            reserveCarIndex: 0,
            reserveDateStart: 0,
            reserveDateEnd: 0,
            reserveTotalPrice: 0,
            hasReservedCar: false,

            carModel: '',
            carNumber: '',
            carClear: 0,
            carFuel: 0,
            carLocation: '',
            carImage: ''
        }
    },
    methods: {
        /**
         * 유닉스 시간에서 년월일시분 으로 보여주기
         */
        unixToDate: function(unixTime) {
            let d = new Date(unixTime * 1000)
            // let d = new Date(unixTime)
            return d.getFullYear() + '-' + ('00' + (d.getMonth() + 1)).slice(-2) +
            '-' + ('00' + d.getDate()).slice(-2) +
            ' ' + ('00' + d.getHours()).slice(-2) +
            ':' + ('00' + d.getMinutes()).slice(-2)
        },
        reserveCancelModal: function() {
            const thisVue = this
            thisVue.$bvModal.msgBoxConfirm('예약을 취소하게 되면 수수료 10%가 발생하게 됩니다.', {
                title: '예약을 취소할까요?',
                size: 'sm',
                buttonSize: 'sm',
                okVariant: 'danger',
                okTitle: '예약 취소',
                cancelTitle: '닫기',
                headerClass: 'border-bottom-0',
                footerClass: 'border-top-0',
                hideHeaderClose: true,
                centered: true
            }).then(value => {
                if(value) {
                    //  사용자 테이블에서 대여 차량 삭제하기
                    thisVue.axios.delete('http://localhost:8090/teamcarsharing/api/DeleteUserReserveCar', {
                        params: {userIndex: thisVue.userIndex}
                    }).then(() => {
                        this.connectData()
                        thisVue.$bvToast.toast('예약을 취소했습니다.', {
                            title: '예약 취소',
                            variant: 'success',
                            solid: true
                        })
                    }).catch(error => {
                        console.error(error)
                    })
                }
            }).catch(error => {
                console.error(error)
            })
        },
        connectData: function() {
            const thisVue = this
            let id = sessionStorage.getItem('_UserConn-ID')
            let pw = sessionStorage.getItem('_UserConn-PASSWORD')
            //  사용자 데이터 가져오기
            thisVue.axios.get('http://localhost:8090/teamcarsharing/api/GetUserFromSession', {
                params: {userId: id, userPw: pw}
            }).then(response => {
                thisVue.userIndex = response.data.userIndex
                thisVue.isUserBlocked = response.data.isBlock

                //  사용자 데이터를 불러오고, 사용자가 차단되어있지 않을 경우
                if(thisVue.userIndex !== 0 && !thisVue.isUserBlocked) {
                    //  사용자의 대여 차량 데이터 가져오기
                    thisVue.axios.get('http://localhost:8090/teamcarsharing/api/GetUserCarReservedData', {
                        params: {userIndex: thisVue.userIndex}
                    }).then(response => {
                        thisVue.reserveCarIndex = response.data.carIndex
                        thisVue.reserveDateStart = response.data.reserveDateStart
                        thisVue.reserveDateEnd = response.data.reserveDateEnd
                        thisVue.reserveTotalPrice = response.data.carReservePrice

                        let date = Math.floor(new Date()) / 1000
                        thisVue.hasReservedCar = (date < thisVue.reserveDateStart)

                        if(thisVue.hasReservedCar) {
                            //  대여 차량 정보 데이터 가져오기
                            thisVue.axios.get('http://localhost:8090/teamcarsharing/api/GetCarData', {
                                params: {carIndex: thisVue.reserveCarIndex}
                            }).then(response => {
                                thisVue.carModel = response.data.carModel
                                thisVue.carNumber = response.data.carNumber
                                thisVue.carClear = response.data.carClear
                                thisVue.carFuel = response.data.carFuel
                                thisVue.carImage = response.data.carImage
                                thisVue.carLocation = response.data.carLocation
                            })
                        }

                    }).catch(error => {
                        console.error(error)
                    })
                }
            }).catch(error => {
                console.error(error)
            })
        }
    },
    mounted: function() {
        this.connectData()
    }
}
</script>

<style scoped>
.reserve-main {
    margin: 0 auto;
    max-width: 650px;
    margin-top: 38px;
}
.reserve-main .reserve-nodata {
    font-size: 18px;
    text-align: center;
}
.reserve-main .reserve-my {
    /* height: 160px; */
    display: flex;
    margin: 0 6px;
}
.reserve-main .reserve-my #car-pic {
    width: 140px;
    height: 140px;
    /* background-color: #ccc; */
    margin-right: 16px;
}
.reserve-main .reserve-my #car-info {
    display: flex;
    flex-direction: column;
    margin-right: 16px;
}
.reserve-main .reserve-my #car-cancel {
    display: block;
    padding-top: 98px;
}
.reserve-main #cancel-info {
    margin: 6px 6px;
    color: #999;
    font-size: 14px;
    font-style: italic;
}
.reserve-main #car-cancel-min {
    display: none;
    margin: 6px 6px 0 6px;
    text-align: center;
}
.reserve-main .reserve-my #car-info #car-name {
    height: 20%;
    font-weight: 500;
    font-size: 22px;
}
.reserve-main .reserve-my #car-info #car-num {
    height: 20%;
}
.reserve-main .reserve-my #car-info #car-location {
    height: 40%;
    padding-top: 38px;
}
.reserve-main .reserve-my #car-info #car-reservedate {
    height: 20%;
}

.reserve-title {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    border-bottom: 1px solid #999;
}
.reserve-title div {
    width: 33.3%;
    padding: 0 24px;
}
.reserve-title #back {
    font-size: 28px;
    color: #000;
}
.reserve-title #title {
    font-size: 28px;
    font-weight: 500;
    text-align: center;
}

#car-cancel button,
#car-cancel-min button {
    font-size: 18px;
    line-height: 0px;
    background-color: #fff;
    border: 2px solid #c95555;
    border-radius: 0.7em;
    color: #c95555;
    height: 38px;
}

#car-cancel button:hover,
#car-cancel-min button:hover {
    background-color: #c95555;
    border: 2px solid #c95555;
    color: #ffffff;
}

@media screen and (max-width: 575px) {
    .reserve-main .reserve-my #car-cancel {
        display: none;
    }
    .reserve-main #car-cancel-min {
        display: block;
    }
}
</style>