<template>
  <div class="my-view">
    <div class="container-fluid">
      <!-- 제일 위에 헤더 -->
      <div class="re-header">
        <div>
          <button @click="moveBack" class="btn" style="margin-bottom: 8px">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="35"
              height="35"
              fill="currentColor"
              class="bi bi-chevron-left"
              viewBox="0 0 16 16"
            >
              <path
                fill-rule="evenodd"
                d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z"
              />
            </svg>
          </button>
          <b style="margin-left: 310px; font-size: 30px; line-height: 64px"
            >결제정보</b
          >
        </div>
      </div>
      <!-- 제일 위에 헤더 -->

      <!-- 차량 정보 -->
      <div class="re-impo">
        <br />
        <div class="text-center">
          <img
            :src="'http://localhost:8090/images/car_images/' + carImage"
            width="60%"
            height="60%"
          />
        </div>
        <br />
        <div style="margin-left: 350px">
          <ul id="ul">
            <li>
              <b>{{ carModel }}</b>
            </li>
            <li>
              <b>{{ carNumber }}</b>
            </li>
            <li v-if="carClear == 1"><b>최근세차</b></li>
            <i class="fas fa-gas-pump"><span style="margin-left:6px;">{{ carFuel }}%</span></i>
          </ul>
        </div>

        <br />
        <br />
      </div>
      <!-- 차량 정보 -->

      <!-- 대여시간 -->
      <div class="start-time">
        <div
          class="text-center start-item rounded"
          id="time"
          style="height: 65px"
        >
          <h4>대여시간</h4>
          <h4>{{ reserveStartDateDisplay }}</h4>
        </div>
      </div>
      <!-- 대여시간 -->

      <!-- 중간 아이콘 -->
      <div class="grid-icon">
        <i class="bi bi-chevron-right"></i>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="40"
          height="60"
          fill="currentColor"
          class="bi bi-chevron-right"
          viewBox="0 0 16 16"
        >
          <path
            fill-rule="evenodd"
            d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z"
          />
        </svg>
      </div>
      <!-- 중간 아이콘 -->

      <!-- 반납 시간 -->
      <div class="end-time">
        <div
          class="text-center end-item rounded"
          id="time"
          style="height: 65px"
        >
          <h4>반납시간</h4>
          <h4>{{ reserveEndDateDisplay }}</h4>
        </div>
      </div>
      <!-- 반납 시간 -->

      <!-- 시간 컨트롤러 -->
      <div class="retime-controller">
        <div id="control" style="margin-left: 100px">
          <span
            ><i
              @click="subReserveStartDate(600)"
              class="fas fa-caret-square-left fa-2x"
              id="caret"
              style="font-size:54px;margin-right: 20px;margin-top:15px"
            ></i
          ></span>
          <span style=""
            ><i
              @click="addReserveStartDate(600)"
              class="fas fa-caret-square-right fa-2x"
              id="caret"
              style="font-size:54px;margin-right: 430px"
            ></i
          ></span>
          <span
            ><i
              @click="subReserveEndDate(600)"
              class="fas fa-caret-square-left fa-2x"
              id="caret"
              style="font-size:54px;margin-right:20px;"
            ></i
          ></span>
          <span
            ><i @click="addReserveEndDate(600)" class="fas fa-caret-square-right fa-2x" id="caret" style="font-size:54px;"></i
          ></span>
        </div>
      </div>
      <!-- 시간 컨트롤러 -->

      <br />
      <br />
      <br />

      <!-- 대여 위치 -->
      <div class="rental-location" style="margin-top: 100px; width: 100%">
        <span style="float: left; margin: 4px 10px 0px 0px; font-size: 28px"
          >대여 위치</span
        >
        <input
          v-model="carLocation"
          type="text"
          class="form-control form-control-lg"
          placeholder="대여 위치를 입력해주세요."
          style="width: 85%"
        />
      </div>
      <!-- 대여 위치 -->

      <!-- 반납 위치 -->
      <div class="return-location" style="margin-top: 30px">
        <span style="float: left; margin: 4px 10px 0px 0px; font-size: 28px"
          >반납 위치</span
        >
        <input
          v-model="carLocation"
          type="text"
          class="form-control form-control-lg"
          placeholder="반납 위치를 입력해주세요."
          style="width: 85%"
        />
      </div>
      <!-- 반납 위치 -->

      <!-- 총 대여요금 -->
      <div class="re-agree" style="margin-top: 100px">
        <div style="text-align: center">
          <span style="font-size: 40px; margin-right: 20px">대여 요금 : </span>
          <span id="pay" style="font-weight: bold; font-size: 45px">{{
            numberFormat(carPrice + getReservePrice())
          }}</span>
        </div>
        <!-- 총 대여요금 -->

        <!-- 예약하기 버튼 -->
        <div class="text-center">
          <button
            type="button"
            class="btn btn-lg"
            style="
              font-size: 20px;
              padding: 10px 150px 10px 150px;
              border-radius: 2em;
              margin-top: 40px;
            "
            id="ser"
            v-on:click="movePayment"
          >
            <b>예약하기</b>
          </button>
        </div>
        <!-- 예약하기 버튼 -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      RESERVE_START_DATE_MIN: 1800, //  현재 시간에서 더해질 시간 (초)
      RESERVE_MIN_GAP_TIME: 1800, //  대여 시간과 반납 시간의 최소 차이 (초)
      RESERVE_START_DATE_ROUND: 10,
      RESERVE_COST_PRICE: 1200, //  10분당 가격

      reserveStartDate: 0,
      reserveStartDateDisplay: "",
      reserveEndDate: 0,
      reserveEndDateDisplay: "",

      reservePrice: 0,

      selectCarIndex: 1,

      // 0은 int(숫자), ''빈칸은 String(문자)
      carIndex: 0,
      carNumber: "",
      carModel: "",
      carPrice: 0,
      carTakePerson: 0,
      carClear: 0,
      carFuel: 0,
      carOption1: "",
      carOption2: "",
      carOption3: "",
      carUsage: "",
      carLocation: "",
      carImage: "",
      userIndex: 0,
      reserveDateStart: 0,
      reserveDateEnd: 0,
      carReservePrice: 0,
    };
  },
  methods: {
    //시간 컨트롤

    /**
     * 유닉스 시간에서 년월일시분 으로 보여주기
     */
    unixToDate: function (unixTime) {
      let d = new Date(unixTime * 1000);
      // let d = new Date(unixTime)
      return (
        d.getFullYear() +
        "-" +
        ("00" + (d.getMonth() + 1)).slice(-2) +
        "-" +
        ("00" + d.getDate()).slice(-2) +
        " " +
        ("00" + d.getHours()).slice(-2) +
        ":" +
        ("00" + d.getMinutes()).slice(-2)
      );
    },
    /**
     * 유닉스 시간이 오늘의 마지막 시간인지 여부 가져오기
     */
    isLastToday: function (unixTime) {
      let d = new Date(unixTime * 1000);
      let hh = d.getHours();
      let mm = d.getMinutes();
      return hh === 23 && mm >= 50;
    },
    /**
     * 현재 시간 구하기
     */
    getNow: function () {
      let d = new Date();
      let now = (Math.floor(d.getTime() / 1000) * 1000) / 1000;
      return now;
    },
    /**
     * 현재 시간에서 반올림한 시간 구하기
     */
    getNowRounded: function () {
      // return Math.floor(new Date().getTime() / 1000)
      let ms = 1000 * 60 * this.RESERVE_START_DATE_ROUND;
      let d = new Date();
      let rounded = (Math.round(d.getTime() / ms) * ms) / 1000;
      if (this.getNow() >= rounded) rounded += 600;
      return rounded;
    },
    /**
     * 반납 시간과 대여 시간의 차이 구하기
     */
    getTimeGap: function () {
      let gap = this.reserveEndDate * 1000 - this.reserveStartDate * 1000;
      return (
        Math.floor((gap % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)) +
        "시간 " +
        Math.floor((gap % (1000 * 60 * 60)) / (1000 * 60)) +
        "분"
      );
    },
    /**
     * 대여 시간과 반납 시간 차이간 시간 가격 계산하기
     */
    getReservePrice: function () {
      let gap = this.reserveEndDate - this.reserveStartDate;
      return (gap / 600) * this.RESERVE_COST_PRICE;
    },

    //  대여, 반납 시간 설정 함수
    setReserveStartDate: function (time) {
      this.reserveStartDate = time;
      this.reserveStartDateDisplay = this.unixToDate(this.reserveStartDate);
    },
    setReserveEndDate: function (time) {
      this.reserveEndDate = time;
      this.reserveEndDateDisplay = this.unixToDate(this.reserveEndDate);
    },

    //  대여 시간 추가 및 감소 함수
    subReserveStartDate: function (param) {
      console.log("test");
      //  대여 시간을 감소한 후에 현재 시간과 같거나 많을경우 param만큼 시간을 뺀다.
      if (this.reserveStartDate - param >= this.getNowRounded())
        this.setReserveStartDate(this.reserveStartDate - param);
      else {
        //  아닐 경우 대여 시간과 반납 시간을 초기화한다.
        this.setReserveStartDate(this.getNowRounded());
        this.setReserveEndDate(this.getNowRounded() + 3600);
      }
    },
    addReserveStartDate: function (param) {
      //  반납 시간이 오늘의 마지막이거나, 대여 시간과 반납 시간의 차이가 30분 이거나 낮을 경우 대여 시간을 추가하지 않는다.
      if (
        this.isLastToday(this.reserveEndDate) &&
        this.reserveEndDate - this.reserveStartDate <= this.RESERVE_MIN_GAP_TIME
      )
        return;

      this.setReserveStartDate(this.reserveStartDate + param);

      //  반납 시간과 차이가 30분 이하일 때 반납 시간을
      //  현재 반납 시간 기준으로 30분 추가한다.
      if (
        this.reserveEndDate - this.reserveStartDate <
        this.RESERVE_MIN_GAP_TIME
      ) {
        this.setReserveEndDate(this.reserveEndDate + this.RESERVE_MIN_GAP_TIME);
      }

      //  반납 시간과 비교해서 대여 시간이 더 길어지면
      //  반납 시간을 1시간 추가한다.
      // if(this.reserveEndDate <= this.reserveStartDate)
      //     this.setReserveEndDate(this.reserveEndDate + 3600)
    },

    //  반납 시간 추가 및 감소 함수
    subReserveEndDate: function (param) {
      if (
        //  대여 시간이 현재 시간이거나, 더 높아야 하고,
        this.reserveStartDate >= this.getNowRounded() &&
        //  반납 시간과 대여 시간이 30분 이상 차이나야 한다.
        this.reserveEndDate - this.reserveStartDate > this.RESERVE_MIN_GAP_TIME
      ) {
        this.setReserveEndDate(this.reserveEndDate - param);
      }
    },
    addReserveEndDate: function (param) {
      //  반납 시간이 오늘의 마지막 시간이 아닐 경우 추가한다.
      if (!this.isLastToday(this.reserveEndDate))
        this.setReserveEndDate(this.reserveEndDate + param);
    },
    checkClear() {
      if (this.carClear == 1) {
        return true;
      } else {
        return false;
      }
    },

    //시간 컨트롤 끝

    // 예약하기 버튼 누르면 payment로 이동
    movePayment() {
      this.axios.get('http://localhost:8090/teamcarsharing/api/IsUserCarReserved', {
        params: {
          userIndex: this.userIndex
        }
      }).then(response => {
        if(response.data)
          alert('현재 차량을 대여하고 있습니다.')
        else {
          this.$router.push({
            name: "AppPayment",
            params: {
              // params로 밑에 있는 정보들을 가지고 payment로 이동함
              carIndex: this.carIndex,
              userIndex: this.userIndex,
              reserveStartDate: this.reserveStartDate,
              reserveEndDate: this.reserveEndDate,
              totalReservePrice:
                this.carPrice + this.reservePrice + this.getReservePrice(),
            },
          }); // 버튼태그(예약하기 누르면) payment로 이동할것이다
        }
      }).catch(error => {
        console.error(error)
      })
    },
    // 예약하기 버튼 누르면 payment로 이동 끝

    // 뒤로가기 버튼 누르면 reservation로 이동
    moveBack() {
      this.$router.push({ name: "AppMain" });
    },
    // 뒤로가기 버튼 누르면 reservation로 이동 끝

    // 글자 사이에 ,넣기
    numberFormat: function (num) {
      return num.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  mounted: function () {
    let thisVue = this;

    this.userIndex = sessionStorage.getItem('_UserConn-INDEX')

    // 차량 정보
    thisVue.$axios
      .get("http://localhost:8090/teamcarsharing/api/GetCarData", {
        params: {
          carIndex: thisVue.$route.params.carIndex
        },
      })
      //  성공
      .then(function (response) {
        console.log(response.data.carIndex);
        thisVue.carIndex = response.data.carIndex;
        thisVue.carNumber = response.data.carNumber;
        thisVue.carModel = response.data.carModel;
        thisVue.carPrice = response.data.carPrice;
        thisVue.carTakePerson = response.data.carTakePerson;
        thisVue.carClear = response.data.carClear;
        thisVue.carFuel = response.data.carFuel;
        thisVue.carOption1 = response.data.carOption1;
        thisVue.carOption2 = response.data.carOption2;
        thisVue.carOption3 = response.data.carOption3;
        thisVue.carUsage = response.data.carUsage;
        thisVue.carLocation = response.data.carLocation;
        thisVue.carImage = response.data.carImage;
      })
      .catch(function (error) {
        console.error(error);
      });
      // 차량 정보 끝

    // 시간 컨트롤러 최소시간 설정
    this.setReserveStartDate(
      this.getNowRounded() + this.RESERVE_START_DATE_MIN
    );
    this.setReserveEndDate(this.getNowRounded() + 3600);
    // 시간 컨트롤러 최소시간 설정 끝
  },
};
</script>

<style scoped>
.my-view {
  margin: 0 auto;
  width: 900px;
}

.container-fluid {
  display: grid;
  grid-template-areas:
    "repage-header repage-header repage-header"
    "repage-impo repage-impo repage-impo"
    "repage-Starttime icon-grid repage-Endtime"
    "repage-controller repage-controller repage-controller"
    "repage-renteal repage-renteal repage-renteal"
    "repage-return repage-return repage-return"
    "repage-agree repage-agree repage-agree";
}

.re-header {
  grid-area: repage-header;
  border-bottom: 1px solid silver;
}

.re-impo {
  grid-area: repage-impo;
}

.start-time {
  grid-area: repage-Starttime;
}

.end-time {
  grid-area: repage-Endtime;
}

.grid-icon {
  grid-area: icon-grid;
  text-align: center;
}

.rental-location {
  grid-area: repage-renteal;
}

.return-location {
  grid-area: repage-return;
}

.re-agree {
  grid-area: repage-agree;
}

.retime-controller {
  grid-area: repage-controller;
}

#ul {
  color: dimgray;
}
#time {
  background-color: #84c2bd;
  color: #fff;
}
#pay {
  color: #84c2bd;
}
#ser {
  background-color: #84c2bd;
  color: #fff;
}
#caret {
  background-color: #fff;
  color: #84c2bd;
}
</style>