var vm = new Vue({
    el: '#wrap',
    data: {
        is_login: false,
        noLogin: true,
        logged: false,
        input_username: '',
        input_password: '',
        user_id: '',
        user_names: '',
        user_head_portrait: '',
        clock_time: '',
        userSaleList: [],
        categorySaleList: [],
        userDayList: [],
        userWeekList: [],
        userMonthList: [],

        // day_rank:'day_rank',
        // day_rank_fore:'day_rank_fore',
    },
    methods: {
        //首页数据的请求
        HTTPDataFun: function () {
            this.$http.post(URL + '/sale/user/index', {}).then(function (res) {
                console.log(res);
                this.userSaleList = res.data.obj.userSaleList;
                this.categorySaleList = res.data.obj.categorySaleList;
                this.userDayList = res.data.obj.userDayList;
                this.userWeekList = res.data.obj.userWeekList;
                this.userMonthList = res.data.obj.userMonthList;
                for (var i = 0; i < this.userSaleList.length; i++) {
                    this.userSaleList[i].sale_time = vm.clock_timeFun(this.userSaleList[i].sale_time)

                }

            }, function (res) {
                console.log('失败');
                console.log(res);
            });
            vm.LoggedPersonalFun();
        },
        clock_timeFun: function (val) {
            if (val == 'T') {
                var Years = new Date().getFullYear();
                var Months = new Date().getMonth() + 1 < 10 ? '0' + (new Date().getMonth() + 1) : new Date().getMonth() + 1;
                var Dates = new Date().getDate() < 10 ? '0' + new Date().getDate() : new Date().getDate();
                var Hours = new Date().getHours() < 10 ? '0' + new Date().getHours() : new Date().getHours();
                var Minutes = new Date().getMinutes() < 10 ? '0' + new Date().getMinutes() : new Date().getMinutes();
                var Seconds = new Date().getSeconds() < 10 ? '0' + new Date().getSeconds() : new Date().getSeconds();
                this.clock_time = Years + '.' + Months + '.' + Dates + ' ' + Hours + ':' + Minutes + ':' + Seconds;
            } else {
                var times = new Date(val);
                var m_Months = times.getMonth() + 1 < 10 ? '0' + (times.getMonth() + 1) : times.getMonth() + 1;
                var m_Dates = times.getDate() < 10 ? '0' + times.getDate() : times.getDate();
                var m_Hours = times.getHours() < 10 ? '0' + times.getHours() : times.getHours();
                var m_Minutes = times.getMinutes() < 10 ? '0' + times.getMinutes() : times.getMinutes();
                return m_Months + '.' + m_Dates + ' ' + m_Hours + ':' + m_Minutes;
            }
        },
        //登录弹框显示
        ShowLoginFun: function () {
            this.is_login = true;
        },
        //登录弹框关闭
        CloseLoginFun: function () {
            this.is_login = false;
        },
        //用户登录
        UserLoginFun: function () {
            console.log(this.input_username);
            console.log(this.input_password);
            if (this.input_username !== '' && this.input_password !== '') {
                this.$http.post(URL + '/sale/user/login', {
                    'name': this.input_username,
                    'password': this.input_password,
                }).then(function (res) {
                    console.log(res);
                    if (res.data.mark == '0') {
                        this.is_login = false;
                        this.noLogin = false;
                        this.logged = true;
                        this.user_id = res.data.obj.user_id;
                        this.user_names = res.data.obj.user_name;
                        this.user_head_portrait = res.data.obj.user_head_portrait;
                        sessionStorage.setItem("WS_username", this.input_username);
                        sessionStorage.setItem("WS_password", this.input_password);
                    } else {
                        alert('用户名或密码不正确')
                    }
                }, function (res) {
                    console.log('失败');
                    console.log(res);
                })
            }

        },
        //个人中心返回首页已登录状态数据请求
        LoggedPersonalFun: function () {
            var WS_username = sessionStorage.getItem("WS_username");
            var WS_password = sessionStorage.getItem("WS_password");
            if (WS_username !== '' &&WS_password!==null&& WS_password !== ''&& WS_password !== null) {
                this.$http.post(URL + '/sale/user/login', {
                    'name': WS_username,
                    'password': WS_password,
                }).then(function (res) {
                    console.log(res);
                    if (res.data.mark == '0') {
                        this.is_login = false;
                        this.noLogin = false;
                        this.logged = true;
                        this.user_id = res.data.obj.user_id;
                        this.user_names = res.data.obj.user_name;
                        this.user_head_portrait = res.data.obj.user_head_portrait;
                    } else {
                        alert('请重新登录')
                    }
                }, function (res) {
                    console.log('失败');
                    console.log(res);
                })
            }
        },
        //跳转个人中心
        personalCenterFun: function (data) {
            window.location.href = 'personalCenter.html?id=' + data;

        },


    },
})
vm.HTTPDataFun();
var clockTime = setInterval(function () {
    vm.clock_timeFun('T');
}, 1000)

