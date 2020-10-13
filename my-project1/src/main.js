import Vue from 'vue'
import App from './App'

Vue.config.productionTip = false
// Vue.prototype.$Url = 'http://localhost:6666/api';

App.mpType = 'app'

const app = new Vue({
  ...App
})
app.$mount()
