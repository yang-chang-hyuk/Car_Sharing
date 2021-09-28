<template>
  <div id="app">
    <CarInfoHeader></CarInfoHeader>
    <CarInfoMain
      v-bind:carIndex="carIndex"
      :carNumber="carNumber"
      :carModel="carModel"
      :carPrice="carPrice"
      :carTakePerson="carTakePerson"
      :carClear="carClear"
      :carFuel="carFuel"
      :carOption1="carOption1"
      :carOption2="carOption2"
      :carOption3="carOption3"
      :carUsage="carUsage"
      :carLocation="carLocation"
      :carImage="carImage"></CarInfoMain>
  </div>
</template>

<script>
import CarInfoHeader from "@/components/CarInfoHeader.vue";
import CarInfoMain from "@/components/CarInfoMain.vue";

export default {
  components: {
    CarInfoHeader, CarInfoMain
  },
  data() {
    return {
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

      carReviews: [], // 리스트로 가져온다.
    }
  },
  mounted() {
    let thisVue = this;
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
      
    // 리뷰
    this.$axios
      .get("http://localhost:8090/api/GetReview", {
        params: {
          carIndex: thisVue.$route.params.carIndex
        },
      })
      .then(function (response) {
        thisVue.carReviews = response.data;
      })
      .catch(function (error) {
        console.error(error);
      });
  }
};

</script>

<style>
</style>