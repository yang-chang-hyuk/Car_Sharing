<template>
  <div class="row justify-content-center">
    <div class="col-md-12">
      <br />
      <table class="table table-hover">
        <thead class="thead-light">
          <tr>
            <th class="col-md-2">차량 번호</th>
            <th class="col-md-2">작성날짜</th>
            <th class="col-md-6">내용</th>
            <th class="col-md-2">삭제</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in items" v-bind:key="item.Index">
            <td class="col-md-2">{{ item.carIndex }}</td>
            <td class="col-md-2">{{ item.reviewDate }}</td>
            <td class="col-md-6">{{ item.reviewContents }}</td>

            <!-- 리뷰 삭제 버튼 -->

            <td class="col-md-2">
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

    // 댓글 삭제 엑시오스(ReviewAdmin과 동일함)

    deleteReview(user, car) {
      let obj = this;

      obj.$axios
        .get("http://localhost:8090/team3/reviewdelete", {

          // param으로 전달함(get으로 삭제함)

          params: {
            userIndex: user,
            carIndex: car,
          },
        })
        .then(function() {
          console.log("댓글 삭제 성공");
          alert("댓글이 삭제 되었습니다.");
          obj.$router.push({ name: "AppAdminMain" });
        })
        .catch(function(err) {
          console.log("댓글 삭제 에러");
          console.log(err);
        });
    },
  },
  mounted() {
    let obj = this;

    obj.$axios
      .get("http://localhost:8090/team3/reviewadminuserget", {

        // AccountAdmin에서 이동할 때 userIndex를 받아옴

        params: {
          userIndex: obj.$route.params.userIndex,
        },
      })
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

<style></style>
