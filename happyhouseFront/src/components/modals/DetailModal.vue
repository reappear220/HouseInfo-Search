<template>
<div class="modal" tabindex="-1" id="detailModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">글 상세</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="window.location.reload()"></button>
      </div>
      <div class="modal-body">
        <table class="table table-hover">
          <tbody>
            <tr><td>글번호</td><td>{{ $store.state.freeboard.boardId }}</td></tr>
            <tr><td>제목</td><td>{{ $store.state.freeboard.title }}</td></tr>
            <tr><td>내용</td><td v-html="$store.state.freeboard.content"></td></tr>
            <tr><td>작성자</td><td>{{ $store.state.freeboard.userName }}</td></tr>
            <!-- 아래 코드는 오류 발생 초기 생성 시점에 regDt = {} -->
            <!-- <tr><td>작성일시</td><td>{{ makeDateStr(regDt.date.year, regDt.date.month, regDt.date.day, '.') }}</td></tr> -->
            <tr><td>작성일시</td><td>{{ $store.state.freeboard.regDt }}</td></tr>
            <tr><td>조회수</td><td>{{ $store.state.freeboard.readCount }}</td></tr>
            <!-- New for FileUpload -->
            <tr><td colspan="2">첨부파일</td></tr>
            <tr>
              <td colspan="2">
                <div v-for="(file, index) in $store.state.freeboard.fileList" :key="index">
                  <span class="fileName">{{ file.fileName }}</span>
                  &nbsp;&nbsp;
                  <a type="button" class="btn btn-outline btn-default btn-xs" v-bind:href="file.fileUrl" download>내려받기</a>
                </div>
              </td>
            </tr>
            <!-- / New for FileUpload -->
          </tbody>
        </table>
      </div>
      <div class="modal-footer">
        <button v-show="$store.state.freeboard.isOwner" @click="changeToUpdate" class="btn btn-sm btn-primary btn-outline" data-dismiss="modal" type="button">글 수정하기</button>
        <button v-show="$store.state.freeboard.isOwner" @click="changeToDelete" class="btn btn-sm btn-warning btn-outline" data-dismiss="modal" type="button">글 삭제하기</button>
      </div>
    </div>
  </div>
</div>
</template>

<script>

export default {
  name: 'DetailModal',

    methods: {
        changeToUpdate(){
          this.$emit( 'call-parent-change-to-update' );
        },
        changeToDelete(){
          this.$emit( 'call-parent-change-to-delete' );
        }
    },
}
</script>

<style>
</style>