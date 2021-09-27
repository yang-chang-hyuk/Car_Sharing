<template>
    <div id="app-main">
        <MainAttache
            v-on:setSearchAddress="setSearchAddress"
            v-on:toggleDisplayCarData="toggleDisplayCarData"
            v-bind:carData="carData"
            :carReserveData="carReserveData"
            :carCounts="carCounts"
            :displayCarData="displayCarData"
            :searchAddress="searchAddress">
        </MainAttache>
        <Map
            v-on:setCarData="setCarData"
            v-on:setCarCount="setCarCount"
            v-on:setCarReserveData="setCarReserveData"
            v-on:toggleDisplayCarData="toggleDisplayCarData"
            v-on:setSearchAddress="setSearchAddress">
        </Map>
    </div>
</template>

<script>
import MainAttache from '@/components/MainAttache'
import Map from '@/components/Map'

export default {
    components: {
        MainAttache, Map
    },
    data: function() {
        return {
            displayCarData: false,
            searchAddress: null,
            carData: [],
            carReserveData: [],
            carCounts: 0
        }
    },
    methods: {
        setCarData: function(param1) {
            this.carData = param1
        },
        setCarCount: function(param) {
            this.carCounts = param
        },
        setCarReserveData: function(param1) {
            this.carReserveData = param1  
        },
        setSearchAddress: function(param) {
            this.searchAddress = param
        },
        toggleDisplayCarData: function(param) {
            this.displayCarData = param
        }
    }
}
</script>

<style scoped>
</style>