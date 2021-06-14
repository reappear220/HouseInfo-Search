import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

import http from "@/common/axios.js";
import router from "@/routers/routers.js";

export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: {
    // login, NavBar
    login: {
      isLogin: false,
      userId: "gywls",
      userPassword: "11111",
      userName: "",
      userEmail: "",
      userAddressGu: "",
      userAddressDong: "",
      // userProfileImageUrl: "",
    },
    //
    board: {
      // list
      list: [],
      limit: 10,
      offset: 0,
      searchWord: "",

      // pagination
      listRowCount: 10,
      pageLinkCount: 10,
      currentPageIndex: 1,

      totalListItemCount: 0,

      // detail, update, delete
      no: 0,
      aptName: "",
      dealAmount: "",
      buildYear: "",
      area: "",
      dong: "",
      jibun: "",
      dealType: "",
      listDetail: [],
      lat: "",
      lng: "",
    },

    freeboard: {
      // list
      list: [],
      limit: 10,
      offset: 0,
      searchWord: "",

      // pagination
      listRowCount: 10,
      pageLinkCount: 10,
      currentPageIndex: 1,

      totalListItemCount: 0,

      // detail, update, delete
      boardId: 0,
      title: "",
      content: "",
      userName: "",
      regDt: {},
      readCount: 0,
      fileList: [],
      isOwner: false,
    },
  },
  mutations: {
    SET_LOGIN(state, payload) {
      state.login.isLogin = payload.isLogin;
      state.login.userName = payload.userName;
      state.login.userEmail = payload.userEmail;
      state.login.userPassword = payload.userPassword;
      state.login.userAddressGu = payload.userAddressGu;
      state.login.userAddressDong = payload.userAddressDong;
      // state.login.userProfileImageUrl = payload.userProfileImageUrl;
    },

    SET_BOARD_LIST(state, list) {
      state.board.list = list;
    },

    SET_BOARD_TOTAL_LIST_ITEM_COUNT(state, count) {
      state.board.totalListItemCount = count;
    },

    SET_BOARD_MOVE_PAGE(state, pageIndex) {
      state.board.offset = (pageIndex - 1) * state.board.listRowCount;
      state.board.currentPageIndex = pageIndex;
    },

    SET_BOARD_DETAIL(state, payload) {
      state.board.no = payload.no;
      state.board.aptName = payload.aptName;
      state.board.dealAmount = payload.dealAmount;
      state.board.buildYear = payload.buildYear;
      state.board.area = payload.area;
      state.board.dong = payload.dong;
      state.board.jibun = payload.jibun;
      state.board.floor = payload.floor;
      state.board.dealType = payload.dealType;
    },
    SET_BOARD_DETAIL_LIST(state, payload) {
      //console.log(payload);
      state.board.listDetail = payload;
    },

    // 자유게시판
    SET_FREEBOARD_LIST(state, list) {
      state.freeboard.list = list;
    },

    SET_FREEBOARD_TOTAL_LIST_ITEM_COUNT(state, count) {
      state.freeboard.totalListItemCount = count;
    },

    SET_FREEBOARD_MOVE_PAGE(state, pageIndex) {
      state.freeboard.offset = (pageIndex - 1) * state.freeboard.listRowCount;
      state.freeboard.currentPageIndex = pageIndex;
    },

    SET_FREEBOARD_DETAIL(state, payload) {
      state.freeboard.boardId = payload.boardId;
      state.freeboard.title = payload.title;
      state.freeboard.content = payload.content;
      state.freeboard.userName = payload.userName;
      state.freeboard.readCount = payload.readCount;
      state.freeboard.regDt = payload.regDt;
      state.freeboard.fileList = payload.fileList;
      state.freeboard.isOwner = payload.isOwner;
    },
    SET_FREEBOARD_DETAIL_LIST(state, payload) {
      //console.log(payload);
      state.freeboard.listDetail = payload;
    },
  },
  actions: {
    boardList(context) {
      http
        .get("/boards", {
          // get query string
          params: {
            limit: this.state.board.limit,
            offset: this.state.board.offset,
            searchWord: this.state.board.searchWord,
          },
        })
        .then(({ data }) => {
          console.log("BoardMainVue: data : ");
          console.log(data);
          if (data.result == "login") {
            router.push("/login");
          } else {
            context.commit("SET_BOARD_LIST", data.list);
            context.commit("SET_BOARD_TOTAL_LIST_ITEM_COUNT", data.count);
          }
        });
    },
    freeboardList(context) {
      http
        .get("/freeboards", {
          // get query string
          params: {
            limit: this.state.freeboard.limit,
            offset: this.state.freeboard.offset,
            searchWord: this.state.freeboard.searchWord,
          },
        })
        .then(({ data }) => {
          console.log("BoardMainVue: data : ");
          console.log(data);
          if (data.result == "login") {
            router.push("/login");
          } else {
            context.commit("SET_FREEBOARD_LIST", data.list);
            context.commit("SET_FREEBOARD_TOTAL_LIST_ITEM_COUNT", data.count);
          }
        });
    },
    // setMessage(context, message){
    //   context.commit('mutateSetMessage', message)
    // },
  },
  getters: {
    isLogin: function(state) {
      return state.login.isLogin;
    },
    getBoardList: function(state) {
      return state.board.list;
    },
    getFreeBoardList: function(state) {
      return state.freeboard.list;
    },
  },
});
