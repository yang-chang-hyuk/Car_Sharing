<template>
  <div class="row justify-content-center">
    <div class="col-md-12">
      <br />

      <!-- 댓글을 리스트로 배열해서 나타내는 테이블 -->

      <table class="table table-hover">
        <thead class="thead-light">
          <tr>
            <th class="col-md-1">차량 번호</th>
            <th class="col-md-2">사용자 번호</th>
            <th class="col-md-2">작성날짜</th>
            <th class="col-md-6">내용</th>
            <th class="col-md-1">삭제</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="item in items" v-bind:key="item.Index">
            <td class="col-md-1">{{ item.carIndex }}</td>
            <td class="col-md-2">{{ item.userIndex }}</td>
            <td class="col-md-2">{{ item.reviewDate }}</td>
            <td class="col-md-6">{{ item.reviewContents }}</td>
            
            <!-- 댓글 삭제 버튼 -->

            <td class="col-md-1">
              <span
                class="delete-review"
                v-on:click="deleteReview(item.userIndex, item.carIndex)"
                >삭제</span
              >
            </td>
          </tr>
        </tbody>

      </table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [],
    };
  },
  methods: {

    // 댓글 삭제 엑시오스

    deleteReview(user, car) {
      let obj = this;

      obj.$axios
        .get("http://localhost:8090/team3/reviewdelete", {

          // get으로 삭제

          params: {
            userIndex: user,
            carIndex: car,
          },
        })
        .then(function() {
          console.log("댓글 삭제 성공");
        })
        .catch(function(err) {
          console.log("댓글 삭제 에러");
          console.log(err);
        });

      //새로고침 함수

      this.$router.go(this.$router.currentRoute);
    }
  },
  mounted() {
    let obj = this;

    obj.$axios
      .get("http://localhost:8090/team3/reviewadminget")
      .then(function(res) {
        console.log("댓글 가져오기 성공");
        obj.items = res.data;
      })
      .catch(function(err) {
        console.log("댓글 가져오기 오류");
        console.log(err);
      });
  },
};
</script>

<style>
.delete-review {
  color: red;
  transition: 0.2s;
}
.delete-review:hover {
  cursor: pointer;
  color: rgb(158, 26, 26);
}
</style>
