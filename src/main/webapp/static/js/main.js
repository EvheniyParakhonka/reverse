Vue.use(VueResource);

var button = new Vue({
        el: '#buttons',
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
                    histroy.items.push({
                        id: response.body['id'],
                        dateTime: new Date(response.body['date'])
                    })
                });
            },
            xmlToJson: function () {
                this.$http.post('./xml', xmlArea.message,{
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
                    histroy.items.push({
                        id: response.body['id'],
                        dateTime: new Date(response.body['date'])
                    })
                });
            },

        }
    })
;

var histroy = new Vue({
    el: '#v-for-object',
    data: {
        items: []
    },
    methods: {
        getHistory: function (id) {
            this.$http.get('./historyID', {params: {id: id}}).then(response => {
                    jsonArea.message = response.body['json'];
                    xmlArea.message = response.body['xml'];
                }
            )
        }
    },
    created: function () {
        this.$http.get('./history/all').then(response => {

            for (var i = 0; i < response.body.length; i++) {
                var id = response.body[i];
                this.items.push({
                    id: id['id'],
                    dateTime: new Date(id['date'])
                })
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