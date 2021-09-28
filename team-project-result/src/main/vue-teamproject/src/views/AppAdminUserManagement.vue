<template>
  <div class="row justify-content-center">
    <div class="col-md-12">
      <br />

      <!-- 유저 정보를 나타내는 테이블 -->

      <table class="table table-hover">
        <thead class="thead-light">
          <tr>
            <th class="col-md-1">No.</th>
            <th class="col-md-3">이름</th>
            <th class="col-md-3">아이디</th>

            <!-- 툴팁(마우스를 올리면 나오게) -->

            <th class="col-md-3"><div v-b-tooltip.hover title="클릭하면 해당 회원이 작성한 댓글을 볼 수 있는 페이지로 넘어갑니다.">댓글 관리</div></th>
            <th class="col-md-2">회원 차단</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="item in items" v-bind:key="item.userIndex">
            <td class="col-md-1">{{ item.userIndex }}</td>
            <td class="col-md-3">{{ item.userName }}</td>
            <td class="col-md-3">{{ item.userId }}</td>

            <!-- 유저가 작성한 댓글로 넘어가는 링크 -->

            <td class="col-md-3"><span class="review-router-span" v-on:click="viewUserReview(item.userIndex)">댓글 보기</span></td>

            <!-- 차단 여부 체크박스 -->

            <td class="col-md-2">
              <input
                type="checkbox"
                v-on:click="setBlock(item.userIndex, item.isBlock)"
                v-model="item.isBlock"
              />

              <!-- 체크박스 체크 여부에 따른 텍스트 출력 -->

              <span v-if="item.isBlock == '1'">
                차단 완료
              </span>
              <span v-else>
                차단 하기
              </span>

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

    //put 작동 안됨

    // putisblock() {
    //   // axios를 사용하여 데이터 수정
    //   this.$axios
    //     .put("http://localhost:8090/team3/isblocktruefalse", {
    //       isBlock: this.isBlock,
    //     })
    //     .then(function() {
    //       console.log("비동기 통신 성공");
    //     })
    //     .catch(function(err) {
    //       console.log("비동기 통신 실패");
    //       console.log(err);
    //     });
    // },

    //차단하기 엑시오스

    setBlock(index, block) {

      //에러 잡기용

      if (block == 0) {
        block = 1;
      }
      else {
        block = 0;
      }

      this.$axios
        .get("http://localhost:8090/team3/setblock", {
          params: {
            userIndex: index,
            isBlock: block,
          },
        })
        .then(function() {
          console.log("차단 통신 성공");
        })
        .catch(function(err) {
          console.log("차단 통신 실패");
          console.error(err);
        });
    },

    //유저가 작성한 댓글을 볼수있는 페이지(ReviewDetail)로 넘어감

    viewUserReview(userIndex) {
      this.$router.push({ name: "AppAdminReviewDetail", params: { userIndex: userIndex } });
    }
  },
  mounted() {
    let obj = this;

    obj.$axios
      .get("http://localhost:8090/team3/accountadminget")
      .then(function(res) {
        console.log("계정 불러오기 성공");
        obj.items = res.data;
      })
      .catch(function(err) {
        console.log("계정 불러오기 에러");
        console.log(err);
      });
  },
};
</script>

<style>
.review-router-span{
  font-weight: bold;
}
.review-router-span:hover {
  cursor: pointer;
  color: rgb(132, 196, 190);
  text-decoration: underline;
  transition: color .2s;
}
</style>
