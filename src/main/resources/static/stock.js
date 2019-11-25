angular.module('stockApp', [])
    .controller('StockController', function ($http) {
        var stockList = this;
        stockList.stocks = [
            {text: 'learn AngularJS', done: true},
            {text: 'build an AngularJS app', done: false}];


        stockList.showStocks = function () {
            $http({
                method: "GET",
                url: "/api/stocks"
            }).then(function mySuccess(response) {
                stockList.stocks = response.data;
            }, function myError(response) {
                console.error(response)
            });
        }
    });