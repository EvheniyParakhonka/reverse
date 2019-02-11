Vue.use(VueResource);

var buttonJson = new Vue({
    el: '#buttonJson',
    methods: {
        jsonToXml: function () {
            this.$http.post('./json', jsonArea.message, {
                headers: {
                    accept: 'text/plain',
                    'Content-Type': 'text/plain'
                }
            }).then(response => {

                // get body data
                xmlArea.message = response.bodyText;
            }, response => {
                alert(response.body)
            });

            this.$http.get('./history', {
                headers: {
                    accept: 'application/json'
                }
            }).then(response => {
                histroy.historyc(response.body)
            });
        }
    }
});

var page = new Vue({
    el: '#page',
    data: {
        items: []
    },
    methods: {
        getPageFunction: function (page) {
            this.$http.get('./history/ten',{params:  {page: page}}).then(response => {
                histroy.items=[];
                for (var i = 0; i < response.body.length; i++) {
                    var id = response.body[i];
                    histroy.historyc(id)

                }


            })
        }
    },
    created: function () {
        this.$http.get('./history/pages').then(response => {

            for (var i = 0; i < response.body; i++) {

                this.items.push({
                   page: i + 1
                })

            }


        })
    }
});

var buttonXml = new Vue({
    el: '#buttonXml',
    methods: {
        xmlToJson: function () {
            this.$http.post('./xml', xmlArea.message, {
                headers: {
                    accept: 'text/plain',
                    'Content-Type': 'text/plain'
                }
            }).then(response => {

                // get body data
                jsonArea.message = response.bodyText;
            }, response => {
                alert(response.body)
            });
            this.$http.get('./history', {
                headers: {
                    accept: 'application/json'
                }
            }).then(response => {
                histroy.historyc(response.body)
            });
        },

    }
});

var histroy = new Vue({
    el: '#v-for-object',
    data: {
        items: []
    },
    methods: {
        historyc: function (id) {
            histroy.items.push({

                id: id['id'],
                user: id['name'],
                date: new Date(id['date']).getDate() + "-" + (new Date(id['date']).getMonth() + 1)
                    + "-" + new Date(id['date']).getFullYear() + " " +
                    new Date(id['date']).getHours() + ":" + new Date(id['date']).getMinutes(),
                jsonFull: id['json'],
                xmlFull: id['xml']
            })
        }
    },
    created: function () {
        this.$http.get('./history/ten', {params:  {page: 1}} ).then(response => {

            for (var i = 0; i < response.body.length; i++) {
                var id = response.body[i];
                histroy.historyc(id)

            }


        })

    }
});

var jsonArea = new Vue({
    el: '#areaJson',
    data:
        {
            message: ''
        }

});

var xmlArea = new Vue({
    el: '#areaXml',
    data:
        {
            message: ''
        }
});