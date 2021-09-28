<template>
  <div id="app">
    <div class="container">
      <h1 class="p-5">관리자 페이지</h1>

      <!-- 페이지 세개를 분리시키는 탭 -->

      <b-tabs content-class="mt-3" fill>

        <!-- AccountAdmin.vue로 이동 -->
        <b-tab title="사용자 계정 관리" v-on:click="moveAccountManage"></b-tab>

        <!-- CarAdmin.vue로 이동 -->
        <b-tab title="차량 관리" v-on:click="moveCarManage"></b-tab>

        <!-- ReviewAdmin.vue로 이동 -->
        <b-tab title="댓글 관리" v-on:click="moveReviewManage"></b-tab>

      </b-tabs>
      <br>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  methods: {

    // 라우터 이동 메소드들

    moveAccountManage() {
      this.$router.push({ name: "AppAdminUserManagement" });
    },
    moveCarManage() {
      this.$router.push({ name: "AppAdminCar" });
    },
    moveReviewManage() {
      this.$router.push({ name: "AppAdminReview" });
    },
  },
};
</script>

<style>
#test {
  text-align: center;
}
.nav-link {
  font-size: 30px;
}
.container {
  max-width: 1200px;
}
a {
  color: rgb(132, 194, 189);
  transition: 0.2s color;
}
a:hover {
  color: rgb(90, 129, 126);
}
</style>
