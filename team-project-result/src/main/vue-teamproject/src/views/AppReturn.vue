<template>
    <div id="car-return">
        <div class="car-return-title">
            <div id="back">
                <router-link to="/" style="color: #000;">
                    <i class="fas fa-long-arrow-alt-left"></i>
                </router-link>
            </div>
            <div id="title">
                반납
            </div>
            <div>

            </div>
        </div>

        <div class="car-return-main">
            <div class="car-return-body" v-if="isCanReturn">
                <div class="return-my">
                    <div id="car-pic">
                        <img v-if="carImage" :src="'http://localhost:8090/images/car_images/' + carImage" width="100%"/>
                    </div>
                    <div id="car-info">
                        <div id="car-name">{{carModel}}</div>
                        <div id="car-num">{{carNumber}}</div>
                        <div id="car-location"><span style="color:#777">반납 위치: </span>{{carLocation}}</div>
                        <div id="car-reservedate"><span style="color:#777">반납 시간: </span> {{unixToDate(reserveDateEnd)}}</div>
                    </div>
                </div>
                <div class="car-return-bottom">
                    <div class="return-info" v-if="getTimeGap(getNow(), reserveDateEnd) > 0">
                        차량 반납 까지 <b>{{formatTimeGap(getNow(), reserveDateEnd)}}</b> 남았습니다.
                    </div>
                    <div class="return-info danger" v-else>
                        차량 반납 시간을 초과했습니다. <br/>
                        현재 초과 후 <b>{{formatTimeGap(reserveDateEnd, getNow())}}</b> 동안 사용하였고, 
                        초과 이용 금액은 <b>{{numberFormat(getOverPrice())}}</b>원 입니다.
                    </div>
                    <div class="danger-notice">
                        ※ 초과 이용 금액은 반납 시 자동으로 결제 됩니다.
                    </div>

                    <div class="return-done">
                        <button class="return-btn" @click="reserveCarReturn()">
                            반납
                        </button>
                    </div>
                </div>
            </div>
            <div class="car-return-nodata" v-else>
                반납 가능한 대여 차량이 없습니다.
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data: function() {
        return {
            RESERVE_COST_PRICE: 1200,   //  10분당 가격

            userIndex: 0,
            isUserBlocked: 0,

            reserveCarIndex: 0,
            reserveDateStart: 0,
            reserveDateEnd: 0,
            reserveTotalPrice: 0,
            isCanReturn: false,

            carModel: '',
            carNumber: '',
            carFuel: 0,
            carLocation: '',
            carImage: ''
        }
    },
    methods: {
        /**
         * 현재 시간
         */
        getNow: function() {
            return (Math.floor(new Date().getTime() / 1000))
        },
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
        /**
         * 남은 시간 반환하기
         */
        formatTimeGap: function(start, end) {
            let tempStart = start * 1000
            let tempEnd = end * 1000
            let gap = this.getTimeGap(tempStart, tempEnd)
            return Math.floor((gap % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)) + "시간 " +
            Math.floor((gap % (1000 * 60 * 60)) / (1000 * 60)) + "분"
        },
        /**
         * 남은 시간 구하기
         */
        getTimeGap: function(start, end) {
            let gap = end - start
            return (gap < 0 ? 0 : gap)
        },
        /**
         * 초과 금액 반환
         */
        getOverPrice: function() {
            let result = 0
            if(this.getNow() > this.reserveDateEnd) {
                let gap = this.getTimeGap(this.reserveDateEnd, this.getNow())
                result = Math.floor((gap / 600) * this.RESERVE_COST_PRICE)
            }
            return result
        },
        /**
         * 숫자 포멧
         */
        numberFormat: function(num) {
            return num.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")
        },
        reserveCarReturn: function() {
            this.$bvModal.msgBoxConfirm('차량을 반납할까요? ' + ((this.getOverPrice() > 0) ? '초과 이용으로 인해 ' + this.numberFormat(this.getOverPrice()) + '원이 추가로 결제됩니다.' : ''), {
                size: 'sm',
                buttonSize: 'sm',
                okVariant: 'success',
                okTitle: '반납',
                cancelTitle: '취소',
                footerClass: 'p-2 border-top-0',
                hideHeaderClose: true,
                centered: true
            }).then(value => {
                if(value) {
                    this.axios.delete('http://localhost:8090/teamcarsharing/api/DeleteUserReserveCar', {
                        params: {userIndex: this.userIndex}
                    }).then(() => {
                        this.connectData()
                        this.$bvToast.toast('차량을 반납했습니다. 이용해 주셔서 감사합니다.', {
                            title: '반납',
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
            //  user
            thisVue.axios.get('http://localhost:8090/teamcarsharing/api/GetUserFromSession', {
                params: {userId: id, userPw: pw}
            }).then(response => {
                thisVue.userIndex = response.data.userIndex
                thisVue.isUserBlocked = response.data.isBlock

                if(thisVue.userIndex !== 0 && !thisVue.isUserBlocked) {
                    //  car_reserve
                    thisVue.axios.get('http://localhost:8090/teamcarsharing/api/GetUserCarReservedData', {
                        params: {userIndex: thisVue.userIndex}
                    }).then(response => {
                        thisVue.reserveCarIndex = response.data.carIndex
                        thisVue.reserveDateStart = response.data.reserveDateStart
                        thisVue.reserveDateEnd = response.data.reserveDateEnd
                        thisVue.reserveTotalPrice = response.data.carReservePrice

                        let now = Math.floor(new Date()) / 1000
                        //  지금 시간이 대여 시간과 같거나 많을 경우 반납할 수 있게 한다.
                        thisVue.isCanReturn = (now >= thisVue.reserveDateStart)

                        if(thisVue.reserveCarIndex !== 0 || thisVue.reserveCarIndex !== undefined) {
                            //  car
                            thisVue.axios.get('http://localhost:8090/teamcarsharing/api/GetCarData', {
                                params: {carIndex: thisVue.reserveCarIndex}
                            }).then(response => {
                                thisVue.carModel = response.data.carModel
                                thisVue.carNumber = response.data.carNumber
                                thisVue.carFuel = response.data.carFuel
                                thisVue.carLocation = response.data.carLocation
                                thisVue.carImage = response.data.carImage
                            }).catch(error => {
                                console.error(error)
                            })
                        }
                    }).catch(error => {
                        console.error(error)
                    })
                }
            })
        }
    },
    mounted: function() {
        this.connectData()
    }
}
</script>

<style scoped>
.car-return-title {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    border-bottom: 1px solid #999;
}
.car-return-title div {
    width: 33.3%;
    padding: 0 24px;
}
.car-return-title #back {
    font-size: 28px;
    color: #000;
}
.car-return-title #title {
    font-size: 28px;
    font-weight: 500;
    text-align: center;
}

.car-return-main {
    margin: 0 auto;
    max-width: 650px;
    margin-top: 38px;
}
.car-return-main .return-my {
    display: flex;
    margin: 0 6px;
}
.car-return-main .return-my #car-pic {
    width: 140px;
    height: 140px;
    /* background-color: #ccc; */
    margin-right: 16px;
}
.car-return-main .return-my #car-info {
    display: flex;
    flex-direction: column;
    margin-right: 16px;
}
.car-return-main .car-return-nodata {
    text-align: center;
    font-size: 18px;
}
.car-return-main .return-my #car-info #car-name {
    height: 20%;
    font-weight: 500;
    font-size: 22px;
}
.car-return-main .return-my #car-info #car-num {
    height: 20%;
}
.car-return-main .return-my #car-info #car-location {
    height: 40%;
    padding-top: 38px;
}
.car-return-main .return-my #car-info #car-reservedate {
    height: 20%;
}

.car-return-main .car-return-bottom {
    margin: 12px 6px 0 6px;
}
.car-return-main .car-return-bottom .return-info {
    background: rgb(131, 193, 188);
    color: #fff;
    text-align: center;
    font-size: 17px;
    /* line-height: 36px; */

    border-radius: .5em;
}
.car-return-main .car-return-bottom .danger {
    font-size: 16px;
    background: rgb(204, 70, 70);
}
.car-return-main .car-return-bottom .danger-notice {
    margin-top: 12px;
    font-size: 16px;
    font-style: italic;
}
.car-return-main .car-return-bottom .return-done {
    margin-top: 12px;
    text-align: center;
}
.car-return-main .car-return-bottom .return-done .return-btn {
    width: 200px;
    height: 32px;
    line-height: 0;
    font-size: 19px;
    border: 1px solid rgb(20, 150, 226);
    color: #fff;
    background-color: rgb(20, 150, 226);
    border-radius: .3em;
}
</style>