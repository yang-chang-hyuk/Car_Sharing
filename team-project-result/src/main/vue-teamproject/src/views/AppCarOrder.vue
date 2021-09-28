<template>
  <div class="my-view">
    <div class="container-fluid">
      <!-- 제일 위에 헤더 -->
      <div class="qr-header">
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
          <b style="margin-left: 350px; font-size: 30px; line-height: 64px"
            >결제</b
          >
        </div>
      </div>
      <!-- 제일 위에 헤더 -->

      <!-- 카드 -->
      <div class="qr-card-grid">
        <div class="card">
          <p
            style="
              border-bottom: 3px solid white;
              font-size: 20px;
              padding: 3px 3px 3px 3px;
            "
          >
            결제 카드
          </p>
          <p
            style="
              margin-top: 70px;
              font-size: 40px;
              margin-bottom: 0;
              font-weight: bold;
              text-align: center;
            "
          >
            {{ userCardNum }}
          </p>
          <div
            v-if="checkCardNum()"
            style="color: red; font-size: 25px; text-align: center"
          >
            카드를 등록해주세요
          </div>
          <p v-if="checkValidity() && userCardValidity" class="Validity-item">
            {{
             userCardValidity
            }}
          </p>
          <div class="myCard">
            <p v-if="checkcard_name() && userName" class="cardname-item">
              {{ userName.substring(0, 1) + "○○" }}
              <i
                class="fab fa-cc-visa card_icon"
                style="color: #ccc; font-size: 50px; margin-left: 460px"
                v-if="!checkCardNum()"
              ></i>
            </p>
          </div>
        </div>
      </div>
      <!-- 카드 -->

      <!-- 버튼 -->
      <div class="qr-button">
        <button
          type="button"
          class="btn b1"
          data-toggle="modal"
          data-target="#exampleModal"
          @click="submit()"
        >
          {{ numberFormat(totalReservePrice) }}원 결제
        </button>
        <!-- <div
          v-if="isValidCardNum()"
          class="modal fade"
          id="exampleModal"
          tabindex="-1"
          aria-labelledby="exampleModalLabel"
          aria-hidden="true"
        >
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button
                  type="button"
                  class="close"
                  data-dismiss="modal"
                  aria-label="Close"
                >
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body text-center">
                <b style="font-size: 50px">결&emsp;제&emsp;완&emsp;료!</b>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn b2">완료</button>
              </div>
            </div>
          </div>
        </div>-->
      </div>
      <!-- 버튼 -->
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      carIndex: 0,
      userIndex: 0,

      reserveStartDate: 0,
      reserveEndDate: 0,
      totalReservePrice: 0,

      userCardNum: "",
      userName: "",
      userCardValidity: ""
    };
  },

  // 뒤로가기 버튼 누르면 reservation으로 이동
  methods: {
    // 뒤로가기 버튼
    moveBack() {
      this.$router.push({ name: "AppCarReserve" });
    },
    // 뒤로가기 버튼 누르면 reservation으로 이동 끝

    // 글자 사이에 ,넣기
    numberFormat: function (num) {
      return num.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    },
    // 글자 사이에 ,넣기 끝

    // 카드 정보가 없을때
    isValidCardNum() {
      return (
        this.userCardNum !== null &&
        this.userCardNum !== undefined &&
        this.userCardNum !== ""
      );
    },
    // 카드 정보가 없을때 끝

    // 등록된 카드가 없을때 MyPage로 이동
    submit() {
      //  등록된 카드가 없다
      if (!this.isValidCardNum()) {
        alert("결제할 카드를 등록해주세요.");
        this.$router.push({name: 'AppUserMyPage'})
      }
      else {
        this.axios.post('http://localhost:8090/teamcarsharing/api/CarReserve', {
          carIndex: this.carIndex,
          userIndex: this.userIndex,
          reserveDateStart: this.reserveStartDate,
          reserveDateEnd: this.reserveEndDate,
          carReservePrice: this.totalReservePrice
        }).then(() => {
          this.$router.push({name: 'AppReserve'})
        }).catch(error => {
          console.log(error)
        })
      }
    },
    // 등록된 카드가 없을때 MyPage로 이동 끝

    // 카드 번호
    checkCardNum: function () {
      if (
        this.userCardNum == "" ||
        this.userCardNum == null ||
        this.userCardNum == undefined
      ) {
        return true;
      } else {
        this.userCardNum =
          this.userCardNum.substring(0, 4) +
          "-XXXX-XXXX-" +
          this.userCardNum.substring(15, 19);
        return false;
      }
      // 카드 번호 끝
    },

    // 유효기간
    checkValidity() {
      if (this.userCardValidity !== "" || this.userCardValidity !== null) {
        return true;
      } else {
        return false;
      }
    },
    // 유효기간 끝

    // 카드 사용자이름
    checkcard_name() {
      if (this.card_name !== "" || this.card_name !== null) {
        return true;
      } else {
        return false;
      }
    },
    // 카드 사용자이름 끝
  },
  mounted: function () {
    this.userIndex = sessionStorage.getItem('_UserConn-INDEX');
    // params로 가져온 토탈 가격을 넣음
    this.carIndex = this.$route.params.carIndex
    this.totalReservePrice = this.$route.params.totalReservePrice + this.$route.params.premiumPrice;
    this.reserveStartDate = this.$route.params.reserveStartDate
    this.reserveEndDate = this.$route.params.reserveEndDate

    let thisVue = this;
    // 유저정보
    thisVue.axios
      .get("http://localhost:8090/teamcarsharing/api/GetUserFromSession", {
        params: {
          userId: sessionStorage.getItem('_UserConn-ID'),
          userPw: sessionStorage.getItem('_UserConn-PASSWORD')
        },
      })
      .then(function (response) {
        thisVue.userIndex = response.data.userIndex;
        thisVue.userName = response.data.userName;
        thisVue.userCardNum = response.data.userCardNum;
        thisVue.userCardValidity = response.data.userCardValidity;
      })
      .catch(function (error) {
        console.error(error);
      });
      // 유저정보
  },
};
</script>


<style scoped>
/* scoped css스타일을 이페이지에서만 쓴다 */
.my-view {
  margin: 0 auto;
  width: 900px;
}

.container-fluid {
  display: grid;
  grid-template-areas:
    "qrpage-header qrpage-header qrpage-header "
    "qrpage-card qrpage-card qrpage-card"
    "qepage-button qepage-button qepage-button";
}

.qr-header {
  grid-area: qrpage-header;
  border-bottom: 1px solid silver;
}

.qr-card-grid {
  grid-area: qrpage-card;
  margin-top: 50px;
}

.qr-button {
  grid-area: qepage-button;
  border-radius: 2em;
  margin-left: 15%;
}

.card {
  border: 1px solid silver;
  width: 75%;
  margin-left: 15%;
  height: 350px;
  border-radius: 10px;
  background: linear-gradient(to right bottom, gray, silver 40%, black);
}

.b1 {
  background-color: #84c2bd;
  color: #fff;
  margin-top: 30px;
  font-size: 20px;
  width: 88%;
  border-radius: 2rem;
}

.b2 {
  background-color: #84c2bd;
  color: #fff;
  width: 100%;
}

.Validity-item {
  text-align: center;
  margin-right: 370px;
  font-size: 23px;
}

.cardname-item {
  margin: 50px 0px 0px 20px;
  font-size: 23px;
}
</style>
