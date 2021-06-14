<template>
<div class="modal" tabindex="-1" id="houseModal">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 id= "modal-t" class="modal-title">부동산 상세 정보입니다</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>

      <div class="modal-body">
        <table class="table table-hover">
          <tbody>
            <tr><td><h4><b>아파트 명</b></h4></td> <td><h4><b>{{ $store.state.board.aptName }}</b></h4></td></tr>
            <tr><td>동 / 지번</td><td>{{ $store.state.board.dong }} {{ $store.state.board.jibun }}</td></tr>
            <tr><td>건축년도</td><td>{{ $store.state.board.buildYear }}</td></tr>
            <tr><td>거래타입</td><td>{{ $store.state.board.dealType }}</td></tr>
            <tr><td colspan="2">
                  <LineChart
                    :chart-data = 'datacollection'
                  ></LineChart></td></tr>
          </tbody>
        </table>
  <br>
        <table class="table table-striped">
              <thead>
                <tr>
                  <th>no</th>
                  <th>code</th>
                  <th>거래 날짜</th>
                  <th>가격</th>
                  <th>층</th>
                  <th>면적</th>
                  <th></th>
                </tr>
              </thead>
                <tbody>
                  <tr v-for="(detail, index) in $store.state.board.listDetail" v-bind:key="index">
                    <td>{{ index }}</td>
                    <td>{{ detail.code }}</td>
                    <td>{{ detail.dealYear }}년  {{ detail.dealMonth }}월  {{ detail.dealDay }}일</td>
                    <td>{{ detail.dealAmount }}</td>
                    <td>{{ detail.floor }}</td>
                    <td>{{ detail.area }}</td>
                  </tr>
                </tbody>
              </table>

      </div>
      <div class="modal-footer">
        <!-- <button  class="btn btn-sm btn-primary btn-outline" data-dismiss="modal" type="button" ></button> -->
      </div>
    </div>
  </div>
</div>
</template>

<script>
import LineChart from './Line.js';
// import http from "@/common/axios.js";
export default{
    name: 'HouseModal',
    components :{
      LineChart,
    },
    data(){
      return {
        detailset : this.$store.state.board.listDetail,
        datacollection :null,
        loaded : false
      }
    },
    methods: {

      fillData(){
        const labelset = [];
        const dataset = [];
        //const detailset = this.$store.state.board.listDetail;
        console.log("그래프 데이터는 ", this.detailset)
        for (var i = 0; i < this.detailset.length; i++) {
          labelset.push(String(this.detailset[i].dealYear) +'/'+ String(this.detailset[i].dealMonth) +'/'+ String(this.detailset[i].dealDay));
          dataset.push(parseInt(this.detailset[i].dealAmount));
        }
        console.log("최종:", dataset);

        this.datacollection = {
          labels : labelset,
          datasets : [
            {
              label : '거래가격',
              backgroundColor : '#99CCFF',
              borderColor : 'lightblue',
              pointBackgroundColor : 'blue',
              borderWidth : 2,
              pointBorderColor : 'blue',
              data : dataset
            }
          ]

        }
      }

    },
    mounted(){
      // this.createChart(myChart2, this.chartD)
      this.fillData()
    },
  
}
</script>
<style scoped>

</style>