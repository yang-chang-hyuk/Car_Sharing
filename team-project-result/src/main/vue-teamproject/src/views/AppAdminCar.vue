<template>
  <div class="row justify-content-center">
    <div class="col-md-12">
      <br />
      <table class="table table-hover">
        <!-- 차량 정보 나타내는 테이블 -->

        <thead class="thead-light">
          <tr>
            <th class="col-md-1">No.</th>
            <th class="col-md-2">차량</th>
            <th class="col-md-3">모델명</th>
            <th class="col-md-2">연료</th>
            <th class="col-md-2">세차 여부</th>
            <th class="col-md-1">정보 수정</th>
          </tr>
        </thead>

        <!-- 리스트 -->

        <tbody>
          <tr v-for="item in items" v-bind:key="item.carIndex">
            <td class="col-md-1">{{ item.carIndex }}</td>
            <td class="col-md-2">차량 {{ item.carIndex }}</td>
            <td class="col-md-3">{{ item.carModel }}</td>
            <td class="col-md-1">{{ item.carFuel }} %</td>

            <!-- 세차 체크박스 -->

            <td class="col-md-2">
              <input
                type="checkbox"
                v-model="item.carClear"
                v-on:click="sendData(item.carIndex, item.carClear)"
              />
              <span v-if="item.carClear == '1'">
                세차 완료
              </span>
              <span v-else>
                세차 안됨
              </span>
            </td>

            <!-- 차량 정보 수정 모달 버튼 -->

            <td class="col-md-1">
              <button
                v-b-modal.carEdit
                id="car-add-btn"
                v-on:click="carInfoEdit(item.carIndex)"
              >
                차량 수정
              </button>
              <!-- 리스트에서 차량 삭제하는 버튼(에러남) -->

              <!-- &nbsp;
              <button
                class="car-axios-button-delete"
                v-on:click="deleteCar(item.carIndex)"
              >
                삭제
              </button>-->
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <div>
      <b-modal id="carEdit" centered title="차량 수정" size="lg">
        <!-- 차량 정보 수정 -->
        <!-- 차량 모델은 데이터에서 리스트를 리턴해서 선택할 수 있게 구현 -->

        <span class="my-4">
          <!-- 차량 모델 -->

          <b-form-group
            label-cols="4"
            label-cols-lg="2"
            label="차량 모델 : "
            label-for="input-default"
          >
            <b-form-input
              list="input-list"
              id="input-with-list"
              v-model="edit.carModel"
            ></b-form-input>

            <b-form-datalist
              id="input-list"
              :options="carModelList"
            ></b-form-datalist>
          </b-form-group>

          <!-- 차 번호 -->

          <b-form-group
            label-cols="4"
            label-cols-lg="2"
            label="차 번호 : "
            label-for="input-default"
          >
            <b-form-input
              id="input-default"
              v-model="edit.carNumber"
            ></b-form-input>
          </b-form-group>
        </span>

        <span class="row">
          <!-- 탑승 인원, 연료량, 세차여부 -->

          <!-- 탑승 인원 -->
          <span class="col-5">
            <b-form-group
              label-cols="4"
              label-cols-lg="5"
              label="탑승 인원 : "
              label-for="input-default"
              ><b-form-input
                v-model="edit.carTakePerson"
                type="number"
              ></b-form-input>
            </b-form-group>
          </span>

          <!-- 연료량 입력 폼 -->
          <span class="col-4">
            <b-form-group
              label-cols="4"
              label-cols-lg="4"
              label="연료량 : "
              placeholder="%"
              label-for="input-default"
              ><b-form-input
                v-model="edit.carFuel"
                type="number"
              ></b-form-input>
            </b-form-group>
          </span>

          <!-- 체크박스 -->
          <span class="col-3">
            <b-form-group
              label-cols="4"
              label-cols-lg="7"
              label="세차 여부 : "
              label-for="input-checkbox"
            >
              <input
                type="checkbox"
                v-model="edit.carClear"
                style="zoom:2.5; cursor:pointer; margin-top:2px"
              />
            </b-form-group>
          </span>
        </span>

        <!-- 내장, 안전, 편의 입력 폼 -->

        <p>
          <!-- 내장 -->

          <span>
            <b-form-group
              label-cols="4"
              label-cols-lg="2"
              label="내장 : "
              label-for="input-default"
            >
              <b-form-textarea
                id="textarea-default"
                v-model="edit.carOption1"
              ></b-form-textarea
            ></b-form-group>
          </span>

          <!-- 안전 -->

          <span>
            <b-form-group
              label-cols="4"
              label-cols-lg="2"
              label="안전 : "
              label-for="input-default"
            >
              <b-form-textarea
                id="textarea-default"
                v-model="edit.carOption2"
              ></b-form-textarea
            ></b-form-group>
          </span>

          <!-- 편의 -->

          <span>
            <b-form-group
              label-cols="4"
              label-cols-lg="2"
              label="편의 : "
              label-for="input-default"
            >
              <b-form-textarea
                id="textarea-default"
                v-model="edit.carOption3"
              ></b-form-textarea
            ></b-form-group>
          </span>

          <!-- 차량 설명 url 입력 폼 -->

          <span>
            <b-form-group
              label-cols="4"
              label-cols-lg="2"
              label="차량 설명 URL : "
              label-for="input-default"
            >
              <b-form-input
                id="input-default"
                v-model="edit.carUsage"
              ></b-form-input>
            </b-form-group>
          </span>

          <!-- 위치 입력 폼 -->

          <span>
            <b-form-group
              label-cols="4"
              label-cols-lg="2"
              label="차량 위치 : "
              label-for="input-default"
            >
              <b-form-input
                id="input-default"
                v-model="edit.carLocation"
              ></b-form-input>
            </b-form-group>
          </span>
        </p>

        <!-- modal 푸터(삭제 버튼과 등록 버튼이 존재) -->

        <template #modal-footer="{ cancel }">
          <button id="modal-cancel" v-on:click="cancel()">
            취소
          </button>
          <button
            id="modal-submit"
            v-on:click="
              carEditSubmit(
                edit.carIndex,
                edit.carModel,
                edit.carNumber,
                edit.carTakePerson,
                edit.carClear,
                edit.carFuel,
                edit.carOption1,
                edit.carOption2,
                edit.carOption3,
                edit.carUsage,
                edit.carLocation
              )
            "
          >
            등록
          </button>
        </template>
      </b-modal>

      <!-- 차량 정보 삽입 (차량 추가 버튼을 누르면 modal로 나오게 됨) -->

      <b-button v-b-modal.carAdd id="car-add-btn">차량 추가</b-button>
      <b-modal id="carAdd" centered title="차량 데이터 추가" size="lg">
        <!-- 차량 모델과 번호 삽입 -->
        <!-- 차량 모델은 데이터에서 리스트를 리턴해서 선택할 수 있게 구현 -->

        <span class="my-4">
          <!-- 차량 모델 -->

          <b-form-group
            label-cols="4"
            label-cols-lg="2"
            label="차량 모델 : "
            label-for="input-default"
          >
            <b-form-input
              list="input-list"
              id="input-with-list"
              v-model="carModel"
            ></b-form-input>

            <b-form-datalist
              id="input-list"
              :options="carModelList"
            ></b-form-datalist>
          </b-form-group>

          <!-- 차 번호 -->

          <b-form-group
            label-cols="4"
            label-cols-lg="2"
            label="차 번호 : "
            label-for="input-default"
          >
            <b-form-input id="input-default" v-model="carNumber"></b-form-input>
          </b-form-group>
        </span>

        <span class="row">
          <!-- 탑승 인원, 연료량, 세차여부 -->

          <!-- 탑승 인원 -->
          <span class="col-5">
            <b-form-group
              label-cols="4"
              label-cols-lg="5"
              label="탑승 인원 : "
              label-for="input-default"
              ><b-form-input
                v-model="carTakePerson"
                type="number"
              ></b-form-input>
            </b-form-group>
          </span>

          <!-- 연료량 입력 폼 -->
          <span class="col-4">
            <b-form-group
              label-cols="4"
              label-cols-lg="4"
              label="연료량 : "
              placeholder="%"
              label-for="input-default"
              ><b-form-input v-model="carFuel" type="number"></b-form-input>
            </b-form-group>
          </span>

          <!-- 체크박스 -->
          <span class="col-3">
            <b-form-group
              label-cols="4"
              label-cols-lg="7"
              label="세차 여부 : "
              label-for="input-checkbox"
            >
              <input
                type="checkbox"
                v-model="carClear"
                style="zoom:2.5; cursor:pointer; margin-top:2px"
              />
            </b-form-group>
          </span>
        </span>

        <!-- 내장, 안전, 편의 입력 폼 -->

        <p>
          <!-- 내장 -->

          <span>
            <b-form-group
              label-cols="4"
              label-cols-lg="2"
              label="내장 : "
              label-for="input-default"
            >
              <b-form-textarea
                id="textarea-default"
                v-model="carOption1"
              ></b-form-textarea
            ></b-form-group>
          </span>

          <!-- 안전 -->

          <span>
            <b-form-group
              label-cols="4"
              label-cols-lg="2"
              label="안전 : "
              label-for="input-default"
            >
              <b-form-textarea
                id="textarea-default"
                v-model="carOption2"
              ></b-form-textarea
            ></b-form-group>
          </span>

          <!-- 편의 -->

          <span>
            <b-form-group
              label-cols="4"
              label-cols-lg="2"
              label="편의 : "
              label-for="input-default"
            >
              <b-form-textarea
                id="textarea-default"
                v-model="carOption3"
              ></b-form-textarea
            ></b-form-group>
          </span>

          <!-- 차량 설명 url 입력 폼 -->

          <span>
            <b-form-group
              label-cols="4"
              label-cols-lg="2"
              label="차량 설명 URL : "
              label-for="input-default"
            >
              <b-form-input
                id="input-default"
                v-model="carUsage"
              ></b-form-input>
            </b-form-group>
          </span>

          <!-- 위치 입력 폼 -->

          <span>
            <b-form-group
              label-cols="4"
              label-cols-lg="2"
              label="차량 위치 : "
              label-for="input-default"
            >
              <b-form-input
                id="input-default"
                v-model="carLocation"
              ></b-form-input>
            </b-form-group>
          </span>
        </p>

        <!-- modal 푸터(닫기 버튼과 등록 버튼이 존재) -->

        <template #modal-footer="{ cancel }">
          <button id="modal-cancel" v-on:click="cancel()">
            취소
          </button>
          <button id="modal-submit" v-on:click="carInfoAdd">등록</button>
        </template>
      </b-modal>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [],
      edit: [],

      //차량등록 모델에서 불러오는 데이터리스트

      carModelList: [
        "더 뉴 아반떼AD",
        "K5 3세대(하이브리드)",
        "올 뉴 K3",
        "올 뉴 아반떼",
        "K5 3세대",
      ],

      //정보 등록을 위한 변수

      carIndex: "",
      carModel: "",
      carNumber: "",
      carTakePerson: "",
      carFuel: "",
      carClear: 0,
      carOption1: "",
      carOption2: "",
      carOption3: "",
      carUsage: "",
      carLocation: "",
    };
  },
  methods: {
    //리스트에서 정보 수정을 하고 나서 정보를 전송하는 버튼 엑시오스

    sendData(Index, Clear) {
      //에러잡기용 코드

      if (Clear == 1) {
        Clear = 0;
      } else {
        Clear = 1;
      }

      this.$axios
        .get("http://localhost:8090/team3/carinfoupdate", {
          // get으로 보냄

          params: {
            carIndex: Index,
            carClear: Clear,
          },
        })
        .then(function() {
          console.log("차량 정보 업데이트 성공");
        })
        .catch(function(err) {
          console.log("차량 정보 업데이트 에러");
          console.log(err);
        });
    },

    //차량 추가 엑시오스

    carInfoAdd() {
      let obj = this;

      obj.$axios
        .post("http://localhost:8090/team3/carinsert", {
          // 정보 등록을 위한 함수

          carNumber: this.carNumber,
          carModel: this.carModel,
          carTakePerson: this.carTakePerson,
          carFuel: this.carFuel,
          // 삼항연산자로 에러잡기
          carClear: this.carClear == true ? 1 : 0,
          carOption1: this.carOption1,
          carOption2: this.carOption2,
          carOption3: this.carOption3,
          carUsage: this.carUsage,
          carLocation: this.carLocation,
        })
        .then(function() {
          console.log("차량 정보 삽입 성공");
        })
        .catch(function(err) {
          console.log("차량 정보 삽입 에러");
          console.log(err);
        });

      //새로고침 함수
    },
    carInfoEdit(carIndex) {
      let obj = this;

      obj.$axios
        .get("http://localhost:8090/team3/careditinfo", {
          params: {
            carIndex: carIndex,
          },
        })
        .then(function(res) {
          console.log("차량 수정 불러오기 성공");
          obj.edit = res.data;
        })
        .catch(function(err) {
          console.log("차량 목록 불러오기 에러");
          console.log(err);
        });
    },
    carEditSubmit(
      carIndex,
      carModel,
      carNumber,
      carTakePerson,
      carClear,
      carFuel,
      carOption1,
      carOption2,
      carOption3,
      carUsage,
      carLocation
    ) {
      this.$axios
        .post("http://localhost:8090/team3/careditsubmit", {
          carIndex: carIndex,
          carModel: carModel,
          carNumber: carNumber,
          carTakePerson: carTakePerson,
          carClear: carClear == true ? 1 : 0,
          carFuel: carFuel,
          carOption1: carOption1,
          carOption2: carOption2,
          carOption3: carOption3,
          carUsage: carUsage,
          carLocation: carLocation,
        })
        .then(function() {
          console.log("차량 수정 성공");
        })
        .catch(function(err) {
          console.log("차량 수정 에러");
          console.log(err);
        });
    },

    //차량 정보 삭제 버튼(에러남)

    // // deleteCar(car) {
    // //   let obj = this;

    // //   obj.$axios
    // //     .get("http://localhost:8090/team3/cardelete", {
    // //       params: {
    // //         carIndex: car,
    // //       },
    // //     })
    // //     .then(function() {
    // //       console.log("차량 정보 삭제 성공");
    // //     })
    // //     .catch(function(err) {
    // //       console.log("차량 정보 삭제 에러");
    // //       console.log(err);
    // //     });
    // // },
  },
  mounted() {
    let obj = this;

    obj.$axios
      .get("http://localhost:8090/team3/caradminget")
      .then(function(res) {
        console.log("차량 목록 불러오기 성공");
        obj.items = res.data;
      })
      .catch(function(err) {
        console.log("차량 목록 불러오기 에러");
        console.log(err);
      });
  },
};
</script>

<style>
.car-axios-button,
.car-axios-button-delete {
  color: white;
  border: none;
  border-radius: 5px;
  box-shadow: 1.5px 1.5px 1.5px black;
  transition-duration: 0.15s;
}

.car-axios-button {
  background-color: rgb(132, 194, 189);
}

.car-axios-button:hover {
  background-color: rgb(90, 129, 126);
}

.car-axios-button-delete {
  background-color: rgb(199, 23, 23);
}

.car-axios-button-delete:hover {
  background-color: rgb(146, 39, 39);
}

#car-form-padding {
  padding-top: 8px;
  padding-bottom: 8px;
}

input[id="input-checkbox"] {
  zoom: 2;
  cursor: pointer;
}

#car-add-btn {
  color: white;
  border: none;
  border-radius: 5px;
  box-shadow: 1.5px 1.5px 1.5px black;
  transition-duration: 0.15s;
  background-color: rgb(132, 194, 189);
  border: none;
}
#car-add-btn:hover,
#car-add-btn:active {
  background-color: rgb(90, 129, 126);
}

#modal-cancel {
  background-color: rgb(199, 23, 23);
  border: none;
  border-radius: 5px;
  color: white;
  width: 60px;
  height: 30px;
  transition: background-color 0.2s;
}

#modal-cancel:hover {
  background-color: rgb(146, 39, 39);
}

#modal-submit {
  background-color: rgb(132, 194, 189);
  border: none;
  border-radius: 5px;
  color: white;
  width: 60px;
  height: 30px;
  transition: background-color 0.2s;
}

#modal-submit:hover {
  background-color: rgb(90, 129, 126);
}
</style>
