var url = location.search;
var theRequest = new Object();
if (url.indexOf("?") != -1) {
    var str = url.substr(1); //substr()方法返回从参数值开始到结束的字符串；
    var strs = str.split("&");
    for (var i = 0; i < strs.length; i++) {
        theRequest[strs[i].split("=")[0]] = ( strs[i].split("=")[1] );
    }
    console.log(theRequest); //此时的theRequest就是我们需要的参数；
}

Vue.component("page", {
    template: "#page",
    data: function () {
        return {
            current: 1,
            showItem: 5,
            allpage: 8
        }
    },

    computed: {
        pages: function () {
            var pag = [];
            if (this.current < this.showItem) { //如果当前的激活的项 小于要显示的条数
                //总页数和要显示的条数那个大就显示多少条
                var i = Math.min(this.showItem, this.allpage);
                while (i) {
                    pag.unshift(i--);
                }
            } else { //当前页数大于显示页数了
                var middle = this.current - Math.floor(this.showItem / 2),//从哪里开始
                    i = this.showItem;
                if (middle > (this.allpage - this.showItem)) {
                    middle = (this.allpage - this.showItem) + 1
                }
                while (i--) {
                    pag.push(middle++);
                }
            }
            return pag
        }
    },
    created() {
        this.$http.post(URL + '/sale/user/detail', {
            'user_id': theRequest.id,
            'current_page': 1,
            'page_size': 15,
            // 'page_size': 1,
        }).then(function (res) {

            if (res.data.mark == '0') {

                this.allpage = res.data.obj.totle_page;

            } else {

            }
        }, function (res) {
            console.log('失败');
            console.log(res);
        })

    },
    methods: {

        goto: function (index) {
            this.current = index;
            //这里可以发送ajax请求
            if(index<=this.allpage){
            vm.HttpDataFun(index);
            }

        }
    }
})

var vm = new Vue({
    el: '#wrap',
    data: {
        categoryList: [],
        sr_user: '',
        totle_page: '',
        user_sale_list: '',
        category_title: '',
        sale_count: '',
        explain: '',
        category_id: '',
        update_category_title: '',
        update_sale_count: '',
        update_explain: '',
        update_category_id: '',
        update_sale_id: '',
        delete_sale_id: '',
        isPopup: false,
        isPopup_info: false,
        is_Change_popup: false,
        is_Delete_popup: false,
        popup_info_data: '',
        pageNum: 1,
        user_name: '',
        user_head_portrait: '',
        file: '',
        src: '',

    },

    methods: {
        HttpDataFun: function (pageNum) {
            this.$http.post(URL + '/sale/user/detail', {
                'user_id': theRequest.id,
                'current_page': pageNum,
                'page_size': 15,
                // 'page_size': 1,
            }).then(function (res) {
                console.log(res);
                // console.log(res.data.obj.categoryList);
                if (res.data.mark == '0') {
                    this.categoryList = res.data.obj.categoryList;
                    this.sr_user = res.data.obj.sr_user;
                    this.totle_page = res.data.obj.totle_page;
                    this.allpage = res.data.obj.totle_page;
                    this.user_sale_list = res.data.obj.user_sale_list;
                    this.user_name = res.data.obj.sr_user.user_name;
                    this.user_head_portrait = res.data.obj.sr_user.user_head_portrait;
                    for (var i = 0; i < this.user_sale_list.length; i++) {
                        this.user_sale_list[i].update_time = vm.TimeFun(this.user_sale_list[i].update_time)
                    }

                } else {

                }
            }, function (res) {
                console.log('失败');
                console.log(res);
            })
        },
        //转化时间
        TimeFun: function (val) {
            var times = new Date(val);
            var Years = times.getFullYear();
            var Months = times.getMonth() + 1 < 10 ? '0' + (times.getMonth() + 1) : times.getMonth() + 1;
            var Dates = times.getDate() < 10 ? '0' + times.getDate() : times.getDate();
            var Hours = times.getHours() < 10 ? '0' + times.getHours() : times.getHours();
            var Minutes = times.getMinutes() < 10 ? '0' + times.getMinutes() : times.getMinutes();
            var Seconds = times.getSeconds() < 10 ? '0' + times.getSeconds() : times.getSeconds();
            return Years + '.' + Months + '.' + Dates + ' ' + Hours + ':' + Minutes + ':' + Seconds;
        },
        //改动成功弹框内容
        ChangeDataFun: function (info) {
            this.isPopup = true;
            this.isPopup_info = true;
            this.popup_info_data = info;
        },
        //修改成功弹框显示2S
        hiddenFun: function () {
            var that = this;
            var timeOut = setTimeout(function () {
                that.isPopup = false;
                that.isPopup_info = false;
                clearTimeout(timeOut);
            }, 2000)
        },
        //添加数据
        AddData: function () {
            console.log(this.category_title)
            console.log(this.sale_count);
            console.log(this.explain);
            if (this.explain == '') {
                this.explain = '无'
            }
            if (this.category_title != '' && this.sale_count != '') {
                // vm.IdNameFun(this.category_title, this.category_id);
                for (var i = 0; i < this.categoryList.length; i++) {
                    if (this.category_title == this.categoryList[i].category_name) {
                        this.category_id = this.categoryList[i].category_id
                    }
                }
                this.$http.post(URL + '/sale/user/sale/insert', {
                    'user_id': theRequest.id,
                    // 'user_id': 1,//用户id
                    'category_id': this.category_id, //类别id
                    'sale_money': this.sale_count, //销售金额
                    'sale_comment': this.explain//销售备注
                }).then(function (res) {
                    console.log(res);
                    if (res.data.mark == '0') {
                        console.log('录入成功');
                        //弹框信息提示
                        vm.ChangeDataFun('录入成功');
                        //2s后弹框消失
                        vm.hiddenFun();
                        //清空输入框
                        this.explain = '';
                        this.sale_count = '';
                        this.category_title = '';
                        vm.HttpDataFun(this.pageNum);
                    } else {
                        alert(res.data.tip)
                    }
                }, function (res) {
                    console.log('失败');
                    console.log(res);
                })
            } else {
                alert('请选择类别或填写金额')
            }
        },
        //修改数据数据处理
        Update: function (item) {
            console.log(item);
            this.update_category_title = item.category_name;
            this.update_sale_count = item.sale_money;
            this.update_explain = item.sale_comment;
            this.update_sale_id = item.sale_id;
            for (var i = 0; i < this.categoryList.length; i++) {
                if (this.update_category_title == this.categoryList[i].category_name) {
                    this.update_category_id = this.categoryList[i].category_id
                }
            }
            //显示弹框
            this.isPopup = true;
            this.is_Change_popup = true;

        },
        //确定修改
        SureUpdate: function () {
            if (this.update_explain == '') {
                this.update_explain = '无'
            }
            if (this.update_category_title != '' && this.update_sale_count != '') {

                this.$http.post(URL + '/sale/user/sale/update', {
                    'category_id': this.update_category_id, //类别id
                    'sale_id': this.update_sale_id, //销售记录id
                    'sale_money': this.update_sale_count, //销售金额
                    'sale_comment': this.update_explain//销售备注
                }).then(function (res) {
                    console.log(res);
                    if (res.data.mark == '0') {
                        console.log('修改成功');

                        vm.CloseUpdate('修改成功');
                        // vm.hiddenFun();
                        vm.HttpDataFun(this.pageNum);
                    } else {
                        alert(res.data.tip)
                    }
                }, function (res) {
                    console.log('失败');
                    console.log(res);
                })
            } else {
                alert('请选择修改的类别或填写修改金额')
            }
        },
        //取消修改
        CloseUpdate: function (val) {
            this.isPopup = false;
            this.is_Change_popup = false;
            this.update_category_title = '';
            this.update_sale_count = '';
            this.update_explain = '';
            if (val == '修改成功') {
                vm.ChangeDataFun('修改成功');
                vm.hiddenFun();
            }

        },
        //删除数据处理
        DeleteData: function (item) {
            console.log(item);
            this.delete_sale_id = item.sale_id;
            this.isPopup = true;
            this.is_Delete_popup = true;
        },
        //确定删除
        DeleteSureFun: function () {
            if (this.delete_sale_id != '') {
                this.$http.post(URL + '/sale/user/sale/delete', {
                    "sale_id": this.delete_sale_id, //销售记录id
                }).then(function (res) {
                    console.log(res);
                    if (res.data.mark == '0') {
                        console.log('删除成功');
                        // this.isPopup = true;
                        // this.isPopup_info = true;
                        // this.popup_info_data = '删除成功';
                        this.is_Delete_popup = false;
                        vm.ChangeDataFun('删除成功');
                        vm.hiddenFun();
                        //删除刷新页码问题
                        vm.HttpDataFun(this.pageNum);

                    } else {
                        alert(res.data.tip)
                    }
                }, function (res) {
                    console.log('删除失败');
                    console.log(res);
                })
            }

        },
        //取消删除
        DeleteCancelFun: function () {
            console.log('删除取消');
            this.isPopup = false;
            this.is_Delete_popup = false;
        },

        //返回首页
        GoHomeFun: function () {
            history.go(-1)
        },
        //退出
        QuitLoginFun: function () {
            sessionStorage.removeItem("WS_username");
            sessionStorage.removeItem("WS_password");
            history.go(-1)
        },
        //修改头像
        changeImage: function (event) {
            this.file = event.target.files[0];//获取文件
            console.log(this.file)
            let formdata = new FormData();
            formdata.append('user_head_portrait', this.file);
            formdata.append('user_id', theRequest.id);
            let config = {
                headers: {
                    'Content-Type': 'multipart/form-data',
                    emulateJSON: true,
                }
            };
            this.$http.post(URL + '/sale/user/update', formdata, config).then(function (res) {
                console.log(res);
                if (res.data.mark == '0') {
                    console.log('图片修改成功');
                    vm.HttpDataFun(this.pageNum);
                } else {

                }
            }, function (res) {
                console.log('失败');
                console.log(res);
            })
        },

    }
})
vm.HttpDataFun(this.pageNum);
