Vue.use(VueResource);

var button = new Vue({
    el: '#buttons',
    methods: {
        jsonToXml: function () {
            this.$http.post('./json', jsonArea.message).then(response => {

                // get body data
                xmlArea.message = response.bodyText;
            }, response => {
               alert("is not json")
            });
        },
        xmlToJson: function () {
            this.$http.post('./xml', xmlArea.message).then(response => {

                // get body data
                jsonArea.message = response.bodyText;
            }, response => {
                alert("is not xml")
            });
        },


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