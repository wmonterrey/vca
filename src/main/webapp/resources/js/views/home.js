var ProcessDashboardCenso = function () {
	
return {
  //main function to initiate the module
  init: function (parametros) {
	  
	  viviendasPorDia ();
	  
	  Date.prototype.yyyymmdd = function() {         
		  var yyyy = this.getFullYear().toString();                                    
		  var mm = (this.getMonth()+1).toString(); // getMonth() is zero-based         
		  var dd  = this.getDate().toString();             
		  return yyyy + '-' + (mm[1]?mm:"0"+mm[0]) + '-' + (dd[1]?dd:"0"+dd[0]);
	  };

	  
	  function viviendasPorDia (){
		  $('div.daychart').block({ message: parametros.waitmessage });
		  var fechas = [];
		  var encuestas = [];
		  var activas = [];
		  $.getJSON(parametros.vivPorDiaUrl, function(data) {
			  for (var row in data) {
				  var d1 = (new Date(data[row][0])).yyyymmdd();
				  fechas.push([d1]);
				  encuestas.push([data[row][1]]);
				  activas.push([data[row][3]]);
			  }
			  var dataSet1 = {
					  labels: fechas,
					  datasets: [
						  {
							  type: 'line',
							  label: parametros.vivact,
							  backgroundColor: 'transparent',
				  		      borderColor: 'rgba(0,0,0,0.75)',
				  		      pointBackgroundColor: '#000',
				  		      pointBorderColor: 'rgba(0,0,0,0.75)',
				  		      pointHoverBackgroundColor: '#000',
				  		      borderWidth: 2,
				  		      data: activas
						  },
						  {
							  type: 'bar',
				  		      label: parametros.vivtot,
				  		      backgroundColor: '#ECECEC',
				  		      borderColor: '#ECECEC',
				  		      pointHoverBackgroundColor: '#ECECEC',
				  		      data: encuestas
						  }
						  ]
			  };
			  
			  var options = {
					  responsive: true,
					  maintainAspectRatio: false,
					  scales: {
						  xAxes: [{
							  gridLines: {
								  drawOnChartArea: false,
							  }
						  }],
						  yAxes: [{
							  ticks: {
								  beginAtZero: true,
				  		          maxTicksLimit: 5,
							  }
						  }]
					  },
					  elements: {
						  point: {
							  radius: 4,
				  		      hitRadius: 10,
				  		      hoverRadius: 4,
				  		      hoverBorderWidth: 3,
						  },
						  line: {
							  tension: 0.00001,
						  }
					  },
					  legend: {
						  display: false
					  }
			  };
			  
			  var ctx = $('#dates-chart');
					
			  var mainChart = new Chart(ctx, {
				  type: 'bar',
				  data: dataSet1,
				  options: options
			  });
			  $('div.daychart').unblock();
		  })
		  .fail(function() {
			  alert( "error" );
			  $('div.daychart').unblock();
		  });
	  }
  }

};

}();