<template>
    <div id="app">
        <router-view/>
    </div>
</template>

<style lang="scss">
#app {
    // font-family: Avenir, Helvetica, Arial, sans-serif;
    font-family: 'Noto Sans KR', sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    // text-align: center;
    color: #2c3e50;
}
.public-shadow {
    -webkit-box-shadow: 5px 5px 7px 1px rgba(0,0,0,0.45); 
    box-shadow: 5px 5px 7px 1px rgba(0,0,0,0.45);
}
.btn-not-allow {
    cursor: not-allowed;
}

/* 스크롤바 설정*/ ::-webkit-scrollbar{ /* 스크롤바 막대 너비 설정 */ width: 10px; height: 10px;} /* 스크롤바 막대 설정*/ ::-webkit-scrollbar-thumb{ /* 스크롤바 막대 높이 설정 */ height: 6px; background-color: rgb(170, 170, 170); /* 스크롤바 둥글게 설정 */ border-radius: 1.5em; } /* 스크롤바 뒷 배경 설정*/ ::-webkit-scrollbar-track{ background-color: rgba(0,0,0,0); }
</style>
