<template>
  <div>
    <main id="main">
      <section id="hero">
      </section>
      <!-- ======= Services Section ======= -->
      <section id="services" class="services">
        <div class="container">
          <div class="section-title" data-aos="fade-in" data-aos-delay="100">
            <h2 style="font-family: 'Poor Story', cursive; font-size: 50px; ">부동산 정보</h2>
            <p style="font-family: 'Poor Story', cursive; font-size: 23px; ">
              <b>건물의 이름과 원하는 지역의 동을 검색하실 수 있습니다.</b> 부동산 테이블에 있는 건물들의 <b>위치</b>를 <b>지도</b>로 확인해보세요!  <br>
              목록을 누르시면 해당 건물의 <b>상세정보</b>와 <b>거래내역</b>을 확인하실 수 있습니다. 유익한 시간 보내세요~~ XD
            </p>
          </div>
            <div>
              <div id="map" class="map"></div>
            </div>
            <br>
            <div class="input-group mb-3">
            
            <input v-model="$store.state.board.searchWord" @keydown.enter="boardList" type="text" class="form-control" placeholder="동 또는 아파트를 검색해보세요"
                    style="font-family: 'Poor Story', cursive; ">
            <button @click="boardList" class="btn btn-success" type="button" >Search</button>
          </div>

            <table class="table table-hover"  style="font-family: 'Poor Story', cursive; font-size: 20px;">
              <thead style="font-size: 23px">
                <tr>
                  <th>no</th>
                  <th>아파트 명</th>
                  <th>건축년도</th>
                  <th>법정동</th>
                  <th>지번</th>
                </tr>
              </thead>
                <tbody>
                  <!-- store 사용 -->

                  <!-- 직접 store 에 접근해도 된다. -->
                  <!-- <tr v-for="(board, index) in $store.state.board.list" @click="boardDetail(board.boardId)" v-bind:key="index"> -->
                  
                  <!-- getters 를 이용하는 코드 -->
                  <!-- computed - listGetters - getBoardList  -->
                  <tr v-for="(board, index) in listGetters" @click="[showM,boardDetail(board.no)]" v-bind:key="index">
                    <td>{{ board.no }}</td>
                    <td>{{ board.aptName }}</td>
                    <td>{{ board.buildYear }}</td>
                    <td>{{ board.dong }}</td>
                    <td>{{ board.jibun }}</td>
                  </tr>
                </tbody>
              </table>
          <pagination v-on:call-parent="movePage" @click="window.location.reload()"></pagination>
          <house-modal></house-modal>
        </div>
      </section>
    </main>
  </div>
</template>

<script>
import Pagination from "./Pagination.vue";
import HouseModal from "../modals/HouseModal.vue";

import { Modal } from 'bootstrap';

import http from "@/common/axios.js";
//import util from "@/common/util.js";

export default {
  name: "HouseBoard",
  components: {
    Pagination,
    HouseModal,
  },
  data() {
    return {
      showModal: false,
      
    };
  },
  computed :{
    // gttters 이용
    listGetters(){
      return this.$store.getters.getBoardList; // no getBoardList()
    }
  },
  methods : {
    refreshAll() {
            // 새로고침
            this.$router.go();
    },

    boardList(){
      this.$store.dispatch('boardList');
      //const parsedToDos = JSON.parse( localStorage.getItem('vuex'));
      //console.log("로컬에서 가져오기" , parsedToDos['board'].searchWord);
      JSON.parse( localStorage.getItem('vuex'))['board'].searchWord = '';
    },
    movePage(pageIndex){
      console.log("BoardMainVue : movePage : pageIndex : " + pageIndex );

      // store commit 으로 변경
      // this.offset = (pageIndex - 1) * this.listRowCount;
      // this.currentPageIndex = pageIndex;
      this.$store.commit( 'SET_BOARD_MOVE_PAGE', pageIndex );


      this.boardList();
    },

    boardDetail(no){

      http.get(
      '/boards/' + no, // props variable
      )
      .then(({ data }) => {
        console.log("HouseModalVue: data : ");
        console.log(data);
        const tempData = data[0];
        this.$store.commit(
          'SET_BOARD_DETAIL',
          { 
            no: tempData.no,
            aptName: tempData.aptName,
            dealAmount: tempData.dealAmount,
            buildYear: tempData.buildYear,
            area: tempData.area,
            dong: tempData.dong,
            jibun: tempData.jibun,
            floor: tempData.floor,
            dealType: tempData.dealType,
            lat: tempData.lat,
            lng: tempData.lng

          }
        );
        this.$store.commit(
          'SET_BOARD_DETAIL_LIST', data
        );
        this.houseModal.show();
        })

      
      .catch((error) => {
        console.log("HouseModalVue: error ");
        console.log(error);
      });

      
    },
    showM(){
      setTimeout(function(){
            location.reload();
        });
      //this.houseModal.show();
    },
    initMap() {
            var container = document.getElementById('map');
            var options = {
              center: new kakao.maps.LatLng(37.5666805, 126.9784147),
              level: 7
            };

            var map = new kakao.maps.Map(container, options);
            
            // 마커이미지 주소
            var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
            // console.log("리스트는", this.$store.state.board.list)
            for (var i = 0; i < this.$store.state.board.list.length; i++) {
                var imageSize = new kakao.maps.Size(24, 35); 
                var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

                var positions = {
                  title: this.$store.state.board.list[i].aptName,
                  latlng : new kakao.maps.LatLng(this.$store.state.board.list[i].lat, this.$store.state.board.list[i].lng)
                }
                // 마커를 생성합니다
                var marker = new kakao.maps.Marker({
                    map: map, // 마커를 표시할 지도
                    position: positions.latlng,
                    title:positions.title,
                    image : markerImage 
                    
                })
                var iwContent = `<div style="padding:5px;">`+ `<b>`
                  + this.$store.state.board.list[i].aptName +`</b><br>`
                  + this.$store.state.board.list[i].dong + ' ' + this.$store.state.board.list[i].jibun+
                  `</div>`,
                 // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
                  iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
                var infowindow = new kakao.maps.InfoWindow({
                    content : iwContent,
                    removable : iwRemoveable
                });
                kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
                kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));

            }
            // 인포윈도우를 표시하는 클로저를 만드는 함수
            function makeOverListener(map, marker, infowindow) {
                return function() {
                    infowindow.open(map, marker);
                };
            }
            // 인포윈도우를 닫는 클로저를 만드는 함수
            function makeOutListener(infowindow) {
                return function() {
                    infowindow.close();
                };
            }
            marker.setMap(map);
        }

  },
  created() {
    this.boardList();
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement('script');
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=c912af8f1ce2f9bdb39ea6b4b2148c89';
      document.head.appendChild(script);
    }

    this.houseModal = new Modal(document.getElementById('houseModal'));
  },
  
};
</script>

<style scoped>
.map {
  width: 100%;
  height: 400px;
}
</style>
