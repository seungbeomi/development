<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html ng-app>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.0-beta.8/angular.min.js"></script>
</head>
<body>

  <div ng-controller="CartController">
    <div ng-repeat="item in items">
      <span>{{item.title}}</span> 
      <input ng-model="item.quantity"> 
      <span>{{item.price | currency}}</span> 
      <span>{{item.price * item.quantity | currency}}</span>
    </div>
    <div>Total: {{totalCart() | currency}}</div>
    <div>Discount: {{bill.discount | currency}}</div>
    <div>Subtotal: {{subtotal() | currency}}</div>
  </div>

  <script>
			function CartController($scope) {
				$scope.bill = {};
				$scope.items = [ {
					title : 'Paint pots',
					quantity : 8,
					price : 3.95
				}, {
					title : 'Polka dots',
					quantity : 17,
					price : 12.95
				}, {
					title : 'Pebbles',
					quantity : 5,
					price : 6.95
				} ];
				$scope.totalCart = function() {
					var total = 0;
					for ( var i = 0, len = $scope.items.length; i < len; i++) {
						total = total + $scope.items[i].price
								* $scope.items[i].quantity;
					}
					return total;
				}
				$scope.subtotal = function() {
					return $scope.totalCart() - $scope.discount;
				};
				function calculateDiscount(newValue, oldValue, scope) {
					$scope.bill.discount = newValue > 100 ? 10 : 0;
				}
				$scope.$watch($scope.totalCart, calculateDiscount);
			}
		</script>


</body>
</html>