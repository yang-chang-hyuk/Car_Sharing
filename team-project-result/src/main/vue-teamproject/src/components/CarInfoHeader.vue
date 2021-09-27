<template>
  <div class="my-view">
    <div class="container-fluid">

      <!-- 제일 위에 헤더 -->
      <div class="car-header">
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
            >차량정보</b
          >
        </div>
      </div>
      <!-- 제일 위에 헤더 -->
      
    </div>
  </div>
</template>

<script>
export default {
  // 뒤로가기 버튼
  methods: {
    moveBack() {
      this.$router.push({ name: "AppMain" });
    },
    // 뒤로가기 버튼 끝
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
  "header-car header-car";
}

.car-header {
  grid-area: header-car;
  border-bottom: 1px solid silver;
}

#ul {
  color: dimgray;
}
</style>