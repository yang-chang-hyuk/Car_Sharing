<template>
<div class="MyPageView">
    <div class="container-fluid">
        <div class="CarInfo-header">
            <p class="moveBack-Style" @click="moveBack">뒤로가기</p>
            <p class="carInfo-Header">내가 쓴 댓글</p>
        </div>
        <div class="Car-review-btn">
            <div class="ReviewBtn">댓글</div>
        </div>
        <div class="Car-review-contents">
            <div class="review-body" v-for="item in reviews" v-bind:key="item.index">
                <div class="review-name">
                    <span v-if="userProfile !== null && userProfile">
                        <img v-bind:src="userProfile" alt="" class="img-item">
                    </span>
                    <span v-else style="float: left;">
                        <img src="../assets/img/user-icon.png" alt="" class="img-item">
                    </span>
                    <span style="padding-left: 10px;">{{ userNickName }}</span>
                </div>
                <div class="review-carinfo">
                    <span>{{item.carModel}}</span>
                </div>
                <div class="review-carinfo">
                    <span>{{item.carNumber}}</span>
                </div>
                <div class="review-content">
                    <span style="padding-left:64px"><em>"{{item.reviewContents}}"</em></span>
                </div>
                <div class="review-date">
                    <span style="padding-left:64px">{{item.reviewDate}}</span>
                </div>
            </div>
        </div>
    </div>
</div>
</template>

<script>
export default {
    data() {
        return {
            userNickName: '',
            userProfile: '',
            
            reviews: []
        }
    },
    methods: {
        moveBack() {
            this.$router.push({ name: "AppUserMyPage" })
        }
    },
    mounted() {
        let userPage = this;

        // 마이페이지 정보 가져오기
        userPage.$axios.get('http://localhost:8090/vue/userDetail', {
            params: { userIndex: sessionStorage.getItem('_UserConn-INDEX') }
        })
        .then(function(res) {
            userPage.userNickName = res.data.userNickname

            // 리뷰정보 가져오기
            userPage.$axios.get('http://localhost:8090/vue/getReview', {
                params: {
                    userIndex: sessionStorage.getItem('_UserConn-INDEX')
                }
            })
            .then(response => {
                userPage.reviews = response.data
            })
            .catch(error => {
                console.error(error);
            })

            // 프로필 사진 가져오기
            userPage.axios.get('http://localhost:8090/vue/getProfile', {
                params: {
                userIndex: sessionStorage.getItem('_UserConn-INDEX')
                }
            }).then(response => {
                userPage.userProfile = response.data.userProfile
            }).catch(error => {
                console.error(error);
            })
        })
        .catch(function(error) {
            console.error(error);
        })
    }
}
</script>

<style>
    .MyPageView {
        margin: 0 auto;
        width: 940px;
    }

    .container-fluid {
        display: grid;
        grid-template-areas: 
        'CarInfoHeader CarInfoHeader CarInfoHeader'
        'CarReviewBtn CarReviewBtn CarReviewBtn'
        'CarReviewContents CarReviewContents CarReviewContents'
        ;
    }

    .carInfo-Header {
        text-align: left;
        font-size: 30px;
        font-weight: bold;
    }

    .CarInfo-header {
        grid-area: CarInfoHeader;
        border-bottom: 1px solid silver;
    }

    .Car-review-btn {
        grid-area: CarReviewBtn;
        border-bottom: 4px solid #84C2BD;
    }

    .Car-review-contents {
        width: 900px;
        display: flex;
        flex-direction: column;
    }

    .ReviewBtn {
        margin: 15px;
    }

    .inputfile {
    width: 0px; 
    height: 0px; 
    overflow: hidden;
    }

    .review-body:nth-child(1) {
        border-top: 1px solid #ccc;
    }
    .review-body {
        width: 100%;
        height: 80px;
        border-bottom: 1px solid #ccc;
        margin-top: 10px;
        display: flex;
        justify-content: space-around;
    }

    .review-content, .review-date, .review-name, .review-carinfo {
        line-height: 75px;
    }

    .img-item {
        width: 50px;
        height: 50px;
        border-radius: 50%;
    }

    .moveBack-Style {
        text-align: left;
        margin: 25px 0px 50px 0px;
        cursor: pointer;
    }
</style>