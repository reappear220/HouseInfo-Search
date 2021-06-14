<template>
  <div>
    <main id="main">
      <section id="hero">
      </section>
      <!-- ======= Services Section ======= -->
      <section id="services" class="services">
        <div class="container">
          <div class="section-title" data-aos="fade-in" data-aos-delay="100">
            <h2 style="font-family: 'Poor Story', cursive; font-size: 50px; ">자유게시판</h2>
            <p style="font-family: 'Poor Story', cursive; font-size: 25px">
              <b>자유롭게 게시판을 이용할 수 있습니다~~~ 유익한 시간 보내세요~~ XD </b>
            </p>
          </div>
            <br>
            <div class="input-group mb-3">
            
            <input v-model="$store.state.freeboard.searchWord" @keydown.enter="freeboardList" type="text" class="form-control" placeholder="원하는 게시글을 검색해보세요"
                    style="font-family: 'Poor Story', cursive;">
            <button @click="freeboardList" class="btn btn-success" type="button" >Search</button>
          </div>

            <table class="table table-strip table-hover" style="font-family: 'Poor Story', cursive; font-size: 20px">
              <thead style="font-size: 23px">
                <tr>
                  <th>#</th>
                  <th>제목</th>
                  <th>작성자</th>
                  <th>작성일시</th>
                  <th>조회수</th>
                </tr>
              </thead>
                <tbody>
                  <!-- store 사용 -->

                  <!-- 직접 store 에 접근해도 된다. -->
                  <!-- <tr v-for="(board, index) in $store.state.board.list" @click="boardDetail(board.boardId)" v-bind:key="index"> -->
                  
                  <!-- getters 를 이용하는 코드 -->
                  <!-- computed - listGetters - getBoardList  -->
                  <tr v-for="(freeboard, index) in listGetters" @click="freeboardDetail(freeboard.boardId)" v-bind:key="index">
                    <td>{{ freeboard.boardId }}</td>
                    <td>{{ freeboard.title }}</td>
                    <td>{{ freeboard.userName }}</td>

                    <!-- function 을 바로 사용하는 경우 -->
                    <!-- <td>{{ makeDateStr(board.regDt.date.year, board.regDt.date.month, board.regDt.date.day, '.') }}</td> -->

                    <!-- computed + index 를 사용하는 경우 -->
                    <td>{{ formatDate[index] }}</td>
                    
                    <td>{{ freeboard.readCount }}</td>
                  </tr>
                </tbody>
              </table>
            <pagination2 v-on:call-parent="movePage" @click="window.location.reload()"></pagination2>
            <button class="btn btn-sm btn-primary" @click="showInsertModal">글쓰기</button>

            <insert-modal v-on:call-parent-insert="closeAfterInsert"></insert-modal>
            <!-- props 제거 -->
            <detail-modal v-on:call-parent-change-to-update="changeToUpdate" v-on:call-parent-change-to-delete="changeToDelete"></detail-modal>
            <update-modal v-on:call-parent-update="closeAfterUpdate"></update-modal>
  
        </div>
      </section>
    </main>
  </div>
</template>

<script>
import InsertModal from '../modals/InsertModal.vue';
import DetailModal from '../modals/DetailModal.vue';
import UpdateModal from '../modals/UpdateModal.vue';

import { Modal } from 'bootstrap';

import http from "@/common/axios.js";
import util from "@/common/utils.js";

import Pagination2 from './Pagination2.vue';

// 삭제
import Vue from 'vue';
import VueAlertify from 'vue-alertify'; 
Vue.use(VueAlertify);

export default {
  
  name: 'FreeBoard',
  components: { 
    InsertModal, DetailModal, UpdateModal,Pagination2
    },
  data(){
    return {
      // modal
      insertModal : null,
      detailModal : null,
      updateModal : null,
    }
  },

   computed :{
    // gttters 이용
    listGetters(){
      return this.$store.getters.getFreeBoardList; // no getBoardList()
    },
    formatDate : function(){
      let $this = this;
      // store 사용
      return this.$store.state.freeboard.list.map( function( freeboard ){
        return $this.makeDateStr(freeboard.regDt.date.year, freeboard.regDt.date.month, freeboard.regDt.date.day, '.')
      });
    }
  },
  methods : {

    // list
    // store actions 에 구현
    // 가능한 한 가지 방법
    freeboardList(){
      this.$store.dispatch('freeboardList');      
    },

    // pagination
    movePage(pageIndex){
      console.log("FreeBoardMainVue : movePage : pageIndex : " + pageIndex );


      this.$store.commit( 'SET_FREEBOARD_MOVE_PAGE', pageIndex );


      this.freeboardList();
    },

    // util
    makeDateStr : util.makeDateStr,

    // insert
    showInsertModal(){
      this.insertModal.show();
    },

    closeAfterInsert(){
      this.insertModal.hide();
      this.freeboardList();
    },

    // detail
    // board-vue 와 다르게 구현해 봄
    // Detail Component 에서 Server 요청을 하지 않고 이곳에서 수행 후에 Detail 보여줌
    freeboardDetail(boardId){
      // store 변경
      // this.boardId = boardId;
      // this.$store.commit('mutateSetBoardBoardId', boardId);

      http.get(
      '/freeboards/' + boardId, // props variable
      )
      .then(({ data }) => {
        console.log("DetailModalVue: data : ");
        console.log(data);
        
        if( data.result == 'login' ){
          this.$router.push("/login")
        }else{
          console.log("디데일데이터 :", data);
          this.$store.commit(
            'SET_FREEBOARD_DETAIL',
            { 
              boardId: data.dto.boardId,
              title: data.dto.title,
              content: data.dto.content,
              readCount : data.dto.readCount,
              userName: data.dto.userName,
              regDt: this.makeDateStr(data.dto.regDt.date.year, data.dto.regDt.date.month, data.dto.regDt.date.day, '.'),
              fileList: data.dto.fileList,
              isOwner: data.isOwner, // not data.dto.isOwner

            }
          );

          this.detailModal.show();
        }
      })
      .catch((error) => {
        console.log("DetailModalVue: error ");
        console.log(error);
      });

    },

    // update 
    changeToUpdate(){ // board parameter 필요 없음.
      // data update 사용 X
      // this.board = board; 
      this.detailModal.hide();
      this.updateModal.show();
    },

    closeAfterUpdate(){      
      // boardId 초기화 필요 X watch 사용 X
      //this.boardId = 0;

      this.updateModal.hide();
      this.freeboardList();
    },

    // delete
    // $emit board 사용 X
    // changeToDelete(board){
    changeToDelete(){
      this.detailModal.hide();

      var $this = this; // alertify.confirm-function()에서 this 는 alertify 객체
      this.$alertify.confirm(
        //'삭제 확인', '이 글을 삭제하시겠습니까?', <- ???? title 추가하면 cancel이 ok 처럼 동작
        '이 글을 삭제하시겠습니까?',
        function(){
          // board.boardId 사용 X
          $this.freeboardDelete(); // $this 사용
        },
        function(){
          console.log('cancel');
        }
      );
    },
    // delete
    freeboardDelete(){ // parameter 사용 X
    console.log(">>>>>>>>>>>>>>>>>>>>> " + this.$store.state.freeboard.boardId )
      http.delete(
        "/freeboards/" + this.$store.state.freeboard.boardId
        )
        .then(({ data }) => {
          console.log("FreeBoardMainVue: data : ");
          console.log(data);
          if( data.result == 'login' ){
            this.$router.push("/login")
          }else{
            this.freeboardList();
          }
        })
        .catch( error => {
            console.log(error)
        });
    }
  },
  created() {
    this.freeboardList();
  },
  mounted() {
    this.insertModal = new Modal(document.getElementById('insertModal'));
    this.detailModal = new Modal(document.getElementById('detailModal'));
    this.updateModal = new Modal(document.getElementById('updateModal'));
  },
}
</script>

<style scoped>
</style>