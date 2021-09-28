<template>
    <div id="map">

    </div>
</template>

<script>
export default {
    data: function() {
        return {
            //  키 값
            // mapKey: 'c01c02b64fd891c0417b0fb2872679f9',     //  AWS 8090
            mapKey: '26f4cf92f4e38bcb481f9244e8f9d2cd', //  8090
            // mapKey: 'f937c1ec371ef909f5e2b374d9a73ce8', //  8080

            //  기본 위치
            lat: 35.14866244535633,
            lng: 129.11355158997253,
            //  차량 정보
            carMapInfo: []
        }
    },
    mounted: function() {
        window.kakao && window.kakao.maps ? this.initMap() : this.initScript()

        let thisVue = this
        thisVue.$axios.get('http://localhost:8090/teamcarsharing/api/GetCarCountFromUniqueLocation').then(function(response) {
            thisVue.carMapInfo = response.data
        }).catch(function(error) {
            console.error(error)
        })
    },

    methods: {
        initMap: function() {
            const thisVue = this

            const container = document.getElementById('map')
            const options = {
                center: new kakao.maps.LatLng(thisVue.lat, thisVue.lng),
                level: 3
            }

            let map = new kakao.maps.Map(container, options)

            // kakao.maps.event.addListener(map, 'click', function(event) {
            //     let location = event.latLng
            //     let message = "위도: " + location.getLat() + "\n경도: " + location.getLng()
            //     console.log(message)
            // })

            let addr = sessionStorage.getItem('_SearchAddress')
            if(addr !== null && addr !== '') {
                let geocoder = new kakao.maps.services.Geocoder()
                geocoder.addressSearch(addr, function(result, status) {
                    if(status === kakao.maps.services.Status.OK) {
                        let coords = new kakao.maps.LatLng(result[0].y, result[0].x)
                        
                        let markerImage = 'http://localhost:8090/images/marker/marker.png'
                        let markerSize = new kakao.maps.Size(56, 56)
                        let markerOffs = {offset: new kakao.maps.Point(27, 56)}
                        let marker = new kakao.maps.Marker({
                            map: map,
                            image: new kakao.maps.MarkerImage(markerImage, markerSize, markerOffs),
                            position: coords
                        })

                        map.setCenter(coords);
                    }
                })
            }

            //  지도에 차량 위치 표시하기
            if(thisVue.carMapInfo.length > 0) {
                for(let i in thisVue.carMapInfo) {
                    var carLocation =  new kakao.maps.services.Geocoder()
                    carLocation.addressSearch(thisVue.carMapInfo[i].carLocation, function(result, status) {
                        if(status === kakao.maps.services.Status.OK) {
                            let image = ''
                            if(thisVue.carMapInfo[i].carCount == 1)
                                image = 'http://localhost:8090/images/marker/car_marker_1.png'
                            else if(thisVue.carMapInfo[i].carCount == 2)
                                image = 'http://localhost:8090/images/marker/car_marker_2.png'
                            else if(thisVue.carMapInfo[i].carCount >= 3)
                                image = 'http://localhost:8090/images/marker/car_marker_3.png'
                            let imageSize = new kakao.maps.Size(56, 56)
                            let imageOffs = {
                                offset: new kakao.maps.Point(27, 62)
                            }
                            
                            let markerResult = new kakao.maps.Marker({
                                position: new kakao.maps.LatLng(result[0].y, result[0].x),
                                image: new kakao.maps.MarkerImage(image, imageSize, imageOffs),
                                clickable: true
                            })

                            //  차량 위치 마커를 누르면 해당 위치의 차량들의 데이터를 가져온다.
                            kakao.maps.event.addListener(markerResult, 'click', function() {
                                //  대여 가능한 차량 가져오기
                                thisVue.$axios.get('http://localhost:8090/teamcarsharing/api/GetAllowReserveCarFromLocation', {
                                    params: {
                                        location: thisVue.carMapInfo[i].carLocation
                                    }
                                }).then(function(response) {
                                    thisVue.$emit("setCarData", response.data)
                                    thisVue.$emit("setSearchAddress", thisVue.carMapInfo[i].carLocation)
                                    // alert(JSON.stringify(response.data))
                                }).catch(function(error) {
                                    console.error(error)
                                })

                                //  대여 가능한 차량 개수 가져오기
                                thisVue.$axios.get('http://localhost:8090/teamcarsharing/api/GetAllowReserveCarCountFromLocation', {
                                    params: {
                                        location: thisVue.carMapInfo[i].carLocation
                                    }
                                }).then(function(response) {
                                    thisVue.$emit('setCarCount', response.data)
                                }).catch(function(error) {
                                    console.error(error)
                                })

                                //  대여된 차량 가져오기
                                thisVue.$axios.get('http://localhost:8090/teamcarsharing/api/GetReserveCarFromLocation', {
                                    params: {
                                        location: thisVue.carMapInfo[i].carLocation
                                    }
                                }).then(function(response) {
                                    thisVue.$emit('setCarReserveData', response.data)
                                }).catch(function(error) {
                                    console.error(error)
                                })

                                thisVue.$emit('toggleDisplayCarData', true)
                            })
                            markerResult.setMap(map)

                            // console.log('created! lat: ' + result[0].y + ' lng: ' + result[0].x)
                        }
                    })
                }
            }

            // var markerPos = new kakao.maps.LatLng(thisVue.lat, thisVue.lng)
            // var marker = new kakao.maps.Marker({
            //     position: markerPos
            // })
            // marker.setMap(map)
        },
        //  지도 스크립트 불러오기
        initScript: function() {
            const script = document.createElement('script')
            script.onload = () => kakao.maps.load(this.initMap)
            script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=' + this.mapKey + '&libraries=services'
            document.head.appendChild(script)
        }
    }
}
</script>

<style scoped>
#map {
    width: 100%;
    height: 100vh;
    z-index: 1;
}
</style>