<template>
  <payment>
    <div class="my-view-1">
      <div class="container-fluid-1">

        <!-- 제일 위에 헤더 -->
        <div class="pa-header">
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
              >차량결재</b
            >
          </div>
        </div>
        <!-- 제일 위에 헤더 -->

        <!-- 차량 이미지 -->
        <div class="pa-img">
          <img
            :src="'http://localhost:8090/images/car_images/' + carImage"
            width="90%"
            height="90%"
          />
        </div>
        <!-- 차량 이미지 -->

        <!-- 대여 시간 -->
        <div class="pa-time">
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <ul id="ul">
            <li>
              <b>{{ carModel }}</b>
            </li>
          </ul>
          <p>
            <b
              >{{ unixToDate(reserveStartDate) }} ~
              {{ unixToDate(reserveEndDate) }}</b
            >
          </p>
        </div>
        <!-- 대여 시간 -->

        <!-- 요금 정보 -->
        <div class="pa-fee">
          <h3>요금 정보</h3>
        </div>
        <div class="pa-fee-coin">
          <h2 class="pay1">
            <b>{{ numberFormat(totalReservePrice) }}원</b>
          </h2>
        </div>
        <!-- 요금 정보 -->

        <!-- 보험료 -->
        <div class="pa-insur">
          <h4 class="insurance">보험료</h4>
        </div>
        <div class="pa-insur-coin">
          <h4 class="insurance-item">{{numberFormat(premiumPrice)}}원</h4>
        </div>

        <!-- 이용안내 -->
        <div class="pa-agree">
          <h3>이용안내 및 약관 동의</h3>
        </div>
        <!-- 이용안내 -->
      </div>
    </div>

    <!-- 패널티 조항  이용안내 글자랑 따로임 -->
    <div class="my-view-2">
      <div class="container-fluid-2">
        <div class="penalty-left-1">
          <h5><p class="penalty-item-1">[ 주요 페널티 안내 ]</p></h5>
        </div>
        <div class="penalty-right-1"></div>
        <div class="penalty-left-2">페널티 조항</div>
        <div class="penalty-right-2">페널티 및 이용제한</div>
        <div class="penalty-left-3">
          <span class="penalty-item-3">차량 내 흡연</span>
        </div>
        <div class="penalty-right-3">
          <span class="penalty-item-2">페널티 20만원 부과</span><br />
          <span class="penalty-item-3">회원자격 박탈</span>
        </div>

        <br />
        <div class="penalty-left-4">
          <p class="penalty-item-3">제3자 운행 및</p>
          <span class="penalty-item-3">동반운전자 단독 운행</span>
        </div>
        <div class="penalty-right-4">
          <span class="penalty-item-2">법적조치/</span><br />
          <span class="penalty-item-3">200,000원 </span><br />
          <span class="penalty-item-3">발생실비(탁송,수리비)</span><br />
          <span class="penalty-item-3">영구정지</span>
        </div>
        <div class="penalty-left-5">
          <ul class="penalty-item-1">
            <li>전체 페널티가 궁금하시다면?</li>
          </ul>
        </div>
        <div class="penalty-left-6">
          <span class="penalty-item-1">[취소수수료 안내]</span>
          <ul class="penalty-item-1">
            <li>취소시점에 따라 수수료가 보과될수 있습니다.</li>
          </ul>
        </div>
        <div class="penalty-left-7">
          <span class="penalty-item-1">[이용 안내]</span>
          <ul class="penalty-item-1">
            <li>주유 시 차량 내 비치된 주유카드를 이용해주세요.</li>
            <li>
              운행 전, 차량 외관상태를 확인 후 파손된 부위를 촬영하여 등록해
              주세요.<br />
              (스마트키 내 차량확인 기능 활용)
            </li>
            <li>
              운행 전, 하이패스와 블랙박스, 내비게이션 동작 상태를 확인해주세요.
            </li>
            <li>포인트는 대여요금에만 적용이 가능합니다.</li>
            <li>
              무료편도, 쿠폰 사용한 예약은 조기반납 포인트가 지급되지 않습니다.
            </li>
            <li>
              하이패스로 결제한 통행료, 주차비는 대부분 3일이내 자동결제 되거나
              <br />
              최대 2주 소요될수도 있습니다.
            </li>
          </ul>
        </div>
        <!-- 패널티 조항 -->

        <!-- 동의, 결제버튼 -->
        <div class="pa-foot" style="text-align: center">
          <form action="/action_page.php" class="was-validated">
            <div class="form-group form-check">
              <label class="form-check-label">
                <input
                  class="form-check-input"
                  id="agree"
                  type="radio"
                  name="remember"
                  required
                />
                동의하십니까.
                <div class="valid-feedback">감사합니다.</div>
                <div class="invalid-feedback">동의해주세요.</div>
              </label>
            </div>
            <button
              type="submit"
              class="btn b"
              style="
                font-size: 20px;
                padding: 10px 150px 10px 150px;
                border-radius: 2em;
              "
              v-on:click="moveqr"
            >
              {{ numberFormat(totalReservePrice) }}원 결제하기
            </button>
          </form>
        </div>
        <!-- 동의, 결제버튼 -->
      </div>
    </div>
  </payment>
</template>

<script>
export default {
  data() {
    return {
      premiumPrice: 5500, //  보험료다

      reserveStartDate: 0,
      reserveEndDate: 0,
      totalReservePrice: 0,

      carIndex: 0,
      carNumber: "",
      carModel: "",
      carPrice: 0,
      carLocation: "",
      carImage: "",
    };
  },
  methods: {
    /**
     * 유닉스 시간에서 년월일시분 으로 보여주기
     */
    unixToDate: function (unixTime) {
      let d = new Date(unixTime * 1000);
      // let d = new Date(unixTime)
      return (
        ("00" + (d.getMonth() + 1)).slice(-2) +
        "-" +
        ("00" + d.getDate()).slice(-2) +
        " " +
        ("00" + d.getHours()).slice(-2) +
        ":" +
        ("00" + d.getMinutes()).slice(-2)
      );
    },
    // 글자 사이에 ,넣기
    numberFormat: function (num) {
      return num.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    },

    // 결제하기 버튼 누르면 qr페이지로 이동
    moveqr() {
      if (document.getElementById("agree").checked)
        // 체크되지 않으면 결재 페이지로 넘어가지않음
        this.$router.push({
          name: "AppCarOrder",
          params: {
            // params로 밑에 있는 값들을 qr페이지로 가지고감
            carIndex: this.carIndex,
            userIndex: this.userIndex,
            reserveStartDate: this.reserveStartDate,
            reserveEndDate: this.reserveEndDate,
            totalReservePrice: this.totalReservePrice,
            premiumPrice: this.premiumPrice,
          },
        });
    },
    // 결제하기 버튼 누르면 qr페이지로 이동 끝

    // 뒤로가기 버튼 reservation으로 이동
    moveBack() {
      this.$router.push({ name: "AppCarReserve", params: {carIndex: this.carIndex} });
    },
    // 뒤로가기 버튼 reservation으로 이동 끝
  },
  mounted: function () {
    let thisVue = this;
    // 차량 정보
    thisVue.$axios
      .get("http://localhost:8090/teamcarsharing/api/GetCarData", {
        params: {
          carIndex: thisVue.$route.params.carIndex,
        },
      })
      //  성공
      .then(function (response) {
        console.log(response.data.carIndex);
        thisVue.carIndex = response.data.carIndex;
        thisVue.carNumber = response.data.carNumber;
        thisVue.carModel = response.data.carModel;
        thisVue.carPrice = response.data.carPrice;
        thisVue.carLocation = response.data.carLocation;
        thisVue.carImage = response.data.carImage;
      })
      .catch(function (error) {
        console.error(error);
      });
    this.userIndex = this.$route.params.userIndex;
    this.reserveStartDate = this.$route.params.reserveStartDate;
    this.reserveEndDate = this.$route.params.reserveEndDate;
    this.totalReservePrice = this.$route.params.totalReservePrice + this.premiumPrice;
  },
  // 차량 정보 끝
};
</script>

<style>
.my-view-1 {
  margin: 0 auto;
  width: 900px;
}

.container-fluid-1 {
  display: grid;
  grid-template-areas:
    "header-pa header-pa"
    "img-pa time-pa"
    "fee-pa coin-fee-pa"
    "insur-pa coin-insur-pa"
    "agree-pa agree-pa";
}

.my-view-2 {
  margin: 0 auto;
  width: 900px;
}

.container-fluid-2 {
  display: grid;
  grid-template-areas:
    "left-penalty-1 right-panalty-1"
    "left-penalty-2 right-panalty-2"
    "left-penalty-3 right-penalty-3"
    "left-penalty-4 right-penalty-4"
    "left-penalty-5 left-penalty-5"
    "left-penalty-6 left-penalty-6"
    "left-penalty-7 left-penalty-7"
    "foot-pa foot-pa";
}

.pa-header {
  grid-area: header-pa;
  border-bottom: 1px solid silver;
}

.pa-img {
  grid-area: img-pa;
  margin-top: 20px;
  border-bottom: 1px solid silver;
}

.pa-time {
  grid-area: time-pa;
  margin-top: 20px;
  border-bottom: 1px solid silver;
}

.pa-fee {
  grid-area: fee-pa;
  margin-top: 40px;
}

.pa-fee-coin {
  grid-area: coin-fee-pa;
  margin-top: 40px;
}

.pa-insur {
  grid-area: insur-pa;
  margin-top: 20px;
  border-bottom: 1px solid silver;
}

.pa-insur-coin {
  grid-area: coin-insur-pa;
  margin-top: 20px;
  border-bottom: 1px solid silver;
}

.pa-agree {
  grid-area: agree-pa;
  margin-top: 40px;
}

.penalty-left-1 {
  grid-area: left-penalty-1;
  margin-top: 60px;
}

.penalty-right-1 {
  grid-area: right-panalty-1;
  margin-top: 60px;
}

.penalty-left-2 {
  grid-area: left-penalty-2;
  background-color: #7c8083;
  color: #fff;
  text-align: center;
  font-size: 18px;
  height: 74px;
  padding-top: 24px;
}

.penalty-right-2 {
  grid-area: right-panalty-2;
  background-color: #7c8083;
  color: #fff;
  text-align: center;
  font-size: 18px;
  height: 74px;
  padding-top: 24px;
}

.penalty-left-3 {
  grid-area: left-penalty-3;
  border-bottom: 1px solid silver;
  border-right: 1px solid silver;
  height: 100px;
  padding-top: 40px;
}

.penalty-right-3 {
  grid-area: right-penalty-3;
  border-bottom: 1px solid silver;
  height: 100px;
  padding-top: 20px;
}

.penalty-left-4 {
  grid-area: left-penalty-4;
  border-bottom: 4px solid silver;
  border-right: 1px solid silver;
  height: 150px;
  padding-top: 40px;
}

.penalty-right-4 {
  grid-area: right-penalty-4;
  border-bottom: 4px solid silver;
  height: 150px;
  padding-top: 20px;
}

.penalty-left-5 {
  grid-area: left-penalty-5;
  margin-bottom: 20px;
  margin-top: 20px;
}
.penalty-left-6 {
  grid-area: left-penalty-6;
  margin-bottom: 20px;
}

.penalty-left-7 {
  grid-area: left-penalty-7;
  margin-bottom: 20px;
}

.penalty-header {
  padding: 0px 0px 0px 0px;
}

.pa-foot {
  grid-area: foot-pa;
  margin-top: 40px;
}

.penalty-item-1 {
  color: dimgray;
  font-size: 18px;
}

.penalty-item-2 {
  color: red;
  font-size: 18px;
}

.penalty-item-3 {
  color: black;
  font-size: 18px;
}

#ul {
  color: dimgray;
}

.pay1 {
  color: #84c2bd;
}

.insurance {
  color: dimgray;
  margin-bottom: 40px;
}

.insurance-item {
  color: dimgray;
  margin-left: 50px;
}

.b {
  background-color: #84c2bd;
  color: #fff;
}
</style>
