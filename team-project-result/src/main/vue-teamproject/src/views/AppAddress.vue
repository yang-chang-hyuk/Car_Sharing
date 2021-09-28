<template>
    <div id="app-address">
        <div class="address-body">
            <div class="address-header">
                <div class="address-input" @click="openAddressEmbed()">
                    <input type="text" placeholder="어디에서 출발하나요？" v-model="address" disabled/>
                </div>
                <div class="address-search">
                    <i class="fas fa-location-arrow" @click="searchAddress()" style="cursor:pointer"></i>
                </div>
            </div>
            <div class="address-main">
                <div ref="addrembed" class="address-embed">
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data: function() {
        return {
            address: ''
        }
    },
    methods: {
        openAddressEmbed: function() {
            new window.daum.Postcode({
                oncomplete: (data) => {
                    let roadAddr = data.roadAddress
                    // let extraRoadAddr = ''

                    // if(data.bname !== '' && /[도|로|가]$/g.test(data.bname))
                    //     extraRoadAddr += data.bname
                    
                    // if(data.buildingName != '' && data.apartment === 'Y')
                    //     extraRoadAddr += ((extraRoadAddr !== '') ? ', ' + data.buildingName : data.buildingName)
                    
                    // if(extraRoadAddr !== '')
                    //     extraRoadAddr = ' (' + extraRoadAddr + ')'
                    
                    // if(roadAddr !== '')
                    //     roadAddr += extraRoadAddr

                    // this.address = data.zonecode + " " + roadAddr
                    this.address = roadAddr
                    sessionStorage.setItem('_SearchAddress', this.address)
                },
                width: '100%', height: '100%'
            }).embed(this.$refs.addrembed)
        },
        searchAddress: function() {
            if(this.address === null || this.address === '') {
                alert('주소를 입력하세요.');
            }
            else {
                this.$router.push({
                    name: 'AppMain'
                })
                this.$router.go()
            }
        }
    }
}
</script>

<style scoped>
#app-address {
    margin: 0 auto;
    max-width: 600px;
}
.address-body {
    margin: 0 20px;
}
.address-header {
    margin-top: 42px;
    display: flex;
    flex-direction: row;
}
.address-header .address-input {
    width: 90%;
}
.address-header .address-search {
    width: 10%;
    color: #83C1BC;
    font-size: 32px;
    line-height: 64px;
    text-align: center;
    justify-content: center;
}
.address-header .address-input input {
    width: 100%;
    height: 54px;
    font-size: 22px;
    border: none;
    border-bottom: 3px solid #83C1BC;
    background-color: #ffffff00;
}
.address-header .address-input input::placeholder {
    font-size: 22px;
    color: #ccc;
}
.address-main {
    width: 100%;
    margin-top: 32px;
}
.address-main .address-embed {
    width: 100%;
    height: 500px;
}
</style>