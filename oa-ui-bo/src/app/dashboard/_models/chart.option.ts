export class ChartOptions{
	titleString: string = '';
	xAxisValue: string = '';
	yAxisValue: string = '';
	barChartOptions: any;
	pieChartOptions: any;
	
	constructor(t?: string, x?: string, y?: string){
		this.titleString = t;
		this.xAxisValue = x;
		this.yAxisValue = y;
		this.barChartOptions = this.setBarChartOptions();
		this.pieChartOptions = this.setPieChartOptions();
	}
	
	globalChartOptions: any = {
		responsive: true,
		legend: {
		  display: false,
		  position: 'bottom'
		}
	};
	
	setBarChartOptions(){
		return Object.assign({
			scaleShowVerticalLines: false,
			responsive: true,
			scaleShowValues: true,
			scaleValuePaddingX: 10,
			scaleValuePaddingY: 10,
			title: {
			  display: true,
			  text: this.titleString,
			  fontColor: 'black',
			  fontSize: 16
			},
			scales: {
			  yAxes: [{
				ticks: {
				  beginAtZero: true
				},
				scaleLabel: {
					display: true,
					labelString: this.yAxisValue
				}
			  }],
			  xAxes: [{
				scaleLabel: {
					display: true,
					labelString: this.xAxisValue
				}
			  }]
			},
			animation: {
			  onComplete: function () {
				var ctx = this.chart.ctx;
				ctx.textAlign = "center";
				ctx.textBaseline = "middle";
				var chart = this;
				var datasets = this.config.data.datasets;

				datasets.forEach(function (dataset: Array<any>, i: number) {
				  ctx.font = "10px Arial";

				  ctx.fillStyle = "Black";
				  chart.getDatasetMeta(i).data.forEach(function (p: any, j: any) {
					ctx.fillText(datasets[i].data[j], p._model.x, p._model.y - 20);
				  });

				});
			  }
			},
		}, this.globalChartOptions);
	}
	
	setPieChartOptions(){
		return Object.assign({
			title: {
			  display: true,
			  text: this.titleString,
			  fontColor: 'black',
			  fontSize: 16
			}
		}, this.globalChartOptions);
	}
	
}