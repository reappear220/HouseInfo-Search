<template>
  <nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
      <li v-if="prev" class="page-item">
        <a class="page-link" href="#" aria-label="Previous" @click="paginationChanged(startPageIndex - 1)">
          <span aria-hidden="true">&laquo;</span>
        </a>
      </li>
      <li v-for="index in ( endPageIndex-startPageIndex + 1 )" :key="index"
          v-bind:class="{active: (startPageIndex + index - 1 == $store.state.freeboard.currentPageIndex)}" class="page-item">
        <a @click="paginationChanged(startPageIndex + index - 1)" 
           class="page-link" href="#">{{ startPageIndex + index - 1 }}</a> <!-- href 는 그대로, 커서 모양 유지-->
      </li>
      <li v-if="next" class="page-item">
        <a class="page-link" href="#" aria-label="Next" @click="paginationChanged(endPageIndex + 1)">
          <span aria-hidden="true">&raquo;</span>
        </a>
      </li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: "Pagination2",
  data() {
    return {};
  },
computed: {
    pageCount: function(){
      return Math.ceil(this.$store.state.freeboard.totalListItemCount/this.$store.state.freeboard.listRowCount);
    },
    startPageIndex: function(){
      if( (this.$store.state.freeboard.currentPageIndex % this.$store.state.freeboard.pageLinkCount) == 0 ){ //10, 20...맨마지막
        return ((this.$store.state.freeboard.currentPageIndex / this.$store.state.freeboard.pageLinkCount)-1)*this.$store.state.freeboard.pageLinkCount + 1
      }else{
        return  Math.floor(this.$store.state.freeboard.currentPageIndex / this.$store.state.freeboard.pageLinkCount)*this.$store.state.freeboard.pageLinkCount + 1
      }
    },
    endPageIndex: function(){

      let ret = 0;
      if( (this.$store.state.freeboard.currentPageIndex % this.$store.state.freeboard.pageLinkCount) == 0 ){ //10, 20...맨마지막
        ret = ((this.$store.state.freeboard.currentPageIndex / this.$store.state.freeboard.pageLinkCount)-1)*this.$store.state.freeboard.pageLinkCount + this.$store.state.freeboard.pageLinkCount;
      }else{
        ret = Math.floor(this.$store.state.freeboard.currentPageIndex / this.$store.state.freeboard.pageLinkCount)*this.$store.state.freeboard.pageLinkCount + this.$store.state.freeboard.pageLinkCount;
      }
      return ( ret > this.pageCount ) ? this.pageCount : ret;
    },
    prev: function(){
      if( this.$store.state.freeboard.currentPageIndex <= this.$store.state.freeboard.pageLinkCount ){
        return false;
      }else{
        return true;
      }
    },
    next: function(){
      if( this.endPageIndex >=  this.pageCount){
        return false;
      }else{
        return true;
      }
    }
  },
  methods:{
    paginationChanged(pageIndex){
      console.log("paginationVue : paginationChanged : pageIndex : " + pageIndex );

      this.$emit('call-parent', pageIndex);
    }
  },
  
}
</script>
