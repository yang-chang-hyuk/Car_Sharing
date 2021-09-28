<template>
  <main class="my-view">
    <div class="container-fluid">
      <!-- 차량 이미지 -->
      <div class="car-img text-center">
        <img
          v-if="carImage"
          :src="'http://localhost:8090/images/car_images/' + carImage"
          width="70%"
          height="100%"
        />
        <div class="car-noimage" v-else>
          사진이 없습니다.
        </div>
      </div>
      <!-- 차량 이미지 -->

      <!-- 차량 정보 -->
      <div class="car-fuel">
        <ul id="ul" class="car-fuel-item" style="margin-top: 10px">
          <li>
            <b>{{ carModel }}</b>
          </li>
          <li>
            <b>{{ carNumber }}</b>
          </li>
          <li v-if="checkClear() && carClear"><b>최근세차</b></li>
          <i class="fas fa-gas-pump">{{ carFuel }}%</i>
        </ul>
      </div>
      <!-- 차량 정보 -->

      <!-- 옵션 정보 상단 -->
      <div class="car-info">
        <h2><b>옵션정보</b></h2>
      </div>
      <div class="basic-info-1">
        <div class="basic-info-item-1">
          <b>기본정보</b>
        </div>
      </div>
      <div class="basic-info-left-2">
        <div class="basic-info-item-left">
          <b>모델</b>
        </div>
      </div>
      <div class="basic-info-right-2">
        <div class="basic-info-item-right">
          <b>{{ carModel }}</b>
        </div>
      </div>
      <div class="basic-info-left-3">
        <div class="basic-info-item-left-person">
          <b>승차인원</b>
        </div>
      </div>
      <div class="basic-info-right-3">
        <div class="basic-info-item-right">
          <b>{{ carTakePerson }}</b>
        </div>
      </div>
      <!-- 옵션 정보 상단 -->

      <!-- 옵션 정보 하단 -->
      <div class="option-info">
        <div class="basic-info-item-1">
          <b>옵션정보</b>
        </div>
      </div>
      <div class="basic-info-left-4">
        <div class="basic-info-item-left">
          <b>내장</b>
        </div>
      </div>
      <div class="basic-info-right-4">
        <div class="basic-info-item-right">
          <b>{{ carOption1 }}</b>
        </div>
      </div>
      <div class="basic-info-left-5">
        <div class="basic-info-item-left">
          <b>안전</b>
        </div>
      </div>
      <div class="basic-info-right-5">
        <div class="basic-info-item-right">
          <b>{{ carOption2 }}</b>
        </div>
      </div>
      <div class="basic-info-left-6">
        <div class="basic-info-item-left">
          <b>편의</b>
        </div>
      </div>
      <div class="basic-info-right-6">
        <div class="basic-info-item-right">
          <b>{{ carOption3 }}</b>
        </div>
      </div>
      <!-- 옵션 정보 하단 -->

      <!-- 차량사용법 -->
      <div class="car-use">
        <h2><b>차량사용법</b></h2>
      </div>
      <div class="usage">
        <iframe width="100%" height="900px" :src="carUsage"></iframe>
      </div>
      <!-- 차량사용법 -->

      <!-- 후기 -->
      <div class="review-header">
        <h2><b>후기</b></h2>
      </div>
      <div class="review-body">
        <button
          class="btn btn-block"
          id="co"
          type="button"
          data-toggle="collapse"
          data-target="#collapseExample"
          aria-expanded="false"
          aria-controls="collapseExample"
        >
          <div class="row">
            <div class="col-2 text-center"><h4>후기</h4></div>
            <div class="col-9 text-right"><h4>총 0건</h4></div>
          </div>
        </button>
        <div
          class="collapse"
          id="collapseExample"
          v-for="i in carReviews"
          v-bind:key="i.carIndex"
        >
          <div class="card card-body">
            <div class="row">
              <div class="col-2 text-center">{{ i.userNickname }}</div>
              <div class="col-3 text-center">{{ i.reviewDate }}</div>
              <div class="col-5 text-center">{{ i.reviewContents }}</div>
              <div class="col-2 text-right">
                <i class="bi bi-heart-fill"></i>
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="16"
                  height="16"
                  fill="currentColor"
                  class="bi bi-heart-fill"
                  viewBox="0 0 16 16"
                >
                  <path
                    fill-rule="evenodd"
                    d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"
                  />
                </svg>
                {{ i.likes }}
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- 후기 -->
    </div>
  </main>
</template>

<script>
export default {
  props: ["carIndex",
  "carNumber",
  "carModel",
  "carPrice",
  "carTakePerson",
  "carClear",
  "carFuel",
  "carOption1",
  "carOption2",
  "carOption3",
  "carUsage",
  "carLocation",
  "carImage"],
  methods: {
    //세차 확인
    checkClear() {
      if (this.carClear == 1) {
        return true;
      } else {
        return false;
      }
    },
  }
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
    "img-car img-car"
    "item-fuel-car item-fuel-car"
    "fuel-car fuel-car"
    "info-car info-car"
    "info-basic-1 info-basic-1"
    "left-info-basic-2 right-info-basic-2"
    "left-info-basic-3 right-info-basic-3"
    "info-option info-option"
    "left-info-basic-4 right-info-basic-4"
    "left-info-basic-5 right-info-basic-5"
    "left-info-basic-6 right-info-basic-6"
    "use-car use-car"
    "ageus ageus"
    "header-review header-review"
    "body-review body-review";
}

.car-img {
  grid-area: img-car;
  margin-top: 20px;
}

.car-fuel {
  grid-area: fuel-car;
}

.car-fuel-item {
  grid-area: item-fuel-car;
  margin-left: 370px;
}

.car-info {
  grid-area: info-car;
  margin-top: 20px;
  border-bottom: 2px solid silver;
  margin-bottom: 40px;
}

.basic-info-1 {
  grid-area: info-basic-1;
  background-color: #e0e0e0;
  border-top: 1px solid silver;
  border-bottom: 1px solid silver;
}

.basic-info-item-left {
  margin: 10px 0px 10px 40px;
}

.basic-info-item-right {
  margin: 10px 0px 10px 30px;
  text-align: center;
}

.basic-info-item-left-person {
  margin: 10px 0px 0px 30px;
}

.basic-info-left-2 {
  grid-area: left-info-basic-2;
  border-bottom: 1px solid silver;
}

.basic-info-right-2 {
  grid-area: right-info-basic-2;
  border-bottom: 1px solid silver;
}

.basic-info-left-3 {
  grid-area: left-info-basic-3;
  margin-bottom: 50px;
  border-bottom: 1px solid silver;
}

.basic-info-right-3 {
  grid-area: right-info-basic-3;
  margin-bottom: 50px;
  border-bottom: 1px solid silver;
}

.option-info {
  grid-area: info-option;
  background-color: #e0e0e0;
  border-top: 1px solid silver;
  border-bottom: 1px solid silver;
}

.basic-info-left-4 {
  grid-area: left-info-basic-4;
  border-bottom: 1px solid silver;
}

.basic-info-right-4 {
  grid-area: right-info-basic-4;
  border-bottom: 1px solid silver;
}

.basic-info-left-5 {
  grid-area: left-info-basic-5;
  border-bottom: 1px solid silver;
}

.basic-info-right-5 {
  grid-area: right-info-basic-5;
  border-bottom: 1px solid silver;
}

.basic-info-left-6 {
  grid-area: left-info-basic-6;
  border-bottom: 1px solid silver;
}

.basic-info-right-6 {
  grid-area: right-info-basic-6;
  border-bottom: 1px solid silver;
}

.basic-info-item-1 {
  margin: 10px 0px 10px 30px;
}

.car-use {
  grid-area: use-car;
  margin-top: 40px;
  border-bottom: 2px solid silver;
  margin-bottom: 40px;
}

.usage {
  grid-area: ageus;
}

.review-header {
  grid-area: header-review;
  margin-top: 40px;
  border-bottom: 2px solid silver;
  margin-bottom: 40px;
}

.review-body {
  grid-area: body-review;
  margin-bottom: 40px;
}

#co {
  color: black;
  background-color: #e0e0e0;
}

.car-noimage {
  width: 70%;
  height: 100%;
  background-color: #ccc;
}
</style>