Vue.use(VueResource);

var spinner = new Vue({
    el: '#spinner',
    data: {
        displ: false
    }
});
var buttonJson = new Vue({
    el: '#buttonJson',
    data: {
        disable: false
    },
    methods: {
        jsonToXml: function () {
            buttonJson.disable = true;
            buttonXml.disable = true;
            xmlArea.disable = true;
            jsonArea.disable = true;
            spinner.displ = true;
            this.$http.post('./parse/to-xml', jsonArea.message, {
                headers: {
                    accept: 'text/plain',
                    'Content-Type': 'text/plain'
                }
            }).then(response => {
                xmlArea.message = response.bodyText;
                if (page.page === 1) {
                    histroy.getLastAddedHistory();
                }
                page.getNumberOfPage();
                buttonJson.disable = false;
                buttonXml.disable = false;
                xmlArea.disable = false;
                jsonArea.disable = false;
                spinner.displ = false;
            }, response => {
                alert(response.bodyText);
                buttonJson.disable = false;
                buttonXml.disable = false;
                xmlArea.disable = false;
                jsonArea.disable = false;
                spinner.displ = false;
            });
        }
    }
});

var buttonXml = new Vue({
    el: '#buttonXml',
    data: {
        disable: false
    },
    methods: {
        xmlToJson: function () {
            buttonJson.disable = true;
            buttonXml.disable = true;
            xmlArea.disable = true;
            jsonArea.disable = true;

            this.$http.post('./parse/to-json', xmlArea.message, {
                headers: {
                    accept: 'text/plain',
                    'Content-Type': 'text/plain'
                }
            }).then(response => {
                jsonArea.message = response.bodyText;
                if (page.page === 1) {
                    histroy.getLastAddedHistory();
                }
                page.getNumberOfPage();
                buttonJson.disable = false;
                buttonXml.disable = false;
                xmlArea.disable = false;
                jsonArea.disable = false;
            }, response => {
                alert(response.bodyText);
                buttonJson.disable = false;
                buttonXml.disable = false;
                xmlArea.disable = false;
                jsonArea.disable = false;
            });
        },

    }
});

var page = new Vue({
    el: '#page',
    data: {
        items: [],
        page: 1
    },
    methods: {
        getPageFunction: function (page) {
            this.page = page;
            histroy.getTenHistory(page);
        },
        getNumberOfPage: function () {
            this.items = [];
            this.$http.get('./history/pages', {params: {count: 10}}).then(response => {

                for (var i = 0; i < response.body; i++) {

                    this.items.push({
                        page: i + 1
                    })

                }
            })
        },
    },
    created: function () {
        this.getNumberOfPage();
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
        },
        getFullJsonFromHistory: function(index){
            $("#myModal .modal-body").html(histroy.items[index].jsonFull);
            $("#myModal").modal().show();
        },
        getFullXmlFromHistory: function(index){
            $("#myModal2 .modal-body").text(histroy.items[index].xmlFull);
            $("#myModal2").modal().show();
        },
        getTenHistory: function (page) {
            this.$http.get('./history/stories', {params: {page: page, count: 10}}).then(response => {
                histroy.items = [];
                for (var i = 0; i < response.body.length; i++) {
                    var id = response.body[i];
                    histroy.historyc(id)
                }
            })
        },
        getLastAddedHistory: function () {
            this.$http.get('./history/last', {
                headers: {
                    accept: 'application/json'
                }
            }).then(response => {
                histroy.items.pop();
                histroy.items.unshift({

                    id: response.body['id'],
                    user: response.body['name'],
                    date: new Date(response.body['date']).getDate() + "-" + (new Date(response.body['date']).getMonth() + 1)
                        + "-" + new Date(response.body['date']).getFullYear() + " " +
                        new Date(response.body['date']).getHours() + ":" + new Date(response.body['date']).getMinutes(),
                    jsonFull: response.body['json'],
                    xmlFull: response.body['xml']
                })
            });
        }
    },
    created: function () {
        this.getTenHistory(1);
    }
});

var jsonArea = new Vue({
    el: '#areaJson',
    data:
        {
            disable: false,
            message: ''
        }

});

var xmlArea = new Vue({
    el: '#areaXml',
    data:
        {
            disable: false,
            message: ''
        }
});