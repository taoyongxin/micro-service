<template>
  <div>
    <button type="default" @click="login">登录</button>
    <!-- <navigator url="../tabBar/index" open-type="switchTab" hover-class="other-navigator-hover">
      <button type="default">登录</button>
    </navigator> -->
  </div>
</template>

<script>
import { request } from "@/utils/request";
import { LOGIN_URL } from "@/utils/api";
export default {
 name: 'login',
  data() {
    return {

    }
  },
  components: {},
  created() {},
  mounted() {},
  methods: {
    login() {
      request(LOGIN_URL, "POST", {
        wxId: "wx_cmj1",
        wxNickname: "cmj111",
        avatarUrl: "avatar13.jpg",
      }).then((res) => {
        console.log(res);
      });
    },
  },
  computed: {}
}
</script>

<style scoped lang="scss">

</style>

