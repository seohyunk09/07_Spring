<script setup>
import { computed, reactive, ref } from 'vue'
import { useAuthStore } from '@/stores/auth'
import { useRoute, useRouter } from 'vue-router'

const cr = useRoute()
const router = useRouter()
const auth = useAuthStore()

// 폼 데이터 관리
const member = reactive({
  username: '',
  password: '',
})

const error = ref('')
const disableSubmit = computed(() => !(member.username && member.password))

const login = async () => {
  console.log(member)
  try {
    await auth.login(member)
    if (cr.query.next) {
      router.push({ name: cr.query.next })
    } else {
      // 인증 스토어의 login 액션 호출
      router.push('/') // 성공 시 홈페이지로 이동
    }
  } catch (e) {
    console.log('에러=======', e)
    error.value = e.response.data // 에러 메시지 표시
  }
}
</script>

<template>
  <div class="mt-5 mx-auto" style="width: 500px">
    <h1 class="my-5">
      <i class="fa-solid fa-right-to-bracket"></i>
      로그인
    </h1>

    <form @submit.prevent="login">
      <!-- 사용자 ID 입력 -->
      <div class="mb-3 mt-3">
        <label for="username" class="form-label">
          <i class="fa-solid fa-user"></i> 사용자 ID:
        </label>
        <input type="text" class="form-control" placeholder="사용자 ID" v-model="member.username" />
      </div>

      <!-- 비밀번호 입력 -->
      <div class="mb-3">
        <label for="password" class="form-label">
          <i class="fa-solid fa-lock"></i> 비밀번호:
        </label>
        <input
          type="password"
          class="form-control"
          placeholder="비밀번호"
          v-model="member.password"
        />
      </div>

      <!-- 에러 메시지 표시 -->
      <div v-if="error" class="text-danger">{{ error }}</div>

      <!-- 로그인 버튼 -->
      <button type="submit" class="btn btn-primary mt-4" :disabled="disableSubmit">
        <i class="fa-solid fa-right-to-bracket"></i> 로그인
      </button>
    </form>
  </div>
</template>

<!-- <script setup>
import { computed, reactive, ref, onMounted } from 'vue';
import { useAuthStore } from '@/stores/auth';
import { useRouter } from 'vue-router';

import axios from 'axios'
const userInfo = ref({})
const isLoggedIn = ref(false)

const loginForm = ref({
  username: '',
  password: '',
})

//로그인 처리 함수
const handleLogin = async () => {
  try {
    const response = await axios.post('/api/auth/login', loginForm.value)
    console.log('response.data', response.data)
    const { token, user } = response.data
    localStorage.setItem('authToken', token)
    // 로컬 스토리지에 사용자 정보 저장
    localStorage.setItem('userInfo', JSON.stringify(user))

    //상태 업데이트
    isLoggedIn.value = true

    //로그인 폼에 입력된 값 초기화(v-model)

    loginForm.value = {
      username: '',
      password: '',
    }
  } catch (e) {
    console.error(e)
  }
}

// 로그아웃
const handleLogout = () => {
  // 로컬스토리지 저장된 내용 비우기
  //localStorage.clear()
  localStorage.removeItem('authToken')
  localStorage.removeItem('userInfo')

  isLoggedIn.value = false
}

// // 로그인 상태 확인 함수
const checkLoginStatus = () => {
  const token = localStorage.getItem('authToken')
  const savedUserInfo = localStorage.getItem('userInfo')

  if (token && savedUserInfo) {
    isLoggedIn.value = true
    userInfo.value = JSON.parse(savedUserInfo)
  } else {
    isLoggedIn.value = false
    userInfo.value = {}
  }
}

// // 컴포넌트 마운트 시 로그인 상태 확인
onMounted(() => {
  checkLoginStatus()
})
</script>
<template>
  <h1>LoginPage</h1>

  <div v-if="!isLoggedIn">
    <form @submit.prevent="handleLogin">
      <div>
        username:
        <input type="text" v-model="loginForm.username" />
      </div>
      <div>
        password:
        <input type="password" v-model="loginForm.password" />
      </div>

      <div>
        <button type="submit">로그인</button>
      </div>
    </form>
  </div>
  <div v-else>
    <h1>환영합니다!</h1>
    <p>
      <strong>사용자명:{{ userInfo.username }} </strong>
    </p>
    <p>
      <strong>이메일:{{ userInfo.email }} </strong>
    </p>
    <p>
      <strong>권한:{{ userInfo.roles }} </strong>
    </p>

    <button @click="handleLogout">로그아웃</button>
  </div>
</template>
 -->
