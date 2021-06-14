<template>
  <div>
    <!-- ======= Hero Section ======= -->
    <section id="hero">
      <div class="hero-container" data-aos="fade-up">
        <h1>Welcome to HappyHouse</h1>
        <br />
        <h2>We are team of talented real state agents searching houses for You !</h2>
        <a href="#about" class="btn-get-started scrollto"><i class="bx bx-chevrons-down"></i></a>
      </div>
    </section>
    <!-- End Hero -->

    <main id="main">
      <!-- ======= About Section ======= -->
      <section id="about" class="about">
        <div class="container">
          <div class="row no-gutters">
            <div class="content col-xl-5 d-flex align-items-stretch" data-aos="fade-up">
              <div class="content">
                <h3>With HappyHouse !</h3>
                <p>
                  HappyHouse와 함께 최고의 부동산 서비스를 경험하세요.
                </p>
                <router-link href="#hero" to="/register" class="about-btn"
                  >Join us<i class="bx bx-chevron-right"></i
                ></router-link>
              </div>
            </div>
            <div class="col-xl-7 d-flex align-items-stretch">
              <div class="icon-boxes d-flex flex-column justify-content-center">
                <div class="row">
                  <div class="col-md-6 icon-box" data-aos="fade-up" data-aos-delay="100">
                    <i class="bx bx-home"></i>
                    <h4>부동산 정보</h4>
                    <p>
                      서울시내 아파트들의 지도상의 위치, 건축년도, <br />법정동 및 주소 등
                      상세정보를 제공합니다.
                    </p>
                  </div>
                  <div class="col-md-6 icon-box" data-aos="fade-up" data-aos-delay="200">
                    <i class="bx bx-search-alt"></i>
                    <h4>부동산 세부검색</h4>
                    <p>
                      서울시내 아파트들의 효과적인 매매를 위해,
                      <br />거래내역과 세부 검색 기능을 제공합니다.
                    </p>
                  </div>
                  <div class="col-md-6 icon-box" data-aos="fade-up" data-aos-delay="300">
                    <i class="bx bx-heart-circle"></i>
                    <h4>나만의 부동산</h4>
                    <p>관심 아파트 및 내 아파트를 저장하여, <br />편리한 관리 기능을 제공합니다.</p>
                  </div>
                  <div class="col-md-6 icon-box" data-aos="fade-up" data-aos-delay="400">
                    <i class="bx bx-message-dots"></i>
                    <h4>커뮤니티</h4>
                    <p>
                      다양한 회원들과 부동산 거래정보 및 다양한 정보를 교환할 수 있는 커뮤니티
                      서비스를 제공합니다.
                    </p>
                  </div>
                </div>
              </div>
              <!-- End .content-->
            </div>
          </div>
        </div>
      </section>
      <!-- End About Section -->

      <!-- ======= Services Section ======= -->
      <section id="services" class="services">
        <div class="container">
          <div class="section-title" data-aos="fade-in" data-aos-delay="100">
            <h2>Services</h2>
            <p></p>
          </div>

          <div class="row">
            <div class="col-md-6 col-lg-3 d-flex align-items-stretch mb-5 mb-lg-0">
              <div class="icon-box" data-aos="fade-up">
                <div class="icon"><i class="bx bxl-dribbble"></i></div>
                <h4 class="title"><router-link to="/houseboard">부동산 정보</router-link></h4>
                <p class="description">
                  원하는 동네와 아파트를 검색해서 거래 상세정보를 확인하세요!
                </p>
              </div>
            </div>

            <div class="col-md-6 col-lg-3 d-flex align-items-stretch mb-5 mb-lg-0">
              <div class="icon-box" data-aos="fade-up" data-aos-delay="100">
                <div class="icon"><i class="bx bx-file"></i></div>
                <h4 class="title" @click="checkLogin()">
                  <router-link to="/freeboard">자유 게시판</router-link>
                </h4>
                <p class="description">
                  자유 게시판을 통해 HappyHouse <br />
                  사용자들과 함께 소통해보세요~
                </p>
              </div>
            </div>

            <div class="col-md-6 col-lg-3 d-flex align-items-stretch mb-5 mb-lg-0">
              <div class="icon-box" data-aos="fade-up" data-aos-delay="200">
                <div class="icon"><i class="bx bx-tachometer"></i></div>
                <h4 class="title" @click="checkLogin()"><a href="/">마이 부동산</a></h4>
                <p class="description">
                  나만의 관심 부동산을 편리하게 <br />
                  관리하고 이용해 보세요!
                </p>
              </div>
            </div>

            <div class="col-md-6 col-lg-3 d-flex align-items-stretch mb-5 mb-lg-0">
              <div class="icon-box" data-aos="fade-up" data-aos-delay="300">
                <div class="icon"><i class="bx bx-world"></i></div>
                <h4 class="title" @click="checkLogin()">
                  <router-link to="/userinfo">마이 페이지</router-link>
                </h4>
                <p class="description">회원 정보 확인, 수정 및 탈퇴가 <br />가능합니다.</p>
              </div>
            </div>
          </div>
        </div>
      </section>
      <!-- End Services Section -->

      <!-- ======= Portfolio Section ======= -->

      <section id="news" class="portfolio">
        <div class="container">
          <div class="section-title" data-aos="fade-in" data-aos-delay="100">
            <h2>News</h2>
            <div class="row row-cols-1 row-cols-md-3 g-4">
              <div class="col">
                <div class="card h-100">
                  <img
                    src="../../assets/img/portfolio/portfolio-1.jpg"
                    class="card-img-top"
                    alt=""
                  />
                  <div class="card-body" >
                    <a style="color: black; font-size:20px; " class="card-title" href="https://www.donga.com/news/article/all/20210524/107092108/1">
                      주식-코인 자금, 부동산으로?…<br />
                      아파트값 들썩들썩~~~
                    </a>
                    <p class="card-text">
                      24일 한국부동산원에 따르면 올해 1월부터 5월 셋째 주(17일 조사 기준)까지 서울
                      노원구 아파트 매매가격은 2.38% 올랐다. 지난해 연간 상승률 2.06%를 4개월 3주
                      만에 넘어선 것이다. 올해 초 6억∼7억 원 선에 거래되던 상계주공6단지 전용면적
                      58m²는 최근에는 8억 원 안팎에 거래되고 있다. 주식, 가상화폐 시장에서 빠져나온
                      자금은 상가로도 향하고 있다. 서울 강남권 빌딩 중개·매입 컨설턴트 B 씨는 최근
                      30대 초반 투자자에게 100억 원대 상가 빌딩 매입을 주선...
                    </p>
                  </div>
                  <div class="card-footer">
                    <small class="text-muted">Last updated 3 mins ago</small>
                  </div>
                </div>
              </div>
              <div class="col">
                <div class="card h-100">
                  <img
                    src="../../assets/img/portfolio/portfolio-2.jpg"
                    class="card-img-top"
                    alt=""
                  />
                  <div class="card-body">
                    <a style="color: black; font-size:20px; " class="card-title" href="https://newsis.com/view/?id=NISX20210525_0001452881&cid=10201" >
                      "공부하러 왔다" 외국인까지.. <br />
                      부동산 갭투자…첫 적발 빚투 그만!
                    </a>
                    <p class="card-text">
                      이민특수조사대는 지난 2월 국토교통부로부터 지난 3년간 수도권지역 외국인
                      부동산거래신고내역 4만7000여건을 넘겨받아 분석했다. 그 결과 비자 범위를 벗어난
                      활동을 한 외국인 2명을 적발했다고 한다. 조사 결과 A씨는 유학(D-2) 비자로
                      입국한 뒤 인천광역시 소재 빌라 2채를 1억8000여만원에 취득한 뒤 이를 외국인
                      유학생들에게 각각 임대, 매달 90만원 상당 월세를 ...
                    </p>
                  </div>
                  <div class="card-footer">
                    <small class="text-muted">Last updated 1 day ago</small>
                  </div>
                </div>
              </div>
              <div class="col">
                <div class="card h-100">
                  <img
                    src="../../assets/img/portfolio/portfolio-3.jpg"
                    class="card-img-top"
                    alt=""
                  />
                  <div class="card-body">
                    <p style="color: black; font-size:20px; " class="card-title">
                      삼성이 키운 어마무시한 디지털 인재, <br />
                      취업 뚫는 비결은..
                    </p><p> </p><p> </p>
                    <a class="card-text" href="http://localhost:5500/img/portfolio-3.eaea71e9.jpg" style="font-size: 30px;"><br><br>더보기..!!!</a>
                  </div>
                  <div class="card-footer">
                    <small class="text-muted">Last updated 3 days ago</small>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      <!-- End Portfolio Section -->
    </main>
    <!-- End #main -->
  </div>
</template>

<script>
export default {
  name: "Main",

  methods: {
    checkLogin() {
      const loadedToDos = localStorage.getItem("vuex");
      if (loadedToDos != null) {
        const loginObject = JSON.parse(loadedToDos).login;
        console.log("로그인 정보", loginObject.isLogin);

        if (!loginObject.isLogin) {
          alert("로그인하세요!");
          location.href = "/login";
        }
      } else {
        alert("로그인하세요!");
        location.href = "/login";
      }
    },
  },
};
</script>

<style></style>
